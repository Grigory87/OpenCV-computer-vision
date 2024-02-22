package computerVision.searchKeyPoints;

import org.opencv.core.*;
import org.opencv.features2d.Features2d;
import utils.CvUtil;

/**
 * DrawKeypoints
 *
 * @author Grigory Dyakonov
 */

public class DrawKeypoints {
    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public static void main(String[] args) {
        Mat m = new Mat(50, 500, CvType.CV_8UC3, new Scalar(255, 255, 255));
        MatOfKeyPoint kp = new MatOfKeyPoint(
                new KeyPoint(30, 30, 30, 90),
                new KeyPoint(120, 20, 20, 10),
                new KeyPoint(80, 30, 15, 45)
        );
        Mat m2 = new Mat();
        Features2d.drawKeypoints(m, kp, m2);
        CvUtil.show(m2, "Без флага");

        Mat m3 = new Mat(50, 500, CvType.CV_8UC3, new Scalar(90, 90, 90));
        Features2d.drawKeypoints(m, kp, m3, new Scalar(255, 0, 0),
                Features2d.DrawMatchesFlags_DRAW_OVER_OUTIMG);
        CvUtil.show(m3, "DRAW_OVER_OUTIMG");

        Mat m4 = m.clone();
        Features2d.drawKeypoints(m, kp, m4, Scalar.all(-1),
                Features2d.DrawMatchesFlags_DRAW_RICH_KEYPOINTS);
        CvUtil.show(m4, "DRAW_RICH_KEYPOINTS");

        m.release();
        m2.release();
        m3.release();
        m4.release();
    }
}
