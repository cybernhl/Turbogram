package org.telegram.tgnet;

import java.util.ArrayList;

public class TLRPC$TL_account_registerDevice extends TLObject {
    public static int constructor = 1555998096;
    public boolean app_sandbox;
    public ArrayList<Integer> other_uids = new ArrayList();
    public byte[] secret;
    public String token;
    public int token_type;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$Bool.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeInt32(this.token_type);
        stream.writeString(this.token);
        stream.writeBool(this.app_sandbox);
        stream.writeByteArray(this.secret);
        stream.writeInt32(481674261);
        int count = this.other_uids.size();
        stream.writeInt32(count);
        for (int a = 0; a < count; a++) {
            stream.writeInt32(((Integer) this.other_uids.get(a)).intValue());
        }
    }
}
