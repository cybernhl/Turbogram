package org.telegram.tgnet;

public class TLRPC$TL_securePlainEmail extends TLRPC$SecurePlainData {
    public static int constructor = 569137759;
    public String email;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.email = stream.readString(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeString(this.email);
    }
}
