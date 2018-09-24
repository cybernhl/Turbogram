package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzca extends zzbn {
    private static final Logger logger = Logger.getLogger(zzca.class.getName());
    private static final boolean zzhj = zzfl.zzdx();
    zzcc zzhk = this;

    static class zza extends zzca {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        zza(byte[] bArr, int i, int i2) {
            super();
            if (bArr == null) {
                throw new NullPointerException("buffer");
            } else if (((i2 | 0) | (bArr.length - (i2 + 0))) < 0) {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(0), Integer.valueOf(i2)}));
            } else {
                this.buffer = bArr;
                this.offset = 0;
                this.position = 0;
                this.limit = i2 + 0;
            }
        }

        private final void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                System.arraycopy(bArr, i, this.buffer, this.position, i2);
                this.position += i2;
            } catch (Throwable e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(i2)}), e);
            }
        }

        public final void zza(byte b) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = b;
            } catch (Throwable e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
            }
        }

        public final void zza(int i, long j) throws IOException {
            zzd(i, 0);
            zzb(j);
        }

        public final void zza(int i, zzbo zzbo) throws IOException {
            zzd(i, 2);
            zza(zzbo);
        }

        public final void zza(int i, zzdx zzdx) throws IOException {
            zzd(1, 3);
            zzf(2, i);
            zzd(3, 2);
            zzb(zzdx);
            zzd(1, 4);
        }

        final void zza(int i, zzdx zzdx, zzen zzen) throws IOException {
            zzd(i, 2);
            zzbf zzbf = (zzbf) zzdx;
            int zzal = zzbf.zzal();
            if (zzal == -1) {
                zzal = zzen.zzn(zzbf);
                zzbf.zzh(zzal);
            }
            zzq(zzal);
            zzen.zza(zzdx, this.zzhk);
        }

        public final void zza(int i, String str) throws IOException {
            zzd(i, 2);
            zzh(str);
        }

        public final void zza(zzbo zzbo) throws IOException {
            zzq(zzbo.size());
            zzbo.zza((zzbn) this);
        }

        public final void zza(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        public final int zzaz() {
            return this.limit - this.position;
        }

        public final void zzb(int i, zzbo zzbo) throws IOException {
            zzd(1, 3);
            zzf(2, i);
            zza(3, zzbo);
            zzd(1, 4);
        }

        public final void zzb(int i, boolean z) throws IOException {
            int i2 = 0;
            zzd(i, 0);
            if (z) {
                i2 = 1;
            }
            zza((byte) i2);
        }

        public final void zzb(long j) throws IOException {
            byte[] bArr;
            int i;
            if (!zzca.zzhj || zzaz() < 10) {
                while ((j & -128) != 0) {
                    try {
                        bArr = this.buffer;
                        i = this.position;
                        this.position = i + 1;
                        bArr[i] = (byte) ((((int) j) & 127) | 128);
                        j >>>= 7;
                    } catch (Throwable e) {
                        throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
                    }
                }
                bArr = this.buffer;
                i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) ((int) j);
                return;
            }
            while ((j & -128) != 0) {
                bArr = this.buffer;
                i = this.position;
                this.position = i + 1;
                zzfl.zza(bArr, (long) i, (byte) ((((int) j) & 127) | 128));
                j >>>= 7;
            }
            bArr = this.buffer;
            i = this.position;
            this.position = i + 1;
            zzfl.zza(bArr, (long) i, (byte) ((int) j));
        }

        public final void zzb(zzdx zzdx) throws IOException {
            zzq(zzdx.zzbl());
            zzdx.zzb(this);
        }

        public final void zzc(int i, long j) throws IOException {
            zzd(i, 1);
            zzd(j);
        }

        public final void zzd(int i, int i2) throws IOException {
            zzq((i << 3) | i2);
        }

        public final void zzd(long j) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) ((int) j);
                bArr = this.buffer;
                i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) ((int) (j >> 8));
                bArr = this.buffer;
                i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) ((int) (j >> 16));
                bArr = this.buffer;
                i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) ((int) (j >> 24));
                bArr = this.buffer;
                i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) ((int) (j >> 32));
                bArr = this.buffer;
                i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) ((int) (j >> 40));
                bArr = this.buffer;
                i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) ((int) (j >> 48));
                bArr = this.buffer;
                i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) ((int) (j >> 56));
            } catch (Throwable e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
            }
        }

        public final void zzd(byte[] bArr, int i, int i2) throws IOException {
            zzq(i2);
            write(bArr, 0, i2);
        }

        public final void zze(int i, int i2) throws IOException {
            zzd(i, 0);
            zzp(i2);
        }

        public final void zzf(int i, int i2) throws IOException {
            zzd(i, 0);
            zzq(i2);
        }

        public final void zzh(int i, int i2) throws IOException {
            zzd(i, 5);
            zzs(i2);
        }

        public final void zzh(String str) throws IOException {
            int i = this.position;
            try {
                int zzv = zzca.zzv(str.length() * 3);
                int zzv2 = zzca.zzv(str.length());
                if (zzv2 == zzv) {
                    this.position = i + zzv2;
                    zzv = zzfn.zza(str, this.buffer, this.position, zzaz());
                    this.position = i;
                    zzq((zzv - i) - zzv2);
                    this.position = zzv;
                    return;
                }
                zzq(zzfn.zza(str));
                this.position = zzfn.zza(str, this.buffer, this.position, zzaz());
            } catch (zzfq e) {
                this.position = i;
                zza(str, e);
            } catch (Throwable e2) {
                throw new zzb(e2);
            }
        }

        public final void zzp(int i) throws IOException {
            if (i >= 0) {
                zzq(i);
            } else {
                zzb((long) i);
            }
        }

        public final void zzq(int i) throws IOException {
            byte[] bArr;
            int i2;
            if (!zzca.zzhj || zzaz() < 10) {
                while ((i & -128) != 0) {
                    try {
                        bArr = this.buffer;
                        i2 = this.position;
                        this.position = i2 + 1;
                        bArr[i2] = (byte) ((i & 127) | 128);
                        i >>>= 7;
                    } catch (Throwable e) {
                        throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
                    }
                }
                bArr = this.buffer;
                i2 = this.position;
                this.position = i2 + 1;
                bArr[i2] = (byte) i;
                return;
            }
            while ((i & -128) != 0) {
                bArr = this.buffer;
                i2 = this.position;
                this.position = i2 + 1;
                zzfl.zza(bArr, (long) i2, (byte) ((i & 127) | 128));
                i >>>= 7;
            }
            bArr = this.buffer;
            i2 = this.position;
            this.position = i2 + 1;
            zzfl.zza(bArr, (long) i2, (byte) i);
        }

        public final void zzs(int i) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                bArr[i2] = (byte) i;
                bArr = this.buffer;
                i2 = this.position;
                this.position = i2 + 1;
                bArr[i2] = (byte) (i >> 8);
                bArr = this.buffer;
                i2 = this.position;
                this.position = i2 + 1;
                bArr[i2] = (byte) (i >> 16);
                bArr = this.buffer;
                i2 = this.position;
                this.position = i2 + 1;
                bArr[i2] = i >> 24;
            } catch (Throwable e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
            }
        }
    }

    public static class zzb extends IOException {
        zzb() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        zzb(String str, Throwable th) {
            String valueOf = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
            String valueOf2 = String.valueOf(str);
            super(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), th);
        }

        zzb(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }
    }

    private zzca() {
    }

    public static int zza(int i, zzde zzde) {
        int zzt = zzt(i);
        int zzbl = zzde.zzbl();
        return zzt + (zzbl + zzv(zzbl));
    }

    public static int zza(zzde zzde) {
        int zzbl = zzde.zzbl();
        return zzbl + zzv(zzbl);
    }

    static int zza(zzdx zzdx, zzen zzen) {
        zzbf zzbf = (zzbf) zzdx;
        int zzal = zzbf.zzal();
        if (zzal == -1) {
            zzal = zzen.zzn(zzbf);
            zzbf.zzh(zzal);
        }
        return zzal + zzv(zzal);
    }

    private static int zzaa(int i) {
        return (i << 1) ^ (i >> 31);
    }

    @Deprecated
    public static int zzab(int i) {
        return zzv(i);
    }

    public static int zzb(double d) {
        return 8;
    }

    public static int zzb(int i, double d) {
        return zzt(i) + 8;
    }

    public static int zzb(int i, float f) {
        return zzt(i) + 4;
    }

    public static int zzb(int i, zzde zzde) {
        return ((zzt(1) << 1) + zzj(2, i)) + zza(3, zzde);
    }

    public static int zzb(int i, zzdx zzdx) {
        return ((zzt(1) << 1) + zzj(2, i)) + (zzt(3) + zzc(zzdx));
    }

    static int zzb(int i, zzdx zzdx, zzen zzen) {
        return zzt(i) + zza(zzdx, zzen);
    }

    public static int zzb(int i, String str) {
        return zzt(i) + zzi(str);
    }

    public static int zzb(zzbo zzbo) {
        int size = zzbo.size();
        return size + zzv(size);
    }

    public static int zzb(boolean z) {
        return 1;
    }

    public static int zzc(int i, zzbo zzbo) {
        int zzt = zzt(i);
        int size = zzbo.size();
        return zzt + (size + zzv(size));
    }

    @Deprecated
    static int zzc(int i, zzdx zzdx, zzen zzen) {
        int zzt = zzt(i) << 1;
        zzbf zzbf = (zzbf) zzdx;
        int zzal = zzbf.zzal();
        if (zzal == -1) {
            zzal = zzen.zzn(zzbf);
            zzbf.zzh(zzal);
        }
        return zzal + zzt;
    }

    public static int zzc(int i, boolean z) {
        return zzt(i) + 1;
    }

    public static int zzc(zzdx zzdx) {
        int zzbl = zzdx.zzbl();
        return zzbl + zzv(zzbl);
    }

    public static int zzd(float f) {
        return 4;
    }

    public static int zzd(int i, long j) {
        return zzt(i) + zzf(j);
    }

    public static int zzd(int i, zzbo zzbo) {
        return ((zzt(1) << 1) + zzj(2, i)) + zzc(3, zzbo);
    }

    @Deprecated
    public static int zzd(zzdx zzdx) {
        return zzdx.zzbl();
    }

    public static zzca zzd(byte[] bArr) {
        return new zza(bArr, 0, bArr.length);
    }

    public static int zze(int i, long j) {
        return zzt(i) + zzf(j);
    }

    public static int zze(long j) {
        return zzf(j);
    }

    public static int zze(byte[] bArr) {
        int length = bArr.length;
        return length + zzv(length);
    }

    public static int zzf(int i, long j) {
        return zzt(i) + zzf(zzj(j));
    }

    public static int zzf(long j) {
        if ((-128 & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        long j2;
        int i = 2;
        if ((-34359738368L & j) != 0) {
            i = 6;
            j2 = j >>> 28;
        } else {
            j2 = j;
        }
        if ((-2097152 & j2) != 0) {
            i += 2;
            j2 >>>= 14;
        }
        return (j2 & -16384) != 0 ? i + 1 : i;
    }

    public static int zzg(int i, long j) {
        return zzt(i) + 8;
    }

    public static int zzg(long j) {
        return zzf(zzj(j));
    }

    public static int zzh(int i, long j) {
        return zzt(i) + 8;
    }

    public static int zzh(long j) {
        return 8;
    }

    public static int zzi(int i, int i2) {
        return zzt(i) + zzu(i2);
    }

    public static int zzi(long j) {
        return 8;
    }

    public static int zzi(String str) {
        int zza;
        try {
            zza = zzfn.zza(str);
        } catch (zzfq e) {
            zza = str.getBytes(zzct.UTF_8).length;
        }
        return zza + zzv(zza);
    }

    public static int zzj(int i, int i2) {
        return zzt(i) + zzv(i2);
    }

    private static long zzj(long j) {
        return (j << 1) ^ (j >> 63);
    }

    public static int zzk(int i, int i2) {
        return zzt(i) + zzv(zzaa(i2));
    }

    public static int zzl(int i, int i2) {
        return zzt(i) + 4;
    }

    public static int zzm(int i, int i2) {
        return zzt(i) + 4;
    }

    public static int zzn(int i, int i2) {
        return zzt(i) + zzu(i2);
    }

    public static int zzt(int i) {
        return zzv(i << 3);
    }

    public static int zzu(int i) {
        return i >= 0 ? zzv(i) : 10;
    }

    public static int zzv(int i) {
        return (i & -128) == 0 ? 1 : (i & -16384) == 0 ? 2 : (-2097152 & i) == 0 ? 3 : (-268435456 & i) == 0 ? 4 : 5;
    }

    public static int zzw(int i) {
        return zzv(zzaa(i));
    }

    public static int zzx(int i) {
        return 4;
    }

    public static int zzy(int i) {
        return 4;
    }

    public static int zzz(int i) {
        return zzu(i);
    }

    public abstract void zza(byte b) throws IOException;

    public final void zza(double d) throws IOException {
        zzd(Double.doubleToRawLongBits(d));
    }

    public final void zza(int i, double d) throws IOException {
        zzc(i, Double.doubleToRawLongBits(d));
    }

    public final void zza(int i, float f) throws IOException {
        zzh(i, Float.floatToRawIntBits(f));
    }

    public abstract void zza(int i, long j) throws IOException;

    public abstract void zza(int i, zzbo zzbo) throws IOException;

    public abstract void zza(int i, zzdx zzdx) throws IOException;

    abstract void zza(int i, zzdx zzdx, zzen zzen) throws IOException;

    public abstract void zza(int i, String str) throws IOException;

    public abstract void zza(zzbo zzbo) throws IOException;

    final void zza(String str, zzfq zzfq) throws IOException {
        logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzfq);
        byte[] bytes = str.getBytes(zzct.UTF_8);
        try {
            zzq(bytes.length);
            zza(bytes, 0, bytes.length);
        } catch (Throwable e) {
            throw new zzb(e);
        } catch (zzb e2) {
            throw e2;
        }
    }

    public final void zza(boolean z) throws IOException {
        zza((byte) (z ? 1 : 0));
    }

    public abstract int zzaz();

    public final void zzb(int i, long j) throws IOException {
        zza(i, zzj(j));
    }

    public abstract void zzb(int i, zzbo zzbo) throws IOException;

    public abstract void zzb(int i, boolean z) throws IOException;

    public abstract void zzb(long j) throws IOException;

    public abstract void zzb(zzdx zzdx) throws IOException;

    public final void zzba() {
        if (zzaz() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public final void zzc(float f) throws IOException {
        zzs(Float.floatToRawIntBits(f));
    }

    public abstract void zzc(int i, long j) throws IOException;

    public final void zzc(long j) throws IOException {
        zzb(zzj(j));
    }

    public abstract void zzd(int i, int i2) throws IOException;

    public abstract void zzd(long j) throws IOException;

    abstract void zzd(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zze(int i, int i2) throws IOException;

    public abstract void zzf(int i, int i2) throws IOException;

    public final void zzg(int i, int i2) throws IOException {
        zzf(i, zzaa(i2));
    }

    public abstract void zzh(int i, int i2) throws IOException;

    public abstract void zzh(String str) throws IOException;

    public abstract void zzp(int i) throws IOException;

    public abstract void zzq(int i) throws IOException;

    public final void zzr(int i) throws IOException {
        zzq(zzaa(i));
    }

    public abstract void zzs(int i) throws IOException;
}
