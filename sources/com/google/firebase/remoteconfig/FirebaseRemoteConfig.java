package com.google.firebase.remoteconfig;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.XmlResourceParser;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.config.zzal;
import com.google.android.gms.internal.config.zzam;
import com.google.android.gms.internal.config.zzan;
import com.google.android.gms.internal.config.zzao;
import com.google.android.gms.internal.config.zzap;
import com.google.android.gms.internal.config.zzaq;
import com.google.android.gms.internal.config.zzar;
import com.google.android.gms.internal.config.zzas;
import com.google.android.gms.internal.config.zzat;
import com.google.android.gms.internal.config.zzau;
import com.google.android.gms.internal.config.zzav;
import com.google.android.gms.internal.config.zzaw;
import com.google.android.gms.internal.config.zzax;
import com.google.android.gms.internal.config.zzay;
import com.google.android.gms.internal.config.zzbh;
import com.google.android.gms.internal.config.zze;
import com.google.android.gms.internal.config.zzj;
import com.google.android.gms.internal.config.zzk;
import com.google.android.gms.internal.config.zzv;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseApp;
import com.google.firebase.abt.FirebaseABTesting;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings.Builder;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.concurrent.GuardedBy;

public class FirebaseRemoteConfig {
    public static final boolean DEFAULT_VALUE_FOR_BOOLEAN = false;
    public static final byte[] DEFAULT_VALUE_FOR_BYTE_ARRAY = new byte[0];
    public static final double DEFAULT_VALUE_FOR_DOUBLE = 0.0d;
    public static final long DEFAULT_VALUE_FOR_LONG = 0;
    public static final String DEFAULT_VALUE_FOR_STRING = "";
    public static final int LAST_FETCH_STATUS_FAILURE = 1;
    public static final int LAST_FETCH_STATUS_NO_FETCH_YET = 0;
    public static final int LAST_FETCH_STATUS_SUCCESS = -1;
    public static final int LAST_FETCH_STATUS_THROTTLED = 2;
    public static final int VALUE_SOURCE_DEFAULT = 1;
    public static final int VALUE_SOURCE_REMOTE = 2;
    public static final int VALUE_SOURCE_STATIC = 0;
    @GuardedBy("FirebaseRemoteConfig.class")
    private static FirebaseRemoteConfig zzaf;
    private final Context mContext;
    private zzao zzag;
    private zzao zzah;
    private zzao zzai;
    private zzar zzaj;
    private final FirebaseApp zzak;
    private final ReadWriteLock zzal = new ReentrantReadWriteLock(true);
    private final FirebaseABTesting zzam;

    private FirebaseRemoteConfig(Context context, @Nullable zzao zzao, @Nullable zzao zzao2, @Nullable zzao zzao3, @Nullable zzar zzar) {
        this.mContext = context;
        if (zzar == null) {
            zzar = new zzar();
        }
        this.zzaj = zzar;
        this.zzaj.zzc(zzd(this.mContext));
        this.zzag = zzao;
        this.zzah = zzao2;
        this.zzai = zzao3;
        this.zzak = FirebaseApp.initializeApp(this.mContext);
        this.zzam = zzf(this.mContext);
    }

    public static FirebaseRemoteConfig getInstance() {
        return zzc(FirebaseApp.getInstance().getApplicationContext());
    }

    private static zzao zza(zzas zzas) {
        int i = 0;
        if (zzas == null) {
            return null;
        }
        Map hashMap = new HashMap();
        for (zzav zzav : zzas.zzbf) {
            String str = zzav.namespace;
            Map hashMap2 = new HashMap();
            for (zzat zzat : zzav.zzbo) {
                hashMap2.put(zzat.zzbi, zzat.zzbj);
            }
            hashMap.put(str, hashMap2);
        }
        byte[][] bArr = zzas.zzbg;
        List arrayList = new ArrayList();
        int length = bArr.length;
        while (i < length) {
            arrayList.add(bArr[i]);
            i++;
        }
        return new zzao(hashMap, zzas.timestamp, arrayList);
    }

