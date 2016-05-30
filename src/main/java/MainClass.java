import com.mackenzy.imagecomparison.beans.Pixel;
import com.mackenzy.imagecomparison.beans.Section;
import com.mackenzy.imagecomparison.logic.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class MainClass {
    private static final String IMAGE_1 = "1.jpg";
    private static final String IMAGE_2 = "2.jpg";
    private static final String RESULT_IMAGE = "3.jpg";
    private static final String OUTPUT_DIR = Paths.get("").toAbsolutePath().toString() + File.separator + "out" + File.separator;
    private static final String INPUT_DIR = Paths.get("").toAbsolutePath().toString() + File.separator + "in" + File.separator;
    private static final double DIFF_THRESHOLD = 0.1;
    private static final int LINE_SEGMENT_SIZE = 2;
    private static final int INDENT_SIZE = 5;

    static {
        File outDir = new File(OUTPUT_DIR);

        if (!outDir.exists() && outDir.mkdir()) {
            System.out.println("Directory " + OUTPUT_DIR + " has been created");
        }
    }

    private final ImageReader imageReader = new ImageReader();
    private final ImageSaver imageSaver = new ImageSaver();
    private final ImageComparator imageComparator = new ImageComparator();
    private final PixelAreaCutter pixelAreaCutter = new PixelAreaCutter(LINE_SEGMENT_SIZE);
    private final ImageOutliner imageOutliner = new ImageOutliner();

    void main() throws IOException {

        BufferedImage img1 = imageReader.readImage(INPUT_DIR + IMAGE_1);
        BufferedImage img2 = imageReader.readImage(INPUT_DIR + IMAGE_2);

        List<Pixel> pixels2Blank = imageComparator.compare(img1, img2, DIFF_THRESHOLD);

        List<Section> sections2outline = pixelAreaCutter.defineAndCut(pixels2Blank);

        imageOutliner.outline(img2, sections2outline, INDENT_SIZE);
        imageSaver.saveImage(img2, OUTPUT_DIR + RESULT_IMAGE);
    }

    public static void main(String[] args) throws IOException {

        new MainClass().main();

    }
}
