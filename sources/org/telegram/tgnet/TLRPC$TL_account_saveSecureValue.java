package org.telegram.tgnet;

public class TLRPC$TL_account_saveSecureValue extends TLObject {
    public static int constructor = -1986010339;
    public long secure_secret_id;
    public TLRPC$TL_inputSecureValue value;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$TL_secureValue.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        this.value.serializeToStream(stream);
        stream.writeInt64(this.secure_secret_id);
    }
}
