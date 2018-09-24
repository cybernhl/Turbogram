package de.jurihock.voicesmith;

import android.media.AudioRecord;

public class DataHelper {
    /* renamed from: a */
    public int f395a = -1;
    private AudioRecord audioRecord = null;
    /* renamed from: b */
    public boolean f396b;

    public DataHelper(AudioRecord audioRecord) {
        this.audioRecord = audioRecord;
    }

    /* renamed from: a */
    public int m594a(short[] sArr, int i, int i2) {
        return i2 == 0 ? 0 : this.audioRecord.read(sArr, i, i2);
    }

    /* renamed from: a */
    public void m595a(short[] sArr) {
        this.f395a = 0;
        this.f396b = true;
        m596b(sArr);
    }

    /* renamed from: b */
    public final boolean m596b(short[] sArr) {
        if (sArr == null || sArr.length == 0) {
            return false;
        }
        int i = 0;
        do {
            int a = m594a(sArr, i, sArr.length - i);
            this.f395a = a;
            if (a <= 0) {
                return false;
            }
            i += a;
        } while (i < sArr.length);
        return true;
    }
}
