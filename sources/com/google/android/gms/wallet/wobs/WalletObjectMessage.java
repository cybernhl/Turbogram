package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "WalletObjectMessageCreator")
@Reserved({1})
public final class WalletObjectMessage extends AbstractSafeParcelable {
    public static final Creator<WalletObjectMessage> CREATOR = new zzn();
    @Field(id = 2)
    String zzhb;
    @Field(id = 3)
    String zzhc;
    @Field(id = 4)
    TimeInterval zzhg;
    @Field(id = 5)
    @Deprecated
    UriData zzhh;
    @Field(id = 6)
    @Deprecated
    UriData zzhi;

    public final class Builder {
        private final /* synthetic */ WalletObjectMessage zzhj;

        private Builder(WalletObjectMessage walletObjectMessage) {
            this.zzhj = walletObjectMessage;
        }

        public final Builder setHeader(String str) {
            this.zzhj.zzhb = str;
            return this;
        }

        public final Builder setBody(String str) {
            this.zzhj.zzhc = str;
            return this;
        }

        public final Builder setDisplayInterval(TimeInterval timeInterval) {
            this.zzhj.zzhg = timeInterval;
            return this;
        }

        @Deprecated
        public final Builder setActionUri(UriData uriData) {
            this.zzhj.zzhh = uriData;
            return this;
        }

        @Deprecated
        public final Builder setImageUri(UriData uriData) {
            this.zzhj.zzhi = uriData;
            return this;
        }

        public final WalletObjectMessage build() {
            return this.zzhj;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzhb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzhc, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzhg, i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzhh, i, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzhi, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Constructor
    WalletObjectMessage(@Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) TimeInterval timeInterval, @Param(id = 5) UriData uriData, @Param(id = 6) UriData uriData2) {
        this.zzhb = str;
        this.zzhc = str2;
        this.zzhg = timeInterval;
        this.zzhh = uriData;
        this.zzhi = uriData2;
    }

    WalletObjectMessage() {
    }

    public final String getHeader() {
        return this.zzhb;
    }

    public final String getBody() {
        return this.zzhc;
    }

    public final TimeInterval getDisplayInterval() {
        return this.zzhg;
    }

    @Deprecated
    public final UriData getActionUri() {
        return this.zzhh;
    }

    @Deprecated
    public final UriData getImageUri() {
        return this.zzhi;
    }
}
