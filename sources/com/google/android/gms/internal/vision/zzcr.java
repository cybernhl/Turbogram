package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzcr<MessageType extends zzcr<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzbf<MessageType, BuilderType> {
    private static Map<Object, zzcr<?, ?>> zzkt = new ConcurrentHashMap();
    protected zzfg zzkr = zzfg.zzdu();
    private int zzks = -1;

    public static abstract class zza<MessageType extends zzcr<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzbg<MessageType, BuilderType> {
        private final MessageType zzku;
        protected MessageType zzkv;
        private boolean zzkw = false;

        protected zza(MessageType messageType) {
            this.zzku = messageType;
            this.zzkv = (zzcr) messageType.zza(zzd.zzlb, null, null);
        }

        private static void zza(MessageType messageType, MessageType messageType2) {
            zzek.zzdc().zzq(messageType).zzc(messageType, messageType2);
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zzcr zzcr;
            zza zza = (zza) this.zzku.zza(zzd.zzlc, null, null);
            if (this.zzkw) {
                zzcr = this.zzkv;
            } else {
                zzcr = this.zzkv;
                zzek.zzdc().zzq(zzcr).zzd(zzcr);
                this.zzkw = true;
                zzcr = this.zzkv;
            }
            zza.zza(zzcr);
            return zza;
        }

        public final boolean isInitialized() {
            return zzcr.zza(this.zzkv, false);
        }

        protected final /* synthetic */ zzbg zza(zzbf zzbf) {
            return zza((zzcr) zzbf);
        }

        public final BuilderType zza(MessageType messageType) {
            zzbx();
            zza(this.zzkv, messageType);
            return this;
        }

        public final /* synthetic */ zzbg zzam() {
            return (zza) clone();
        }

        public final /* synthetic */ zzdx zzbw() {
            return this.zzku;
        }

        protected final void zzbx() {
            if (this.zzkw) {
                zzcr zzcr = (zzcr) this.zzkv.zza(zzd.zzlb, null, null);
                zza(zzcr, this.zzkv);
                this.zzkv = zzcr;
                this.zzkw = false;
            }
        }

        public final MessageType zzby() {
            zzcr zzcr;
            boolean z;
            if (this.zzkw) {
                zzcr = this.zzkv;
            } else {
                zzcr = this.zzkv;
                zzek.zzdc().zzq(zzcr).zzd(zzcr);
                this.zzkw = true;
                zzcr = this.zzkv;
            }
            zzcr = zzcr;
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) zzcr.zza(zzd.zzky, null, null)).byteValue();
            if (byteValue == (byte) 1) {
                z = true;
            } else if (byteValue == (byte) 0) {
                z = false;
            } else {
                boolean zzp = zzek.zzdc().zzq(zzcr).zzp(zzcr);
                if (booleanValue) {
                    zzcr.zza(zzd.zzkz, zzp ? zzcr : null, null);
                }
                z = zzp;
            }
            if (z) {
                return zzcr;
            }
            throw new zzfe(zzcr);
        }

        public final /* synthetic */ zzdx zzbz() {
            if (this.zzkw) {
                return this.zzkv;
            }
            zzcr zzcr = this.zzkv;
            zzek.zzdc().zzq(zzcr).zzd(zzcr);
            this.zzkw = true;
            return this.zzkv;
        }

        public final /* synthetic */ zzdx zzca() {
            zzcr zzcr;
            boolean z;
            if (this.zzkw) {
                zzcr = this.zzkv;
            } else {
                zzcr = this.zzkv;
                zzek.zzdc().zzq(zzcr).zzd(zzcr);
                this.zzkw = true;
                zzcr = this.zzkv;
            }
            zzcr = zzcr;
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) zzcr.zza(zzd.zzky, null, null)).byteValue();
            if (byteValue == (byte) 1) {
                z = true;
            } else if (byteValue == (byte) 0) {
                z = false;
            } else {
                boolean zzp = zzek.zzdc().zzq(zzcr).zzp(zzcr);
                if (booleanValue) {
                    zzcr.zza(zzd.zzkz, zzp ? zzcr : null, null);
                }
                z = zzp;
            }
            if (z) {
                return zzcr;
            }
            throw new zzfe(zzcr);
        }
    }

    public static class zzb<T extends zzcr<T, ?>> extends zzbh<T> {
        private T zzku;

        public zzb(T t) {
            this.zzku = t;
        }
    }

    public static abstract class zzc<MessageType extends zzc<MessageType, BuilderType>, BuilderType> extends zzcr<MessageType, BuilderType> implements zzdz {
        protected zzcj<Object> zzkx = zzcj.zzbk();
    }

    public enum zzd {
        public static final int zzky = 1;
        public static final int zzkz = 2;
        public static final int zzla = 3;
        public static final int zzlb = 4;
        public static final int zzlc = 5;
        public static final int zzld = 6;
        public static final int zzle = 7;
        private static final /* synthetic */ int[] zzlf = new int[]{zzky, zzkz, zzla, zzlb, zzlc, zzld, zzle};
        public static final int zzlg = 1;
        public static final int zzlh = 2;
        private static final /* synthetic */ int[] zzli = new int[]{zzlg, zzlh};
        public static final int zzlj = 1;
        public static final int zzlk = 2;
        private static final /* synthetic */ int[] zzll = new int[]{zzlj, zzlk};

        /* renamed from: values$50KLMJ33DTMIUPRFDTJMOP9FE1P6UT3FC9QMCBQ7CLN6ASJ1EHIM8JB5EDPM2PR59HKN8P949LIN8Q3FCHA6UIBEEPNMMP9R0 */
        public static int[] m593x126d66cb() {
            return (int[]) zzlf.clone();
        }
    }

    private static <T extends zzcr<T, ?>> T zza(T t, byte[] bArr) throws zzcx {
        zzcr zzcr = (zzcr) t.zza(zzd.zzlb, null, null);
        try {
            zzek.zzdc().zzq(zzcr).zza(zzcr, bArr, 0, bArr.length, new zzbl());
            zzek.zzdc().zzq(zzcr).zzd(zzcr);
            if (zzcr.zzgi == 0) {
                return zzcr;
            }
            throw new RuntimeException();
        } catch (IOException e) {
            if (e.getCause() instanceof zzcx) {
                throw ((zzcx) e.getCause());
            }
            throw new zzcx(e.getMessage()).zzg(zzcr);
        } catch (IndexOutOfBoundsException e2) {
            throw zzcx.zzcb().zzg(zzcr);
        }
    }

    protected static Object zza(zzdx zzdx, String str, Object[] objArr) {
        return new zzem(zzdx, str, objArr);
    }

    static Object zza(Method method, Object obj, Object... objArr) {
        Throwable e;
        try {
            return method.invoke(obj, objArr);
        } catch (Throwable e2) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e2);
        } catch (InvocationTargetException e3) {
            e2 = e3.getCause();
            if (e2 instanceof RuntimeException) {
                throw ((RuntimeException) e2);
            } else if (e2 instanceof Error) {
                throw ((Error) e2);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", e2);
            }
        }
    }

    protected static <T extends zzcr<?, ?>> void zza(Class<T> cls, T t) {
        zzkt.put(cls, t);
    }

    protected static final <T extends zzcr<T, ?>> boolean zza(T t, boolean z) {
        byte byteValue = ((Byte) t.zza(zzd.zzky, null, null)).byteValue();
        return byteValue == (byte) 1 ? true : byteValue == (byte) 0 ? false : zzek.zzdc().zzq(t).zzp(t);
    }

    protected static <T extends zzcr<T, ?>> T zzb(T t, byte[] bArr) throws zzcx {
        T zza = zza((zzcr) t, bArr);
        if (zza != null) {
            boolean z;
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) zza.zza(zzd.zzky, null, null)).byteValue();
            if (byteValue == (byte) 1) {
                z = true;
            } else if (byteValue == (byte) 0) {
                z = false;
            } else {
                boolean zzp = zzek.zzdc().zzq(zza).zzp(zza);
                if (booleanValue) {
                    zza.zza(zzd.zzkz, zzp ? zza : null, null);
                }
                z = zzp;
            }
            if (!z) {
                throw new zzcx(new zzfe(zza).getMessage()).zzg(zza);
            }
        }
        return zza;
    }

    protected static <E> zzcw<E> zzbt() {
        return zzel.zzdd();
    }

    static <T extends zzcr<?, ?>> T zzc(Class<T> cls) {
        T t = (zzcr) zzkt.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zzcr) zzkt.get(cls);
            } catch (Throwable e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t != null) {
            return t;
        }
        String str = "Unable to get default instance for: ";
        String valueOf = String.valueOf(cls.getName());
        throw new IllegalStateException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    public boolean equals(Object obj) {
        return this == obj ? true : !((zzcr) zza(zzd.zzld, null, null)).getClass().isInstance(obj) ? false : zzek.zzdc().zzq(this).equals(this, (zzcr) obj);
    }

    public int hashCode() {
        if (this.zzgi != 0) {
            return this.zzgi;
        }
        this.zzgi = zzek.zzdc().zzq(this).hashCode(this);
        return this.zzgi;
    }

    public final boolean isInitialized() {
        boolean booleanValue = Boolean.TRUE.booleanValue();
        byte byteValue = ((Byte) zza(zzd.zzky, null, null)).byteValue();
        if (byteValue == (byte) 1) {
            return true;
        }
        if (byteValue == (byte) 0) {
            return false;
        }
        boolean zzp = zzek.zzdc().zzq(this).zzp(this);
        if (booleanValue) {
            zza(zzd.zzkz, zzp ? this : null, null);
        }
        return zzp;
    }

    public String toString() {
        return zzea.zza(this, super.toString());
    }

    protected abstract Object zza(int i, Object obj, Object obj2);

    final int zzal() {
        return this.zzks;
    }

    public final void zzb(zzca zzca) throws IOException {
        zzek.zzdc().zze(getClass()).zza(this, zzcc.zza(zzca));
    }

    public final int zzbl() {
        if (this.zzks == -1) {
            this.zzks = zzek.zzdc().zzq(this).zzn(this);
        }
        return this.zzks;
    }

    public final /* synthetic */ zzdy zzbu() {
        zza zza = (zza) zza(zzd.zzlc, null, null);
        zza.zza(this);
        return zza;
    }

    public final /* synthetic */ zzdy zzbv() {
        return (zza) zza(zzd.zzlc, null, null);
    }

    public final /* synthetic */ zzdx zzbw() {
        return (zzcr) zza(zzd.zzld, null, null);
    }

    final void zzh(int i) {
        this.zzks = i;
    }
}
