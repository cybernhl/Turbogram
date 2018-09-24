package org.telegram.tgnet;

public class TLRPC$TL_auth_checkPassword extends TLObject {
    public static int constructor = -779399914;
    public TLRPC$InputCheckPasswordSRP password;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$TL_auth_authorization.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        this.password.serializeToStream(stream);
    }
}
