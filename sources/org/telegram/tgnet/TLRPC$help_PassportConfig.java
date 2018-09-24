package org.telegram.tgnet;

public abstract class TLRPC$help_PassportConfig extends TLObject {
    public static TLRPC$help_PassportConfig TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        TLRPC$help_PassportConfig result = null;
        switch (constructor) {
            case -1600596305:
                result = new TLRPC$TL_help_passportConfig();
                break;
            case -1078332329:
                result = new TLRPC$TL_help_passportConfigNotModified();
                break;
        }
        if (result == null && exception) {
            throw new RuntimeException(String.format("can't parse magic %x in help_PassportConfig", new Object[]{Integer.valueOf(constructor)}));
        }
        if (result != null) {
            result.readParams(stream, exception);
        }
        return result;
    }
}
