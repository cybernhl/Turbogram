package com.google.android.gms.internal.ads;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public final class zzap {
    public static zzc zzb(zzp zzp) {
        Object obj;
        long j;
        long currentTimeMillis = System.currentTimeMillis();
        Map map = zzp.zzab;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        Object obj2 = null;
        String str = (String) map.get("Date");
        if (str != null) {
            j2 = zzf(str);
        }
        str = (String) map.get("Cache-Control");
        if (str != null) {
            String[] split = str.split(",");
            int i = 0;
            obj = null;
            while (i < split.length) {
                String trim = split[i].trim();
                if (trim.equals("no-cache") || trim.equals("no-store")) {
                    return null;
                }
                if (trim.startsWith("max-age=")) {
                    try {
                        j3 = Long.parseLong(trim.substring(8));
                        j = j4;
                    } catch (Exception e) {
                        j = j4;
                    }
                } else if (trim.startsWith("stale-while-revalidate=")) {
                    try {
                        j = Long.parseLong(trim.substring(23));
                    } catch (Exception e2) {
                        j = j4;
                    }
                } else if (trim.equals("must-revalidate") || trim.equals("proxy-revalidate")) {
                    obj = 1;
                    j = j4;
                } else {
                    j = j4;
                }
                i++;
                j4 = j;
            }
            obj2 = 1;
        } else {
            obj = null;
        }
        str = (String) map.get("Expires");
        long zzf = str != null ? zzf(str) : 0;
        str = (String) map.get("Last-Modified");
        long zzf2 = str != null ? zzf(str) : 0;
        str = (String) map.get("ETag");
        if (obj2 != null) {
            j3 = currentTimeMillis + (1000 * j3);
            j = obj != null ? j3 : (1000 * j4) + j3;
        } else if (j2 <= 0 || zzf < j2) {
            j = 0;
            j3 = 0;
        } else {
            j4 = currentTimeMillis + (zzf - j2);
            j = j4;
            j3 = j4;
        }
        zzc zzc = new zzc();
        zzc.data = zzp.data;
        zzc.zza = str;
        zzc.zze = j3;
        zzc.zzd = j;
        zzc.zzb = j2;
        zzc.zzc = zzf2;
        zzc.zzf = map;
        zzc.zzg = zzp.allHeaders;
        return zzc;
    }

    static String zzb(long j) {
        return zzp().format(new Date(j));
    }

    private static long zzf(String str) {
        try {
            return zzp().parse(str).getTime();
        } catch (Throwable e) {
            zzaf.zza(e, "Unable to parse dateStr: %s, falling back to 0", str);
            return 0;
        }
    }

    private static SimpleDateFormat zzp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat;
    }
}
