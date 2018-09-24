package de.jurihock.voicesmith.dsp.processors;

import de.jurihock.voicesmith.dsp.Math;

public final class RobotizeProcessor {
    public static void processFrame(float[] frame) {
        int fftSize = frame.length / 2;
        for (int i = 1; i < fftSize; i++) {
            frame[i * 2] = Math.abs(frame[i * 2], frame[(i * 2) + 1]);
            frame[(i * 2) + 1] = 0.0f;
        }
    }
}
