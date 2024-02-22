package computerVision.haarCascade;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import utils.CvUtil;

/**
 * SearchSmile
 *
 * @author Grigory Dyakonov
 */

public class SearchSmile {
    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public static void main(String[] args) {
        Mat img = Imgcodecs.imread("image/i.jpg");

        CascadeClassifier faceCascade = new CascadeClassifier("data/haarcascade_frontalface_alt.xml");
        if (faceCascade.empty()) {
            System.out.println("Unable to load cascade classifier");
            System.exit(0);
        }

        CascadeClassifier smileCascade = new CascadeClassifier("data/haarcascade_smile.xml");
        if (smileCascade.empty()) {
            System.out.println("Unable to load cascade classifier");
            System.exit(0);
        }

        MatOfRect faces = new MatOfRect();
        faceCascade.detectMultiScale(img, faces);
        for (Rect rect : faces.toList()) {
            Mat face = img.submat(rect);
            MatOfRect smiles = new MatOfRect();
            smileCascade.detectMultiScale(face, smiles);
            for (Rect smile : smiles.toList()) {
                Imgproc.rectangle(
                        face,
                        smile.tl(), // new Point(rect.x, rect.y)
                        smile.br(), // new Point(rect.x + rect.width, rect.y + rect.height)
                        new Scalar(0, 255, 0),
                        2
                );
            }
        }
        CvUtil.show(img, "Результат");

        img.release();
    }
}
