package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.SparseArray;
import com.google.android.exoplayer2.C0246C;
import com.google.android.gms.internal.vision.zzr;
import com.google.android.gms.internal.vision.zzx;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class TextBlock implements Text {
    private Point[] cornerPoints;
    private zzx[] zzdb;
    private List<Line> zzdc;
    private String zzdd;
    private Rect zzde;

    TextBlock(SparseArray<zzx> sparseArray) {
        this.zzdb = new zzx[sparseArray.size()];
        for (int i = 0; i < this.zzdb.length; i++) {
            this.zzdb[i] = (zzx) sparseArray.valueAt(i);
        }
    }

    public Rect getBoundingBox() {
        if (this.zzde == null) {
            this.zzde = zzc.zza((Text) this);
        }
        return this.zzde;
    }

    public List<? extends Text> getComponents() {
        if (this.zzdb.length == 0) {
            return new ArrayList(0);
        }
        if (this.zzdc == null) {
            this.zzdc = new ArrayList(this.zzdb.length);
            for (zzx line : this.zzdb) {
                this.zzdc.add(new Line(line));
            }
        }
        return this.zzdc;
    }

    public Point[] getCornerPoints() {
        if (this.cornerPoints == null) {
            if (this.zzdb.length == 0) {
                this.cornerPoints = new Point[0];
            } else {
                int i;
                double sin;
                int i2;
                int i3 = Integer.MAX_VALUE;
                int i4 = Integer.MIN_VALUE;
                int i5 = Integer.MAX_VALUE;
                int i6 = Integer.MIN_VALUE;
                for (zzx zzx : this.zzdb) {
                    zzr zzr = zzx.zzdj;
                    zzr zzr2 = this.zzdb[0].zzdj;
                    int i7 = -zzr2.left;
                    int i8 = -zzr2.top;
                    sin = Math.sin(Math.toRadians((double) zzr2.zzdh));
                    double cos = Math.cos(Math.toRadians((double) zzr2.zzdh));
                    Point[] pointArr = new Point[4];
                    pointArr[0] = new Point(zzr.left, zzr.top);
                    pointArr[0].offset(i7, i8);
                    i7 = (int) ((((double) pointArr[0].x) * cos) + (((double) pointArr[0].y) * sin));
                    i8 = (int) ((((double) (-pointArr[0].x)) * sin) + (((double) pointArr[0].y) * cos));
                    pointArr[0].x = i7;
                    pointArr[0].y = i8;
                    pointArr[1] = new Point(zzr.width + i7, i8);
                    pointArr[2] = new Point(zzr.width + i7, zzr.height + i8);
                    pointArr[3] = new Point(i7, zzr.height + i8);
                    for (i2 = 0; i2 < 4; i2++) {
                        Point point = pointArr[i2];
                        i3 = Math.min(i3, point.x);
                        i4 = Math.max(i4, point.x);
                        i5 = Math.min(i5, point.y);
                        i6 = Math.max(i6, point.y);
                    }
                }
                zzr zzr3 = this.zzdb[0].zzdj;
                i2 = zzr3.left;
                int i9 = zzr3.top;
                double sin2 = Math.sin(Math.toRadians((double) zzr3.zzdh));
                sin = Math.cos(Math.toRadians((double) zzr3.zzdh));
                Point[] pointArr2 = new Point[]{new Point(i3, i5), new Point(i4, i5), new Point(i4, i6), new Point(i3, i6)};
                for (i = 0; i < 4; i++) {
                    i5 = (int) ((((double) pointArr2[i].x) * sin2) + (((double) pointArr2[i].y) * sin));
                    pointArr2[i].x = (int) ((((double) pointArr2[i].x) * sin) - (((double) pointArr2[i].y) * sin2));
                    pointArr2[i].y = i5;
                    pointArr2[i].offset(i2, i9);
                }
                this.cornerPoints = pointArr2;
            }
        }
        return this.cornerPoints;
    }

    public String getLanguage() {
        if (this.zzdd != null) {
            return this.zzdd;
        }
        HashMap hashMap = new HashMap();
        for (zzx zzx : this.zzdb) {
            hashMap.put(zzx.zzdd, Integer.valueOf((hashMap.containsKey(zzx.zzdd) ? ((Integer) hashMap.get(zzx.zzdd)).intValue() : 0) + 1));
        }
        this.zzdd = (String) ((Entry) Collections.max(hashMap.entrySet(), new zza(this))).getKey();
        if (this.zzdd == null || this.zzdd.isEmpty()) {
            this.zzdd = C0246C.LANGUAGE_UNDETERMINED;
        }
        return this.zzdd;
    }

    public String getValue() {
        if (this.zzdb.length == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(this.zzdb[0].zzdm);
        for (int i = 1; i < this.zzdb.length; i++) {
            stringBuilder.append("\n");
            stringBuilder.append(this.zzdb[i].zzdm);
        }
        return stringBuilder.toString();
    }
}
