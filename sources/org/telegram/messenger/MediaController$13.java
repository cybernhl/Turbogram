package org.telegram.messenger;

import android.graphics.SurfaceTexture;
import org.telegram.ui.Components.PipRoundVideoView;
import org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate;

class MediaController$13 implements VideoPlayerDelegate {
    final /* synthetic */ MediaController this$0;

    /* renamed from: org.telegram.messenger.MediaController$13$1 */
    class C08351 implements Runnable {
        C08351() {
        }

        public void run() {
            MediaController$13.this.this$0.cleanupPlayer(true, true);
        }
    }

    MediaController$13(MediaController this$0) {
        this.this$0 = this$0;
    }

    public void onStateChanged(boolean playWhenReady, int playbackState) {
        if (MediaController.access$3500(this.this$0) != null) {
            if (playbackState == 4 || playbackState == 1) {
                try {
                    MediaController.access$4300(this.this$0).getWindow().clearFlags(128);
                } catch (Exception e) {
                    FileLog.e(e);
                }
            } else {
                try {
                    MediaController.access$4300(this.this$0).getWindow().addFlags(128);
                } catch (Exception e2) {
                    FileLog.e(e2);
                }
            }
            if (playbackState == 3) {
                MediaController.access$4402(this.this$0, true);
                if (MediaController.access$4500(this.this$0) != null && MediaController.access$4500(this.this$0).getVisibility() != 0) {
                    MediaController.access$4500(this.this$0).setVisibility(0);
                }
            } else if (MediaController.access$3500(this.this$0).isPlaying() && playbackState == 4) {
                this.this$0.cleanupPlayer(true, true, true);
            }
        }
    }

    public void onError(Exception e) {
        FileLog.e(e);
    }

    public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {
        MediaController.access$4602(this.this$0, unappliedRotationDegrees);
        if (unappliedRotationDegrees == 90 || unappliedRotationDegrees == 270) {
            int temp = width;
            width = height;
            height = temp;
        }
        MediaController.access$4702(this.this$0, height == 0 ? 1.0f : (((float) width) * pixelWidthHeightRatio) / ((float) height));
        if (MediaController.access$4800(this.this$0) != null) {
            MediaController.access$4800(this.this$0).setAspectRatio(MediaController.access$4700(this.this$0), MediaController.access$4600(this.this$0));
        }
    }

    public void onRenderedFirstFrame() {
        if (MediaController.access$4800(this.this$0) != null && !MediaController.access$4800(this.this$0).isDrawingReady()) {
            MediaController.access$4902(this.this$0, true);
            MediaController.access$4800(this.this$0).setDrawingReady(true);
            if (MediaController.access$4500(this.this$0) != null && MediaController.access$4500(this.this$0).getVisibility() != 0) {
                MediaController.access$4500(this.this$0).setVisibility(0);
            }
        }
    }

    public boolean onSurfaceDestroyed(SurfaceTexture surfaceTexture) {
        if (MediaController.access$3500(this.this$0) == null) {
            return false;
        }
        if (MediaController.access$5000(this.this$0) == 2) {
            if (MediaController.access$4800(this.this$0) != null) {
                if (MediaController.access$4900(this.this$0)) {
                    MediaController.access$4800(this.this$0).setDrawingReady(true);
                }
                if (MediaController.access$4800(this.this$0).getParent() == null) {
                    MediaController.access$4500(this.this$0).addView(MediaController.access$4800(this.this$0));
                }
                if (MediaController.access$5100(this.this$0).getSurfaceTexture() != surfaceTexture) {
                    MediaController.access$5100(this.this$0).setSurfaceTexture(surfaceTexture);
                }
                MediaController.access$3500(this.this$0).setTextureView(MediaController.access$5100(this.this$0));
            }
            MediaController.access$5002(this.this$0, 0);
            return true;
        } else if (MediaController.access$5000(this.this$0) != 1) {
            return false;
        } else {
            if (MediaController.access$4300(this.this$0) != null) {
                if (MediaController.access$5200(this.this$0) == null) {
                    try {
                        MediaController.access$5202(this.this$0, new PipRoundVideoView());
                        MediaController.access$5200(this.this$0).show(MediaController.access$4300(this.this$0), new C08351());
                    } catch (Exception e) {
                        MediaController.access$5202(this.this$0, null);
                    }
                }
                if (MediaController.access$5200(this.this$0) != null) {
                    if (MediaController.access$5200(this.this$0).getTextureView().getSurfaceTexture() != surfaceTexture) {
                        MediaController.access$5200(this.this$0).getTextureView().setSurfaceTexture(surfaceTexture);
                    }
                    MediaController.access$3500(this.this$0).setTextureView(MediaController.access$5200(this.this$0).getTextureView());
                }
            }
            MediaController.access$5002(this.this$0, 0);
            return true;
        }
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }
}
