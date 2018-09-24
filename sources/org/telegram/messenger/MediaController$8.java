package org.telegram.messenger;

import java.util.ArrayList;

class MediaController$8 implements Runnable {
    final /* synthetic */ MediaController this$0;
    final /* synthetic */ ArrayList val$screenshotDates;

    MediaController$8(MediaController this$0, ArrayList arrayList) {
        this.this$0 = this$0;
        this.val$screenshotDates = arrayList;
    }

    public void run() {
        NotificationCenter.getInstance(MediaController.access$4100(this.this$0)).postNotificationName(NotificationCenter.screenshotTook, new Object[0]);
        MediaController.access$4200(this.this$0, this.val$screenshotDates);
    }
}
