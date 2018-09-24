package org.telegram.tgnet;

public abstract class TLRPC$messages_FoundStickerSets extends TLObject {
    public static TLRPC$messages_FoundStickerSets TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        TLRPC$messages_FoundStickerSets result = null;
        switch (constructor) {
            case 223655517:
                result = new TLRPC$TL_messages_foundStickerSetsNotModified();
                break;
            case 1359533640:
                result = new TLRPC$TL_messages_foundStickerSets();
                break;
        }
        if (result == null && exception) {
            throw new RuntimeException(String.format("can't parse magic %x in messages_FoundStickerSets", new Object[]{Integer.valueOf(constructor)}));
        }
        if (result != null) {
            result.readParams(stream, exception);
        }
        return result;
    }
}
