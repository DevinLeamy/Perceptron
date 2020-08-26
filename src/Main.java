import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main extends Canvas {
    public double f(double x) {
        return 7*x;
    }

    static ArrayList<Point> trainingData = new ArrayList<>();
    static Perceptron perceptron;
    public static void main(String[] args) {
        int numberOfPoints = 40000;
        perceptron = new Perceptron(2);
        JFrame frame = new JFrame("Point Display");
        Canvas canvas = new Main();
        canvas.setSize(800, 800);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
        for (int i = 0; i < numberOfPoints; i++) {
            trainingData.add(new Point());
        }
    }

    static double getSuccessRate(Graphics graphics) {
        int correct = 0;
        for (Point point : trainingData) {
            int guess = perceptron.guess(new double[] {point.x, point.y});
            if (guess == point.label) {
                correct++;
                point.showResult(graphics, true);
            } else {
                point.showResult(graphics, false);
            }
        }
        return (double)correct/(double)trainingData.size();
    }

    static void train() {
        for (Point point : trainingData) {
            perceptron.learn(point);
        }
    }

    public void paint(Graphics graphics) {
        for (Point point : trainingData) {
            point.show(graphics);
        }
        graphics.setColor(new Color(0, 0, 0));
        Point p1 = new Point(-10, f(-10));
        Point p2 = new Point(10, f(10));
        graphics.drawLine((int)p1.pointX(), (int)p1.pointY(), (int)p2.pointX(), (int)p2.pointY());
        for (int i = 0; i < 100; i++) {

            System.out.println("Success Rate: " + getSuccessRate(graphics));
            System.out.println("y = " + perceptron.assumedSlope() + "x + " + perceptron.assumedB());
            train();
//            drawAssumedLine(graphics);
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
//                graphics.clearRect(0, 0, 800, 800);
            } catch (Exception e) {e.printStackTrace();}
        }
    }

    void drawAssumedLine(Graphics graphics) {
        Point p1 = new Point(-400, perceptron.guessY(-400));
        Point p2 = new Point(400, perceptron.guessY(400));
        graphics.setColor(new Color(0, 0, 0));
        graphics.drawLine((int)p1.pointX(), (int)p1.pointY(), (int)p2.pointX(), (int)p2.pointY());
    }
}
