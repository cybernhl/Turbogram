package net.hockeyapp.android.metrics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.view.Display;
import android.view.WindowManager;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import net.hockeyapp.android.Constants;
import net.hockeyapp.android.metrics.model.Application;
import net.hockeyapp.android.metrics.model.Device;
import net.hockeyapp.android.metrics.model.Internal;
import net.hockeyapp.android.metrics.model.Session;
import net.hockeyapp.android.metrics.model.User;
import net.hockeyapp.android.utils.AsyncTaskUtils;
import net.hockeyapp.android.utils.HockeyLog;
import net.hockeyapp.android.utils.Util;

class TelemetryContext {
    private static final String SESSION_IS_FIRST_KEY = "SESSION_IS_FIRST";
    private static final String SHARED_PREFERENCES_KEY = "HOCKEY_APP_TELEMETRY_CONTEXT";
    private static final String TAG = "HockeyApp-Metrics";
    private final Object IKEY_LOCK;
    final Application mApplication;
    final Device mDevice;
    private String mInstrumentationKey;
    final Internal mInternal;
    private String mPackageName;
    final Session mSession;
    final User mUser;
    private WeakReference<Context> mWeakContext;

    /* renamed from: net.hockeyapp.android.metrics.TelemetryContext$1 */
    class C06351 extends AsyncTask<Void, Object, Object> {
        C06351() {
        }

        protected Object doInBackground(Void... voids) {
            Throwable e;
            try {
                String deviceId = (String) Constants.getDeviceIdentifier().get();
                TelemetryContext.this.setDeviceId(deviceId);
                TelemetryContext.this.setAnonymousUserId(deviceId);
            } catch (InterruptedException e2) {
                e = e2;
                HockeyLog.debug("Error config device identifier", e);
                return null;
            } catch (ExecutionException e3) {
                e = e3;
                HockeyLog.debug("Error config device identifier", e);
                return null;
            }
            return null;
        }
    }

    private TelemetryContext() {
        this.IKEY_LOCK = new Object();
        this.mDevice = new Device();
        this.mSession = new Session();
        this.mUser = new User();
        this.mApplication = new Application();
        this.mInternal = new Internal();
    }

    @SuppressLint({"StaticFieldLeak"})
    TelemetryContext(Context context, String appIdentifier) {
        this();
        this.mWeakContext = new WeakReference(context);
        this.mInstrumentationKey = Util.convertAppIdentifierToGuid(appIdentifier);
        configDeviceContext();
        configInternalContext();
        configApplicationContext();
        AsyncTaskUtils.execute(new C06351());
    }

    void renewSessionContext(String sessionId) {
        configSessionContext(sessionId);
    }

    private void configSessionContext(String sessionId) {
        HockeyLog.debug(TAG, "Configuring session context");
        setSessionId(sessionId);
        HockeyLog.debug(TAG, "Setting the isNew-flag to true, as we only count new sessions");
        setIsNewSession("true");
        Context context = getContext();
        if (context == null) {
            HockeyLog.warn(TAG, "Failed to write to SharedPreferences, context is null");
            return;
        }
        SharedPreferences settings = context.getSharedPreferences(SHARED_PREFERENCES_KEY, 0);
        if (settings.getBoolean(SESSION_IS_FIRST_KEY, false)) {
            setIsFirstSession("false");
            HockeyLog.debug(TAG, "It's not their first session, writing false to SharedPreferences.");
            return;
        }
        Editor editor = settings.edit();
        editor.putBoolean(SESSION_IS_FIRST_KEY, true);
        editor.apply();
        setIsFirstSession("true");
        HockeyLog.debug(TAG, "It's our first session, writing true to SharedPreferences.");
    }

    private void configApplicationContext() {
        HockeyLog.debug(TAG, "Configuring application context");
        this.mPackageName = "";
        if (Constants.APP_PACKAGE != null) {
            this.mPackageName = Constants.APP_PACKAGE;
        }
        setAppVersion(String.format("%s (%S)", new Object[]{Constants.APP_VERSION_NAME, Constants.APP_VERSION}));
        setSdkVersion("android:" + "5.1.0");
    }

