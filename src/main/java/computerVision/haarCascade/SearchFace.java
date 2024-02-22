package computerVision.haarCascade;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import utils.CvUtil;

/**
 * SearchFace
 *
 * @author Grigory Dyakonov
 */

public class SearchFace {
    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public static void main(String[] args) {
        Mat img = Imgcodecs.imread("image/i.jpg");
        CascadeClassifier faceCascade = new CascadeClassifier("data/haarcascade_frontalface_alt.xml");
        if(faceCascade.empty()) {
            System.out.println("Unable to load cascade classifier");
            System.exit(0);
        }

        MatOfRect faces = new MatOfRect();
        faceCascade.detectMultiScale(img, faces);
        for(Rect rect : faces.toList()) {
            Imgproc.rectangle(
                    img,
                    rect.tl(), // new Point(rect.x, rect.y)
                    rect.br(), // new Point(rect.x + rect.width, rect.y + rect.height)
                    new Scalar(0, 255, 0),
                    2
            );
        }
        CvUtil.show(img, "Результат");

        img.release();
    }
}
