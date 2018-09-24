package org.telegram.tgnet;

public class TLRPC$TL_inputMediaPhotoExternal extends TLRPC$InputMedia {
    public static int constructor = -440664550;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.flags = stream.readInt32(exception);
        this.url = stream.readString(exception);
        if ((this.flags & 1) != 0) {
            this.ttl_seconds = stream.readInt32(exception);
        }
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeInt32(this.flags);
        stream.writeString(this.url);
        if ((this.flags & 1) != 0) {
            stream.writeInt32(this.ttl_seconds);
        }
    }
}
