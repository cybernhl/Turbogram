package org.telegram.tgnet;

public abstract class TLRPC$contacts_TopPeers extends TLObject {
    public static TLRPC$contacts_TopPeers TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        TLRPC$contacts_TopPeers result = null;
        switch (constructor) {
            case -1255369827:
                result = new TLRPC$TL_contacts_topPeersDisabled();
                break;
            case -567906571:
                result = new TLRPC$TL_contacts_topPeersNotModified();
                break;
            case 1891070632:
                result = new TLRPC$TL_contacts_topPeers();
                break;
        }
        if (result == null && exception) {
            throw new RuntimeException(String.format("can't parse magic %x in contacts_TopPeers", new Object[]{Integer.valueOf(constructor)}));
        }
        if (result != null) {
            result.readParams(stream, exception);
        }
        return result;
    }
}
