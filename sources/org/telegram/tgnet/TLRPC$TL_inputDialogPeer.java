package org.telegram.tgnet;

public class TLRPC$TL_inputDialogPeer extends TLRPC$InputDialogPeer {
    public static int constructor = -55902537;
    public TLRPC$InputPeer peer;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.peer = TLRPC$InputPeer.TLdeserialize(stream, stream.readInt32(exception), exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        this.peer.serializeToStream(stream);
    }
}
