package org.telegram.tgnet;

public abstract class TLRPC$SecureFile extends TLObject {
    public static TLRPC$SecureFile TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        TLRPC$SecureFile result = null;
        switch (constructor) {
            case -534283678:
                result = new TLRPC$TL_secureFile();
                break;
            case 1679398724:
                result = new TLRPC$TL_secureFileEmpty();
                break;
        }
        if (result == null && exception) {
            throw new RuntimeException(String.format("can't parse magic %x in SecureFile", new Object[]{Integer.valueOf(constructor)}));
        }
        if (result != null) {
            result.readParams(stream, exception);
        }
        return result;
    }
}
