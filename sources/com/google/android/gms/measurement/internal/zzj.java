package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzfv;
import com.google.android.gms.internal.measurement.zzfw;
import com.google.android.gms.internal.measurement.zzfx;
import com.google.android.gms.internal.measurement.zzfy;
import com.google.android.gms.internal.measurement.zzfz;
import com.google.android.gms.internal.measurement.zzgd;
import com.google.android.gms.internal.measurement.zzge;
import com.google.android.gms.internal.measurement.zzgf;
import com.google.android.gms.internal.measurement.zzgg;
import com.google.android.gms.internal.measurement.zzgj;
import com.google.android.gms.internal.measurement.zzgk;
import com.google.android.gms.internal.measurement.zzgl;
import com.google.android.gms.internal.measurement.zzyy;
import com.google.android.gms.internal.measurement.zzzg;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

final class zzj extends zzez {
    zzj(zzfa zzfa) {
        super(zzfa);
    }

    protected final boolean zzgt() {
        return false;
    }

    @WorkerThread
    final zzgd[] zza(String str, zzgf[] zzgfArr, zzgl[] zzglArr) {
        BitSet bitSet;
        BitSet bitSet2;
        Map arrayMap;
        int i;
        Map map;
        int i2;
        Object obj;
        zzgd zzgd;
        Long l;
        zzco zzjq;
        int i3;
        int length;
        Map map2;
        zzgd zzgd2;
        ArrayMap arrayMap2;
        ArrayMap arrayMap3;
        Preconditions.checkNotEmpty(str);
        HashSet hashSet = new HashSet();
        ArrayMap arrayMap4 = new ArrayMap();
        Map arrayMap5 = new ArrayMap();
        ArrayMap arrayMap6 = new ArrayMap();
        ArrayMap arrayMap7 = new ArrayMap();
        ArrayMap arrayMap8 = new ArrayMap();
        boolean zzd = zzgq().zzd(str, zzaf.zzakw);
        Map zzbo = zzjq().zzbo(str);
        if (zzbo != null) {
            for (Integer intValue : zzbo.keySet()) {
                int intValue2 = intValue.intValue();
                zzgj zzgj = (zzgj) zzbo.get(Integer.valueOf(intValue2));
                bitSet = (BitSet) arrayMap5.get(Integer.valueOf(intValue2));
                bitSet2 = (BitSet) arrayMap6.get(Integer.valueOf(intValue2));
                if (zzd) {
                    arrayMap = new ArrayMap();
                    if (!(zzgj == null || zzgj.zzayg == null)) {
                        for (zzge zzge : zzgj.zzayg) {
                            if (zzge.zzawq != null) {
                                arrayMap.put(zzge.zzawq, zzge.zzawr);
                            }
                        }
                    }
                    arrayMap7.put(Integer.valueOf(intValue2), arrayMap);
                    map = arrayMap;
                } else {
                    map = null;
                }
                if (bitSet == null) {
                    bitSet = new BitSet();
                    arrayMap5.put(Integer.valueOf(intValue2), bitSet);
                    bitSet2 = new BitSet();
                    arrayMap6.put(Integer.valueOf(intValue2), bitSet2);
                }
                for (i2 = 0; i2 < (zzgj.zzaye.length << 6); i2++) {
                    obj = null;
                    if (zzfg.zza(zzgj.zzaye, i2)) {
                        zzgo().zzjl().zze("Filter already evaluated. audience ID, filter ID", Integer.valueOf(intValue2), Integer.valueOf(i2));
                        bitSet2.set(i2);
                        if (zzfg.zza(zzgj.zzayf, i2)) {
                            bitSet.set(i2);
                            obj = 1;
                        }
                    }
                    if (map != null && r6 == null) {
                        map.remove(Integer.valueOf(i2));
                    }
                }
                zzgd = new zzgd();
                arrayMap4.put(Integer.valueOf(intValue2), zzgd);
                zzgd.zzawo = Boolean.valueOf(false);
                zzgd.zzawn = zzgj;
                zzgd.zzawm = new zzgj();
                zzgd.zzawm.zzayf = zzfg.zza(bitSet);
                zzgd.zzawm.zzaye = zzfg.zza(bitSet2);
                if (zzd) {
                    zzgd.zzawm.zzayg = zzd(map);
                    arrayMap8.put(Integer.valueOf(intValue2), new ArrayMap());
                }
            }
        }
        if (zzgfArr != null) {
            zzgf zzgf = null;
            long j = 0;
            l = null;
            ArrayMap arrayMap9 = new ArrayMap();
            for (zzgf zzgf2 : zzgfArr) {
                zzgg[] zzggArr;
                String str2;
                Long l2;
                long j2;
                zzgf zzgf3;
                zzz zzg;
                zzz zzz;
                Map map3;
                int intValue3;
                BitSet bitSet3;
                Map map4;
                BitSet bitSet4;
                String str3 = zzgf2.name;
                zzgg[] zzggArr2 = zzgf2.zzawt;
                if (zzgq().zzd(str, zzaf.zzakq)) {
                    zzjo();
                    Long l3 = (Long) zzfg.zzb(zzgf2, "_eid");
                    Object obj2 = l3 != null ? 1 : null;
                    Object obj3 = (obj2 == null || !str3.equals("_ep")) ? null : 1;
                    if (obj3 != null) {
                        zzjo();
                        str3 = (String) zzfg.zzb(zzgf2, "_en");
                        if (TextUtils.isEmpty(str3)) {
                            zzgo().zzjd().zzg("Extra parameter without an event name. eventId", l3);
                        } else {
                            Long l4;
                            int i4;
                            if (zzgf == null || l == null || l3.longValue() != l.longValue()) {
                                Pair zza = zzjq().zza(str, l3);
                                if (zza == null || zza.first == null) {
                                    zzgo().zzjd().zze("Extra parameter without existing main event. eventName, eventId", str3, l3);
                                } else {
                                    zzgf zzgf4 = (zzgf) zza.first;
                                    j = ((Long) zza.second).longValue();
                                    zzjo();
                                    l4 = (Long) zzfg.zzb(zzgf4, "_eid");
                                    zzgf = zzgf4;
                                }
                            } else {
                                l4 = l;
                            }
                            j--;
                            if (j <= 0) {
                                zzjq = zzjq();
                                zzjq.zzaf();
                                zzjq.zzgo().zzjl().zzg("Clearing complex main event info. appId", str);
                                try {
                                    zzjq.getWritableDatabase().execSQL("delete from main_event_params where app_id=?", new String[]{str});
                                } catch (SQLiteException e) {
                                    zzjq.zzgo().zzjd().zzg("Error clearing complex main event", e);
                                }
                            } else {
                                zzjq().zza(str, l3, j, zzgf);
                            }
                            zzgg[] zzggArr3 = new zzgg[(zzgf.zzawt.length + zzggArr2.length)];
                            i3 = 0;
                            zzgg[] zzggArr4 = zzgf.zzawt;
                            int length2 = zzggArr4.length;
                            i2 = 0;
                            while (i2 < length2) {
                                zzgg zzgg = zzggArr4[i2];
                                zzjo();
                                if (zzfg.zza(zzgf2, zzgg.name) == null) {
                                    i4 = i3 + 1;
                                    zzggArr3[i3] = zzgg;
                                } else {
                                    i4 = i3;
                                }
                                i2++;
                                i3 = i4;
                            }
                            if (i3 > 0) {
                                zzgg[] zzggArr5;
                                length = zzggArr2.length;
                                i4 = 0;
                                while (i4 < length) {
                                    i2 = i3 + 1;
                                    zzggArr3[i3] = zzggArr2[i4];
                                    i4++;
                                    i3 = i2;
                                }
                                if (i3 == zzggArr3.length) {
                                    zzggArr5 = zzggArr3;
                                } else {
                                    zzggArr5 = (zzgg[]) Arrays.copyOf(zzggArr3, i3);
                                }
                                zzggArr = zzggArr5;
                                str2 = str3;
                                l2 = l4;
                                j2 = j;
                                zzgf3 = zzgf;
                            } else {
                                zzgo().zzjg().zzg("No unique parameters in main event. eventName", str3);
                                zzggArr = zzggArr2;
                                str2 = str3;
                                l2 = l4;
                                j2 = j;
                                zzgf3 = zzgf;
                            }
                        }
                    } else if (obj2 != null) {
                        zzjo();
                        Long valueOf = Long.valueOf(0);
                        l = zzfg.zzb(zzgf2, "_epc");
                        if (l != null) {
                            valueOf = l;
                        }
                        j = valueOf.longValue();
                        if (j <= 0) {
                            zzgo().zzjg().zzg("Complex event with zero extra param count. eventName", str3);
                            zzggArr = zzggArr2;
                            str2 = str3;
                            l2 = l3;
                            j2 = j;
                            zzgf3 = zzgf2;
                        } else {
                            zzjq().zza(str, l3, j, zzgf2);
                            zzggArr = zzggArr2;
                            str2 = str3;
                            l2 = l3;
                            j2 = j;
                            zzgf3 = zzgf2;
                        }
                    }
                    zzg = zzjq().zzg(str, zzgf2.name);
                    if (zzg != null) {
                        zzgo().zzjg().zze("Event aggregate wasn't created during raw event logging. appId, event", zzap.zzbv(str), zzgl().zzbs(str2));
                        zzz = new zzz(str, zzgf2.name, 1, 1, zzgf2.zzawu.longValue(), 0, null, null, null, null);
                    } else {
                        zzz = zzg.zziu();
                    }
                    zzjq().zza(zzz);
                    j = zzz.zzaie;
                    map2 = (Map) arrayMap9.get(str2);
                    if (map2 != null) {
                        map2 = zzjq().zzl(str, str2);
                        if (map2 == null) {
                            map2 = new ArrayMap();
                        }
                        arrayMap9.put(str2, map2);
                        map3 = map2;
                    } else {
                        map3 = map2;
                    }
                    for (Integer intValue4 : r9.keySet()) {
                        intValue3 = intValue4.intValue();
                        if (hashSet.contains(Integer.valueOf(intValue3))) {
                            zzgd2 = (zzgd) arrayMap4.get(Integer.valueOf(intValue3));
                            bitSet = (BitSet) arrayMap5.get(Integer.valueOf(intValue3));
                            bitSet2 = (BitSet) arrayMap6.get(Integer.valueOf(intValue3));
                            arrayMap = null;
                            zzbo = null;
                            if (zzd) {
                                arrayMap = (Map) arrayMap7.get(Integer.valueOf(intValue3));
                                zzbo = (Map) arrayMap8.get(Integer.valueOf(intValue3));
                            }
                            if (zzgd2 != null) {
                                zzgd2 = new zzgd();
                                arrayMap4.put(Integer.valueOf(intValue3), zzgd2);
                                zzgd2.zzawo = Boolean.valueOf(true);
                                bitSet3 = new BitSet();
                                arrayMap5.put(Integer.valueOf(intValue3), bitSet3);
                                bitSet2 = new BitSet();
                                arrayMap6.put(Integer.valueOf(intValue3), bitSet2);
                                if (zzd) {
                                    map4 = arrayMap;
                                    bitSet4 = bitSet2;
                                } else {
                                    arrayMap2 = new ArrayMap();
                                    arrayMap7.put(Integer.valueOf(intValue3), arrayMap2);
                                    arrayMap3 = new ArrayMap();
                                    arrayMap8.put(Integer.valueOf(intValue3), arrayMap3);
                                    zzbo = arrayMap3;
                                    map4 = arrayMap2;
                                    bitSet4 = bitSet2;
                                }
                            } else {
                                map4 = arrayMap;
                                bitSet4 = bitSet2;
                                bitSet3 = bitSet;
                            }
                            for (zzfv zzfv : (List) r9.get(Integer.valueOf(intValue3))) {
                                if (zzgo().isLoggable(2)) {
                                    zzgo().zzjl().zzd("Evaluating filter. audience, filter, event", Integer.valueOf(intValue3), zzfv.zzave, zzgl().zzbs(zzfv.zzavf));
                                    zzgo().zzjl().zzg("Filter definition", zzjo().zza(zzfv));
                                }
                                if (zzfv.zzave != null || zzfv.zzave.intValue() > 256) {
                                    zzgo().zzjg().zze("Invalid event filter ID. appId, id", zzap.zzbv(str), String.valueOf(zzfv.zzave));
                                } else if (zzd) {
                                    Object obj4 = (zzfv == null || zzfv.zzavb == null || !zzfv.zzavb.booleanValue()) ? null : 1;
                                    Object obj5 = (zzfv == null || zzfv.zzavc == null || !zzfv.zzavc.booleanValue()) ? null : 1;
                                    if (bitSet3.get(zzfv.zzave.intValue()) && obj4 == null && obj5 == null) {
                                        zzgo().zzjl().zze("Event filter already evaluated true and it is not associated with a dynamic audience. audience ID, filter ID", Integer.valueOf(intValue3), zzfv.zzave);
                                    } else {
                                        r4 = zza(zzfv, str2, zzggArr, j);
                                        r5 = zzgo().zzjl();
                                        String str4 = "Event filter result";
                                        if (r4 == null) {
                                            obj3 = "null";
                                        } else {
                                            r2 = r4;
                                        }
                                        r5.zzg(str4, obj3);
                                        if (r4 == null) {
                                            hashSet.add(Integer.valueOf(intValue3));
                                        } else {
                                            bitSet4.set(zzfv.zzave.intValue());
                                            if (r4.booleanValue()) {
                                                bitSet3.set(zzfv.zzave.intValue());
                                                if (!((obj4 == null && obj5 == null) || zzgf2.zzawu == null)) {
                                                    if (obj5 != null) {
                                                        zzb(zzbo, zzfv.zzave.intValue(), zzgf2.zzawu.longValue());
                                                    } else {
                                                        zza(map4, zzfv.zzave.intValue(), zzgf2.zzawu.longValue());
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else if (bitSet3.get(zzfv.zzave.intValue())) {
                                    zzgo().zzjl().zze("Event filter already evaluated true. audience ID, filter ID", Integer.valueOf(intValue3), zzfv.zzave);
                                } else {
                                    r4 = zza(zzfv, str2, zzggArr, j);
                                    r5 = zzgo().zzjl();
                                    String str5 = "Event filter result";
                                    if (r4 == null) {
                                        obj3 = "null";
                                    } else {
                                        r2 = r4;
                                    }
                                    r5.zzg(str5, obj3);
                                    if (r4 == null) {
                                        hashSet.add(Integer.valueOf(intValue3));
                                    } else {
                                        bitSet4.set(zzfv.zzave.intValue());
                                        if (r4.booleanValue()) {
                                            bitSet3.set(zzfv.zzave.intValue());
                                        }
                                    }
                                }
                            }
                        } else {
                            zzgo().zzjl().zzg("Skipping failed audience ID", Integer.valueOf(intValue3));
                        }
                    }
                    l = l2;
                    j = j2;
                    zzgf = zzgf3;
                }
                zzggArr = zzggArr2;
                str2 = str3;
                l2 = l;
                j2 = j;
                zzgf3 = zzgf;
                zzg = zzjq().zzg(str, zzgf2.name);
                if (zzg != null) {
                    zzz = zzg.zziu();
                } else {
                    zzgo().zzjg().zze("Event aggregate wasn't created during raw event logging. appId, event", zzap.zzbv(str), zzgl().zzbs(str2));
                    zzz = new zzz(str, zzgf2.name, 1, 1, zzgf2.zzawu.longValue(), 0, null, null, null, null);
                }
                zzjq().zza(zzz);
                j = zzz.zzaie;
                map2 = (Map) arrayMap9.get(str2);
                if (map2 != null) {
                    map3 = map2;
                } else {
                    map2 = zzjq().zzl(str, str2);
                    if (map2 == null) {
                        map2 = new ArrayMap();
                    }
                    arrayMap9.put(str2, map2);
                    map3 = map2;
                }
                while (r15.hasNext()) {
                    intValue3 = intValue4.intValue();
                    if (hashSet.contains(Integer.valueOf(intValue3))) {
                        zzgd2 = (zzgd) arrayMap4.get(Integer.valueOf(intValue3));
                        bitSet = (BitSet) arrayMap5.get(Integer.valueOf(intValue3));
                        bitSet2 = (BitSet) arrayMap6.get(Integer.valueOf(intValue3));
                        arrayMap = null;
                        zzbo = null;
                        if (zzd) {
                            arrayMap = (Map) arrayMap7.get(Integer.valueOf(intValue3));
                            zzbo = (Map) arrayMap8.get(Integer.valueOf(intValue3));
                        }
                        if (zzgd2 != null) {
                            map4 = arrayMap;
                            bitSet4 = bitSet2;
                            bitSet3 = bitSet;
                        } else {
                            zzgd2 = new zzgd();
                            arrayMap4.put(Integer.valueOf(intValue3), zzgd2);
                            zzgd2.zzawo = Boolean.valueOf(true);
                            bitSet3 = new BitSet();
                            arrayMap5.put(Integer.valueOf(intValue3), bitSet3);
                            bitSet2 = new BitSet();
                            arrayMap6.put(Integer.valueOf(intValue3), bitSet2);
                            if (zzd) {
                                map4 = arrayMap;
                                bitSet4 = bitSet2;
                            } else {
                                arrayMap2 = new ArrayMap();
                                arrayMap7.put(Integer.valueOf(intValue3), arrayMap2);
                                arrayMap3 = new ArrayMap();
                                arrayMap8.put(Integer.valueOf(intValue3), arrayMap3);
                                zzbo = arrayMap3;
                                map4 = arrayMap2;
                                bitSet4 = bitSet2;
                            }
                        }
                        for (zzfv zzfv2 : (List) r9.get(Integer.valueOf(intValue3))) {
                            if (zzgo().isLoggable(2)) {
                                zzgo().zzjl().zzd("Evaluating filter. audience, filter, event", Integer.valueOf(intValue3), zzfv2.zzave, zzgl().zzbs(zzfv2.zzavf));
                                zzgo().zzjl().zzg("Filter definition", zzjo().zza(zzfv2));
                            }
                            if (zzfv2.zzave != null) {
                            }
                            zzgo().zzjg().zze("Invalid event filter ID. appId, id", zzap.zzbv(str), String.valueOf(zzfv2.zzave));
                        }
                    } else {
                        zzgo().zzjl().zzg("Skipping failed audience ID", Integer.valueOf(intValue3));
                    }
                }
                l = l2;
                j = j2;
                zzgf = zzgf3;
            }
        }
        if (zzglArr != null) {
            Map arrayMap10 = new ArrayMap();
            for (zzgl zzgl : zzglArr) {
                map2 = (Map) arrayMap10.get(zzgl.name);
                if (map2 == null) {
                    map2 = zzjq().zzm(str, zzgl.name);
                    if (map2 == null) {
                        map2 = new ArrayMap();
                    }
                    arrayMap10.put(zzgl.name, map2);
                    map = map2;
                } else {
                    map = map2;
                }
                for (Integer intValue42 : r7.keySet()) {
                    int intValue5 = intValue42.intValue();
                    if (hashSet.contains(Integer.valueOf(intValue5))) {
                        zzgo().zzjl().zzg("Skipping failed audience ID", Integer.valueOf(intValue5));
                    } else {
                        BitSet bitSet5;
                        zzgd2 = (zzgd) arrayMap4.get(Integer.valueOf(intValue5));
                        bitSet = (BitSet) arrayMap5.get(Integer.valueOf(intValue5));
                        bitSet2 = (BitSet) arrayMap6.get(Integer.valueOf(intValue5));
                        zzbo = null;
                        Map map5 = null;
                        if (zzd) {
                            map5 = (Map) arrayMap8.get(Integer.valueOf(intValue5));
                            zzbo = (Map) arrayMap7.get(Integer.valueOf(intValue5));
                        }
                        if (zzgd2 == null) {
                            zzgd2 = new zzgd();
                            arrayMap4.put(Integer.valueOf(intValue5), zzgd2);
                            zzgd2.zzawo = Boolean.valueOf(true);
                            bitSet5 = new BitSet();
                            arrayMap5.put(Integer.valueOf(intValue5), bitSet5);
                            bitSet2 = new BitSet();
                            arrayMap6.put(Integer.valueOf(intValue5), bitSet2);
                            if (zzd) {
                                arrayMap2 = new ArrayMap();
                                arrayMap7.put(Integer.valueOf(intValue5), arrayMap2);
                                arrayMap3 = new ArrayMap();
                                arrayMap8.put(Integer.valueOf(intValue5), arrayMap3);
                                arrayMap = arrayMap3;
                                zzbo = arrayMap2;
                            } else {
                                arrayMap = map5;
                            }
                        } else {
                            arrayMap = map5;
                            bitSet5 = bitSet;
                        }
                        for (zzfy zzfy : (List) r7.get(Integer.valueOf(intValue5))) {
                            if (zzgo().isLoggable(2)) {
                                zzgo().zzjl().zzd("Evaluating filter. audience, filter, property", Integer.valueOf(intValue5), zzfy.zzave, zzgl().zzbu(zzfy.zzavu));
                                zzgo().zzjl().zzg("Filter definition", zzjo().zza(zzfy));
                            }
                            if (zzfy.zzave == null || zzfy.zzave.intValue() > 256) {
                                zzgo().zzjg().zze("Invalid property filter ID. appId, id", zzap.zzbv(str), String.valueOf(zzfy.zzave));
                                hashSet.add(Integer.valueOf(intValue5));
                                break;
                            } else if (zzd) {
                                Object obj6 = (zzfy == null || zzfy.zzavb == null || !zzfy.zzavb.booleanValue()) ? null : 1;
                                r3 = (zzfy == null || zzfy.zzavc == null || !zzfy.zzavc.booleanValue()) ? null : 1;
                                if (bitSet5.get(zzfy.zzave.intValue()) && obj6 == null && r3 == null) {
                                    zzgo().zzjl().zze("Property filter already evaluated true and it is not associated with a dynamic audience. audience ID, filter ID", Integer.valueOf(intValue5), zzfy.zzave);
                                } else {
                                    Boolean zza2 = zza(zzfy, zzgl);
                                    zzar zzjl = zzgo().zzjl();
                                    String str6 = "Property filter result";
                                    if (zza2 == null) {
                                        obj = "null";
                                    } else {
                                        r6 = zza2;
                                    }
                                    zzjl.zzg(str6, obj);
                                    if (zza2 == null) {
                                        hashSet.add(Integer.valueOf(intValue5));
                                    } else {
                                        bitSet2.set(zzfy.zzave.intValue());
                                        bitSet5.set(zzfy.zzave.intValue(), zza2.booleanValue());
                                        if (zza2.booleanValue() && !((obj6 == null && r3 == null) || zzgl.zzayl == null)) {
                                            if (r3 != null) {
                                                zzb(arrayMap, zzfy.zzave.intValue(), zzgl.zzayl.longValue());
                                            } else {
                                                zza(zzbo, zzfy.zzave.intValue(), zzgl.zzayl.longValue());
                                            }
                                        }
                                    }
                                }
                            } else if (bitSet5.get(zzfy.zzave.intValue())) {
                                zzgo().zzjl().zze("Property filter already evaluated true. audience ID, filter ID", Integer.valueOf(intValue5), zzfy.zzave);
                            } else {
                                r6 = zza(zzfy, zzgl);
                                zzar zzjl2 = zzgo().zzjl();
                                String str7 = "Property filter result";
                                if (r6 == null) {
                                    r3 = "null";
                                } else {
                                    Boolean bool = r6;
                                }
                                zzjl2.zzg(str7, r3);
                                if (r6 == null) {
                                    hashSet.add(Integer.valueOf(intValue5));
                                } else {
                                    bitSet2.set(zzfy.zzave.intValue());
                                    if (r6.booleanValue()) {
                                        bitSet5.set(zzfy.zzave.intValue());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        zzgd[] zzgdArr = new zzgd[arrayMap5.size()];
        i3 = 0;
        for (Integer intValue422 : arrayMap5.keySet()) {
            length = intValue422.intValue();
            if (!hashSet.contains(Integer.valueOf(length))) {
                zzgd2 = (zzgd) arrayMap4.get(Integer.valueOf(length));
                if (zzgd2 == null) {
                    zzgd = new zzgd();
                } else {
                    zzgd = zzgd2;
                }
                int i5 = i3 + 1;
                zzgdArr[i3] = zzgd;
                zzgd.zzauy = Integer.valueOf(length);
                zzgd.zzawm = new zzgj();
                zzgd.zzawm.zzayf = zzfg.zza((BitSet) arrayMap5.get(Integer.valueOf(length)));
                zzgd.zzawm.zzaye = zzfg.zza((BitSet) arrayMap6.get(Integer.valueOf(length)));
                if (zzd) {
                    zzgk[] zzgkArr;
                    zzgd.zzawm.zzayg = zzd((Map) arrayMap7.get(Integer.valueOf(length)));
                    zzgj zzgj2 = zzgd.zzawm;
                    map2 = (Map) arrayMap8.get(Integer.valueOf(length));
                    if (map2 == null) {
                        zzgkArr = new zzgk[0];
                    } else {
                        zzgk[] zzgkArr2 = new zzgk[map2.size()];
                        i = 0;
                        for (Integer num : map2.keySet()) {
                            zzgk zzgk = new zzgk();
                            zzgk.zzawq = num;
                            List<Long> list = (List) map2.get(num);
                            if (list != null) {
                                Collections.sort(list);
                                long[] jArr = new long[list.size()];
                                int i6 = 0;
                                for (Long l5 : list) {
                                    int i7 = i6 + 1;
                                    jArr[i6] = l5.longValue();
                                    i6 = i7;
                                }
                                zzgk.zzayj = jArr;
                            }
                            i3 = i + 1;
                            zzgkArr2[i] = zzgk;
                            i = i3;
                        }
                        zzgkArr = zzgkArr2;
                    }
                    zzgj2.zzayh = zzgkArr;
                }
                zzjq = zzjq();
                zzzg zzzg = zzgd.zzawm;
                zzjq.zzcl();
                zzjq.zzaf();
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotNull(zzzg);
                try {
                    byte[] bArr = new byte[zzzg.zzvu()];
                    zzyy zzk = zzyy.zzk(bArr, 0, bArr.length);
                    zzzg.zza(zzk);
                    zzk.zzyt();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("app_id", str);
                    contentValues.put("audience_id", Integer.valueOf(length));
                    contentValues.put("current_results", bArr);
                    try {
                        if (zzjq.getWritableDatabase().insertWithOnConflict("audience_filter_values", null, contentValues, 5) == -1) {
                            zzjq.zzgo().zzjd().zzg("Failed to insert filter results (got -1). appId", zzap.zzbv(str));
                        }
                        i3 = i5;
                    } catch (SQLiteException e2) {
                        zzjq.zzgo().zzjd().zze("Error storing filter results. appId", zzap.zzbv(str), e2);
                        i3 = i5;
                    }
                } catch (IOException e3) {
                    zzjq.zzgo().zzjd().zze("Configuration loss. Failed to serialize filter results. appId", zzap.zzbv(str), e3);
                    i3 = i5;
                }
            }
        }
        return (zzgd[]) Arrays.copyOf(zzgdArr, i3);
    }

    private final Boolean zza(zzfv zzfv, String str, zzgg[] zzggArr, long j) {
        Boolean zza;
        if (zzfv.zzavi != null) {
            zza = zza(j, zzfv.zzavi);
            if (zza == null) {
                return null;
            }
            if (!zza.booleanValue()) {
                return Boolean.valueOf(false);
            }
        }
        Set hashSet = new HashSet();
        for (zzfw zzfw : zzfv.zzavg) {
            if (TextUtils.isEmpty(zzfw.zzavn)) {
                zzgo().zzjg().zzg("null or empty param name in filter. event", zzgl().zzbs(str));
                return null;
            }
            hashSet.add(zzfw.zzavn);
        }
        Map arrayMap = new ArrayMap();
        for (zzgg zzgg : zzggArr) {
            if (hashSet.contains(zzgg.name)) {
                if (zzgg.zzawx != null) {
                    arrayMap.put(zzgg.name, zzgg.zzawx);
                } else if (zzgg.zzauh != null) {
                    arrayMap.put(zzgg.name, zzgg.zzauh);
                } else if (zzgg.zzamp != null) {
                    arrayMap.put(zzgg.name, zzgg.zzamp);
                } else {
                    zzgo().zzjg().zze("Unknown value for param. event, param", zzgl().zzbs(str), zzgl().zzbt(zzgg.name));
                    return null;
                }
            }
        }
        for (zzfw zzfw2 : zzfv.zzavg) {
            boolean equals = Boolean.TRUE.equals(zzfw2.zzavm);
            String str2 = zzfw2.zzavn;
            if (TextUtils.isEmpty(str2)) {
                zzgo().zzjg().zzg("Event has empty param name. event", zzgl().zzbs(str));
                return null;
            }
            Object obj = arrayMap.get(str2);
            if (obj instanceof Long) {
                if (zzfw2.zzavl == null) {
                    zzgo().zzjg().zze("No number filter for long param. event, param", zzgl().zzbs(str), zzgl().zzbt(str2));
                    return null;
                }
                zza = zza(((Long) obj).longValue(), zzfw2.zzavl);
                if (zza == null) {
                    return null;
                }
                if (((!zza.booleanValue() ? 1 : 0) ^ equals) != 0) {
                    return Boolean.valueOf(false);
                }
            } else if (obj instanceof Double) {
                if (zzfw2.zzavl == null) {
                    zzgo().zzjg().zze("No number filter for double param. event, param", zzgl().zzbs(str), zzgl().zzbt(str2));
                    return null;
                }
                zza = zza(((Double) obj).doubleValue(), zzfw2.zzavl);
                if (zza == null) {
                    return null;
                }
                if (((!zza.booleanValue() ? 1 : 0) ^ equals) != 0) {
                    return Boolean.valueOf(false);
                }
            } else if (obj instanceof String) {
                if (zzfw2.zzavk != null) {
                    zza = zza((String) obj, zzfw2.zzavk);
                } else if (zzfw2.zzavl == null) {
                    zzgo().zzjg().zze("No filter for String param. event, param", zzgl().zzbs(str), zzgl().zzbt(str2));
                    return null;
                } else if (zzfg.zzcp((String) obj)) {
                    zza = zza((String) obj, zzfw2.zzavl);
                } else {
                    zzgo().zzjg().zze("Invalid param value for number filter. event, param", zzgl().zzbs(str), zzgl().zzbt(str2));
                    return null;
                }
                if (zza == null) {
                    return null;
                }
                if (((!zza.booleanValue() ? 1 : 0) ^ equals) != 0) {
                    return Boolean.valueOf(false);
                }
            } else if (obj == null) {
                zzgo().zzjl().zze("Missing param for filter. event, param", zzgl().zzbs(str), zzgl().zzbt(str2));
                return Boolean.valueOf(false);
            } else {
                zzgo().zzjg().zze("Unknown param type. event, param", zzgl().zzbs(str), zzgl().zzbt(str2));
                return null;
            }
        }
        return Boolean.valueOf(true);
    }

    private final Boolean zza(zzfy zzfy, zzgl zzgl) {
        zzfw zzfw = zzfy.zzavv;
        if (zzfw == null) {
            zzgo().zzjg().zzg("Missing property filter. property", zzgl().zzbu(zzgl.name));
            return null;
        }
        boolean equals = Boolean.TRUE.equals(zzfw.zzavm);
        if (zzgl.zzawx != null) {
            if (zzfw.zzavl != null) {
                return zza(zza(zzgl.zzawx.longValue(), zzfw.zzavl), equals);
            }
            zzgo().zzjg().zzg("No number filter for long property. property", zzgl().zzbu(zzgl.name));
            return null;
        } else if (zzgl.zzauh != null) {
            if (zzfw.zzavl != null) {
                return zza(zza(zzgl.zzauh.doubleValue(), zzfw.zzavl), equals);
            }
            zzgo().zzjg().zzg("No number filter for double property. property", zzgl().zzbu(zzgl.name));
            return null;
        } else if (zzgl.zzamp == null) {
            zzgo().zzjg().zzg("User property has no value, property", zzgl().zzbu(zzgl.name));
            return null;
        } else if (zzfw.zzavk != null) {
            return zza(zza(zzgl.zzamp, zzfw.zzavk), equals);
        } else {
            if (zzfw.zzavl == null) {
                zzgo().zzjg().zzg("No string or number filter defined. property", zzgl().zzbu(zzgl.name));
                return null;
            } else if (zzfg.zzcp(zzgl.zzamp)) {
                return zza(zza(zzgl.zzamp, zzfw.zzavl), equals);
            } else {
                zzgo().zzjg().zze("Invalid user property value for Numeric number filter. property, value", zzgl().zzbu(zzgl.name), zzgl.zzamp);
                return null;
            }
        }
    }

    @VisibleForTesting
    private static Boolean zza(Boolean bool, boolean z) {
        if (bool == null) {
            return null;
        }
        return Boolean.valueOf(bool.booleanValue() ^ z);
    }

    @VisibleForTesting
    private final Boolean zza(String str, zzfz zzfz) {
        int i = 0;
        String str2 = null;
        Preconditions.checkNotNull(zzfz);
        if (str == null || zzfz.zzavw == null || zzfz.zzavw.intValue() == 0) {
            return null;
        }
        boolean z;
        String str3;
        List list;
        if (zzfz.zzavw.intValue() == 6) {
            if (zzfz.zzavz == null || zzfz.zzavz.length == 0) {
                return null;
            }
        } else if (zzfz.zzavx == null) {
            return null;
        }
        int intValue = zzfz.zzavw.intValue();
        if (zzfz.zzavy == null || !zzfz.zzavy.booleanValue()) {
            z = false;
        } else {
            z = true;
        }
        if (z || intValue == 1 || intValue == 6) {
            str3 = zzfz.zzavx;
        } else {
            str3 = zzfz.zzavx.toUpperCase(Locale.ENGLISH);
        }
        if (zzfz.zzavz == null) {
            list = null;
        } else {
            String[] strArr = zzfz.zzavz;
            if (z) {
                list = Arrays.asList(strArr);
            } else {
                list = new ArrayList();
                int length = strArr.length;
                while (i < length) {
                    list.add(strArr[i].toUpperCase(Locale.ENGLISH));
                    i++;
                }
            }
        }
        if (intValue == 1) {
            str2 = str3;
        }
        return zza(str, intValue, z, str3, list, str2);
    }

    private final Boolean zza(String str, int i, boolean z, String str2, List<String> list, String str3) {
        if (str == null) {
            return null;
        }
        if (i == 6) {
            if (list == null || list.size() == 0) {
                return null;
            }
        } else if (str2 == null) {
            return null;
        }
        if (!(z || i == 1)) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (i) {
            case 1:
                try {
                    return Boolean.valueOf(Pattern.compile(str3, z ? 0 : 66).matcher(str).matches());
                } catch (PatternSyntaxException e) {
                    zzgo().zzjg().zzg("Invalid regular expression in REGEXP audience filter. expression", str3);
                    return null;
                }
            case 2:
                return Boolean.valueOf(str.startsWith(str2));
            case 3:
                return Boolean.valueOf(str.endsWith(str2));
            case 4:
                return Boolean.valueOf(str.contains(str2));
            case 5:
                return Boolean.valueOf(str.equals(str2));
            case 6:
                return Boolean.valueOf(list.contains(str));
            default:
                return null;
        }
    }

    private final Boolean zza(long j, zzfx zzfx) {
        try {
            return zza(new BigDecimal(j), zzfx, (double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private final Boolean zza(double d, zzfx zzfx) {
        try {
            return zza(new BigDecimal(d), zzfx, Math.ulp(d));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private final Boolean zza(String str, zzfx zzfx) {
        Boolean bool = null;
        if (zzfg.zzcp(str)) {
            try {
                bool = zza(new BigDecimal(str), zzfx, (double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
            } catch (NumberFormatException e) {
            }
        }
        return bool;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @com.google.android.gms.common.util.VisibleForTesting
    private static java.lang.Boolean zza(java.math.BigDecimal r10, com.google.android.gms.internal.measurement.zzfx r11, double r12) {
        /*
        r8 = 4;
        r7 = -1;
        r1 = 0;
        r0 = 1;
        r2 = 0;
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r11);
        r3 = r11.zzavo;
        if (r3 == 0) goto L_0x0014;
    L_0x000c:
        r3 = r11.zzavo;
        r3 = r3.intValue();
        if (r3 != 0) goto L_0x0016;
    L_0x0014:
        r0 = r2;
    L_0x0015:
        return r0;
    L_0x0016:
        r3 = r11.zzavo;
        r3 = r3.intValue();
        if (r3 != r8) goto L_0x0028;
    L_0x001e:
        r3 = r11.zzavr;
        if (r3 == 0) goto L_0x0026;
    L_0x0022:
        r3 = r11.zzavs;
        if (r3 != 0) goto L_0x002e;
    L_0x0026:
        r0 = r2;
        goto L_0x0015;
    L_0x0028:
        r3 = r11.zzavq;
        if (r3 != 0) goto L_0x002e;
    L_0x002c:
        r0 = r2;
        goto L_0x0015;
    L_0x002e:
        r3 = r11.zzavo;
        r6 = r3.intValue();
        r3 = r11.zzavo;
        r3 = r3.intValue();
        if (r3 != r8) goto L_0x0066;
    L_0x003c:
        r3 = r11.zzavr;
        r3 = com.google.android.gms.measurement.internal.zzfg.zzcp(r3);
        if (r3 == 0) goto L_0x004c;
    L_0x0044:
        r3 = r11.zzavs;
        r3 = com.google.android.gms.measurement.internal.zzfg.zzcp(r3);
        if (r3 != 0) goto L_0x004e;
    L_0x004c:
        r0 = r2;
        goto L_0x0015;
    L_0x004e:
        r4 = new java.math.BigDecimal;	 Catch:{ NumberFormatException -> 0x0063 }
        r3 = r11.zzavr;	 Catch:{ NumberFormatException -> 0x0063 }
        r4.<init>(r3);	 Catch:{ NumberFormatException -> 0x0063 }
        r3 = new java.math.BigDecimal;	 Catch:{ NumberFormatException -> 0x0063 }
        r5 = r11.zzavs;	 Catch:{ NumberFormatException -> 0x0063 }
        r3.<init>(r5);	 Catch:{ NumberFormatException -> 0x0063 }
        r5 = r2;
    L_0x005d:
        if (r6 != r8) goto L_0x007d;
    L_0x005f:
        if (r4 != 0) goto L_0x007f;
    L_0x0061:
        r0 = r2;
        goto L_0x0015;
    L_0x0063:
        r0 = move-exception;
        r0 = r2;
        goto L_0x0015;
    L_0x0066:
        r3 = r11.zzavq;
        r3 = com.google.android.gms.measurement.internal.zzfg.zzcp(r3);
        if (r3 != 0) goto L_0x0070;
    L_0x006e:
        r0 = r2;
        goto L_0x0015;
    L_0x0070:
        r5 = new java.math.BigDecimal;	 Catch:{ NumberFormatException -> 0x007a }
        r3 = r11.zzavq;	 Catch:{ NumberFormatException -> 0x007a }
        r5.<init>(r3);	 Catch:{ NumberFormatException -> 0x007a }
        r3 = r2;
        r4 = r2;
        goto L_0x005d;
    L_0x007a:
        r0 = move-exception;
        r0 = r2;
        goto L_0x0015;
    L_0x007d:
        if (r5 == 0) goto L_0x0082;
    L_0x007f:
        switch(r6) {
            case 1: goto L_0x0084;
            case 2: goto L_0x0091;
            case 3: goto L_0x009f;
            case 4: goto L_0x00ed;
            default: goto L_0x0082;
        };
    L_0x0082:
        r0 = r2;
        goto L_0x0015;
    L_0x0084:
        r2 = r10.compareTo(r5);
        if (r2 != r7) goto L_0x008f;
    L_0x008a:
        r0 = java.lang.Boolean.valueOf(r0);
        goto L_0x0015;
    L_0x008f:
        r0 = r1;
        goto L_0x008a;
    L_0x0091:
        r2 = r10.compareTo(r5);
        if (r2 != r0) goto L_0x009d;
    L_0x0097:
        r0 = java.lang.Boolean.valueOf(r0);
        goto L_0x0015;
    L_0x009d:
        r0 = r1;
        goto L_0x0097;
    L_0x009f:
        r2 = 0;
        r2 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1));
        if (r2 == 0) goto L_0x00df;
    L_0x00a5:
        r2 = new java.math.BigDecimal;
        r2.<init>(r12);
        r3 = new java.math.BigDecimal;
        r4 = 2;
        r3.<init>(r4);
        r2 = r2.multiply(r3);
        r2 = r5.subtract(r2);
        r2 = r10.compareTo(r2);
        if (r2 != r0) goto L_0x00dd;
    L_0x00be:
        r2 = new java.math.BigDecimal;
        r2.<init>(r12);
        r3 = new java.math.BigDecimal;
        r4 = 2;
        r3.<init>(r4);
        r2 = r2.multiply(r3);
        r2 = r5.add(r2);
        r2 = r10.compareTo(r2);
        if (r2 != r7) goto L_0x00dd;
    L_0x00d7:
        r0 = java.lang.Boolean.valueOf(r0);
        goto L_0x0015;
    L_0x00dd:
        r0 = r1;
        goto L_0x00d7;
    L_0x00df:
        r2 = r10.compareTo(r5);
        if (r2 != 0) goto L_0x00eb;
    L_0x00e5:
        r0 = java.lang.Boolean.valueOf(r0);
        goto L_0x0015;
    L_0x00eb:
        r0 = r1;
        goto L_0x00e5;
    L_0x00ed:
        r2 = r10.compareTo(r4);
        if (r2 == r7) goto L_0x00ff;
    L_0x00f3:
        r2 = r10.compareTo(r3);
        if (r2 == r0) goto L_0x00ff;
    L_0x00f9:
        r0 = java.lang.Boolean.valueOf(r0);
        goto L_0x0015;
    L_0x00ff:
        r0 = r1;
        goto L_0x00f9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzj.zza(java.math.BigDecimal, com.google.android.gms.internal.measurement.zzfx, double):java.lang.Boolean");
    }

    private static zzge[] zzd(Map<Integer, Long> map) {
        if (map == null) {
            return null;
        }
        zzge[] zzgeArr = new zzge[map.size()];
        int i = 0;
        for (Integer num : map.keySet()) {
            zzge zzge = new zzge();
            zzge.zzawq = num;
            zzge.zzawr = (Long) map.get(num);
            int i2 = i + 1;
            zzgeArr[i] = zzge;
            i = i2;
        }
        return zzgeArr;
    }

    private static void zza(Map<Integer, Long> map, int i, long j) {
        Long l = (Long) map.get(Integer.valueOf(i));
        long j2 = j / 1000;
        if (l == null || j2 > l.longValue()) {
            map.put(Integer.valueOf(i), Long.valueOf(j2));
        }
    }

    private static void zzb(Map<Integer, List<Long>> map, int i, long j) {
        List list = (List) map.get(Integer.valueOf(i));
        if (list == null) {
            list = new ArrayList();
            map.put(Integer.valueOf(i), list);
        }
        list.add(Long.valueOf(j / 1000));
    }
}
