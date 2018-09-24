package me.cheshmak.android.sdk.core.push.p030a;

import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import org.json.JSONObject;

/* renamed from: me.cheshmak.android.sdk.core.push.a.d */
class C0580d extends C0577h {
    /* renamed from: a */
    long f735a;
    /* renamed from: b */
    DownloadManager f736b;

    /* renamed from: me.cheshmak.android.sdk.core.push.a.d$1 */
    class C05791 extends BroadcastReceiver {
        /* renamed from: a */
        final /* synthetic */ C0580d f734a;

        C05791(C0580d c0580d) {
            this.f734a = c0580d;
        }

        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.DOWNLOAD_COMPLETE".equals(intent.getAction())) {
                intent.getLongExtra("extra_download_id", 0);
                Query query = new Query();
                query.setFilterById(new long[]{this.f734a.f735a});
                Cursor query2 = this.f734a.f736b.query(query);
                if (query2.moveToFirst() && 8 == query2.getInt(query2.getColumnIndex(NotificationCompat.CATEGORY_STATUS))) {
                    String string = query2.getString(query2.getColumnIndex("local_uri"));
                    Intent intent2 = new Intent("android.intent.action.VIEW");
                    Log.e("xxxxxxxxxxxx", query2.getString(query2.getColumnIndex("media_type")));
                    intent2.setDataAndType(Uri.parse(string), query2.getString(query2.getColumnIndex("media_type")));
                    intent2.setFlags(268435456);
                    context.startActivity(intent2);
                    context.unregisterReceiver(this);
                }
            }
        }
    }

    public C0580d(Context context, Bundle bundle) {
        super(context, bundle);
    }

    /* renamed from: a */
    void mo4410a() {
        try {
            this.f736b = (DownloadManager) this.c.getSystemService("download");
            JSONObject jSONObject = new JSONObject(this.d.getString("me.cheshmak.data"));
            Request request = new Request(Uri.parse(jSONObject.getString("url")));
            request.setTitle("Downloading ...");
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, jSONObject.getString("url").substring(jSONObject.getString("url").lastIndexOf(47) + 1, jSONObject.getString("url").length()));
            this.f735a = this.f736b.enqueue(request);
            this.c.registerReceiver(new C05791(this), new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
        } catch (Throwable e) {
            Log.e("xxxxxxxxxxxxxxx", "downloader", e);
        }
    }
}
