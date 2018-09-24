package de.jurihock.voicesmith.dsp.stft;

import de.jurihock.voicesmith.dsp.KissFFT;
import de.jurihock.voicesmith.dsp.Math;
import de.jurihock.voicesmith.dsp.Window;
import de.jurihock.voicesmith.io.AudioDevice;
import java.util.Arrays;

public final class StftPostprocessor {
    private final boolean doInverseFFT;
    private KissFFT fft = null;
    private int frameCursor;
    private final int frameSize;
    private final int hopSize;
    private final short[] nextFrame;
    private final AudioDevice output;
    private final short[] prevFrame;
    private final float[] window;

    public StftPostprocessor(AudioDevice output, int frameSize, int hopSize, boolean doInverseFFT) {
        this.output = output;
        this.frameSize = frameSize;
        this.hopSize = hopSize;
        this.doInverseFFT = doInverseFFT;
        this.fft = new KissFFT(frameSize);
        this.window = new Window(frameSize, true).hann();
        this.prevFrame = new short[frameSize];
        this.nextFrame = new short[frameSize];
        this.frameCursor = 0;
    }

    public short[] processFrame(float[] frame) {
        if (this.doInverseFFT) {
            this.fft.ifft(frame);
        }
        synthesizeFrame(frame, 0, this.prevFrame, this.frameCursor, this.frameSize - this.frameCursor, this.window);
        synthesizeFrame(frame, this.frameSize - this.frameCursor, this.nextFrame, 0, this.frameCursor, this.window);
        this.frameCursor += this.hopSize;
        short[] result = new short[this.prevFrame.length];
        if (this.frameCursor < this.frameSize) {
            return null;
        }
        this.frameCursor -= this.frameSize;
        System.arraycopy(this.prevFrame, 0, result, 0, this.prevFrame.length);
        System.arraycopy(this.nextFrame, 0, this.prevFrame, 0, this.frameSize);
        Arrays.fill(this.nextFrame, (short) 0);
        return result;
    }

    private static void synthesizeFrame(float[] src, int offsetSrc, short[] dst, int offsetDst, int count, float[] window) {
        if (count != 0) {
            for (int i = 0; i < count; i++) {
                int i2 = i + offsetDst;
                dst[i2] = (short) (dst[i2] + ((short) Math.round(32767.0f * Math.min(1.0f, Math.max(-1.0f, src[i + offsetSrc] * window[i + offsetSrc])))));
            }
        }
    }
}
