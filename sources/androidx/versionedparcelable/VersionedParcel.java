package androidx.versionedparcelable;

import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.NetworkOnMainThreadException;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.util.ArraySet;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseBooleanArray;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@RestrictTo({Scope.LIBRARY_GROUP})
public abstract class VersionedParcel {
    private static final int EX_BAD_PARCELABLE = -2;
    private static final int EX_ILLEGAL_ARGUMENT = -3;
    private static final int EX_ILLEGAL_STATE = -5;
    private static final int EX_NETWORK_MAIN_THREAD = -6;
    private static final int EX_NULL_POINTER = -4;
    private static final int EX_PARCELABLE = -9;
    private static final int EX_SECURITY = -1;
    private static final int EX_UNSUPPORTED_OPERATION = -7;
    private static final String TAG = "VersionedParcel";
    private static final int TYPE_BINDER = 5;
    private static final int TYPE_PARCELABLE = 2;
    private static final int TYPE_SERIALIZABLE = 3;
    private static final int TYPE_STRING = 4;
    private static final int TYPE_VERSIONED_PARCELABLE = 1;

    public static class ParcelException extends RuntimeException {
        public ParcelException(Throwable source) {
            super(source);
        }
    }

    protected abstract void closeField();

    protected abstract VersionedParcel createSubParcel();

    protected abstract boolean readBoolean();

    protected abstract Bundle readBundle();

    protected abstract byte[] readByteArray();

    protected abstract double readDouble();

    protected abstract boolean readField(int i);

    protected abstract float readFloat();

    protected abstract int readInt();

    protected abstract long readLong();

    protected abstract <T extends Parcelable> T readParcelable();

    protected abstract String readString();

    protected abstract IBinder readStrongBinder();

    protected abstract void setOutputField(int i);

    protected abstract void writeBoolean(boolean z);

    protected abstract void writeBundle(Bundle bundle);

    protected abstract void writeByteArray(byte[] bArr);

    protected abstract void writeByteArray(byte[] bArr, int i, int i2);

    protected abstract void writeDouble(double d);

    protected abstract void writeFloat(float f);

    protected abstract void writeInt(int i);

    protected abstract void writeLong(long j);

    protected abstract void writeParcelable(Parcelable parcelable);

    protected abstract void writeString(String str);

    protected abstract void writeStrongBinder(IBinder iBinder);

    protected abstract void writeStrongInterface(IInterface iInterface);

    public boolean isStream() {
        return false;
    }

    public void setSerializationFlags(boolean allowSerialization, boolean ignoreParcelables) {
    }

    public void writeStrongInterface(IInterface val, int fieldId) {
        setOutputField(fieldId);
        writeStrongInterface(val);
    }

    public void writeBundle(Bundle val, int fieldId) {
        setOutputField(fieldId);
        writeBundle(val);
    }

    public void writeBoolean(boolean val, int fieldId) {
        setOutputField(fieldId);
        writeBoolean(val);
    }

    public void writeByteArray(byte[] b, int fieldId) {
        setOutputField(fieldId);
        writeByteArray(b);
    }

    public void writeByteArray(byte[] b, int offset, int len, int fieldId) {
        setOutputField(fieldId);
        writeByteArray(b, offset, len);
    }

    public void writeInt(int val, int fieldId) {
        setOutputField(fieldId);
        writeInt(val);
    }

    public void writeLong(long val, int fieldId) {
        setOutputField(fieldId);
        writeLong(val);
    }

    public void writeFloat(float val, int fieldId) {
        setOutputField(fieldId);
        writeFloat(val);
    }

    public void writeDouble(double val, int fieldId) {
        setOutputField(fieldId);
        writeDouble(val);
    }

    public void writeString(String val, int fieldId) {
        setOutputField(fieldId);
        writeString(val);
    }

    public void writeStrongBinder(IBinder val, int fieldId) {
        setOutputField(fieldId);
        writeStrongBinder(val);
    }

    public void writeParcelable(Parcelable p, int fieldId) {
        setOutputField(fieldId);
        writeParcelable(p);
    }

    public boolean readBoolean(boolean def, int fieldId) {
        return !readField(fieldId) ? def : readBoolean();
    }

    public int readInt(int def, int fieldId) {
        return !readField(fieldId) ? def : readInt();
    }

    public long readLong(long def, int fieldId) {
        return !readField(fieldId) ? def : readLong();
    }

    public float readFloat(float def, int fieldId) {
        return !readField(fieldId) ? def : readFloat();
    }

