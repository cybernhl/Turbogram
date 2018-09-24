package org.telegram.tgnet;

public class TLRPC$TL_draftMessageEmpty extends TLRPC$DraftMessage {
    public static int constructor = 453805082;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.flags = stream.readInt32(exception);
        if ((this.flags & 1) != 0) {
            this.date = stream.readInt32(exception);
        }
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeInt32(this.flags);
        if ((this.flags & 1) != 0) {
            stream.writeInt32(this.date);
        }
    }
}
