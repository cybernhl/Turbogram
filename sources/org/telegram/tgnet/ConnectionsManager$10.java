package org.telegram.tgnet;

import org.telegram.messenger.NotificationCenter;

class ConnectionsManager$10 implements Runnable {
    ConnectionsManager$10() {
    }

    public void run() {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needShowAlert, Integer.valueOf(3));
    }
}
