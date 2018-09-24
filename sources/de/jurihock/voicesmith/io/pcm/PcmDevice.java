package de.jurihock.voicesmith.io.pcm;

import android.content.Context;
import de.jurihock.voicesmith.io.AudioDevice;

public abstract class PcmDevice extends AudioDevice {
    private int audioSource;
    private int bufferSize;
    private int channels;
    private int encoding;
    private int minBufferSize;

    public PcmDevice(Context context) {
        super(context);
    }

    public int getAudioSource() {
        return this.audioSource;
    }

    protected void setAudioSource(int audioSource) {
        this.audioSource = audioSource;
    }

    public int getChannels() {
        return this.channels;
    }

    protected void setChannels(int channels) {
        this.channels = channels;
    }

    public int getEncoding() {
        return this.encoding;
    }

    protected void setEncoding(int encoding) {
        this.encoding = encoding;
    }

    public int getMinBufferSize() {
        return this.minBufferSize;
    }

    protected void setMinBufferSize(int minBufferSize) {
        this.minBufferSize = minBufferSize;
    }

    public int getBufferSize() {
        return this.bufferSize;
    }

    protected void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }
}
