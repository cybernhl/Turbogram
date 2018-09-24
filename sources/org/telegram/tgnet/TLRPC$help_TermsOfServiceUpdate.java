package org.telegram.tgnet;

public abstract class TLRPC$help_TermsOfServiceUpdate extends TLObject {
    public static TLRPC$help_TermsOfServiceUpdate TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        TLRPC$help_TermsOfServiceUpdate result = null;
        switch (constructor) {
            case -483352705:
                result = new TLRPC$TL_help_termsOfServiceUpdateEmpty();
                break;
            case 686618977:
                result = new TLRPC$TL_help_termsOfServiceUpdate();
                break;
        }
        if (result == null && exception) {
            throw new RuntimeException(String.format("can't parse magic %x in help_TermsOfServiceUpdate", new Object[]{Integer.valueOf(constructor)}));
        }
        if (result != null) {
            result.readParams(stream, exception);
        }
        return result;
    }
}
