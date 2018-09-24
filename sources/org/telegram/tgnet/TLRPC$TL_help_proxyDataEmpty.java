package org.telegram.tgnet;

public class TLRPC$TL_help_proxyDataEmpty extends TLRPC$help_ProxyData {
    public static int constructor = -526508104;
    public int expires;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.expires = stream.readInt32(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeInt32(this.expires);
    }
}
