package com.mackenzy.imagecomparison.logic;

import com.mackenzy.imagecomparison.beans.Pixel;
import com.mackenzy.imagecomparison.beans.Section;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PixelAreaCutter {

    private final int lineSegmentSize;

    public PixelAreaCutter(int lineSegmentSize) {
        this.lineSegmentSize = lineSegmentSize;
    }

    public List<Section> defineAndCut(List<Pixel> pixels) {
        List<List<Pixel>> blankLogs = cutBlankLogYs(pixels);

        blankLogs.forEach(this::sortXPixelsByX);

        return cutSections(blankLogs);
    }

    protected List<List<Pixel>> cutBlankLogYs(List<Pixel> pixelsSortedByY) {

        Iterator<Pixel> iterator = pixelsSortedByY.iterator();
        List<List<Pixel>> listToReturn = new ArrayList<>();
        List<Pixel> blankLogsPixels = new ArrayList<>();

        int yPrevPoint = iterator.next().getY();

        while (iterator.hasNext()) {
            Pixel next = iterator.next();

            if (next.getY() - yPrevPoint > lineSegmentSize) {
                listToReturn.add(blankLogsPixels);
                blankLogsPixels = new ArrayList<>();
            }

            blankLogsPixels.add(next);
            yPrevPoint = next.getY();

            if (!iterator.hasNext()) {
                listToReturn.add(blankLogsPixels);
            }
        }

        return listToReturn;
    }

    protected List<Section> cutSections(List<List<Pixel>> blanks) {

        List<Section> listToReturn = new ArrayList<>();

        for (List<Pixel> blankY : blanks) {

            Iterator<Pixel> iterator = blankY.iterator();

            Pixel next = iterator.next();

            int xStartPoint = next.getX();
            int xPrevPoint = 0;
            int yPrevPoint;
            int maxY = Integer.MIN_VALUE;
            int minY = Integer.MAX_VALUE;

            while (iterator.hasNext()) {
                xPrevPoint = next.getX();
                yPrevPoint = next.getY();

                next = iterator.next();

                if (yPrevPoint > maxY) {
                    maxY = yPrevPoint;
                }

                if (minY > yPrevPoint) {
                    minY = yPrevPoint;
                }

                if (next.getX() - xPrevPoint > lineSegmentSize) {
                    listToReturn.add(new Section(xStartPoint, minY, xPrevPoint, maxY));

                    xStartPoint = next.getX();

                    maxY = Integer.MIN_VALUE;
                    minY = Integer.MAX_VALUE;
                }
            }

            int currY = next.getY();

            if (currY > maxY) {
                maxY = currY;
            }
            if (minY > currY) {
                minY = currY;
            }

            listToReturn.add(new Section(xStartPoint, minY, xPrevPoint, maxY));
        }

        return listToReturn;
    }

    protected void sortXPixelsByX(List<Pixel> pixels) {
        Collections.sort(pixels, (o1, o2) -> Integer.compare(o1.getX(), o2.getX()));
    }
}
