package de.jurihock.voicesmith.dsp;

public final class LuenbergerObserver {
    private final float[] gain;
    private float value;
    private float velocity;

    public LuenbergerObserver(float value, float velocity, float[] gain) {
        this.value = value;
        this.velocity = velocity;
        this.gain = gain;
    }

    private float predict() {
        return this.value + this.velocity;
    }

    private float correct(float newValue) {
        float error = newValue - this.value;
        this.value = (this.gain[0] * error) + predict();
        this.velocity += this.gain[1] * error;
        return this.value;
    }

    public float smooth(float newValue) {
        correct(newValue);
        return predict();
    }
}
