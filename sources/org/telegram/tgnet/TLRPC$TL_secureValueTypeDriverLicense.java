package org.telegram.tgnet;

public class TLRPC$TL_secureValueTypeDriverLicense extends TLRPC$SecureValueType {
    public static int constructor = 115615172;

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
    }
}
