package de.jurihock.voicesmith.dsp;

public final class Math {
    public static final float PI = 3.1415927f;

    public static native float abs(float f, float f2);

    public static native float ceil(float f);

    public static native float cos(float f);

    public static native float imag(float f, float f2);

    public static native float max(float f, float f2);

    public static native short mean(short[] sArr, int i, int i2);

    public static native float min(float f, float f2);

    public static native float pow(float f, float f2);

    public static native float random(float f, float f2);

    public static native float real(float f, float f2);

    public static native float rms(short[] sArr, int i, int i2);

    public static native float rms2dbfs(float f, float f2, float f3);

    public static native float sqrt(float f);

    public static int round(float value) {
        return Math.round(value);
    }
}
