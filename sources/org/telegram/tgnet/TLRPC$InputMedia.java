package org.telegram.tgnet;

import java.util.ArrayList;

public abstract class TLRPC$InputMedia extends TLObject {
    public String address;
    public ArrayList<TLRPC$DocumentAttribute> attributes = new ArrayList();
    public TLRPC$InputFile file;
    public String first_name;
    public int flags;
    public TLRPC$InputGeoPoint geo_point;
    public String last_name;
    public String mime_type;
    public boolean nosound_video;
    public int period;
    public String phone_number;
    public String provider;
    /* renamed from: q */
    public String f790q;
    public ArrayList<TLRPC$InputDocument> stickers = new ArrayList();
    public TLRPC$InputFile thumb;
    public String title;
    public int ttl_seconds;
    public String url;
    public String vcard;
    public String venue_id;
    public String venue_type;

    public static TLRPC$InputMedia TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        TLRPC$InputMedia result = null;
        switch (constructor) {
            case -1771768449:
                result = new TLRPC$TL_inputMediaEmpty();
                break;
            case -1279654347:
                result = new TLRPC$TL_inputMediaPhoto();
                break;
            case -1052959727:
                result = new TLRPC$TL_inputMediaVenue();
                break;
            case -750828557:
                result = new TLRPC$TL_inputMediaGame();
                break;
            case -440664550:
                result = new TLRPC$TL_inputMediaPhotoExternal();
                break;
            case -122978821:
                result = new TLRPC$TL_inputMediaContact();
                break;
            case -104578748:
                result = new TLRPC$TL_inputMediaGeoPoint();
                break;
            case -78455655:
                result = new TLRPC$TL_inputMediaDocumentExternal();
                break;
            case 505969924:
                result = new TLRPC$TL_inputMediaUploadedPhoto();
                break;
            case 598418386:
                result = new TLRPC$TL_inputMediaDocument();
                break;
            case 1212395773:
                result = new TLRPC$TL_inputMediaGifExternal();
                break;
            case 1530447553:
                result = new TLRPC$TL_inputMediaUploadedDocument();
                break;
            case 2065305999:
                result = new TLRPC$TL_inputMediaGeoLive();
                break;
        }
        if (result == null && exception) {
            throw new RuntimeException(String.format("can't parse magic %x in InputMedia", new Object[]{Integer.valueOf(constructor)}));
        }
        if (result != null) {
            result.readParams(stream, exception);
        }
        return result;
    }
}
