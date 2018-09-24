package org.telegram.tgnet;

public class TLRPC$TL_secureValueTypeEmail extends TLRPC$SecureValueType {
    public static int constructor = -1908627474;

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
    }
}
