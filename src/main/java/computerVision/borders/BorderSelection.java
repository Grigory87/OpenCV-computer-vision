package computerVision.borders;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import utils.CvUtil;

/**
 * BorderSelection
 *
 * @author Grigory Dyakonov
 */

public class BorderSelection {
    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public static final String PATH = "image/sova.jpg";

    public static void main(String[] args) {
        Mat img = Imgcodecs.imread(PATH);
        Mat imgGray = new Mat();
        Imgproc.cvtColor(img, imgGray, Imgproc.COLOR_BGR2GRAY);
        CvUtil.show(imgGray, "Оригинал");

        Mat edges = new Mat();
        Imgproc.Canny(imgGray, edges, 80, 200);
        CvUtil.show(edges, "1 вариант");

        Mat img3 = new Mat();
        Imgproc.threshold(imgGray, img3, 100, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
        Mat edges2 = new Mat();
        Imgproc.Canny(img3, edges2, 80, 200);
        CvUtil.show(edges2, "2 вариант");

        Mat img4 = new Mat();
        Imgproc.adaptiveThreshold(imgGray,
                img4,
                255,
                Imgproc.ADAPTIVE_THRESH_MEAN_C,
                Imgproc.THRESH_BINARY, 3, 5
        );
        Mat edges3 = new Mat();
        Imgproc.Canny(img4, edges3, 80, 200);
        CvUtil.show(edges3, "3 вариант");

        img.release();
        imgGray.release();
        edges.release();
        img3.release();
        edges2.release();
        img4.release();
        edges3.release();
    }
}
