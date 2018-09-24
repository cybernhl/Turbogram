package org.telegram.tgnet;

public class TLRPC$TL_secureData extends TLObject {
    public static int constructor = -1964327229;
    public byte[] data;
    public byte[] data_hash;
    public byte[] secret;

    public static TLRPC$TL_secureData TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        if (constructor == constructor) {
            TLRPC$TL_secureData result = new TLRPC$TL_secureData();
            result.readParams(stream, exception);
            return result;
        } else if (!exception) {
            return null;
        } else {
            throw new RuntimeException(String.format("can't parse magic %x in TL_secureData", new Object[]{Integer.valueOf(constructor)}));
        }
    }

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.data = stream.readByteArray(exception);
        this.data_hash = stream.readByteArray(exception);
        this.secret = stream.readByteArray(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeByteArray(this.data);
        stream.writeByteArray(this.data_hash);
        stream.writeByteArray(this.secret);
    }
}
