package de.jurihock.voicesmith.dsp.processors;

public final class DetuneProcessor {
    public static void processFrame(float[] frame) {
        int fftSize = frame.length / 2;
        for (int i = 1; i < fftSize; i++) {
            float im = -frame[(i * 2) + 1];
            frame[i * 2] = frame[i * 2];
            frame[(i * 2) + 1] = im;
        }
    }
}
