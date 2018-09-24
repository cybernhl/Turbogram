package org.telegram.tgnet;

public class TLRPC$TL_secureValueErrorReverseSide extends TLRPC$SecureValueError {
    public static int constructor = -2037765467;
    public byte[] file_hash;
    public String text;
    public TLRPC$SecureValueType type;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.type = TLRPC$SecureValueType.TLdeserialize(stream, stream.readInt32(exception), exception);
        this.file_hash = stream.readByteArray(exception);
        this.text = stream.readString(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        this.type.serializeToStream(stream);
        stream.writeByteArray(this.file_hash);
        stream.writeString(this.text);
    }
}
