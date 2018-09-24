package com.google.android.gms.internal.vision;

import java.lang.reflect.Type;

public enum zzcm {
    DOUBLE(0, zzco.SCALAR, zzcz.DOUBLE),
    FLOAT(1, zzco.SCALAR, zzcz.FLOAT),
    INT64(2, zzco.SCALAR, zzcz.LONG),
    UINT64(3, zzco.SCALAR, zzcz.LONG),
    INT32(4, zzco.SCALAR, zzcz.INT),
    FIXED64(5, zzco.SCALAR, zzcz.LONG),
    FIXED32(6, zzco.SCALAR, zzcz.INT),
    BOOL(7, zzco.SCALAR, zzcz.BOOLEAN),
    STRING(8, zzco.SCALAR, zzcz.STRING),
    MESSAGE(9, zzco.SCALAR, zzcz.MESSAGE),
    BYTES(10, zzco.SCALAR, zzcz.BYTE_STRING),
    UINT32(11, zzco.SCALAR, zzcz.INT),
    ENUM(12, zzco.SCALAR, zzcz.ENUM),
    SFIXED32(13, zzco.SCALAR, zzcz.INT),
    SFIXED64(14, zzco.SCALAR, zzcz.LONG),
    SINT32(15, zzco.SCALAR, zzcz.INT),
    SINT64(16, zzco.SCALAR, zzcz.LONG),
    GROUP(17, zzco.SCALAR, zzcz.MESSAGE),
    DOUBLE_LIST(18, zzco.VECTOR, zzcz.DOUBLE),
    FLOAT_LIST(19, zzco.VECTOR, zzcz.FLOAT),
    INT64_LIST(20, zzco.VECTOR, zzcz.LONG),
    UINT64_LIST(21, zzco.VECTOR, zzcz.LONG),
    INT32_LIST(22, zzco.VECTOR, zzcz.INT),
    FIXED64_LIST(23, zzco.VECTOR, zzcz.LONG),
    FIXED32_LIST(24, zzco.VECTOR, zzcz.INT),
    BOOL_LIST(25, zzco.VECTOR, zzcz.BOOLEAN),
    STRING_LIST(26, zzco.VECTOR, zzcz.STRING),
    MESSAGE_LIST(27, zzco.VECTOR, zzcz.MESSAGE),
    BYTES_LIST(28, zzco.VECTOR, zzcz.BYTE_STRING),
    UINT32_LIST(29, zzco.VECTOR, zzcz.INT),
    ENUM_LIST(30, zzco.VECTOR, zzcz.ENUM),
    SFIXED32_LIST(31, zzco.VECTOR, zzcz.INT),
    SFIXED64_LIST(32, zzco.VECTOR, zzcz.LONG),
    SINT32_LIST(33, zzco.VECTOR, zzcz.INT),
    SINT64_LIST(34, zzco.VECTOR, zzcz.LONG),
    DOUBLE_LIST_PACKED(35, zzco.PACKED_VECTOR, zzcz.DOUBLE),
    FLOAT_LIST_PACKED(36, zzco.PACKED_VECTOR, zzcz.FLOAT),
    INT64_LIST_PACKED(37, zzco.PACKED_VECTOR, zzcz.LONG),
    UINT64_LIST_PACKED(38, zzco.PACKED_VECTOR, zzcz.LONG),
    INT32_LIST_PACKED(39, zzco.PACKED_VECTOR, zzcz.INT),
    FIXED64_LIST_PACKED(40, zzco.PACKED_VECTOR, zzcz.LONG),
    FIXED32_LIST_PACKED(41, zzco.PACKED_VECTOR, zzcz.INT),
    BOOL_LIST_PACKED(42, zzco.PACKED_VECTOR, zzcz.BOOLEAN),
    UINT32_LIST_PACKED(43, zzco.PACKED_VECTOR, zzcz.INT),
    ENUM_LIST_PACKED(44, zzco.PACKED_VECTOR, zzcz.ENUM),
    SFIXED32_LIST_PACKED(45, zzco.PACKED_VECTOR, zzcz.INT),
    SFIXED64_LIST_PACKED(46, zzco.PACKED_VECTOR, zzcz.LONG),
    SINT32_LIST_PACKED(47, zzco.PACKED_VECTOR, zzcz.INT),
    SINT64_LIST_PACKED(48, zzco.PACKED_VECTOR, zzcz.LONG),
    GROUP_LIST(49, zzco.VECTOR, zzcz.MESSAGE),
    MAP(50, zzco.MAP, zzcz.VOID);
    
    private static final zzcm[] zzkd = null;
    private static final Type[] zzke = null;
    private final int id;
    private final zzcz zzjz;
    private final zzco zzka;
    private final Class<?> zzkb;
    private final boolean zzkc;

    static {
        zzke = new Type[0];
        zzcm[] values = values();
        zzkd = new zzcm[values.length];
        int length = values.length;
        int i;
        while (i < length) {
            zzcm zzcm = values[i];
            zzkd[zzcm.id] = zzcm;
            i++;
        }
    }

    private zzcm(int i, zzco zzco, zzcz zzcz) {
        this.id = i;
        this.zzka = zzco;
        this.zzjz = zzcz;
        switch (zzcn.zzkg[zzco.ordinal()]) {
            case 1:
                this.zzkb = zzcz.zzch();
                break;
            case 2:
                this.zzkb = zzcz.zzch();
                break;
            default:
                this.zzkb = null;
                break;
        }
        boolean z = false;
        if (zzco == zzco.SCALAR) {
            switch (zzcn.zzkh[zzcz.ordinal()]) {
                case 1:
                case 2:
                case 3:
                    break;
                default:
                    z = true;
                    break;
            }
        }
        this.zzkc = z;
    }

    public final int id() {
        return this.id;
    }
}
