package com.google.android.gms.internal.vision;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

final class zzfl {
    private static final Logger logger = Logger.getLogger(zzfl.class.getName());
    private static final Class<?> zzgm = zzbj.zzar();
    private static final boolean zzhj = zzea();
    private static final Unsafe zznd = zzdz();
    private static final boolean zzpa = zzi(Long.TYPE);
    private static final boolean zzpb = zzi(Integer.TYPE);
    private static final zzd zzpc;
    private static final boolean zzpd = zzeb();
    private static final long zzpe = ((long) zzg(byte[].class));
    private static final long zzpf = ((long) zzg(boolean[].class));
    private static final long zzpg = ((long) zzh(boolean[].class));
    private static final long zzph = ((long) zzg(int[].class));
    private static final long zzpi = ((long) zzh(int[].class));
    private static final long zzpj = ((long) zzg(long[].class));
    private static final long zzpk = ((long) zzh(long[].class));
    private static final long zzpl = ((long) zzg(float[].class));
    private static final long zzpm = ((long) zzh(float[].class));
    private static final long zzpn = ((long) zzg(double[].class));
    private static final long zzpo = ((long) zzh(double[].class));
    private static final long zzpp = ((long) zzg(Object[].class));
    private static final long zzpq = ((long) zzh(Object[].class));
    private static final long zzpr = zza(zzec());
    private static final long zzps;
    private static final boolean zzpt = (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN);

    static abstract class zzd {
        Unsafe zzpu;

        zzd(Unsafe unsafe) {
            this.zzpu = unsafe;
        }

        public abstract void zza(Object obj, long j, double d);

        public abstract void zza(Object obj, long j, float f);

        public final void zza(Object obj, long j, int i) {
            this.zzpu.putInt(obj, j, i);
        }

        public final void zza(Object obj, long j, long j2) {
            this.zzpu.putLong(obj, j, j2);
        }

        public abstract void zza(Object obj, long j, boolean z);

        public abstract void zze(Object obj, long j, byte b);

        public final int zzj(Object obj, long j) {
            return this.zzpu.getInt(obj, j);
        }

        public final long zzk(Object obj, long j) {
            return this.zzpu.getLong(obj, j);
        }

        public abstract boolean zzl(Object obj, long j);

        public abstract float zzm(Object obj, long j);

        public abstract double zzn(Object obj, long j);

        public abstract byte zzx(Object obj, long j);
    }

    static final class zza extends zzd {
        zza(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }

