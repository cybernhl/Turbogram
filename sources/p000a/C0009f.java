package p000a;

import java.nio.ByteBuffer;

/* renamed from: a.f */
final class C0009f implements C0001b {
    /* renamed from: a */
    public final C0004a f13a = new C0004a();
    /* renamed from: b */
    public final C0000k f14b;
    /* renamed from: c */
    boolean f15c;

    C0009f(C0000k c0000k) {
        if (c0000k == null) {
            throw new NullPointerException("sink == null");
        }
        this.f14b = c0000k;
    }

    /* renamed from: a */
    public C0001b m43a() {
        if (this.f15c) {
            throw new IllegalStateException("closed");
        }
        long a = this.f13a.m5a();
        if (a > 0) {
            this.f14b.mo2a(this.f13a, a);
        }
        return this;
    }

    /* renamed from: a */
    public void mo2a(C0004a c0004a, long j) {
        if (this.f15c) {
            throw new IllegalStateException("closed");
        }
        this.f13a.mo2a(c0004a, j);
        m43a();
    }

    /* renamed from: c */
    public C0001b mo5c(byte[] bArr) {
        if (this.f15c) {
            throw new IllegalStateException("closed");
        }
        this.f13a.m12b(bArr);
        return m43a();
    }

    public void close() {
        if (!this.f15c) {
            Throwable th = null;
            try {
                if (this.f13a.f2b > 0) {
                    this.f14b.mo2a(this.f13a, this.f13a.f2b);
                }
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                this.f14b.close();
            } catch (Throwable th3) {
                if (th == null) {
                    th = th3;
                }
            }
            this.f15c = true;
            if (th != null) {
                C0016n.m69a(th);
            }
        }
    }

    public void flush() {
        if (this.f15c) {
            throw new IllegalStateException("closed");
        }
        if (this.f13a.f2b > 0) {
            this.f14b.mo2a(this.f13a, this.f13a.f2b);
        }
        this.f14b.flush();
    }

    public boolean isOpen() {
        return !this.f15c;
    }

    public String toString() {
        return "buffer(" + this.f14b + ")";
    }

    public int write(ByteBuffer byteBuffer) {
        if (this.f15c) {
            throw new IllegalStateException("closed");
        }
        int write = this.f13a.write(byteBuffer);
        m43a();
        return write;
    }
}
