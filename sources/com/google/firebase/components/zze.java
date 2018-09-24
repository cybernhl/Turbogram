package com.google.firebase.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.firebase:firebase-common@@16.0.1 */
final class zze {

    /* compiled from: com.google.firebase:firebase-common@@16.0.1 */
    static class zza {
        private final Component<?> zza;
        private final Set<zza> zzb = new HashSet();
        private final Set<zza> zzc = new HashSet();

        zza(Component<?> component) {
            this.zza = component;
        }

        final void zza(zza zza) {
            this.zzb.add(zza);
        }

        final void zzb(zza zza) {
            this.zzc.add(zza);
        }

        final Set<zza> zza() {
            return this.zzb;
        }

        final void zzc(zza zza) {
            this.zzc.remove(zza);
        }

        final Component<?> zzb() {
            return this.zza;
        }

        final boolean zzc() {
            return this.zzc.isEmpty();
        }

        final boolean zzd() {
            return this.zzb.isEmpty();
        }
    }

    private static Set<zza> zza(Set<zza> set) {
        Set<zza> hashSet = new HashSet();
        for (zza zza : set) {
            if (zza.zzc()) {
                hashSet.add(zza);
            }
        }
        return hashSet;
    }

    static List<Component<?>> zza(List<Component<?>> list) {
        zza zza;
        Map hashMap = new HashMap(list.size());
        for (Component component : list) {
            zza zza2 = new zza(component);
            for (Class put : component.zza()) {
                if (hashMap.put(put, zza2) != null) {
                    throw new IllegalArgumentException(String.format("Multiple components provide %s.", new Object[]{(Class) r4.next()}));
                }
            }
        }
        for (zza zza3 : hashMap.values()) {
            for (Dependency dependency : zza3.zzb().zzb()) {
                zza zza4;
                if (dependency.zzc()) {
                    zza4 = (zza) hashMap.get(dependency.zza());
                    if (zza4 != null) {
                        zza3.zza(zza4);
                        zza4.zzb(zza3);
                    }
                }
            }
        }
        Set<zza> hashSet = new HashSet(hashMap.values());
        Set zza5 = zza((Set) hashSet);
        List<Component<?>> arrayList = new ArrayList();
        while (!zza5.isEmpty()) {
            zza3 = (zza) zza5.iterator().next();
            zza5.remove(zza3);
            arrayList.add(zza3.zzb());
            for (zza zza42 : zza3.zza()) {
                zza42.zzc(zza3);
                if (zza42.zzc()) {
                    zza5.add(zza42);
                }
            }
        }
        if (arrayList.size() == list.size()) {
            Collections.reverse(arrayList);
            return arrayList;
        }
        List arrayList2 = new ArrayList();
        for (zza zza32 : hashSet) {
            if (!(zza32.zzc() || zza32.zzd())) {
                arrayList2.add(zza32.zzb());
            }
        }
        throw new DependencyCycleException(arrayList2);
    }
}
