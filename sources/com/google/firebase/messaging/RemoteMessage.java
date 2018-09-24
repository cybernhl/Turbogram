package com.google.firebase.messaging;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;
import java.util.Map.Entry;

@Class(creator = "RemoteMessageCreator")
@Reserved({1})
public final class RemoteMessage extends AbstractSafeParcelable {
    public static final Creator<RemoteMessage> CREATOR = new zzc();
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_NORMAL = 2;
    public static final int PRIORITY_UNKNOWN = 0;
    @Field(id = 2)
    Bundle zzdp;
    private Map<String, String> zzdq;
    private Notification zzdr;

    public static class Builder {
        private final Bundle zzdp = new Bundle();
        private final Map<String, String> zzdq = new ArrayMap();

        public Builder(String str) {
            if (TextUtils.isEmpty(str)) {
                String str2 = "Invalid to: ";
                String valueOf = String.valueOf(str);
                throw new IllegalArgumentException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            }
            this.zzdp.putString("google.to", str);
        }

        public RemoteMessage build() {
            Bundle bundle = new Bundle();
            for (Entry entry : this.zzdq.entrySet()) {
                bundle.putString((String) entry.getKey(), (String) entry.getValue());
            }
            bundle.putAll(this.zzdp);
            this.zzdp.remove("from");
            return new RemoteMessage(bundle);
        }

        public Builder addData(String str, String str2) {
            this.zzdq.put(str, str2);
            return this;
        }

        public Builder setData(Map<String, String> map) {
            this.zzdq.clear();
            this.zzdq.putAll(map);
            return this;
        }

        public Builder clearData() {
            this.zzdq.clear();
            return this;
        }

        public Builder setMessageId(String str) {
            this.zzdp.putString("google.message_id", str);
            return this;
        }

        public Builder setMessageType(String str) {
            this.zzdp.putString("message_type", str);
            return this;
        }

        public Builder setTtl(@IntRange(from = 0, to = 86400) int i) {
            this.zzdp.putString("google.ttl", String.valueOf(i));
            return this;
        }

