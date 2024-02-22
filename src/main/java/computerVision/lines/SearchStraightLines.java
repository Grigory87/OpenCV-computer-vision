package computerVision.lines;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import utils.CvUtil;

/**
 * SearchLines
 *
 * @author Grigory Dyakonov
 */

public class SearchStraightLines {
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
        Mat edges = new Mat();
        Imgproc.Canny(imgGray, edges, 80, 200);
        CvUtil.show(edges, "Canny");

        Mat lines = new Mat();
        Imgproc.HoughLinesP(edges, lines, 1, Math.toRadians(1.5), 20, 5, 0);
        Mat result = new Mat(img.size(), CvType.CV_8UC3, new Scalar(255, 255, 255));
        for (int i = 0, r = lines.rows(); i < r; i++) {
            for (int j = 0, c = lines.cols(); j < c; j++) {
                double[] line = lines.get(i, j);
                Imgproc.line(result, new Point(line[0], line[1]),
                        new Point(line[2], line[3]), new Scalar(0, 0, 0));
            }
        }
        CvUtil.show(result, "Результат");

        img.release();
        imgGray.release();
        edges.release();
        result.release();
    }
}
