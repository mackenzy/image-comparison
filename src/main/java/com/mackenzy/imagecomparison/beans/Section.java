package com.mackenzy.imagecomparison.beans;

public class Section {

    private final int minX;
    private final int maxX;
    private final int minY;
    private final int maxY;

    public Section(int minX, int minY, int maxX, int maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public int getMinX() {
        return minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxY() {
        return maxY;
    }
}
