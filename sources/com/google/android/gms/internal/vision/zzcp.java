package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzcp extends zzbi<Float> implements zzcw<Float>, zzej, RandomAccess {
    private static final zzcp zzko;
    private int size;
    private float[] zzkp;

    static {
        zzbi zzcp = new zzcp();
        zzko = zzcp;
        zzcp.zzao();
    }

    zzcp() {
        this(new float[10], 0);
    }

    private zzcp(float[] fArr, int i) {
        this.zzkp = fArr;
        this.size = i;
    }

    private final void zzc(int i, float f) {
        zzap();
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException(zzj(i));
        }
        if (this.size < this.zzkp.length) {
            System.arraycopy(this.zzkp, i, this.zzkp, i + 1, this.size - i);
        } else {
            Object obj = new float[(((this.size * 3) / 2) + 1)];
            System.arraycopy(this.zzkp, 0, obj, 0, i);
            System.arraycopy(this.zzkp, i, obj, i + 1, this.size - i);
            this.zzkp = obj;
        }
        this.zzkp[i] = f;
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
        zzc(i, ((Float) obj).floatValue());
    }

    public final boolean addAll(Collection<? extends Float> collection) {
        zzap();
        zzct.checkNotNull(collection);
        if (!(collection instanceof zzcp)) {
            return super.addAll(collection);
        }
        zzcp zzcp = (zzcp) collection;
        if (zzcp.size == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.size < zzcp.size) {
            throw new OutOfMemoryError();
        }
        int i = this.size + zzcp.size;
        if (i > this.zzkp.length) {
            this.zzkp = Arrays.copyOf(this.zzkp, i);
        }
        System.arraycopy(zzcp.zzkp, 0, this.zzkp, this.size, zzcp.size);
        this.size = i;
        this.modCount++;
        return true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzcp)) {
            return super.equals(obj);
        }
        zzcp zzcp = (zzcp) obj;
        if (this.size != zzcp.size) {
            return false;
        }
        float[] fArr = zzcp.zzkp;
        for (int i = 0; i < this.size; i++) {
            if (this.zzkp[i] != fArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        zzi(i);
        return Float.valueOf(this.zzkp[i]);
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + Float.floatToIntBits(this.zzkp[i2]);
        }
        return i;
    }

    public final /* synthetic */ Object remove(int i) {
        zzap();
        zzi(i);
        float f = this.zzkp[i];
        if (i < this.size - 1) {
            System.arraycopy(this.zzkp, i + 1, this.zzkp, i, this.size - i);
        }
        this.size--;
        this.modCount++;
        return Float.valueOf(f);
    }

    public final boolean remove(Object obj) {
        zzap();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Float.valueOf(this.zzkp[i]))) {
                System.arraycopy(this.zzkp, i + 1, this.zzkp, i, this.size - i);
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
        System.arraycopy(this.zzkp, i2, this.zzkp, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        float floatValue = ((Float) obj).floatValue();
        zzap();
        zzi(i);
        float f = this.zzkp[i];
        this.zzkp[i] = floatValue;
        return Float.valueOf(f);
    }

    public final int size() {
        return this.size;
    }

    public final void zze(float f) {
        zzc(this.size, f);
    }

    public final /* synthetic */ zzcw zzk(int i) {
        if (i >= this.size) {
            return new zzcp(Arrays.copyOf(this.zzkp, i), this.size);
        }
        throw new IllegalArgumentException();
    }
}
