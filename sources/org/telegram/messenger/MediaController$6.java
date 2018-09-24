package org.telegram.messenger;

import com.google.android.exoplayer2.C0246C;
import java.util.TimerTask;

class MediaController$6 extends TimerTask {
    final /* synthetic */ MediaController this$0;
    final /* synthetic */ MessageObject val$currentPlayingMessageObject;

    /* renamed from: org.telegram.messenger.MediaController$6$1 */
    class C08501 implements Runnable {
        C08501() {
        }

        public void run() {
            if (MediaController$6.this.val$currentPlayingMessageObject == null) {
                return;
            }
            if ((MediaController.access$3400(MediaController$6.this.this$0) != null || MediaController.access$3500(MediaController$6.this.this$0) != null) && !MediaController.access$3600(MediaController$6.this.this$0)) {
                try {
                    long duration;
                    long progress;
                    float bufferedValue;
                    float value;
                    if (MediaController.access$3500(MediaController$6.this.this$0) != null) {
                        duration = MediaController.access$3500(MediaController$6.this.this$0).getDuration();
                        progress = MediaController.access$3500(MediaController$6.this.this$0).getCurrentPosition();
                        bufferedValue = ((float) MediaController.access$3500(MediaController$6.this.this$0).getBufferedPosition()) / ((float) duration);
                        if (duration >= 0) {
                            value = ((float) progress) / ((float) duration);
                        } else {
                            value = 0.0f;
                        }
                        if (progress < 0 || value >= 1.0f) {
                            return;
                        }
                    }
                    duration = MediaController.access$3400(MediaController$6.this.this$0).getDuration();
                    progress = MediaController.access$3400(MediaController$6.this.this$0).getCurrentPosition();
                    if (duration == C0246C.TIME_UNSET || duration < 0) {
                        value = 0.0f;
                    } else {
                        value = ((float) progress) / ((float) duration);
                    }
                    bufferedValue = ((float) MediaController.access$3400(MediaController$6.this.this$0).getBufferedPosition()) / ((float) duration);
                    if (duration != C0246C.TIME_UNSET && progress >= 0) {
                        if (MediaController.access$3700(MediaController$6.this.this$0) != 0.0f) {
                            return;
                        }
                    }
                    return;
                    MediaController.access$3802(MediaController$6.this.this$0, progress);
                    MediaController$6.this.val$currentPlayingMessageObject.audioPlayerDuration = (int) (duration / 1000);
                    MediaController$6.this.val$currentPlayingMessageObject.audioProgress = value;
                    MediaController$6.this.val$currentPlayingMessageObject.audioProgressSec = (int) (MediaController.access$3800(MediaController$6.this.this$0) / 1000);
                    MediaController$6.this.val$currentPlayingMessageObject.bufferedProgress = bufferedValue;
                    NotificationCenter.getInstance(MediaController$6.this.val$currentPlayingMessageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingProgressDidChanged, Integer.valueOf(MediaController$6.this.val$currentPlayingMessageObject.getId()), Float.valueOf(value));
                } catch (Exception e) {
                    FileLog.e(e);
                }
            }
        }
    }

    MediaController$6(MediaController this$0, MessageObject messageObject) {
        this.this$0 = this$0;
        this.val$currentPlayingMessageObject = messageObject;
    }

    public void run() {
        synchronized (MediaController.access$3300(this.this$0)) {
            AndroidUtilities.runOnUIThread(new C08501());
        }
    }
}
