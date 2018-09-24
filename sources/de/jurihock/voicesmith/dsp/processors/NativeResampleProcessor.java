package de.jurihock.voicesmith.dsp.processors;

public class NativeResampleProcessor {
    private final long handle;

    private native long alloc(int i, int i2);

    private native void processFrame(long j, float[] fArr, float[] fArr2);

    public NativeResampleProcessor(int frameSizeIn, int frameSizeOut) {
        this.handle = alloc(frameSizeIn, frameSizeOut);
    }

    public void processFrame(float[] frameIn, float[] frameOut) {
        processFrame(this.handle, frameIn, frameOut);
    }
}
