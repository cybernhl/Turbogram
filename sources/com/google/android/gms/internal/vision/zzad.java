package com.google.android.gms.internal.vision;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.LoadingException;

public final class zzad extends zzl<zzt> {
    private final zzae zzdg;

    public zzad(Context context, zzae zzae) {
        super(context, "TextNativeHandle", "text");
        this.zzdg = zzae;
        zzp();
    }

    protected final /* synthetic */ Object zza(DynamiteModule dynamiteModule, Context context) throws RemoteException, LoadingException {
        zzv zzv;
        IBinder instantiate = dynamiteModule.instantiate("com.google.android.gms.vision.text.ChimeraNativeTextRecognizerCreator");
        if (instantiate == null) {
            zzv = null;
        } else {
            IInterface queryLocalInterface = instantiate.queryLocalInterface("com.google.android.gms.vision.text.internal.client.INativeTextRecognizerCreator");
            zzv = queryLocalInterface instanceof zzv ? (zzv) queryLocalInterface : new zzw(instantiate);
        }
        return zzv == null ? null : zzv.zza(ObjectWrapper.wrap(context), this.zzdg);
    }

    public final zzx[] zza(Bitmap bitmap, zzm zzm, zzz zzz) {
        if (!isOperational()) {
            return new zzx[0];
        }
        try {
            return ((zzt) zzp()).zza(ObjectWrapper.wrap(bitmap), zzm, zzz);
        } catch (Throwable e) {
            Log.e("TextNativeHandle", "Error calling native text recognizer", e);
            return new zzx[0];
        }
    }

    protected final void zzm() throws RemoteException {
        ((zzt) zzp()).zzq();
    }
}
