package org.telegram.tgnet;

public class TLRPC$TL_peerNotifySettings extends TLRPC$PeerNotifySettings {
    public static int constructor = -1353671392;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.flags = stream.readInt32(exception);
        if ((this.flags & 1) != 0) {
            this.show_previews = stream.readBool(exception);
        }
        if ((this.flags & 2) != 0) {
            this.silent = stream.readBool(exception);
        }
        if ((this.flags & 4) != 0) {
            this.mute_until = stream.readInt32(exception);
        }
        if ((this.flags & 8) != 0) {
            this.sound = stream.readString(exception);
        }
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeInt32(this.flags);
        if ((this.flags & 1) != 0) {
            stream.writeBool(this.show_previews);
        }
        if ((this.flags & 2) != 0) {
            stream.writeBool(this.silent);
        }
        if ((this.flags & 4) != 0) {
            stream.writeInt32(this.mute_until);
        }
        if ((this.flags & 8) != 0) {
            stream.writeString(this.sound);
        }
    }
}
