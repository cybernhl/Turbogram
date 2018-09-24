package me.cheshmak.android.sdk.core.p022l;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/* renamed from: me.cheshmak.android.sdk.core.l.k */
public final class C0543k {
    /* renamed from: a */
    public static void m1031a(Context context, String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("bazaar://details?id=" + str));
        intent.setPackage("com.farsitel.bazaar");
        context.startActivity(intent);
    }

    /* renamed from: b */
    public static void m1032b(Context context, String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("myket://comment?id=" + str));
        context.startActivity(intent);
    }

    /* renamed from: c */
    public static void m1033c(Context context, String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("iranapps://app/" + str));
        intent.setPackage("ir.tgbs.android.iranapp");
        context.startActivity(intent);
    }

    /* renamed from: d */
    public static void m1034d(Context context, String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + str));
        context.startActivity(intent);
    }
}
