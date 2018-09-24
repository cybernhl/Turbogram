package org.telegram.tgnet;

/* renamed from: org.telegram.tgnet.TLRPC$TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow */
public class C0972x72c667f extends TLRPC$PasswordKdfAlgo {
    public static int constructor = 982592842;
    /* renamed from: g */
    public int f809g;
    /* renamed from: p */
    public byte[] f810p;
    public byte[] salt1;
    public byte[] salt2;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.salt1 = stream.readByteArray(exception);
        this.salt2 = stream.readByteArray(exception);
        this.f809g = stream.readInt32(exception);
        this.f810p = stream.readByteArray(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeByteArray(this.salt1);
        stream.writeByteArray(this.salt2);
        stream.writeInt32(this.f809g);
        stream.writeByteArray(this.f810p);
    }
}
