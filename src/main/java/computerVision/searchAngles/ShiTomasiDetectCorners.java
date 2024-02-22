package computerVision.searchAngles;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import utils.CvUtil;

import java.util.Arrays;

/**
 * ShiTomasiDetectCorners
 *
 * @author Grigory Dyakonov
 */

public class ShiTomasiDetectCorners {
    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public static void main(String[] args) {
        Mat img = Imgcodecs.imread("image/model.jpg");
        Imgproc.cvtColor(img, img, Imgproc.COLOR_BGR2GRAY);
        CvUtil.show(img, "Исходное изображение");

        Mat dst = new Mat();
        Imgproc.cornerMinEigenVal(img, dst, 2, 3);
        Mat dst2 = new Mat();
        Imgproc.cornerEigenValsAndVecs(img, dst2, 2, 3, Core.BORDER_DEFAULT);
        Core.MinMaxLocResult m = Core.minMaxLoc(dst);

        System.out.println(dst2.channels()); // 6
        double[] maxDst = dst.get((int) m.maxLoc.y, (int) m.maxLoc.x);
        System.out.println(Arrays.toString(maxDst));
        double[] maxDst2 = dst2.get((int) m.maxLoc.y, (int) m.maxLoc.x);
        System.out.println(Arrays.toString(maxDst2));

        Imgproc.threshold(dst, dst, m.maxVal * 0.01, 1.0, Imgproc.THRESH_BINARY);
        dst.convertTo(dst, CvType.CV_8U, 255);
        CvUtil.show(dst, "Результат");

        img.release();
        dst.release();
        dst2.release();
    }
}
