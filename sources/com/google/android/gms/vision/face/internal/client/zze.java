package com.google.android.gms.vision.face.internal.client;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.vision.zzm;

public interface zze extends IInterface {
    FaceParcel[] zzc(IObjectWrapper iObjectWrapper, zzm zzm) throws RemoteException;

    boolean zzd(int i) throws RemoteException;

    void zzn() throws RemoteException;
}
