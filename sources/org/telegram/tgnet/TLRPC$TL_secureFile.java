package org.telegram.tgnet;

public class TLRPC$TL_secureFile extends TLRPC$SecureFile {
    public static int constructor = -534283678;
    public long access_hash;
    public int date;
    public int dc_id;
    public byte[] file_hash;
    public long id;
    public byte[] secret;
    public int size;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.id = stream.readInt64(exception);
        this.access_hash = stream.readInt64(exception);
        this.size = stream.readInt32(exception);
        this.dc_id = stream.readInt32(exception);
        this.date = stream.readInt32(exception);
        this.file_hash = stream.readByteArray(exception);
        this.secret = stream.readByteArray(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeInt64(this.id);
        stream.writeInt64(this.access_hash);
        stream.writeInt32(this.size);
        stream.writeInt32(this.dc_id);
        stream.writeInt32(this.date);
        stream.writeByteArray(this.file_hash);
        stream.writeByteArray(this.secret);
    }
}
