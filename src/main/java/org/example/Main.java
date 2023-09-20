package org.example;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Main {
    private static double prevValue = Double.MAX_VALUE;
    private static double middleValue = Double.MAX_VALUE;
    public static void gold (double a, double b){

    }

    public static void main(String[] args) {
        XYSeries series = new XYSeries("f(x)");
        GoldenSection GS = new GoldenSection();

        for(float i = -10; i < 10; i+=0.01){
            double f = Math.pow(i,3)+(2/(Math.sin(i)+Math.pow(i,2)));
            series.add(i, f);
            if (f >middleValue && middleValue<prevValue && prevValue!=Double.MAX_VALUE && Math.abs(f-middleValue)<1){
                System.out.println(GS.findMin(i-0.02, i, 0.001) + " Min");
                //System.out.println(i + " Минимум");
            }
            if (f < middleValue && middleValue > prevValue && prevValue!=Double.MAX_VALUE&& Math.abs(f-middleValue)<1){
                System.out.println(GS.findMax(i-0.02,i, 0.001) + " Max");
                //System.out.println(i + " Максимум");
            }
            prevValue = middleValue;
            middleValue = f;
        }

       /* System.out.println(GS.findMin(0, 10, 0.001) + " Min");
        System.out.println(GS.findMax(-1, 0, 0.001) + " Max");*/


        XYDataset xyDataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory
                .createXYLineChart("y = f(x)", "x", "y",
                        xyDataset,
                        PlotOrientation.VERTICAL,
                        true, true, true);
        JFrame frame =
                new JFrame("MinimalStaticChart");
        // Помещаем график на фрейм
        frame.getContentPane()
                .add(new ChartPanel(chart));
        frame.setSize(400,300);
        frame.show();
    }
}