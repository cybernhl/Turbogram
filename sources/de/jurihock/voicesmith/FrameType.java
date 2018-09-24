package de.jurihock.voicesmith;

public enum FrameType {
    Large(2.0d),
    Default(1.0d),
    Medium(0.5d),
    Small(0.25d);
    
    public final double ratio;

    private FrameType(double ratio) {
        this.ratio = ratio;
    }
}