    public double readDouble(double def, int fieldId) {
        return !readField(fieldId) ? def : readDouble();
    }

    public String readString(String def, int fieldId) {
        return !readField(fieldId) ? def : readString();
    }

    public IBinder readStrongBinder(IBinder def, int fieldId) {
        return !readField(fieldId) ? def : readStrongBinder();
    }

    public byte[] readByteArray(byte[] def, int fieldId) {
        return !readField(fieldId) ? def : readByteArray();
    }

    public <T extends Parcelable> T readParcelable(T def, int fieldId) {
        return !readField(fieldId) ? def : readParcelable();
    }

    public Bundle readBundle(Bundle def, int fieldId) {
        return !readField(fieldId) ? def : readBundle();
    }

    public void writeByte(byte val, int fieldId) {
        setOutputField(fieldId);
        writeInt(val);
    }

    @RequiresApi(api = 21)
    public void writeSize(Size val, int fieldId) {
        setOutputField(fieldId);
        writeBoolean(val != null);
        if (val != null) {
            writeInt(val.getWidth());
            writeInt(val.getHeight());
        }
    }

    @RequiresApi(api = 21)
    public void writeSizeF(SizeF val, int fieldId) {
        setOutputField(fieldId);
        writeBoolean(val != null);
        if (val != null) {
            writeFloat(val.getWidth());
            writeFloat(val.getHeight());
        }
    }

    public void writeSparseBooleanArray(SparseBooleanArray val, int fieldId) {
        setOutputField(fieldId);
        if (val == null) {
            writeInt(-1);
            return;
        }
        int n = val.size();
        writeInt(n);
        for (int i = 0; i < n; i++) {
            writeInt(val.keyAt(i));
            writeBoolean(val.valueAt(i));
        }
    }

    public void writeBooleanArray(boolean[] val, int fieldId) {
        setOutputField(fieldId);
        writeBooleanArray(val);
    }

    protected void writeBooleanArray(boolean[] val) {
        if (val != null) {
            writeInt(n);
            for (boolean z : val) {
                writeInt(z ? 1 : 0);
            }
            return;
        }
        writeInt(-1);
    }

    public boolean[] readBooleanArray(boolean[] def, int fieldId) {
        return !readField(fieldId) ? def : readBooleanArray();
    }

    protected boolean[] readBooleanArray() {
        int n = readInt();
        if (n < 0) {
            return null;
        }
        boolean[] val = new boolean[n];
        for (int i = 0; i < n; i++) {
            val[i] = readInt() != 0;
        }
        return val;
    }

    public void writeCharArray(char[] val, int fieldId) {
        setOutputField(fieldId);
        if (val != null) {
            writeInt(n);
            for (char writeInt : val) {
                writeInt(writeInt);
            }
            return;
        }
        writeInt(-1);
    }

    public char[] readCharArray(char[] def, int fieldId) {
        if (!readField(fieldId)) {
            return def;
        }
        int n = readInt();
        if (n < 0) {
            return null;
        }
        char[] val = new char[n];
        for (int i = 0; i < n; i++) {
            val[i] = (char) readInt();
        }
        return val;
    }

    public void writeIntArray(int[] val, int fieldId) {
        setOutputField(fieldId);
        writeIntArray(val);
    }

    protected void writeIntArray(int[] val) {
        if (val != null) {
            writeInt(n);
            for (int writeInt : val) {
                writeInt(writeInt);
            }
            return;
        }
        writeInt(-1);
    }

    public int[] readIntArray(int[] def, int fieldId) {
        return !readField(fieldId) ? def : readIntArray();
    }

    protected int[] readIntArray() {
        int n = readInt();
        if (n < 0) {
            return null;
        }
        int[] val = new int[n];
        for (int i = 0; i < n; i++) {
            val[i] = readInt();
        }
        return val;
    }

    public void writeLongArray(long[] val, int fieldId) {
        setOutputField(fieldId);
        writeLongArray(val);
    }

    protected void writeLongArray(long[] val) {
        if (val != null) {
            writeInt(n);
            for (long writeLong : val) {
                writeLong(writeLong);
            }
            return;
        }
        writeInt(-1);
    }

    public long[] readLongArray(long[] def, int fieldId) {
        return !readField(fieldId) ? def : readLongArray();
    }

    protected long[] readLongArray() {
        int n = readInt();
        if (n < 0) {
            return null;
        }
        long[] val = new long[n];
        for (int i = 0; i < n; i++) {
            val[i] = readLong();
        }
        return val;
    }

