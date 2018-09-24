package de.jurihock.voicesmith.dsp;

public final class Window {
    private final int frameSize;
    private final int hopSize;
    private final boolean isPeriodic;
    private final boolean isWeighted;

    public Window(int frameSize, boolean isPeriodic) {
        this(frameSize, frameSize, isPeriodic, false);
    }

    public Window(int frameSize, int hopSize, boolean isPeriodic, boolean isWeighted) {
        this.frameSize = frameSize;
        this.hopSize = hopSize;
        this.isPeriodic = isPeriodic;
        this.isWeighted = isWeighted;
    }

    private void weight(float[] window) {
        int n;
        float weighting = 0.0f;
        for (n = 0; n < this.frameSize; n++) {
            weighting += window[n] * window[n];
        }
        weighting = 1.0f / Math.sqrt(weighting / ((float) this.hopSize));
        for (n = 0; n < this.frameSize; n++) {
            window[n] = window[n] * weighting;
        }
    }

    public float[] hann() {
        float[] window = new float[this.frameSize];
        int N = this.isPeriodic ? this.frameSize + 1 : this.frameSize;
        for (int n = 0; n < this.frameSize; n++) {
            window[n] = 0.5f * (1.0f - Math.cos((6.2831855f * ((float) n)) / (((float) N) - 1.0f)));
        }
        if (this.isWeighted) {
            weight(window);
        }
        return window;
    }
}
