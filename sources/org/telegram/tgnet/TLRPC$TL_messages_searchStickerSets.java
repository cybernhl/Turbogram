package org.telegram.tgnet;

public class TLRPC$TL_messages_searchStickerSets extends TLObject {
    public static int constructor = -1028140917;
    public boolean exclude_featured;
    public int flags;
    public int hash;
    /* renamed from: q */
    public String f808q;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$messages_FoundStickerSets.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        this.flags = this.exclude_featured ? this.flags | 1 : this.flags & -2;
        stream.writeInt32(this.flags);
        stream.writeString(this.f808q);
        stream.writeInt32(this.hash);
    }
}
