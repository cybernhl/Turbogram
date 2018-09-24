package org.telegram.tgnet;

import java.util.ArrayList;

public class TLRPC$TL_account_acceptAuthorization extends TLObject {
    public static int constructor = -419267436;
    public int bot_id;
    public TLRPC$TL_secureCredentialsEncrypted credentials;
    public String public_key;
    public String scope;
    public ArrayList<TLRPC$TL_secureValueHash> value_hashes = new ArrayList();

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$Bool.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeInt32(this.bot_id);
        stream.writeString(this.scope);
        stream.writeString(this.public_key);
        stream.writeInt32(481674261);
        int count = this.value_hashes.size();
        stream.writeInt32(count);
        for (int a = 0; a < count; a++) {
            ((TLRPC$TL_secureValueHash) this.value_hashes.get(a)).serializeToStream(stream);
        }
        this.credentials.serializeToStream(stream);
    }
}
