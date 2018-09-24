package org.telegram.tgnet;

public class TLRPC$TL_messages_getStickers extends TLObject {
    public static int constructor = 71126828;
    public String emoticon;
    public int hash;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$messages_Stickers.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeString(this.emoticon);
        stream.writeInt32(this.hash);
    }
}
