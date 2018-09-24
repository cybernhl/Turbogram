package org.telegram.messenger;

import org.telegram.tgnet.TLRPC.User;

class WearDataLayerListenerService$2 implements Runnable {
    final /* synthetic */ WearDataLayerListenerService this$0;
    final /* synthetic */ NotificationCenter$NotificationCenterDelegate val$listener;
    final /* synthetic */ User val$user;

    WearDataLayerListenerService$2(WearDataLayerListenerService this$0, NotificationCenter$NotificationCenterDelegate notificationCenter$NotificationCenterDelegate, User user) {
        this.this$0 = this$0;
        this.val$listener = notificationCenter$NotificationCenterDelegate;
        this.val$user = user;
    }

    public void run() {
        NotificationCenter.getInstance(WearDataLayerListenerService.access$000(this.this$0)).addObserver(this.val$listener, NotificationCenter.FileDidLoaded);
        FileLoader.getInstance(WearDataLayerListenerService.access$000(this.this$0)).loadFile(this.val$user.photo.photo_small, null, 0, 1);
    }
}
