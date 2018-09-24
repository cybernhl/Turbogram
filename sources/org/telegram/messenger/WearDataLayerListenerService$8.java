package org.telegram.messenger;

import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wearable.CapabilityInfo;
import com.google.android.gms.wearable.MessageClient;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.Wearable;

class WearDataLayerListenerService$8 implements OnCompleteListener<CapabilityInfo> {
    final /* synthetic */ byte[] val$data;
    final /* synthetic */ String val$path;

    WearDataLayerListenerService$8(String str, byte[] bArr) {
        this.val$path = str;
        this.val$data = bArr;
    }

    public void onComplete(@NonNull Task<CapabilityInfo> task) {
        CapabilityInfo info = (CapabilityInfo) task.getResult();
        if (info != null) {
            MessageClient mc = Wearable.getMessageClient(ApplicationLoader.applicationContext);
            for (Node node : info.getNodes()) {
                mc.sendMessage(node.getId(), this.val$path, this.val$data);
            }
        }
    }
}
