package com.google.android.gms.wallet;

import android.app.Activity;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.internal.wallet.zze;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AutoResolveHelper {
    public static final int RESULT_ERROR = 1;
    private static final long zzp = TimeUnit.MINUTES.toMillis(10);
    @VisibleForTesting
    static long zzq = SystemClock.elapsedRealtime();

    @VisibleForTesting
    static class zza<TResult extends AutoResolvableResult> implements OnCompleteListener<TResult>, Runnable {
        @VisibleForTesting
        private static final Handler zzr = new zze(Looper.getMainLooper());
        @VisibleForTesting
        static final SparseArray<zza<?>> zzs = new SparseArray(2);
        private static final AtomicInteger zzt = new AtomicInteger();
        int zzu;
        private zzb zzv;
        private Task<TResult> zzw;

        zza() {
        }

        public static <TResult extends AutoResolvableResult> zza<TResult> zza(Task<TResult> task) {
            zza<TResult> zza = new zza();
            zza.zzu = zzt.incrementAndGet();
            zzs.put(zza.zzu, zza);
            zzr.postDelayed(zza, AutoResolveHelper.zzp);
            task.addOnCompleteListener(zza);
            return zza;
        }

        public final void zza(zzb zzb) {
            this.zzv = zzb;
            zzb();
        }

        public final void zzb(zzb zzb) {
            if (this.zzv == zzb) {
                this.zzv = null;
            }
        }

        public final void onComplete(@NonNull Task<TResult> task) {
            this.zzw = task;
            zzb();
        }

        public final void run() {
            zzs.delete(this.zzu);
        }

        private final void zzb() {
            if (this.zzw != null && this.zzv != null) {
                zzs.delete(this.zzu);
                zzr.removeCallbacks(this);
                this.zzv.zzb(this.zzw);
            }
        }
    }

    public static class zzb extends Fragment {
        private static String zzaa = "delivered";
        private static String zzx = "resolveCallId";
        private static String zzy = "requestCode";
        private static String zzz = "initializationElapsedRealtime";
        private int zzab;
        private zza<?> zzac;
        @VisibleForTesting
        private boolean zzad;

        private static Fragment zza(int i, int i2) {
            Bundle bundle = new Bundle();
            bundle.putInt(zzx, i);
            bundle.putInt(zzy, i2);
            bundle.putLong(zzz, AutoResolveHelper.zzq);
            Fragment zzb = new zzb();
            zzb.setArguments(bundle);
            return zzb;
        }

        public final void onCreate(@Nullable Bundle bundle) {
            super.onCreate(bundle);
            this.zzab = getArguments().getInt(zzy);
            if (AutoResolveHelper.zzq != getArguments().getLong(zzz)) {
                this.zzac = null;
            } else {
                this.zzac = (zza) zza.zzs.get(getArguments().getInt(zzx));
            }
            boolean z = bundle != null && bundle.getBoolean(zzaa);
            this.zzad = z;
        }

        public final void onResume() {
            super.onResume();
            if (this.zzac != null) {
                this.zzac.zza(this);
                return;
            }
            if (Log.isLoggable("AutoResolveHelper", 5)) {
                Log.w("AutoResolveHelper", "Sending canceled result for garbage collected task!");
            }
            zzb(null);
        }

        public final void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);
            bundle.putBoolean(zzaa, this.zzad);
            zzc();
        }

        public final void onPause() {
            super.onPause();
            zzc();
        }

        private final void zzc() {
            if (this.zzac != null) {
                this.zzac.zzb(this);
            }
        }

        private final void zzb(@Nullable Task<? extends AutoResolvableResult> task) {
            if (!this.zzad) {
                this.zzad = true;
                Activity activity = getActivity();
                activity.getFragmentManager().beginTransaction().remove(this).commit();
                if (task != null) {
                    AutoResolveHelper.zza(activity, this.zzab, (Task) task);
                } else {
                    AutoResolveHelper.zza(activity, this.zzab, 0, new Intent());
                }
            }
        }
    }

    private AutoResolveHelper() {
    }

    @MainThread
    public static <TResult extends AutoResolvableResult> void resolveTask(@NonNull Task<TResult> task, @NonNull Activity activity, int i) {
        zza zza = zza.zza((Task) task);
        activity.getFragmentManager().beginTransaction().add(zzb.zza(zza.zzu, i), "com.google.android.gms.wallet.AutoResolveHelper" + zza.zzu).commit();
    }

    @Nullable
    public static Status getStatusFromIntent(@Nullable Intent intent) {
        return intent == null ? null : (Status) intent.getParcelableExtra("com.google.android.gms.common.api.AutoResolveHelper.status");
    }

    public static void putStatusIntoIntent(@NonNull Intent intent, @Nullable Status status) {
        if (status == null) {
            intent.removeExtra("com.google.android.gms.common.api.AutoResolveHelper.status");
        } else {
            intent.putExtra("com.google.android.gms.common.api.AutoResolveHelper.status", status);
        }
    }

    public static <TResult> void zza(Status status, TResult tResult, TaskCompletionSource<TResult> taskCompletionSource) {
        if (status.isSuccess()) {
            taskCompletionSource.setResult(tResult);
        } else {
            taskCompletionSource.setException(ApiExceptionUtil.fromStatus(status));
        }
    }

    private static void zza(Activity activity, int i, Task<? extends AutoResolvableResult> task) {
        if (activity.isFinishing()) {
            if (Log.isLoggable("AutoResolveHelper", 3)) {
                Log.d("AutoResolveHelper", "Ignoring task result for, Activity is finishing.");
            }
        } else if (task.getException() instanceof ResolvableApiException) {
            try {
                ((ResolvableApiException) task.getException()).startResolutionForResult(activity, i);
            } catch (Throwable e) {
                if (Log.isLoggable("AutoResolveHelper", 6)) {
                    Log.e("AutoResolveHelper", "Error starting pending intent!", e);
                }
            }
        } else {
            int i2;
            Intent intent = new Intent();
            if (task.isSuccessful()) {
                ((AutoResolvableResult) task.getResult()).putIntoIntent(intent);
                i2 = -1;
            } else if (task.getException() instanceof ApiException) {
                ApiException apiException = (ApiException) task.getException();
                putStatusIntoIntent(intent, new Status(apiException.getStatusCode(), apiException.getMessage(), null));
                i2 = 1;
            } else {
                if (Log.isLoggable("AutoResolveHelper", 6)) {
                    Log.e("AutoResolveHelper", "Unexpected non API exception!", task.getException());
                }
                putStatusIntoIntent(intent, new Status(8, "Unexpected non API exception when trying to deliver the task result to an activity!"));
                i2 = 1;
            }
            zza(activity, i, i2, intent);
        }
    }

    private static void zza(Activity activity, int i, int i2, Intent intent) {
        PendingIntent createPendingResult = activity.createPendingResult(i, intent, 1073741824);
        if (createPendingResult != null) {
            try {
                createPendingResult.send(i2);
            } catch (Throwable e) {
                if (Log.isLoggable("AutoResolveHelper", 6)) {
                    Log.e("AutoResolveHelper", "Exception sending pending result", e);
                }
            }
        } else if (Log.isLoggable("AutoResolveHelper", 5)) {
            Log.w("AutoResolveHelper", "Null pending result returned when trying to deliver task result!");
        }
    }
}
