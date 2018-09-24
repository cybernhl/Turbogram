package com.google.firebase.abt;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.firebase_abt.zzj;
import com.google.android.gms.internal.firebase_abt.zzn;
import com.google.android.gms.internal.firebase_abt.zzo;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.measurement.AppMeasurement.ConditionalUserProperty;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@KeepForSdk
public class FirebaseABTesting {
    private AppMeasurement zza;
    private String zzb;
    private int zzc;
    private long zzd;
    private SharedPreferences zze;
    private String zzf;
    @Nullable
    private Integer zzg = null;

    @KeepForSdk
    public FirebaseABTesting(Context context, String str, int i) throws NoClassDefFoundError {
        this.zza = AppMeasurement.getInstance(context);
        this.zzb = str;
        this.zzc = i;
        this.zze = context.getSharedPreferences("com.google.firebase.abt", 0);
        this.zzf = String.format("%s_lastKnownExperimentStartTime", new Object[]{str});
        this.zzd = this.zze.getLong(this.zzf, 0);
    }

    private static zzo zza(byte[] bArr) {
        try {
            return (zzo) zzj.zza(new zzo(), bArr, 0, bArr.length);
        } catch (Throwable e) {
            Log.e("FirebaseABTesting", "Payload was not defined or could not be deserialized.", e);
            return null;
        }
    }

    private final void zza() {
        if (this.zze.getLong(this.zzf, 0) != this.zzd) {
            Editor edit = this.zze.edit();
            edit.putLong(this.zzf, this.zzd);
            edit.apply();
        }
    }

    @VisibleForTesting
    private final void zza(String str) {
        this.zza.clearConditionalUserProperty(str, null, null);
    }

    private final void zza(Collection<ConditionalUserProperty> collection) {
        for (ConditionalUserProperty conditionalUserProperty : collection) {
            zza(conditionalUserProperty.mName);
        }
    }

    private final boolean zza(zzo zzo) {
        int i = zzo.zzc;
        int i2 = this.zzc;
        if (i == 0) {
            i = i2 != 0 ? i2 : 1;
        }
        if (i == 1) {
            return true;
        }
        if (!Log.isLoggable("FirebaseABTesting", 3)) {
            return false;
        }
        Log.d("FirebaseABTesting", String.format("Experiment won't be set due to the overflow policy: [%s, %s]", new Object[]{zzo.zzaq, zzo.zzar}));
        return false;
    }

    private final ConditionalUserProperty zzb(zzo zzo) {
        ConditionalUserProperty conditionalUserProperty = new ConditionalUserProperty();
        conditionalUserProperty.mOrigin = this.zzb;
        conditionalUserProperty.mCreationTimestamp = zzo.zzas;
        conditionalUserProperty.mName = zzo.zzaq;
        conditionalUserProperty.mValue = zzo.zzar;
        conditionalUserProperty.mTriggerEventName = TextUtils.isEmpty(zzo.zzat) ? null : zzo.zzat;
        conditionalUserProperty.mTriggerTimeout = zzo.zzau;
        conditionalUserProperty.mTimeToLive = zzo.zzav;
        return conditionalUserProperty;
    }

    @WorkerThread
    private final List<ConditionalUserProperty> zzb() {
        return this.zza.getConditionalUserProperties(this.zzb, "");
    }

    @WorkerThread
    private final int zzc() {
        if (this.zzg == null) {
            this.zzg = Integer.valueOf(this.zza.getMaxUserProperties(this.zzb));
        }
        return this.zzg.intValue();
    }

    @WorkerThread
    @KeepForSdk
    public void addExperiment(byte[] bArr) {
        zzo zza = zza(bArr);
        if (zza == null) {
            return;
        }
        if (zza.zzbb == null || zza.zzbb.length == 0) {
            Log.e("FirebaseABTesting", "The ongoingExperiments field of the payload is not defined.");
            return;
        }
        int size;
        zzn[] zznArr = zza.zzbb;
        Set hashSet = new HashSet();
        for (zzn zzn : zznArr) {
            hashSet.add(zzn.zzaq);
        }
        if (hashSet.contains(zza.zzaq)) {
            ConditionalUserProperty conditionalUserProperty;
            this.zzd = Math.max(this.zzd, zza.zzas);
            zza();
            Deque arrayDeque = new ArrayDeque();
            Collection arrayList = new ArrayList();
            for (ConditionalUserProperty conditionalUserProperty2 : zzb()) {
                if (conditionalUserProperty2.mName.equals(zza.zzaq)) {
                    Log.w("FirebaseABTesting", String.format("The payload experiment [%s, %s] is already set with variant: %s", new Object[]{zza.zzaq, zza.zzar, conditionalUserProperty2.mValue}));
                    return;
                } else if (hashSet.contains(conditionalUserProperty2.mName)) {
                    arrayDeque.offer(conditionalUserProperty2);
                } else {
                    arrayList.add(conditionalUserProperty2);
                }
            }
            size = arrayDeque.size();
            int zzc = zzc();
            if (size >= zzc) {
                if (size > zzc) {
                    Log.w("FirebaseABTesting", String.format("Max experiment limit exceeded: %d > %d", new Object[]{Integer.valueOf(size), Integer.valueOf(zzc)}));
                }
                size = 0;
            } else {
                size = 1;
            }
            if (size != 0 || zza(zza)) {
                zza(arrayList);
                while (arrayDeque.size() >= zzc()) {
                    conditionalUserProperty2 = (ConditionalUserProperty) arrayDeque.pollFirst();
                    if (Log.isLoggable("FirebaseABTesting", 3)) {
                        Log.d("FirebaseABTesting", String.format("Clearing experiment due to the overflow policy: [%s, %s]", new Object[]{conditionalUserProperty2.mName, conditionalUserProperty2.mValue}));
                    }
                    zza(conditionalUserProperty2.mName);
                }
                this.zza.setConditionalUserProperty(zzb(zza));
                return;
            }
            return;
        }
        Log.e("FirebaseABTesting", String.format("The payload experiment [%s, %s] is not in ongoingExperiments.", new Object[]{zza.zzaq, zza.zzar}));
    }

