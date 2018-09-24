package org.telegram.tgnet;

public abstract class TLRPC$InputDialogPeer extends TLObject {
    public static TLRPC$InputDialogPeer TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        TLRPC$InputDialogPeer result = null;
        switch (constructor) {
            case -55902537:
                result = new TLRPC$TL_inputDialogPeer();
                break;
            case 741914831:
                result = new TLRPC$TL_inputDialogPeerFeed();
                break;
        }
        if (result == null && exception) {
            throw new RuntimeException(String.format("can't parse magic %x in InputDialogPeer", new Object[]{Integer.valueOf(constructor)}));
        }
        if (result != null) {
            result.readParams(stream, exception);
        }
        return result;
    }
}
