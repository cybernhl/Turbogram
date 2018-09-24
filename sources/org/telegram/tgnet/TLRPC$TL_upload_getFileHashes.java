package org.telegram.tgnet;

public class TLRPC$TL_upload_getFileHashes extends TLObject {
    public static int constructor = -956147407;
    public TLRPC$InputFileLocation location;
    public int offset;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        TLRPC$Vector vector = new TLRPC$Vector();
        int size = stream.readInt32(exception);
        for (int a = 0; a < size; a++) {
            TLRPC$TL_fileHash object = TLRPC$TL_fileHash.TLdeserialize(stream, stream.readInt32(exception), exception);
            if (object == null) {
                break;
            }
            vector.objects.add(object);
        }
        return vector;
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        this.location.serializeToStream(stream);
        stream.writeInt32(this.offset);
    }
}
