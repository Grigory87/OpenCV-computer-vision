package computerVision.borders;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import utils.CvUtil;

/**
 * SearchByColor
 *
 * @author Grigory Dyakonov
 */

public class SearchByColor {
    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public static void main(String[] args) {
        Mat img = Imgcodecs.imread("image/model.jpg");
        CvUtil.show(img, "Исходное изображение");

        Mat hsv = new Mat(); // HSV - Hue, Saturation, Value(оттенок, насыщенность, значение)
        Imgproc.cvtColor(img, hsv, Imgproc.COLOR_BGR2HSV);
        CvUtil.show(hsv, "HSV");

        Mat h = new Mat();
        Core.extractChannel(hsv, h, 0);

        Mat img2 = new Mat();
        Core.inRange(h, new Scalar(40), new Scalar(80), img2);
        CvUtil.show(img2, "Зелёный фильтр");

        Core.inRange(h, new Scalar(100), new Scalar(140), img2);
        CvUtil.show(img2, "Синий фильтр");

        Core.inRange(h, new Scalar(0, 200, 200), new Scalar(20, 256, 256), img2);
        CvUtil.show(img2, "Красный фильтр");

        Core.inRange(h, new Scalar(0, 0, 0), new Scalar(0, 0, 50), img2);
        CvUtil.show(img2, "Черный фильтр");

        img.release();
        hsv.release();
        h.release();
        img2.release();
    }
}
