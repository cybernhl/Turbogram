package com.google.android.gms.internal.vision;

public abstract class zzbg<MessageType extends zzbf<MessageType, BuilderType>, BuilderType extends zzbg<MessageType, BuilderType>> implements zzdy {
    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzam();
    }

    protected abstract BuilderType zza(MessageType messageType);

    public final /* synthetic */ zzdy zza(zzdx zzdx) {
        if (zzbw().getClass().isInstance(zzdx)) {
            return zza((zzbf) zzdx);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }

    public abstract BuilderType zzam();
}