    public void writeFloatArray(float[] val, int fieldId) {
        setOutputField(fieldId);
        writeFloatArray(val);
    }

    protected void writeFloatArray(float[] val) {
        if (val != null) {
            writeInt(n);
            for (float writeFloat : val) {
                writeFloat(writeFloat);
            }
            return;
        }
        writeInt(-1);
    }

    public float[] readFloatArray(float[] def, int fieldId) {
        return !readField(fieldId) ? def : readFloatArray();
    }

    protected float[] readFloatArray() {
        int n = readInt();
        if (n < 0) {
            return null;
        }
        float[] val = new float[n];
        for (int i = 0; i < n; i++) {
            val[i] = readFloat();
        }
        return val;
    }

    public void writeDoubleArray(double[] val, int fieldId) {
        setOutputField(fieldId);
        writeDoubleArray(val);
    }

    protected void writeDoubleArray(double[] val) {
        if (val != null) {
            writeInt(n);
            for (double writeDouble : val) {
                writeDouble(writeDouble);
            }
            return;
        }
        writeInt(-1);
    }

    public double[] readDoubleArray(double[] def, int fieldId) {
        return !readField(fieldId) ? def : readDoubleArray();
    }

    protected double[] readDoubleArray() {
        int n = readInt();
        if (n < 0) {
            return null;
        }
        double[] val = new double[n];
        for (int i = 0; i < n; i++) {
            val[i] = readDouble();
        }
        return val;
    }

    public <T> void writeSet(Set<T> val, int fieldId) {
        writeCollection(val, fieldId);
    }

    public <T> void writeList(List<T> val, int fieldId) {
        writeCollection(val, fieldId);
    }