    private void configDeviceContext() {
        HockeyLog.debug(TAG, "Configuring device context");
        setOsVersion(VERSION.RELEASE);
        setOsName("Android");
        setDeviceModel(Build.MODEL);
        setDeviceOemName(Build.MANUFACTURER);
        setOsLocale(Locale.getDefault().toString());
        setOsLanguage(Locale.getDefault().getLanguage());
        updateScreenResolution();
        Context context = getContext();
        TelephonyManager telephonyManager = context != null ? (TelephonyManager) context.getSystemService("phone") : null;
        if (telephonyManager == null || telephonyManager.getPhoneType() != 0) {
            setDeviceType("Phone");
        } else {
            setDeviceType("Tablet");
        }
        if (Util.isEmulator()) {
            setDeviceModel("[Emulator]" + this.mDevice.getModel());
        }
    }

    void updateScreenResolution() {
        Point size;
        Display d;
        Context context = getContext();
        if (context != null) {
            int width;
            int height;
            WindowManager wm = (WindowManager) context.getSystemService("window");
            if (VERSION.SDK_INT >= 17) {
                size = new Point();
                d = wm.getDefaultDisplay();
                if (d != null) {
                    d.getRealSize(size);
                    width = size.x;
                    height = size.y;
                } else {
                    width = 0;
                    height = 0;
                }
            } else {
                try {
                    Method mGetRawW = Display.class.getMethod("getRawWidth", new Class[0]);
                    Method mGetRawH = Display.class.getMethod("getRawHeight", new Class[0]);
                    Display display = wm.getDefaultDisplay();
                    width = ((Integer) mGetRawW.invoke(display, new Object[0])).intValue();
                    height = ((Integer) mGetRawH.invoke(display, new Object[0])).intValue();
                } catch (Exception ex) {
                    size = new Point();
                    d = wm.getDefaultDisplay();
                    if (d != null) {
                        d.getSize(size);
                        width = size.x;
                        height = size.y;
                    } else {
                        width = 0;
                        height = 0;
                    }
                    HockeyLog.debug(TAG, "Couldn't determine screen resolution: " + ex.toString());
                }
            }
            setScreenResolution(String.valueOf(height) + "x" + String.valueOf(width));
        }
    }

    void configInternalContext() {
        setSdkVersion("android:" + "5.1.0");
    }

    private Context getContext() {
        return this.mWeakContext != null ? (Context) this.mWeakContext.get() : null;
    }

    protected String getPackageName() {
        return this.mPackageName;
    }

    protected Map<String, String> getContextTags() {
        Map<String, String> contextTags = new LinkedHashMap();
        synchronized (this.mApplication) {
            this.mApplication.addToHashMap(contextTags);
        }
        synchronized (this.mDevice) {
            this.mDevice.addToHashMap(contextTags);
        }
        synchronized (this.mSession) {
            this.mSession.addToHashMap(contextTags);
        }
        synchronized (this.mUser) {
            this.mUser.addToHashMap(contextTags);
        }
        synchronized (this.mInternal) {
            this.mInternal.addToHashMap(contextTags);
        }
        return contextTags;
    }

    public String getInstrumentationKey() {
        String str;
        synchronized (this.IKEY_LOCK) {
            str = this.mInstrumentationKey;
        }
        return str;
    }

    public synchronized void setInstrumentationKey(String instrumentationKey) {
        synchronized (this.IKEY_LOCK) {
            this.mInstrumentationKey = instrumentationKey;
        }
    }

    public String getScreenResolution() {
        String screenResolution;
        synchronized (this.mDevice) {
            screenResolution = this.mDevice.getScreenResolution();
        }
        return screenResolution;
    }

    public void setScreenResolution(String screenResolution) {
        synchronized (this.mDevice) {
            this.mDevice.setScreenResolution(screenResolution);
        }
    }

