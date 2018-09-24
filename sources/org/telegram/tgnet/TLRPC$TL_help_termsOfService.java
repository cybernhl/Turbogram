package org.telegram.tgnet;

import java.util.ArrayList;

public class TLRPC$TL_help_termsOfService extends TLObject {
    public static int constructor = 2013922064;
    public ArrayList<TLRPC$MessageEntity> entities = new ArrayList();
    public int flags;
    public TLRPC$TL_dataJSON id;
    public int min_age_confirm;
    public boolean popup;
    public String text;

    public static TLRPC$TL_help_termsOfService TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        if (constructor == constructor) {
            TLRPC$TL_help_termsOfService result = new TLRPC$TL_help_termsOfService();
            result.readParams(stream, exception);
            return result;
        } else if (!exception) {
            return null;
        } else {
            throw new RuntimeException(String.format("can't parse magic %x in TL_help_termsOfService", new Object[]{Integer.valueOf(constructor)}));
        }
    }

    public void readParams(AbstractSerializedData stream, boolean exception) {
        boolean z;
        this.flags = stream.readInt32(exception);
        if ((this.flags & 1) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.popup = z;
        this.id = TLRPC$TL_dataJSON.TLdeserialize(stream, stream.readInt32(exception), exception);
        this.text = stream.readString(exception);
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
            if ((this.flags & 2) != 0) {
                this.min_age_confirm = stream.readInt32(exception);
            }
        } else if (exception) {
            throw new RuntimeException(String.format("wrong Vector magic, got %x", new Object[]{Integer.valueOf(magic)}));
        }
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        this.flags = this.popup ? this.flags | 1 : this.flags & -2;
        stream.writeInt32(this.flags);
        this.id.serializeToStream(stream);
        stream.writeString(this.text);
        stream.writeInt32(481674261);
        int count = this.entities.size();
        stream.writeInt32(count);
        for (int a = 0; a < count; a++) {
            ((TLRPC$MessageEntity) this.entities.get(a)).serializeToStream(stream);
        }
        if ((this.flags & 2) != 0) {
            stream.writeInt32(this.min_age_confirm);
        }
    }
}
