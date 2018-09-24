package org.telegram.tgnet;

public class TLRPC$TL_secureValueTypePassportRegistration extends TLRPC$SecureValueType {
    public static int constructor = -1713143702;

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
    }
}
