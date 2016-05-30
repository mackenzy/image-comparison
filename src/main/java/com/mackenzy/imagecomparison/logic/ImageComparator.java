package com.mackenzy.imagecomparison.logic;

import com.mackenzy.imagecomparison.beans.Pixel;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ImageComparator {

    public List<Pixel> compare(BufferedImage img1, BufferedImage img2, double diffThreshold) {

        int h1 = img1.getHeight();
        int w1 = img1.getWidth();
        int h2 = img2.getHeight();
        int w2 = img2.getWidth();

        if (h1 != h2 || w1 != w2) {
            throw new RuntimeException("Images have different size!");
        }

        List<Pixel> pixels2Blank = new ArrayList<>();

        for (int y = 0; y < h1; y++) {
            for (int x = 0; x < w1; x++) {
                int rgb1 = img1.getRGB(x, y);
                int rgb2 = img2.getRGB(x, y);

                int red1 = rgb1 >> 0xf & 0xff;
                int grn1 = rgb1 >> 0x8 & 0xff;
                int blu1 = rgb1 & 0xff;

                int red2 = rgb2 >> 0xf & 0xff;
                int grn2 = rgb2 >> 0x8 & 0xff;
                int blu2 = rgb2 & 0xff;

                int diffSum = Math.abs(red1 - red2) + Math.abs(grn1 - grn2) + Math.abs(blu1 - blu2);
                double diffPercent = (double) diffSum / (255 * 3);

                if (diffPercent > diffThreshold) {
                    pixels2Blank.add(new Pixel(x, y));
                }
            }
        }

        return pixels2Blank;
    }
}