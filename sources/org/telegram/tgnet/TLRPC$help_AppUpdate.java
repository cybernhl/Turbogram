package org.telegram.tgnet;

public abstract class TLRPC$help_AppUpdate extends TLObject {
    public static TLRPC$help_AppUpdate TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        TLRPC$help_AppUpdate result = null;
        switch (constructor) {
            case -1000708810:
                result = new TLRPC$TL_help_noAppUpdate();
                break;
            case 497489295:
                result = new TLRPC$TL_help_appUpdate();
                break;
        }
        if (result == null && exception) {
            throw new RuntimeException(String.format("can't parse magic %x in help_AppUpdate", new Object[]{Integer.valueOf(constructor)}));
        }
        if (result != null) {
            result.readParams(stream, exception);
        }
        return result;
    }
}
