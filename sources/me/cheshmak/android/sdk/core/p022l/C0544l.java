package me.cheshmak.android.sdk.core.p022l;

import android.os.Bundle;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: me.cheshmak.android.sdk.core.l.l */
public class C0544l {
    /* renamed from: a */
    public static Bundle m1035a(JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            bundle.putString(str, jSONObject.optString(str));
        }
        return bundle;
    }

    /* renamed from: a */
    public static JSONObject m1036a(Bundle bundle) {
        try {
            return new JSONObject(bundle.getString("setting"));
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m1037a(String str) {
        if (str == null) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            try {
                JSONArray jSONArray = new JSONArray(str);
            } catch (JSONException e2) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: b */
    public static JSONObject m1038b(Bundle bundle) {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            try {
                jSONObject.put(str, bundle.get(str) + "");
            } catch (JSONException e) {
            }
        }
        return jSONObject;
    }

    /* renamed from: b */
    public static JSONObject m1039b(String str) {
        if (str == null) {
            return null;
        }
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            try {
                JSONArray jSONArray = new JSONArray(str);
                return null;
            } catch (JSONException e2) {
                return null;
            }
        }
    }
}
