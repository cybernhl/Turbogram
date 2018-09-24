package org.telegram.messenger;

import java.io.File;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLRPC$TL_document;
import org.telegram.tgnet.TLRPC$TL_documentAttributeAudio;

class MediaController$19 implements Runnable {
    final /* synthetic */ MediaController this$0;
    final /* synthetic */ TLRPC$TL_document val$audioToSend;
    final /* synthetic */ File val$recordingAudioFileToSend;
    final /* synthetic */ int val$send;

    /* renamed from: org.telegram.messenger.MediaController$19$1 */
    class C08411 implements Runnable {
        C08411() {
        }

        public void run() {
            MediaController$19.this.val$audioToSend.date = ConnectionsManager.getInstance(MediaController.access$1200(MediaController$19.this.this$0)).getCurrentTime();
            MediaController$19.this.val$audioToSend.size = (int) MediaController$19.this.val$recordingAudioFileToSend.length();
            TLRPC$TL_documentAttributeAudio attributeAudio = new TLRPC$TL_documentAttributeAudio();
            attributeAudio.voice = true;
            attributeAudio.waveform = MediaController$19.this.this$0.getWaveform2(MediaController.access$400(MediaController$19.this.this$0), MediaController.access$400(MediaController$19.this.this$0).length);
            if (attributeAudio.waveform != null) {
                attributeAudio.flags |= 4;
            }
            long duration = MediaController.access$700(MediaController$19.this.this$0);
            attributeAudio.duration = (int) (MediaController.access$700(MediaController$19.this.this$0) / 1000);
            MediaController$19.this.val$audioToSend.attributes.add(attributeAudio);
            if (duration > 700) {
                if (MediaController$19.this.val$send == 1) {
                    SendMessagesHelper.getInstance(MediaController.access$1200(MediaController$19.this.this$0)).sendMessage(MediaController$19.this.val$audioToSend, null, MediaController$19.this.val$recordingAudioFileToSend.getAbsolutePath(), MediaController.access$5700(MediaController$19.this.this$0), MediaController.access$5800(MediaController$19.this.this$0), null, null, null, null, 0);
                }
                NotificationCenter instance = NotificationCenter.getInstance(MediaController.access$1200(MediaController$19.this.this$0));
                int i = NotificationCenter.audioDidSent;
                Object[] objArr = new Object[2];
                objArr[0] = MediaController$19.this.val$send == 2 ? MediaController$19.this.val$audioToSend : null;
                objArr[1] = MediaController$19.this.val$send == 2 ? MediaController$19.this.val$recordingAudioFileToSend.getAbsolutePath() : null;
                instance.postNotificationName(i, objArr);
                return;
            }
            MediaController$19.this.val$recordingAudioFileToSend.delete();
        }
    }

    MediaController$19(MediaController this$0, TLRPC$TL_document tLRPC$TL_document, File file, int i) {
        this.this$0 = this$0;
        this.val$audioToSend = tLRPC$TL_document;
        this.val$recordingAudioFileToSend = file;
        this.val$send = i;
    }

    public void run() {
        MediaController.access$5900(this.this$0);
        AndroidUtilities.runOnUIThread(new C08411());
    }
}
