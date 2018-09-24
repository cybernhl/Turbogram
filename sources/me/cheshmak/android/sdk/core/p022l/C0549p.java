package me.cheshmak.android.sdk.core.p022l;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/* renamed from: me.cheshmak.android.sdk.core.l.p */
public class C0549p {
    /* renamed from: a */
    private final C0548a f666a;

    /* renamed from: me.cheshmak.android.sdk.core.l.p$a */
    private static final class C0548a extends AbstractQueuedSynchronizer {
        /* renamed from: a */
        public final int f665a;

        C0548a(int i) {
            this.f665a = i;
            setState(this.f665a);
        }

        /* renamed from: a */
        int m1055a() {
            return getState();
        }

        /* renamed from: b */
        public void m1056b() {
            setState(this.f665a);
        }

        public int tryAcquireShared(int i) {
            return getState() == 0 ? 1 : -1;
        }

        public boolean tryReleaseShared(int i) {
            int i2;
            int state;
            do {
                state = getState();
                if (state == 0) {
                    return false;
                }
                i2 = state - 1;
            } while (!compareAndSetState(state, i2));
            return i2 == 0;
        }
    }

    public C0549p(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("count < 0");
        }
        this.f666a = new C0548a(i);
    }

    /* renamed from: a */
    public void m1057a() {
        this.f666a.acquireSharedInterruptibly(1);
    }

    /* renamed from: b */
    public void m1058b() {
        this.f666a.m1056b();
    }

    /* renamed from: c */
    public void m1059c() {
        this.f666a.releaseShared(1);
    }

    public String toString() {
        return super.toString() + "[Count = " + this.f666a.m1055a() + "]";
    }
}
