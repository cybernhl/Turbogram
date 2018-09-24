package org.telegram.tgnet;

public class TLRPC$TL_account_passwordInputSettings extends TLObject {
    public static int constructor = -1036572727;
    public String email;
    public int flags;
    public String hint;
    public TLRPC$PasswordKdfAlgo new_algo;
    public byte[] new_password_hash;
    public TLRPC$TL_secureSecretSettings new_secure_settings;

    public static TLRPC$TL_account_passwordInputSettings TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        if (constructor == constructor) {
            TLRPC$TL_account_passwordInputSettings result = new TLRPC$TL_account_passwordInputSettings();
            result.readParams(stream, exception);
            return result;
        } else if (!exception) {
            return null;
        } else {
            throw new RuntimeException(String.format("can't parse magic %x in TL_account_passwordInputSettings", new Object[]{Integer.valueOf(constructor)}));
        }
    }

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.flags = stream.readInt32(exception);
        if ((this.flags & 1) != 0) {
            this.new_algo = TLRPC$PasswordKdfAlgo.TLdeserialize(stream, stream.readInt32(exception), exception);
        }
        if ((this.flags & 1) != 0) {
            this.new_password_hash = stream.readByteArray(exception);
        }
        if ((this.flags & 1) != 0) {
            this.hint = stream.readString(exception);
        }
        if ((this.flags & 2) != 0) {
            this.email = stream.readString(exception);
        }
        if ((this.flags & 4) != 0) {
            this.new_secure_settings = TLRPC$TL_secureSecretSettings.TLdeserialize(stream, stream.readInt32(exception), exception);
        }
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeInt32(this.flags);
        if ((this.flags & 1) != 0) {
            this.new_algo.serializeToStream(stream);
        }
        if ((this.flags & 1) != 0) {
            stream.writeByteArray(this.new_password_hash);
        }
        if ((this.flags & 1) != 0) {
            stream.writeString(this.hint);
        }
        if ((this.flags & 2) != 0) {
            stream.writeString(this.email);
        }
        if ((this.flags & 4) != 0) {
            this.new_secure_settings.serializeToStream(stream);
        }
    }
}
