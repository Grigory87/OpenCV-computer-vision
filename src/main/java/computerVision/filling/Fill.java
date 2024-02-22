package computerVision.filling;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import utils.CvUtil;

/**
 * Fill
 *
 * @author Grigory Dyakonov
 */

public class Fill {
    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public static void main(String[] args) {
        Mat img = Imgcodecs.imread("image/model2.jpg");
        CvUtil.show(img, "Исходное изображение");

        Imgproc.floodFill(img, new Mat(), new Point(1, 1), new Scalar(255, 255, 255));
        CvUtil.show(img, "Результат");

        img.release();
    }
}
