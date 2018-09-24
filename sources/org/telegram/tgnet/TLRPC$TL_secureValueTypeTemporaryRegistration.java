package org.telegram.tgnet;

public class TLRPC$TL_secureValueTypeTemporaryRegistration extends TLRPC$SecureValueType {
    public static int constructor = -368907213;

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
    }
}
