package org.telegram.tgnet;

public class TLRPC$TL_secureValueTypePhone extends TLRPC$SecureValueType {
    public static int constructor = -1289704741;

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
    }
}
