package org.telegram.tgnet;

import java.util.ArrayList;

public class TLRPC$TL_account_deleteSecureValue extends TLObject {
    public static int constructor = -1199522741;
    public ArrayList<TLRPC$SecureValueType> types = new ArrayList();

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$Bool.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeInt32(481674261);
        int count = this.types.size();
        stream.writeInt32(count);
        for (int a = 0; a < count; a++) {
            ((TLRPC$SecureValueType) this.types.get(a)).serializeToStream(stream);
        }
    }
}
