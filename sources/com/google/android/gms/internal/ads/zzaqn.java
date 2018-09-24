package com.google.android.gms.internal.ads;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@zzadh
public final class zzaqn extends zzaqh {
    private static final Set<String> zzdbg = Collections.synchronizedSet(new HashSet());
    private static final DecimalFormat zzdbh = new DecimalFormat("#,###");
    private File zzdbi;
    private boolean zzdbj;

    public zzaqn(zzapw zzapw) {
        super(zzapw);
        File cacheDir = this.mContext.getCacheDir();
        if (cacheDir == null) {
            zzane.zzdk("Context.getCacheDir() returned null");
            return;
        }
        this.zzdbi = new File(cacheDir, "admobVideoStreams");
        String str;
        String valueOf;
        if (!this.zzdbi.isDirectory() && !this.zzdbi.mkdirs()) {
            str = "Could not create preload cache directory at ";
            valueOf = String.valueOf(this.zzdbi.getAbsolutePath());
            zzane.zzdk(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            this.zzdbi = null;
        } else if (!this.zzdbi.setReadable(true, false) || !this.zzdbi.setExecutable(true, false)) {
            str = "Could not set cache file permissions at ";
            valueOf = String.valueOf(this.zzdbi.getAbsolutePath());
            zzane.zzdk(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            this.zzdbi = null;
        }
    }

    private final File zzc(File file) {
        return new File(this.zzdbi, String.valueOf(file.getName()).concat(".done"));
    }

    public final void abort() {
        this.zzdbj = true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzdp(java.lang.String r27) {
        /*
        r26 = this;
        r0 = r26;
        r2 = r0.zzdbi;
        if (r2 != 0) goto L_0x0014;
    L_0x0006:
        r2 = 0;
        r3 = "noCacheDir";
        r4 = 0;
        r0 = r26;
        r1 = r27;
        r0.zza(r1, r2, r3, r4);
        r2 = 0;
    L_0x0013:
        return r2;
    L_0x0014:
        r0 = r26;
        r2 = r0.zzdbi;
        if (r2 != 0) goto L_0x004b;
    L_0x001a:
        r2 = 0;
        r3 = r2;
    L_0x001c:
        r2 = com.google.android.gms.internal.ads.zznk.zzaux;
        r4 = com.google.android.gms.internal.ads.zzkb.zzik();
        r2 = r4.zzd(r2);
        r2 = (java.lang.Integer) r2;
        r2 = r2.intValue();
        if (r3 <= r2) goto L_0x00b6;
    L_0x002e:
        r0 = r26;
        r2 = r0.zzdbi;
        if (r2 != 0) goto L_0x006e;
    L_0x0034:
        r2 = 0;
    L_0x0035:
        if (r2 != 0) goto L_0x0014;
    L_0x0037:
        r2 = "Unable to expire stream cache";
        com.google.android.gms.internal.ads.zzane.zzdk(r2);
        r2 = 0;
        r3 = "expireFailed";
        r4 = 0;
        r0 = r26;
        r1 = r27;
        r0.zza(r1, r2, r3, r4);
        r2 = 0;
        goto L_0x0013;
    L_0x004b:
        r2 = 0;
        r0 = r26;
        r3 = r0.zzdbi;
        r4 = r3.listFiles();
        r5 = r4.length;
        r3 = 0;
    L_0x0056:
        if (r3 >= r5) goto L_0x006c;
    L_0x0058:
        r6 = r4[r3];
        r6 = r6.getName();
        r7 = ".done";
        r6 = r6.endsWith(r7);
        if (r6 != 0) goto L_0x0069;
    L_0x0067:
        r2 = r2 + 1;
    L_0x0069:
        r3 = r3 + 1;
        goto L_0x0056;
    L_0x006c:
        r3 = r2;
        goto L_0x001c;
    L_0x006e:
        r7 = 0;
        r4 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
        r0 = r26;
        r2 = r0.zzdbi;
        r9 = r2.listFiles();
        r10 = r9.length;
        r2 = 0;
        r8 = r2;
    L_0x007f:
        if (r8 >= r10) goto L_0x009d;
    L_0x0081:
        r6 = r9[r8];
        r2 = r6.getName();
        r3 = ".done";
        r2 = r2.endsWith(r3);
        if (r2 != 0) goto L_0x05de;
    L_0x0090:
        r2 = r6.lastModified();
        r11 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r11 >= 0) goto L_0x05de;
    L_0x0098:
        r8 = r8 + 1;
        r4 = r2;
        r7 = r6;
        goto L_0x007f;
    L_0x009d:
        r2 = 0;
        if (r7 == 0) goto L_0x0035;
    L_0x00a0:
        r2 = r7.delete();
        r0 = r26;
        r3 = r0.zzc(r7);
        r4 = r3.isFile();
        if (r4 == 0) goto L_0x0035;
    L_0x00b0:
        r3 = r3.delete();
        r2 = r2 & r3;
        goto L_0x0035;
    L_0x00b6:
        com.google.android.gms.internal.ads.zzkb.zzif();
        r2 = com.google.android.gms.internal.ads.zzamu.zzde(r27);
        r13 = new java.io.File;
        r0 = r26;
        r3 = r0.zzdbi;
        r13.<init>(r3, r2);
        r0 = r26;
        r14 = r0.zzc(r13);
        r2 = r13.isFile();
        if (r2 == 0) goto L_0x0105;
    L_0x00d2:
        r2 = r14.isFile();
        if (r2 == 0) goto L_0x0105;
    L_0x00d8:
        r2 = r13.length();
        r3 = (int) r2;
        r4 = "Stream cache hit at ";
        r2 = java.lang.String.valueOf(r27);
        r5 = r2.length();
        if (r5 == 0) goto L_0x00ff;
    L_0x00ea:
        r2 = r4.concat(r2);
    L_0x00ee:
        com.google.android.gms.internal.ads.zzane.zzck(r2);
        r2 = r13.getAbsolutePath();
        r0 = r26;
        r1 = r27;
        r0.zza(r1, r2, r3);
        r2 = 1;
        goto L_0x0013;
    L_0x00ff:
        r2 = new java.lang.String;
        r2.<init>(r4);
        goto L_0x00ee;
    L_0x0105:
        r0 = r26;
        r2 = r0.zzdbi;
        r2 = r2.getAbsolutePath();
        r3 = java.lang.String.valueOf(r2);
        r2 = java.lang.String.valueOf(r27);
        r4 = r2.length();
        if (r4 == 0) goto L_0x0155;
    L_0x011b:
        r2 = r3.concat(r2);
        r9 = r2;
    L_0x0120:
        r3 = zzdbg;
        monitor-enter(r3);
        r2 = zzdbg;	 Catch:{ all -> 0x0152 }
        r2 = r2.contains(r9);	 Catch:{ all -> 0x0152 }
        if (r2 == 0) goto L_0x0162;
    L_0x012b:
        r4 = "Stream cache already in progress at ";
        r2 = java.lang.String.valueOf(r27);	 Catch:{ all -> 0x0152 }
        r5 = r2.length();	 Catch:{ all -> 0x0152 }
        if (r5 == 0) goto L_0x015c;
    L_0x0138:
        r2 = r4.concat(r2);	 Catch:{ all -> 0x0152 }
    L_0x013c:
        com.google.android.gms.internal.ads.zzane.zzdk(r2);	 Catch:{ all -> 0x0152 }
        r2 = r13.getAbsolutePath();	 Catch:{ all -> 0x0152 }
        r4 = "inProgress";
        r5 = 0;
        r0 = r26;
        r1 = r27;
        r0.zza(r1, r2, r4, r5);	 Catch:{ all -> 0x0152 }
        r2 = 0;
        monitor-exit(r3);	 Catch:{ all -> 0x0152 }
        goto L_0x0013;
    L_0x0152:
        r2 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0152 }
        throw r2;
    L_0x0155:
        r2 = new java.lang.String;
        r2.<init>(r3);
        r9 = r2;
        goto L_0x0120;
    L_0x015c:
        r2 = new java.lang.String;	 Catch:{ all -> 0x0152 }
        r2.<init>(r4);	 Catch:{ all -> 0x0152 }
        goto L_0x013c;
    L_0x0162:
        r2 = zzdbg;	 Catch:{ all -> 0x0152 }
        r2.add(r9);	 Catch:{ all -> 0x0152 }
        monitor-exit(r3);	 Catch:{ all -> 0x0152 }
        r5 = 0;
        r11 = "error";
        r10 = 0;
        com.google.android.gms.ads.internal.zzbv.zzew();	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r2 = com.google.android.gms.internal.ads.zznk.zzavc;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r3 = com.google.android.gms.internal.ads.zzkb.zzik();	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r2 = r3.zzd(r2);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r2 = (java.lang.Integer) r2;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r7 = r2.intValue();	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r3 = new java.net.URL;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r0 = r27;
        r3.<init>(r0);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r2 = 0;
    L_0x0188:
        r4 = r2 + 1;
        r2 = 20;
        if (r4 > r2) goto L_0x02b6;
    L_0x018e:
        r2 = r3.openConnection();	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r2.setConnectTimeout(r7);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r2.setReadTimeout(r7);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r6 = r2 instanceof java.net.HttpURLConnection;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        if (r6 != 0) goto L_0x0220;
    L_0x019c:
        r2 = new java.io.IOException;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r3 = "Invalid protocol.";
        r2.<init>(r3);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        throw r2;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
    L_0x01a5:
        r2 = move-exception;
        r3 = r10;
        r4 = r11;
    L_0x01a8:
        r6 = r2 instanceof java.lang.RuntimeException;
        if (r6 == 0) goto L_0x01b6;
    L_0x01ac:
        r6 = com.google.android.gms.ads.internal.zzbv.zzeo();
        r7 = "VideoStreamFullFileCache.preload";
        r6.zza(r2, r7);
    L_0x01b6:
        r5.close();	 Catch:{ IOException -> 0x05cb, NullPointerException -> 0x05ce }
    L_0x01b9:
        r0 = r26;
        r5 = r0.zzdbj;
        if (r5 == 0) goto L_0x0598;
    L_0x01bf:
        r2 = java.lang.String.valueOf(r27);
        r2 = r2.length();
        r2 = r2 + 26;
        r5 = new java.lang.StringBuilder;
        r5.<init>(r2);
        r2 = "Preload aborted for URL \"";
        r2 = r5.append(r2);
        r0 = r27;
        r2 = r2.append(r0);
        r5 = "\"";
        r2 = r2.append(r5);
        r2 = r2.toString();
        com.google.android.gms.internal.ads.zzane.zzdj(r2);
    L_0x01e9:
        r2 = r13.exists();
        if (r2 == 0) goto L_0x020d;
    L_0x01ef:
        r2 = r13.delete();
        if (r2 != 0) goto L_0x020d;
    L_0x01f5:
        r5 = "Could not delete partial cache file at ";
        r2 = r13.getAbsolutePath();
        r2 = java.lang.String.valueOf(r2);
        r6 = r2.length();
        if (r6 == 0) goto L_0x05c4;
    L_0x0206:
        r2 = r5.concat(r2);
    L_0x020a:
        com.google.android.gms.internal.ads.zzane.zzdk(r2);
    L_0x020d:
        r2 = r13.getAbsolutePath();
        r0 = r26;
        r1 = r27;
        r0.zza(r1, r2, r4, r3);
        r2 = zzdbg;
        r2.remove(r9);
        r2 = 0;
        goto L_0x0013;
    L_0x0220:
        r2 = (java.net.HttpURLConnection) r2;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r6 = new com.google.android.gms.internal.ads.zzamy;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r6.<init>();	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r8 = 0;
        r6.zza(r2, r8);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r8 = 0;
        r2.setInstanceFollowRedirects(r8);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r8 = r2.getResponseCode();	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r6.zza(r2, r8);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r6 = r8 / 100;
        r8 = 3;
        if (r6 != r8) goto L_0x02bf;
    L_0x023b:
        r6 = "Location";
        r8 = r2.getHeaderField(r6);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        if (r8 != 0) goto L_0x0252;
    L_0x0244:
        r2 = new java.io.IOException;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r3 = "Missing Location header in redirect";
        r2.<init>(r3);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        throw r2;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
    L_0x024d:
        r2 = move-exception;
        r3 = r10;
        r4 = r11;
        goto L_0x01a8;
    L_0x0252:
        r6 = new java.net.URL;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r6.<init>(r3, r8);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r3 = r6.getProtocol();	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        if (r3 != 0) goto L_0x0266;
    L_0x025d:
        r2 = new java.io.IOException;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r3 = "Protocol is null";
        r2.<init>(r3);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        throw r2;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
    L_0x0266:
        r12 = "http";
        r12 = r3.equals(r12);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        if (r12 != 0) goto L_0x0295;
    L_0x026f:
        r12 = "https";
        r12 = r3.equals(r12);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        if (r12 != 0) goto L_0x0295;
    L_0x0278:
        r4 = new java.io.IOException;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r6 = "Unsupported scheme: ";
        r2 = java.lang.String.valueOf(r3);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r3 = r2.length();	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        if (r3 == 0) goto L_0x028f;
    L_0x0287:
        r2 = r6.concat(r2);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
    L_0x028b:
        r4.<init>(r2);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        throw r4;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
    L_0x028f:
        r2 = new java.lang.String;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r2.<init>(r6);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        goto L_0x028b;
    L_0x0295:
        r12 = "Redirecting to ";
        r3 = java.lang.String.valueOf(r8);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r8 = r3.length();	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        if (r8 == 0) goto L_0x02b0;
    L_0x02a2:
        r3 = r12.concat(r3);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
    L_0x02a6:
        com.google.android.gms.internal.ads.zzane.zzck(r3);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r2.disconnect();	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r2 = r4;
        r3 = r6;
        goto L_0x0188;
    L_0x02b0:
        r3 = new java.lang.String;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r3.<init>(r12);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        goto L_0x02a6;
    L_0x02b6:
        r2 = new java.io.IOException;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r3 = "Too many redirects (20)";
        r2.<init>(r3);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        throw r2;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
    L_0x02bf:
        r3 = r2 instanceof java.net.HttpURLConnection;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        if (r3 == 0) goto L_0x0325;
    L_0x02c3:
        r0 = r2;
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r3 = r0;
        r6 = r3.getResponseCode();	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r3 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r6 < r3) goto L_0x0325;
    L_0x02cf:
        r4 = "badUrl";
        r2 = "HTTP request failed. Code: ";
        r3 = java.lang.Integer.toString(r6);	 Catch:{ IOException -> 0x0321, RuntimeException -> 0x05d1 }
        r3 = java.lang.String.valueOf(r3);	 Catch:{ IOException -> 0x0321, RuntimeException -> 0x05d1 }
        r7 = r3.length();	 Catch:{ IOException -> 0x0321, RuntimeException -> 0x05d1 }
        if (r7 == 0) goto L_0x031b;
    L_0x02e3:
        r3 = r2.concat(r3);	 Catch:{ IOException -> 0x0321, RuntimeException -> 0x05d1 }
    L_0x02e7:
        r2 = new java.io.IOException;	 Catch:{ IOException -> 0x0318, RuntimeException -> 0x05d5 }
        r7 = java.lang.String.valueOf(r27);	 Catch:{ IOException -> 0x0318, RuntimeException -> 0x05d5 }
        r7 = r7.length();	 Catch:{ IOException -> 0x0318, RuntimeException -> 0x05d5 }
        r7 = r7 + 32;
        r8 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0318, RuntimeException -> 0x05d5 }
        r8.<init>(r7);	 Catch:{ IOException -> 0x0318, RuntimeException -> 0x05d5 }
        r7 = "HTTP status code ";
        r7 = r8.append(r7);	 Catch:{ IOException -> 0x0318, RuntimeException -> 0x05d5 }
        r6 = r7.append(r6);	 Catch:{ IOException -> 0x0318, RuntimeException -> 0x05d5 }
        r7 = " at ";
        r6 = r6.append(r7);	 Catch:{ IOException -> 0x0318, RuntimeException -> 0x05d5 }
        r0 = r27;
        r6 = r6.append(r0);	 Catch:{ IOException -> 0x0318, RuntimeException -> 0x05d5 }
        r6 = r6.toString();	 Catch:{ IOException -> 0x0318, RuntimeException -> 0x05d5 }
        r2.<init>(r6);	 Catch:{ IOException -> 0x0318, RuntimeException -> 0x05d5 }
        throw r2;	 Catch:{ IOException -> 0x0318, RuntimeException -> 0x05d5 }
    L_0x0318:
        r2 = move-exception;
        goto L_0x01a8;
    L_0x031b:
        r3 = new java.lang.String;	 Catch:{ IOException -> 0x0321, RuntimeException -> 0x05d1 }
        r3.<init>(r2);	 Catch:{ IOException -> 0x0321, RuntimeException -> 0x05d1 }
        goto L_0x02e7;
    L_0x0321:
        r2 = move-exception;
        r3 = r10;
        goto L_0x01a8;
    L_0x0325:
        r7 = r2.getContentLength();	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        if (r7 >= 0) goto L_0x035c;
    L_0x032b:
        r3 = "Stream cache aborted, missing content-length header at ";
        r2 = java.lang.String.valueOf(r27);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r4 = r2.length();	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        if (r4 == 0) goto L_0x0356;
    L_0x0338:
        r2 = r3.concat(r2);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
    L_0x033c:
        com.google.android.gms.internal.ads.zzane.zzdk(r2);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r2 = r13.getAbsolutePath();	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r3 = "contentLengthMissing";
        r4 = 0;
        r0 = r26;
        r1 = r27;
        r0.zza(r1, r2, r3, r4);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r2 = zzdbg;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r2.remove(r9);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r2 = 0;
        goto L_0x0013;
    L_0x0356:
        r2 = new java.lang.String;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r2.<init>(r3);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        goto L_0x033c;
    L_0x035c:
        r3 = zzdbh;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r0 = (long) r7;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r16 = r0;
        r0 = r16;
        r4 = r3.format(r0);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r3 = com.google.android.gms.internal.ads.zznk.zzauy;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r6 = com.google.android.gms.internal.ads.zzkb.zzik();	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r3 = r6.zzd(r3);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r3 = (java.lang.Integer) r3;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r15 = r3.intValue();	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        if (r7 <= r15) goto L_0x03dd;
    L_0x0379:
        r2 = java.lang.String.valueOf(r4);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r2 = r2.length();	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r2 = r2 + 33;
        r3 = java.lang.String.valueOf(r27);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r3 = r3.length();	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r2 = r2 + r3;
        r3 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r3.<init>(r2);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r2 = "Content length ";
        r2 = r3.append(r2);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r2 = r2.append(r4);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r3 = " exceeds limit at ";
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r0 = r27;
        r2 = r2.append(r0);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r2 = r2.toString();	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        com.google.android.gms.internal.ads.zzane.zzdk(r2);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r3 = "File too big for full file cache. Size: ";
        r2 = java.lang.String.valueOf(r4);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r4 = r2.length();	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        if (r4 == 0) goto L_0x03d7;
    L_0x03bd:
        r2 = r3.concat(r2);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
    L_0x03c1:
        r3 = r13.getAbsolutePath();	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r4 = "sizeExceeded";
        r0 = r26;
        r1 = r27;
        r0.zza(r1, r3, r4, r2);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r2 = zzdbg;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r2.remove(r9);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r2 = 0;
        goto L_0x0013;
    L_0x03d7:
        r2 = new java.lang.String;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r2.<init>(r3);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        goto L_0x03c1;
    L_0x03dd:
        r3 = java.lang.String.valueOf(r4);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r3 = r3.length();	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r3 = r3 + 20;
        r6 = java.lang.String.valueOf(r27);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r6 = r6.length();	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r3 = r3 + r6;
        r6 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r6.<init>(r3);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r3 = "Caching ";
        r3 = r6.append(r3);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r3 = r3.append(r4);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r4 = " bytes from ";
        r3 = r3.append(r4);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r0 = r27;
        r3 = r3.append(r0);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r3 = r3.toString();	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        com.google.android.gms.internal.ads.zzane.zzck(r3);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r2 = r2.getInputStream();	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r16 = java.nio.channels.Channels.newChannel(r2);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r12 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r12.<init>(r13);	 Catch:{ IOException -> 0x01a5, RuntimeException -> 0x024d }
        r17 = r12.getChannel();	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r2 = 1048576; // 0x100000 float:1.469368E-39 double:5.180654E-318;
        r18 = java.nio.ByteBuffer.allocate(r2);	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r19 = com.google.android.gms.ads.internal.zzbv.zzer();	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r6 = 0;
        r20 = r19.currentTimeMillis();	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r2 = com.google.android.gms.internal.ads.zznk.zzavb;	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r3 = com.google.android.gms.internal.ads.zzkb.zzik();	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r2 = r3.zzd(r2);	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r2 = (java.lang.Long) r2;	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r2 = r2.longValue();	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r22 = new com.google.android.gms.internal.ads.zzamj;	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r0 = r22;
        r0.<init>(r2);	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r2 = com.google.android.gms.internal.ads.zznk.zzava;	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r3 = com.google.android.gms.internal.ads.zzkb.zzik();	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r2 = r3.zzd(r2);	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r2 = (java.lang.Long) r2;	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r24 = r2.longValue();	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
    L_0x045b:
        r0 = r16;
        r1 = r18;
        r2 = r0.read(r1);	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        if (r2 < 0) goto L_0x0525;
    L_0x0465:
        r6 = r6 + r2;
        if (r6 <= r15) goto L_0x0498;
    L_0x0468:
        r4 = "sizeExceeded";
        r2 = "File too big for full file cache. Size: ";
        r3 = java.lang.Integer.toString(r6);	 Catch:{ IOException -> 0x0493, RuntimeException -> 0x04fd }
        r3 = java.lang.String.valueOf(r3);	 Catch:{ IOException -> 0x0493, RuntimeException -> 0x04fd }
        r5 = r3.length();	 Catch:{ IOException -> 0x0493, RuntimeException -> 0x04fd }
        if (r5 == 0) goto L_0x048d;
    L_0x047c:
        r3 = r2.concat(r3);	 Catch:{ IOException -> 0x0493, RuntimeException -> 0x04fd }
    L_0x0480:
        r2 = new java.io.IOException;	 Catch:{ IOException -> 0x0489, RuntimeException -> 0x04e7 }
        r5 = "stream cache file size limit exceeded";
        r2.<init>(r5);	 Catch:{ IOException -> 0x0489, RuntimeException -> 0x04e7 }
        throw r2;	 Catch:{ IOException -> 0x0489, RuntimeException -> 0x04e7 }
    L_0x0489:
        r2 = move-exception;
        r5 = r12;
        goto L_0x01a8;
    L_0x048d:
        r3 = new java.lang.String;	 Catch:{ IOException -> 0x0493, RuntimeException -> 0x04fd }
        r3.<init>(r2);	 Catch:{ IOException -> 0x0493, RuntimeException -> 0x04fd }
        goto L_0x0480;
    L_0x0493:
        r2 = move-exception;
        r3 = r10;
        r5 = r12;
        goto L_0x01a8;
    L_0x0498:
        r18.flip();	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
    L_0x049b:
        r2 = r17.write(r18);	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        if (r2 > 0) goto L_0x049b;
    L_0x04a1:
        r18.clear();	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r2 = r19.currentTimeMillis();	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r2 = r2 - r20;
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r4 = r4 * r24;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 <= 0) goto L_0x04eb;
    L_0x04b2:
        r4 = "downloadTimeout";
        r2 = java.lang.Long.toString(r24);	 Catch:{ IOException -> 0x0493, RuntimeException -> 0x04fd }
        r3 = java.lang.String.valueOf(r2);	 Catch:{ IOException -> 0x0493, RuntimeException -> 0x04fd }
        r3 = r3.length();	 Catch:{ IOException -> 0x0493, RuntimeException -> 0x04fd }
        r3 = r3 + 29;
        r5 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0493, RuntimeException -> 0x04fd }
        r5.<init>(r3);	 Catch:{ IOException -> 0x0493, RuntimeException -> 0x04fd }
        r3 = "Timeout exceeded. Limit: ";
        r3 = r5.append(r3);	 Catch:{ IOException -> 0x0493, RuntimeException -> 0x04fd }
        r2 = r3.append(r2);	 Catch:{ IOException -> 0x0493, RuntimeException -> 0x04fd }
        r3 = " sec";
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x0493, RuntimeException -> 0x04fd }
        r3 = r2.toString();	 Catch:{ IOException -> 0x0493, RuntimeException -> 0x04fd }
        r2 = new java.io.IOException;	 Catch:{ IOException -> 0x0489, RuntimeException -> 0x04e7 }
        r5 = "stream cache time limit exceeded";
        r2.<init>(r5);	 Catch:{ IOException -> 0x0489, RuntimeException -> 0x04e7 }
        throw r2;	 Catch:{ IOException -> 0x0489, RuntimeException -> 0x04e7 }
    L_0x04e7:
        r2 = move-exception;
        r5 = r12;
        goto L_0x01a8;
    L_0x04eb:
        r0 = r26;
        r2 = r0.zzdbj;	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        if (r2 == 0) goto L_0x0502;
    L_0x04f1:
        r4 = "externalAbort";
        r2 = new java.io.IOException;	 Catch:{ IOException -> 0x0493, RuntimeException -> 0x04fd }
        r3 = "abort requested";
        r2.<init>(r3);	 Catch:{ IOException -> 0x0493, RuntimeException -> 0x04fd }
        throw r2;	 Catch:{ IOException -> 0x0493, RuntimeException -> 0x04fd }
    L_0x04fd:
        r2 = move-exception;
        r3 = r10;
        r5 = r12;
        goto L_0x01a8;
    L_0x0502:
        r2 = r22.tryAcquire();	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        if (r2 == 0) goto L_0x045b;
    L_0x0508:
        r5 = r13.getAbsolutePath();	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r23 = com.google.android.gms.internal.ads.zzamu.zzsy;	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r2 = new com.google.android.gms.internal.ads.zzaqi;	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r8 = 0;
        r3 = r26;
        r4 = r27;
        r2.<init>(r3, r4, r5, r6, r7, r8);	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r0 = r23;
        r0.post(r2);	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        goto L_0x045b;
    L_0x051f:
        r2 = move-exception;
        r3 = r10;
        r4 = r11;
        r5 = r12;
        goto L_0x01a8;
    L_0x0525:
        r12.close();	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r2 = 3;
        r2 = com.google.android.gms.internal.ads.zzane.isLoggable(r2);	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        if (r2 == 0) goto L_0x056d;
    L_0x052f:
        r2 = zzdbh;	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r4 = (long) r6;	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r2 = r2.format(r4);	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r3 = java.lang.String.valueOf(r2);	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r3 = r3.length();	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r3 = r3 + 22;
        r4 = java.lang.String.valueOf(r27);	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r4 = r4.length();	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r3 = r3 + r4;
        r4 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r4.<init>(r3);	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r3 = "Preloaded ";
        r3 = r4.append(r3);	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r2 = r3.append(r2);	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r3 = " bytes from ";
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r0 = r27;
        r2 = r2.append(r0);	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r2 = r2.toString();	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        com.google.android.gms.internal.ads.zzane.zzck(r2);	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
    L_0x056d:
        r2 = 1;
        r3 = 0;
        r13.setReadable(r2, r3);	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r2 = r14.isFile();	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        if (r2 == 0) goto L_0x0592;
    L_0x0578:
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r14.setLastModified(r2);	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
    L_0x057f:
        r2 = r13.getAbsolutePath();	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r0 = r26;
        r1 = r27;
        r0.zza(r1, r2, r6);	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r2 = zzdbg;	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r2.remove(r9);	 Catch:{ IOException -> 0x051f, RuntimeException -> 0x05d8 }
        r2 = 1;
        goto L_0x0013;
    L_0x0592:
        r14.createNewFile();	 Catch:{ IOException -> 0x0596, RuntimeException -> 0x05d8 }
        goto L_0x057f;
    L_0x0596:
        r2 = move-exception;
        goto L_0x057f;
    L_0x0598:
        r5 = java.lang.String.valueOf(r27);
        r5 = r5.length();
        r5 = r5 + 25;
        r6 = new java.lang.StringBuilder;
        r6.<init>(r5);
        r5 = "Preload failed for URL \"";
        r5 = r6.append(r5);
        r0 = r27;
        r5 = r5.append(r0);
        r6 = "\"";
        r5 = r5.append(r6);
        r5 = r5.toString();
        com.google.android.gms.internal.ads.zzane.zzc(r5, r2);
        goto L_0x01e9;
    L_0x05c4:
        r2 = new java.lang.String;
        r2.<init>(r5);
        goto L_0x020a;
    L_0x05cb:
        r5 = move-exception;
        goto L_0x01b9;
    L_0x05ce:
        r5 = move-exception;
        goto L_0x01b9;
    L_0x05d1:
        r2 = move-exception;
        r3 = r10;
        goto L_0x01a8;
    L_0x05d5:
        r2 = move-exception;
        goto L_0x01a8;
    L_0x05d8:
        r2 = move-exception;
        r3 = r10;
        r4 = r11;
        r5 = r12;
        goto L_0x01a8;
    L_0x05de:
        r2 = r4;
        r6 = r7;
        goto L_0x0098;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaqn.zzdp(java.lang.String):boolean");
    }
}
