package de.jurihock.voicesmith.dsp.processors;

public final class NativeTimescaleProcessor {
    private final long handle;

    private native long alloc(int i, int i2, int i3);

    private native void processFrame(long j, float[] fArr);

    public NativeTimescaleProcessor(int frameSize, int analysisHopSize, int synthesisHopSize) {
        this.handle = alloc(frameSize, analysisHopSize, synthesisHopSize);
    }

    public void processFrame(float[] frame) {
        processFrame(this.handle, frame);
    }
}
