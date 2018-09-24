package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.provider.CalendarContract.Events;
import android.text.TextUtils;
import com.google.android.gms.ads.impl.C0371R;
import com.google.android.gms.ads.internal.zzbv;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.Map;

@zzadh
public final class zzzy extends zzaal {
    private final Context mContext;
    private final Map<String, String> zzbgp;
    private String zzbvs = zzbu("description");
    private long zzbvt = zzbv("start_ticks");
    private long zzbvu = zzbv("end_ticks");
    private String zzbvv = zzbu("summary");
    private String zzbvw = zzbu(Param.LOCATION);

    public zzzy(zzaqw zzaqw, Map<String, String> map) {
        super(zzaqw, "createCalendarEvent");
        this.zzbgp = map;
        this.mContext = zzaqw.zzto();
    }

    private final String zzbu(String str) {
        return TextUtils.isEmpty((CharSequence) this.zzbgp.get(str)) ? "" : (String) this.zzbgp.get(str);
    }

    private final long zzbv(String str) {
        String str2 = (String) this.zzbgp.get(str);
        if (str2 == null) {
            return -1;
        }
        try {
            return Long.parseLong(str2);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    @TargetApi(14)
    final Intent createIntent() {
        Intent data = new Intent("android.intent.action.EDIT").setData(Events.CONTENT_URI);
        data.putExtra("title", this.zzbvs);
        data.putExtra("eventLocation", this.zzbvw);
        data.putExtra("description", this.zzbvv);
        if (this.zzbvt > -1) {
            data.putExtra("beginTime", this.zzbvt);
        }
        if (this.zzbvu > -1) {
            data.putExtra("endTime", this.zzbvu);
        }
        data.setFlags(268435456);
        return data;
    }

    public final void execute() {
        if (this.mContext == null) {
            zzbw("Activity context is not available.");
            return;
        }
        zzbv.zzek();
        if (zzakk.zzao(this.mContext).zziz()) {
            zzbv.zzek();
            Builder zzan = zzakk.zzan(this.mContext);
            Resources resources = zzbv.zzeo().getResources();
            zzan.setTitle(resources != null ? resources.getString(C0371R.string.s5) : "Create calendar event");
            zzan.setMessage(resources != null ? resources.getString(C0371R.string.s6) : "Allow Ad to create a calendar event?");
            zzan.setPositiveButton(resources != null ? resources.getString(C0371R.string.s3) : "Accept", new zzzz(this));
            zzan.setNegativeButton(resources != null ? resources.getString(C0371R.string.s4) : "Decline", new zzaaa(this));
            zzan.create().show();
            return;
        }
        zzbw("This feature is not available on the device.");
    }
}
