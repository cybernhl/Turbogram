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
import java.util.ArrayList;
import java.util.List;

@Class(creator = "CartCreator")
@Reserved({1})
public final class Cart extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<Cart> CREATOR = new zzg();
    @Field(id = 2)
    String zzao;
    @Field(id = 3)
    String zzap;
    @Field(defaultValueUnchecked = "new java.util.ArrayList<LineItem>()", id = 4)
    ArrayList<LineItem> zzaq;

    public final class Builder {
        private final /* synthetic */ Cart zzar;

        private Builder(Cart cart) {
            this.zzar = cart;
        }

        public final Builder setTotalPrice(String str) {
            this.zzar.zzao = str;
            return this;
        }

        public final Builder setCurrencyCode(String str) {
            this.zzar.zzap = str;
            return this;
        }

        public final Builder setLineItems(List<LineItem> list) {
            this.zzar.zzaq.clear();
            this.zzar.zzaq.addAll(list);
            return this;
        }

        public final Builder addLineItem(LineItem lineItem) {
            this.zzar.zzaq.add(lineItem);
            return this;
        }

        public final Cart build() {
            return this.zzar;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzao, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzap, false);
        SafeParcelWriter.writeTypedList(parcel, 4, this.zzaq, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Constructor
    Cart(@Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) ArrayList<LineItem> arrayList) {
        this.zzao = str;
        this.zzap = str2;
        this.zzaq = arrayList;
    }

    Cart() {
        this.zzaq = new ArrayList();
    }

    public final String getTotalPrice() {
        return this.zzao;
    }

    public final String getCurrencyCode() {
        return this.zzap;
    }

    public final ArrayList<LineItem> getLineItems() {
        return this.zzaq;
    }
}
