package p000a;

import java.nio.ByteBuffer;

/* renamed from: a.g */
final class C0010g implements C0003c {
    /* renamed from: a */
    public final C0004a f16a = new C0004a();
    /* renamed from: b */
    public final C0002l f17b;
    /* renamed from: c */
    boolean f18c;

    C0010g(C0002l c0002l) {
        if (c0002l == null) {
            throw new NullPointerException("source == null");
        }
        this.f17b = c0002l;
    }

    /* renamed from: b */
    public long mo3b(C0004a c0004a, long j) {
        if (c0004a == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.f18c) {
            throw new IllegalStateException("closed");
        } else if (this.f16a.f2b == 0 && this.f17b.mo3b(this.f16a, 8192) == -1) {
            return -1;
        } else {
            return this.f16a.mo3b(c0004a, Math.min(j, this.f16a.f2b));
        }
    }

    /* renamed from: b */
    public byte[] mo4b() {
        this.f16a.m6a(this.f17b);
        return this.f16a.mo4b();
    }

    public void close() {
        if (!this.f18c) {
            this.f18c = true;
            this.f17b.close();
            this.f16a.m18c();
        }
    }

    public boolean isOpen() {
        return !this.f18c;
    }

    public int read(ByteBuffer byteBuffer) {
        return (this.f16a.f2b == 0 && this.f17b.mo3b(this.f16a, 8192) == -1) ? -1 : this.f16a.read(byteBuffer);
    }

    public String toString() {
        return "buffer(" + this.f17b + ")";
    }
}
