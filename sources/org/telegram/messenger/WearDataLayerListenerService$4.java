package org.telegram.messenger;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class WearDataLayerListenerService$4 implements NotificationCenter$NotificationCenterDelegate {
    final /* synthetic */ WearDataLayerListenerService this$0;
    final /* synthetic */ CyclicBarrier val$barrier;
    final /* synthetic */ String[] val$code;

    WearDataLayerListenerService$4(WearDataLayerListenerService this$0, String[] strArr, CyclicBarrier cyclicBarrier) {
        this.this$0 = this$0;
        this.val$code = strArr;
        this.val$barrier = cyclicBarrier;
    }

    public void didReceivedNotification(int id, int account, Object... args) {
        if (id == NotificationCenter.didReceivedNewMessages && ((Long) args[0]).longValue() == 777000) {
            ArrayList<MessageObject> arr = args[1];
            if (arr.size() > 0) {
                MessageObject msg = (MessageObject) arr.get(0);
                if (!TextUtils.isEmpty(msg.messageText)) {
                    Matcher matcher = Pattern.compile("[0-9]+").matcher(msg.messageText);
                    if (matcher.find()) {
                        this.val$code[0] = matcher.group();
                        try {
                            this.val$barrier.await(10, TimeUnit.MILLISECONDS);
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
    }
}
