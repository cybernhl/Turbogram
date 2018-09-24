package org.telegram.tgnet;

import java.util.ArrayList;

public abstract class TLRPC$WebDocument extends TLObject {
    public long access_hash;
    public ArrayList<TLRPC$DocumentAttribute> attributes = new ArrayList();
    public String mime_type;
    public int size;
    public String url;

    public static TLRPC$WebDocument TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        TLRPC$WebDocument result = null;
        switch (constructor) {
            case -971322408:
                result = new TLRPC$TL_webDocument_layer81();
                break;
            case -104284986:
                result = new TLRPC$TL_webDocumentNoProxy();
                break;
            case 475467473:
                result = new TLRPC$TL_webDocument();
                break;
        }
        if (result == null && exception) {
            throw new RuntimeException(String.format("can't parse magic %x in WebDocument", new Object[]{Integer.valueOf(constructor)}));
        }
        if (result != null) {
            result.readParams(stream, exception);
        }
        return result;
    }
}
