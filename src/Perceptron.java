import java.util.Random;

public class Perceptron {
    double[] weights;
    double learningRate = 0.001;
    int bias = 1;
    double biasWeight;
    Random random = new Random();
    Perceptron(int numberOfNodes) {
        // Initialize weights to a random value between -1 and 1
        this.weights = new double[numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++) {
            this.weights[i] = randomDouble(-1, 1);
        }
        this.biasWeight = randomDouble(-1, 1);
    }

    int guess(double[] inputs) {
        double weightedSum = 0;
        for (int i = 0; i < inputs.length; i++) {
            weightedSum += this.weights[i] * inputs[i];
        }
        weightedSum += this.bias * this.biasWeight;
        int output = this.activation(weightedSum);
        return output;
    }

    double randomDouble(double min, double max) {
        double random = this.random.nextDouble();
        double result = min + (random * (max - min));
        return result;
    }

    // Activation function
    int activation(double weightedSum) {
        if (weightedSum >= 0) return 1;
        return -1;
    }

    // Calculates Error
    int getError(int answer, int output) {
        return answer - output;
    }

    //Adjust Weights
    void learn(Point point) {
        double[] input = {point.x, point.y};
        int answer = point.label;
        int output = this.guess(input);
        int error = this.getError(answer, output);
        for (int i = 0; i < this.weights.length; i++) {
            double deltaWeight = error * input[i] * learningRate;
            this.weights[i] += deltaWeight;
        }
        this.biasWeight += error * learningRate;
    }

    double guessY(double x) {
        return (biasWeight - (weights[0] * x))/weights[1];
    }

    double assumedSlope() {
        return -1 * (weights[0]/weights[1]);
    }

    double assumedB() {
        return this.biasWeight / weights[1];
    }

}