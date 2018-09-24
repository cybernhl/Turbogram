package org.telegram.tgnet;

public class TLRPC$TL_messages_searchGifs extends TLObject {
    public static int constructor = -1080395925;
    public int offset;
    /* renamed from: q */
    public String f806q;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$TL_messages_foundGifs.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeString(this.f806q);
        stream.writeInt32(this.offset);
    }
}
