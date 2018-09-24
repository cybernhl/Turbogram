package com.p001a.p002a.p003a;

import java.util.Collection;
import java.util.Set;

/* renamed from: com.a.a.a.s */
public enum C0242s {
    ALL,
    ANY;

    /* renamed from: a */
    public boolean m558a(Collection<String> collection, Set<String> set) {
        if (this == ANY) {
            for (String contains : collection) {
                if (set.contains(contains)) {
                    return true;
                }
            }
            return false;
        }
        for (String contains2 : collection) {
            if (!set.contains(contains2)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public boolean m559a(String[] strArr, Set<String> set) {
        if (this == ANY) {
            for (Object contains : strArr) {
                if (set.contains(contains)) {
                    return true;
                }
            }
            return false;
        }
        for (Object contains2 : strArr) {
            if (!set.contains(contains2)) {
                return false;
            }
        }
        return true;
    }
}
