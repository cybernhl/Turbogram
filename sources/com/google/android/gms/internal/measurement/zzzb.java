package com.google.android.gms.internal.measurement;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public final class zzzb<M extends zzza<M>, T> {
    public final int tag;
    private final int type;
    private final zzvm<?, ?> zzbyp;
    protected final Class<T> zzcfd;
    protected final boolean zzcfe;

    public static <M extends zzza<M>, T extends zzzg> zzzb<M, T> zza(int i, Class<T> cls, long j) {
        return new zzzb(11, cls, 810, false);
    }

    private zzzb(int i, Class<T> cls, int i2, boolean z) {
        this(11, cls, null, 810, false);
    }

    private zzzb(int i, Class<T> cls, zzvm<?, ?> zzvm, int i2, boolean z) {
        this.type = i;
        this.zzcfd = cls;
        this.tag = i2;
        this.zzcfe = false;
        this.zzbyp = null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzzb)) {
            return false;
        }
        zzzb zzzb = (zzzb) obj;
        if (this.type == zzzb.type && this.zzcfd == zzzb.zzcfd && this.tag == zzzb.tag && this.zzcfe == zzzb.zzcfe) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (this.zzcfe ? 1 : 0) + ((((((this.type + 1147) * 31) + this.zzcfd.hashCode()) * 31) + this.tag) * 31);
    }

    final T zzah(List<zzzi> list) {
        int i = 0;
        if (list == null) {
            return null;
        }
        if (this.zzcfe) {
            int i2;
            List arrayList = new ArrayList();
            for (i2 = 0; i2 < list.size(); i2++) {
                zzzi zzzi = (zzzi) list.get(i2);
                if (zzzi.zzbug.length != 0) {
                    arrayList.add(zze(zzyx.zzn(zzzi.zzbug)));
                }
            }
            i2 = arrayList.size();
            if (i2 == 0) {
                return null;
            }
            T cast = this.zzcfd.cast(Array.newInstance(this.zzcfd.getComponentType(), i2));
            while (i < i2) {
                Array.set(cast, i, arrayList.get(i));
                i++;
            }
            return cast;
        } else if (list.isEmpty()) {
            return null;
        } else {
            return this.zzcfd.cast(zze(zzyx.zzn(((zzzi) list.get(list.size() - 1)).zzbug)));
        }
    }

    private final Object zze(zzyx zzyx) {
        String valueOf;
        Class componentType = this.zzcfe ? this.zzcfd.getComponentType() : this.zzcfd;
        try {
            zzzg zzzg;
            switch (this.type) {
                case 10:
                    zzzg = (zzzg) componentType.newInstance();
                    zzyx.zza(zzzg, this.tag >>> 3);
                    return zzzg;
                case 11:
                    zzzg = (zzzg) componentType.newInstance();
                    zzyx.zza(zzzg);
                    return zzzg;
                default:
                    throw new IllegalArgumentException("Unknown type " + this.type);
            }
        } catch (Throwable e) {
            valueOf = String.valueOf(componentType);
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 33).append("Error creating instance of class ").append(valueOf).toString(), e);
        } catch (Throwable e2) {
            valueOf = String.valueOf(componentType);
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 33).append("Error creating instance of class ").append(valueOf).toString(), e2);
        } catch (Throwable e22) {
            throw new IllegalArgumentException("Error reading extension field", e22);
        }
    }

    protected final void zza(Object obj, zzyy zzyy) {
        try {
            zzyy.zzca(this.tag);
            switch (this.type) {
                case 10:
                    int i = this.tag >>> 3;
                    ((zzzg) obj).zza(zzyy);
                    zzyy.zzc(i, 4);
                    return;
                case 11:
                    zzyy.zzb((zzzg) obj);
                    return;
                default:
                    throw new IllegalArgumentException("Unknown type " + this.type);
            }
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
        throw new IllegalStateException(e);
    }

    protected final int zzak(Object obj) {
        int i = this.tag >>> 3;
        switch (this.type) {
            case 10:
                return (zzyy.zzbb(i) << 1) + ((zzzg) obj).zzvu();
            case 11:
                return zzyy.zzb(i, (zzzg) obj);
            default:
                throw new IllegalArgumentException("Unknown type " + this.type);
        }
    }
}
