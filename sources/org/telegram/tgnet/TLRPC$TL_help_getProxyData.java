package org.telegram.tgnet;

public class TLRPC$TL_help_getProxyData extends TLObject {
    public static int constructor = 1031231713;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$help_ProxyData.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
    }
}