    @VisibleForTesting
    private final Task<Void> zza(long j, zzv zzv) {
        int i = Integer.MAX_VALUE;
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zzal.readLock().lock();
        try {
            long convert;
            zzj zzj = new zzj();
            zzj.zza(j);
            if (this.zzak != null) {
                zzj.zza(this.zzak.getOptions().getApplicationId());
            }
            if (this.zzaj.isDeveloperModeEnabled()) {
                zzj.zza("_rcn_developer", "true");
            }
            zzj.zza(10300);
            if (!(this.zzah == null || this.zzah.getTimestamp() == -1)) {
                convert = TimeUnit.SECONDS.convert(System.currentTimeMillis() - this.zzah.getTimestamp(), TimeUnit.MILLISECONDS);
                zzj.zzc(convert < 2147483647L ? (int) convert : Integer.MAX_VALUE);
            }
            if (!(this.zzag == null || this.zzag.getTimestamp() == -1)) {
                convert = TimeUnit.SECONDS.convert(System.currentTimeMillis() - this.zzag.getTimestamp(), TimeUnit.MILLISECONDS);
                if (convert < 2147483647L) {
                    i = (int) convert;
                }
                zzj.zzb(i);
            }
            zze.zze.zza(zzv.asGoogleApiClient(), zzj.zzf()).setResultCallback(new zza(this, taskCompletionSource));
            return taskCompletionSource.getTask();
        } finally {
            this.zzal.readLock().unlock();
        }
    }

    private final void zza(TaskCompletionSource<Void> taskCompletionSource, Status status) {
        if (status == null) {
            Log.w("FirebaseRemoteConfig", "Received null IPC status for failure.");
        } else {
            int statusCode = status.getStatusCode();
            String statusMessage = status.getStatusMessage();
            Log.w("FirebaseRemoteConfig", new StringBuilder(String.valueOf(statusMessage).length() + 25).append("IPC failure: ").append(statusCode).append(":").append(statusMessage).toString());
        }
        this.zzal.writeLock().lock();
        try {
            this.zzaj.zzf(1);
            taskCompletionSource.setException(new FirebaseRemoteConfigFetchException());
            zzn();
        } finally {
            this.zzal.writeLock().unlock();
        }
    }

    private static void zza(Runnable runnable) {
        AsyncTask.SERIAL_EXECUTOR.execute(runnable);
    }

    private final void zza(Map<String, Object> map, String str, boolean z) {
        if (str != null) {
            Object obj = (map == null || map.isEmpty()) ? 1 : null;
            Map hashMap = new HashMap();
            if (obj == null) {
                for (String str2 : map.keySet()) {
                    Object obj2 = map.get(str2);
                    if (obj2 instanceof String) {
                        hashMap.put(str2, ((String) obj2).getBytes(zzaq.UTF_8));
                    } else if (obj2 instanceof Long) {
                        hashMap.put(str2, ((Long) obj2).toString().getBytes(zzaq.UTF_8));
                    } else if (obj2 instanceof Integer) {
                        hashMap.put(str2, ((Integer) obj2).toString().getBytes(zzaq.UTF_8));
                    } else if (obj2 instanceof Double) {
                        hashMap.put(str2, ((Double) obj2).toString().getBytes(zzaq.UTF_8));
                    } else if (obj2 instanceof Float) {
                        hashMap.put(str2, ((Float) obj2).toString().getBytes(zzaq.UTF_8));
                    } else if (obj2 instanceof byte[]) {
                        hashMap.put(str2, (byte[]) obj2);
                    } else if (obj2 instanceof Boolean) {
                        hashMap.put(str2, ((Boolean) obj2).toString().getBytes(zzaq.UTF_8));
                    } else {
                        throw new IllegalArgumentException("The type of a default value needs to beone of String, Long, Double, Boolean, or byte[].");
                    }
                }
            }
            this.zzal.writeLock().lock();
            if (obj != null) {
                try {
                    if (this.zzai == null || !this.zzai.zzb(str)) {
                        this.zzal.writeLock().unlock();
                        return;
                    } else {
                        this.zzai.zza(null, str);
                        this.zzai.setTimestamp(System.currentTimeMillis());
                    }
                } catch (Throwable th) {
                    this.zzal.writeLock().unlock();
                }
            } else {
                if (this.zzai == null) {
                    this.zzai = new zzao(new HashMap(), System.currentTimeMillis(), null);
                }
                this.zzai.zza(hashMap, str);
                this.zzai.setTimestamp(System.currentTimeMillis());
            }
            if (z) {
                this.zzaj.zzc(str);
            }
            zzn();
            this.zzal.writeLock().unlock();
        }
    }

