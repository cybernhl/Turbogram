package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$TL_error;

class MessagesController$4 implements Runnable {
    final /* synthetic */ MessagesController this$0;
    final /* synthetic */ int val$chat_id;
    final /* synthetic */ TLRPC$TL_error val$error;

    MessagesController$4(MessagesController this$0, TLRPC$TL_error tLRPC$TL_error, int i) {
        this.this$0 = this$0;
        this.val$error = tLRPC$TL_error;
        this.val$chat_id = i;
    }

    public void run() {
        MessagesController.access$700(this.this$0, this.val$error.text, this.val$chat_id);
        MessagesController.access$500(this.this$0).remove(Integer.valueOf(this.val$chat_id));
    }
}
