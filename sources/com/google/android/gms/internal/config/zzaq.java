package com.google.android.gms.internal.config;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue;
import java.nio.charset.Charset;
import java.util.regex.Pattern;

public final class zzaq implements FirebaseRemoteConfigValue {
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final Pattern zzl = Pattern.compile("^(1|true|t|yes|y|on)$", 2);
    public static final Pattern zzm = Pattern.compile("^(0|false|f|no|n|off|)$", 2);
    private final byte[] zzbb;
    private final int zzbc;

    public zzaq(byte[] bArr, int i) {
        this.zzbb = bArr;
        this.zzbc = i;
    }

    public final boolean asBoolean() throws IllegalArgumentException {
        if (this.zzbc == 0) {
            return false;
        }
        CharSequence trim = asString().trim();
        if (zzl.matcher(trim).matches()) {
            return true;
        }
        if (zzm.matcher(trim).matches()) {
            return false;
        }
        throw new IllegalArgumentException(String.format("[Value: %s] cannot be converted to a %s.", new Object[]{trim, "boolean"}));
    }

    public final byte[] asByteArray() {
        return this.zzbc == 0 ? FirebaseRemoteConfig.DEFAULT_VALUE_FOR_BYTE_ARRAY : this.zzbb;
    }

    public final double asDouble() {
        if (this.zzbc == 0) {
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
        try {
            return Double.valueOf(asString().trim()).doubleValue();
        } catch (Throwable e) {
            throw new IllegalArgumentException(String.format("[Value: %s] cannot be converted to a %s.", new Object[]{r1, "double"}), e);
        }
    }

    public final long asLong() {
        if (this.zzbc == 0) {
            return 0;
        }
        try {
            return Long.valueOf(asString().trim()).longValue();
        } catch (Throwable e) {
            throw new IllegalArgumentException(String.format("[Value: %s] cannot be converted to a %s.", new Object[]{r1, "long"}), e);
        }
    }

    public final String asString() {
        if (this.zzbc == 0) {
            return "";
        }
        if (this.zzbb != null) {
            return new String(this.zzbb, UTF_8);
        }
        throw new IllegalArgumentException("Value is null, and cannot be converted to the desired type.");
    }

    public final int getSource() {
        return this.zzbc;
    }
}
