package org.telegram.tgnet;

public class TLRPC$TL_secureValueError extends TLRPC$SecureValueError {
    public static int constructor = -2036501105;
    public byte[] hash;
    public String text;
    public TLRPC$SecureValueType type;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.type = TLRPC$SecureValueType.TLdeserialize(stream, stream.readInt32(exception), exception);
        this.hash = stream.readByteArray(exception);
        this.text = stream.readString(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        this.type.serializeToStream(stream);
        stream.writeByteArray(this.hash);
        stream.writeString(this.text);
    }
}
