package com.google.firebase.analytics.connector.internal;

import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.connector.AnalyticsConnector.AnalyticsConnectorListener;
import java.util.Set;

public final class zzf implements zza {
    private AppMeasurement zzbsg;
    private AnalyticsConnectorListener zzbst;
    private zzg zzbsw = new zzg(this);

    public zzf(AppMeasurement appMeasurement, AnalyticsConnectorListener analyticsConnectorListener) {
        this.zzbst = analyticsConnectorListener;
        this.zzbsg = appMeasurement;
        this.zzbsg.registerOnMeasurementEventListener(this.zzbsw);
    }

    public final AnalyticsConnectorListener zztl() {
        return this.zzbst;
    }

    public final void registerEventNames(Set<String> set) {
    }

    public final void unregisterEventNames() {
    }
}
