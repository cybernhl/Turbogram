package de.jurihock.voicesmith.dsp;

public final class KissFFT {
    private final long handle;

    private native long alloc(int i);

    private native void fft(long j, float[] fArr);

    private native void ifft(long j, float[] fArr);

    public KissFFT(int size) {
        this.handle = alloc(size);
    }

    public void fft(float[] buffer) {
        fft(this.handle, buffer);
    }

    public void ifft(float[] buffer) {
        ifft(this.handle, buffer);
    }
}
