package com.google.android.gms.maps;

import android.location.Location;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.maps.GoogleMap.OnMyLocationClickListener;
import com.google.android.gms.maps.internal.zzba;

final class zzj extends zzba {
    private final /* synthetic */ OnMyLocationClickListener zzr;

    zzj(GoogleMap googleMap, OnMyLocationClickListener onMyLocationClickListener) {
        this.zzr = onMyLocationClickListener;
    }

    public final void onMyLocationClick(@NonNull Location location) throws RemoteException {
        this.zzr.onMyLocationClick(location);
    }
}