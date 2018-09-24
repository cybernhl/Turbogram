package com.google.android.gms.vision;

import android.util.SparseArray;
import com.google.android.gms.vision.Detector.Detections;
import com.google.android.gms.vision.Detector.Processor;
import java.util.HashSet;
import java.util.Set;

public class MultiProcessor<T> implements Processor<T> {
    private int zzal;
    private Factory<T> zzaz;
    private SparseArray<zza> zzba;

    public static class Builder<T> {
        private MultiProcessor<T> zzbb = new MultiProcessor();

        public Builder(Factory<T> factory) {
            if (factory == null) {
                throw new IllegalArgumentException("No factory supplied.");
            }
            this.zzbb.zzaz = factory;
        }

        public MultiProcessor<T> build() {
            return this.zzbb;
        }

        public Builder<T> setMaxGapFrames(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("Invalid max gap: " + i);
            }
            this.zzbb.zzal = i;
            return this;
        }
    }

    public interface Factory<T> {
        Tracker<T> create(T t);
    }

    class zza {
        private Tracker<T> zzak;
        private int zzao;

        private zza(MultiProcessor multiProcessor) {
            this.zzao = 0;
        }
    }

    private MultiProcessor() {
        this.zzba = new SparseArray();
        this.zzal = 3;
    }

    public void receiveDetections(Detections<T> detections) {
        int i;
        int i2;
        SparseArray detectedItems = detections.getDetectedItems();
        for (i = 0; i < detectedItems.size(); i++) {
            int keyAt = detectedItems.keyAt(i);
            Object valueAt = detectedItems.valueAt(i);
            if (this.zzba.get(keyAt) == null) {
                zza zza = new zza();
                zza.zzak = this.zzaz.create(valueAt);
                zza.zzak.onNewItem(keyAt, valueAt);
                this.zzba.append(keyAt, zza);
            }
        }
        SparseArray detectedItems2 = detections.getDetectedItems();
        Set<Integer> hashSet = new HashSet();
        for (i2 = 0; i2 < this.zzba.size(); i2++) {
            zza zza2;
            int keyAt2 = this.zzba.keyAt(i2);
            if (detectedItems2.get(keyAt2) == null) {
                zza2 = (zza) this.zzba.valueAt(i2);
                zza2.zzao = zza2.zzao + 1;
                if (zza2.zzao >= this.zzal) {
                    zza2.zzak.onDone();
                    hashSet.add(Integer.valueOf(keyAt2));
                } else {
                    zza2.zzak.onMissing(detections);
                }
            }
        }
        for (Integer intValue : hashSet) {
            this.zzba.delete(intValue.intValue());
        }
        detectedItems2 = detections.getDetectedItems();
        for (i2 = 0; i2 < detectedItems2.size(); i2++) {
            i = detectedItems2.keyAt(i2);
            valueAt = detectedItems2.valueAt(i2);
            zza2 = (zza) this.zzba.get(i);
            zza2.zzao = 0;
            zza2.zzak.onUpdate(detections, valueAt);
        }
    }

    public void release() {
        for (int i = 0; i < this.zzba.size(); i++) {
            ((zza) this.zzba.valueAt(i)).zzak.onDone();
        }
        this.zzba.clear();
    }
}
