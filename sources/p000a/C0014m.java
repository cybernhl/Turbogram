package p000a;

import java.io.InterruptedIOException;

/* renamed from: a.m */
public class C0014m {
    /* renamed from: a */
    public static final C0014m f30a = new C00151();
    /* renamed from: b */
    private boolean f31b;
    /* renamed from: c */
    private long f32c;

    /* renamed from: a.m$1 */
    class C00151 extends C0014m {
        C00151() {
        }

        /* renamed from: a */
        public void mo18a() {
        }
    }

    /* renamed from: a */
    public void mo18a() {
        if (Thread.interrupted()) {
            throw new InterruptedIOException("thread interrupted");
        } else if (this.f31b && this.f32c - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }
}
