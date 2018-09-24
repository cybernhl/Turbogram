package org.telegram.tgnet;

public class TLRPC$TL_inputSecureFile extends TLRPC$InputSecureFile {
    public static int constructor = 1399317950;
    public long access_hash;
    public long id;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.id = stream.readInt64(exception);
        this.access_hash = stream.readInt64(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeInt64(this.id);
        stream.writeInt64(this.access_hash);
    }
}
