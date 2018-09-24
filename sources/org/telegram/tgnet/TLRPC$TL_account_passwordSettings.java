package org.telegram.tgnet;

public class TLRPC$TL_account_passwordSettings extends TLObject {
    public static int constructor = -1705233435;
    public String email;
    public int flags;
    public TLRPC$TL_secureSecretSettings secure_settings;

    public static TLRPC$TL_account_passwordSettings TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        if (constructor == constructor) {
            TLRPC$TL_account_passwordSettings result = new TLRPC$TL_account_passwordSettings();
            result.readParams(stream, exception);
            return result;
        } else if (!exception) {
            return null;
        } else {
            throw new RuntimeException(String.format("can't parse magic %x in TL_account_passwordSettings", new Object[]{Integer.valueOf(constructor)}));
        }
    }

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.flags = stream.readInt32(exception);
        if ((this.flags & 1) != 0) {
            this.email = stream.readString(exception);
        }
        if ((this.flags & 2) != 0) {
            this.secure_settings = TLRPC$TL_secureSecretSettings.TLdeserialize(stream, stream.readInt32(exception), exception);
        }
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeInt32(this.flags);
        if ((this.flags & 1) != 0) {
            stream.writeString(this.email);
        }
        if ((this.flags & 2) != 0) {
            this.secure_settings.serializeToStream(stream);
        }
    }
}
