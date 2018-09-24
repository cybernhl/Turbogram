package com.google.android.gms.internal.vision;

final class zzci {
    private static final zzcg<?> zzhs = new zzch();
    private static final zzcg<?> zzht = zzbh();

    private static zzcg<?> zzbh() {
        try {
            return (zzcg) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }

    static zzcg<?> zzbi() {
        return zzhs;
    }

    static zzcg<?> zzbj() {
        if (zzht != null) {
            return zzht;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}
