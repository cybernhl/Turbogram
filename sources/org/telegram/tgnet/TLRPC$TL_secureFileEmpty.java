package org.telegram.tgnet;

public class TLRPC$TL_secureFileEmpty extends TLRPC$SecureFile {
    public static int constructor = 1679398724;

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
    }
}