    public String getAppVersion() {
        String ver;
        synchronized (this.mApplication) {
            ver = this.mApplication.getVer();
        }
        return ver;
    }

    public void setAppVersion(String appVersion) {
        synchronized (this.mApplication) {
            this.mApplication.setVer(appVersion);
        }
    }

    public String getAnonymousUserId() {
        String id;
        synchronized (this.mUser) {
            id = this.mUser.getId();
        }
        return id;
    }

    public void setAnonymousUserId(String userId) {
        synchronized (this.mUser) {
            this.mUser.setId(userId);
        }
    }

    public String getSdkVersion() {
        String sdkVersion;
        synchronized (this.mInternal) {
            sdkVersion = this.mInternal.getSdkVersion();
        }
        return sdkVersion;
    }

    public void setSdkVersion(String sdkVersion) {
        synchronized (this.mInternal) {
            this.mInternal.setSdkVersion(sdkVersion);
        }
    }

    public String getSessionId() {
        String id;
        synchronized (this.mSession) {
            id = this.mSession.getId();
        }
        return id;
    }

    public void setSessionId(String sessionId) {
        synchronized (this.mSession) {
            this.mSession.setId(sessionId);
        }
    }

    public String getIsFirstSession() {
        String isFirst;
        synchronized (this.mSession) {
            isFirst = this.mSession.getIsFirst();
        }
        return isFirst;
    }

    public void setIsFirstSession(String isFirst) {
        synchronized (this.mSession) {
            this.mSession.setIsFirst(isFirst);
        }
    }

    public String getIsNewSession() {
        String isNew;
        synchronized (this.mSession) {
            isNew = this.mSession.getIsNew();
        }
        return isNew;
    }

    public void setIsNewSession(String isNewSession) {
        synchronized (this.mSession) {
            this.mSession.setIsNew(isNewSession);
        }
    }

    public String getOsVersion() {
        String osVersion;
        synchronized (this.mDevice) {
            osVersion = this.mDevice.getOsVersion();
        }
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        synchronized (this.mDevice) {
            this.mDevice.setOsVersion(osVersion);
        }
    }

    public String getOsName() {
        String os;
        synchronized (this.mDevice) {
            os = this.mDevice.getOs();
        }
        return os;
    }

    public void setOsName(String osName) {
        synchronized (this.mDevice) {
            this.mDevice.setOs(osName);
        }
    }

    public String getDeviceModel() {
        String model;
        synchronized (this.mDevice) {
            model = this.mDevice.getModel();
        }
        return model;
    }

    public void setDeviceModel(String deviceModel) {
        synchronized (this.mDevice) {
            this.mDevice.setModel(deviceModel);
        }
    }

    public String getDeviceOemName() {
        String oemName;
        synchronized (this.mDevice) {
            oemName = this.mDevice.getOemName();
        }
        return oemName;
    }

    public void setDeviceOemName(String deviceOemName) {
        synchronized (this.mDevice) {
            this.mDevice.setOemName(deviceOemName);
        }
    }

    public String getOsLocale() {
        String locale;
        synchronized (this.mDevice) {
            locale = this.mDevice.getLocale();
        }
        return locale;
    }

    public void setOsLocale(String osLocale) {
        synchronized (this.mDevice) {
            this.mDevice.setLocale(osLocale);
        }
    }

    public String getOSLanguage() {
        String language;
        synchronized (this.mDevice) {
            language = this.mDevice.getLanguage();
        }
        return language;
    }

    public void setOsLanguage(String osLanguage) {
        synchronized (this.mDevice) {
            this.mDevice.setLanguage(osLanguage);
        }
    }

    public String getDeviceId() {
        return this.mDevice.getId();
    }

    public void setDeviceId(String deviceId) {
        synchronized (this.mDevice) {
            this.mDevice.setId(deviceId);
        }
    }

    public String getDeviceType() {
        return this.mDevice.getType();
    }

    public void setDeviceType(String deviceType) {
        synchronized (this.mDevice) {
            this.mDevice.setType(deviceType);
        }
    }
}
