package computerVision.borders;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import utils.CvUtil;

import java.util.ArrayList;

/**
 * WorkWithContour
 *
 * @author Grigory Dyakonov
 */

public class WorkWithContours {
    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public static void main(String[] args) {
        Mat img = Imgcodecs.imread("image/model.jpg");
        Mat imgGray = new Mat();
        Imgproc.cvtColor(img, imgGray, Imgproc.COLOR_BGR2GRAY);
        Mat edges = new Mat();
        Imgproc.Canny(imgGray, edges, 80, 200);
        Mat edgesCopy = edges.clone();

        ArrayList<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(edgesCopy, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

        for(int i = 0; i < contours.size(); i++) {
            System.out.println(Imgproc.contourArea(contours.get(i)));
            Rect rect = Imgproc.boundingRect(contours.get(i));
            System.out.println("boundingRect: " + rect);
            double len = Imgproc.arcLength(new MatOfPoint2f(contours.get(i).toArray()), true);
            System.out.println("arcLength: " + len);
            Imgproc.rectangle(
                    img,
                    new Point(rect.x, rect.y),
                    new Point(rect.x + rect.width - 1, rect.y + rect.height - 1),
                    new Scalar(0, 255, 0)
            );
        }
        CvUtil.show(img, "Результат");

        img.release();
        edges.release();
        edgesCopy.release();
        hierarchy.release();
    }
}
