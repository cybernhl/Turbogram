package org.telegram.tgnet;

public class TLRPC$TL_account_resetWebAuthorization extends TLObject {
    public static int constructor = 755087855;
    public long hash;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$Bool.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeInt64(this.hash);
    }
}
