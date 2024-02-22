package computerVision.searchAngles;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import utils.CvUtil;

/**
 * DetectCorners
 *
 * @author Grigory Dyakonov
 */

public class HarrisDetectCorners {
    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public static void main(String[] args) {
        Mat img = Imgcodecs.imread("image/model.jpg");
        Imgproc.cvtColor(img, img, Imgproc.COLOR_BGR2GRAY);
        CvUtil.show(img, "Исходное изображение");

        Mat dst = new Mat();
        Imgproc.cornerHarris(img, dst, 2, 3, 0.004, Core.BORDER_DEFAULT);

        Core.MinMaxLocResult m = Core.minMaxLoc(dst);
        Imgproc.threshold(dst, dst, m.maxVal * 0.001, 255, Imgproc.THRESH_BINARY);
        CvUtil.show(dst, "Результат");

        img.release();
        dst.release();
    }
}
