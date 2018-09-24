package p000a;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

/* renamed from: a.e */
public final class C0008e {
    /* renamed from: a */
    static final Logger f12a = Logger.getLogger(C0008e.class.getName());

    /* renamed from: a.e$1 */
    class C00061 implements C0000k {
        /* renamed from: a */
        final /* synthetic */ C0014m f8a;
        /* renamed from: b */
        final /* synthetic */ OutputStream f9b;

        C00061(C0014m c0014m, OutputStream outputStream) {
            this.f8a = c0014m;
            this.f9b = outputStream;
        }

        /* renamed from: a */
        public void mo2a(C0004a c0004a, long j) {
            C0016n.m68a(c0004a.f2b, 0, j);
            while (j > 0) {
                this.f8a.mo18a();
                C0011h c0011h = c0004a.f1a;
                int min = (int) Math.min(j, (long) (c0011h.f21c - c0011h.f20b));
                this.f9b.write(c0011h.f19a, c0011h.f20b, min);
                c0011h.f20b += min;
                j -= (long) min;
                c0004a.f2b -= (long) min;
                if (c0011h.f20b == c0011h.f21c) {
                    c0004a.f1a = c0011h.m52b();
                    C0012i.m55a(c0011h);
                }
            }
        }

        public void close() {
            this.f9b.close();
        }

        public void flush() {
            this.f9b.flush();
        }

        public String toString() {
            return "sink(" + this.f9b + ")";
        }
    }

    /* renamed from: a.e$2 */
    class C00072 implements C0002l {
        /* renamed from: a */
        final /* synthetic */ C0014m f10a;
        /* renamed from: b */
        final /* synthetic */ InputStream f11b;

        C00072(C0014m c0014m, InputStream inputStream) {
            this.f10a = c0014m;
            this.f11b = inputStream;
        }

        /* renamed from: b */
        public long mo3b(C0004a c0004a, long j) {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (j == 0) {
                return 0;
            } else {
                try {
                    this.f10a.mo18a();
                    C0011h a = c0004a.m7a(1);
                    int read = this.f11b.read(a.f19a, a.f21c, (int) Math.min(j, (long) (8192 - a.f21c)));
                    if (read == -1) {
                        return -1;
                    }
                    a.f21c += read;
                    c0004a.f2b += (long) read;
                    return (long) read;
                } catch (AssertionError e) {
                    if (C0008e.m41a(e)) {
                        throw new IOException(e);
                    }
                    throw e;
                }
            }
        }

        public void close() {
            this.f11b.close();
        }

        public String toString() {
            return "source(" + this.f11b + ")";
        }
    }

    private C0008e() {
    }

    /* renamed from: a */
    public static C0001b m34a(C0000k c0000k) {
        return new C0009f(c0000k);
    }

    /* renamed from: a */
    public static C0003c m35a(C0002l c0002l) {
        return new C0010g(c0002l);
    }

    /* renamed from: a */
    public static C0000k m36a(OutputStream outputStream) {
        return C0008e.m37a(outputStream, new C0014m());
    }

    /* renamed from: a */
    private static C0000k m37a(OutputStream outputStream, C0014m c0014m) {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        } else if (c0014m != null) {
            return new C00061(c0014m, outputStream);
        } else {
            throw new IllegalArgumentException("timeout == null");
        }
    }

    /* renamed from: a */
    public static C0002l m38a(File file) {
        if (file != null) {
            return C0008e.m39a(new FileInputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    /* renamed from: a */
    public static C0002l m39a(InputStream inputStream) {
        return C0008e.m40a(inputStream, new C0014m());
    }

    /* renamed from: a */
    private static C0002l m40a(InputStream inputStream, C0014m c0014m) {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        } else if (c0014m != null) {
            return new C00072(c0014m, inputStream);
        } else {
            throw new IllegalArgumentException("timeout == null");
        }
    }

    /* renamed from: a */
    static boolean m41a(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }

    /* renamed from: b */
    public static C0000k m42b(File file) {
        if (file != null) {
            return C0008e.m36a(new FileOutputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }
}
