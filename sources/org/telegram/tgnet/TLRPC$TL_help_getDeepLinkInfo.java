package org.telegram.tgnet;

public class TLRPC$TL_help_getDeepLinkInfo extends TLObject {
    public static int constructor = 1072547679;
    public String path;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$help_DeepLinkInfo.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeString(this.path);
    }
}
