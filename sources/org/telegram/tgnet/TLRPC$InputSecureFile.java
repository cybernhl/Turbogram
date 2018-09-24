package org.telegram.tgnet;

public abstract class TLRPC$InputSecureFile extends TLObject {
    public static TLRPC$InputSecureFile TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        TLRPC$InputSecureFile result = null;
        switch (constructor) {
            case 859091184:
                result = new TLRPC$TL_inputSecureFileUploaded();
                break;
            case 1399317950:
                result = new TLRPC$TL_inputSecureFile();
                break;
        }
        if (result == null && exception) {
            throw new RuntimeException(String.format("can't parse magic %x in InputSecureFile", new Object[]{Integer.valueOf(constructor)}));
        }
        if (result != null) {
            result.readParams(stream, exception);
        }
        return result;
    }
}
