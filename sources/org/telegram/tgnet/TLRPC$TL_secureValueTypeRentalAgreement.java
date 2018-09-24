package org.telegram.tgnet;

public class TLRPC$TL_secureValueTypeRentalAgreement extends TLRPC$SecureValueType {
    public static int constructor = -1954007928;

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
    }
}
