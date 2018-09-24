package org.telegram.messenger;

import android.net.Uri;
import java.util.ArrayList;
import org.telegram.tgnet.TLRPC$InputDocument;
import org.telegram.tgnet.TLRPC$MessageEntity;

public class SendMessagesHelper$SendingMediaInfo {
    public String caption;
    public ArrayList<TLRPC$MessageEntity> entities;
    public boolean isVideo;
    public ArrayList<TLRPC$InputDocument> masks;
    public String path;
    public MediaController$SearchImage searchImage;
    public int ttl;
    public Uri uri;
    public VideoEditedInfo videoEditedInfo;
}
