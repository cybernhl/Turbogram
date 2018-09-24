package com.google.android.gms.internal.vision;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.vision.barcode.Barcode;

public interface zzh extends IInterface {
    Barcode[] zza(IObjectWrapper iObjectWrapper, zzm zzm) throws RemoteException;

    Barcode[] zzb(IObjectWrapper iObjectWrapper, zzm zzm) throws RemoteException;

    void zzn() throws RemoteException;
}
