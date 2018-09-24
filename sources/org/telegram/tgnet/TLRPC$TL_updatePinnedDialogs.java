package org.telegram.tgnet;

import java.util.ArrayList;

public class TLRPC$TL_updatePinnedDialogs extends TLRPC$Update {
    public static int constructor = -364071333;
    public int flags;
    public ArrayList<TLRPC$DialogPeer> order = new ArrayList();

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.flags = stream.readInt32(exception);
        if ((this.flags & 1) != 0) {
            if (stream.readInt32(exception) == 481674261) {
                int count = stream.readInt32(exception);
                int a = 0;
                while (a < count) {
                    TLRPC$DialogPeer object = TLRPC$DialogPeer.TLdeserialize(stream, stream.readInt32(exception), exception);
                    if (object != null) {
                        this.order.add(object);
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
        if ((this.flags & 1) != 0) {
            stream.writeInt32(481674261);
            int count = this.order.size();
            stream.writeInt32(count);
            for (int a = 0; a < count; a++) {
                ((TLRPC$DialogPeer) this.order.get(a)).serializeToStream(stream);
            }
        }
    }
}