    private static FirebaseRemoteConfig zzc(Context context) {
        synchronized (FirebaseRemoteConfig.class) {
            if (zzaf == null) {
                zzao zza;
                zzao zza2;
                zzao zza3;
                zzar zzar;
                zzaw zze = zze(context);
                if (zze != null) {
                    if (Log.isLoggable("FirebaseRemoteConfig", 3)) {
                        Log.d("FirebaseRemoteConfig", "Initializing from persisted config.");
                    }
                    zza = zza(zze.zzbp);
                    zza2 = zza(zze.zzbq);
                    zza3 = zza(zze.zzbr);
                    zzau zzau = zze.zzbs;
                    if (zzau == null) {
                        zzar = null;
                    } else {
                        zzar = new zzar();
                        zzar.zzf(zzau.zzbk);
                        zzar.zza(zzau.zzbl);
                    }
                    if (zzar != null) {
                        zzax[] zzaxArr = zze.zzbt;
                        Map hashMap = new HashMap();
                        if (zzaxArr != null) {
                            for (zzax zzax : zzaxArr) {
                                hashMap.put(zzax.namespace, new zzal(zzax.resourceId, zzax.zzbv));
                            }
                        }
                        zzar.zza(hashMap);
                    }
                } else if (Log.isLoggable("FirebaseRemoteConfig", 3)) {
                    Log.d("FirebaseRemoteConfig", "No persisted config was found. Initializing from scratch.");
                    zzar = null;
                    zza3 = null;
                    zza2 = null;
                    zza = null;
                } else {
                    zzar = null;
                    zza3 = null;
                    zza2 = null;
                    zza = null;
                }
                zzaf = new FirebaseRemoteConfig(context, zza, zza2, zza3, zzar);
            }
        }
        return zzaf;
    }

    private final long zzd(Context context) {
        long j = 0;
        try {
            return Wrappers.packageManager(this.mContext).getPackageInfo(context.getPackageName(), 0).lastUpdateTime;
        } catch (NameNotFoundException e) {
            String packageName = context.getPackageName();
            Log.e("FirebaseRemoteConfig", new StringBuilder(String.valueOf(packageName).length() + 25).append("Package [").append(packageName).append("] was not found!").toString());
            return j;
        }
    }

