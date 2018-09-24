package net.hockeyapp.android.metrics;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import net.hockeyapp.android.PrivateEventManager.Event;
import net.hockeyapp.android.PrivateEventManager.HockeyEventListener;
import net.hockeyapp.android.metrics.model.Data;
import net.hockeyapp.android.metrics.model.Domain;
import net.hockeyapp.android.metrics.model.EventData;
import net.hockeyapp.android.metrics.model.SessionState;
import net.hockeyapp.android.metrics.model.SessionStateData;
import net.hockeyapp.android.metrics.model.TelemetryData;
import net.hockeyapp.android.utils.AsyncTaskUtils;
import net.hockeyapp.android.utils.HockeyLog;
import net.hockeyapp.android.utils.Util;

public class MetricsManager {
    private static final AtomicInteger ACTIVITY_COUNT = new AtomicInteger(0);
    private static final AtomicLong LAST_BACKGROUND = new AtomicLong(getTime());
    private static final Object LOCK = new Object();
    private static final Integer SESSION_RENEWAL_INTERVAL = Integer.valueOf(20000);
    private static final String TAG = "HA-MetricsManager";
    private static volatile MetricsManager instance;
    private static Channel sChannel;
    private static Sender sSender;
    private static TelemetryContext sTelemetryContext;
    private static boolean sUserMetricsEnabled = true;
    private static WeakReference<Application> sWeakApplication;
    private volatile boolean mSessionTrackingDisabled;
    private TelemetryLifecycleCallbacks mTelemetryLifecycleCallbacks;

    /* renamed from: net.hockeyapp.android.metrics.MetricsManager$1 */
    static class C06281 implements HockeyEventListener {
        C06281() {
        }

        public void onHockeyEvent(Event event) {
            if (event.getType() == 1) {
                MetricsManager.sChannel.synchronize();
            }
        }
    }

    private class TelemetryLifecycleCallbacks implements ActivityLifecycleCallbacks {
        private final long MAX_ACTIVITY_TRANSITION_TIME_MS;
        private Timer mActivityTransitionTimer;
        private TimerTask mActivityTransitionTimerTask;

        /* renamed from: net.hockeyapp.android.metrics.MetricsManager$TelemetryLifecycleCallbacks$1 */
        class C06311 extends TimerTask {
            C06311() {
            }

            public void run() {
                HockeyLog.debug(MetricsManager.TAG, "Application goes into the background. Sending logs.");
                MetricsManager.sChannel.synchronize();
            }
        }

        private TelemetryLifecycleCallbacks() {
            this.MAX_ACTIVITY_TRANSITION_TIME_MS = AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS;
        }

        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
            MetricsManager.this.updateSession();
            if (this.mActivityTransitionTimerTask != null) {
                this.mActivityTransitionTimerTask.cancel();
                this.mActivityTransitionTimerTask = null;
            }
            if (this.mActivityTransitionTimer != null) {
                this.mActivityTransitionTimer.cancel();
                this.mActivityTransitionTimer = null;
            }
        }

