package com.mackenzy.imagecomparison.logic;

import com.mackenzy.imagecomparison.beans.Section;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class ImageOutliner {

    private static final float OUTPUT_STROKE = 2f;

    public void outline(BufferedImage template, List<Section> sections, int indent) {
        Graphics2D g2 = template.createGraphics();

        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(OUTPUT_STROKE));

        for (Section sec : sections) {
            g2.drawRect(sec.getMinX() - indent,
                    sec.getMinY() - indent,
                    sec.getMaxX() - sec.getMinX() + indent * 2,
                    sec.getMaxY() - sec.getMinY() + indent * 2);
        }
    }
}
