package computerVision.filling;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import utils.CvUtil;

/**
 * FillGradient
 *
 * @author Grigory Dyakonov
 */

public class FillGradient {
    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public static void main(String[] args) {
        Mat img = Imgcodecs.imread("image/gradient.jpg");
        Imgproc.cvtColor(img, img, Imgproc.COLOR_BGR2GRAY);
        CvUtil.show(img, "Исходное изображение");

        Mat mask = new Mat(img.rows() + 2, img.cols() + 2, CvType.CV_8UC1, new Scalar(0));
        Rect rect = new Rect();
        Imgproc.floodFill(img,
                mask,
                new Point(1, 1),
                new Scalar(255),
                rect,
                new Scalar(20),
                new Scalar(40),
                4 | (255 << 8)
        );
        CvUtil.show(mask, "Маска");
        CvUtil.show(img, "Результат");

        img.release();
        mask.release();
    }
}
