package org.telegram.tgnet;

public class TLRPC$TL_securePlainPhone extends TLRPC$SecurePlainData {
    public static int constructor = 2103482845;
    public String phone;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.phone = stream.readString(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeString(this.phone);
    }
}
