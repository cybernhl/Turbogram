package de.jurihock.voicesmith.dsp.processors;

import android.content.Context;
import de.jurihock.voicesmith.Preferences;
import de.jurihock.voicesmith.dsp.Math;

public final class DenoiseProcessor {
    private final float bpLowerFreq;
    private final float bpUpperFreq;
    private final boolean isBandpassFilterOn;
    private final boolean isSpectralNoiseGateOn;
    private final float ngCoeff;
    private final int sampleRate;

    public DenoiseProcessor(int sampleRate, Context context) {
        this.sampleRate = sampleRate;
        Preferences preferences = new Preferences(context);
        this.isSpectralNoiseGateOn = preferences.isSpectralNoiseGateOn();
        this.isBandpassFilterOn = preferences.isBandpassFilterOn();
        this.ngCoeff = Math.pow(10.0f, (float) (-preferences.getNoiseGateCoeffExponent()));
        this.bpLowerFreq = (((float) preferences.getBandpassLowerFreq()) * 2.0f) / ((float) sampleRate);
        this.bpUpperFreq = (((float) preferences.getBandpassUpperFreq()) * 2.0f) / ((float) sampleRate);
    }

    public void processFrame(float[] frame) {
        if (this.isSpectralNoiseGateOn || this.isBandpassFilterOn) {
            int fftSize = frame.length / 2;
            float coeff = this.ngCoeff;
            int start = (int) (((float) fftSize) * this.bpLowerFreq);
            int end = (int) (((float) fftSize) * this.bpUpperFreq);
            boolean isIndexInBand = true;
            int i = 1;
            while (i < fftSize) {
                float re = frame[i * 2];
                float im = frame[(i * 2) + 1];
                float abs = Math.abs(re, im);
                float r = 1.0f;
                if (this.isBandpassFilterOn) {
                    isIndexInBand = i >= start && i <= end;
                    if (!isIndexInBand) {
                        r = 0.0f;
                    }
                }
                if (this.isSpectralNoiseGateOn && isIndexInBand) {
                    r = noiseGate(abs / ((float) fftSize), coeff);
                }
                frame[i * 2] = re * r;
                frame[(i * 2) + 1] = im * r;
                i++;
            }
        }
    }

    private static float noiseGate(float value, float coeff) {
        return value / (value + coeff);
    }
}
