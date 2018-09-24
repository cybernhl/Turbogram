package org.telegram.tgnet;

public abstract class TLRPC$SecurePlainData extends TLObject {
    public static TLRPC$SecurePlainData TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        TLRPC$SecurePlainData result = null;
        switch (constructor) {
            case 569137759:
                result = new TLRPC$TL_securePlainEmail();
                break;
            case 2103482845:
                result = new TLRPC$TL_securePlainPhone();
                break;
        }
        if (result == null && exception) {
            throw new RuntimeException(String.format("can't parse magic %x in SecurePlainData", new Object[]{Integer.valueOf(constructor)}));
        }
        if (result != null) {
            result.readParams(stream, exception);
        }
        return result;
    }
}
