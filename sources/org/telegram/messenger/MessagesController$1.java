package org.telegram.messenger;

class MessagesController$1 implements Runnable {
    final /* synthetic */ MessagesController this$0;

    MessagesController$1(MessagesController this$0) {
        this.this$0 = this$0;
    }

    public void run() {
        UserConfig.getInstance(MessagesController.access$000(this.this$0)).checkSavedPassword();
    }
}
