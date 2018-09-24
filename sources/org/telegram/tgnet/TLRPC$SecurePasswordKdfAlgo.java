package org.telegram.tgnet;

public abstract class TLRPC$SecurePasswordKdfAlgo extends TLObject {
    public static TLRPC$SecurePasswordKdfAlgo TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        TLRPC$SecurePasswordKdfAlgo result = null;
        switch (constructor) {
            case -2042159726:
                result = new TLRPC$TL_securePasswordKdfAlgoSHA512();
                break;
            case -1141711456:
                result = new TLRPC$TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000();
                break;
            case 4883767:
                result = new TLRPC$TL_securePasswordKdfAlgoUnknown();
                break;
        }
        if (result == null && exception) {
            throw new RuntimeException(String.format("can't parse magic %x in SecurePasswordKdfAlgo", new Object[]{Integer.valueOf(constructor)}));
        }
        if (result != null) {
            result.readParams(stream, exception);
        }
        return result;
    }
}
