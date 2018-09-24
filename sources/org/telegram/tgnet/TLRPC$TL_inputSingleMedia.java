package org.telegram.tgnet;

import java.util.ArrayList;

public class TLRPC$TL_inputSingleMedia extends TLObject {
    public static int constructor = 482797855;
    public ArrayList<TLRPC$MessageEntity> entities = new ArrayList();
    public int flags;
    public TLRPC$InputMedia media;
    public String message;
    public long random_id;

    public static TLRPC$TL_inputSingleMedia TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        if (constructor == constructor) {
            TLRPC$TL_inputSingleMedia result = new TLRPC$TL_inputSingleMedia();
            result.readParams(stream, exception);
            return result;
        } else if (!exception) {
            return null;
        } else {
            throw new RuntimeException(String.format("can't parse magic %x in TL_inputSingleMedia", new Object[]{Integer.valueOf(constructor)}));
        }
    }

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.flags = stream.readInt32(exception);
        this.media = TLRPC$InputMedia.TLdeserialize(stream, stream.readInt32(exception), exception);
        this.random_id = stream.readInt64(exception);
        this.message = stream.readString(exception);
        if ((this.flags & 1) != 0) {
            if (stream.readInt32(exception) == 481674261) {
                int count = stream.readInt32(exception);
                int a = 0;
                while (a < count) {
                    TLRPC$MessageEntity object = TLRPC$MessageEntity.TLdeserialize(stream, stream.readInt32(exception), exception);
                    if (object != null) {
                        this.entities.add(object);
                        a++;
                    } else {
                        return;
                    }
                }
            } else if (exception) {
                throw new RuntimeException(String.format("wrong Vector magic, got %x", new Object[]{Integer.valueOf(magic)}));
            }
        }
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeInt32(this.flags);
        this.media.serializeToStream(stream);
        stream.writeInt64(this.random_id);
        stream.writeString(this.message);
        if ((this.flags & 1) != 0) {
            stream.writeInt32(481674261);
            int count = this.entities.size();
            stream.writeInt32(count);
            for (int a = 0; a < count; a++) {
                ((TLRPC$MessageEntity) this.entities.get(a)).serializeToStream(stream);
            }
        }
    }
}
