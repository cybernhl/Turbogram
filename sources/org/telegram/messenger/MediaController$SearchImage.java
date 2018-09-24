package org.telegram.messenger;

import java.io.File;
import java.util.ArrayList;
import org.telegram.tgnet.TLRPC$Document;
import org.telegram.tgnet.TLRPC$InputDocument;
import org.telegram.tgnet.TLRPC$MessageEntity;
import org.telegram.tgnet.TLRPC$Photo;
import org.telegram.tgnet.TLRPC$PhotoSize;

public class MediaController$SearchImage {
    public CharSequence caption;
    public int date;
    public TLRPC$Document document;
    public ArrayList<TLRPC$MessageEntity> entities;
    public int height;
    public String id;
    public String imagePath;
    public String imageUrl;
    public boolean isCropped;
    public boolean isFiltered;
    public boolean isPainted;
    public String localUrl;
    public TLRPC$Photo photo;
    public TLRPC$PhotoSize photoSize;
    public MediaController$SavedFilterState savedFilterState;
    public int size;
    public ArrayList<TLRPC$InputDocument> stickers = new ArrayList();
    public String thumbPath;
    public TLRPC$PhotoSize thumbPhotoSize;
    public String thumbUrl;
    public int ttl;
    public int type;
    public int width;

    public void reset() {
        this.isFiltered = false;
        this.isPainted = false;
        this.isCropped = false;
        this.ttl = 0;
        this.imagePath = null;
        this.thumbPath = null;
        this.caption = null;
        this.entities = null;
        this.savedFilterState = null;
        this.stickers.clear();
    }

    public String getAttachName() {
        if (this.photoSize != null) {
            return FileLoader.getAttachFileName(this.photoSize);
        }
        if (this.document != null) {
            return FileLoader.getAttachFileName(this.document);
        }
        if (!(this.type == 1 || this.localUrl == null || this.localUrl.length() <= 0)) {
            File file = new File(this.localUrl);
            if (file.exists()) {
                return file.getName();
            }
            this.localUrl = "";
        }
        return Utilities.MD5(this.imageUrl) + "." + ImageLoader.getHttpUrlExtension(this.imageUrl, "jpg");
    }

    public String getPathToAttach() {
        if (this.photoSize != null) {
            return FileLoader.getPathToAttach(this.photoSize, true).getAbsolutePath();
        }
        if (this.document != null) {
            return FileLoader.getPathToAttach(this.document, true).getAbsolutePath();
        }
        return this.imageUrl;
    }
}
