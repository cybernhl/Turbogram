package com.google.android.gms.internal.measurement;

final class zzuy {
    private static final Class<?> zzbvi = zzvk();

    private static Class<?> zzvk() {
        try {
            return Class.forName("com.google.protobuf.ExtensionRegistry");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static zzuz zzvl() {
        if (zzbvi != null) {
            try {
                return zzfz("getEmptyRegistry");
            } catch (Exception e) {
            }
        }
        return zzuz.zzbvm;
    }

    static zzuz zzvm() {
        zzuz zzuz = null;
        if (zzbvi != null) {
            try {
                zzuz = zzfz("loadGeneratedRegistry");
            } catch (Exception e) {
            }
        }
        if (zzuz == null) {
            zzuz = zzuz.zzvm();
        }
        return zzuz == null ? zzvl() : zzuz;
    }

    private static final zzuz zzfz(String str) throws Exception {
        return (zzuz) zzbvi.getDeclaredMethod(str, new Class[0]).invoke(null, new Object[0]);
    }
}
