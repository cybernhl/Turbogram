package org.telegram.messenger;

import android.media.AudioRecord;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

class MediaController$3 implements Runnable {
    final /* synthetic */ MediaController this$0;

    MediaController$3(MediaController this$0) {
        this.this$0 = this$0;
    }

    public void run() {
        try {
            MediaController.access$202(this.this$0, AudioRecord.getMinBufferSize(16000, 16, 2));
            if (MediaController.access$200(this.this$0) <= 0) {
                MediaController.access$202(this.this$0, 1280);
            }
            for (int a = 0; a < 5; a++) {
                ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
                buffer.order(ByteOrder.nativeOrder());
                MediaController.access$100(this.this$0).add(buffer);
            }
        } catch (Exception e) {
            FileLog.e(e);
        }
    }
}
