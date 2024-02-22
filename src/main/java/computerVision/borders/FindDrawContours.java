package computerVision.borders;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import utils.CvUtil;

import java.util.ArrayList;

/**
 * computerVision.borders.FindDrawContours
 *
 * @author Grigory Dyakonov
 */

public class FindDrawContours {
    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public static void main(String[] args) {
        Mat img = Imgcodecs.imread("image/sova.jpg");
        Mat imgGray = new Mat();
        Imgproc.cvtColor(img, imgGray, Imgproc.COLOR_BGR2GRAY);
        Mat edges = new Mat();
        Imgproc.Canny(imgGray, edges, 80, 200);
        Mat edgesCopy = edges.clone();

        ArrayList<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(edgesCopy, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

        Imgproc.drawContours(img, contours, -1, new Scalar(255, 0, 0));
        CvUtil.show(img, "Результат");

        img.release();
        hierarchy.release();
    }
}
