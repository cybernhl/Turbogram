package com.google.android.gms.internal.vision;

final class zzce {
    private static final Class<?> zzhn = zzbd();

    private static Class<?> zzbd() {
        try {
            return Class.forName("com.google.protobuf.ExtensionRegistry");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static zzcf zzbe() {
        if (zzhn != null) {
            try {
                return (zzcf) zzhn.getDeclaredMethod("getEmptyRegistry", new Class[0]).invoke(null, new Object[0]);
            } catch (Exception e) {
            }
        }
        return zzcf.zzhq;
    }
}
