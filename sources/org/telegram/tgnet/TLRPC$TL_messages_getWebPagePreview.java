package org.telegram.tgnet;

import java.util.ArrayList;

public class TLRPC$TL_messages_getWebPagePreview extends TLObject {
    public static int constructor = -1956073268;
    public ArrayList<TLRPC$MessageEntity> entities = new ArrayList();
    public int flags;
    public String message;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$MessageMedia.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeInt32(this.flags);
        stream.writeString(this.message);
        if ((this.flags & 8) != 0) {
            stream.writeInt32(481674261);
            int count = this.entities.size();
            stream.writeInt32(count);
            for (int a = 0; a < count; a++) {
                ((TLRPC$MessageEntity) this.entities.get(a)).serializeToStream(stream);
            }
        }
    }
}
