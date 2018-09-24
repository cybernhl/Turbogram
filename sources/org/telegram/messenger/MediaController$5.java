package org.telegram.messenger;

class MediaController$5 implements Runnable {
    final /* synthetic */ MediaController this$0;

    MediaController$5(MediaController this$0) {
        this.this$0 = this$0;
    }

    public void run() {
        for (int a = 0; a < 3; a++) {
            NotificationCenter.getInstance(a).addObserver(this.this$0, NotificationCenter.FileDidLoaded);
            NotificationCenter.getInstance(a).addObserver(this.this$0, NotificationCenter.httpFileDidLoaded);
            NotificationCenter.getInstance(a).addObserver(this.this$0, NotificationCenter.didReceivedNewMessages);
            NotificationCenter.getInstance(a).addObserver(this.this$0, NotificationCenter.messagesDeleted);
            NotificationCenter.getInstance(a).addObserver(this.this$0, NotificationCenter.removeAllMessagesFromDialog);
            NotificationCenter.getInstance(a).addObserver(this.this$0, NotificationCenter.musicDidLoaded);
            NotificationCenter.getGlobalInstance().addObserver(this.this$0, NotificationCenter.playerDidStartPlaying);
        }
    }
}
