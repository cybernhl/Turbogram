package org.telegram.tgnet;

public class TLRPC$TL_exportedMessageLink extends TLObject {
    public static int constructor = 1571494644;
    public String html;
    public String link;

    public static TLRPC$TL_exportedMessageLink TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        if (constructor == constructor) {
            TLRPC$TL_exportedMessageLink result = new TLRPC$TL_exportedMessageLink();
            result.readParams(stream, exception);
            return result;
        } else if (!exception) {
            return null;
        } else {
            throw new RuntimeException(String.format("can't parse magic %x in TL_exportedMessageLink", new Object[]{Integer.valueOf(constructor)}));
        }
    }

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.link = stream.readString(exception);
        this.html = stream.readString(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeString(this.link);
        stream.writeString(this.html);
    }
}
