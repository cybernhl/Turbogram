package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
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

@Class(creator = "GiftCardWalletObjectCreator")
@Reserved({1})
public final class GiftCardWalletObject extends AbstractSafeParcelable {
    public static final Creator<GiftCardWalletObject> CREATOR = new zzo();
    @Field(id = 4)
    String pin;
    @Field(id = 2)
    CommonWalletObject zzbk = CommonWalletObject.zze().zzf();
    @Field(id = 3)
    String zzbl;
    @Field(id = 5)
    @Deprecated
    String zzbm;
    @Field(id = 6)
    long zzbn;
    @Field(id = 7)
    String zzbo;
    @Field(id = 8)
    long zzbp;
    @Field(id = 9)
    String zzbq;

    public final class Builder {
        private zza zzbr;
        private final /* synthetic */ GiftCardWalletObject zzbs;

        private Builder(GiftCardWalletObject giftCardWalletObject) {
            this.zzbs = giftCardWalletObject;
            this.zzbr = CommonWalletObject.zze();
        }

        public final Builder setCardNumber(String str) {
            this.zzbs.zzbl = str;
            return this;
        }

        public final Builder setPin(String str) {
            this.zzbs.pin = str;
            return this;
        }

        public final Builder setBalanceMicros(long j) {
            this.zzbs.zzbn = j;
            return this;
        }

        public final Builder setBalanceCurrencyCode(String str) {
            this.zzbs.zzbo = str;
            return this;
        }

        public final Builder setBalanceUpdateTime(long j) {
            this.zzbs.zzbp = j;
            return this;
        }

        public final Builder setEventNumber(String str) {
            this.zzbs.zzbq = str;
            return this;
        }

        @Deprecated
        public final Builder setCardIdentifier(String str) {
            this.zzbs.zzbm = str;
            return this;
        }

        public final Builder setId(String str) {
            this.zzbr.zza(str);
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

        public final GiftCardWalletObject build() {
            boolean z;
            boolean z2 = true;
            Preconditions.checkArgument(!TextUtils.isEmpty(this.zzbs.zzbl), "Card number is required.");
            this.zzbs.zzbk = this.zzbr.zzf();
            if (TextUtils.isEmpty(this.zzbs.zzbk.getName())) {
                z = false;
            } else {
                z = true;
            }
            Preconditions.checkArgument(z, "Card name is required.");
            if (TextUtils.isEmpty(this.zzbs.zzbk.getIssuerName())) {
                z2 = false;
            }
            Preconditions.checkArgument(z2, "Card issuer name is required.");
            return this.zzbs;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzbk, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzbl, false);
        SafeParcelWriter.writeString(parcel, 4, this.pin, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzbm, false);
        SafeParcelWriter.writeLong(parcel, 6, this.zzbn);
        SafeParcelWriter.writeString(parcel, 7, this.zzbo, false);
        SafeParcelWriter.writeLong(parcel, 8, this.zzbp);
        SafeParcelWriter.writeString(parcel, 9, this.zzbq, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    GiftCardWalletObject() {
    }

    @Constructor
    GiftCardWalletObject(@Param(id = 2) CommonWalletObject commonWalletObject, @Param(id = 3) String str, @Param(id = 4) String str2, @Param(id = 5) String str3, @Param(id = 6) long j, @Param(id = 7) String str4, @Param(id = 8) long j2, @Param(id = 9) String str5) {
        this.zzbk = commonWalletObject;
        this.zzbl = str;
        this.pin = str2;
        this.zzbn = j;
        this.zzbo = str4;
        this.zzbp = j2;
        this.zzbq = str5;
        this.zzbm = str3;
    }

    public final String getId() {
        return this.zzbk.getId();
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

    public final String getCardNumber() {
        return this.zzbl;
    }

    public final String getPin() {
        return this.pin;
    }

    public final long getBalanceMicros() {
        return this.zzbn;
    }

    public final String getBalanceCurrencyCode() {
        return this.zzbo;
    }

    public final long getBalanceUpdateTime() {
        return this.zzbp;
    }

    public final String getEventNumber() {
        return this.zzbq;
    }

    @Deprecated
    public final String getCardIdentifier() {
        return this.zzbm;
    }
}
