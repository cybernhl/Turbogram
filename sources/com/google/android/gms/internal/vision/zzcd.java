package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzcd extends zzbi<Double> implements zzcw<Double>, zzej, RandomAccess {
    private static final zzcd zzhl;
    private int size;
    private double[] zzhm;

    static {
        zzbi zzcd = new zzcd();
        zzhl = zzcd;
        zzcd.zzao();
    }

    zzcd() {
        this(new double[10], 0);
    }

    private zzcd(double[] dArr, int i) {
        this.zzhm = dArr;
        this.size = i;
    }

    private final void zzc(int i, double d) {
        zzap();
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException(zzj(i));
        }
        if (this.size < this.zzhm.length) {
            System.arraycopy(this.zzhm, i, this.zzhm, i + 1, this.size - i);
        } else {
            Object obj = new double[(((this.size * 3) / 2) + 1)];
            System.arraycopy(this.zzhm, 0, obj, 0, i);
            System.arraycopy(this.zzhm, i, obj, i + 1, this.size - i);
            this.zzhm = obj;
        }
        this.zzhm[i] = d;
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
        zzc(i, ((Double) obj).doubleValue());
    }

    public final boolean addAll(Collection<? extends Double> collection) {
        zzap();
        zzct.checkNotNull(collection);
        if (!(collection instanceof zzcd)) {
            return super.addAll(collection);
        }
        zzcd zzcd = (zzcd) collection;
        if (zzcd.size == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.size < zzcd.size) {
            throw new OutOfMemoryError();
        }
        int i = this.size + zzcd.size;
        if (i > this.zzhm.length) {
            this.zzhm = Arrays.copyOf(this.zzhm, i);
        }
        System.arraycopy(zzcd.zzhm, 0, this.zzhm, this.size, zzcd.size);
        this.size = i;
        this.modCount++;
        return true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzcd)) {
            return super.equals(obj);
        }
        zzcd zzcd = (zzcd) obj;
        if (this.size != zzcd.size) {
            return false;
        }
        double[] dArr = zzcd.zzhm;
        for (int i = 0; i < this.size; i++) {
            if (this.zzhm[i] != dArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        zzi(i);
        return Double.valueOf(this.zzhm[i]);
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzct.zzk(Double.doubleToLongBits(this.zzhm[i2]));
        }
        return i;
    }

    public final /* synthetic */ Object remove(int i) {
        zzap();
        zzi(i);
        double d = this.zzhm[i];
        if (i < this.size - 1) {
            System.arraycopy(this.zzhm, i + 1, this.zzhm, i, this.size - i);
        }
        this.size--;
        this.modCount++;
        return Double.valueOf(d);
    }

    public final boolean remove(Object obj) {
        zzap();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Double.valueOf(this.zzhm[i]))) {
                System.arraycopy(this.zzhm, i + 1, this.zzhm, i, this.size - i);
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
        System.arraycopy(this.zzhm, i2, this.zzhm, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        double doubleValue = ((Double) obj).doubleValue();
        zzap();
        zzi(i);
        double d = this.zzhm[i];
        this.zzhm[i] = doubleValue;
        return Double.valueOf(d);
    }

    public final int size() {
        return this.size;
    }

    public final void zzc(double d) {
        zzc(this.size, d);
    }

    public final /* synthetic */ zzcw zzk(int i) {
        if (i >= this.size) {
            return new zzcd(Arrays.copyOf(this.zzhm, i), this.size);
        }
        throw new IllegalArgumentException();
    }
}
