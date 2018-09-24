package org.telegram.messenger;

class WearDataLayerListenerService$5 implements Runnable {
    final /* synthetic */ WearDataLayerListenerService this$0;
    final /* synthetic */ NotificationCenter$NotificationCenterDelegate val$listener;

    WearDataLayerListenerService$5(WearDataLayerListenerService this$0, NotificationCenter$NotificationCenterDelegate notificationCenter$NotificationCenterDelegate) {
        this.this$0 = this$0;
        this.val$listener = notificationCenter$NotificationCenterDelegate;
    }

    public void run() {
        NotificationCenter.getInstance(WearDataLayerListenerService.access$000(this.this$0)).addObserver(this.val$listener, NotificationCenter.didReceivedNewMessages);
    }
}
