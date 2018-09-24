package de.jurihock.voicesmith.io.pcm;

import android.content.Context;
import android.media.AudioRecord;
import de.jurihock.voicesmith.HeadsetMode;
import de.jurihock.voicesmith.Preferences;
import java.io.IOException;

public class PcmInDevice extends PcmDevice {
    private static final int BLUETOOTH_HEADSET_SOURCE = 0;
    private static final int WIRED_HEADPHONES_SOURCE = 5;
    private static final int WIRED_HEADSET_SOURCE = 0;
    private AudioRecord input = null;

    public PcmInDevice(Context context, HeadsetMode headsetMode) throws IOException {
        super(context);
        switch (headsetMode) {
            case WIRED_HEADPHONES:
                setAudioSource(5);
                break;
            case WIRED_HEADSET:
                setAudioSource(0);
                break;
            case BLUETOOTH_HEADSET:
                setSampleRate(8000);
                setAudioSource(0);
                break;
            default:
                throw new IOException("Unknown HeadsetMode!");
        }
        init(context);
    }

    private void init(Context context) throws IOException {
        setChannels(16);
        setEncoding(2);
        setMinBufferSize(AudioRecord.getMinBufferSize(getSampleRate(), getChannels(), getEncoding()));
        if (getMinBufferSize() == -2 || getMinBufferSize() == -1) {
            throw new IOException("Unable to determine the MinBufferSize for AudioRecord!");
        }
        setBufferSize(new Preferences(context).getPcmBufferSize(getSampleRate()));
    }

    public void dispose() {
        if (this.input != null) {
            this.input.release();
            this.input = null;
        }
    }
}