    private static zzaw zze(Context context) {
        FileInputStream openFileInput;
        Throwable e;
        if (context == null) {
            return null;
        }
        try {
            openFileInput = context.openFileInput("persisted_config");
            try {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = openFileInput.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byte[] toByteArray = byteArrayOutputStream.toByteArray();
                zzay zza = zzay.zza(toByteArray, 0, toByteArray.length);
                zzbh zzaw = new zzaw();
                zzaw.zza(zza);
                if (openFileInput != null) {
                    try {
                        openFileInput.close();
                    } catch (Throwable e2) {
                        Log.e("FirebaseRemoteConfig", "Failed to close persisted config file.", e2);
                    }
                }
                return zzaw;
            } catch (FileNotFoundException e3) {
                e = e3;
                try {
                    if (Log.isLoggable("FirebaseRemoteConfig", 3)) {
                        Log.d("FirebaseRemoteConfig", "Persisted config file was not found.", e);
                    }
                    if (openFileInput != null) {
                        return null;
                    }
                    try {
                        openFileInput.close();
                        return null;
                    } catch (Throwable e4) {
                        Log.e("FirebaseRemoteConfig", "Failed to close persisted config file.", e4);
                        return null;
                    }
                } catch (Throwable e22) {
                    e4 = e22;
                    if (openFileInput != null) {
                        try {
                            openFileInput.close();
                        } catch (Throwable e222) {
                            Log.e("FirebaseRemoteConfig", "Failed to close persisted config file.", e222);
                        }
                    }
                    throw e4;
                }
            } catch (IOException e5) {
                e4 = e5;
                Log.e("FirebaseRemoteConfig", "Cannot initialize from persisted config.", e4);
                if (openFileInput != null) {
                    return null;
                }
                try {
                    openFileInput.close();
                    return null;
                } catch (Throwable e42) {
                    Log.e("FirebaseRemoteConfig", "Failed to close persisted config file.", e42);
                    return null;
                }
            }
        } catch (FileNotFoundException e6) {
            e42 = e6;
            openFileInput = null;
            if (Log.isLoggable("FirebaseRemoteConfig", 3)) {
                Log.d("FirebaseRemoteConfig", "Persisted config file was not found.", e42);
            }
            if (openFileInput != null) {
                return null;
            }
            openFileInput.close();
            return null;
        } catch (IOException e7) {
            e42 = e7;
            openFileInput = null;
            Log.e("FirebaseRemoteConfig", "Cannot initialize from persisted config.", e42);
            if (openFileInput != null) {
                return null;
            }
            openFileInput.close();
            return null;
        } catch (Throwable th) {
            e42 = th;
            openFileInput = null;
            if (openFileInput != null) {
                openFileInput.close();
            }
            throw e42;
        }
    }

    private static FirebaseABTesting zzf(Context context) {
        try {
            return new FirebaseABTesting(context, "frc", 1);
        } catch (NoClassDefFoundError e) {
            Log.w("FirebaseRemoteConfig", "Unable to use ABT: Analytics library is missing.");
            return null;
        }
    }

    private final void zzn() {
        this.zzal.readLock().lock();
        try {
            zza(new zzan(this.mContext, this.zzag, this.zzah, this.zzai, this.zzaj));
        } finally {
            this.zzal.readLock().unlock();
        }
    }

    public boolean activateFetched() {
        this.zzal.writeLock().lock();
        try {
            if (this.zzag == null) {
                return false;
            }
            if (this.zzah == null || this.zzah.getTimestamp() < this.zzag.getTimestamp()) {
                long timestamp = this.zzag.getTimestamp();
                this.zzah = this.zzag;
                this.zzah.setTimestamp(System.currentTimeMillis());
                this.zzag = new zzao(null, timestamp, null);
                zza(new zzam(this.zzam, this.zzah.zzg()));
                zzn();
                this.zzal.writeLock().unlock();
                return true;
            }
            this.zzal.writeLock().unlock();
            return false;
        } finally {
            this.zzal.writeLock().unlock();
        }
    }

    public Task<Void> fetch() {
        return fetch(43200);
    }

    public Task<Void> fetch(long j) {
        return zza(j, new zzv(this.mContext));
    }

    public boolean getBoolean(String str) {
        return getBoolean(str, "configns:firebase");
    }

    public boolean getBoolean(String str, String str2) {
        Lock lock = true;
        if (str2 == null) {
            return false;
        }
        this.zzal.readLock().lock();
        try {
            CharSequence str3;
            if (this.zzah != null && this.zzah.zzb(str, str2)) {
                str3 = new String(this.zzah.zzc(str, str2), zzaq.UTF_8);
                if (zzaq.zzl.matcher(str3).matches()) {
                    return lock;
                }
                if (zzaq.zzm.matcher(str3).matches()) {
                    this.zzal.readLock().unlock();
                    return false;
                }
            }
            if (this.zzai != null && this.zzai.zzb(str, str2)) {
                str3 = new String(this.zzai.zzc(str, str2), zzaq.UTF_8);
                if (zzaq.zzl.matcher(str3).matches()) {
                    this.zzal.readLock().unlock();
                    return true;
                } else if (zzaq.zzm.matcher(str3).matches()) {
                    this.zzal.readLock().unlock();
                    return false;
                }
            }
            this.zzal.readLock().unlock();
            return false;
        } finally {
            lock = this.zzal.readLock();
            lock.unlock();
        }
    }

