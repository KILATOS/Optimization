package org.example;

class GoldenSection{

    final double PHI = (1 + Math.sqrt(5)) / 2;

    double f(double i){
        return Math.pow(i,3)+(2/(Math.sin(i)+Math.pow(i,2)));
    }

    double findMin(double a, double b, double e){
        double x1, x2;
        while (true){
            x1 = b - (b - a) / PHI;
            x2 = a + (b - a) / PHI;
            if (f(x1) >= f(x2))
                a = x1;
            else
                b = x2;
            if (Math.abs(b - a) < e)
                break;
        }
        return (a + b) / 2;
    }

    double findMax(double a, double b, double e){
        double x1, x2;
        while (true){
            x1 = b - (b - a) / PHI;
            x2 = a + (b - a) / PHI;
            if (f(x1) <= f(x2))
                a = x1;
            else
                b = x2;
            if (Math.abs(b - a) < e)
                break;
        }
        return (a + b) / 2;
    }

}