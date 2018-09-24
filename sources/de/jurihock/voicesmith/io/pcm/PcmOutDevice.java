package de.jurihock.voicesmith.io.pcm;

import android.content.Context;
import android.media.AudioTrack;
import de.jurihock.voicesmith.HeadsetMode;
import de.jurihock.voicesmith.Preferences;
import java.io.IOException;

public final class PcmOutDevice extends PcmDevice {
    private static final int BLUETOOTH_HEADSET_SOURCE = 0;
    private static final int WIRED_HEADPHONES_SOURCE = 3;
    private static final int WIRED_HEADSET_SOURCE = 3;
    private AudioTrack output = null;

    private final class WrappedAudioTrack extends AudioTrack {
        public WrappedAudioTrack(int streamType, int sampleRateInHz, int channelConfig, int audioFormat, int bufferSizeInBytes, int mode) throws IllegalArgumentException {
            super(streamType, sampleRateInHz, channelConfig, audioFormat, bufferSizeInBytes, mode);
        }
    }

    public PcmOutDevice(Context context, HeadsetMode headsetMode) throws IOException {
        super(context);
        switch (headsetMode) {
            case WIRED_HEADPHONES:
                setAudioSource(3);
                break;
            case WIRED_HEADSET:
                setAudioSource(3);
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
        setChannels(4);
        setEncoding(2);
        setMinBufferSize(AudioTrack.getMinBufferSize(getSampleRate(), getChannels(), getEncoding()));
        if (getMinBufferSize() == -2 || getMinBufferSize() == -1) {
            throw new IOException("Unable to determine the MinBufferSize for AudioTrack!");
        }
        setBufferSize(new Preferences(context).getPcmBufferSize(getSampleRate()));
        this.output = new WrappedAudioTrack(getAudioSource(), getSampleRate(), getChannels(), getEncoding(), getBufferSize(), 1);
        if (this.output.getState() != 1) {
            dispose();
            throw new IOException("Unable to initialize an AudioTrack instance!");
        }
    }

    public void dispose() {
        if (this.output != null) {
            this.output.release();
            this.output = null;
        }
    }
}
