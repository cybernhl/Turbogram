package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class DownloadController$2 extends BroadcastReceiver {
    final /* synthetic */ DownloadController this$0;

    DownloadController$2(DownloadController this$0) {
        this.this$0 = this$0;
    }

    public void onReceive(Context context, Intent intent) {
        this.this$0.checkAutodownloadSettings();
    }
}
