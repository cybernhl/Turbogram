package org.telegram.tgnet;

public class TLRPC$TL_updateDialogUnreadMark extends TLRPC$Update {
    public static int constructor = -513517117;
    public int flags;
    public TLRPC$DialogPeer peer;
    public boolean unread;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.flags = stream.readInt32(exception);
        this.unread = (this.flags & 1) != 0;
        this.peer = TLRPC$DialogPeer.TLdeserialize(stream, stream.readInt32(exception), exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        this.flags = this.unread ? this.flags | 1 : this.flags & -2;
        stream.writeInt32(this.flags);
        this.peer.serializeToStream(stream);
    }
}
