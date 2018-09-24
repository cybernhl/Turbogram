package com.google.android.gms.internal.measurement;

public abstract class zztx<MessageType extends zztw<MessageType, BuilderType>, BuilderType extends zztx<MessageType, BuilderType>> implements zzwu {
    protected abstract BuilderType zza(MessageType messageType);

    public abstract BuilderType zztv();

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zztv();
    }

    public final /* synthetic */ zzwu zza(zzwt zzwt) {
        if (zzwf().getClass().isInstance(zzwt)) {
            return zza((zztw) zzwt);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
