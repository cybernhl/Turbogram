package org.telegram.tgnet;

public abstract class TLRPC$PasswordKdfAlgo extends TLObject {
    public static TLRPC$PasswordKdfAlgo TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        TLRPC$PasswordKdfAlgo result = null;
        switch (constructor) {
            case -732254058:
                result = new TLRPC$TL_passwordKdfAlgoUnknown();
                break;
            case 982592842:
                result = new C0972x72c667f();
                break;
        }
        if (result == null && exception) {
            throw new RuntimeException(String.format("can't parse magic %x in PasswordKdfAlgo", new Object[]{Integer.valueOf(constructor)}));
        }
        if (result != null) {
            result.readParams(stream, exception);
        }
        return result;
    }
}
