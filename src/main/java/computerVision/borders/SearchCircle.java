package computerVision.borders;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import utils.CvUtil;

/**
 * SearchCircle
 *
 * @author Grigory Dyakonov
 */

public class SearchCircle {
    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public static void main(String[] args) {
        Mat img = Imgcodecs.imread("image/model.jpg");
        Mat matGray = new Mat();
        Imgproc.cvtColor(img, matGray, Imgproc.COLOR_BGR2GRAY);
        CvUtil.show(matGray, "Исходное изображение");

        Mat circle = new Mat();
        Imgproc.HoughCircles(
                matGray,
                circle,
                Imgproc.HOUGH_GRADIENT,
                2,
                matGray.rows()/2
        );

        Mat result = new Mat(img.size(), CvType.CV_8UC3, new Scalar(255, 255, 255));
        for (int i = 0, r = circle.rows(); i < r; i++) {
            for (int j = 0, c = circle.cols(); j < c; j++) {
                double[] center = circle.get(i, j);
                Imgproc.circle(result, new Point(center[0], center[1]), (int) center[2], new Scalar(0, 0, 0));
            }
        }
        CvUtil.show(result, "Результат");

        img.release();
        matGray.release();
        circle.release();
        result.release();
    }
}
