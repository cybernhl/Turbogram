package org.telegram.messenger;

import android.database.ContentObserver;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import org.telegram.ui.PhotoViewer;

class MediaController$GalleryObserverInternal extends ContentObserver {
    final /* synthetic */ MediaController this$0;

    /* renamed from: org.telegram.messenger.MediaController$GalleryObserverInternal$1 */
    class C08521 implements Runnable {
        C08521() {
        }

        public void run() {
            if (PhotoViewer.getInstance().isVisible()) {
                MediaController$GalleryObserverInternal.this.scheduleReloadRunnable();
                return;
            }
            MediaController.access$1702(null);
            MediaController.loadGalleryPhotosAlbums(0);
        }
    }

    public MediaController$GalleryObserverInternal(MediaController mediaController) {
        this.this$0 = mediaController;
        super(null);
    }

    private void scheduleReloadRunnable() {
        AndroidUtilities.runOnUIThread(MediaController.access$1702(new C08521()), AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
    }

    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        if (MediaController.access$1700() != null) {
            AndroidUtilities.cancelRunOnUIThread(MediaController.access$1700());
        }
        scheduleReloadRunnable();
    }
}
