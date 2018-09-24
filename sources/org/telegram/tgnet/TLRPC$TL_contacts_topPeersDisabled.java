package org.telegram.tgnet;

public class TLRPC$TL_contacts_topPeersDisabled extends TLRPC$contacts_TopPeers {
    public static int constructor = -1255369827;

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
    }
}
