package org.telegram.tgnet;

public class TLRPC$TL_documentAttributeVideo extends TLRPC$DocumentAttribute {
    public static int constructor = 250621158;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        boolean z = true;
        this.flags = stream.readInt32(exception);
        this.round_message = (this.flags & 1) != 0;
        if ((this.flags & 2) == 0) {
            z = false;
        }
        this.supports_streaming = z;
        this.duration = stream.readInt32(exception);
        this.w = stream.readInt32(exception);
        this.h = stream.readInt32(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        this.flags = this.round_message ? this.flags | 1 : this.flags & -2;
        this.flags = this.supports_streaming ? this.flags | 2 : this.flags & -3;
        stream.writeInt32(this.flags);
        stream.writeInt32(this.duration);
        stream.writeInt32(this.w);
        stream.writeInt32(this.h);
    }
}
