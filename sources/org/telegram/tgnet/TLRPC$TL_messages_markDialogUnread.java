package org.telegram.tgnet;

public class TLRPC$TL_messages_markDialogUnread extends TLObject {
    public static int constructor = -1031349873;
    public int flags;
    public TLRPC$InputDialogPeer peer;
    public boolean unread;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$Bool.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        this.flags = this.unread ? this.flags | 1 : this.flags & -2;
        stream.writeInt32(this.flags);
        this.peer.serializeToStream(stream);
    }
}
