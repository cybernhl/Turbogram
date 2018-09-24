package org.telegram.messenger;

import android.graphics.SurfaceTexture;
import org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate;

class MediaController$15 implements VideoPlayerDelegate {
    final /* synthetic */ MediaController this$0;
    final /* synthetic */ MessageObject val$messageObject;

    MediaController$15(MediaController this$0, MessageObject messageObject) {
        this.this$0 = this$0;
        this.val$messageObject = messageObject;
    }

    public void onStateChanged(boolean playWhenReady, int playbackState) {
        if (playbackState == 4) {
            if (MediaController.access$5300(this.this$0).isEmpty() || MediaController.access$5300(this.this$0).size() <= 1) {
                MediaController mediaController = this.this$0;
                boolean z = this.val$messageObject != null && this.val$messageObject.isVoice();
                mediaController.cleanupPlayer(true, true, z);
                return;
            }
            MediaController.access$5400(this.this$0, true);
        } else if (MediaController.access$3700(this.this$0) == 0.0f) {
        } else {
            if (playbackState == 3 || playbackState == 1) {
                int seekTo = (int) (((float) MediaController.access$3400(this.this$0).getDuration()) * MediaController.access$3700(this.this$0));
                MediaController.access$3400(this.this$0).seekTo((long) seekTo);
                MediaController.access$3802(this.this$0, (long) seekTo);
                MediaController.access$3702(this.this$0, 0.0f);
            }
        }
    }

    public void onError(Exception e) {
    }

    public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {
    }

    public void onRenderedFirstFrame() {
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public boolean onSurfaceDestroyed(SurfaceTexture surfaceTexture) {
        return false;
    }
}
