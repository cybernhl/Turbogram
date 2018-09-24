package com.google.android.exoplayer2.upstream;

import com.google.android.exoplayer2.C0246C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import com.google.android.exoplayer2.upstream.HttpDataSource.InvalidResponseCodeException;
import com.google.android.exoplayer2.upstream.Loader.Loadable;
import com.google.android.gms.wallet.WalletConstants;
import java.io.IOException;

public interface LoadErrorHandlingPolicy<T extends Loadable> {
    public static final LoadErrorHandlingPolicy<Loadable> DEFAULT = new C03521();
    public static final int DEFAULT_MIN_LOADABLE_RETRY_COUNT = 3;

    /* renamed from: com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy$1 */
    static class C03521 implements LoadErrorHandlingPolicy<Loadable> {
        C03521() {
        }

        public long getBlacklistDurationMsFor(Loadable loadable, long loadDurationMs, IOException exception, int errorCount) {
            if (!(exception instanceof InvalidResponseCodeException)) {
                return C0246C.TIME_UNSET;
            }
            int responseCode = ((InvalidResponseCodeException) exception).responseCode;
            if (responseCode == WalletConstants.ERROR_CODE_INVALID_PARAMETERS || responseCode == WalletConstants.ERROR_CODE_INVALID_TRANSACTION) {
                return ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS;
            }
            return C0246C.TIME_UNSET;
        }

        public long getRetryDelayMsFor(Loadable loadable, long loadDurationMs, IOException exception, int errorCount) {
            if (exception instanceof ParserException) {
                return C0246C.TIME_UNSET;
            }
            return (long) Math.min((errorCount - 1) * 1000, DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS);
        }

        public int getMinimumLoadableRetryCount(Loadable loadable) {
            return 3;
        }
    }

    long getBlacklistDurationMsFor(T t, long j, IOException iOException, int i);

    int getMinimumLoadableRetryCount(T t);

    long getRetryDelayMsFor(T t, long j, IOException iOException, int i);
}