    @WorkerThread
    @KeepForSdk
    public void removeAllExperiments() {
        zza(zzb());
    }

    @WorkerThread
    @KeepForSdk
    public void replaceAllExperiments(List<byte[]> list) {
        if (list == null) {
            Log.e("FirebaseABTesting", "Cannot replace experiments because experimentPayloads is null.");
        } else if (list.isEmpty()) {
            removeAllExperiments();
        } else {
            List arrayList = new ArrayList();
            for (byte[] zza : list) {
                zzo zza2 = zza(zza);
                if (zza2 != null) {
                    arrayList.add(zza2);
                }
            }
            if (arrayList.isEmpty()) {
                Log.e("FirebaseABTesting", "All payloads are either not defined or cannot not be deserialized.");
                return;
            }
            zzo zzo;
            Set hashSet = new HashSet();
            ArrayList arrayList2 = (ArrayList) arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList2.get(i);
                i++;
                hashSet.add(((zzo) obj).zzaq);
            }
            List<ConditionalUserProperty> zzb = zzb();
            Set hashSet2 = new HashSet();
            for (ConditionalUserProperty conditionalUserProperty : zzb) {
                hashSet2.add(conditionalUserProperty.mName);
            }
            Collection arrayList3 = new ArrayList();
            for (ConditionalUserProperty conditionalUserProperty2 : zzb) {
                if (!hashSet.contains(conditionalUserProperty2.mName)) {
                    arrayList3.add(conditionalUserProperty2);
                }
            }
            zza(arrayList3);
            ArrayList arrayList4 = new ArrayList();
            arrayList2 = (ArrayList) arrayList;
            int size2 = arrayList2.size();
            int i2 = 0;
            while (i2 < size2) {
                size = i2 + 1;
                zzo = (zzo) arrayList2.get(i2);
                if (!hashSet2.contains(zzo.zzaq)) {
                    Object obj2;
                    if (zzo.zzas <= this.zzd) {
                        if (Log.isLoggable("FirebaseABTesting", 3)) {
                            Log.d("FirebaseABTesting", String.format("The experiment [%s, %s, %d] is not new since its startTime is before lastKnownStartTime: %d", new Object[]{zzo.zzaq, zzo.zzar, Long.valueOf(zzo.zzas), Long.valueOf(this.zzd)}));
                        }
                        obj2 = null;
                    } else {
                        obj2 = 1;
                    }
                    if (obj2 != null) {
                        arrayList4.add(zzo);
                    }
                }
                i2 = size;
            }
            Deque arrayDeque = new ArrayDeque(zzb());
            int zzc = zzc();
            arrayList2 = arrayList4;
            size2 = arrayList2.size();
            i = 0;
            while (i < size2) {
                i2 = i + 1;
                zzo = (zzo) arrayList2.get(i);
                if (arrayDeque.size() >= zzc) {
                    if (zza(zzo)) {
                        while (arrayDeque.size() >= zzc) {
                            zza(((ConditionalUserProperty) arrayDeque.pollFirst()).mName);
                        }
                    } else {
                        i = i2;
                    }
                }
                ConditionalUserProperty zzb2 = zzb(zzo);
                this.zza.setConditionalUserProperty(zzb2);
                arrayDeque.offer(zzb2);
                i = i2;
            }
            ArrayList arrayList5 = (ArrayList) arrayList;
            i = arrayList5.size();
            int i3 = 0;
            while (i3 < i) {
                Object obj3 = arrayList5.get(i3);
                i3++;
                this.zzd = Math.max(this.zzd, ((zzo) obj3).zzas);
            }
            zza();
        }
    }
}
