package org.telegram.tgnet;

public class TLRPC$TL_account_getAuthorizationForm extends TLObject {
    public static int constructor = -1200903967;
    public int bot_id;
    public String public_key;
    public String scope;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$TL_account_authorizationForm.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeInt32(this.bot_id);
        stream.writeString(this.scope);
        stream.writeString(this.public_key);
    }
}
