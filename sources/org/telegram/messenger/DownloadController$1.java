package org.telegram.messenger;

class DownloadController$1 implements Runnable {
    final /* synthetic */ DownloadController this$0;

    DownloadController$1(DownloadController this$0) {
        this.this$0 = this$0;
    }

    public void run() {
        NotificationCenter.getInstance(DownloadController.access$000(this.this$0)).addObserver(this.this$0, NotificationCenter.FileDidFailedLoad);
        NotificationCenter.getInstance(DownloadController.access$000(this.this$0)).addObserver(this.this$0, NotificationCenter.FileDidLoaded);
        NotificationCenter.getInstance(DownloadController.access$000(this.this$0)).addObserver(this.this$0, NotificationCenter.FileLoadProgressChanged);
        NotificationCenter.getInstance(DownloadController.access$000(this.this$0)).addObserver(this.this$0, NotificationCenter.FileUploadProgressChanged);
        NotificationCenter.getInstance(DownloadController.access$000(this.this$0)).addObserver(this.this$0, NotificationCenter.httpFileDidLoaded);
        NotificationCenter.getInstance(DownloadController.access$000(this.this$0)).addObserver(this.this$0, NotificationCenter.httpFileDidFailedLoad);
    }
}
