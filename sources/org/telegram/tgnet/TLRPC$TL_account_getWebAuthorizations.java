package org.telegram.tgnet;

public class TLRPC$TL_account_getWebAuthorizations extends TLObject {
    public static int constructor = 405695855;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$TL_account_webAuthorizations.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
    }
}
