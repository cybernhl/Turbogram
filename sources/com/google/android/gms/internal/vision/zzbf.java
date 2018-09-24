package com.google.android.gms.internal.vision;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class zzbf<MessageType extends zzbf<MessageType, BuilderType>, BuilderType extends zzbg<MessageType, BuilderType>> implements zzdx {
    private static boolean zzgj = false;
    protected int zzgi = 0;

    protected static <T> void zza(Iterable<T> iterable, List<? super T> list) {
        zzct.checkNotNull(iterable);
        int size;
        if (iterable instanceof zzdg) {
            List zzck = ((zzdg) iterable).zzck();
            zzdg zzdg = (zzdg) list;
            int size2 = list.size();
            for (Object next : zzck) {
                if (next == null) {
                    String str = "Element at index " + (zzdg.size() - size2) + " is null.";
                    for (size = zzdg.size() - 1; size >= size2; size--) {
                        zzdg.remove(size);
                    }
                    throw new NullPointerException(str);
                } else if (next instanceof zzbo) {
                    zzdg.zzc((zzbo) next);
                } else {
                    zzdg.add((String) next);
                }
            }
        } else if (iterable instanceof zzej) {
            list.addAll((Collection) iterable);
        } else {
            if ((list instanceof ArrayList) && (iterable instanceof Collection)) {
                ((ArrayList) list).ensureCapacity(((Collection) iterable).size() + list.size());
            }
            size = list.size();
            for (Object next2 : iterable) {
                if (next2 == null) {
                    String str2 = "Element at index " + (list.size() - size) + " is null.";
                    for (int size3 = list.size() - 1; size3 >= size; size3--) {
                        list.remove(size3);
                    }
                    throw new NullPointerException(str2);
                }
                list.add(next2);
            }
        }
    }

    public final byte[] toByteArray() {
        try {
            byte[] bArr = new byte[zzbl()];
            zzca zzd = zzca.zzd(bArr);
            zzb(zzd);
            zzd.zzba();
            return bArr;
        } catch (Throwable e) {
            String str = "byte array";
            String name = getClass().getName();
            throw new RuntimeException(new StringBuilder((String.valueOf(name).length() + 62) + String.valueOf(str).length()).append("Serializing ").append(name).append(" to a ").append(str).append(" threw an IOException (should never happen).").toString(), e);
        }
    }

    public final zzbo zzak() {
        try {
            zzbt zzm = zzbo.zzm(zzbl());
            zzb(zzm.zzax());
            return zzm.zzaw();
        } catch (Throwable e) {
            String str = "ByteString";
            String name = getClass().getName();
            throw new RuntimeException(new StringBuilder((String.valueOf(name).length() + 62) + String.valueOf(str).length()).append("Serializing ").append(name).append(" to a ").append(str).append(" threw an IOException (should never happen).").toString(), e);
        }
    }

    int zzal() {
        throw new UnsupportedOperationException();
    }

    void zzh(int i) {
        throw new UnsupportedOperationException();
    }
}
