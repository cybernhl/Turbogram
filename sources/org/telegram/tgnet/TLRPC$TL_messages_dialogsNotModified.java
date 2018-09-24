package org.telegram.tgnet;

public class TLRPC$TL_messages_dialogsNotModified extends TLRPC$messages_Dialogs {
    public static int constructor = -253500010;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.count = stream.readInt32(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeInt32(this.count);
    }
}
