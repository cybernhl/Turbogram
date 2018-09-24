package org.telegram.tgnet;

public class TLRPC$TL_auth_importBotAuthorization extends TLObject {
    public static int constructor = 1738800940;
    public String api_hash;
    public int api_id;
    public String bot_auth_token;
    public int flags;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$TL_auth_authorization.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData paramAbstractSerializedData) {
        paramAbstractSerializedData.writeInt32(constructor);
        paramAbstractSerializedData.writeInt32(this.flags);
        paramAbstractSerializedData.writeInt32(this.api_id);
        paramAbstractSerializedData.writeString(this.api_hash);
        paramAbstractSerializedData.writeString(this.bot_auth_token);
    }
}
