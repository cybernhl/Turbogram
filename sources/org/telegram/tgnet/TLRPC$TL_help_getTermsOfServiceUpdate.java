package org.telegram.tgnet;

public class TLRPC$TL_help_getTermsOfServiceUpdate extends TLObject {
    public static int constructor = 749019089;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$help_TermsOfServiceUpdate.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
    }
}
