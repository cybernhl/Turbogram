package org.telegram.messenger;

class MediaController$20 implements Runnable {
    final /* synthetic */ MediaController this$0;
    final /* synthetic */ int val$send;

    /* renamed from: org.telegram.messenger.MediaController$20$1 */
    class C08421 implements Runnable {
        C08421() {
        }

        public void run() {
            int i = 1;
            NotificationCenter instance = NotificationCenter.getInstance(MediaController.access$1200(MediaController$20.this.this$0));
            int i2 = NotificationCenter.recordStopped;
            Object[] objArr = new Object[1];
            if (MediaController$20.this.val$send != 2) {
                i = 0;
            }
            objArr[0] = Integer.valueOf(i);
            instance.postNotificationName(i2, objArr);
        }
    }

    MediaController$20(MediaController this$0, int i) {
        this.this$0 = this$0;
        this.val$send = i;
    }

    public void run() {
        if (MediaController.access$000(this.this$0) != null) {
            try {
                MediaController.access$1302(this.this$0, this.val$send);
                MediaController.access$000(this.this$0).stop();
            } catch (Exception e) {
                FileLog.e(e);
                if (MediaController.access$5500(this.this$0) != null) {
                    MediaController.access$5500(this.this$0).delete();
                }
            }
            if (this.val$send == 0) {
                MediaController.access$1400(this.this$0, 0);
            }
            try {
                MediaController.access$6300(this.this$0).performHapticFeedback(3, 2);
            } catch (Exception e2) {
            }
            AndroidUtilities.runOnUIThread(new C08421());
        }
    }
}
