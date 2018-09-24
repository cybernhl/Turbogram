package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzdl extends zzbi<Long> implements zzcw<Long>, zzej, RandomAccess {
    private static final zzdl zzmr;
    private int size;
    private long[] zzms;

    static {
        zzbi zzdl = new zzdl();
        zzmr = zzdl;
        zzdl.zzao();
    }

    zzdl() {
        this(new long[10], 0);
    }

    private zzdl(long[] jArr, int i) {
        this.zzms = jArr;
        this.size = i;
    }

    private final void zzi(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzj(i));
        }
    }

    private final String zzj(int i) {
        return "Index:" + i + ", Size:" + this.size;
    }

    private final void zzk(int i, long j) {
        zzap();
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException(zzj(i));
        }
        if (this.size < this.zzms.length) {
            System.arraycopy(this.zzms, i, this.zzms, i + 1, this.size - i);
        } else {
            Object obj = new long[(((this.size * 3) / 2) + 1)];
            System.arraycopy(this.zzms, 0, obj, 0, i);
            System.arraycopy(this.zzms, i, obj, i + 1, this.size - i);
            this.zzms = obj;
        }
        this.zzms[i] = j;
        this.size++;
        this.modCount++;
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzk(i, ((Long) obj).longValue());
    }

    public final boolean addAll(Collection<? extends Long> collection) {
        zzap();
        zzct.checkNotNull(collection);
        if (!(collection instanceof zzdl)) {
            return super.addAll(collection);
        }
        zzdl zzdl = (zzdl) collection;
        if (zzdl.size == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.size < zzdl.size) {
            throw new OutOfMemoryError();
        }
        int i = this.size + zzdl.size;
        if (i > this.zzms.length) {
            this.zzms = Arrays.copyOf(this.zzms, i);
        }
        System.arraycopy(zzdl.zzms, 0, this.zzms, this.size, zzdl.size);
        this.size = i;
        this.modCount++;
        return true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzdl)) {
            return super.equals(obj);
        }
        zzdl zzdl = (zzdl) obj;
        if (this.size != zzdl.size) {
            return false;
        }
        long[] jArr = zzdl.zzms;
        for (int i = 0; i < this.size; i++) {
            if (this.zzms[i] != jArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        return Long.valueOf(getLong(i));
    }

    public final long getLong(int i) {
        zzi(i);
        return this.zzms[i];
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzct.zzk(this.zzms[i2]);
        }
        return i;
    }

    public final /* synthetic */ Object remove(int i) {
        zzap();
        zzi(i);
        long j = this.zzms[i];
        if (i < this.size - 1) {
            System.arraycopy(this.zzms, i + 1, this.zzms, i, this.size - i);
        }
        this.size--;
        this.modCount++;
        return Long.valueOf(j);
    }

    public final boolean remove(Object obj) {
        zzap();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Long.valueOf(this.zzms[i]))) {
                System.arraycopy(this.zzms, i + 1, this.zzms, i, this.size - i);
                this.size--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    protected final void removeRange(int i, int i2) {
        zzap();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        System.arraycopy(this.zzms, i2, this.zzms, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        long longValue = ((Long) obj).longValue();
        zzap();
        zzi(i);
        long j = this.zzms[i];
        this.zzms[i] = longValue;
        return Long.valueOf(j);
    }

    public final int size() {
        return this.size;
    }

    public final /* synthetic */ zzcw zzk(int i) {
        if (i >= this.size) {
            return new zzdl(Arrays.copyOf(this.zzms, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final void zzl(long j) {
        zzk(this.size, j);
    }
}
