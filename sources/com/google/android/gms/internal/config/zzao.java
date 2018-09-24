package com.google.android.gms.internal.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public final class zzao {
    private Map<String, Map<String, byte[]>> zzaw;
    private long zzax;
    private List<byte[]> zzs;

    public zzao(Map<String, Map<String, byte[]>> map, long j, List<byte[]> list) {
        this.zzaw = map;
        this.zzax = j;
        this.zzs = list;
    }

    public final long getTimestamp() {
        return this.zzax;
    }

    public final void setTimestamp(long j) {
        this.zzax = j;
    }

    public final void zza(Map<String, byte[]> map, String str) {
        if (this.zzaw == null) {
            this.zzaw = new HashMap();
        }
        this.zzaw.put(str, map);
    }

    public final boolean zzb(String str) {
        return str == null ? false : (!zzq() || this.zzaw.get(str) == null || ((Map) this.zzaw.get(str)).isEmpty()) ? false : true;
    }

    public final boolean zzb(String str, String str2) {
        return zzq() && zzb(str2) && zzc(str, str2) != null;
    }

    public final byte[] zzc(String str, String str2) {
        return (str == null || !zzb(str2)) ? null : (byte[]) ((Map) this.zzaw.get(str2)).get(str);
    }

    public final Set<String> zzd(String str, String str2) {
        Set<String> treeSet = new TreeSet();
        if (!zzb(str2)) {
            return treeSet;
        }
        if (str == null || str.isEmpty()) {
            return ((Map) this.zzaw.get(str2)).keySet();
        }
        for (String str3 : ((Map) this.zzaw.get(str2)).keySet()) {
            if (str3.startsWith(str)) {
                treeSet.add(str3);
            }
        }
        return treeSet;
    }

    public final List<byte[]> zzg() {
        return this.zzs;
    }

    public final Map<String, Map<String, byte[]>> zzp() {
        return this.zzaw;
    }

    public final boolean zzq() {
        return (this.zzaw == null || this.zzaw.isEmpty()) ? false : true;
    }
}
