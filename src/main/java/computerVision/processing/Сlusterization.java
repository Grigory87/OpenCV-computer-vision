package computerVision.processing;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import utils.CvUtil;

/**
 * Clustering
 *
 * @author Grigory Dyakonov
 */

public class Сlusterization {
    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public static void main(String[] args) {
        Mat img = Imgcodecs.imread("image/sova.jpg");
        Imgproc.cvtColor(img, img, Imgproc.COLOR_BGR2GRAY);
        CvUtil.show(img, "Исходное изображение");

        Mat data = img.reshape(1, img.rows() * img.cols() * img.channels());
        data.convertTo(data, CvType.CV_32F, 1.0 / 255.0);
        Mat bestLabels = new Mat();
        Mat centers = new Mat();
        TermCriteria criteria = new TermCriteria(TermCriteria.MAX_ITER + TermCriteria.EPS, 10, 1);
        int k = 3;
        Core.kmeans(data, k, bestLabels, criteria, 5, Core.KMEANS_PP_CENTERS, centers);

        Mat colors = new Mat();
        centers.t().convertTo(colors, CvType.CV_8UC1, 255);

        Mat lut = new Mat(1, 256, CvType.CV_8UC1, new Scalar(0));
        colors.copyTo(new Mat(lut, new Range(0, 1), new Range(0, colors.cols())));

        Mat result = bestLabels.reshape(img.channels(), img.rows());
        result.convertTo(result, CvType.CV_8U);
        Core.LUT(result, lut, result);
        CvUtil.show(result, "Результат");

        img.release();
        data.release();
        bestLabels.release();
        centers.release();
        colors.release();
        lut.release();
        result.release();
    }
}
