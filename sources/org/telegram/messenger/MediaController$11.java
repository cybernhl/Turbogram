package org.telegram.messenger;

class MediaController$11 implements Runnable {
    final /* synthetic */ MediaController this$0;

    MediaController$11(MediaController this$0) {
        this.this$0 = this$0;
    }

    public void run() {
        if (MediaController.access$2400(this.this$0) != null) {
            MediaController.access$2300(this.this$0).unregisterListener(this.this$0, MediaController.access$2400(this.this$0));
        }
        if (MediaController.access$2500(this.this$0) != null) {
            MediaController.access$2300(this.this$0).unregisterListener(this.this$0, MediaController.access$2500(this.this$0));
        }
        if (MediaController.access$2600(this.this$0) != null) {
            MediaController.access$2300(this.this$0).unregisterListener(this.this$0, MediaController.access$2600(this.this$0));
        }
        MediaController.access$2300(this.this$0).unregisterListener(this.this$0, MediaController.access$2700(this.this$0));
    }
}
