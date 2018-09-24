package org.telegram.tgnet;

public abstract class TLRPC$SecureValueError extends TLObject {
    public static TLRPC$SecureValueError TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        TLRPC$SecureValueError result = null;
        switch (constructor) {
            case -2037765467:
                result = new TLRPC$TL_secureValueErrorReverseSide();
                break;
            case -2036501105:
                result = new TLRPC$TL_secureValueError();
                break;
            case -1592506512:
                result = new TLRPC$TL_secureValueErrorTranslationFile();
                break;
            case -449327402:
                result = new TLRPC$TL_secureValueErrorSelfie();
                break;
            case -391902247:
                result = new TLRPC$TL_secureValueErrorData();
                break;
            case 12467706:
                result = new TLRPC$TL_secureValueErrorFrontSide();
                break;
            case 878931416:
                result = new TLRPC$TL_secureValueErrorTranslationFiles();
                break;
            case 1717706985:
                result = new TLRPC$TL_secureValueErrorFiles();
                break;
            case 2054162547:
                result = new TLRPC$TL_secureValueErrorFile();
                break;
        }
        if (result == null && exception) {
            throw new RuntimeException(String.format("can't parse magic %x in SecureValueError", new Object[]{Integer.valueOf(constructor)}));
        }
        if (result != null) {
            result.readParams(stream, exception);
        }
        return result;
    }
}
