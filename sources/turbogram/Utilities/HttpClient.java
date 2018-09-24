package turbogram.Utilities;

import android.text.TextUtils;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.json.JSONObject;
import org.telegram.messenger.support.widget.helper.ItemTouchHelper.Callback;

public class HttpClient {

    public static class Response {
        public int code;
        public List<String> cookies;
        public String text;

        public Response(int code, String text, List<String> cookies) {
            this.code = code;
            this.text = text;
            this.cookies = cookies;
        }
    }

    public static Response performPostCall(String requestURL, HashMap<String, String> params, List<HttpCookie> cookies) {
        return performHttpCall(requestURL, params, null, null, cookies);
    }

    public static Response performGetCall(String requestURL, List<HttpCookie> cookies) {
        return performHttpCall(requestURL, null, null, null, cookies);
    }

    public static Response performHttpCall(String requestURL, HashMap<String, String> params, JSONObject json, String string, List<HttpCookie> cookies) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(requestURL).openConnection();
            conn.setReadTimeout(DefaultLoadControl.DEFAULT_MIN_BUFFER_MS);
            conn.setConnectTimeout(DefaultLoadControl.DEFAULT_MIN_BUFFER_MS);
            if (params == null && json == null && string == null) {
                conn.setRequestMethod("GET");
            } else {
                conn.setRequestMethod("POST");
            }
            conn.setDoInput(true);
            conn.setDoOutput(true);
            if (cookies != null) {
                conn.setRequestProperty("Cookie", TextUtils.join(";", cookies));
            }
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer;
            if (params != null) {
                writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(params));
                writer.flush();
                writer.close();
            } else if (json != null) {
                os.write(json.toString().getBytes("UTF-8"));
            } else if (string != null) {
                writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(string);
                writer.flush();
                writer.close();
            }
            os.close();
            int resCode = conn.getResponseCode();
            List<String> resCookies = (List) conn.getHeaderFields().get("Set-Cookie");
            String resText = "";
            if (resCode == Callback.DEFAULT_DRAG_ANIMATION_DURATION) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while (true) {
                    String line = br.readLine();
                    if (line == null) {
                        break;
                    }
                    resText = resText + line;
                }
            } else {
                resText = null;
            }
            return new Response(resCode, resText, resCookies);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public static String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Entry<String, String> entry : params.entrySet()) {
            if (first) {
                first = false;
            } else {
                result.append("&");
            }
            result.append(URLEncoder.encode((String) entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode((String) entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }
}
