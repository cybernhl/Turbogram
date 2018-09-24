package org.telegram.tgnet;

public class TLRPC$TL_secureValueTypeBankStatement extends TLRPC$SecureValueType {
    public static int constructor = -1995211763;

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
    }
}
