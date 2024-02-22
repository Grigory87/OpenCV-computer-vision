package computerVision.borders;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import utils.CvUtil;

/**
 * GrubCut
 *
 * @author Grigory Dyakonov
 */

public class GrubCut {
    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public static void main(String[] args) {
        Mat img = Imgcodecs.imread("image/modelGradient.jpg");
        CvUtil.show(img, "Исходное изображение");

        Mat bgdModel = new Mat();
        Mat fgdModel = new Mat();

        // Инициализация с помощью прямоугольной области
        Mat mask = new Mat();
        Rect rect = new Rect(1, 1, img.width() - 2, img.height() - 2);
        Imgproc.grabCut(img, mask, rect, bgdModel, fgdModel, 1, Imgproc.GC_INIT_WITH_RECT);


         // Инициализация с помощью маски
//         Mat mask = new Mat(img.size(), CvType.CV_8UC1, new Scalar(Imgproc.GC_BGD));
//         Imgproc.rectangle(
//                  mask,
//                  new Point(1, 1),
//                  new Point(img.width() - 2, img.height() - 2),
//                  new Scalar(Imgproc.GC_PR_FGD), Core.FILLED
//         );
//         Imgproc.grabCut(img, mask, new Rect(), bgdModel, fgdModel, 1, Imgproc.GC_INIT_WITH_MASK);

        // Повторный вызов
        Imgproc.grabCut(img, mask, new Rect(), bgdModel, fgdModel, 1, Imgproc.GC_EVAL);

        Mat maskPR_FGD = new Mat();
        Core.compare(mask, new Scalar(Imgproc.GC_PR_FGD), maskPR_FGD, Core.CMP_EQ);
        Mat resultPR_FGD = new Mat(img.rows(), img.cols(), CvType.CV_8UC3, new Scalar(255, 255, 255));
        img.copyTo(resultPR_FGD, maskPR_FGD);
        CvUtil.show(resultPR_FGD, "Результат GC_PR_FGD");

        Mat maskPR_BGD = new Mat();
        Core.compare(mask, new Scalar(Imgproc.GC_PR_BGD), maskPR_BGD, Core.CMP_EQ);
        Mat resultPR_BGD = new Mat(img.rows(), img.cols(), CvType.CV_8UC3, new Scalar(255, 255, 255));
        img.copyTo(resultPR_BGD, maskPR_BGD);
        CvUtil.show(resultPR_BGD, "Результат GC_PR_BGD");

        img.release();
        mask.release();
        maskPR_FGD.release();
        maskPR_BGD.release();
        bgdModel.release();
        fgdModel.release();
    }
}
