package me.cheshmak.android.sdk.advertise;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;
import me.cheshmak.android.sdk.core.p022l.C0519d;

/* renamed from: me.cheshmak.android.sdk.advertise.c */
public class C0465c {
    /* renamed from: a */
    static String f421a = "AdvertiseUtils";

    /* renamed from: a */
    public static String m628a(Context context) {
        String str = "";
        try {
            Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
            if (advertisingIdInfo != null) {
                str = advertisingIdInfo.getId();
            }
        } catch (IOException e) {
        } catch (IllegalStateException e2) {
        } catch (GooglePlayServicesRepairableException e3) {
        } catch (GooglePlayServicesNotAvailableException e4) {
        } catch (Throwable th) {
            C0519d.m899b("Throwable", "Throwable", th);
        }
        return str;
    }
}
