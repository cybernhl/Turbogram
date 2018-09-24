package com.google.android.gms.vision;

import android.util.SparseArray;
import com.google.android.gms.vision.Frame.Metadata;
import javax.annotation.concurrent.GuardedBy;

public abstract class Detector<T> {
    private final Object zzad = new Object();
    @GuardedBy("processorLock")
    private Processor<T> zzae;

    public static class Detections<T> {
        private final SparseArray<T> zzaf;
        private final Metadata zzag;
        private final boolean zzah;

        public Detections(SparseArray<T> sparseArray, Metadata metadata, boolean z) {
            this.zzaf = sparseArray;
            this.zzag = metadata;
            this.zzah = z;
        }

        public boolean detectorIsOperational() {
            return this.zzah;
        }

        public SparseArray<T> getDetectedItems() {
            return this.zzaf;
        }

        public Metadata getFrameMetadata() {
            return this.zzag;
        }
    }

    public interface Processor<T> {
        void receiveDetections(Detections<T> detections);

        void release();
    }

    public abstract SparseArray<T> detect(Frame frame);

    public boolean isOperational() {
        return true;
    }

    public void receiveFrame(Frame frame) {
        Metadata metadata = new Metadata(frame.getMetadata());
        metadata.zzd();
        Detections detections = new Detections(detect(frame), metadata, isOperational());
        synchronized (this.zzad) {
            if (this.zzae == null) {
                throw new IllegalStateException("Detector processor must first be set with setProcessor in order to receive detection results.");
            }
            this.zzae.receiveDetections(detections);
        }
    }

    public void release() {
        synchronized (this.zzad) {
            if (this.zzae != null) {
                this.zzae.release();
                this.zzae = null;
            }
        }
    }

    public boolean setFocus(int i) {
        return true;
    }

    public void setProcessor(Processor<T> processor) {
        synchronized (this.zzad) {
            if (this.zzae != null) {
                this.zzae.release();
            }
            this.zzae = processor;
        }
    }
}
