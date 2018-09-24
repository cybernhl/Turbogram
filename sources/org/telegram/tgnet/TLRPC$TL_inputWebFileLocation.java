package org.telegram.tgnet;

public class TLRPC$TL_inputWebFileLocation extends TLRPC$InputWebFileLocation {
    public static int constructor = -1036396922;
    public long access_hash;
    public String url;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.url = stream.readString(exception);
        this.access_hash = stream.readInt64(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeString(this.url);
        stream.writeInt64(this.access_hash);
    }
}
