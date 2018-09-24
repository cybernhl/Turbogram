package org.telegram.tgnet;

public class TLRPC$TL_account_password extends TLObject {
    public static int constructor = -1390001672;
    public TLRPC$PasswordKdfAlgo current_algo;
    public String email_unconfirmed_pattern;
    public int flags;
    public boolean has_password;
    public boolean has_recovery;
    public boolean has_secure_values;
    public String hint;
    public TLRPC$PasswordKdfAlgo new_algo;
    public TLRPC$SecurePasswordKdfAlgo new_secure_algo;
    public byte[] secure_random;
    public byte[] srp_B;
    public long srp_id;

    public static TLRPC$TL_account_password TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        if (constructor == constructor) {
            TLRPC$TL_account_password result = new TLRPC$TL_account_password();
            result.readParams(stream, exception);
            return result;
        } else if (!exception) {
            return null;
        } else {
            throw new RuntimeException(String.format("can't parse magic %x in TL_account_password", new Object[]{Integer.valueOf(constructor)}));
        }
    }

    public void readParams(AbstractSerializedData stream, boolean exception) {
        boolean z;
        boolean z2 = true;
        this.flags = stream.readInt32(exception);
        this.has_recovery = (this.flags & 1) != 0;
        if ((this.flags & 2) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.has_secure_values = z;
        if ((this.flags & 4) == 0) {
            z2 = false;
        }
        this.has_password = z2;
        if ((this.flags & 4) != 0) {
            this.current_algo = TLRPC$PasswordKdfAlgo.TLdeserialize(stream, stream.readInt32(exception), exception);
        }
        if ((this.flags & 4) != 0) {
            this.srp_B = stream.readByteArray(exception);
        }
        if ((this.flags & 4) != 0) {
            this.srp_id = stream.readInt64(exception);
        }
        if ((this.flags & 8) != 0) {
            this.hint = stream.readString(exception);
        }
        if ((this.flags & 16) != 0) {
            this.email_unconfirmed_pattern = stream.readString(exception);
        }
        this.new_algo = TLRPC$PasswordKdfAlgo.TLdeserialize(stream, stream.readInt32(exception), exception);
        this.new_secure_algo = TLRPC$SecurePasswordKdfAlgo.TLdeserialize(stream, stream.readInt32(exception), exception);
        this.secure_random = stream.readByteArray(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        this.flags = this.has_recovery ? this.flags | 1 : this.flags & -2;
        this.flags = this.has_secure_values ? this.flags | 2 : this.flags & -3;
        this.flags = this.has_password ? this.flags | 4 : this.flags & -5;
        stream.writeInt32(this.flags);
        if ((this.flags & 4) != 0) {
            this.current_algo.serializeToStream(stream);
        }
        if ((this.flags & 4) != 0) {
            stream.writeByteArray(this.srp_B);
        }
        if ((this.flags & 4) != 0) {
            stream.writeInt64(this.srp_id);
        }
        if ((this.flags & 8) != 0) {
            stream.writeString(this.hint);
        }
        if ((this.flags & 16) != 0) {
            stream.writeString(this.email_unconfirmed_pattern);
        }
        this.new_algo.serializeToStream(stream);
        this.new_secure_algo.serializeToStream(stream);
        stream.writeByteArray(this.secure_random);
    }
}
