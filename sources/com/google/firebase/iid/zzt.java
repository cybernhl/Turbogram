package com.google.firebase.iid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.io.IOException;

final class zzt implements Continuation<Bundle, String> {
    private final /* synthetic */ zzq zzbk;

    zzt(zzq zzq) {
        this.zzbk = zzq;
    }

    public final /* synthetic */ Object then(@NonNull Task task) throws Exception {
        return zzq.zza((Bundle) task.getResult(IOException.class));
    }
}
