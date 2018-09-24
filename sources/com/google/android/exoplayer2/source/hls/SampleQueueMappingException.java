package com.google.android.exoplayer2.source.hls;

import android.support.annotation.Nullable;
import java.io.IOException;

public final class SampleQueueMappingException extends IOException {
    public SampleQueueMappingException(@Nullable String mimeType) {
        super("Unable to bind a sample queue to TrackGroup with mime type " + mimeType + ".");
    }
}
