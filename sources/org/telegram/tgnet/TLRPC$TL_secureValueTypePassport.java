package org.telegram.tgnet;

public class TLRPC$TL_secureValueTypePassport extends TLRPC$SecureValueType {
    public static int constructor = 1034709504;

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
    }
}
