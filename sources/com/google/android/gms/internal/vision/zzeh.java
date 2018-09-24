package com.google.android.gms.internal.vision;

final class zzeh {
    private static final zzef zznv = zzdb();
    private static final zzef zznw = new zzeg();

    static zzef zzcz() {
        return zznv;
    }

    static zzef zzda() {
        return zznw;
    }

    private static zzef zzdb() {
        try {
            return (zzef) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }
}
