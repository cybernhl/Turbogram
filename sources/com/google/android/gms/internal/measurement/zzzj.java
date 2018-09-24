package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzzj {
    public static final int[] zzcax = new int[0];
    private static final int zzcfn = 11;
    private static final int zzcfo = 12;
    private static final int zzcfp = 16;
    private static final int zzcfq = 26;
    public static final long[] zzcfr = new long[0];
    private static final float[] zzcfs = new float[0];
    private static final double[] zzcft = new double[0];
    private static final boolean[] zzcfu = new boolean[0];
    public static final String[] zzcfv = new String[0];
    private static final byte[][] zzcfw = new byte[0][];
    public static final byte[] zzcfx = new byte[0];

    public static final int zzb(zzyx zzyx, int i) throws IOException {
        int i2 = 1;
        int position = zzyx.getPosition();
        zzyx.zzao(i);
        while (zzyx.zzug() == i) {
            zzyx.zzao(i);
            i2++;
        }
        zzyx.zzt(position, i);
        return i2;
    }
}
