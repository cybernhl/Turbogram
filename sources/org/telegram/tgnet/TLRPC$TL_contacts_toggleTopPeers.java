package org.telegram.tgnet;

public class TLRPC$TL_contacts_toggleTopPeers extends TLObject {
    public static int constructor = -2062238246;
    public boolean enabled;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$Bool.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeBool(this.enabled);
    }
}
