package org.telegram.tgnet;

public abstract class TLRPC$messages_RecentStickers extends TLObject {
    public static TLRPC$messages_RecentStickers TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        TLRPC$messages_RecentStickers result = null;
        switch (constructor) {
            case 186120336:
                result = new TLRPC$TL_messages_recentStickersNotModified();
                break;
            case 586395571:
                result = new TLRPC$TL_messages_recentStickers();
                break;
        }
        if (result == null && exception) {
            throw new RuntimeException(String.format("can't parse magic %x in messages_RecentStickers", new Object[]{Integer.valueOf(constructor)}));
        }
        if (result != null) {
            result.readParams(stream, exception);
        }
        return result;
    }
}