        public Builder setCollapseKey(String str) {
            this.zzdp.putString("collapse_key", str);
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MessagePriority {
    }

    public static class Notification {
        private final String tag;
        private final String zzds;
        private final String zzdt;
        private final String[] zzdu;
        private final String zzdv;
        private final String zzdw;
        private final String[] zzdx;
        private final String zzdy;
        private final String zzdz;
        private final String zzea;
        private final String zzeb;
        private final Uri zzec;

        private Notification(Bundle bundle) {
            this.zzds = zza.zza(bundle, "gcm.n.title");
            this.zzdt = zza.zzb(bundle, "gcm.n.title");
            this.zzdu = zze(bundle, "gcm.n.title");
            this.zzdv = zza.zza(bundle, "gcm.n.body");
            this.zzdw = zza.zzb(bundle, "gcm.n.body");
            this.zzdx = zze(bundle, "gcm.n.body");
            this.zzdy = zza.zza(bundle, "gcm.n.icon");
            this.zzdz = zza.zzi(bundle);
            this.tag = zza.zza(bundle, "gcm.n.tag");
            this.zzea = zza.zza(bundle, "gcm.n.color");
            this.zzeb = zza.zza(bundle, "gcm.n.click_action");
            this.zzec = zza.zzg(bundle);
        }

        private static String[] zze(Bundle bundle, String str) {
            Object[] zzc = zza.zzc(bundle, str);
            if (zzc == null) {
                return null;
            }
            String[] strArr = new String[zzc.length];
            for (int i = 0; i < zzc.length; i++) {
                strArr[i] = String.valueOf(zzc[i]);
            }
            return strArr;
        }

        @Nullable
        public String getTitle() {
            return this.zzds;
        }

        @Nullable
        public String getTitleLocalizationKey() {
            return this.zzdt;
        }

        @Nullable
        public String[] getTitleLocalizationArgs() {
            return this.zzdu;
        }

        @Nullable
        public String getBody() {
            return this.zzdv;
        }

        @Nullable
        public String getBodyLocalizationKey() {
            return this.zzdw;
        }

        @Nullable
        public String[] getBodyLocalizationArgs() {
            return this.zzdx;
        }

        @Nullable
        public String getIcon() {
            return this.zzdy;
        }

        @Nullable
        public String getSound() {
            return this.zzdz;
        }

        @Nullable
        public String getTag() {
            return this.tag;
        }

        @Nullable
        public String getColor() {
            return this.zzea;
        }

        @Nullable
        public String getClickAction() {
            return this.zzeb;
        }

        @Nullable
        public Uri getLink() {
            return this.zzec;
        }
    }

    @Constructor
    public RemoteMessage(@Param(id = 2) Bundle bundle) {
        this.zzdp = bundle;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 2, this.zzdp, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Nullable
    public final String getFrom() {
        return this.zzdp.getString("from");
    }

    @Nullable
    public final String getTo() {
        return this.zzdp.getString("google.to");
    }

    public final Map<String, String> getData() {
        if (this.zzdq == null) {
            Bundle bundle = this.zzdp;
            Map arrayMap = new ArrayMap();
            for (String str : bundle.keySet()) {
                Object obj = bundle.get(str);
                if (obj instanceof String) {
                    String str2 = (String) obj;
                    if (!(str.startsWith("google.") || str.startsWith("gcm.") || str.equals("from") || str.equals("message_type") || str.equals("collapse_key"))) {
                        arrayMap.put(str, str2);
                    }
                }
            }
            this.zzdq = arrayMap;
        }
        return this.zzdq;
    }

    @Nullable
    public final String getCollapseKey() {
        return this.zzdp.getString("collapse_key");
    }

    @Nullable
    public final String getMessageId() {
        String string = this.zzdp.getString("google.message_id");
        if (string == null) {
            return this.zzdp.getString("message_id");
        }
        return string;
    }

    @Nullable
    public final String getMessageType() {
        return this.zzdp.getString("message_type");
    }

    public final long getSentTime() {
        Object obj = this.zzdp.get("google.sent_time");
        if (obj instanceof Long) {
            return ((Long) obj).longValue();
        }
        if (obj instanceof String) {
            try {
                return Long.parseLong((String) obj);
            } catch (NumberFormatException e) {
                String valueOf = String.valueOf(obj);
                Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(valueOf).length() + 19).append("Invalid sent time: ").append(valueOf).toString());
            }
        }
        return 0;
    }

    public final int getTtl() {
        Object obj = this.zzdp.get("google.ttl");
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        if (obj instanceof String) {
            try {
                return Integer.parseInt((String) obj);
            } catch (NumberFormatException e) {
                String valueOf = String.valueOf(obj);
                Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(valueOf).length() + 13).append("Invalid TTL: ").append(valueOf).toString());
            }
        }
        return 0;
    }

    public final int getOriginalPriority() {
        String string = this.zzdp.getString("google.original_priority");
        if (string == null) {
            string = this.zzdp.getString("google.priority");
        }
        return zzm(string);
    }

    public final int getPriority() {
        String string = this.zzdp.getString("google.delivered_priority");
        if (string == null) {
            if ("1".equals(this.zzdp.getString("google.priority_reduced"))) {
                return 2;
            }
            string = this.zzdp.getString("google.priority");
        }
        return zzm(string);
    }

    private static int zzm(String str) {
        if ("high".equals(str)) {
            return 1;
        }
        if ("normal".equals(str)) {
            return 2;
        }
        return 0;
    }

    @Nullable
    public final Notification getNotification() {
        if (this.zzdr == null && zza.zzf(this.zzdp)) {
            this.zzdr = new Notification(this.zzdp);
        }
        return this.zzdr;
    }

    @KeepForSdk
    public final Intent toIntent() {
        Intent intent = new Intent();
        intent.putExtras(this.zzdp);
        return intent;
    }
}
