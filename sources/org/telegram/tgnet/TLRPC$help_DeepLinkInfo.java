package org.telegram.tgnet;

public abstract class TLRPC$help_DeepLinkInfo extends TLObject {
    public static TLRPC$help_DeepLinkInfo TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        TLRPC$help_DeepLinkInfo result = null;
        switch (constructor) {
            case 1722786150:
                result = new TLRPC$TL_help_deepLinkInfoEmpty();
                break;
            case 1783556146:
                result = new TLRPC$TL_help_deepLinkInfo();
                break;
        }
        if (result == null && exception) {
            throw new RuntimeException(String.format("can't parse magic %x in help_DeepLinkInfo", new Object[]{Integer.valueOf(constructor)}));
        }
        if (result != null) {
            result.readParams(stream, exception);
        }
        return result;
    }
}
