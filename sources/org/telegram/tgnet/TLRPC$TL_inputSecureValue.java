package org.telegram.tgnet;

import java.util.ArrayList;

public class TLRPC$TL_inputSecureValue extends TLObject {
    public static int constructor = -618540889;
    public TLRPC$TL_secureData data;
    public ArrayList<TLRPC$InputSecureFile> files = new ArrayList();
    public int flags;
    public TLRPC$InputSecureFile front_side;
    public TLRPC$SecurePlainData plain_data;
    public TLRPC$InputSecureFile reverse_side;
    public TLRPC$InputSecureFile selfie;
    public ArrayList<TLRPC$InputSecureFile> translation = new ArrayList();
    public TLRPC$SecureValueType type;

    public static TLRPC$TL_inputSecureValue TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        if (constructor == constructor) {
            TLRPC$TL_inputSecureValue result = new TLRPC$TL_inputSecureValue();
            result.readParams(stream, exception);
            return result;
        } else if (!exception) {
            return null;
        } else {
            throw new RuntimeException(String.format("can't parse magic %x in TL_inputSecureValue", new Object[]{Integer.valueOf(constructor)}));
        }
    }

    public void readParams(AbstractSerializedData stream, boolean exception) {
        int count;
        int a;
        TLRPC$InputSecureFile object;
        this.flags = stream.readInt32(exception);
        this.type = TLRPC$SecureValueType.TLdeserialize(stream, stream.readInt32(exception), exception);
        if ((this.flags & 1) != 0) {
            this.data = TLRPC$TL_secureData.TLdeserialize(stream, stream.readInt32(exception), exception);
        }
        if ((this.flags & 2) != 0) {
            this.front_side = TLRPC$InputSecureFile.TLdeserialize(stream, stream.readInt32(exception), exception);
        }
        if ((this.flags & 4) != 0) {
            this.reverse_side = TLRPC$InputSecureFile.TLdeserialize(stream, stream.readInt32(exception), exception);
        }
        if ((this.flags & 8) != 0) {
            this.selfie = TLRPC$InputSecureFile.TLdeserialize(stream, stream.readInt32(exception), exception);
        }
        if ((this.flags & 64) != 0) {
            if (stream.readInt32(exception) == 481674261) {
                count = stream.readInt32(exception);
                a = 0;
                while (a < count) {
                    object = TLRPC$InputSecureFile.TLdeserialize(stream, stream.readInt32(exception), exception);
                    if (object != null) {
                        this.translation.add(object);
                        a++;
                    } else {
                        return;
                    }
                }
            } else if (exception) {
                throw new RuntimeException(String.format("wrong Vector magic, got %x", new Object[]{Integer.valueOf(magic)}));
            } else {
                return;
            }
        }
        if ((this.flags & 16) != 0) {
            if (stream.readInt32(exception) == 481674261) {
                count = stream.readInt32(exception);
                a = 0;
                while (a < count) {
                    object = TLRPC$InputSecureFile.TLdeserialize(stream, stream.readInt32(exception), exception);
                    if (object != null) {
                        this.files.add(object);
                        a++;
                    } else {
                        return;
                    }
                }
            } else if (exception) {
                throw new RuntimeException(String.format("wrong Vector magic, got %x", new Object[]{Integer.valueOf(magic)}));
            } else {
                return;
            }
        }
        if ((this.flags & 32) != 0) {
            this.plain_data = TLRPC$SecurePlainData.TLdeserialize(stream, stream.readInt32(exception), exception);
        }
    }

    public void serializeToStream(AbstractSerializedData stream) {
        int count;
        int a;
        stream.writeInt32(constructor);
        stream.writeInt32(this.flags);
        this.type.serializeToStream(stream);
        if ((this.flags & 1) != 0) {
            this.data.serializeToStream(stream);
        }
        if ((this.flags & 2) != 0) {
            this.front_side.serializeToStream(stream);
        }
        if ((this.flags & 4) != 0) {
            this.reverse_side.serializeToStream(stream);
        }
        if ((this.flags & 8) != 0) {
            this.selfie.serializeToStream(stream);
        }
        if ((this.flags & 64) != 0) {
            stream.writeInt32(481674261);
            count = this.translation.size();
            stream.writeInt32(count);
            for (a = 0; a < count; a++) {
                ((TLRPC$InputSecureFile) this.translation.get(a)).serializeToStream(stream);
            }
        }
        if ((this.flags & 16) != 0) {
            stream.writeInt32(481674261);
            count = this.files.size();
            stream.writeInt32(count);
            for (a = 0; a < count; a++) {
                ((TLRPC$InputSecureFile) this.files.get(a)).serializeToStream(stream);
            }
        }
        if ((this.flags & 32) != 0) {
            this.plain_data.serializeToStream(stream);
        }
    }
}
