package org.telegram.tgnet;

public abstract class TLRPC$help_ProxyData extends TLObject {
    public static TLRPC$help_ProxyData TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        TLRPC$help_ProxyData result = null;
        switch (constructor) {
            case -526508104:
                result = new TLRPC$TL_help_proxyDataEmpty();
                break;
            case 737668643:
                result = new TLRPC$TL_help_proxyDataPromo();
                break;
        }
        if (result == null && exception) {
            throw new RuntimeException(String.format("can't parse magic %x in help_ProxyData", new Object[]{Integer.valueOf(constructor)}));
        }
        if (result != null) {
            result.readParams(stream, exception);
        }
        return result;
    }
}
