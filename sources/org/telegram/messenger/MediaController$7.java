package org.telegram.messenger;

class MediaController$7 implements Runnable {
    final /* synthetic */ MediaController this$0;

    MediaController$7(MediaController this$0) {
        this.this$0 = this$0;
    }

    public void run() {
        try {
            if (MediaController.access$3900(this.this$0) != null) {
                ApplicationLoader.applicationContext.getContentResolver().unregisterContentObserver(MediaController.access$3900(this.this$0));
                MediaController.access$3902(this.this$0, null);
            }
        } catch (Exception e) {
            FileLog.e(e);
        }
    }
}
