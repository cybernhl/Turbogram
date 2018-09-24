package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzcs extends zzbi<Integer> implements zzcw<Integer>, zzej, RandomAccess {
    private static final zzcs zzlm;
    private int size;
    private int[] zzln;

    static {
        zzbi zzcs = new zzcs();
        zzlm = zzcs;
        zzcs.zzao();
    }

    zzcs() {
        this(new int[10], 0);
    }

    private zzcs(int[] iArr, int i) {
        this.zzln = iArr;
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

    private final void zzq(int i, int i2) {
        zzap();
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException(zzj(i));
        }
        if (this.size < this.zzln.length) {
            System.arraycopy(this.zzln, i, this.zzln, i + 1, this.size - i);
        } else {
            Object obj = new int[(((this.size * 3) / 2) + 1)];
            System.arraycopy(this.zzln, 0, obj, 0, i);
            System.arraycopy(this.zzln, i, obj, i + 1, this.size - i);
            this.zzln = obj;
        }
        this.zzln[i] = i2;
        this.size++;
        this.modCount++;
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzq(i, ((Integer) obj).intValue());
    }

    public final boolean addAll(Collection<? extends Integer> collection) {
        zzap();
        zzct.checkNotNull(collection);
        if (!(collection instanceof zzcs)) {
            return super.addAll(collection);
        }
        zzcs zzcs = (zzcs) collection;
        if (zzcs.size == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.size < zzcs.size) {
            throw new OutOfMemoryError();
        }
        int i = this.size + zzcs.size;
        if (i > this.zzln.length) {
            this.zzln = Arrays.copyOf(this.zzln, i);
        }
        System.arraycopy(zzcs.zzln, 0, this.zzln, this.size, zzcs.size);
        this.size = i;
        this.modCount++;
        return true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzcs)) {
            return super.equals(obj);
        }
        zzcs zzcs = (zzcs) obj;
        if (this.size != zzcs.size) {
            return false;
        }
        int[] iArr = zzcs.zzln;
        for (int i = 0; i < this.size; i++) {
            if (this.zzln[i] != iArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        return Integer.valueOf(getInt(i));
    }

    public final int getInt(int i) {
        zzi(i);
        return this.zzln[i];
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + this.zzln[i2];
        }
        return i;
    }

    public final /* synthetic */ Object remove(int i) {
        zzap();
        zzi(i);
        int i2 = this.zzln[i];
        if (i < this.size - 1) {
            System.arraycopy(this.zzln, i + 1, this.zzln, i, this.size - i);
        }
        this.size--;
        this.modCount++;
        return Integer.valueOf(i2);
    }

    public final boolean remove(Object obj) {
        zzap();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Integer.valueOf(this.zzln[i]))) {
                System.arraycopy(this.zzln, i + 1, this.zzln, i, this.size - i);
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
        System.arraycopy(this.zzln, i2, this.zzln, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        int intValue = ((Integer) obj).intValue();
        zzap();
        zzi(i);
        int i2 = this.zzln[i];
        this.zzln[i] = intValue;
        return Integer.valueOf(i2);
    }

    public final int size() {
        return this.size;
    }

    public final void zzae(int i) {
        zzq(this.size, i);
    }

    public final /* synthetic */ zzcw zzk(int i) {
        if (i >= this.size) {
            return new zzcs(Arrays.copyOf(this.zzln, i), this.size);
        }
        throw new IllegalArgumentException();
    }
}
