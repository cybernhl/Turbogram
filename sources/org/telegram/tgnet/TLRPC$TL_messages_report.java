package org.telegram.tgnet;

import java.util.ArrayList;

public class TLRPC$TL_messages_report extends TLObject {
    public static int constructor = -1115507112;
    public ArrayList<Integer> id = new ArrayList();
    public TLRPC$InputPeer peer;
    public TLRPC$ReportReason reason;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$Bool.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        this.peer.serializeToStream(stream);
        stream.writeInt32(481674261);
        int count = this.id.size();
        stream.writeInt32(count);
        for (int a = 0; a < count; a++) {
            stream.writeInt32(((Integer) this.id.get(a)).intValue());
        }
        this.reason.serializeToStream(stream);
    }
}
