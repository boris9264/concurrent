package com.boris.learn.concurrent.part1.demo06.render;

public class ImageData {
    private int size;
    private String imgUrl;

    public ImageData(int size, String imgUrl) {
        this.size = size;
        this.imgUrl = imgUrl;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