    public byte[] getByteArray(String str) {
        return getByteArray(str, "configns:firebase");
    }

    public byte[] getByteArray(String str, String str2) {
        if (str2 == null) {
            return DEFAULT_VALUE_FOR_BYTE_ARRAY;
        }
        this.zzal.readLock().lock();
        byte[] bArr;
        if (this.zzah == null || !this.zzah.zzb(str, str2)) {
            try {
                if (this.zzai == null || !this.zzai.zzb(str, str2)) {
                    bArr = DEFAULT_VALUE_FOR_BYTE_ARRAY;
                    this.zzal.readLock().unlock();
                    return bArr;
                }
                bArr = this.zzai.zzc(str, str2);
                this.zzal.readLock().unlock();
                return bArr;
            } finally {
                this.zzal.readLock().unlock();
            }
        } else {
            bArr = this.zzah.zzc(str, str2);
            return bArr;
        }
    }

    public double getDouble(String str) {
        return getDouble(str, "configns:firebase");
    }

    public double getDouble(String str, String str2) {
        double d = DEFAULT_VALUE_FOR_DOUBLE;
        if (str2 != null) {
            this.zzal.readLock().lock();
            try {
                if (this.zzah != null && this.zzah.zzb(str, str2)) {
                    try {
                        d = Double.valueOf(new String(this.zzah.zzc(str, str2), zzaq.UTF_8)).doubleValue();
                    } catch (NumberFormatException e) {
                    }
                }
                if (this.zzai != null && this.zzai.zzb(str, str2)) {
                    try {
                        d = Double.valueOf(new String(this.zzai.zzc(str, str2), zzaq.UTF_8)).doubleValue();
                        this.zzal.readLock().unlock();
                    } catch (NumberFormatException e2) {
                    }
                }
                this.zzal.readLock().unlock();
            } finally {
                this.zzal.readLock().unlock();
            }
        }
        return d;
    }

    public FirebaseRemoteConfigInfo getInfo() {
        FirebaseRemoteConfigInfo zzap = new zzap();
        this.zzal.readLock().lock();
        try {
            zzap.zzb(this.zzag == null ? -1 : this.zzag.getTimestamp());
            zzap.zzf(this.zzaj.getLastFetchStatus());
            zzap.setConfigSettings(new Builder().setDeveloperModeEnabled(this.zzaj.isDeveloperModeEnabled()).build());
            return zzap;
        } finally {
            this.zzal.readLock().unlock();
        }
    }

    public Set<String> getKeysByPrefix(String str) {
        return getKeysByPrefix(str, "configns:firebase");
    }

    public Set<String> getKeysByPrefix(String str, String str2) {
        this.zzal.readLock().lock();
        try {
            Set<String> treeSet = new TreeSet();
            if (this.zzah != null) {
                treeSet.addAll(this.zzah.zzd(str, str2));
            }
            if (this.zzai != null) {
                treeSet.addAll(this.zzai.zzd(str, str2));
            }
            this.zzal.readLock().unlock();
            return treeSet;
        } catch (Throwable th) {
            this.zzal.readLock().unlock();
        }
    }

    public long getLong(String str) {
        return getLong(str, "configns:firebase");
    }

