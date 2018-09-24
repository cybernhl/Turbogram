package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC$DocumentAttribute;
import org.telegram.tgnet.TLRPC$TL_documentAttributeAudio;
import org.telegram.tgnet.TLRPC$TL_messages_messages;
import org.telegram.tgnet.TLRPC$messages_Messages;

class MediaController$18 implements Runnable {
    final /* synthetic */ MediaController this$0;
    final /* synthetic */ String val$id;
    final /* synthetic */ String val$path;

    MediaController$18(MediaController this$0, String str, String str2) {
        this.this$0 = this$0;
        this.val$path = str;
        this.val$id = str2;
    }

    public void run() {
        final byte[] waveform = this.this$0.getWaveform(this.val$path);
        AndroidUtilities.runOnUIThread(new Runnable() {
            public void run() {
                MessageObject messageObject = (MessageObject) MediaController.access$6200(MediaController$18.this.this$0).remove(MediaController$18.this.val$id);
                if (messageObject != null && waveform != null) {
                    for (int a = 0; a < messageObject.getDocument().attributes.size(); a++) {
                        TLRPC$DocumentAttribute attribute = (TLRPC$DocumentAttribute) messageObject.getDocument().attributes.get(a);
                        if (attribute instanceof TLRPC$TL_documentAttributeAudio) {
                            attribute.waveform = waveform;
                            attribute.flags |= 4;
                            break;
                        }
                    }
                    TLRPC$messages_Messages messagesRes = new TLRPC$TL_messages_messages();
                    messagesRes.messages.add(messageObject.messageOwner);
                    MessagesStorage.getInstance(messageObject.currentAccount).putMessages(messagesRes, messageObject.getDialogId(), -1, 0, false);
                    new ArrayList().add(messageObject);
                    NotificationCenter.getInstance(messageObject.currentAccount).postNotificationName(NotificationCenter.replaceMessagesObjects, Long.valueOf(messageObject.getDialogId()), arrayList);
                }
            }
        });
    }
}
