package android.support.v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class LruCache<K, V> {
    private int createCount;
    private int evictionCount;
    private int hitCount;
    private final LinkedHashMap<K, V> map;
    private int maxSize;
    private int missCount;
    private int putCount;
    private int size;

    public LruCache(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.maxSize = maxSize;
        this.map = new LinkedHashMap(0, 0.75f, true);
    }

    public void resize(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        synchronized (this) {
            this.maxSize = maxSize;
        }
        trimToSize(maxSize);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.Nullable
    public final V get(@android.support.annotation.NonNull K r6) {
        /*
        r5 = this;
        if (r6 != 0) goto L_0x000b;
    L_0x0002:
        r3 = new java.lang.NullPointerException;
        r4 = "key == null";
        r3.<init>(r4);
        throw r3;
    L_0x000b:
        monitor-enter(r5);
        r3 = r5.map;	 Catch:{ all -> 0x002e }
        r1 = r3.get(r6);	 Catch:{ all -> 0x002e }
        if (r1 == 0) goto L_0x001e;
    L_0x0014:
        r3 = r5.hitCount;	 Catch:{ all -> 0x002e }
        r3 = r3 + 1;
        r5.hitCount = r3;	 Catch:{ all -> 0x002e }
        monitor-exit(r5);	 Catch:{ all -> 0x002e }
        r2 = r1;
        r0 = r1;
    L_0x001d:
        return r0;
    L_0x001e:
        r3 = r5.missCount;	 Catch:{ all -> 0x002e }
        r3 = r3 + 1;
        r5.missCount = r3;	 Catch:{ all -> 0x002e }
        monitor-exit(r5);	 Catch:{ all -> 0x002e }
        r0 = r5.create(r6);
        if (r0 != 0) goto L_0x0031;
    L_0x002b:
        r0 = 0;
        r2 = r1;
        goto L_0x001d;
    L_0x002e:
        r3 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x002e }
        throw r3;
    L_0x0031:
        monitor-enter(r5);
        r3 = r5.createCount;	 Catch:{ all -> 0x0059 }
        r3 = r3 + 1;
        r5.createCount = r3;	 Catch:{ all -> 0x0059 }
        r3 = r5.map;	 Catch:{ all -> 0x0059 }
        r1 = r3.put(r6, r0);	 Catch:{ all -> 0x0059 }
        if (r1 == 0) goto L_0x004f;
    L_0x0040:
        r3 = r5.map;	 Catch:{ all -> 0x0059 }
        r3.put(r6, r1);	 Catch:{ all -> 0x0059 }
    L_0x0045:
        monitor-exit(r5);	 Catch:{ all -> 0x0059 }
        if (r1 == 0) goto L_0x005c;
    L_0x0048:
        r3 = 0;
        r5.entryRemoved(r3, r6, r0, r1);
        r2 = r1;
        r0 = r1;
        goto L_0x001d;
    L_0x004f:
        r3 = r5.size;	 Catch:{ all -> 0x0059 }
        r4 = r5.safeSizeOf(r6, r0);	 Catch:{ all -> 0x0059 }
        r3 = r3 + r4;
        r5.size = r3;	 Catch:{ all -> 0x0059 }
        goto L_0x0045;
    L_0x0059:
        r3 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0059 }
        throw r3;
    L_0x005c:
        r3 = r5.maxSize;
        r5.trimToSize(r3);
        r2 = r1;
        goto L_0x001d;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.util.LruCache.get(java.lang.Object):V");
    }

    @Nullable
    public final V put(@NonNull K key, @NonNull V value) {
        if (key == null || value == null) {
            throw new NullPointerException("key == null || value == null");
        }
        V previous;
        synchronized (this) {
            this.putCount++;
            this.size += safeSizeOf(key, value);
            previous = this.map.put(key, value);
            if (previous != null) {
                this.size -= safeSizeOf(key, previous);
            }
        }
        if (previous != null) {
            entryRemoved(false, key, previous, value);
        }
        trimToSize(this.maxSize);
        return previous;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void trimToSize(int r7) {
        /*
        r6 = this;
    L_0x0000:
        monitor-enter(r6);
        r3 = r6.size;	 Catch:{ all -> 0x0033 }
        if (r3 < 0) goto L_0x0011;
    L_0x0005:
        r3 = r6.map;	 Catch:{ all -> 0x0033 }
        r3 = r3.isEmpty();	 Catch:{ all -> 0x0033 }
        if (r3 == 0) goto L_0x0036;
    L_0x000d:
        r3 = r6.size;	 Catch:{ all -> 0x0033 }
        if (r3 == 0) goto L_0x0036;
    L_0x0011:
        r3 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0033 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0033 }
        r4.<init>();	 Catch:{ all -> 0x0033 }
        r5 = r6.getClass();	 Catch:{ all -> 0x0033 }
        r5 = r5.getName();	 Catch:{ all -> 0x0033 }
        r4 = r4.append(r5);	 Catch:{ all -> 0x0033 }
        r5 = ".sizeOf() is reporting inconsistent results!";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0033 }
        r4 = r4.toString();	 Catch:{ all -> 0x0033 }
        r3.<init>(r4);	 Catch:{ all -> 0x0033 }
        throw r3;	 Catch:{ all -> 0x0033 }
    L_0x0033:
        r3 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0033 }
        throw r3;
    L_0x0036:
        r3 = r6.size;	 Catch:{ all -> 0x0033 }
        if (r3 <= r7) goto L_0x0042;
    L_0x003a:
        r3 = r6.map;	 Catch:{ all -> 0x0033 }
        r3 = r3.isEmpty();	 Catch:{ all -> 0x0033 }
        if (r3 == 0) goto L_0x0044;
    L_0x0042:
        monitor-exit(r6);	 Catch:{ all -> 0x0033 }
        return;
    L_0x0044:
        r3 = r6.map;	 Catch:{ all -> 0x0033 }
        r3 = r3.entrySet();	 Catch:{ all -> 0x0033 }
        r3 = r3.iterator();	 Catch:{ all -> 0x0033 }
        r1 = r3.next();	 Catch:{ all -> 0x0033 }
        r1 = (java.util.Map.Entry) r1;	 Catch:{ all -> 0x0033 }
        r0 = r1.getKey();	 Catch:{ all -> 0x0033 }
        r2 = r1.getValue();	 Catch:{ all -> 0x0033 }
        r3 = r6.map;	 Catch:{ all -> 0x0033 }
        r3.remove(r0);	 Catch:{ all -> 0x0033 }
        r3 = r6.size;	 Catch:{ all -> 0x0033 }
        r4 = r6.safeSizeOf(r0, r2);	 Catch:{ all -> 0x0033 }
        r3 = r3 - r4;
        r6.size = r3;	 Catch:{ all -> 0x0033 }
        r3 = r6.evictionCount;	 Catch:{ all -> 0x0033 }
        r3 = r3 + 1;
        r6.evictionCount = r3;	 Catch:{ all -> 0x0033 }
        monitor-exit(r6);	 Catch:{ all -> 0x0033 }
        r3 = 1;
        r4 = 0;
        r6.entryRemoved(r3, r0, r2, r4);
        goto L_0x0000;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.util.LruCache.trimToSize(int):void");
    }

    @Nullable
    public final V remove(@NonNull K key) {
        if (key == null) {
            throw new NullPointerException("key == null");
        }
        V previous;
        synchronized (this) {
            previous = this.map.remove(key);
            if (previous != null) {
                this.size -= safeSizeOf(key, previous);
            }
        }
        if (previous != null) {
            entryRemoved(false, key, previous, null);
        }
        return previous;
    }

    protected void entryRemoved(boolean evicted, @NonNull K k, @NonNull V v, @Nullable V v2) {
    }

    @Nullable
    protected V create(@NonNull K k) {
        return null;
    }

    private int safeSizeOf(K key, V value) {
        int result = sizeOf(key, value);
        if (result >= 0) {
            return result;
        }
        throw new IllegalStateException("Negative size: " + key + "=" + value);
    }

    protected int sizeOf(@NonNull K k, @NonNull V v) {
        return 1;
    }

    public final void evictAll() {
        trimToSize(-1);
    }

    public final synchronized int size() {
        return this.size;
    }

    public final synchronized int maxSize() {
        return this.maxSize;
    }

    public final synchronized int hitCount() {
        return this.hitCount;
    }

    public final synchronized int missCount() {
        return this.missCount;
    }

    public final synchronized int createCount() {
        return this.createCount;
    }

    public final synchronized int putCount() {
        return this.putCount;
    }

    public final synchronized int evictionCount() {
        return this.evictionCount;
    }

    public final synchronized Map<K, V> snapshot() {
        return new LinkedHashMap(this.map);
    }

    public final synchronized String toString() {
        String format;
        int hitPercent = 0;
        synchronized (this) {
            int accesses = this.hitCount + this.missCount;
            if (accesses != 0) {
                hitPercent = (this.hitCount * 100) / accesses;
            }
            format = String.format(Locale.US, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", new Object[]{Integer.valueOf(this.maxSize), Integer.valueOf(this.hitCount), Integer.valueOf(this.missCount), Integer.valueOf(hitPercent)});
        }
        return format;
    }
}
