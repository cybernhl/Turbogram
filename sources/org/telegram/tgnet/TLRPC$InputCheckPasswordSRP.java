package org.telegram.tgnet;

public abstract class TLRPC$InputCheckPasswordSRP extends TLObject {
    public static TLRPC$InputCheckPasswordSRP TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        TLRPC$InputCheckPasswordSRP result = null;
        switch (constructor) {
            case -1736378792:
                result = new TLRPC$TL_inputCheckPasswordEmpty();
                break;
            case -763367294:
                result = new TLRPC$TL_inputCheckPasswordSRP();
                break;
        }
        if (result == null && exception) {
            throw new RuntimeException(String.format("can't parse magic %x in InputCheckPasswordSRP", new Object[]{Integer.valueOf(constructor)}));
        }
        if (result != null) {
            result.readParams(stream, exception);
        }
        return result;
    }
}