        public void onActivityPaused(Activity activity) {
            MetricsManager.LAST_BACKGROUND.set(MetricsManager.getTime());
            this.mActivityTransitionTimer = new Timer();
            this.mActivityTransitionTimerTask = new C06311();
            this.mActivityTransitionTimer.schedule(this.mActivityTransitionTimerTask, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
        }

        public void onActivityStopped(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        public void onActivityDestroyed(Activity activity) {
        }
    }

    MetricsManager(Context context, TelemetryContext telemetryContext, Sender sender, Persistence persistence, Channel channel) {
        sTelemetryContext = telemetryContext;
        if (sender == null) {
            sender = new Sender();
        }
        sSender = sender;
        if (persistence == null) {
            persistence = new Persistence(context, sender);
        } else {
            persistence.setSender(sender);
        }
        sSender.setPersistence(persistence);
        if (channel == null) {
            sChannel = new Channel(sTelemetryContext, persistence);
        } else {
            sChannel = channel;
        }
        persistence.sendAvailable();
    }

    public static void register(Application application) {
        String appIdentifier = Util.getAppIdentifier(application.getApplicationContext());
        if (appIdentifier == null || appIdentifier.length() == 0) {
            throw new IllegalArgumentException("HockeyApp app identifier was not configured correctly in manifest or build configuration.");
        }
        register(application, appIdentifier);
    }

    public static void register(Application application, String appIdentifier) {
        register(application, appIdentifier, null, null, null);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void register(android.app.Application r8, java.lang.String r9, net.hockeyapp.android.metrics.Sender r10, net.hockeyapp.android.metrics.Persistence r11, net.hockeyapp.android.metrics.Channel r12) {
        /*
        r0 = instance;
        if (r0 != 0) goto L_0x0048;
    L_0x0004:
        r7 = LOCK;
        monitor-enter(r7);
        r6 = instance;	 Catch:{ all -> 0x0049 }
        if (r6 != 0) goto L_0x004f;
    L_0x000b:
        r1 = r8.getApplicationContext();	 Catch:{ all -> 0x004c }
        net.hockeyapp.android.Constants.loadFromContext(r1);	 Catch:{ all -> 0x004c }
        r0 = new net.hockeyapp.android.metrics.MetricsManager;	 Catch:{ all -> 0x004c }
        r1 = r8.getApplicationContext();	 Catch:{ all -> 0x004c }
        r2 = new net.hockeyapp.android.metrics.TelemetryContext;	 Catch:{ all -> 0x004c }
        r3 = r8.getApplicationContext();	 Catch:{ all -> 0x004c }
        r2.<init>(r3, r9);	 Catch:{ all -> 0x004c }
        r3 = r10;
        r4 = r11;
        r5 = r12;
        r0.<init>(r1, r2, r3, r4, r5);	 Catch:{ all -> 0x004c }
        r1 = new java.lang.ref.WeakReference;	 Catch:{ all -> 0x0049 }
        r1.<init>(r8);	 Catch:{ all -> 0x0049 }
        sWeakApplication = r1;	 Catch:{ all -> 0x0049 }
    L_0x002e:
        r1 = 0;
        r0.mSessionTrackingDisabled = r1;	 Catch:{ all -> 0x0049 }
        instance = r0;	 Catch:{ all -> 0x0049 }
        r1 = r0.mSessionTrackingDisabled;	 Catch:{ all -> 0x0049 }
        if (r1 != 0) goto L_0x003f;
    L_0x0037:
        r1 = 0;
        r1 = java.lang.Boolean.valueOf(r1);	 Catch:{ all -> 0x0049 }
        setSessionTrackingDisabled(r1);	 Catch:{ all -> 0x0049 }
    L_0x003f:
        monitor-exit(r7);	 Catch:{ all -> 0x0049 }
        r1 = new net.hockeyapp.android.metrics.MetricsManager$1;
        r1.<init>();
        net.hockeyapp.android.PrivateEventManager.addEventListener(r1);
    L_0x0048:
        return;
    L_0x0049:
        r1 = move-exception;
    L_0x004a:
        monitor-exit(r7);	 Catch:{ all -> 0x0049 }
        throw r1;
    L_0x004c:
        r1 = move-exception;
        r0 = r6;
        goto L_0x004a;
    L_0x004f:
        r0 = r6;
        goto L_0x002e;
        */
        throw new UnsupportedOperationException("Method not decompiled: net.hockeyapp.android.metrics.MetricsManager.register(android.app.Application, java.lang.String, net.hockeyapp.android.metrics.Sender, net.hockeyapp.android.metrics.Persistence, net.hockeyapp.android.metrics.Channel):void");
    }

    public static void disableUserMetrics() {
        setUserMetricsEnabled(false);
    }

    public static void enableUserMetrics() {
        setUserMetricsEnabled(true);
    }

    public static boolean isUserMetricsEnabled() {
        return sUserMetricsEnabled;
    }

    private static void setUserMetricsEnabled(boolean enabled) {
        sUserMetricsEnabled = enabled;
        if (instance != null) {
            synchronized (LOCK) {
                if (sUserMetricsEnabled) {
                    instance.registerTelemetryLifecycleCallbacks();
                } else {
                    instance.unregisterTelemetryLifecycleCallbacks();
                }
            }
        }
    }

    @Deprecated
    public static boolean sessionTrackingEnabled() {
        if (instance == null) {
            HockeyLog.error(TAG, "MetricsManager hasn't been registered or User Metrics has been disabled. No User Metrics will be collected!");
            return false;
        } else if (!isUserMetricsEnabled() || instance.mSessionTrackingDisabled) {
            return false;
        } else {
            return true;
        }
    }

    @Deprecated
    public static void setSessionTrackingDisabled(Boolean disabled) {
        if (instance == null || !isUserMetricsEnabled()) {
            HockeyLog.warn(TAG, "MetricsManager hasn't been registered or User Metrics has been disabled. No User Metrics will be collected!");
            return;
        }
        synchronized (LOCK) {
            instance.mSessionTrackingDisabled = disabled.booleanValue();
            if (!disabled.booleanValue()) {
                instance.registerTelemetryLifecycleCallbacks();
            }
        }
    }

    private void registerTelemetryLifecycleCallbacks() {
        if (this.mTelemetryLifecycleCallbacks == null) {
            this.mTelemetryLifecycleCallbacks = new TelemetryLifecycleCallbacks();
        }
        Application application = getApplication();
        if (application != null) {
            application.registerActivityLifecycleCallbacks(this.mTelemetryLifecycleCallbacks);
        }
    }

    private void unregisterTelemetryLifecycleCallbacks() {
        if (this.mTelemetryLifecycleCallbacks != null) {
            Application application = getApplication();
            if (application != null) {
                application.unregisterActivityLifecycleCallbacks(this.mTelemetryLifecycleCallbacks);
            }
            this.mTelemetryLifecycleCallbacks = null;
        }
    }

    public static void setCustomServerURL(String serverURL) {
        if (sSender != null) {
            sSender.setCustomServerURL(serverURL);
        } else {
            HockeyLog.warn(TAG, "HockeyApp couldn't set the custom server url. Please register(...) the MetricsManager before setting the server URL.");
        }
    }

    private static Application getApplication() {
        return sWeakApplication != null ? (Application) sWeakApplication.get() : null;
    }

    private static long getTime() {
        return new Date().getTime();
    }

    protected static Channel getChannel() {
        return sChannel;
    }

    protected void setChannel(Channel channel) {
        sChannel = channel;
    }

    protected static Sender getSender() {
        return sSender;
    }

    protected static void setSender(Sender sender) {
        sSender = sender;
    }

    protected static MetricsManager getInstance() {
        return instance;
    }

    private void updateSession() {
        if (ACTIVITY_COUNT.getAndIncrement() != 0) {
            long now = getTime();
            long then = LAST_BACKGROUND.getAndSet(getTime());
            boolean shouldRenew = now - then >= ((long) SESSION_RENEWAL_INTERVAL.intValue());
            HockeyLog.debug(TAG, "Checking if we have to renew a session, time difference is: " + (now - then));
            if (shouldRenew && sessionTrackingEnabled()) {
                HockeyLog.debug(TAG, "Renewing session");
                renewSession();
            }
        } else if (sessionTrackingEnabled()) {
            HockeyLog.debug(TAG, "Starting & tracking session");
            renewSession();
        } else {
            HockeyLog.debug(TAG, "Session management disabled by the developer");
        }
    }

    @SuppressLint({"StaticFieldLeak"})
    private void renewSession() {
        final String sessionId = UUID.randomUUID().toString();
        try {
            AsyncTaskUtils.execute(new AsyncTask<Void, Void, Void>() {
                protected Void doInBackground(Void... params) {
                    MetricsManager.sTelemetryContext.renewSessionContext(sessionId);
                    MetricsManager.this.trackSessionState(SessionState.START);
                    return null;
                }
            });
        } catch (Throwable e) {
            HockeyLog.error("Could not track session state. Executor rejected async task.", e);
        }
    }

    private void trackSessionState(SessionState sessionState) {
        SessionStateData sessionItem = new SessionStateData();
        sessionItem.setState(sessionState);
        sChannel.enqueueData(createData(sessionItem));
    }

    private static Data<Domain> createData(TelemetryData telemetryData) {
        Data<Domain> data = new Data();
        data.setBaseData(telemetryData);
        data.setBaseType(telemetryData.getBaseType());
        data.QualifiedName = telemetryData.getEnvelopeName();
        return data;
    }

    public static void trackEvent(String eventName) {
        trackEvent(eventName, null);
    }

    public static void trackEvent(String eventName, Map<String, String> properties) {
        trackEvent(eventName, properties, null);
    }

    @SuppressLint({"StaticFieldLeak"})
    public static void trackEvent(String eventName, Map<String, String> properties, Map<String, Double> measurements) {
        if (!TextUtils.isEmpty(eventName)) {
            if (instance == null) {
                HockeyLog.error(TAG, "MetricsManager hasn't been registered or User Metrics has been disabled. No User Metrics will be collected!");
            } else if (isUserMetricsEnabled()) {
                final EventData eventItem = new EventData();
                eventItem.setName(eventName);
                if (properties != null) {
                    eventItem.setProperties(properties);
                }
                if (measurements != null) {
                    eventItem.setMeasurements(measurements);
                }
                try {
                    AsyncTaskUtils.execute(new AsyncTask<Void, Void, Void>() {
                        protected Void doInBackground(Void... params) {
                            MetricsManager.sChannel.enqueueData(MetricsManager.createData(eventItem));
                            return null;
                        }
                    });
                } catch (Throwable e) {
                    HockeyLog.error("Could not track custom event. Executor rejected async task.", e);
                }
            } else {
                HockeyLog.warn("User Metrics is disabled. Will not track event.");
            }
        }
    }
}
