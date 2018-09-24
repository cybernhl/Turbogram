package de.jurihock.voicesmith.dsp.processors;

import android.content.Context;
import de.jurihock.voicesmith.Preferences;
import de.jurihock.voicesmith.dsp.Math;

public final class AmplifyProcessor {
    private final float ampFactor;

    public AmplifyProcessor(Context context) {
        this(new Preferences(context).getSignalAmplificationFactor());
    }

    public AmplifyProcessor(int ampDecibel) {
        this.ampFactor = Math.pow(10.0f, ((float) ampDecibel) / 20.0f);
    }

    public void processFrame(short[] frame) {
        if (this.ampFactor != 1.0f) {
            for (int i = 0; i < frame.length; i++) {
                float result = ((float) frame[i]) * this.ampFactor;
                if (result > 32767.0f) {
                    frame[i] = Short.MAX_VALUE;
                } else if (result < -32768.0f) {
                    frame[i] = Short.MIN_VALUE;
                } else {
                    frame[i] = (short) ((int) result);
                }
            }
        }
    }
}
