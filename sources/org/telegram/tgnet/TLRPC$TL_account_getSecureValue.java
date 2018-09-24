package org.telegram.tgnet;

import java.util.ArrayList;

public class TLRPC$TL_account_getSecureValue extends TLObject {
    public static int constructor = 1936088002;
    public ArrayList<TLRPC$SecureValueType> types = new ArrayList();

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        TLRPC$Vector vector = new TLRPC$Vector();
        int size = stream.readInt32(exception);
        for (int a = 0; a < size; a++) {
            TLRPC$TL_secureValue object = TLRPC$TL_secureValue.TLdeserialize(stream, stream.readInt32(exception), exception);
            if (object == null) {
                break;
            }
            vector.objects.add(object);
        }
        return vector;
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
