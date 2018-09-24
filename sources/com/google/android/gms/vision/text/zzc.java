package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.internal.vision.zzr;

final class zzc {
    static Rect zza(Text text) {
        Point[] cornerPoints = text.getCornerPoints();
        int length = cornerPoints.length;
        int i = 0;
        int i2 = Integer.MIN_VALUE;
        int i3 = Integer.MAX_VALUE;
        int i4 = Integer.MIN_VALUE;
        int i5 = Integer.MAX_VALUE;
        while (i < length) {
            Point point = cornerPoints[i];
            i5 = Math.min(i5, point.x);
            int max = Math.max(i4, point.x);
            int min = Math.min(i3, point.y);
            i2 = Math.max(i2, point.y);
            i++;
            i3 = min;
            i4 = max;
        }
        return new Rect(i5, i3, i4, i2);
    }

    static Point[] zza(zzr zzr) {
        r0 = new Point[4];
        double sin = Math.sin(Math.toRadians((double) zzr.zzdh));
        double cos = Math.cos(Math.toRadians((double) zzr.zzdh));
        r0[0] = new Point(zzr.left, zzr.top);
        r0[1] = new Point((int) (((double) zzr.left) + (((double) zzr.width) * cos)), (int) (((double) zzr.top) + (((double) zzr.width) * sin)));
        r0[2] = new Point((int) (((double) r0[1].x) - (sin * ((double) zzr.height))), (int) ((cos * ((double) zzr.height)) + ((double) r0[1].y)));
        r0[3] = new Point(r0[0].x + (r0[2].x - r0[1].x), r0[0].y + (r0[2].y - r0[1].y));
        return r0;
    }
}
