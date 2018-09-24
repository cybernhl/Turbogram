package org.telegram.tgnet;

public class TLRPC$TL_messageActionBotAllowed extends TLRPC$MessageAction {
    public static int constructor = -1410748418;
    public String domain;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.domain = stream.readString(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeString(this.domain);
    }
}