    public long getLong(String str, String str2) {
        long j = 0;
        if (str2 != null) {
            this.zzal.readLock().lock();
            try {
                if (this.zzah != null && this.zzah.zzb(str, str2)) {
                    try {
                        j = Long.valueOf(new String(this.zzah.zzc(str, str2), zzaq.UTF_8)).longValue();
                    } catch (NumberFormatException e) {
                    }
                }
                if (this.zzai != null && this.zzai.zzb(str, str2)) {
                    try {
                        j = Long.valueOf(new String(this.zzai.zzc(str, str2), zzaq.UTF_8)).longValue();
                        this.zzal.readLock().unlock();
                    } catch (NumberFormatException e2) {
                    }
                }
                this.zzal.readLock().unlock();
            } finally {
                this.zzal.readLock().unlock();
            }
        }
        return j;
    }

    public String getString(String str) {
        return getString(str, "configns:firebase");
    }

    public String getString(String str, String str2) {
        if (str2 == null) {
            return "";
        }
        this.zzal.readLock().lock();
        String str3;
        if (this.zzah == null || !this.zzah.zzb(str, str2)) {
            try {
                if (this.zzai == null || !this.zzai.zzb(str, str2)) {
                    str3 = "";
                    this.zzal.readLock().unlock();
                    return str3;
                }
                str3 = new String(this.zzai.zzc(str, str2), zzaq.UTF_8);
                this.zzal.readLock().unlock();
                return str3;
            } finally {
                this.zzal.readLock().unlock();
            }
        } else {
            str3 = new String(this.zzah.zzc(str, str2), zzaq.UTF_8);
            return str3;
        }
    }

    public FirebaseRemoteConfigValue getValue(String str) {
        return getValue(str, "configns:firebase");
    }

    public FirebaseRemoteConfigValue getValue(String str, String str2) {
        if (str2 == null) {
            return new zzaq(DEFAULT_VALUE_FOR_BYTE_ARRAY, 0);
        }
        this.zzal.readLock().lock();
        FirebaseRemoteConfigValue zzaq;
        if (this.zzah == null || !this.zzah.zzb(str, str2)) {
            try {
                if (this.zzai == null || !this.zzai.zzb(str, str2)) {
                    zzaq = new zzaq(DEFAULT_VALUE_FOR_BYTE_ARRAY, 0);
                    this.zzal.readLock().unlock();
                    return zzaq;
                }
                zzaq = new zzaq(this.zzai.zzc(str, str2), 1);
                this.zzal.readLock().unlock();
                return zzaq;
            } finally {
                this.zzal.readLock().unlock();
            }
        } else {
            zzaq = new zzaq(this.zzah.zzc(str, str2), 2);
            return zzaq;
        }
    }

    public void setConfigSettings(FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        this.zzal.writeLock().lock();
        try {
            boolean isDeveloperModeEnabled = this.zzaj.isDeveloperModeEnabled();
            boolean isDeveloperModeEnabled2 = firebaseRemoteConfigSettings == null ? false : firebaseRemoteConfigSettings.isDeveloperModeEnabled();
            this.zzaj.zza(isDeveloperModeEnabled2);
            if (isDeveloperModeEnabled != isDeveloperModeEnabled2) {
                zzn();
            }
            this.zzal.writeLock().unlock();
        } catch (Throwable th) {
            this.zzal.writeLock().unlock();
        }
    }

    public void setDefaults(int i) {
        setDefaults(i, "configns:firebase");
    }

