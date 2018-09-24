package com.google.android.gms.internal.measurement;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class zzxf {
    private static final zzxf zzcbs = new zzxf();
    private final zzxk zzcbt;
    private final ConcurrentMap<Class<?>, zzxj<?>> zzcbu = new ConcurrentHashMap();

    public static zzxf zzxn() {
        return zzcbs;
    }

    public final <T> zzxj<T> zzi(Class<T> cls) {
        zzvo.zza(cls, "messageType");
        zzxj<T> zzxj = (zzxj) this.zzcbu.get(cls);
        if (zzxj != null) {
            return zzxj;
        }
        zzxj<T> zzh = this.zzcbt.zzh(cls);
        zzvo.zza(cls, "messageType");
        zzvo.zza(zzh, "schema");
        zzxj = (zzxj) this.zzcbu.putIfAbsent(cls, zzh);
        return zzxj != null ? zzxj : zzh;
    }

    public final <T> zzxj<T> zzag(T t) {
        return zzi(t.getClass());
    }

    private zzxf() {
        zzxk zzxk = null;
        String[] strArr = new String[]{"com.google.protobuf.AndroidProto3SchemaFactory"};
        for (int i = 0; i <= 0; i++) {
            zzxk = zzgb(strArr[0]);
            if (zzxk != null) {
                break;
            }
        }
        if (zzxk == null) {
            zzxk = new zzwi();
        }
        this.zzcbt = zzxk;
    }

    private static zzxk zzgb(String str) {
        try {
            return (zzxk) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Throwable th) {
            return null;
        }
    }
}
