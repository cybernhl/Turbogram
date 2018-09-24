package org.telegram.tgnet;

public class TLRPC$TL_account_updatePasswordSettings extends TLObject {
    public static int constructor = -1516564433;
    public TLRPC$TL_account_passwordInputSettings new_settings;
    public TLRPC$InputCheckPasswordSRP password;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$Bool.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        this.password.serializeToStream(stream);
        this.new_settings.serializeToStream(stream);
    }
}
