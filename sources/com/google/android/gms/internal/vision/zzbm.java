package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzbm extends zzbi<Boolean> implements zzcw<Boolean>, zzej, RandomAccess {
    private static final zzbm zzgr;
    private int size;
    private boolean[] zzgs;

    static {
        zzbi zzbm = new zzbm();
        zzgr = zzbm;
        zzbm.zzao();
    }

    zzbm() {
        this(new boolean[10], 0);
    }

    private zzbm(boolean[] zArr, int i) {
        this.zzgs = zArr;
        this.size = i;
    }

    private final void zza(int i, boolean z) {
        zzap();
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException(zzj(i));
        }
        if (this.size < this.zzgs.length) {
            System.arraycopy(this.zzgs, i, this.zzgs, i + 1, this.size - i);
        } else {
            Object obj = new boolean[(((this.size * 3) / 2) + 1)];
            System.arraycopy(this.zzgs, 0, obj, 0, i);
            System.arraycopy(this.zzgs, i, obj, i + 1, this.size - i);
            this.zzgs = obj;
        }
        this.zzgs[i] = z;
        this.size++;
        this.modCount++;
    }

    private final void zzi(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzj(i));
        }
    }

    private final String zzj(int i) {
        return "Index:" + i + ", Size:" + this.size;
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zza(i, ((Boolean) obj).booleanValue());
    }

    public final boolean addAll(Collection<? extends Boolean> collection) {
        zzap();
        zzct.checkNotNull(collection);
        if (!(collection instanceof zzbm)) {
            return super.addAll(collection);
        }
        zzbm zzbm = (zzbm) collection;
        if (zzbm.size == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.size < zzbm.size) {
            throw new OutOfMemoryError();
        }
        int i = this.size + zzbm.size;
        if (i > this.zzgs.length) {
            this.zzgs = Arrays.copyOf(this.zzgs, i);
        }
        System.arraycopy(zzbm.zzgs, 0, this.zzgs, this.size, zzbm.size);
        this.size = i;
        this.modCount++;
        return true;
    }

    public final void addBoolean(boolean z) {
        zza(this.size, z);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbm)) {
            return super.equals(obj);
        }
        zzbm zzbm = (zzbm) obj;
        if (this.size != zzbm.size) {
            return false;
        }
        boolean[] zArr = zzbm.zzgs;
        for (int i = 0; i < this.size; i++) {
            if (this.zzgs[i] != zArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        zzi(i);
        return Boolean.valueOf(this.zzgs[i]);
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzct.zzc(this.zzgs[i2]);
        }
        return i;
    }

    public final /* synthetic */ Object remove(int i) {
        zzap();
        zzi(i);
        boolean z = this.zzgs[i];
        if (i < this.size - 1) {
            System.arraycopy(this.zzgs, i + 1, this.zzgs, i, this.size - i);
        }
        this.size--;
        this.modCount++;
        return Boolean.valueOf(z);
    }

    public final boolean remove(Object obj) {
        zzap();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Boolean.valueOf(this.zzgs[i]))) {
                System.arraycopy(this.zzgs, i + 1, this.zzgs, i, this.size - i);
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
        System.arraycopy(this.zzgs, i2, this.zzgs, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zzap();
        zzi(i);
        boolean z = this.zzgs[i];
        this.zzgs[i] = booleanValue;
        return Boolean.valueOf(z);
    }

    public final int size() {
        return this.size;
    }

    public final /* synthetic */ zzcw zzk(int i) {
        if (i >= this.size) {
            return new zzbm(Arrays.copyOf(this.zzgs, i), this.size);
        }
        throw new IllegalArgumentException();
    }
}
