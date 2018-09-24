package org.telegram.messenger;

class UserConfig$1 implements Runnable {
    final /* synthetic */ UserConfig this$0;

    UserConfig$1(UserConfig this$0) {
        this.this$0 = this$0;
    }

    public void run() {
        this.this$0.saveConfig(false);
    }
}
