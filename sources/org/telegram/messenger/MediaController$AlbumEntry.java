package org.telegram.messenger;

import android.util.SparseArray;
import java.util.ArrayList;

public class MediaController$AlbumEntry {
    public int bucketId;
    public String bucketName;
    public MediaController$PhotoEntry coverPhoto;
    public ArrayList<MediaController$PhotoEntry> photos = new ArrayList();
    public SparseArray<MediaController$PhotoEntry> photosByIds = new SparseArray();

    public MediaController$AlbumEntry(int bucketId, String bucketName, MediaController$PhotoEntry coverPhoto) {
        this.bucketId = bucketId;
        this.bucketName = bucketName;
        this.coverPhoto = coverPhoto;
    }

    public void addPhoto(MediaController$PhotoEntry photoEntry) {
        this.photos.add(photoEntry);
        this.photosByIds.put(photoEntry.imageId, photoEntry);
    }
}
