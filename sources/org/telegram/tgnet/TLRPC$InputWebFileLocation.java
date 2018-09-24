package org.telegram.tgnet;

public abstract class TLRPC$InputWebFileLocation extends TLObject {
    public static TLRPC$InputWebFileLocation TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        TLRPC$InputWebFileLocation result = null;
        switch (constructor) {
            case -1625153079:
                result = new TLRPC$TL_inputWebFileGeoPointLocation();
                break;
            case -1036396922:
                result = new TLRPC$TL_inputWebFileLocation();
                break;
        }
        if (result == null && exception) {
            throw new RuntimeException(String.format("can't parse magic %x in InputWebFileLocation", new Object[]{Integer.valueOf(constructor)}));
        }
        if (result != null) {
            result.readParams(stream, exception);
        }
        return result;
    }
}
