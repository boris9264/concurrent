package com.boris.learn.concurrent.part1.demo06.render;

public class ImageInfo {
    private String imgName;
    private ImageData imageData;

    public ImageInfo(String imgName, ImageData imageData) {
        this.imgName = imgName;
        this.imageData = imageData;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public ImageData getImageData() {
        try {
            Thread.sleep(5000);
            System.out.println(Thread.currentThread() + " init....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return imageData;
    }

    public void setImageData(ImageData imageData) {
        this.imageData = imageData;
    }
}
