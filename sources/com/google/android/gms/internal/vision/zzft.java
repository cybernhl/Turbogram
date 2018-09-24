package com.google.android.gms.internal.vision;

public enum zzft {
    DOUBLE(zzfy.DOUBLE, 1),
    FLOAT(zzfy.FLOAT, 5),
    INT64(zzfy.LONG, 0),
    UINT64(zzfy.LONG, 0),
    INT32(zzfy.INT, 0),
    FIXED64(zzfy.LONG, 1),
    FIXED32(zzfy.INT, 5),
    BOOL(zzfy.BOOLEAN, 0),
    STRING(zzfy.STRING, 2),
    GROUP(zzfy.MESSAGE, 3),
    MESSAGE(zzfy.MESSAGE, 2),
    BYTES(zzfy.BYTE_STRING, 2),
    UINT32(zzfy.INT, 0),
    ENUM(zzfy.ENUM, 0),
    SFIXED32(zzfy.INT, 5),
    SFIXED64(zzfy.LONG, 1),
    SINT32(zzfy.INT, 0),
    SINT64(zzfy.LONG, 0);
    
    private final zzfy zzqo;
    private final int zzqp;

    private zzft(zzfy zzfy, int i) {
        this.zzqo = zzfy;
        this.zzqp = i;
    }

    public final zzfy zzed() {
        return this.zzqo;
    }

    public final int zzee() {
        return this.zzqp;
    }
}
