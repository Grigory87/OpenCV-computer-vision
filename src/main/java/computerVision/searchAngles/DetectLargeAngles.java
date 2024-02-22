package computerVision.searchAngles;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import utils.CvUtil;

/**
 * DetectLargeAngles
 *
 * @author Grigory Dyakonov
 */

public class DetectLargeAngles {
    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public static void main(String[] args) {
        Mat img = Imgcodecs.imread("image/model.jpg");
        Imgproc.cvtColor(img, img, Imgproc.COLOR_BGR2GRAY);
        CvUtil.show(img, "Исходное изображение");

        MatOfPoint corners = new MatOfPoint();
        Imgproc.goodFeaturesToTrack(img, corners, 100, 0.01, 15);
        double[] v;
        for (int i = 0, r = corners.rows(); i < r; i++) {
            for (int j = 0, c = corners.cols(); j < c; j++) {
                v = corners.get(i, j);
                Imgproc.circle(img,
                        new Point(v[0], v[1]),
                        2,
                        new Scalar(255),
                        Core.FILLED
                );
            }
        }
        CvUtil.show(img, "Результат");

        img.release();
        corners.release();
    }
}
