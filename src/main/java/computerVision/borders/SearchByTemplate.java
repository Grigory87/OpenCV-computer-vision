package computerVision.borders;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import utils.CvUtil;

/**
 * SearchByTemplate
 *
 * @author Grigory Dyakonov
 */

public class SearchByTemplate {
    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public static void main(String[] args) {
        Mat img = Imgcodecs.imread("image/model.jpg");
        CvUtil.show(img, "Исходное изображение");
        Mat matGray = new Mat();
        Imgproc.cvtColor(img, matGray, Imgproc.COLOR_BGR2GRAY);

        Mat template = matGray.submat(new Rect(82, 85, 90, 90).clone());
        CvUtil.show(template, "Шаблон");

        Mat result = new Mat();
        Imgproc.matchTemplate(matGray, template, result, Imgproc.TM_SQDIFF);
        Core.MinMaxLocResult r = Core.minMaxLoc(result);
        System.out.println(r.minVal + " " + r.minLoc);
        Imgproc.rectangle(img, r.minLoc, new Point(r.minLoc.x + template.width() - 1,
                r.minLoc.y + template.height() - 1), new Scalar(256, 256, 256));
        CvUtil.show(img, "Результат TM_SQDIFF");

        img.release();
        matGray.release();
        template.release();
        result.release();
    }
}
