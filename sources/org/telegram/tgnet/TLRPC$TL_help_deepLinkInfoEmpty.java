package org.telegram.tgnet;

public class TLRPC$TL_help_deepLinkInfoEmpty extends TLRPC$help_DeepLinkInfo {
    public static int constructor = 1722786150;

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
    }
}
