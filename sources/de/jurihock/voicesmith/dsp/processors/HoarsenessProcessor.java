package de.jurihock.voicesmith.dsp.processors;

import de.jurihock.voicesmith.dsp.Math;

public final class HoarsenessProcessor {
    public static void processFrame(float[] frame) {
        int fftSize = frame.length / 2;
        for (int i = 1; i < fftSize; i++) {
            float abs = Math.abs(frame[i * 2], frame[(i * 2) + 1]);
            float phase = Math.random(-3.1415927f, 3.1415927f);
            frame[i * 2] = Math.real(abs, phase);
            frame[(i * 2) + 1] = Math.imag(abs, phase);
        }
    }
}
