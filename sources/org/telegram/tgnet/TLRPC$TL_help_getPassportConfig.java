package org.telegram.tgnet;

public class TLRPC$TL_help_getPassportConfig extends TLObject {
    public static int constructor = -966677240;
    public int hash;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$help_PassportConfig.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeInt32(this.hash);
    }
}
