package org.telegram.tgnet;

public class TLRPC$TL_secureSecretSettings extends TLObject {
    public static int constructor = 354925740;
    public TLRPC$SecurePasswordKdfAlgo secure_algo;
    public byte[] secure_secret;
    public long secure_secret_id;

    public static TLRPC$TL_secureSecretSettings TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        if (constructor == constructor) {
            TLRPC$TL_secureSecretSettings result = new TLRPC$TL_secureSecretSettings();
            result.readParams(stream, exception);
            return result;
        } else if (!exception) {
            return null;
        } else {
            throw new RuntimeException(String.format("can't parse magic %x in TL_secureSecretSettings", new Object[]{Integer.valueOf(constructor)}));
        }
    }

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.secure_algo = TLRPC$SecurePasswordKdfAlgo.TLdeserialize(stream, stream.readInt32(exception), exception);
        this.secure_secret = stream.readByteArray(exception);
        this.secure_secret_id = stream.readInt64(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        this.secure_algo.serializeToStream(stream);
        stream.writeByteArray(this.secure_secret);
        stream.writeInt64(this.secure_secret_id);
    }
}
