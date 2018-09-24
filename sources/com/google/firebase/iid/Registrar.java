package com.google.firebase.iid;

import android.support.annotation.Keep;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.FirebaseApp;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.events.Subscriber;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import java.util.Arrays;
import java.util.List;

@Keep
@KeepForSdk
public final class Registrar implements ComponentRegistrar {

    private static class zza implements FirebaseInstanceIdInternal {
        private final FirebaseInstanceId zzck;

        public zza(FirebaseInstanceId firebaseInstanceId) {
            this.zzck = firebaseInstanceId;
        }

        public final String getId() {
            return this.zzck.getId();
        }

        public final String getToken() {
            return this.zzck.getToken();
        }
    }

    @Keep
    public final List<Component<?>> getComponents() {
        Component build = Component.builder(FirebaseInstanceId.class).add(Dependency.required(FirebaseApp.class)).add(Dependency.required(Subscriber.class)).factory(zzan.zzcj).alwaysEager().build();
        Component build2 = Component.builder(FirebaseInstanceIdInternal.class).add(Dependency.required(FirebaseInstanceId.class)).factory(zzao.zzcj).build();
        return Arrays.asList(new Component[]{build, build2});
    }
}
