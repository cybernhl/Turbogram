package org.telegram.tgnet;

import java.util.ArrayList;

public class TLRPC$TL_help_appUpdate extends TLRPC$help_AppUpdate {
    public static int constructor = 497489295;
    public TLRPC$Document document;
    public ArrayList<TLRPC$MessageEntity> entities = new ArrayList();
    public int flags;
    public int id;
    public boolean popup;
    public String text;
    public String url;
    public String version;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        boolean z;
        this.flags = stream.readInt32(exception);
        if ((this.flags & 1) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.popup = z;
        this.id = stream.readInt32(exception);
        this.version = stream.readString(exception);
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
                this.document = TLRPC$Document.TLdeserialize(stream, stream.readInt32(exception), exception);
            }
            if ((this.flags & 4) != 0) {
                this.url = stream.readString(exception);
            }
        } else if (exception) {
            throw new RuntimeException(String.format("wrong Vector magic, got %x", new Object[]{Integer.valueOf(magic)}));
        }
    }

    public void serializeToStream(AbstractSerializedData stream) {
        int i;
        stream.writeInt32(constructor);
        if (this.popup) {
            i = this.flags | 1;
        } else {
            i = this.flags & -2;
        }
        this.flags = i;
        stream.writeInt32(this.flags);
        stream.writeInt32(this.id);
        stream.writeString(this.version);
        stream.writeString(this.text);
        stream.writeInt32(481674261);
        int count = this.entities.size();
        stream.writeInt32(count);
        for (int a = 0; a < count; a++) {
            ((TLRPC$MessageEntity) this.entities.get(a)).serializeToStream(stream);
        }
        if ((this.flags & 2) != 0) {
            this.document.serializeToStream(stream);
        }
        if ((this.flags & 4) != 0) {
            stream.writeString(this.url);
        }
    }
}
