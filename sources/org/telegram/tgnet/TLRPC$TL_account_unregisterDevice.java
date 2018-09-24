package org.telegram.tgnet;

import java.util.ArrayList;

public class TLRPC$TL_account_unregisterDevice extends TLObject {
    public static int constructor = 813089983;
    public ArrayList<Integer> other_uids = new ArrayList();
    public String token;
    public int token_type;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$Bool.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeInt32(this.token_type);
        stream.writeString(this.token);
        stream.writeInt32(481674261);
        int count = this.other_uids.size();
        stream.writeInt32(count);
        for (int a = 0; a < count; a++) {
            stream.writeInt32(((Integer) this.other_uids.get(a)).intValue());
        }
    }
}
