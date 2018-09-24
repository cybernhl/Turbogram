package com.google.android.exoplayer2.offline;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.source.TrackGroupArray;
import java.io.IOException;
import java.util.List;

public abstract class DownloadHelper {

    public interface Callback {
        void onPrepareError(DownloadHelper downloadHelper, IOException iOException);

        void onPrepared(DownloadHelper downloadHelper);
    }

    public abstract DownloadAction getDownloadAction(@Nullable byte[] bArr, List<TrackKey> list);

    public abstract int getPeriodCount();

    public abstract DownloadAction getRemoveAction(@Nullable byte[] bArr);

    public abstract TrackGroupArray getTrackGroups(int i);

    protected abstract void prepareInternal() throws IOException;

    public void prepare(final Callback callback) {
        final Handler handler = new Handler(Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper());
        new Thread() {

            /* renamed from: com.google.android.exoplayer2.offline.DownloadHelper$1$1 */
            class C03041 implements Runnable {
                C03041() {
                }

                public void run() {
                    callback.onPrepared(DownloadHelper.this);
                }
            }

            public void run() {
                try {
                    DownloadHelper.this.prepareInternal();
                    handler.post(new C03041());
                } catch (final IOException e) {
                    handler.post(new Runnable() {
                        public void run() {
                            callback.onPrepareError(DownloadHelper.this, e);
                        }
                    });
                }
            }
        }.start();
    }
}
