package org.telegram.tgnet;

public class TLRPC$TL_messages_clearAllDrafts extends TLObject {
    public static int constructor = 2119757468;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$Bool.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
    }
}
