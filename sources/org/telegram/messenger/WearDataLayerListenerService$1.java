package org.telegram.messenger;

import java.io.File;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

class WearDataLayerListenerService$1 implements NotificationCenter$NotificationCenterDelegate {
    final /* synthetic */ WearDataLayerListenerService this$0;
    final /* synthetic */ CyclicBarrier val$barrier;
    final /* synthetic */ File val$photo;

    WearDataLayerListenerService$1(WearDataLayerListenerService this$0, File file, CyclicBarrier cyclicBarrier) {
        this.this$0 = this$0;
        this.val$photo = file;
        this.val$barrier = cyclicBarrier;
    }

    public void didReceivedNotification(int id, int account, Object... args) {
        if (id == NotificationCenter.FileDidLoaded) {
            if (BuildVars.LOGS_ENABLED) {
                FileLog.d("file loaded: " + args[0] + " " + args[0].getClass().getName());
            }
            if (args[0].equals(this.val$photo.getName())) {
                if (BuildVars.LOGS_ENABLED) {
                    FileLog.e("LOADED USER PHOTO");
                }
                try {
                    this.val$barrier.await(10, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                }
            }
        }
    }
}
