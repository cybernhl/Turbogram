package com.google.android.gms.vision;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.os.SystemClock;
import android.support.annotation.RequiresPermission;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import com.google.android.gms.common.images.Size;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.vending.licensing.Policy;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public class CameraSource {
    @SuppressLint({"InlinedApi"})
    public static final int CAMERA_FACING_BACK = 0;
    @SuppressLint({"InlinedApi"})
    public static final int CAMERA_FACING_FRONT = 1;
    private int facing;
    private int rotation;
    private Context zze;
    private final Object zzf;
    @GuardedBy("cameraLock")
    private Camera zzg;
    private Size zzh;
    private float zzi;
    private int zzj;
    private int zzk;
    private boolean zzl;
    private SurfaceTexture zzm;
    private boolean zzn;
    private Thread zzo;
    private zzb zzp;
    private Map<byte[], ByteBuffer> zzq;

    public static class Builder {
        private final Detector<?> zzr;
        private CameraSource zzs = new CameraSource();

        public Builder(Context context, Detector<?> detector) {
            if (context == null) {
                throw new IllegalArgumentException("No context supplied.");
            } else if (detector == null) {
                throw new IllegalArgumentException("No detector supplied.");
            } else {
                this.zzr = detector;
                this.zzs.zze = context;
            }
        }

        public CameraSource build() {
            CameraSource cameraSource = this.zzs;
            CameraSource cameraSource2 = this.zzs;
            cameraSource2.getClass();
            cameraSource.zzp = new zzb(cameraSource2, this.zzr);
            return this.zzs;
        }

        public Builder setAutoFocusEnabled(boolean z) {
            this.zzs.zzl = z;
            return this;
        }

        public Builder setFacing(int i) {
            if (i == 0 || i == 1) {
                this.zzs.facing = i;
                return this;
            }
            throw new IllegalArgumentException("Invalid camera: " + i);
        }

        public Builder setRequestedFps(float f) {
            if (f <= 0.0f) {
                throw new IllegalArgumentException("Invalid fps: " + f);
            }
            this.zzs.zzi = f;
            return this;
        }

        public Builder setRequestedPreviewSize(int i, int i2) {
            if (i <= 0 || i > 1000000 || i2 <= 0 || i2 > 1000000) {
                throw new IllegalArgumentException("Invalid preview size: " + i + "x" + i2);
            }
            this.zzs.zzj = i;
            this.zzs.zzk = i2;
            return this;
        }
    }

    public interface PictureCallback {
        void onPictureTaken(byte[] bArr);
    }

    public interface ShutterCallback {
        void onShutter();
    }

    class zza implements PreviewCallback {
        private final /* synthetic */ CameraSource zzt;

        private zza(CameraSource cameraSource) {
            this.zzt = cameraSource;
        }

        public final void onPreviewFrame(byte[] bArr, Camera camera) {
            this.zzt.zzp.zza(bArr, camera);
        }
    }

    class zzb implements Runnable {
        private final Object lock = new Object();
        private Detector<?> zzr;
        private final /* synthetic */ CameraSource zzt;
        private long zzu = SystemClock.elapsedRealtime();
        private boolean zzv = true;
        private long zzw;
        private int zzx = 0;
        private ByteBuffer zzy;

        zzb(CameraSource cameraSource, Detector<?> detector) {
            this.zzt = cameraSource;
            this.zzr = detector;
        }

        @SuppressLint({"Assert"})
        final void release() {
            this.zzr.release();
            this.zzr = null;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        @android.annotation.SuppressLint({"InlinedApi"})
        public final void run() {
            /*
            r6 = this;
        L_0x0000:
            r1 = r6.lock;
            monitor-enter(r1);
        L_0x0003:
            r0 = r6.zzv;	 Catch:{ all -> 0x0023 }
            if (r0 == 0) goto L_0x001d;
        L_0x0007:
            r0 = r6.zzy;	 Catch:{ all -> 0x0023 }
            if (r0 != 0) goto L_0x001d;
        L_0x000b:
            r0 = r6.lock;	 Catch:{ InterruptedException -> 0x0011 }
            r0.wait();	 Catch:{ InterruptedException -> 0x0011 }
            goto L_0x0003;
        L_0x0011:
            r0 = move-exception;
            r2 = "CameraSource";
            r3 = "Frame processing loop terminated.";
            android.util.Log.d(r2, r3, r0);	 Catch:{ all -> 0x0023 }
            monitor-exit(r1);	 Catch:{ all -> 0x0023 }
        L_0x001c:
            return;
        L_0x001d:
            r0 = r6.zzv;	 Catch:{ all -> 0x0023 }
            if (r0 != 0) goto L_0x0026;
        L_0x0021:
            monitor-exit(r1);	 Catch:{ all -> 0x0023 }
            goto L_0x001c;
        L_0x0023:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0023 }
            throw r0;
        L_0x0026:
            r0 = new com.google.android.gms.vision.Frame$Builder;	 Catch:{ all -> 0x0023 }
            r0.<init>();	 Catch:{ all -> 0x0023 }
            r2 = r6.zzy;	 Catch:{ all -> 0x0023 }
            r3 = r6.zzt;	 Catch:{ all -> 0x0023 }
            r3 = r3.zzh;	 Catch:{ all -> 0x0023 }
            r3 = r3.getWidth();	 Catch:{ all -> 0x0023 }
            r4 = r6.zzt;	 Catch:{ all -> 0x0023 }
            r4 = r4.zzh;	 Catch:{ all -> 0x0023 }
            r4 = r4.getHeight();	 Catch:{ all -> 0x0023 }
            r5 = 17;
            r0 = r0.setImageData(r2, r3, r4, r5);	 Catch:{ all -> 0x0023 }
            r2 = r6.zzx;	 Catch:{ all -> 0x0023 }
            r0 = r0.setId(r2);	 Catch:{ all -> 0x0023 }
            r2 = r6.zzw;	 Catch:{ all -> 0x0023 }
            r0 = r0.setTimestampMillis(r2);	 Catch:{ all -> 0x0023 }
            r2 = r6.zzt;	 Catch:{ all -> 0x0023 }
            r2 = r2.rotation;	 Catch:{ all -> 0x0023 }
            r0 = r0.setRotation(r2);	 Catch:{ all -> 0x0023 }
            r0 = r0.build();	 Catch:{ all -> 0x0023 }
            r2 = r6.zzy;	 Catch:{ all -> 0x0023 }
            r3 = 0;
            r6.zzy = r3;	 Catch:{ all -> 0x0023 }
            monitor-exit(r1);	 Catch:{ all -> 0x0023 }
            r1 = r6.zzr;	 Catch:{ Exception -> 0x007a }
            r1.receiveFrame(r0);	 Catch:{ Exception -> 0x007a }
            r0 = r6.zzt;
            r0 = r0.zzg;
            r1 = r2.array();
            r0.addCallbackBuffer(r1);
            goto L_0x0000;
        L_0x007a:
            r0 = move-exception;
            r1 = "CameraSource";
            r3 = "Exception thrown from receiver.";
            android.util.Log.e(r1, r3, r0);	 Catch:{ all -> 0x0093 }
            r0 = r6.zzt;
            r0 = r0.zzg;
            r1 = r2.array();
            r0.addCallbackBuffer(r1);
            goto L_0x0000;
        L_0x0093:
            r0 = move-exception;
            r1 = r6.zzt;
            r1 = r1.zzg;
            r2 = r2.array();
            r1.addCallbackBuffer(r2);
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.vision.CameraSource.zzb.run():void");
        }

        final void setActive(boolean z) {
            synchronized (this.lock) {
                this.zzv = z;
                this.lock.notifyAll();
            }
        }

        final void zza(byte[] bArr, Camera camera) {
            synchronized (this.lock) {
                if (this.zzy != null) {
                    camera.addCallbackBuffer(this.zzy.array());
                    this.zzy = null;
                }
                if (this.zzt.zzq.containsKey(bArr)) {
                    this.zzw = SystemClock.elapsedRealtime() - this.zzu;
                    this.zzx++;
                    this.zzy = (ByteBuffer) this.zzt.zzq.get(bArr);
                    this.lock.notifyAll();
                    return;
                }
                Log.d("CameraSource", "Skipping frame. Could not find ByteBuffer associated with the image data from the camera.");
            }
        }
    }

    class zzc implements android.hardware.Camera.PictureCallback {
        private final /* synthetic */ CameraSource zzt;
        private PictureCallback zzz;

        private zzc(CameraSource cameraSource) {
            this.zzt = cameraSource;
        }

        public final void onPictureTaken(byte[] bArr, Camera camera) {
            if (this.zzz != null) {
                this.zzz.onPictureTaken(bArr);
            }
            synchronized (this.zzt.zzf) {
                if (this.zzt.zzg != null) {
                    this.zzt.zzg.startPreview();
                }
            }
        }
    }

    static class zzd implements android.hardware.Camera.ShutterCallback {
        private ShutterCallback zzaa;

        private zzd() {
        }

        public final void onShutter() {
            if (this.zzaa != null) {
                this.zzaa.onShutter();
            }
        }
    }

    @VisibleForTesting
    static class zze {
        private Size zzab;
        private Size zzac;

        public zze(Camera.Size size, @Nullable Camera.Size size2) {
            this.zzab = new Size(size.width, size.height);
            if (size2 != null) {
                this.zzac = new Size(size2.width, size2.height);
            }
        }

        public final Size zzb() {
            return this.zzab;
        }

        @Nullable
        public final Size zzc() {
            return this.zzac;
        }
    }

    private CameraSource() {
        this.zzf = new Object();
        this.facing = 0;
        this.zzi = 30.0f;
        this.zzj = 1024;
        this.zzk = Policy.MYKET_NOT_INSTALLED;
        this.zzl = false;
        this.zzq = new HashMap();
    }

    @SuppressLint({"InlinedApi"})
    private final Camera zza() throws IOException {
        int i;
        int i2;
        int i3 = this.facing;
        CameraInfo cameraInfo = new CameraInfo();
        for (i = 0; i < Camera.getNumberOfCameras(); i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == i3) {
                i2 = i;
                break;
            }
        }
        i2 = -1;
        if (i2 == -1) {
            throw new IOException("Could not find requested camera.");
        }
        Camera open = Camera.open(i2);
        int i4 = this.zzj;
        int i5 = this.zzk;
        Parameters parameters = open.getParameters();
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        List<Camera.Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
        List arrayList = new ArrayList();
        for (Camera.Size size : supportedPreviewSizes) {
            float f = ((float) size.width) / ((float) size.height);
            for (Camera.Size size2 : supportedPictureSizes) {
                if (Math.abs(f - (((float) size2.width) / ((float) size2.height))) < 0.01f) {
                    arrayList.add(new zze(size, size2));
                    break;
                }
            }
        }
        if (arrayList.size() == 0) {
            Log.w("CameraSource", "No preview sizes have a corresponding same-aspect-ratio picture size");
            for (Camera.Size size3 : supportedPreviewSizes) {
                arrayList.add(new zze(size3, null));
            }
        }
        zze zze = null;
        int i6 = Integer.MAX_VALUE;
        ArrayList arrayList2 = (ArrayList) arrayList;
        int size4 = arrayList2.size();
        int i7 = 0;
        while (i7 < size4) {
            Object obj = arrayList2.get(i7);
            i7++;
            zze zze2 = (zze) obj;
            Size zzb = zze2.zzb();
            int abs = Math.abs(zzb.getHeight() - i5) + Math.abs(zzb.getWidth() - i4);
            if (abs >= i6) {
                abs = i6;
                zze2 = zze;
            }
            i6 = abs;
            zze = zze2;
        }
        if (zze == null) {
            throw new IOException("Could not find suitable preview size.");
        }
        Size zzc = zze.zzc();
        this.zzh = zze.zzb();
        int i8 = (int) (this.zzi * 1000.0f);
        int[] iArr = null;
        abs = Integer.MAX_VALUE;
        for (int[] iArr2 : open.getParameters().getSupportedPreviewFpsRange()) {
            int[] iArr22;
            i3 = Math.abs(i8 - iArr22[0]) + Math.abs(i8 - iArr22[1]);
            if (i3 >= abs) {
                i3 = abs;
                iArr22 = iArr;
            }
            abs = i3;
            iArr = iArr22;
        }
        if (iArr == null) {
            throw new IOException("Could not find suitable preview frames per second range.");
        }
        Parameters parameters2 = open.getParameters();
        if (zzc != null) {
            parameters2.setPictureSize(zzc.getWidth(), zzc.getHeight());
        }
        parameters2.setPreviewSize(this.zzh.getWidth(), this.zzh.getHeight());
        parameters2.setPreviewFpsRange(iArr[0], iArr[1]);
        parameters2.setPreviewFormat(17);
        i = ((WindowManager) this.zze.getSystemService("window")).getDefaultDisplay().getRotation();
        switch (i) {
            case 0:
                i = 0;
                break;
            case 1:
                i = 90;
                break;
            case 2:
                i = 180;
                break;
            case 3:
                i = 270;
                break;
            default:
                Log.e("CameraSource", "Bad rotation value: " + i);
                i = 0;
                break;
        }
        CameraInfo cameraInfo2 = new CameraInfo();
        Camera.getCameraInfo(i2, cameraInfo2);
        if (cameraInfo2.facing == 1) {
            i3 = (i + cameraInfo2.orientation) % 360;
            i = (360 - i3) % 360;
            abs = i3;
        } else {
            i3 = ((cameraInfo2.orientation - i) + 360) % 360;
            i = i3;
            abs = i3;
        }
        this.rotation = abs / 90;
        open.setDisplayOrientation(i);
        parameters2.setRotation(abs);
        if (this.zzl) {
            if (parameters2.getSupportedFocusModes().contains("continuous-video")) {
                parameters2.setFocusMode("continuous-video");
            } else {
                Log.i("CameraSource", "Camera auto focus is not supported on this device.");
            }
        }
        open.setParameters(parameters2);
        open.setPreviewCallbackWithBuffer(new zza());
        open.addCallbackBuffer(zza(this.zzh));
        open.addCallbackBuffer(zza(this.zzh));
        open.addCallbackBuffer(zza(this.zzh));
        open.addCallbackBuffer(zza(this.zzh));
        return open;
    }

    @SuppressLint({"InlinedApi"})
    private final byte[] zza(Size size) {
        Object obj = new byte[(((int) Math.ceil(((double) ((long) (ImageFormat.getBitsPerPixel(17) * (size.getHeight() * size.getWidth())))) / 8.0d)) + 1)];
        ByteBuffer wrap = ByteBuffer.wrap(obj);
        if (wrap.hasArray() && wrap.array() == obj) {
            this.zzq.put(obj, wrap);
            return obj;
        }
        throw new IllegalStateException("Failed to create valid buffer for camera source.");
    }

    public int getCameraFacing() {
        return this.facing;
    }

    public Size getPreviewSize() {
        return this.zzh;
    }

    public void release() {
        synchronized (this.zzf) {
            stop();
            this.zzp.release();
        }
    }

    @RequiresPermission("android.permission.CAMERA")
    public CameraSource start() throws IOException {
        synchronized (this.zzf) {
            if (this.zzg != null) {
            } else {
                this.zzg = zza();
                this.zzm = new SurfaceTexture(100);
                this.zzg.setPreviewTexture(this.zzm);
                this.zzn = true;
                this.zzg.startPreview();
                this.zzo = new Thread(this.zzp);
                this.zzp.setActive(true);
                this.zzo.start();
            }
        }
        return this;
    }

    @RequiresPermission("android.permission.CAMERA")
    public CameraSource start(SurfaceHolder surfaceHolder) throws IOException {
        synchronized (this.zzf) {
            if (this.zzg != null) {
            } else {
                this.zzg = zza();
                this.zzg.setPreviewDisplay(surfaceHolder);
                this.zzg.startPreview();
                this.zzo = new Thread(this.zzp);
                this.zzp.setActive(true);
                this.zzo.start();
                this.zzn = false;
            }
        }
        return this;
    }

    public void stop() {
        synchronized (this.zzf) {
            this.zzp.setActive(false);
            if (this.zzo != null) {
                try {
                    this.zzo.join();
                } catch (InterruptedException e) {
                    Log.d("CameraSource", "Frame processing thread interrupted on release.");
                }
                this.zzo = null;
            }
            if (this.zzg != null) {
                this.zzg.stopPreview();
                this.zzg.setPreviewCallbackWithBuffer(null);
                try {
                    if (this.zzn) {
                        this.zzg.setPreviewTexture(null);
                    } else {
                        this.zzg.setPreviewDisplay(null);
                    }
                } catch (Exception e2) {
                    String valueOf = String.valueOf(e2);
                    Log.e("CameraSource", new StringBuilder(String.valueOf(valueOf).length() + 32).append("Failed to clear camera preview: ").append(valueOf).toString());
                }
                this.zzg.release();
                this.zzg = null;
            }
            this.zzq.clear();
        }
    }

    public void takePicture(ShutterCallback shutterCallback, PictureCallback pictureCallback) {
        synchronized (this.zzf) {
            if (this.zzg != null) {
                android.hardware.Camera.ShutterCallback zzd = new zzd();
                zzd.zzaa = shutterCallback;
                android.hardware.Camera.PictureCallback zzc = new zzc();
                zzc.zzz = pictureCallback;
                this.zzg.takePicture(zzd, null, null, zzc);
            }
        }
    }
}
