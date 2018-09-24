package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.identity.intents.model.UserAddress;

@Class(creator = "FullWalletCreator")
@Reserved({1})
public final class FullWallet extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<FullWallet> CREATOR = new zzk();
    @Field(id = 2)
    private String zzax;
    @Field(id = 3)
    private String zzay;
    @Field(id = 4)
    private ProxyCard zzaz;
    @Field(id = 5)
    private String zzba;
    @Field(id = 6)
    private zza zzbb;
    @Field(id = 7)
    private zza zzbc;
    @Field(id = 8)
    private String[] zzbd;
    @Field(id = 9)
    private UserAddress zzbe;
    @Field(id = 10)
    private UserAddress zzbf;
    @Field(id = 11)
    private InstrumentInfo[] zzbg;
    @Field(id = 12)
    private PaymentMethodToken zzbh;

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzax, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzay, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzaz, i, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzba, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzbb, i, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzbc, i, false);
        SafeParcelWriter.writeStringArray(parcel, 8, this.zzbd, false);
        SafeParcelWriter.writeParcelable(parcel, 9, this.zzbe, i, false);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzbf, i, false);
        SafeParcelWriter.writeTypedArray(parcel, 11, this.zzbg, i, false);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzbh, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Constructor
    FullWallet(@Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) ProxyCard proxyCard, @Param(id = 5) String str3, @Param(id = 6) zza zza, @Param(id = 7) zza zza2, @Param(id = 8) String[] strArr, @Param(id = 9) UserAddress userAddress, @Param(id = 10) UserAddress userAddress2, @Param(id = 11) InstrumentInfo[] instrumentInfoArr, @Param(id = 12) PaymentMethodToken paymentMethodToken) {
        this.zzax = str;
        this.zzay = str2;
        this.zzaz = proxyCard;
        this.zzba = str3;
        this.zzbb = zza;
        this.zzbc = zza2;
        this.zzbd = strArr;
        this.zzbe = userAddress;
        this.zzbf = userAddress2;
        this.zzbg = instrumentInfoArr;
        this.zzbh = paymentMethodToken;
    }

    private FullWallet() {
    }

    public final String getGoogleTransactionId() {
        return this.zzax;
    }

    public final String getMerchantTransactionId() {
        return this.zzay;
    }

    public final ProxyCard getProxyCard() {
        return this.zzaz;
    }

    public final String getEmail() {
        return this.zzba;
    }

    public final String[] getPaymentDescriptions() {
        return this.zzbd;
    }

    public final UserAddress getBuyerBillingAddress() {
        return this.zzbe;
    }

    public final UserAddress getBuyerShippingAddress() {
        return this.zzbf;
    }

    public final InstrumentInfo[] getInstrumentInfos() {
        return this.zzbg;
    }

    public final PaymentMethodToken getPaymentMethodToken() {
        return this.zzbh;
    }
}
