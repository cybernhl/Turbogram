package org.telegram.messenger;

import java.util.ArrayList;

final /* synthetic */ class NotificationsController$$Lambda$8 implements Runnable {
    private final NotificationsController arg$1;
    private final ArrayList arg$2;
    private final ArrayList arg$3;
    private final boolean arg$4;
    private final ArrayList arg$5;
    private final boolean arg$6;

    NotificationsController$$Lambda$8(NotificationsController notificationsController, ArrayList arrayList, ArrayList arrayList2, boolean z, ArrayList arrayList3, boolean z2) {
        this.arg$1 = notificationsController;
        this.arg$2 = arrayList;
        this.arg$3 = arrayList2;
        this.arg$4 = z;
        this.arg$5 = arrayList3;
        this.arg$6 = z2;
    }

    public void run() {
        this.arg$1.lambda$processNewMessages$16$NotificationsController(this.arg$2, this.arg$3, this.arg$4, this.arg$5, this.arg$6);
    }
}
