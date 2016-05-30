package com.mackenzy.imagecomparison.logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageReader {

    public BufferedImage readImage(String path) throws IOException {
        return ImageIO.read(new File(path));
    }
}
