package org.telegram.tgnet;

import android.text.TextUtils;
import com.google.android.vending.licensing.Policy;

public class TLRPC$TL_message_old extends TLRPC$TL_message {
    public static int constructor = 585853626;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.id = stream.readInt32(exception);
        this.from_id = stream.readInt32(exception);
        this.to_id = TLRPC$Peer.TLdeserialize(stream, stream.readInt32(exception), exception);
        this.out = stream.readBool(exception);
        this.unread = stream.readBool(exception);
        this.flags |= Policy.MYKET_NOT_INSTALLED;
        this.date = stream.readInt32(exception);
        this.message = stream.readString(exception);
        this.media = TLRPC$MessageMedia.TLdeserialize(stream, stream.readInt32(exception), exception);
        if (this.media != null && !TextUtils.isEmpty(this.media.captionLegacy)) {
            this.message = this.media.captionLegacy;
        }
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeInt32(this.id);
        stream.writeInt32(this.from_id);
        this.to_id.serializeToStream(stream);
        stream.writeBool(this.out);
        stream.writeBool(this.unread);
        stream.writeInt32(this.date);
        stream.writeString(this.message);
        this.media.serializeToStream(stream);
        writeAttachPath(stream);
    }
}
