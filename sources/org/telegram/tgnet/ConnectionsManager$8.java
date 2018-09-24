package org.telegram.tgnet;

import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;

class ConnectionsManager$8 implements Runnable {
    final /* synthetic */ int val$currentAccount;

    ConnectionsManager$8(int i) {
        this.val$currentAccount = i;
    }

    public void run() {
        if (UserConfig.getInstance(this.val$currentAccount).getClientUserId() != 0) {
            UserConfig.getInstance(this.val$currentAccount).clearConfig();
            MessagesController.getInstance(this.val$currentAccount).performLogout(0);
        }
    }
}
