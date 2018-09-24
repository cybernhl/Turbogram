package org.telegram.tgnet;

public class TLRPC$TL_inputPeerNotifySettings extends TLObject {
    public static int constructor = -1673717362;
    public int flags;
    public int mute_until;
    public boolean show_previews;
    public boolean silent;
    public String sound;

    public static TLRPC$TL_inputPeerNotifySettings TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        if (constructor == constructor) {
            TLRPC$TL_inputPeerNotifySettings result = new TLRPC$TL_inputPeerNotifySettings();
            result.readParams(stream, exception);
            return result;
        } else if (!exception) {
            return null;
        } else {
            throw new RuntimeException(String.format("can't parse magic %x in TL_inputPeerNotifySettings", new Object[]{Integer.valueOf(constructor)}));
        }
    }

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
