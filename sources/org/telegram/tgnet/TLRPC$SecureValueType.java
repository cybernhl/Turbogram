package org.telegram.tgnet;

public abstract class TLRPC$SecureValueType extends TLObject {
    public static TLRPC$SecureValueType TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        TLRPC$SecureValueType result = null;
        switch (constructor) {
            case -1995211763:
                result = new TLRPC$TL_secureValueTypeBankStatement();
                break;
            case -1954007928:
                result = new TLRPC$TL_secureValueTypeRentalAgreement();
                break;
            case -1908627474:
                result = new TLRPC$TL_secureValueTypeEmail();
                break;
            case -1717268701:
                result = new TLRPC$TL_secureValueTypeInternalPassport();
                break;
            case -1713143702:
                result = new TLRPC$TL_secureValueTypePassportRegistration();
                break;
            case -1658158621:
                result = new TLRPC$TL_secureValueTypePersonalDetails();
                break;
            case -1596951477:
                result = new TLRPC$TL_secureValueTypeIdentityCard();
                break;
            case -1289704741:
                result = new TLRPC$TL_secureValueTypePhone();
                break;
            case -874308058:
                result = new TLRPC$TL_secureValueTypeAddress();
                break;
            case -368907213:
                result = new TLRPC$TL_secureValueTypeTemporaryRegistration();
                break;
            case -63531698:
                result = new TLRPC$TL_secureValueTypeUtilityBill();
                break;
            case 115615172:
                result = new TLRPC$TL_secureValueTypeDriverLicense();
                break;
            case 1034709504:
                result = new TLRPC$TL_secureValueTypePassport();
                break;
        }
        if (result == null && exception) {
            throw new RuntimeException(String.format("can't parse magic %x in SecureValueType", new Object[]{Integer.valueOf(constructor)}));
        }
        if (result != null) {
            result.readParams(stream, exception);
        }
        return result;
    }
}
