package org.telegram.tgnet;

public class TLRPC$TL_inputWebFileGeoPointLocation extends TLRPC$InputWebFileLocation {
    public static int constructor = -1625153079;
    public long access_hash;
    public TLRPC$InputGeoPoint geo_point;
    /* renamed from: h */
    public int f798h;
    public int scale;
    /* renamed from: w */
    public int f799w;
    public int zoom;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.geo_point = TLRPC$InputGeoPoint.TLdeserialize(stream, stream.readInt32(exception), exception);
        this.access_hash = stream.readInt64(exception);
        this.f799w = stream.readInt32(exception);
        this.f798h = stream.readInt32(exception);
        this.zoom = stream.readInt32(exception);
        this.scale = stream.readInt32(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        this.geo_point.serializeToStream(stream);
        stream.writeInt64(this.access_hash);
        stream.writeInt32(this.f799w);
        stream.writeInt32(this.f798h);
        stream.writeInt32(this.zoom);
        stream.writeInt32(this.scale);
    }
}
