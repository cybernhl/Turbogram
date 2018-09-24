package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.wallet.wobs.CommonWalletObject;
import com.google.android.gms.wallet.wobs.CommonWalletObject.zza;
import com.google.android.gms.wallet.wobs.LabelValueRow;
import com.google.android.gms.wallet.wobs.TextModuleData;
import com.google.android.gms.wallet.wobs.TimeInterval;
import com.google.android.gms.wallet.wobs.UriData;
import com.google.android.gms.wallet.wobs.WalletObjectMessage;
import java.util.ArrayList;
import java.util.Collection;

@Class(creator = "OfferWalletObjectCreator")
public final class OfferWalletObject extends AbstractSafeParcelable {
    public static final Creator<OfferWalletObject> CREATOR = new zzab();
    @VersionField(getter = "getVersionCode", id = 1)
    private final int versionCode;
    @Field(id = 4)
    CommonWalletObject zzbk;
    @Field(id = 2)
    String zzcf;
    @Field(id = 3)
    String zzdr;

    public final class Builder {
        private zza zzbr;
        private final /* synthetic */ OfferWalletObject zzds;

        private Builder(OfferWalletObject offerWalletObject) {
            this.zzds = offerWalletObject;
            this.zzbr = CommonWalletObject.zze();
        }

        public final Builder setId(String str) {
            this.zzbr.zza(str);
            this.zzds.zzcf = str;
            return this;
        }

        public final Builder setRedemptionCode(String str) {
            this.zzds.zzdr = str;
            return this;
        }

        public final Builder setIssuerName(String str) {
            this.zzbr.zzd(str);
            return this;
        }

        public final Builder setTitle(String str) {
            this.zzbr.zzc(str);
            return this;
        }

        public final Builder setBarcodeAlternateText(String str) {
            this.zzbr.zze(str);
            return this;
        }

        public final Builder setBarcodeType(String str) {
            this.zzbr.zzf(str);
            return this;
        }

        public final Builder setBarcodeValue(String str) {
            this.zzbr.zzg(str);
            return this;
        }

        @Deprecated
        public final Builder setBarcodeLabel(String str) {
            this.zzbr.zzh(str);
            return this;
        }

        public final Builder setClassId(String str) {
            this.zzbr.zzb(str);
            return this;
        }

        public final Builder setState(int i) {
            this.zzbr.zzc(i);
            return this;
        }

        public final Builder addMessages(Collection<WalletObjectMessage> collection) {
            this.zzbr.zza((Collection) collection);
            return this;
        }

        public final Builder addMessage(WalletObjectMessage walletObjectMessage) {
            this.zzbr.zza(walletObjectMessage);
            return this;
        }

        public final Builder setValidTimeInterval(TimeInterval timeInterval) {
            this.zzbr.zza(timeInterval);
            return this;
        }

        public final Builder addLocations(Collection<LatLng> collection) {
            this.zzbr.zzb((Collection) collection);
            return this;
        }

        public final Builder addLocation(LatLng latLng) {
            this.zzbr.zza(latLng);
            return this;
        }

        @Deprecated
        public final Builder setInfoModuleDataHexFontColor(String str) {
            this.zzbr.zzi(str);
            return this;
        }

        @Deprecated
        public final Builder setInfoModuleDataHexBackgroundColor(String str) {
            this.zzbr.zzj(str);
            return this;
        }

        public final Builder addInfoModuleDataLabelValueRows(Collection<LabelValueRow> collection) {
            this.zzbr.zzc((Collection) collection);
            return this;
        }

        public final Builder addInfoModuleDataLabelValueRow(LabelValueRow labelValueRow) {
            this.zzbr.zza(labelValueRow);
            return this;
        }

        public final Builder setInfoModuleDataShowLastUpdateTime(boolean z) {
            this.zzbr.zza(z);
            return this;
        }

        public final Builder addImageModuleDataMainImageUris(Collection<UriData> collection) {
            this.zzbr.zzd((Collection) collection);
            return this;
        }

        public final Builder addImageModuleDataMainImageUri(UriData uriData) {
            this.zzbr.zza(uriData);
            return this;
        }

        public final Builder addTextModulesData(Collection<TextModuleData> collection) {
            this.zzbr.zze((Collection) collection);
            return this;
        }

        public final Builder addTextModuleData(TextModuleData textModuleData) {
            this.zzbr.zza(textModuleData);
            return this;
        }

        public final Builder addLinksModuleDataUris(Collection<UriData> collection) {
            this.zzbr.zzf((Collection) collection);
            return this;
        }

        public final Builder addLinksModuleDataUri(UriData uriData) {
            this.zzbr.zzb(uriData);
            return this;
        }

        public final OfferWalletObject build() {
            this.zzds.zzbk = this.zzbr.zzf();
            return this.zzds;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final int getVersionCode() {
        return this.versionCode;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getVersionCode());
        SafeParcelWriter.writeString(parcel, 2, this.zzcf, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzdr, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzbk, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Constructor
    OfferWalletObject(@Param(id = 1) int i, @Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) CommonWalletObject commonWalletObject) {
        this.versionCode = i;
        this.zzdr = str2;
        if (i < 3) {
            this.zzbk = CommonWalletObject.zze().zza(str).zzf();
        } else {
            this.zzbk = commonWalletObject;
        }
    }

    OfferWalletObject() {
        this.versionCode = 3;
    }

    public final String getId() {
        return this.zzbk.getId();
    }

    public final String getRedemptionCode() {
        return this.zzdr;
    }

    public final String getClassId() {
        return this.zzbk.getClassId();
    }

    public final String getTitle() {
        return this.zzbk.getName();
    }

    public final String getIssuerName() {
        return this.zzbk.getIssuerName();
    }

    public final String getBarcodeAlternateText() {
        return this.zzbk.getBarcodeAlternateText();
    }

    public final String getBarcodeType() {
        return this.zzbk.getBarcodeType();
    }

    public final String getBarcodeValue() {
        return this.zzbk.getBarcodeValue();
    }

    @Deprecated
    public final String getBarcodeLabel() {
        return this.zzbk.getBarcodeLabel();
    }

    public final int getState() {
        return this.zzbk.getState();
    }

    public final ArrayList<WalletObjectMessage> getMessages() {
        return this.zzbk.getMessages();
    }

    public final TimeInterval getValidTimeInterval() {
        return this.zzbk.getValidTimeInterval();
    }

    public final ArrayList<LatLng> getLocations() {
        return this.zzbk.getLocations();
    }

    @Deprecated
    public final String getInfoModuleDataHexFontColor() {
        return this.zzbk.getInfoModuleDataHexFontColor();
    }

    @Deprecated
    public final String getInfoModuleDataHexBackgroundColor() {
        return this.zzbk.getInfoModuleDataHexBackgroundColor();
    }

    public final ArrayList<LabelValueRow> getInfoModuleDataLabelValueRows() {
        return this.zzbk.getInfoModuleDataLabelValueRows();
    }

    public final boolean getInfoModuleDataShowLastUpdateTime() {
        return this.zzbk.getInfoModuleDataShowLastUpdateTime();
    }

    public final ArrayList<UriData> getImageModuleDataMainImageUris() {
        return this.zzbk.getImageModuleDataMainImageUris();
    }

    public final ArrayList<TextModuleData> getTextModulesData() {
        return this.zzbk.getTextModulesData();
    }

    public final ArrayList<UriData> getLinksModuleDataUris() {
        return this.zzbk.getLinksModuleDataUris();
    }
}
