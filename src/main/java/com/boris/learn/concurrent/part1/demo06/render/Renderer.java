package com.boris.learn.concurrent.part1.demo06.render;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Renderer {
    private final ExecutorService executor;

    public Renderer(ExecutorService executor) {
        this.executor = executor;
    }

    public void renderPage() {
        List<ImageInfo> info = getImageInfos();
        CompletionService<ImageData> completionService =
                new ExecutorCompletionService<ImageData>(executor);
        for (ImageInfo imageInfo : info) {
            completionService.submit(new Callable<ImageData>() {
                @Override
                public ImageData call() throws Exception {
                    System.out.println("get result......");
                    return imageInfo.getImageData();
                }
            });
        }

        try {
            for (int t=0, n=info.size(); t<n; t++) {
                Future<ImageData> f = completionService.take();
                ImageData imageData = f.get();
                System.out.println(imageData.getImgUrl());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public List<ImageInfo> getImageInfos() {
        List<ImageInfo> info = new ArrayList<>();
        ImageData imageData = new ImageData(200 ,"http://aa.jpg");
        ImageInfo imageInfo = new ImageInfo("aa.jpg", imageData);
        info.add(imageInfo);

        ImageData imageData1 = new ImageData(300 ,"http://bb.jpg");
        ImageInfo imageInfo1 = new ImageInfo("bb.jpg", imageData1);
        info.add(imageInfo1);

        ImageData imageData2 = new ImageData(400 ,"http://cc.jpg");
        ImageInfo imageInfo2 = new ImageInfo("cc.jpg", imageData2);
        info.add(imageInfo2);
        return info;
    }

    public static void main(String[] args) {
        Renderer renderer = new Renderer(Executors.newCachedThreadPool());
        renderer.renderPage();
    }
}
