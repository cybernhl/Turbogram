package com.google.android.gms.internal.vision;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public final class zzo {
    public static Bitmap zzb(Bitmap bitmap, zzm zzm) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (zzm.rotation != 0) {
            int i;
            Matrix matrix = new Matrix();
            switch (zzm.rotation) {
                case 0:
                    i = 0;
                    break;
                case 1:
                    i = 90;
                    break;
                case 2:
                    i = 180;
                    break;
                case 3:
                    i = 270;
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported rotation degree.");
            }
            matrix.postRotate((float) i);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        }
        if (zzm.rotation == 1 || zzm.rotation == 3) {
            zzm.width = height;
            zzm.height = width;
        }
        return bitmap;
    }
}
