package org.telegram.tgnet;

public class TLRPC$TL_account_verifyEmail extends TLObject {
    public static int constructor = -323339813;
    public String code;
    public String email;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$Bool.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeString(this.email);
        stream.writeString(this.code);
    }
}
