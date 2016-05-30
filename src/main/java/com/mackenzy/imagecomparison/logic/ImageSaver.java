package com.mackenzy.imagecomparison.logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageSaver {
    private static final String OUTPUT_FORMAT = "jpg";

    public void saveImage(BufferedImage img, String path) throws IOException {
        ImageIO.write(img, OUTPUT_FORMAT, new File(path));
    }
}
