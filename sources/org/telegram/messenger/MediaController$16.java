package org.telegram.messenger;

class MediaController$16 implements Runnable {
    final /* synthetic */ MediaController this$0;
    final /* synthetic */ MessageObject val$messageObject;

    MediaController$16(MediaController this$0, MessageObject messageObject) {
        this.this$0 = this$0;
        this.val$messageObject = messageObject;
    }

    public void run() {
        NotificationCenter.getInstance(this.val$messageObject.currentAccount).postNotificationName(NotificationCenter.FileDidLoaded, FileLoader.getAttachFileName(this.val$messageObject.getDocument()));
    }
}
