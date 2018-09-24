package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.wallet.wobs.LabelValueRow;
import com.google.android.gms.wallet.wobs.LoyaltyPoints;
import com.google.android.gms.wallet.wobs.TextModuleData;
import com.google.android.gms.wallet.wobs.TimeInterval;
import com.google.android.gms.wallet.wobs.UriData;
import com.google.android.gms.wallet.wobs.WalletObjectMessage;
import java.util.ArrayList;
import java.util.Collection;

@Class(creator = "LoyaltyWalletObjectCreator")
@Reserved({1})
public final class LoyaltyWalletObject extends AbstractSafeParcelable {
    public static final Creator<LoyaltyWalletObject> CREATOR = new zzv();
    @Field(id = 12)
    int state;
    @Field(id = 2)
    String zzcf;
    @Field(id = 3)
    String zzcg;
    @Field(id = 4)
    String zzch;
    @Field(id = 5)
    String zzci;
    @Field(id = 6)
    String zzcj;
    @Field(id = 7)
    String zzck;
    @Field(id = 8)
    String zzcl;
    @Field(id = 9)
    String zzcm;
    @Field(id = 10)
    @Deprecated
    String zzcn;
    @Field(id = 11)
    String zzco;
    @Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 13)
    ArrayList<WalletObjectMessage> zzcp;
    @Field(id = 14)
    TimeInterval zzcq;
    @Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 15)
    ArrayList<LatLng> zzcr;
    @Field(id = 16)
    @Deprecated
    String zzcs;
    @Field(id = 17)
    @Deprecated
    String zzct;
    @Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 18)
    ArrayList<LabelValueRow> zzcu;
    @Field(id = 19)
    boolean zzcv;
    @Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 20)
    ArrayList<UriData> zzcw;
    @Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 21)
    ArrayList<TextModuleData> zzcx;
    @Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 22)
    ArrayList<UriData> zzcy;
    @Field(id = 23)
    LoyaltyPoints zzcz;

    public final class Builder {
        private final /* synthetic */ LoyaltyWalletObject zzda;

        private Builder(LoyaltyWalletObject loyaltyWalletObject) {
            this.zzda = loyaltyWalletObject;
        }

        public final Builder setId(String str) {
            this.zzda.zzcf = str;
            return this;
        }

        public final Builder setAccountId(String str) {
            this.zzda.zzcg = str;
            return this;
        }

        public final Builder setIssuerName(String str) {
            this.zzda.zzch = str;
            return this;
        }

        public final Builder setProgramName(String str) {
            this.zzda.zzci = str;
            return this;
        }

        public final Builder setAccountName(String str) {
            this.zzda.zzcj = str;
            return this;
        }

        public final Builder setBarcodeAlternateText(String str) {
            this.zzda.zzck = str;
            return this;
        }

        public final Builder setBarcodeType(String str) {
            this.zzda.zzcl = str;
            return this;
        }

        public final Builder setBarcodeValue(String str) {
            this.zzda.zzcm = str;
            return this;
        }

        @Deprecated
        public final Builder setBarcodeLabel(String str) {
            this.zzda.zzcn = str;
            return this;
        }

        public final Builder setClassId(String str) {
            this.zzda.zzco = str;
            return this;
        }

        public final Builder setState(int i) {
            this.zzda.state = i;
            return this;
        }

        public final Builder addMessages(Collection<WalletObjectMessage> collection) {
            this.zzda.zzcp.addAll(collection);
            return this;
        }

        public final Builder addMessage(WalletObjectMessage walletObjectMessage) {
            this.zzda.zzcp.add(walletObjectMessage);
            return this;
        }

        public final Builder setValidTimeInterval(TimeInterval timeInterval) {
            this.zzda.zzcq = timeInterval;
            return this;
        }

        public final Builder addLocations(Collection<LatLng> collection) {
            this.zzda.zzcr.addAll(collection);
            return this;
        }

        public final Builder addLocation(LatLng latLng) {
            this.zzda.zzcr.add(latLng);
            return this;
        }

        @Deprecated
        public final Builder setInfoModuleDataHexFontColor(String str) {
            this.zzda.zzcs = str;
            return this;
        }

        @Deprecated
        public final Builder setInfoModuleDataHexBackgroundColor(String str) {
            this.zzda.zzct = str;
            return this;
        }

        public final Builder addInfoModuleDataLabelValueRows(Collection<LabelValueRow> collection) {
            this.zzda.zzcu.addAll(collection);
            return this;
        }

        public final Builder addInfoModuleDataLabeValueRow(LabelValueRow labelValueRow) {
            this.zzda.zzcu.add(labelValueRow);
            return this;
        }

        public final Builder setInfoModuleDataShowLastUpdateTime(boolean z) {
            this.zzda.zzcv = z;
            return this;
        }

        public final Builder addImageModuleDataMainImageUris(Collection<UriData> collection) {
            this.zzda.zzcw.addAll(collection);
            return this;
        }

        public final Builder addImageModuleDataMainImageUri(UriData uriData) {
            this.zzda.zzcw.add(uriData);
            return this;
        }

        public final Builder addTextModulesData(Collection<TextModuleData> collection) {
            this.zzda.zzcx.addAll(collection);
            return this;
        }

        public final Builder addTextModuleData(TextModuleData textModuleData) {
            this.zzda.zzcx.add(textModuleData);
            return this;
        }

        public final Builder addLinksModuleDataUris(Collection<UriData> collection) {
            this.zzda.zzcy.addAll(collection);
            return this;
        }

        public final Builder addLinksModuleDataUri(UriData uriData) {
            this.zzda.zzcy.add(uriData);
            return this;
        }

        public final Builder setLoyaltyPoints(LoyaltyPoints loyaltyPoints) {
            this.zzda.zzcz = loyaltyPoints;
            return this;
        }

        public final LoyaltyWalletObject build() {
            return this.zzda;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzcf, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzcg, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzch, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzci, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzcj, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzck, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzcl, false);
        SafeParcelWriter.writeString(parcel, 9, this.zzcm, false);
        SafeParcelWriter.writeString(parcel, 10, this.zzcn, false);
        SafeParcelWriter.writeString(parcel, 11, this.zzco, false);
        SafeParcelWriter.writeInt(parcel, 12, this.state);
        SafeParcelWriter.writeTypedList(parcel, 13, this.zzcp, false);
        SafeParcelWriter.writeParcelable(parcel, 14, this.zzcq, i, false);
        SafeParcelWriter.writeTypedList(parcel, 15, this.zzcr, false);
        SafeParcelWriter.writeString(parcel, 16, this.zzcs, false);
        SafeParcelWriter.writeString(parcel, 17, this.zzct, false);
        SafeParcelWriter.writeTypedList(parcel, 18, this.zzcu, false);
        SafeParcelWriter.writeBoolean(parcel, 19, this.zzcv);
        SafeParcelWriter.writeTypedList(parcel, 20, this.zzcw, false);
        SafeParcelWriter.writeTypedList(parcel, 21, this.zzcx, false);
        SafeParcelWriter.writeTypedList(parcel, 22, this.zzcy, false);
        SafeParcelWriter.writeParcelable(parcel, 23, this.zzcz, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Constructor
    LoyaltyWalletObject(@Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) String str3, @Param(id = 5) String str4, @Param(id = 6) String str5, @Param(id = 7) String str6, @Param(id = 8) String str7, @Param(id = 9) String str8, @Param(id = 10) String str9, @Param(id = 11) String str10, @Param(id = 12) int i, @Param(id = 13) ArrayList<WalletObjectMessage> arrayList, @Param(id = 14) TimeInterval timeInterval, @Param(id = 15) ArrayList<LatLng> arrayList2, @Param(id = 16) String str11, @Param(id = 17) String str12, @Param(id = 18) ArrayList<LabelValueRow> arrayList3, @Param(id = 19) boolean z, @Param(id = 20) ArrayList<UriData> arrayList4, @Param(id = 21) ArrayList<TextModuleData> arrayList5, @Param(id = 22) ArrayList<UriData> arrayList6, @Param(id = 23) LoyaltyPoints loyaltyPoints) {
        this.zzcf = str;
        this.zzcg = str2;
        this.zzch = str3;
        this.zzci = str4;
        this.zzcj = str5;
        this.zzck = str6;
        this.zzcl = str7;
        this.zzcm = str8;
        this.zzcn = str9;
        this.zzco = str10;
        this.state = i;
        this.zzcp = arrayList;
        this.zzcq = timeInterval;
        this.zzcr = arrayList2;
        this.zzcs = str11;
        this.zzct = str12;
        this.zzcu = arrayList3;
        this.zzcv = z;
        this.zzcw = arrayList4;
        this.zzcx = arrayList5;
        this.zzcy = arrayList6;
        this.zzcz = loyaltyPoints;
    }

    LoyaltyWalletObject() {
        this.zzcp = ArrayUtils.newArrayList();
        this.zzcr = ArrayUtils.newArrayList();
        this.zzcu = ArrayUtils.newArrayList();
        this.zzcw = ArrayUtils.newArrayList();
        this.zzcx = ArrayUtils.newArrayList();
        this.zzcy = ArrayUtils.newArrayList();
    }

    public final String getId() {
        return this.zzcf;
    }

    public final String getAccountId() {
        return this.zzcg;
    }

    public final String getIssuerName() {
        return this.zzch;
    }

    public final String getProgramName() {
        return this.zzci;
    }

    public final String getAccountName() {
        return this.zzcj;
    }

    public final String getBarcodeAlternateText() {
        return this.zzck;
    }

    public final String getBarcodeType() {
        return this.zzcl;
    }

    public final String getBarcodeValue() {
        return this.zzcm;
    }

    @Deprecated
    public final String getBarcodeLabel() {
        return this.zzcn;
    }

    public final String getClassId() {
        return this.zzco;
    }

    public final int getState() {
        return this.state;
    }

    public final ArrayList<WalletObjectMessage> getMessages() {
        return this.zzcp;
    }

    public final TimeInterval getValidTimeInterval() {
        return this.zzcq;
    }

    public final ArrayList<LatLng> getLocations() {
        return this.zzcr;
    }

    @Deprecated
    public final String getInfoModuleDataHexFontColor() {
        return this.zzcs;
    }

    @Deprecated
    public final String getInfoModuleDataHexBackgroundColor() {
        return this.zzct;
    }

    public final ArrayList<LabelValueRow> getInfoModuleDataLabelValueRows() {
        return this.zzcu;
    }

    public final boolean getInfoModuleDataShowLastUpdateTime() {
        return this.zzcv;
    }

    public final ArrayList<UriData> getImageModuleDataMainImageUris() {
        return this.zzcw;
    }

    public final ArrayList<TextModuleData> getTextModulesData() {
        return this.zzcx;
    }

    public final ArrayList<UriData> getLinksModuleDataUris() {
        return this.zzcy;
    }

    public final LoyaltyPoints getLoyaltyPoints() {
        return this.zzcz;
    }
}
