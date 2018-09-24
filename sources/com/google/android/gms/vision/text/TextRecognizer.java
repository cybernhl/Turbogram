package com.google.android.gms.vision.text;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.util.SparseArray;
import com.google.android.gms.internal.vision.zzad;
import com.google.android.gms.internal.vision.zzae;
import com.google.android.gms.internal.vision.zzm;
import com.google.android.gms.internal.vision.zzo;
import com.google.android.gms.internal.vision.zzx;
import com.google.android.gms.internal.vision.zzz;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.Frame.Metadata;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public final class TextRecognizer extends Detector<TextBlock> {
    private final zzad zzdf;

    public static class Builder {
        private zzae zzdg = new zzae();
        private Context zze;

        public Builder(Context context) {
            this.zze = context;
        }

        public TextRecognizer build() {
            return new TextRecognizer(new zzad(this.zze, this.zzdg));
        }
    }

    private TextRecognizer() {
        throw new IllegalStateException("Default constructor called");
    }

    private TextRecognizer(zzad zzad) {
        this.zzdf = zzad;
    }

    public final SparseArray<TextBlock> detect(Frame frame) {
        zzz zzz = new zzz(new Rect());
        if (frame == null) {
            throw new IllegalArgumentException("No frame supplied.");
        }
        Bitmap bitmap;
        int i;
        zzm zzc = zzm.zzc(frame);
        if (frame.getBitmap() != null) {
            bitmap = frame.getBitmap();
        } else {
            byte[] array;
            Metadata metadata = frame.getMetadata();
            ByteBuffer grayscaleImageData = frame.getGrayscaleImageData();
            int format = metadata.getFormat();
            int i2 = zzc.width;
            int i3 = zzc.height;
            if (grayscaleImageData.hasArray() && grayscaleImageData.arrayOffset() == 0) {
                array = grayscaleImageData.array();
            } else {
                array = new byte[grayscaleImageData.capacity()];
                grayscaleImageData.get(array);
            }
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new YuvImage(array, format, i2, i3, null).compressToJpeg(new Rect(0, 0, i2, i3), 100, byteArrayOutputStream);
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            bitmap = BitmapFactory.decodeByteArray(toByteArray, 0, toByteArray.length);
        }
        Bitmap zzb = zzo.zzb(bitmap, zzc);
        if (!zzz.zzdr.isEmpty()) {
            Rect rect;
            Rect rect2 = zzz.zzdr;
            i2 = frame.getMetadata().getWidth();
            i3 = frame.getMetadata().getHeight();
            switch (zzc.rotation) {
                case 1:
                    rect = new Rect(i3 - rect2.bottom, rect2.left, i3 - rect2.top, rect2.right);
                    break;
                case 2:
                    rect = new Rect(i2 - rect2.right, i3 - rect2.bottom, i2 - rect2.left, i3 - rect2.top);
                    break;
                case 3:
                    rect = new Rect(rect2.top, i2 - rect2.right, rect2.bottom, i2 - rect2.left);
                    break;
                default:
                    rect = rect2;
                    break;
            }
            zzz.zzdr.set(rect);
        }
        zzc.rotation = 0;
        zzx[] zza = this.zzdf.zza(zzb, zzc, zzz);
        SparseArray sparseArray = new SparseArray();
        for (zzx zzx : zza) {
            SparseArray sparseArray2 = (SparseArray) sparseArray.get(zzx.zzdp);
            if (sparseArray2 == null) {
                sparseArray2 = new SparseArray();
                sparseArray.append(zzx.zzdp, sparseArray2);
            }
            sparseArray2.append(zzx.zzdq, zzx);
        }
        SparseArray<TextBlock> sparseArray3 = new SparseArray(sparseArray.size());
        for (i = 0; i < sparseArray.size(); i++) {
            sparseArray3.append(sparseArray.keyAt(i), new TextBlock((SparseArray) sparseArray.valueAt(i)));
        }
        return sparseArray3;
    }

    public final boolean isOperational() {
        return this.zzdf.isOperational();
    }

    public final void release() {
        super.release();
        this.zzdf.zzo();
    }
}
