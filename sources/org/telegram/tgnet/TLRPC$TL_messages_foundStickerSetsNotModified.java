package org.telegram.tgnet;

public class TLRPC$TL_messages_foundStickerSetsNotModified extends TLRPC$messages_FoundStickerSets {
    public static int constructor = 223655517;

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
    }
}
