package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.Collection;

@KeepName
@Class(creator = "CommonWalletObjectCreator")
@Reserved({1})
public class CommonWalletObject extends AbstractSafeParcelable {
    public static final Creator<CommonWalletObject> CREATOR = new zzb();
    @Field(id = 4)
    String name;
    @Field(id = 10)
    int state;
    @Field(id = 2)
    String zzcf;
    @Field(id = 5)
    String zzch;
    @Field(id = 6)
    String zzck;
    @Field(id = 7)
    String zzcl;
    @Field(id = 8)
    String zzcm;
    @Field(id = 9)
    @Deprecated
    String zzcn;
    @Field(id = 3)
    String zzco;
    @Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 11)
    ArrayList<WalletObjectMessage> zzcp;
    @Field(id = 12)
    TimeInterval zzcq;
    @Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 13)
    ArrayList<LatLng> zzcr;
    @Field(id = 14)
    @Deprecated
    String zzcs;
    @Field(id = 15)
    @Deprecated
    String zzct;
    @Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 16)
    ArrayList<LabelValueRow> zzcu;
    @Field(id = 17)
    boolean zzcv;
    @Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 18)
    ArrayList<UriData> zzcw;
    @Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 19)
    ArrayList<TextModuleData> zzcx;
    @Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 20)
    ArrayList<UriData> zzcy;

    public final class zza {
        private final /* synthetic */ CommonWalletObject zzgo;

        private zza(CommonWalletObject commonWalletObject) {
            this.zzgo = commonWalletObject;
        }

        public final zza zza(String str) {
            this.zzgo.zzcf = str;
            return this;
        }

        public final zza zzb(String str) {
            this.zzgo.zzco = str;
            return this;
        }

        public final zza zzc(String str) {
            this.zzgo.name = str;
            return this;
        }

        public final zza zzd(String str) {
            this.zzgo.zzch = str;
            return this;
        }

        public final zza zze(String str) {
            this.zzgo.zzck = str;
            return this;
        }

        public final zza zzf(String str) {
            this.zzgo.zzcl = str;
            return this;
        }

        public final zza zzg(String str) {
            this.zzgo.zzcm = str;
            return this;
        }

        @Deprecated
        public final zza zzh(String str) {
            this.zzgo.zzcn = str;
            return this;
        }

        public final zza zzc(int i) {
            this.zzgo.state = i;
            return this;
        }

        public final zza zza(Collection<WalletObjectMessage> collection) {
            this.zzgo.zzcp.addAll(collection);
            return this;
        }

        public final zza zza(WalletObjectMessage walletObjectMessage) {
            this.zzgo.zzcp.add(walletObjectMessage);
            return this;
        }

        public final zza zza(TimeInterval timeInterval) {
            this.zzgo.zzcq = timeInterval;
            return this;
        }

        public final zza zzb(Collection<LatLng> collection) {
            this.zzgo.zzcr.addAll(collection);
            return this;
        }

        public final zza zza(LatLng latLng) {
            this.zzgo.zzcr.add(latLng);
            return this;
        }

        @Deprecated
        public final zza zzi(String str) {
            this.zzgo.zzcs = str;
            return this;
        }

        @Deprecated
        public final zza zzj(String str) {
            this.zzgo.zzct = str;
            return this;
        }

        public final zza zzc(Collection<LabelValueRow> collection) {
            this.zzgo.zzcu.addAll(collection);
            return this;
        }

        public final zza zza(LabelValueRow labelValueRow) {
            this.zzgo.zzcu.add(labelValueRow);
            return this;
        }

        public final zza zza(boolean z) {
            this.zzgo.zzcv = z;
            return this;
        }

        public final zza zzd(Collection<UriData> collection) {
            this.zzgo.zzcw.addAll(collection);
            return this;
        }

        public final zza zza(UriData uriData) {
            this.zzgo.zzcw.add(uriData);
            return this;
        }

        public final zza zze(Collection<TextModuleData> collection) {
            this.zzgo.zzcx.addAll(collection);
            return this;
        }

        public final zza zza(TextModuleData textModuleData) {
            this.zzgo.zzcx.add(textModuleData);
            return this;
        }

        public final zza zzf(Collection<UriData> collection) {
            this.zzgo.zzcy.addAll(collection);
            return this;
        }

        public final zza zzb(UriData uriData) {
            this.zzgo.zzcy.add(uriData);
            return this;
        }

        public final CommonWalletObject zzf() {
            return this.zzgo;
        }
    }

    public static zza zze() {
        return new zza();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzcf, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzco, false);
        SafeParcelWriter.writeString(parcel, 4, this.name, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzch, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzck, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzcl, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzcm, false);
        SafeParcelWriter.writeString(parcel, 9, this.zzcn, false);
        SafeParcelWriter.writeInt(parcel, 10, this.state);
        SafeParcelWriter.writeTypedList(parcel, 11, this.zzcp, false);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzcq, i, false);
        SafeParcelWriter.writeTypedList(parcel, 13, this.zzcr, false);
        SafeParcelWriter.writeString(parcel, 14, this.zzcs, false);
        SafeParcelWriter.writeString(parcel, 15, this.zzct, false);
        SafeParcelWriter.writeTypedList(parcel, 16, this.zzcu, false);
        SafeParcelWriter.writeBoolean(parcel, 17, this.zzcv);
        SafeParcelWriter.writeTypedList(parcel, 18, this.zzcw, false);
        SafeParcelWriter.writeTypedList(parcel, 19, this.zzcx, false);
        SafeParcelWriter.writeTypedList(parcel, 20, this.zzcy, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Constructor
    CommonWalletObject(@Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) String str3, @Param(id = 5) String str4, @Param(id = 6) String str5, @Param(id = 7) String str6, @Param(id = 8) String str7, @Param(id = 9) String str8, @Param(id = 10) int i, @Param(id = 11) ArrayList<WalletObjectMessage> arrayList, @Param(id = 12) TimeInterval timeInterval, @Param(id = 13) ArrayList<LatLng> arrayList2, @Param(id = 14) String str9, @Param(id = 15) String str10, @Param(id = 16) ArrayList<LabelValueRow> arrayList3, @Param(id = 17) boolean z, @Param(id = 18) ArrayList<UriData> arrayList4, @Param(id = 19) ArrayList<TextModuleData> arrayList5, @Param(id = 20) ArrayList<UriData> arrayList6) {
        this.zzcf = str;
        this.zzco = str2;
        this.name = str3;
        this.zzch = str4;
        this.zzck = str5;
        this.zzcl = str6;
        this.zzcm = str7;
        this.zzcn = str8;
        this.state = i;
        this.zzcp = arrayList;
        this.zzcq = timeInterval;
        this.zzcr = arrayList2;
        this.zzcs = str9;
        this.zzct = str10;
        this.zzcu = arrayList3;
        this.zzcv = z;
        this.zzcw = arrayList4;
        this.zzcx = arrayList5;
        this.zzcy = arrayList6;
    }

    CommonWalletObject() {
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

    public final String getClassId() {
        return this.zzco;
    }

    public final String getName() {
        return this.name;
    }

    public final String getIssuerName() {
        return this.zzch;
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
}
