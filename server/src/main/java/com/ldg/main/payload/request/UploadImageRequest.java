package com.ldg.main.payload.request;

public class UploadImageRequest {
    private String image;

    public UploadImageRequest() {
    }

    public UploadImageRequest(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
