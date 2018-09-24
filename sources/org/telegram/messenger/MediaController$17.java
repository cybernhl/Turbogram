package org.telegram.messenger;

import android.media.AudioRecord;
import java.io.File;
import org.telegram.tgnet.TLRPC$TL_document;
import org.telegram.tgnet.TLRPC$TL_photoSizeEmpty;
import turbogram.Utilities.TurboConfig;

class MediaController$17 implements Runnable {
    final /* synthetic */ MediaController this$0;
    final /* synthetic */ int val$currentAccount;
    final /* synthetic */ long val$dialog_id;
    final /* synthetic */ MessageObject val$reply_to_msg;

    /* renamed from: org.telegram.messenger.MediaController$17$1 */
    class C08361 implements Runnable {
        C08361() {
        }

        public void run() {
            MediaController.access$3002(MediaController$17.this.this$0, null);
            NotificationCenter.getInstance(MediaController$17.this.val$currentAccount).postNotificationName(NotificationCenter.recordStartError, new Object[0]);
        }
    }

    /* renamed from: org.telegram.messenger.MediaController$17$2 */
    class C08372 implements Runnable {
        C08372() {
        }

        public void run() {
            MediaController.access$3002(MediaController$17.this.this$0, null);
            NotificationCenter.getInstance(MediaController$17.this.val$currentAccount).postNotificationName(NotificationCenter.recordStartError, new Object[0]);
        }
    }

    /* renamed from: org.telegram.messenger.MediaController$17$3 */
    class C08383 implements Runnable {
        C08383() {
        }

        public void run() {
            MediaController.access$3002(MediaController$17.this.this$0, null);
            NotificationCenter.getInstance(MediaController$17.this.val$currentAccount).postNotificationName(NotificationCenter.recordStartError, new Object[0]);
        }
    }

    /* renamed from: org.telegram.messenger.MediaController$17$4 */
    class C08394 implements Runnable {
        C08394() {
        }

        public void run() {
            MediaController.access$3002(MediaController$17.this.this$0, null);
            NotificationCenter.getInstance(MediaController$17.this.val$currentAccount).postNotificationName(NotificationCenter.recordStarted, new Object[0]);
        }
    }

    MediaController$17(MediaController this$0, int i, long j, MessageObject messageObject) {
        this.this$0 = this$0;
        this.val$currentAccount = i;
        this.val$dialog_id = j;
        this.val$reply_to_msg = messageObject;
    }

    public void run() {
        if (MediaController.access$000(this.this$0) != null) {
            AndroidUtilities.runOnUIThread(new C08361());
            return;
        }
        MediaController.access$3102(this.this$0, new TLRPC$TL_document());
        MediaController.access$3100(this.this$0).dc_id = Integer.MIN_VALUE;
        MediaController.access$3100(this.this$0).id = (long) SharedConfig.getLastLocalId();
        MediaController.access$3100(this.this$0).user_id = UserConfig.getInstance(this.val$currentAccount).getClientUserId();
        MediaController.access$3100(this.this$0).mime_type = "audio/ogg";
        MediaController.access$3100(this.this$0).thumb = new TLRPC$TL_photoSizeEmpty();
        MediaController.access$3100(this.this$0).thumb.type = "s";
        SharedConfig.saveConfig();
        MediaController.access$5502(this.this$0, new File(FileLoader.getDirectory(4), FileLoader.getAttachFileName(MediaController.access$3100(this.this$0))));
        try {
            if (MediaController.access$5600(this.this$0, MediaController.access$5500(this.this$0).getAbsolutePath()) == 0) {
                AndroidUtilities.runOnUIThread(new C08372());
                return;
            }
            int rate = 16000;
            if (TurboConfig.voiceChangerType == 1) {
                switch (TurboConfig.voiceChangerSpeed) {
                    case 1:
                        rate = 44100;
                        break;
                    case 2:
                        rate = 22050;
                        break;
                    case 3:
                        rate = 11025;
                        break;
                    case 4:
                        rate = 8000;
                        break;
                }
            }
            MediaController.access$002(this.this$0, new AudioRecord(1, rate, 16, 2, MediaController.access$200(this.this$0) * 10));
            MediaController.access$1102(this.this$0, System.currentTimeMillis());
            MediaController.access$702(this.this$0, 0);
            MediaController.access$302(this.this$0, 0);
            MediaController.access$5702(this.this$0, this.val$dialog_id);
            MediaController.access$1202(this.this$0, this.val$currentAccount);
            MediaController.access$5802(this.this$0, this.val$reply_to_msg);
            MediaController.access$500(this.this$0).rewind();
            MediaController.access$000(this.this$0).startRecording();
            if (TurboConfig.voiceChangerType == 0 || TurboConfig.voiceChangerType == 1) {
                MediaController.access$800(this.this$0).postRunnable(MediaController.access$1000(this.this$0));
            } else {
                MediaController.access$6000(this.this$0, TurboConfig.voiceChangerType);
                MediaController.access$800(this.this$0).postRunnable(MediaController.access$6100(this.this$0));
            }
            AndroidUtilities.runOnUIThread(new C08394());
        } catch (Exception e) {
            FileLog.e(e);
            MediaController.access$3102(this.this$0, null);
            MediaController.access$5900(this.this$0);
            MediaController.access$5500(this.this$0).delete();
            MediaController.access$5502(this.this$0, null);
            try {
                MediaController.access$000(this.this$0).release();
                MediaController.access$002(this.this$0, null);
            } catch (Exception e2) {
                FileLog.e(e2);
            }
            AndroidUtilities.runOnUIThread(new C08383());
        }
    }
}
