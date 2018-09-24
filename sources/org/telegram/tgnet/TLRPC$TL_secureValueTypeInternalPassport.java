package org.telegram.tgnet;

public class TLRPC$TL_secureValueTypeInternalPassport extends TLRPC$SecureValueType {
    public static int constructor = -1717268701;

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
    }
}
