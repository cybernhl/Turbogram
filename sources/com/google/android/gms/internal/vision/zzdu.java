package com.google.android.gms.internal.vision;

final class zzdu {
    private static final zzds zzna = zzcu();
    private static final zzds zznb = new zzdt();

    static zzds zzcs() {
        return zzna;
    }

    static zzds zzct() {
        return zznb;
    }

    private static zzds zzcu() {
        try {
            return (zzds) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }
}
