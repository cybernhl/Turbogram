package de.jurihock.voicesmith.dsp;

public final class SchmittTrigger {
    private final float highThreshold;
    private final float lowThreshold;
    private boolean state;
    private float value;

    public SchmittTrigger(boolean state, float value, float lowThreshold, float highThreshold) {
        this.state = state;
        this.value = value;
        this.lowThreshold = lowThreshold;
        this.highThreshold = highThreshold;
    }

    public boolean state(float newValue) {
        if (newValue > this.value && newValue > this.highThreshold) {
            this.state = true;
        } else if (newValue < this.value && newValue < this.lowThreshold) {
            this.state = false;
        }
        this.value = newValue;
        return this.state;
    }
}
