package org.telegram.messenger;

import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wearable.CapabilityInfo;
import com.google.android.gms.wearable.Node;

class WearDataLayerListenerService$9 implements OnCompleteListener<CapabilityInfo> {
    WearDataLayerListenerService$9() {
    }

    public void onComplete(@NonNull Task<CapabilityInfo> task) {
        WearDataLayerListenerService.access$102(false);
        try {
            CapabilityInfo capabilityInfo = (CapabilityInfo) task.getResult();
            if (capabilityInfo != null) {
                for (Node node : capabilityInfo.getNodes()) {
                    if (node.isNearby()) {
                        WearDataLayerListenerService.access$102(true);
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
