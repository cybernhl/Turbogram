package com.google.firebase.events;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* compiled from: com.google.firebase:firebase-common@@16.0.1 */
public interface Publisher {
    @KeepForSdk
    void publish(Event<?> event);
}
