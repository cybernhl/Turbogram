package org.telegram.tgnet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.telegram.messenger.FileLoader;

class ConnectionsManager$3 extends BroadcastReceiver {
    final /* synthetic */ ConnectionsManager this$0;

    ConnectionsManager$3(ConnectionsManager this$0) {
        this.this$0 = this$0;
    }

    public void onReceive(Context context, Intent intent) {
        ConnectionsManager.access$100(this.this$0);
        FileLoader.getInstance(ConnectionsManager.access$000(this.this$0)).onNetworkChanged(ConnectionsManager.isConnectionSlow());
    }
}
