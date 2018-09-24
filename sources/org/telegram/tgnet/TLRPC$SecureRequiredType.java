package org.telegram.tgnet;

public abstract class TLRPC$SecureRequiredType extends TLObject {
    public static TLRPC$SecureRequiredType TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        TLRPC$SecureRequiredType result = null;
        switch (constructor) {
            case -2103600678:
                result = new TLRPC$TL_secureRequiredType();
                break;
            case 41187252:
                result = new TLRPC$TL_secureRequiredTypeOneOf();
                break;
        }
        if (result == null && exception) {
            throw new RuntimeException(String.format("can't parse magic %x in SecureRequiredType", new Object[]{Integer.valueOf(constructor)}));
        }
        if (result != null) {
            result.readParams(stream, exception);
        }
        return result;
    }
}
