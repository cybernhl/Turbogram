package org.telegram.tgnet;

public class TLRPC$TL_account_resetWebAuthorizations extends TLObject {
    public static int constructor = 1747789204;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$Bool.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
    }
}
