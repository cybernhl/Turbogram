package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.mohamadamin.persianmaterialdatetimepicker.date.MonthView;
import java.util.Map;

final class zzasr implements zzv<zzaqw> {
    private final /* synthetic */ zzasq zzdev;

    zzasr(zzasq zzasq) {
        this.zzdev = zzasq;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        if (map != null) {
            String str = (String) map.get(MonthView.VIEW_PARAMS_HEIGHT);
            if (!TextUtils.isEmpty(str)) {
                try {
                    int parseInt = Integer.parseInt(str);
                    synchronized (this.zzdev) {
                        if (this.zzdev.zzddu != parseInt) {
                            this.zzdev.zzddu = parseInt;
                            this.zzdev.requestLayout();
                        }
                    }
                } catch (Throwable e) {
                    zzane.zzc("Exception occurred while getting webview content height", e);
                }
            }
        }
    }
}
