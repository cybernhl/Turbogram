package org.telegram.tgnet;

public class TLRPC$TL_secureValueTypeIdentityCard extends TLRPC$SecureValueType {
    public static int constructor = -1596951477;

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
    }
}
