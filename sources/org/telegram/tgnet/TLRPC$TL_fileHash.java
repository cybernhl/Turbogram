package org.telegram.tgnet;

public class TLRPC$TL_fileHash extends TLObject {
    public static int constructor = 1648543603;
    public byte[] hash;
    public int limit;
    public int offset;

    public static TLRPC$TL_fileHash TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        if (constructor == constructor) {
            TLRPC$TL_fileHash result = new TLRPC$TL_fileHash();
            result.readParams(stream, exception);
            return result;
        } else if (!exception) {
            return null;
        } else {
            throw new RuntimeException(String.format("can't parse magic %x in TL_fileHash", new Object[]{Integer.valueOf(constructor)}));
        }
    }

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.offset = stream.readInt32(exception);
        this.limit = stream.readInt32(exception);
        this.hash = stream.readByteArray(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeInt32(this.offset);
        stream.writeInt32(this.limit);
        stream.writeByteArray(this.hash);
    }
}
