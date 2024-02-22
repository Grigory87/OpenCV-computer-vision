package computerVision.searchAngles;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import utils.CvUtil;

/**
 * LocationCornersWithSubpixelAccuracy
 *
 * @author Grigory Dyakonov
 */

public class LocationCornersWithSubpixelAccuracy {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        Mat img = Imgcodecs.imread("image/model.jpg");
        Imgproc.cvtColor(img, img, Imgproc.COLOR_BGR2GRAY);
        CvUtil.show(img, "Исходное изображение");

        MatOfPoint corners = new MatOfPoint();
        Imgproc.goodFeaturesToTrack(
                img,
                corners,
                100,
                0.01,
                15,
                new Mat(),
                3,
                false,
                0.04
        );
        Mat imgCopy = img.clone();
        double[] v = null;
        for (int i = 0, r = corners.rows(); i < r; i++) {
            for (int j = 0, c = corners.cols(); j < c; j++) {
                v = corners.get(i, j);
                Imgproc.circle(imgCopy, new Point(v[0], v[1]), 2,
                        new Scalar(255), Core.FILLED);
            }
        }
        CvUtil.show(imgCopy, "Результат goodFeaturesToTrack");

        // Уточнение положения углов
        MatOfPoint2f corners2 = new MatOfPoint2f(corners.toArray());
        TermCriteria criteria = new TermCriteria(
                TermCriteria.MAX_ITER + TermCriteria.EPS, 100, 0.01);
        Imgproc.cornerSubPix(img, corners2, new Size(5, 5),
                new Size(-1, -1), criteria);
        for (int i = 0, r = corners2.rows(); i < r; i++) {
            for (int j = 0, c = corners2.cols(); j < c; j++) {
                v = corners2.get(i, j);
                Imgproc.circle(img, new Point(v[0], v[1]), 2,
                        new Scalar(255), Core.FILLED);
            }
        }
        CvUtil.show(img, "Результат cornerSubPix");

        img.release();
        imgCopy.release();
    }
}
