package computerVision.lines;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.LineSegmentDetector;
import utils.CvUtil;

/**
 * SearchStraightLines2
 *
 * @author Grigory Dyakonov
 */

public class SearchStraightLines2 {
    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public static void main(String[] args) {
        Mat img = new Mat(150, 300, CvType.CV_8UC3, new Scalar(255, 255, 255));
        Imgproc.rectangle(img, new Point(20, 20), new Point(120, 70),
                new Scalar(0, 255, 0), Core.FILLED);
        Imgproc.line(img, new Point(20, 100), new Point(120, 100),
                new Scalar(255, 0, 0), 1);
        Imgproc.line(img, new Point(20, 120), new Point(120, 120),
                new Scalar(255, 0, 0), 3);
        Imgproc.line(img, new Point(150, 20), new Point(150, 100),
                new Scalar(255, 0, 0), 1);
        Imgproc.line(img, new Point(170, 20), new Point(170, 100),
                new Scalar(255, 0, 0), 3);
        Imgproc.line(img, new Point(200, 20), new Point(280, 100),
                new Scalar(255, 0, 0), 3);
        Imgproc.line(img, new Point(280, 20), new Point(200, 100),
                new Scalar(255, 0, 0), 3);
        Mat imgGray = new Mat();
        Imgproc.cvtColor(img, imgGray, Imgproc.COLOR_BGR2GRAY);
        CvUtil.show(imgGray, "Оригинал");

        Mat lines = new Mat();
//        Mat width = new Mat();
//        Mat prec = new Mat();
        Mat result = new Mat(img.size(), CvType.CV_8UC3, new Scalar(255, 255, 255));
        LineSegmentDetector detector = Imgproc.createLineSegmentDetector();
        detector.detect(imgGray, lines);
        detector.drawSegments(result, lines);
        CvUtil.show(result, "Результат");

        img.release();
        imgGray.release();
        lines.release();
        result.release();
    }
}
