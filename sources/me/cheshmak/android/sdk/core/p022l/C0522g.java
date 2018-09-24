package me.cheshmak.android.sdk.core.p022l;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import me.cheshmak.android.sdk.core.p026h.C0504c;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: me.cheshmak.android.sdk.core.l.g */
public class C0522g {
    /* renamed from: a */
    public static String m921a(List<C0504c> list) {
        try {
            JSONArray jSONArray = new JSONArray();
            if (list != null) {
                for (C0504c b : list) {
                    jSONArray.put(new JSONArray(b.m831b()));
                }
            }
            return jSONArray.toString();
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: a */
    public static JSONObject m922a(Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        try {
            for (Entry entry : map.entrySet()) {
                jSONObject.put((String) entry.getKey(), entry.getValue());
            }
        } catch (Exception e) {
        }
        return jSONObject;
    }

    /* renamed from: b */
    public static Set<String> m923b(List<C0504c> list) {
        Set<String> hashSet = new HashSet();
        try {
            for (C0504c a : list) {
                hashSet.add(a.m828a() + "");
            }
        } catch (Exception e) {
        }
        return hashSet;
    }

    /* renamed from: c */
    public static String m924c(List<String> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (String quote : list) {
                arrayList.add(JSONObject.quote(quote));
            }
        }
        return arrayList.toString();
    }
}
