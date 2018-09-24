package org.telegram.tgnet;

public class TLRPC$TL_account_getPasswordSettings extends TLObject {
    public static int constructor = -1663767815;
    public TLRPC$InputCheckPasswordSRP password;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$TL_account_passwordSettings.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        this.password.serializeToStream(stream);
    }
}
