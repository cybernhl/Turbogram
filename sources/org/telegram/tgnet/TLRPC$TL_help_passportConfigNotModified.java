package org.telegram.tgnet;

public class TLRPC$TL_help_passportConfigNotModified extends TLRPC$help_PassportConfig {
    public static int constructor = -1078332329;

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
    }
}
