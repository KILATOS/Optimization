package util;

public class GoldenSection{

    final static double PHI = (1 + Math.sqrt(5)) / 2;
    public static int minOperationsCounter = 0;
    public static int maxOperationsCounter = 0;



    public static double findMin(double a, double b, double e){
        Function f = new Function(a,b);
        minOperationsCounter = 0;
        double x1, x2;
        while (true){
            x1 = b - (b - a) / PHI;
            x2 = a + (b - a) / PHI;
            if (f.getFunction(x1) >= f.getFunction(x2))
                a = x1;
            else
                b = x2;
            minOperationsCounter++;
            if (Math.abs(b - a) < e)
                break;
        }
        return (a + b) / 2;
    }

    public static double findMax(double a, double b, double e){
        Function f = new Function(a,b);
        maxOperationsCounter = 0;
        double x1, x2;
        while (true){
            x1 = b - (b - a) / PHI;
            x2 = a + (b - a) / PHI;
            if (f.getFunction(x1) <= f.getFunction(x2))
                a = x1;
            else
                b = x2;
            maxOperationsCounter++;
            if (Math.abs(b - a) < e)
                break;
        }
        return (a + b) / 2;
    }

}