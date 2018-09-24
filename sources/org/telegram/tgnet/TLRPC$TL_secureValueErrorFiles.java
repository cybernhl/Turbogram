package org.telegram.tgnet;

import java.util.ArrayList;

public class TLRPC$TL_secureValueErrorFiles extends TLRPC$SecureValueError {
    public static int constructor = 1717706985;
    public ArrayList<byte[]> file_hash = new ArrayList();
    public String text;
    public TLRPC$SecureValueType type;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.type = TLRPC$SecureValueType.TLdeserialize(stream, stream.readInt32(exception), exception);
        if (stream.readInt32(exception) == 481674261) {
            int count = stream.readInt32(exception);
            for (int a = 0; a < count; a++) {
                this.file_hash.add(stream.readByteArray(exception));
            }
            this.text = stream.readString(exception);
        } else if (exception) {
            throw new RuntimeException(String.format("wrong Vector magic, got %x", new Object[]{Integer.valueOf(magic)}));
        }
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        this.type.serializeToStream(stream);
        stream.writeInt32(481674261);
        int count = this.file_hash.size();
        stream.writeInt32(count);
        for (int a = 0; a < count; a++) {
            stream.writeByteArray((byte[]) this.file_hash.get(a));
        }
        stream.writeString(this.text);
    }
}
