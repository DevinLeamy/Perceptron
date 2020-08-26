import java.util.Random;
import java.awt.*;

public class Point {
    double x;
    double y;
    int xLow = -10;
    int xHigh = 10;
    int yLow = -10;
    int yHigh = 10;
    int label;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    Point() {
        this.x = this.randomDouble(this.xLow, this.xHigh);
        this.y = this.randomDouble(this.yLow, this.yHigh);
        if (y >= this.f(x)) this.label = 1;
        else this.label = -1;
    }

    double f(double x) {
        return 7*x;
    }

    double map(double val, double curLow, double curHigh, double endLow, double endHigh) {
        double currentRange = Math.abs(curHigh - curLow);
        double newRange = Math.abs(endHigh - endLow);
        double ratio = (val - curLow)/currentRange;
        double newVal = ratio * newRange;
        return newVal + endLow;
    }

    double pointX() {
        return map(this.x, this.xLow, this.xHigh, 0, 800);
    }

    double pointY() {
        return map(this.y, this.yHigh, this.yLow, 0, 800) * -1;
    }

    double randomDouble(double min, double max) {
        double random = new Random().nextDouble();
        double result = min + (random * (max - min));
        return result;
    }

    void show(Graphics graphics) {
        if (this.label == 1) {
            graphics.setColor(new Color(0, 0, 0));
        } else {
            graphics.setColor(new Color(255, 255, 255));
        }
//        System.out.println(this.x + " " + this.y + " " + this.pointX() + " " + this.pointY());
        graphics.fillOval((int) this.pointX(), (int) this.pointY(), 16, 16);
    }

    void showResult(Graphics graphics, boolean correct) {
        if (correct) {
            graphics.setColor(new Color(0, 255, 0));
        } else {
            graphics.setColor(new Color(255, 0, 0));
        }
        graphics.fillOval((int) this.pointX() + 4, (int) this.pointY() + 4, 8, 8);
    }
}
