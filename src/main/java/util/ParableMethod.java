package util;

public class ParableMethod {



    public static double parabolicMethod(double a_started,double b_started , double e){
        Function f = new Function(a_started,b_started);
        double a = f.getA();
        double b = f.getB();
        double del = 1;
        double x1 = a;
        double x2 = (b + a) / 2;
        double x3 = b;


        //double a0 = f.getFunction(x1);
        double a1 = (f.getFunction(x2) - f.getFunction(x1)) / (x2 - x1);
        double a2 = ( 1 / (x3 - x2)) * (((f.getFunction(x3) - f.getFunction(x1)) / (x3 - x1)) - ((f.getFunction(x2) - f.getFunction(x1) / (x2 - x1))));

        double x = 0.5 * (x1 + x2 - (a1 / a2));
        double result = x;
        if ((f.getFunction(x) >= f.getFunction(x2))){
                x1 = x;
        }
        if ((f.getFunction(x2)>=f.getFunction(x))){
            x1 = x2;
            x2 = x;
        }
        while (del > e){
            a1 = (f.getFunction(x2) - f.getFunction(x1)) / (x2 - x1);
            a2 = ( 1 / (x3 - x2)) * (((f.getFunction(x3) - f.getFunction(x1)) / (x3 - x1)) - ((f.getFunction(x2) - f.getFunction(x1) / (x2 - x1))));
            x = 0.5 * (x1 + x2 - (a1 / a2));

            if ((f.getFunction(x) >= f.getFunction(x2))&&(x > x1)){
                x1 = x;
            }
            if ((f.getFunction(x2)>=f.getFunction(x))&&(x < x3)){
                x1 = x2;
                x2 = x;
            }
            del = Math.abs(result - x);
            result = x;

        }
        return result;

    }


}
