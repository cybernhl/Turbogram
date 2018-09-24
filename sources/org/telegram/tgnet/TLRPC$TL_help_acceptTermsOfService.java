package org.telegram.tgnet;

public class TLRPC$TL_help_acceptTermsOfService extends TLObject {
    public static int constructor = -294455398;
    public TLRPC$TL_dataJSON id;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$Bool.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        this.id.serializeToStream(stream);
    }
}
