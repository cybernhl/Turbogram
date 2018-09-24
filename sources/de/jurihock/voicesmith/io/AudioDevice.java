package de.jurihock.voicesmith.io;

import android.content.Context;
import de.jurihock.voicesmith.Preferences;

public abstract class AudioDevice {
    protected final Context context;
    private int sampleRate;

    public AudioDevice(Context context) {
        this(context, new Preferences(context).getSampleRate());
    }

    public AudioDevice(Context context, int sampleRate) {
        this.context = context;
        this.sampleRate = sampleRate;
    }

    public Context getContext() {
        return this.context;
    }

    public int getSampleRate() {
        return this.sampleRate;
    }

    protected void setSampleRate(int sampleRate) {
        this.sampleRate = sampleRate;
    }

    public void dispose() {
    }
}
