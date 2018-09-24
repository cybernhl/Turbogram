package com.google.firebase.iid;

import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;

final /* synthetic */ class zzp implements EventHandler {
    private final zza zzbb;

    zzp(zza zza) {
        this.zzbb = zza;
    }

    public final void handle(Event event) {
        zza zza = this.zzbb;
        synchronized (zza) {
            if (zza.isEnabled()) {
                zza.zzba.zzf();
            }
        }
    }
}
