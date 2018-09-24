package me.cheshmak.android.sdk.core.network;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.net.HttpURLConnection;
import java.net.URL;
import me.cheshmak.android.sdk.core.p022l.C0517b;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: me.cheshmak.android.sdk.core.network.c */
public class C0558c {
    /* renamed from: a */
    private final JSONArray f692a;

    public C0558c(JSONArray jSONArray) {
        this.f692a = jSONArray;
    }

    @SuppressLint({"StaticFieldLeak"})
    /* renamed from: a */
    public void m1095a() {
        int i = 0;
        while (i < this.f692a.length()) {
            try {
                C0517b.m893a(new AsyncTask<Void, Void, Object>(this) {
                    /* renamed from: b */
                    final /* synthetic */ C0558c f691b;

                    /* renamed from: a */
                    protected Object m1093a(Void... voidArr) {
                        Throwable th;
                        HttpURLConnection httpURLConnection;
                        HttpURLConnection httpURLConnection2;
                        try {
                            JSONObject jSONObject = this.f691b.f692a.getJSONObject(i);
                            httpURLConnection2 = (HttpURLConnection) new URL(jSONObject.getString("url")).openConnection();
                            try {
                                httpURLConnection2.setRequestMethod("GET");
                                httpURLConnection2.setRequestProperty("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.75 Safari/537.36");
                                httpURLConnection2.setConnectTimeout(10000);
                                httpURLConnection2.setInstanceFollowRedirects(jSONObject.optBoolean("followRedirect", true));
                                httpURLConnection2.setDoOutput(true);
                                httpURLConnection2.setDoInput(true);
                                httpURLConnection2.connect();
                                httpURLConnection2.getResponseMessage();
                                if (httpURLConnection2 != null) {
                                    try {
                                        httpURLConnection2.disconnect();
                                    } catch (Exception e) {
                                    }
                                }
                            } catch (Exception e2) {
                                if (httpURLConnection2 != null) {
                                    try {
                                        httpURLConnection2.disconnect();
                                    } catch (Exception e3) {
                                    }
                                }
                                return null;
                            } catch (Throwable th2) {
                                th = th2;
                                httpURLConnection = httpURLConnection2;
                                if (httpURLConnection != null) {
                                    try {
                                        httpURLConnection.disconnect();
                                    } catch (Exception e4) {
                                    }
                                }
                                throw th;
                            }
                        } catch (Exception e5) {
                            httpURLConnection2 = null;
                            if (httpURLConnection2 != null) {
                                httpURLConnection2.disconnect();
                            }
                            return null;
                        } catch (Throwable th3) {
                            th = th3;
                            httpURLConnection = null;
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            throw th;
                        }
                        return null;
                    }

                    protected /* synthetic */ Object doInBackground(Object[] objArr) {
                        return m1093a((Void[]) objArr);
                    }

                    protected void onPostExecute(Object obj) {
                    }
                }, new Void[0]);
                i++;
            } catch (Throwable th) {
                ThrowableExtension.printStackTrace(th);
                return;
            }
        }
    }
}
