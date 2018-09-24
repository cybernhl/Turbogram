package com.google.android.gms.vision;

import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.vision.Detector.Detections;
import com.google.android.gms.vision.Detector.Processor;

public abstract class FocusingProcessor<T> implements Processor<T> {
    private Tracker<T> zzak;
    private int zzal = 3;
    private boolean zzam = false;
    private int zzan;
    private int zzao = 0;
    private Detector<T> zzr;

    public FocusingProcessor(Detector<T> detector, Tracker<T> tracker) {
        this.zzr = detector;
        this.zzak = tracker;
    }

    public void receiveDetections(Detections<T> detections) {
        SparseArray detectedItems = detections.getDetectedItems();
        if (detectedItems.size() == 0) {
            if (this.zzao == this.zzal) {
                this.zzak.onDone();
                this.zzam = false;
            } else {
                this.zzak.onMissing(detections);
            }
            this.zzao++;
            return;
        }
        this.zzao = 0;
        if (this.zzam) {
            Object obj = detectedItems.get(this.zzan);
            if (obj != null) {
                this.zzak.onUpdate(detections, obj);
                return;
            } else {
                this.zzak.onDone();
                this.zzam = false;
            }
        }
        int selectFocus = selectFocus(detections);
        Object obj2 = detectedItems.get(selectFocus);
        if (obj2 == null) {
            Log.w("FocusingProcessor", "Invalid focus selected: " + selectFocus);
            return;
        }
        this.zzam = true;
        this.zzan = selectFocus;
        this.zzr.setFocus(this.zzan);
        this.zzak.onNewItem(this.zzan, obj2);
        this.zzak.onUpdate(detections, obj2);
    }

    public void release() {
        this.zzak.onDone();
    }

    public abstract int selectFocus(Detections<T> detections);

    protected final void zza(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Invalid max gap: " + i);
        }
        this.zzal = i;
    }
}
