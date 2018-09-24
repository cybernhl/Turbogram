package de.jurihock.voicesmith.dsp.processors;

import android.content.Context;
import de.jurihock.voicesmith.Preferences;
import de.jurihock.voicesmith.Utils;
import de.jurihock.voicesmith.dsp.LuenbergerObserver;
import de.jurihock.voicesmith.dsp.Math;
import de.jurihock.voicesmith.dsp.SchmittTrigger;

public final class VadProcessor {
    private final LuenbergerObserver energyObserver;
    private final float[] energyObserverGain;
    private float hangoverDuration;
    private final float hangoverMaxDuration;
    private final boolean isEnabled;
    private boolean lastState;
    private final int sampleRate;
    private final SchmittTrigger trigger;
    private Utils utils;
    private final int windowSize;
    private final float windowTimeInterval;

    public VadProcessor(int sampleRate, Context context) {
        this(sampleRate, new Preferences(context).getAutoMuteHighThreshold(), new Preferences(context).getAutoMuteLowThreshold(), new Preferences(context).getAutoMuteHangover(), new Preferences(context).isAutoMuteOn());
        if (new Preferences(context).isLoggingOn()) {
            this.utils = new Utils(context);
        }
    }

    public VadProcessor(int sampleRate, int lowThreshold, int highThreshold, int hangover, boolean enable) {
        this.windowTimeInterval = 0.02f;
        this.energyObserverGain = new float[]{0.3f, 0.02f};
        this.utils = null;
        this.lastState = false;
        this.sampleRate = sampleRate;
        this.windowSize = Math.round(((float) sampleRate) * 0.02f);
        float initialDbfs = ((float) (lowThreshold + highThreshold)) / 2.0f;
        this.energyObserver = new LuenbergerObserver(initialDbfs, 0.0f, this.energyObserverGain);
        this.trigger = new SchmittTrigger(false, initialDbfs, (float) lowThreshold, (float) highThreshold);
        this.hangoverMaxDuration = (float) hangover;
        this.hangoverDuration = 0.0f;
        this.isEnabled = enable;
    }

    public void processFrame(short[] frame) {
        if (this.isEnabled) {
            int windowCount = frame.length / this.windowSize;
            int adaptedWindowSize = windowCount > 0 ? (int) Math.ceil(((float) frame.length) / ((float) windowCount)) : frame.length;
            float windowDuration = ((float) adaptedWindowSize) / ((float) this.sampleRate);
            for (int i = 0; i < windowCount; i++) {
                processFrameInternal(frame, i * adaptedWindowSize, adaptedWindowSize, windowDuration);
            }
        }
    }

    private void processFrameInternal(short[] frame, int offset, int length, float windowDuration) {
        boolean currentState = this.trigger.state(this.energyObserver.smooth(Math.rms2dbfs(Math.rms(frame, offset, length), 1.0E-10f, 1.0f)));
        if (this.hangoverMaxDuration > 0.0f) {
            if (currentState) {
                this.hangoverDuration = 0.0f;
            } else {
                this.hangoverDuration = Math.min(this.hangoverMaxDuration, this.hangoverDuration + windowDuration);
                currentState = this.hangoverDuration < this.hangoverMaxDuration;
            }
        }
        if (!currentState) {
            for (int i = offset; i < offset + length; i++) {
                frame[i] = (short) 0;
            }
        }
        if (this.utils != null && this.lastState != currentState) {
            if (currentState) {
                this.utils.log("Voice activity detected.");
            } else {
                this.utils.log("Voice inactivity detected.");
            }
            this.lastState = currentState;
        }
    }
}
