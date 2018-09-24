package de.jurihock.voicesmith;

public enum AAF {
    FAF,
    DAF,
    UnprocessedFeedback;
    
    private static final AAF[] aafValues = null;

    static {
        aafValues = values();
    }

    public static int count() {
        return aafValues.length;
    }

    public static AAF valueOf(int aafIndex) {
        return aafValues[aafIndex];
    }
}
