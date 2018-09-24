package org.telegram.tgnet;

public class TLRPC$TL_inputSecureFileUploaded extends TLRPC$InputSecureFile {
    public static int constructor = 859091184;
    public byte[] file_hash;
    public long id;
    public String md5_checksum;
    public int parts;
    public byte[] secret;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.id = stream.readInt64(exception);
        this.parts = stream.readInt32(exception);
        this.md5_checksum = stream.readString(exception);
        this.file_hash = stream.readByteArray(exception);
        this.secret = stream.readByteArray(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeInt64(this.id);
        stream.writeInt32(this.parts);
        stream.writeString(this.md5_checksum);
        stream.writeByteArray(this.file_hash);
        stream.writeByteArray(this.secret);
    }
}
