package com.mackenzy.imagecomparison.logic;

import com.mackenzy.imagecomparison.beans.Pixel;
import com.mackenzy.imagecomparison.beans.Section;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PixelAreaCutterTest extends TestCase {

    @Test
    public void testCutBlankLogYsEndPixel() throws Exception {

        PixelAreaCutter target = new PixelAreaCutter(5);
        List<Pixel> pixels = new ArrayList<>();

        pixels.add(new Pixel(1, 2));
        pixels.add(new Pixel(6, 2));
        pixels.add(new Pixel(6, 7));
        pixels.add(new Pixel(12, 7));
        pixels.add(new Pixel(12, 13));

        List<List<Pixel>> blankLogs = target.cutBlankLogYs(pixels);

        assertEquals(2, blankLogs.size());
    }

    @Test
    public void testCutBlankLogYsStartPixel() throws Exception {

        PixelAreaCutter target = new PixelAreaCutter(4);
        List<Pixel> pixels = new ArrayList<>();

        pixels.add(new Pixel(1, 2));
        pixels.add(new Pixel(6, 2));
        pixels.add(new Pixel(6, 7));
        pixels.add(new Pixel(12, 7));
        pixels.add(new Pixel(12, 13));

        List<List<Pixel>> blankLogs = target.cutBlankLogYs(pixels);

        assertEquals(3, blankLogs.size());

        Pixel pixel = blankLogs.get(0).get(0);
        assertTrue(pixel.getX() == 6 && pixel.getY() == 2);

        pixel = blankLogs.get(2).get(0);
        assertTrue(pixel.getX() == 12 && pixel.getY() == 13);
    }

    @Test
    public void testCutSections() throws Exception {
        PixelAreaCutter target = new PixelAreaCutter(4);
        List<Pixel> pixels = new ArrayList<>();

        pixels.add(new Pixel(1, 2));
        pixels.add(new Pixel(6, 2));
        pixels.add(new Pixel(6, 7));
        pixels.add(new Pixel(12, 7));
        pixels.add(new Pixel(12, 13));

        List<List<Pixel>> secList = new ArrayList<>();

        secList.add(pixels);
        secList.add(pixels);

        List<Section> sections = target.cutSections(secList);

        assertEquals(6, sections.size());

        Section sec = sections.get(0);
        assertTrue(sec.getMinX() == 1 && sec.getMaxX() == 1 && sec.getMinY() == 2 && sec.getMaxY() == 2);

        sec = sections.get(1);
        assertTrue(sec.getMinX() == 6 && sec.getMaxX() == 6 && sec.getMinY() == 2 && sec.getMaxY() == 7);

        sec = sections.get(2);
        assertTrue(sec.getMinX() == 12 && sec.getMaxX() == 12 && sec.getMinY() == 7 && sec.getMaxY() == 13);

    }
}