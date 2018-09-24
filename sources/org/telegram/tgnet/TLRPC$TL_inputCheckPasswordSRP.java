package org.telegram.tgnet;

public class TLRPC$TL_inputCheckPasswordSRP extends TLRPC$InputCheckPasswordSRP {
    public static int constructor = -763367294;
    /* renamed from: A */
    public byte[] f797A;
    public byte[] M1;
    public long srp_id;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.srp_id = stream.readInt64(exception);
        this.f797A = stream.readByteArray(exception);
        this.M1 = stream.readByteArray(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeInt64(this.srp_id);
        stream.writeByteArray(this.f797A);
        stream.writeByteArray(this.M1);
    }
}