        public final void zza(Object obj, long j, float f) {
            zza(obj, j, Float.floatToIntBits(f));
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzfl.zzpt) {
                zzfl.zzb(obj, j, z);
            } else {
                zzfl.zzc(obj, j, z);
            }
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzfl.zzpt) {
                zzfl.zza(obj, j, b);
            } else {
                zzfl.zzb(obj, j, b);
            }
        }

        public final boolean zzl(Object obj, long j) {
            return zzfl.zzpt ? zzfl.zzr(obj, j) : zzfl.zzs(obj, j);
        }

        public final float zzm(Object obj, long j) {
            return Float.intBitsToFloat(zzj(obj, j));
        }

        public final double zzn(Object obj, long j) {
            return Double.longBitsToDouble(zzk(obj, j));
        }

        public final byte zzx(Object obj, long j) {
            return zzfl.zzpt ? zzfl.zzp(obj, j) : zzfl.zzq(obj, j);
        }
    }

    static final class zzb extends zzd {
        zzb(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }

        public final void zza(Object obj, long j, float f) {
            zza(obj, j, Float.floatToIntBits(f));
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzfl.zzpt) {
                zzfl.zzb(obj, j, z);
            } else {
                zzfl.zzc(obj, j, z);
            }
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzfl.zzpt) {
                zzfl.zza(obj, j, b);
            } else {
                zzfl.zzb(obj, j, b);
            }
        }

        public final boolean zzl(Object obj, long j) {
            return zzfl.zzpt ? zzfl.zzr(obj, j) : zzfl.zzs(obj, j);
        }

        public final float zzm(Object obj, long j) {
            return Float.intBitsToFloat(zzj(obj, j));
        }

        public final double zzn(Object obj, long j) {
            return Double.longBitsToDouble(zzk(obj, j));
        }

        public final byte zzx(Object obj, long j) {
            return zzfl.zzpt ? zzfl.zzp(obj, j) : zzfl.zzq(obj, j);
        }
    }

    static final class zzc extends zzd {
        zzc(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zza(Object obj, long j, double d) {
            this.zzpu.putDouble(obj, j, d);
        }

        public final void zza(Object obj, long j, float f) {
            this.zzpu.putFloat(obj, j, f);
        }

        public final void zza(Object obj, long j, boolean z) {
            this.zzpu.putBoolean(obj, j, z);
        }

        public final void zze(Object obj, long j, byte b) {
            this.zzpu.putByte(obj, j, b);
        }

        public final boolean zzl(Object obj, long j) {
            return this.zzpu.getBoolean(obj, j);
        }

        public final float zzm(Object obj, long j) {
            return this.zzpu.getFloat(obj, j);
        }

        public final double zzn(Object obj, long j) {
            return this.zzpu.getDouble(obj, j);
        }

        public final byte zzx(Object obj, long j) {
            return this.zzpu.getByte(obj, j);
        }
    }

    static {
        Field field = null;
        zzd zzb = zznd == null ? null : zzbj.zzaq() ? zzpa ? new zzb(zznd) : zzpb ? new zza(zznd) : null : new zzc(zznd);
        zzpc = zzb;
        Field zzb2 = zzb(String.class, Param.VALUE);
        if (zzb2 != null && zzb2.getType() == char[].class) {
            field = zzb2;
        }
        zzps = zza(field);
    }

    private zzfl() {
    }

    static byte zza(byte[] bArr, long j) {
        return zzpc.zzx(bArr, zzpe + j);
    }

    private static long zza(Field field) {
        return (field == null || zzpc == null) ? -1 : zzpc.zzpu.objectFieldOffset(field);
    }

    private static void zza(Object obj, long j, byte b) {
        int i = ((((int) j) ^ -1) & 3) << 3;
        zza(obj, j & -4, (zzj(obj, j & -4) & ((255 << i) ^ -1)) | ((b & 255) << i));
    }

    static void zza(Object obj, long j, double d) {
        zzpc.zza(obj, j, d);
    }

    static void zza(Object obj, long j, float f) {
        zzpc.zza(obj, j, f);
    }

    static void zza(Object obj, long j, int i) {
        zzpc.zza(obj, j, i);
    }

    static void zza(Object obj, long j, long j2) {
        zzpc.zza(obj, j, j2);
    }

    static void zza(Object obj, long j, Object obj2) {
        zzpc.zzpu.putObject(obj, j, obj2);
    }

    static void zza(Object obj, long j, boolean z) {
        zzpc.zza(obj, j, z);
    }

    static void zza(byte[] bArr, long j, byte b) {
        zzpc.zze(bArr, zzpe + j, b);
    }

    private static Field zzb(Class<?> cls, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable th) {
            return null;
        }
    }

    private static void zzb(Object obj, long j, byte b) {
        int i = (((int) j) & 3) << 3;
        zza(obj, j & -4, (zzj(obj, j & -4) & ((255 << i) ^ -1)) | ((b & 255) << i));
    }

    private static void zzb(Object obj, long j, boolean z) {
        zza(obj, j, (byte) (z ? 1 : 0));
    }

    private static void zzc(Object obj, long j, boolean z) {
        zzb(obj, j, (byte) (z ? 1 : 0));
    }

    static boolean zzdx() {
        return zzhj;
    }

    static boolean zzdy() {
        return zzpd;
    }

    static Unsafe zzdz() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzfm());
        } catch (Throwable th) {
            return null;
        }
    }

    private static boolean zzea() {
        if (zznd == null) {
            return false;
        }
        try {
            Class cls = zznd.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("arrayBaseOffset", new Class[]{Class.class});
            cls.getMethod("arrayIndexScale", new Class[]{Class.class});
            cls.getMethod("getInt", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putInt", new Class[]{Object.class, Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putLong", new Class[]{Object.class, Long.TYPE, Long.TYPE});
            cls.getMethod("getObject", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putObject", new Class[]{Object.class, Long.TYPE, Object.class});
            if (zzbj.zzaq()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putByte", new Class[]{Object.class, Long.TYPE, Byte.TYPE});
            cls.getMethod("getBoolean", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putBoolean", new Class[]{Object.class, Long.TYPE, Boolean.TYPE});
            cls.getMethod("getFloat", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putFloat", new Class[]{Object.class, Long.TYPE, Float.TYPE});
            cls.getMethod("getDouble", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putDouble", new Class[]{Object.class, Long.TYPE, Double.TYPE});
            return true;
        } catch (Throwable th) {
            String valueOf = String.valueOf(th);
            logger.logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", new StringBuilder(String.valueOf(valueOf).length() + 71).append("platform method missing - proto runtime falling back to safer methods: ").append(valueOf).toString());
            return false;
        }
    }

    private static boolean zzeb() {
        if (zznd == null) {
            return false;
        }
        try {
            Class cls = zznd.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            if (zzec() == null) {
                return false;
            }
            if (zzbj.zzaq()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Long.TYPE});
            cls.getMethod("putByte", new Class[]{Long.TYPE, Byte.TYPE});
            cls.getMethod("getInt", new Class[]{Long.TYPE});
            cls.getMethod("putInt", new Class[]{Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Long.TYPE});
            cls.getMethod("putLong", new Class[]{Long.TYPE, Long.TYPE});
            cls.getMethod("copyMemory", new Class[]{Long.TYPE, Long.TYPE, Long.TYPE});
            cls.getMethod("copyMemory", new Class[]{Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE});
            return true;
        } catch (Throwable th) {
            String valueOf = String.valueOf(th);
            logger.logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", new StringBuilder(String.valueOf(valueOf).length() + 71).append("platform method missing - proto runtime falling back to safer methods: ").append(valueOf).toString());
            return false;
        }
    }

    private static Field zzec() {
        Field zzb;
        if (zzbj.zzaq()) {
            zzb = zzb(Buffer.class, "effectiveDirectAddress");
            if (zzb != null) {
                return zzb;
            }
        }
        zzb = zzb(Buffer.class, "address");
        return (zzb == null || zzb.getType() != Long.TYPE) ? null : zzb;
    }

    private static int zzg(Class<?> cls) {
        return zzhj ? zzpc.zzpu.arrayBaseOffset(cls) : -1;
    }

    private static int zzh(Class<?> cls) {
        return zzhj ? zzpc.zzpu.arrayIndexScale(cls) : -1;
    }

    private static boolean zzi(Class<?> cls) {
        if (!zzbj.zzaq()) {
            return false;
        }
        try {
            Class cls2 = zzgm;
            cls2.getMethod("peekLong", new Class[]{cls, Boolean.TYPE});
            cls2.getMethod("pokeLong", new Class[]{cls, Long.TYPE, Boolean.TYPE});
            cls2.getMethod("pokeInt", new Class[]{cls, Integer.TYPE, Boolean.TYPE});
            cls2.getMethod("peekInt", new Class[]{cls, Boolean.TYPE});
            cls2.getMethod("pokeByte", new Class[]{cls, Byte.TYPE});
            cls2.getMethod("peekByte", new Class[]{cls});
            cls2.getMethod("pokeByteArray", new Class[]{cls, byte[].class, Integer.TYPE, Integer.TYPE});
            cls2.getMethod("peekByteArray", new Class[]{cls, byte[].class, Integer.TYPE, Integer.TYPE});
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    static int zzj(Object obj, long j) {
        return zzpc.zzj(obj, j);
    }

    static long zzk(Object obj, long j) {
        return zzpc.zzk(obj, j);
    }

    static boolean zzl(Object obj, long j) {
        return zzpc.zzl(obj, j);
    }

    static float zzm(Object obj, long j) {
        return zzpc.zzm(obj, j);
    }

    static double zzn(Object obj, long j) {
        return zzpc.zzn(obj, j);
    }

    static Object zzo(Object obj, long j) {
        return zzpc.zzpu.getObject(obj, j);
    }

    private static byte zzp(Object obj, long j) {
        return (byte) (zzj(obj, -4 & j) >>> ((int) (((-1 ^ j) & 3) << 3)));
    }

    private static byte zzq(Object obj, long j) {
        return (byte) (zzj(obj, -4 & j) >>> ((int) ((3 & j) << 3)));
    }

    private static boolean zzr(Object obj, long j) {
        return zzp(obj, j) != (byte) 0;
    }

    private static boolean zzs(Object obj, long j) {
        return zzq(obj, j) != (byte) 0;
    }
}
