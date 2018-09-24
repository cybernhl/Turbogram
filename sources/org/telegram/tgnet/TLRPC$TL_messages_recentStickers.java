package org.telegram.tgnet;

import java.util.ArrayList;

public class TLRPC$TL_messages_recentStickers extends TLRPC$messages_RecentStickers {
    public static int constructor = 586395571;
    public ArrayList<Integer> dates = new ArrayList();
    public int hash;
    public ArrayList<TLRPC$TL_stickerPack> packs = new ArrayList();
    public ArrayList<TLRPC$Document> stickers = new ArrayList();

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.hash = stream.readInt32(exception);
        if (stream.readInt32(exception) == 481674261) {
            int count = stream.readInt32(exception);
            int a = 0;
            while (a < count) {
                TLRPC$TL_stickerPack object = TLRPC$TL_stickerPack.TLdeserialize(stream, stream.readInt32(exception), exception);
                if (object != null) {
                    this.packs.add(object);
                    a++;
                } else {
                    return;
                }
            }
            if (stream.readInt32(exception) == 481674261) {
                count = stream.readInt32(exception);
                a = 0;
                while (a < count) {
                    TLRPC$Document object2 = TLRPC$Document.TLdeserialize(stream, stream.readInt32(exception), exception);
                    if (object2 != null) {
                        this.stickers.add(object2);
                        a++;
                    } else {
                        return;
                    }
                }
                if (stream.readInt32(exception) == 481674261) {
                    count = stream.readInt32(exception);
                    for (a = 0; a < count; a++) {
                        this.dates.add(Integer.valueOf(stream.readInt32(exception)));
                    }
                } else if (exception) {
                    throw new RuntimeException(String.format("wrong Vector magic, got %x", new Object[]{Integer.valueOf(magic)}));
                }
            } else if (exception) {
                throw new RuntimeException(String.format("wrong Vector magic, got %x", new Object[]{Integer.valueOf(magic)}));
            }
        } else if (exception) {
            throw new RuntimeException(String.format("wrong Vector magic, got %x", new Object[]{Integer.valueOf(magic)}));
        }
    }

    public void serializeToStream(AbstractSerializedData stream) {
        int a;
        stream.writeInt32(constructor);
        stream.writeInt32(this.hash);
        stream.writeInt32(481674261);
        int count = this.packs.size();
        stream.writeInt32(count);
        for (a = 0; a < count; a++) {
            ((TLRPC$TL_stickerPack) this.packs.get(a)).serializeToStream(stream);
        }
        stream.writeInt32(481674261);
        count = this.stickers.size();
        stream.writeInt32(count);
        for (a = 0; a < count; a++) {
            ((TLRPC$Document) this.stickers.get(a)).serializeToStream(stream);
        }
        stream.writeInt32(481674261);
        count = this.dates.size();
        stream.writeInt32(count);
        for (a = 0; a < count; a++) {
            stream.writeInt32(((Integer) this.dates.get(a)).intValue());
        }
    }
}
