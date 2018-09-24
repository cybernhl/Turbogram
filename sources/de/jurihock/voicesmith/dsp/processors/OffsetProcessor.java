package de.jurihock.voicesmith.dsp.processors;

import android.content.Context;
import de.jurihock.voicesmith.Preferences;
import de.jurihock.voicesmith.dsp.LuenbergerObserver;
import de.jurihock.voicesmith.dsp.Math;

public final class OffsetProcessor {
    private final boolean isEnabled;
    private final LuenbergerObserver offsetObserver;
    private final float[] offsetObserverGain;

    public OffsetProcessor(Context context) {
        this(new Preferences(context).isCorrectOffsetOn());
    }

    public OffsetProcessor(boolean enable) {
        this.offsetObserverGain = new float[]{0.025f, 0.0f};
        this.isEnabled = enable;
        this.offsetObserver = new LuenbergerObserver(0.0f, 0.0f, this.offsetObserverGain);
    }

    public void processFrame(short[] frame) {
        if (this.isEnabled) {
            short currentOffset = (short) ((int) this.offsetObserver.smooth((float) Math.mean(frame, 0, frame.length)));
            for (int i = 0; i < frame.length; i++) {
                frame[i] = (short) (frame[i] - currentOffset);
            }
        }
    }
}
