package org.telegram.tgnet;

public class TLRPC$TL_dialogPeer extends TLRPC$DialogPeer {
    public static int constructor = -445792507;
    public TLRPC$Peer peer;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.peer = TLRPC$Peer.TLdeserialize(stream, stream.readInt32(exception), exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        this.peer.serializeToStream(stream);
    }
}
