public class MultiPerceptron {
    int m, n;
    Perceptron[] perceptrons;

    // Creates a multi-perceptron object with m classes and n inputs.
    public MultiPerceptron(int m, int n) {
        this.m = m;
        this.n = n;
        this.perceptrons = new Perceptron[m];
        for (int i = 0; i < m; i++) {
            perceptrons[i] = new Perceptron(n);
        }
    }

    // Returns the number of classes m.
    public int numberOfClasses() {
        return m;
    }

    // Returns the number of inputs n (length of the feature vector).
    public int numberOfInputs() {
        return n;
    }

    // Returns the predicted label (between 0 and m-1) for the given input.
    public int predictMulti(double[] x) {
        double[] confidencies = new double[m];
        double max = 0.0;
        int maxindex = 0;
        for (int i = 0; i < m; i++) {
            confidencies[i] = perceptrons[i].weightedSum(x);
            if (confidencies[i] > max) {
                max = confidencies[i];
                maxindex = i;
            }
        }
        return maxindex;
    }

    // Trains this multi-perceptron on the labeled (between 0 and m-1) input.
    public void trainMulti(double[] x, int label) {
        for (int i = 0; i < m; i++) {
            if (i == label) {
                perceptrons[i].train(x, +1);
            }
            else {
                perceptrons[i].train(x, -1);
            }
        }
    }

    // Returns a string representation of this MultiPerceptron.
    public String toString() {
        String answer = "(";
        for (int i = 0; i < n - 1; i++) {
            answer += perceptrons[i].toString();
            if (i % 2 == 0) answer += ", ";
        }
        answer += ")";
        return answer;
    }

    // Tests this class by directly calling all instance methods.
    public static void main(String[] args) {
        int m = 2;
        int n = 3;

        double[] training1 = { 5.0, -4.0, 3.0 };
        double[] training2 = { 2.0, 3.0, -2.0 };
        double[] training3 = { 4.0, 3.0, 2.0 };
        double[] training4 = { -6.0, -5.0, 7.0 };

        MultiPerceptron perceptron = new MultiPerceptron(m, n);
        StdOut.println(perceptron);
        perceptron.trainMulti(training1, 1);
        StdOut.println(perceptron);
        perceptron.trainMulti(training2, 0);
        StdOut.println(perceptron);
        perceptron.trainMulti(training3, 1);
        StdOut.println(perceptron);
        perceptron.trainMulti(training4, 0);
        StdOut.println(perceptron);

    }

}
