package org.example.sonic;

import java.util.List;

public class MediaContent
        implements java.io.Serializable {
    public Media media;
    public List<Image> images;

    public MediaContent() {
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Media getMedia() {
        return media;
    }

    public List<Image> getImages() {
        return images;
    }
}
