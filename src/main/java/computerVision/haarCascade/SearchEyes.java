package computerVision.haarCascade;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import utils.CvUtil;

/**
 * SearchEyes
 *
 * @author Grigory Dyakonov
 */

public class SearchEyes {
    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public static void main(String[] args) {
        Mat img = Imgcodecs.imread("image/i2.jpg");

        CascadeClassifier faceCascade = new CascadeClassifier("data/haarcascade_frontalface_alt.xml");
        if (faceCascade.empty()) {
            System.out.println("Unable to load cascade classifier");
            System.exit(0);
        }

        CascadeClassifier eyesCascade = new CascadeClassifier("data/haarcascade_eye.xml");
        if (eyesCascade.empty()) {
            System.out.println("Unable to load cascade classifier");
            System.exit(0);
        }

        MatOfRect faces = new MatOfRect();
        faceCascade.detectMultiScale(img, faces);
        for (Rect rect : faces.toList()) {
            Mat face = img.submat(rect);
            MatOfRect eyes = new MatOfRect();
            eyesCascade.detectMultiScale(face, eyes);
            for (Rect eye : eyes.toList()) {
                Imgproc.rectangle(
                        face,
                        eye.tl(), // new Point(rect.x, rect.y)
                        eye.br(), // new Point(rect.x + rect.width, rect.y + rect.height)
                        new Scalar(0, 255, 0),
                        2
                );
            }
        }
        CvUtil.show(img, "Результат");

        img.release();
    }
}