    public void setDefaults(int i, String str) {
        if (str != null) {
            this.zzal.readLock().lock();
            try {
                if (!(this.zzaj == null || this.zzaj.zzr() == null || this.zzaj.zzr().get(str) == null)) {
                    zzal zzal = (zzal) this.zzaj.zzr().get(str);
                    if (i == zzal.getResourceId() && this.zzaj.zzs() == zzal.zzo()) {
                        if (Log.isLoggable("FirebaseRemoteConfig", 3)) {
                            Log.d("FirebaseRemoteConfig", "Skipped setting defaults from resource file as this resource file was already applied.");
                        }
                        this.zzal.readLock().unlock();
                        return;
                    }
                }
                this.zzal.readLock().unlock();
                Map hashMap = new HashMap();
                try {
                    XmlResourceParser xml = this.mContext.getResources().getXml(i);
                    Object obj = null;
                    Object obj2 = null;
                    Object obj3 = null;
                    for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                        if (eventType == 2) {
                            obj3 = xml.getName();
                        } else if (eventType == 3) {
                            if (!(!"entry".equals(xml.getName()) || obj2 == null || obj == null)) {
                                hashMap.put(obj2, obj);
                                obj = null;
                                obj2 = null;
                            }
                            obj3 = null;
                        } else if (eventType == 4) {
                            if ("key".equals(obj3)) {
                                obj2 = xml.getText();
                            } else if (Param.VALUE.equals(obj3)) {
                                obj = xml.getText();
                            }
                        }
                    }
                    this.zzaj.zza(str, new zzal(i, this.zzaj.zzs()));
                    zza(hashMap, str, false);
                } catch (Throwable e) {
                    Log.e("FirebaseRemoteConfig", "Caught exception while parsing XML resource. Skipping setDefaults.", e);
                }
            } catch (Throwable th) {
                this.zzal.readLock().unlock();
            }
        } else if (Log.isLoggable("FirebaseRemoteConfig", 3)) {
            Log.d("FirebaseRemoteConfig", "namespace cannot be null for setDefaults.");
        }
    }

    public void setDefaults(Map<String, Object> map) {
        setDefaults((Map) map, "configns:firebase");
    }

    public void setDefaults(Map<String, Object> map, String str) {
        zza(map, str, true);
    }

    @VisibleForTesting
    final void zza(TaskCompletionSource<Void> taskCompletionSource, zzk zzk) {
        if (zzk == null || zzk.getStatus() == null) {
            zza((TaskCompletionSource) taskCompletionSource, null);
            return;
        }
        int statusCode = zzk.getStatus().getStatusCode();
        this.zzal.writeLock().lock();
        Map zzh;
        Map hashMap;
        Map hashMap2;
        switch (statusCode) {
            case -6508:
            case -6506:
                this.zzaj.zzf(-1);
                if (!(this.zzag == null || this.zzag.zzq())) {
                    zzh = zzk.zzh();
                    hashMap = new HashMap();
                    for (String str : zzh.keySet()) {
                        hashMap2 = new HashMap();
                        for (String str2 : (Set) zzh.get(str)) {
                            hashMap2.put(str2, zzk.zza(str2, null, str));
                        }
                        hashMap.put(str, hashMap2);
                    }
                    this.zzag = new zzao(hashMap, this.zzag.getTimestamp(), zzk.zzg());
                }
                taskCompletionSource.setResult(null);
                zzn();
                break;
            case -6505:
                zzh = zzk.zzh();
                hashMap = new HashMap();
                for (String str3 : zzh.keySet()) {
                    hashMap2 = new HashMap();
                    for (String str22 : (Set) zzh.get(str3)) {
                        hashMap2.put(str22, zzk.zza(str22, null, str3));
                    }
                    hashMap.put(str3, hashMap2);
                }
                this.zzag = new zzao(hashMap, System.currentTimeMillis(), zzk.zzg());
                this.zzaj.zzf(-1);
                taskCompletionSource.setResult(null);
                zzn();
                break;
            case 6500:
            case 6501:
            case 6503:
            case 6504:
                zza((TaskCompletionSource) taskCompletionSource, zzk.getStatus());
                break;
            case 6502:
            case 6507:
                this.zzaj.zzf(2);
                taskCompletionSource.setException(new FirebaseRemoteConfigFetchThrottledException(zzk.getThrottleEndTimeMillis()));
                zzn();
                break;
            default:
                try {
                    if (zzk.getStatus().isSuccess()) {
                        Log.w("FirebaseRemoteConfig", "Unknown (successful) status code: " + statusCode);
                    }
                    zza((TaskCompletionSource) taskCompletionSource, zzk.getStatus());
                    break;
                } catch (Throwable th) {
                    this.zzal.writeLock().unlock();
                }
        }
        this.zzal.writeLock().unlock();
    }
}
