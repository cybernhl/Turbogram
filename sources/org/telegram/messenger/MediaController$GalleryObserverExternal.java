package org.telegram.messenger;

import android.database.ContentObserver;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;

class MediaController$GalleryObserverExternal extends ContentObserver {
    final /* synthetic */ MediaController this$0;

    /* renamed from: org.telegram.messenger.MediaController$GalleryObserverExternal$1 */
    class C08511 implements Runnable {
        C08511() {
        }

        public void run() {
            MediaController.access$1702(null);
            MediaController.loadGalleryPhotosAlbums(0);
        }
    }

    public MediaController$GalleryObserverExternal(MediaController mediaController) {
        this.this$0 = mediaController;
        super(null);
    }

    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        if (MediaController.access$1700() != null) {
            AndroidUtilities.cancelRunOnUIThread(MediaController.access$1700());
        }
        AndroidUtilities.runOnUIThread(MediaController.access$1702(new C08511()), AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
    }
}
