package de.jurihock.voicesmith.dsp.stft;

import de.jurihock.voicesmith.DataHelper;
import de.jurihock.voicesmith.dsp.KissFFT;
import de.jurihock.voicesmith.dsp.Math;
import de.jurihock.voicesmith.dsp.Window;
import de.jurihock.voicesmith.dsp.processors.AmplifyProcessor;
import de.jurihock.voicesmith.dsp.processors.DenoiseProcessor;
import de.jurihock.voicesmith.dsp.processors.OffsetProcessor;
import de.jurihock.voicesmith.dsp.processors.VadProcessor;
import de.jurihock.voicesmith.io.AudioDevice;

public final class StftPreprocessor {
    private final AmplifyProcessor amplifier;
    private final DenoiseProcessor denoiser;
    private final OffsetProcessor deoffset;
    private final boolean doForwardFFT;
    private KissFFT fft = null;
    private int frameCursor;
    private final int frameSize;
    private final int hopSize;
    private final AudioDevice input;
    private final short[] nextFrame;
    private final short[] prevFrame;
    private final VadProcessor vad;
    private final float[] window;

    public StftPreprocessor(AudioDevice input, int frameSize, int hopSize, boolean doForwardFFT) {
        this.input = input;
        this.frameSize = frameSize;
        this.hopSize = hopSize;
        this.doForwardFFT = doForwardFFT;
        this.deoffset = new OffsetProcessor(input.getContext());
        this.vad = new VadProcessor(input.getSampleRate(), input.getContext());
        this.amplifier = new AmplifyProcessor(input.getContext());
        this.denoiser = new DenoiseProcessor(input.getSampleRate(), input.getContext());
        this.fft = new KissFFT(frameSize);
        this.window = new Window(frameSize, true).hann();
        this.prevFrame = new short[frameSize];
        this.nextFrame = new short[frameSize];
        this.frameCursor = -1;
    }

    public void processFrame(float[] frame, DataHelper dataHelper) {
        if (this.frameCursor == -1) {
            this.frameCursor = this.frameSize;
            dataHelper.m595a(this.nextFrame);
            this.deoffset.processFrame(this.nextFrame);
            this.vad.processFrame(this.nextFrame);
            this.amplifier.processFrame(this.nextFrame);
        } else if (this.frameCursor >= this.frameSize) {
            this.frameCursor -= this.frameSize;
            System.arraycopy(this.nextFrame, 0, this.prevFrame, 0, this.frameSize);
            dataHelper.m595a(this.nextFrame);
            this.deoffset.processFrame(this.nextFrame);
            this.vad.processFrame(this.nextFrame);
            this.amplifier.processFrame(this.nextFrame);
        }
        analyzeFrame(this.prevFrame, this.frameCursor, frame, 0, this.frameSize - this.frameCursor, this.window);
        analyzeFrame(this.nextFrame, 0, frame, this.frameSize - this.frameCursor, this.frameCursor, this.window);
        if (this.doForwardFFT) {
            this.fft.fft(frame);
            this.denoiser.processFrame(frame);
        }
        this.frameCursor += this.hopSize;
    }

    private static void analyzeFrame(short[] src, int offsetSrc, float[] dst, int offsetDst, int count, float[] window) {
        if (count != 0) {
            for (int i = 0; i < count; i++) {
                dst[i + offsetDst] = Math.min(1.0f, Math.max(-1.0f, ((float) src[i + offsetSrc]) / 32767.0f)) * window[i + offsetDst];
            }
        }
    }
}
