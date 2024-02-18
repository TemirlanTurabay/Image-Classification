import java.awt.Color;

public class ImageClassifier {

    // function extractFeatures
    public static double[] extractFeatures(Picture picture) {
        int width = picture.width();
        int height = picture.height();

        int counter = 0;
        double[] arr = new double[width * height];

        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                Color color = picture.get(row, col);
                int currcolor = color.getRed();
                arr[counter++] = currcolor;
            }
        }

        return arr;
    }

    // function main
    public static void main(String[] args) {
        In trainFile = new In(args[0]);

        int trainM = trainFile.readInt();
        int trainW = trainFile.readInt();
        int trainH = trainFile.readInt();

        MultiPerceptron mp = new MultiPerceptron(trainM, trainW * trainH);

        while (!trainFile.isEmpty()) {
            String trainName = trainFile.readString();
            Picture trainPicture = new Picture(trainName);
            double[] trainArr = extractFeatures(trainPicture);
            int trainNumber = trainFile.readInt();
            mp.trainMulti(trainArr, trainNumber);
        }

        In testFile = new In(args[1]);

        // These are needed to read through the garbage values
        int testM = testFile.readInt();
        int testW = testFile.readInt();
        int testH = testFile.readInt();

        int errorsNum = 0;
        int totalNum = 0;

        while (!testFile.isEmpty()) {
            String testName = testFile.readString();
            Picture testPicture = new Picture(testName);
            double[] testArr = extractFeatures(testPicture);
            int predictedNum = mp.predictMulti(testArr);
            int testNumber = testFile.readInt();
            if (predictedNum != testNumber) {
                System.out.println(testName);
                errorsNum++;
            }
            totalNum++;
        }

        System.out.println("test error rate = " + errorsNum * 1.0 / totalNum);
    }

}
