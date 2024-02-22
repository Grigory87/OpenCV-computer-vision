package computerVision.processing;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.TermCriteria;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import utils.CvUtil;

/**
 * GroupArea
 *
 * @author Grigory Dyakonov
 */

public class GroupArea {
    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public static void main(String[] args) {
        Mat img = Imgcodecs.imread("image/sova.jpg");
        CvUtil.show(img, "Исходное изображение");

        Mat result = new Mat();
        Imgproc.pyrMeanShiftFiltering(img,
                result,
                20,
                50,
                1,
                new TermCriteria(TermCriteria.MAX_ITER + TermCriteria.EPS, 5, 1)
        );
        CvUtil.show(result, "Результат");

        img.release();
        result.release();
    }
}
