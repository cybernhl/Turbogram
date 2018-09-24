package me.cheshmak.android.sdk.core.task;

import android.support.annotation.NonNull;
import android.util.Base64;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import com.p001a.p002a.p003a.C0238o;
import com.p001a.p002a.p003a.C0239q;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.MessageDigest;
import java.util.zip.GZIPOutputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.cheshmak.android.sdk.core.network.C0571i;
import me.cheshmak.android.sdk.core.p019a.C0477a;

/* renamed from: me.cheshmak.android.sdk.core.task.c */
public class C0592c extends C0459a {
    /* renamed from: f */
    private final int f747f;
    /* renamed from: g */
    private String f748g;

    public C0592c(int i, String str) {
        super(new C0238o(1).m533a().m534a(10000).m536b());
        this.f748g = str;
        this.f747f = i;
    }

    /* renamed from: a */
    private static byte[] m1185a(byte[] bArr) {
        byte[] bArr2 = null;
        if (bArr != null) {
            try {
                if (bArr.length != 0) {
                    OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    OutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                    gZIPOutputStream.write(bArr);
                    gZIPOutputStream.close();
                    bArr2 = byteArrayOutputStream.toByteArray();
                }
            } catch (Throwable th) {
            }
        }
        return bArr2;
    }

    /* renamed from: b */
    private static byte[] m1186b(byte[] bArr) {
        Key secretKeySpec = new SecretKeySpec(MessageDigest.getInstance("MD5").digest("RYxoz9gYl9dRVtFn4haB".getBytes("UTF-8")), "AES");
        Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
        instance.init(1, secretKeySpec);
        byte[] bArr2 = new byte[instance.getOutputSize(bArr.length)];
        instance.doFinal(bArr2, instance.update(bArr, 0, bArr.length, bArr2, 0));
        return bArr2;
    }

    /* renamed from: a */
    protected C0239q mo4386a(@NonNull Throwable th, int i, int i2) {
        return C0239q.m545a(i, ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS);
    }

    /* renamed from: a */
    protected void mo4387a(int i, Throwable th) {
    }

    /* renamed from: f */
    public void mo4388f() {
    }

    /* renamed from: g */
    public void mo4389g() {
        if (C0477a.m656a().m700f()) {
            C0571i.m1123a().m1124a(this.f747f, C0592c.m1185a(C0592c.m1186b(Base64.encode(this.f748g.getBytes("UTF-8"), 2))));
            this.f748g = null;
        }
    }
}
