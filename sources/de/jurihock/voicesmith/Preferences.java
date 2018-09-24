package de.jurihock.voicesmith;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.support.media.ExifInterface;
import turbogram.Utilities.TurboConfig;

public final class Preferences {
    private final SharedPreferences preferences = TurboConfig.getTurboConfigPreferences();

    public Preferences(Context context) {
    }

    public int getSignalAmplificationFactor() {
        return Integer.parseInt(this.preferences.getString("SignalAmplification", "6"));
    }

    public int getSampleRate() {
        return Integer.parseInt(this.preferences.getString("SampleRate", "44100"));
    }

    public boolean isCorrectOffsetOn() {
        return this.preferences.getBoolean("CorrectOffset", true);
    }

    public boolean isSpectralNoiseGateOn() {
        return this.preferences.getBoolean("SpectralNoiseGate", true);
    }

    public int getNoiseGateCoeffExponent() {
        return Integer.parseInt(this.preferences.getString("NoiseGateCoeffExponent", ExifInterface.GPS_MEASUREMENT_3D));
    }

    public boolean isBandpassFilterOn() {
        return this.preferences.getBoolean("BandpassFilter", false);
    }

    public int getBandpassLowerFreq() {
        return Integer.parseInt(this.preferences.getString("BandpassLowerFreq", "100"));
    }

    public int getBandpassUpperFreq() {
        return Integer.parseInt(this.preferences.getString("BandpassUpperFreq", "8000"));
    }

    public boolean isAutoMuteOn() {
        return this.preferences.getBoolean("AutoMute", false);
    }

    public int getAutoMuteHighThreshold() {
        return Math.min(0, Math.max(Integer.parseInt(this.preferences.getString("AutoMuteHighThreshold", "-20")), Integer.parseInt(this.preferences.getString("AutoMuteLowThreshold", "-25"))));
    }

    public int getAutoMuteLowThreshold() {
        return Math.min(0, Math.min(Integer.parseInt(this.preferences.getString("AutoMuteHighThreshold", "-20")), Integer.parseInt(this.preferences.getString("AutoMuteLowThreshold", "-25"))));
    }

    public int getAutoMuteHangover() {
        return Integer.parseInt(this.preferences.getString("AutoMuteHangover", "5"));
    }

    public boolean isLoggingOn() {
        return this.preferences.getBoolean("Logging", false);
    }

    public int getPcmBufferSize(int sampleRate) {
        return Math.max(AudioRecord.getMinBufferSize(sampleRate, 16, 2), AudioTrack.getMinBufferSize(sampleRate, 4, 2));
    }

    public int getFrameSize(FrameType frameType, int sampleRate) {
        int frameSize = (int) ((((double) sampleRate) * 0.046439909297052155d) * frameType.ratio);
        if (frameSize % 2 != 0) {
            return frameSize + 1;
        }
        return frameSize;
    }

    public int getHopSize(FrameType frameType, int sampleRate) {
        return getFrameSize(frameType, sampleRate) / 4;
    }
}
