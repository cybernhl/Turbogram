package org.telegram.tgnet;

public class TLRPC$TL_inputDialogPeerFeed extends TLRPC$InputDialogPeer {
    public static int constructor = 741914831;
    public int feed_id;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.feed_id = stream.readInt32(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeInt32(this.feed_id);
    }
}
