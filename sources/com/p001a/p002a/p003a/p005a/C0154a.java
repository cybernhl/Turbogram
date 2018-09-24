package com.p001a.p002a.p003a.p005a;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.p001a.p002a.p003a.C0153m;
import com.p001a.p002a.p003a.C0171e;
import com.p001a.p002a.p003a.C0229j;
import java.util.Set;

/* renamed from: com.a.a.a.a.a */
public class C0154a implements C0153m {
    /* renamed from: a */
    private C0153m f38a;
    /* renamed from: b */
    private Integer f39b;

    public C0154a(C0153m c0153m) {
        this.f38a = c0153m;
    }

    /* renamed from: c */
    private void m87c() {
        this.f39b = null;
    }

    /* renamed from: d */
    private boolean m88d() {
        return this.f39b != null && this.f39b.intValue() == 0;
    }

    /* renamed from: a */
    public int mo321a() {
        if (this.f39b == null) {
            this.f39b = Integer.valueOf(this.f38a.mo321a());
        }
        return this.f39b.intValue();
    }

    /* renamed from: a */
    public int mo322a(@NonNull C0171e c0171e) {
        return m88d() ? 0 : this.f38a.mo322a(c0171e);
    }

    @Nullable
    /* renamed from: a */
    public C0229j mo323a(@NonNull String str) {
        return this.f38a.mo323a(str);
    }

    /* renamed from: a */
    public void mo324a(@NonNull C0229j c0229j, @NonNull C0229j c0229j2) {
        m87c();
        this.f38a.mo324a(c0229j, c0229j2);
    }

    /* renamed from: a */
    public boolean mo325a(@NonNull C0229j c0229j) {
        m87c();
        return this.f38a.mo325a(c0229j);
    }

    /* renamed from: b */
    public C0229j mo326b(@NonNull C0171e c0171e) {
        if (m88d()) {
            return null;
        }
        C0229j b = this.f38a.mo326b(c0171e);
        if (b == null || this.f39b == null) {
            return b;
        }
        this.f39b = Integer.valueOf(this.f39b.intValue() - 1);
        return b;
    }

    /* renamed from: b */
    public void mo327b() {
        m87c();
        this.f38a.mo327b();
    }

    /* renamed from: b */
    public boolean mo328b(@NonNull C0229j c0229j) {
        m87c();
        return this.f38a.mo328b(c0229j);
    }

    /* renamed from: c */
    public Long mo329c(@NonNull C0171e c0171e) {
        return this.f38a.mo329c(c0171e);
    }

    /* renamed from: c */
    public void mo330c(@NonNull C0229j c0229j) {
        m87c();
        this.f38a.mo330c(c0229j);
    }

    @NonNull
    /* renamed from: d */
    public Set<C0229j> mo331d(@NonNull C0171e c0171e) {
        return this.f38a.mo331d(c0171e);
    }

    /* renamed from: d */
    public void mo332d(@NonNull C0229j c0229j) {
        m87c();
        this.f38a.mo332d(c0229j);
    }
}
