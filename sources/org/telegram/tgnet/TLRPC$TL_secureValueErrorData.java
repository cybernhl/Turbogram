package org.telegram.tgnet;

public class TLRPC$TL_secureValueErrorData extends TLRPC$SecureValueError {
    public static int constructor = -391902247;
    public byte[] data_hash;
    public String field;
    public String text;
    public TLRPC$SecureValueType type;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.type = TLRPC$SecureValueType.TLdeserialize(stream, stream.readInt32(exception), exception);
        this.data_hash = stream.readByteArray(exception);
        this.field = stream.readString(exception);
        this.text = stream.readString(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        this.type.serializeToStream(stream);
        stream.writeByteArray(this.data_hash);
        stream.writeString(this.field);
        stream.writeString(this.text);
    }
}
