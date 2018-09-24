package org.telegram.tgnet;

public class TLRPC$TL_secureCredentialsEncrypted extends TLObject {
    public static int constructor = 871426631;
    public byte[] data;
    public byte[] hash;
    public byte[] secret;

    public static TLRPC$TL_secureCredentialsEncrypted TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        if (constructor == constructor) {
            TLRPC$TL_secureCredentialsEncrypted result = new TLRPC$TL_secureCredentialsEncrypted();
            result.readParams(stream, exception);
            return result;
        } else if (!exception) {
            return null;
        } else {
            throw new RuntimeException(String.format("can't parse magic %x in TL_secureCredentialsEncrypted", new Object[]{Integer.valueOf(constructor)}));
        }
    }

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.data = stream.readByteArray(exception);
        this.hash = stream.readByteArray(exception);
        this.secret = stream.readByteArray(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeByteArray(this.data);
        stream.writeByteArray(this.hash);
        stream.writeByteArray(this.secret);
    }
}
