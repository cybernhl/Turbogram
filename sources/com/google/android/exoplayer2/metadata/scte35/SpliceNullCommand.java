package com.google.android.exoplayer2.metadata.scte35;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class SpliceNullCommand extends SpliceCommand {
    public static final Creator<SpliceNullCommand> CREATOR = new C03011();

    /* renamed from: com.google.android.exoplayer2.metadata.scte35.SpliceNullCommand$1 */
    static class C03011 implements Creator<SpliceNullCommand> {
        C03011() {
        }

        public SpliceNullCommand createFromParcel(Parcel in) {
            return new SpliceNullCommand();
        }

        public SpliceNullCommand[] newArray(int size) {
            return new SpliceNullCommand[size];
        }
    }

    public void writeToParcel(Parcel dest, int flags) {
    }
}
