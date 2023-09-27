package util;

public class Function {
    private double a;
    private double b;

    public Function(double a, double b) {
        if (a > b) {
            double buff = b;
            b = a;
            a = buff;
        }

        this.a = a;
        this.b = b;
    }

    public double getFunction(double i) {
        return Math.pow(i,3)+(2/(Math.sin(i)+Math.pow(i,2)));
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }
}