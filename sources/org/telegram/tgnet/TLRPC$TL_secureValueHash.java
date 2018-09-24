package org.telegram.tgnet;

public class TLRPC$TL_secureValueHash extends TLObject {
    public static int constructor = -316748368;
    public byte[] hash;
    public TLRPC$SecureValueType type;

    public static TLRPC$TL_secureValueHash TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        if (constructor == constructor) {
            TLRPC$TL_secureValueHash result = new TLRPC$TL_secureValueHash();
            result.readParams(stream, exception);
            return result;
        } else if (!exception) {
            return null;
        } else {
            throw new RuntimeException(String.format("can't parse magic %x in TL_secureValueHash", new Object[]{Integer.valueOf(constructor)}));
        }
    }

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.type = TLRPC$SecureValueType.TLdeserialize(stream, stream.readInt32(exception), exception);
        this.hash = stream.readByteArray(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        this.type.serializeToStream(stream);
        stream.writeByteArray(this.hash);
    }
}
