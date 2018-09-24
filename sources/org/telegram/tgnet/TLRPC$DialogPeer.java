package org.telegram.tgnet;

public abstract class TLRPC$DialogPeer extends TLObject {
    public static TLRPC$DialogPeer TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        TLRPC$DialogPeer result = null;
        switch (constructor) {
            case -633170927:
                result = new TLRPC$TL_dialogPeerFeed();
                break;
            case -445792507:
                result = new TLRPC$TL_dialogPeer();
                break;
        }
        if (result == null && exception) {
            throw new RuntimeException(String.format("can't parse magic %x in DialogPeer", new Object[]{Integer.valueOf(constructor)}));
        }
        if (result != null) {
            result.readParams(stream, exception);
        }
        return result;
    }
}
