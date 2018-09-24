package com.google.android.exoplayer2.mediacodec;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil.DecoderQueryException;
import java.util.Collections;
import java.util.List;

public interface MediaCodecSelector {
    public static final MediaCodecSelector DEFAULT = new C02821();
    public static final MediaCodecSelector DEFAULT_WITH_FALLBACK = new C02832();

    /* renamed from: com.google.android.exoplayer2.mediacodec.MediaCodecSelector$1 */
    static class C02821 implements MediaCodecSelector {
        C02821() {
        }

        public List<MediaCodecInfo> getDecoderInfos(Format format, boolean requiresSecureDecoder) throws DecoderQueryException {
            List<MediaCodecInfo> decoderInfos = MediaCodecUtil.getDecoderInfos(format.sampleMimeType, requiresSecureDecoder);
            if (decoderInfos.isEmpty()) {
                return Collections.emptyList();
            }
            return Collections.singletonList(decoderInfos.get(0));
        }

        @Nullable
        public MediaCodecInfo getPassthroughDecoderInfo() throws DecoderQueryException {
            return MediaCodecUtil.getPassthroughDecoderInfo();
        }
    }

    /* renamed from: com.google.android.exoplayer2.mediacodec.MediaCodecSelector$2 */
    static class C02832 implements MediaCodecSelector {
        C02832() {
        }

        public List<MediaCodecInfo> getDecoderInfos(Format format, boolean requiresSecureDecoder) throws DecoderQueryException {
            return MediaCodecUtil.getDecoderInfos(format.sampleMimeType, requiresSecureDecoder);
        }

        @Nullable
        public MediaCodecInfo getPassthroughDecoderInfo() throws DecoderQueryException {
            return MediaCodecUtil.getPassthroughDecoderInfo();
        }
    }

    List<MediaCodecInfo> getDecoderInfos(Format format, boolean z) throws DecoderQueryException;

    @Nullable
    MediaCodecInfo getPassthroughDecoderInfo() throws DecoderQueryException;
}
