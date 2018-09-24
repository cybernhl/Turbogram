package org.telegram.tgnet;

public class TLRPC$TL_geoPoint extends TLRPC$GeoPoint {
    public static int constructor = 43446532;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this._long = stream.readDouble(exception);
        this.lat = stream.readDouble(exception);
        this.access_hash = stream.readInt64(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeDouble(this._long);
        stream.writeDouble(this.lat);
        stream.writeInt64(this.access_hash);
    }
}