    private <T> void writeCollection(Collection<T> val, int fieldId) {
        setOutputField(fieldId);
        if (val == null) {
            writeInt(-1);
            return;
        }
        int n = val.size();
        writeInt(n);
        if (n > 0) {
            int type = getType(val.iterator().next());
            writeInt(type);
            switch (type) {
                case 1:
                    for (T v : val) {
                        writeVersionedParcelable(v);
                    }
                    return;
                case 2:
                    for (T v2 : val) {
                        writeParcelable(v2);
                    }
                    return;
                case 3:
                    for (T v22 : val) {
                        writeSerializable(v22);
                    }
                    return;
                case 4:
                    for (T v222 : val) {
                        writeString(v222);
                    }
                    return;
                case 5:
                    for (T v2222 : val) {
                        writeStrongBinder(v2222);
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public <T> void writeArray(T[] val, int fieldId) {
        setOutputField(fieldId);
        writeArray(val);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected <T> void writeArray(T[] r5) {
        /*
        r4 = this;
        if (r5 != 0) goto L_0x0007;
    L_0x0002:
        r3 = -1;
        r4.writeInt(r3);
    L_0x0006:
        return;
    L_0x0007:
        r1 = r5.length;
        r0 = 0;
        r4.writeInt(r1);
        if (r1 <= 0) goto L_0x0006;
    L_0x000e:
        r3 = 0;
        r3 = r5[r3];
        r2 = r4.getType(r3);
        r4.writeInt(r2);
        switch(r2) {
            case 1: goto L_0x001c;
            case 2: goto L_0x0034;
            case 3: goto L_0x0040;
            case 4: goto L_0x0028;
            case 5: goto L_0x004c;
            default: goto L_0x001b;
        };
    L_0x001b:
        goto L_0x0006;
    L_0x001c:
        if (r0 >= r1) goto L_0x0006;
    L_0x001e:
        r3 = r5[r0];
        r3 = (androidx.versionedparcelable.VersionedParcelable) r3;
        r4.writeVersionedParcelable(r3);
        r0 = r0 + 1;
        goto L_0x001c;
    L_0x0028:
        if (r0 >= r1) goto L_0x0006;
    L_0x002a:
        r3 = r5[r0];
        r3 = (java.lang.String) r3;
        r4.writeString(r3);
        r0 = r0 + 1;
        goto L_0x0028;
    L_0x0034:
        if (r0 >= r1) goto L_0x0006;
    L_0x0036:
        r3 = r5[r0];
        r3 = (android.os.Parcelable) r3;
        r4.writeParcelable(r3);
        r0 = r0 + 1;
        goto L_0x0034;
    L_0x0040:
        if (r0 >= r1) goto L_0x0006;
    L_0x0042:
        r3 = r5[r0];
        r3 = (java.io.Serializable) r3;
        r4.writeSerializable(r3);
        r0 = r0 + 1;
        goto L_0x0040;
    L_0x004c:
        if (r0 >= r1) goto L_0x0006;
    L_0x004e:
        r3 = r5[r0];
        r3 = (android.os.IBinder) r3;
        r4.writeStrongBinder(r3);
        r0 = r0 + 1;
        goto L_0x004c;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.versionedparcelable.VersionedParcel.writeArray(java.lang.Object[]):void");
    }

    private <T> int getType(T t) {
        if (t instanceof String) {
            return 4;
        }
        if (t instanceof Parcelable) {
            return 2;
        }
        if (t instanceof VersionedParcelable) {
            return 1;
        }
        if (t instanceof Serializable) {
            return 3;
        }
        if (t instanceof IBinder) {
            return 5;
        }
        throw new IllegalArgumentException(t.getClass().getName() + " cannot be VersionedParcelled");
    }

    public void writeVersionedParcelable(VersionedParcelable p, int fieldId) {
        setOutputField(fieldId);
        writeVersionedParcelable(p);
    }

    protected void writeVersionedParcelable(VersionedParcelable p) {
        if (p == null) {
            writeString(null);
            return;
        }
        writeVersionedParcelableCreator(p);
        VersionedParcel subParcel = createSubParcel();
        writeToParcel(p, subParcel);
        subParcel.closeField();
    }

    private void writeVersionedParcelableCreator(VersionedParcelable p) {
        try {
            writeString(findParcelClass(p.getClass()).getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(p.getClass().getSimpleName() + " does not have a Parcelizer", e);
        }
    }

    public void writeSerializable(Serializable s, int fieldId) {
        setOutputField(fieldId);
        writeSerializable(s);
    }

    private void writeSerializable(Serializable s) {
        if (s == null) {
            writeString(null);
            return;
        }
        String name = s.getClass().getName();
        writeString(name);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(s);
            oos.close();
            writeByteArray(baos.toByteArray());
        } catch (IOException ioe) {
            throw new RuntimeException("VersionedParcelable encountered IOException writing serializable object (name = " + name + ")", ioe);
        }
    }

    public void writeException(Exception e, int fieldId) {
        setOutputField(fieldId);
        if (e == null) {
            writeNoException();
            return;
        }
        int code = 0;
        if ((e instanceof Parcelable) && e.getClass().getClassLoader() == Parcelable.class.getClassLoader()) {
            code = EX_PARCELABLE;
        } else if (e instanceof SecurityException) {
            code = -1;
        } else if (e instanceof BadParcelableException) {
            code = -2;
        } else if (e instanceof IllegalArgumentException) {
            code = -3;
        } else if (e instanceof NullPointerException) {
            code = -4;
        } else if (e instanceof IllegalStateException) {
            code = -5;
        } else if (e instanceof NetworkOnMainThreadException) {
            code = EX_NETWORK_MAIN_THREAD;
        } else if (e instanceof UnsupportedOperationException) {
            code = EX_UNSUPPORTED_OPERATION;
        }
        writeInt(code);
        if (code != 0) {
            writeString(e.getMessage());
            switch (code) {
                case EX_PARCELABLE /*-9*/:
                    writeParcelable((Parcelable) e);
                    return;
                default:
                    return;
            }
        } else if (e instanceof RuntimeException) {
            throw ((RuntimeException) e);
        } else {
            throw new RuntimeException(e);
        }
    }

    protected void writeNoException() {
        writeInt(0);
    }

    public Exception readException(Exception def, int fieldId) {
        if (!readField(fieldId)) {
            return def;
        }
        int code = readExceptionCode();
        if (code != 0) {
            return readException(code, readString());
        }
        return def;
    }

    private int readExceptionCode() {
        return readInt();
    }

    private Exception readException(int code, String msg) {
        return createException(code, msg);
    }

    @NonNull
    protected static Throwable getRootCause(@NonNull Throwable t) {
        while (t.getCause() != null) {
            t = t.getCause();
        }
        return t;
    }

    private Exception createException(int code, String msg) {
        switch (code) {
            case EX_PARCELABLE /*-9*/:
                return (Exception) readParcelable();
            case EX_UNSUPPORTED_OPERATION /*-7*/:
                return new UnsupportedOperationException(msg);
            case EX_NETWORK_MAIN_THREAD /*-6*/:
                return new NetworkOnMainThreadException();
            case -5:
                return new IllegalStateException(msg);
            case -4:
                return new NullPointerException(msg);
            case -3:
                return new IllegalArgumentException(msg);
            case -2:
                return new BadParcelableException(msg);
            case -1:
                return new SecurityException(msg);
            default:
                return new RuntimeException("Unknown exception code: " + code + " msg " + msg);
        }
    }

    public byte readByte(byte def, int fieldId) {
        return !readField(fieldId) ? def : (byte) (readInt() & 255);
    }

    @RequiresApi(api = 21)
    public Size readSize(Size def, int fieldId) {
        if (!readField(fieldId)) {
            return def;
        }
        if (readBoolean()) {
            return new Size(readInt(), readInt());
        }
        return null;
    }

    @RequiresApi(api = 21)
    public SizeF readSizeF(SizeF def, int fieldId) {
        if (!readField(fieldId)) {
            return def;
        }
        if (readBoolean()) {
            return new SizeF(readFloat(), readFloat());
        }
        return null;
    }

    public SparseBooleanArray readSparseBooleanArray(SparseBooleanArray def, int fieldId) {
        if (!readField(fieldId)) {
            return def;
        }
        int n = readInt();
        if (n < 0) {
            return null;
        }
        SparseBooleanArray sa = new SparseBooleanArray(n);
        for (int i = 0; i < n; i++) {
            sa.put(readInt(), readBoolean());
        }
        return sa;
    }

    public <T> Set<T> readSet(Set<T> def, int fieldId) {
        return !readField(fieldId) ? def : (Set) readCollection(fieldId, new ArraySet());
    }

    public <T> List<T> readList(List<T> def, int fieldId) {
        return !readField(fieldId) ? def : (List) readCollection(fieldId, new ArrayList());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private <T, S extends java.util.Collection<T>> S readCollection(int r4, S r5) {
        /*
        r3 = this;
        r2 = 0;
        r0 = r3.readInt();
        if (r0 >= 0) goto L_0x0009;
    L_0x0007:
        r5 = r2;
    L_0x0008:
        return r5;
    L_0x0009:
        if (r0 == 0) goto L_0x0008;
    L_0x000b:
        r1 = r3.readInt();
        if (r0 >= 0) goto L_0x0013;
    L_0x0011:
        r5 = r2;
        goto L_0x0008;
    L_0x0013:
        switch(r1) {
            case 1: goto L_0x0017;
            case 2: goto L_0x002f;
            case 3: goto L_0x003b;
            case 4: goto L_0x0023;
            case 5: goto L_0x0047;
            default: goto L_0x0016;
        };
    L_0x0016:
        goto L_0x0008;
    L_0x0017:
        if (r0 <= 0) goto L_0x0008;
    L_0x0019:
        r2 = r3.readVersionedParcelable();
        r5.add(r2);
        r0 = r0 + -1;
        goto L_0x0017;
    L_0x0023:
        if (r0 <= 0) goto L_0x0008;
    L_0x0025:
        r2 = r3.readString();
        r5.add(r2);
        r0 = r0 + -1;
        goto L_0x0023;
    L_0x002f:
        if (r0 <= 0) goto L_0x0008;
    L_0x0031:
        r2 = r3.readParcelable();
        r5.add(r2);
        r0 = r0 + -1;
        goto L_0x002f;
    L_0x003b:
        if (r0 <= 0) goto L_0x0008;
    L_0x003d:
        r2 = r3.readSerializable();
        r5.add(r2);
        r0 = r0 + -1;
        goto L_0x003b;
    L_0x0047:
        if (r0 <= 0) goto L_0x0008;
    L_0x0049:
        r2 = r3.readStrongBinder();
        r5.add(r2);
        r0 = r0 + -1;
        goto L_0x0047;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.versionedparcelable.VersionedParcel.readCollection(int, java.util.Collection):S");
    }

    public <T> T[] readArray(T[] def, int fieldId) {
        return !readField(fieldId) ? def : readArray(def);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected <T> T[] readArray(T[] r5) {
        /*
        r4 = this;
        r3 = 0;
        r1 = r4.readInt();
        if (r1 >= 0) goto L_0x0008;
    L_0x0007:
        return r3;
    L_0x0008:
        r0 = new java.util.ArrayList;
        r0.<init>(r1);
        if (r1 == 0) goto L_0x0018;
    L_0x000f:
        r2 = r4.readInt();
        if (r1 < 0) goto L_0x0007;
    L_0x0015:
        switch(r2) {
            case 1: goto L_0x0035;
            case 2: goto L_0x0029;
            case 3: goto L_0x0041;
            case 4: goto L_0x001d;
            case 5: goto L_0x004d;
            default: goto L_0x0018;
        };
    L_0x0018:
        r3 = r0.toArray(r5);
        goto L_0x0007;
    L_0x001d:
        if (r1 <= 0) goto L_0x0018;
    L_0x001f:
        r3 = r4.readString();
        r0.add(r3);
        r1 = r1 + -1;
        goto L_0x001d;
    L_0x0029:
        if (r1 <= 0) goto L_0x0018;
    L_0x002b:
        r3 = r4.readParcelable();
        r0.add(r3);
        r1 = r1 + -1;
        goto L_0x0029;
    L_0x0035:
        if (r1 <= 0) goto L_0x0018;
    L_0x0037:
        r3 = r4.readVersionedParcelable();
        r0.add(r3);
        r1 = r1 + -1;
        goto L_0x0035;
    L_0x0041:
        if (r1 <= 0) goto L_0x0018;
    L_0x0043:
        r3 = r4.readSerializable();
        r0.add(r3);
        r1 = r1 + -1;
        goto L_0x0041;
    L_0x004d:
        if (r1 <= 0) goto L_0x0018;
    L_0x004f:
        r3 = r4.readStrongBinder();
        r0.add(r3);
        r1 = r1 + -1;
        goto L_0x004d;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.versionedparcelable.VersionedParcel.readArray(java.lang.Object[]):T[]");
    }

    public <T extends VersionedParcelable> T readVersionedParcelable(T def, int fieldId) {
        return !readField(fieldId) ? def : readVersionedParcelable();
    }

    protected <T extends VersionedParcelable> T readVersionedParcelable() {
        String name = readString();
        if (name == null) {
            return null;
        }
        return readFromParcel(name, createSubParcel());
    }

    protected Serializable readSerializable() {
        String name = readString();
        if (name == null) {
            return null;
        }
        try {
            return (Serializable) new ObjectInputStream(new ByteArrayInputStream(readByteArray())) {
                protected Class<?> resolveClass(ObjectStreamClass osClass) throws IOException, ClassNotFoundException {
                    Class<?> c = Class.forName(osClass.getName(), false, getClass().getClassLoader());
                    return c != null ? c : super.resolveClass(osClass);
                }
            }.readObject();
        } catch (IOException ioe) {
            throw new RuntimeException("VersionedParcelable encountered IOException reading a Serializable object (name = " + name + ")", ioe);
        } catch (ClassNotFoundException cnfe) {
            throw new RuntimeException("VersionedParcelable encountered ClassNotFoundException reading a Serializable object (name = " + name + ")", cnfe);
        }
    }

    protected static <T extends VersionedParcelable> T readFromParcel(String parcelCls, VersionedParcel versionedParcel) {
        try {
            return (VersionedParcelable) Class.forName(parcelCls, true, VersionedParcel.class.getClassLoader()).getDeclaredMethod("read", new Class[]{VersionedParcel.class}).invoke(null, new Object[]{versionedParcel});
        } catch (IllegalAccessException e) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e);
        } catch (InvocationTargetException e2) {
            if (e2.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e2.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e3);
        } catch (ClassNotFoundException e4) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e4);
        }
    }

    protected static <T extends VersionedParcelable> void writeToParcel(T val, VersionedParcel versionedParcel) {
        try {
            findParcelClass((VersionedParcelable) val).getDeclaredMethod("write", new Class[]{val.getClass(), VersionedParcel.class}).invoke(null, new Object[]{val, versionedParcel});
        } catch (IllegalAccessException e) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e);
        } catch (InvocationTargetException e2) {
            if (e2.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e2.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e3);
        } catch (ClassNotFoundException e4) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e4);
        }
    }

    private static <T extends VersionedParcelable> Class findParcelClass(T val) throws ClassNotFoundException {
        return findParcelClass(val.getClass());
    }

    private static Class findParcelClass(Class<? extends VersionedParcelable> cls) throws ClassNotFoundException {
        String pkg = cls.getPackage().getName();
        return Class.forName(String.format("%s.%sParcelizer", new Object[]{pkg, cls.getSimpleName()}), false, cls.getClassLoader());
    }
}
