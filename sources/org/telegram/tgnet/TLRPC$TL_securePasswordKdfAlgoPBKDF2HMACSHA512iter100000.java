package org.telegram.tgnet;

public class TLRPC$TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000 extends TLRPC$SecurePasswordKdfAlgo {
    public static int constructor = -1141711456;
    public byte[] salt;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.salt = stream.readByteArray(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeByteArray(this.salt);
    }
}
