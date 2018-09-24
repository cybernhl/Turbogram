package org.telegram.tgnet;

public class TLRPC$TL_messageMediaPhoto_layer74 extends TLRPC$TL_messageMediaPhoto {
    public static int constructor = -1256047857;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.flags = stream.readInt32(exception);
        if ((this.flags & 1) != 0) {
            this.photo = TLRPC$Photo.TLdeserialize(stream, stream.readInt32(exception), exception);
        } else {
            this.photo = new TLRPC$TL_photoEmpty();
        }
        if ((this.flags & 2) != 0) {
            this.captionLegacy = stream.readString(exception);
        }
        if ((this.flags & 4) != 0) {
            this.ttl_seconds = stream.readInt32(exception);
        }
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeInt32(this.flags);
        if ((this.flags & 1) != 0) {
            this.photo.serializeToStream(stream);
        }
        if ((this.flags & 2) != 0) {
            stream.writeString(this.captionLegacy);
        }
        if ((this.flags & 4) != 0) {
            stream.writeInt32(this.ttl_seconds);
        }
    }
}
