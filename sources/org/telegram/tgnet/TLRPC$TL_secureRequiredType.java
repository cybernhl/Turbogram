package org.telegram.tgnet;

public class TLRPC$TL_secureRequiredType extends TLRPC$SecureRequiredType {
    public static int constructor = -2103600678;
    public int flags;
    public boolean native_names;
    public boolean selfie_required;
    public boolean translation_required;
    public TLRPC$SecureValueType type;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        boolean z;
        boolean z2 = true;
        this.flags = stream.readInt32(exception);
        if ((this.flags & 1) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.native_names = z;
        if ((this.flags & 2) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.selfie_required = z;
        if ((this.flags & 4) == 0) {
            z2 = false;
        }
        this.translation_required = z2;
        this.type = TLRPC$SecureValueType.TLdeserialize(stream, stream.readInt32(exception), exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        this.flags = this.native_names ? this.flags | 1 : this.flags & -2;
        this.flags = this.selfie_required ? this.flags | 2 : this.flags & -3;
        this.flags = this.translation_required ? this.flags | 4 : this.flags & -5;
        stream.writeInt32(this.flags);
        this.type.serializeToStream(stream);
    }
}
