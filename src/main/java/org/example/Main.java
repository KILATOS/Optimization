package org.example;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import util.Function;
import util.GoldenSection;
import util.ParableMethod;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    private static double prevValue = Double.MAX_VALUE;
    private static double middleValue = Double.MAX_VALUE;
    private static final double epsilon = 0.0001;
    private static final double step = 0.01;
    private static final float leftX = -10;
    private static final float rightX = 10;


    public static void main(String[] args) throws IOException, InterruptedException {
        XYSeries series = new XYSeries("f(x)");
        File file = new File("D:/newFile.txt");
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))){




        for (float i = leftX; i < rightX; i += step) {
            double f = Math.pow(i, 3) + (2 / (Math.sin(i) + Math.pow(i, 2)));
            series.add(i, f);
            if (f > middleValue && middleValue < prevValue && prevValue != Double.MAX_VALUE && Math.abs(f - middleValue) < 1) {
                bufferedWriter.write("//////НАХОДИМ МИНИМУМ ПРИ ПОМОЩИ МЕТОДА ЗОЛОТОГО СЕЧЕНИЯ//////\n");
                bufferedWriter.write(Double.valueOf(GoldenSection.findMin(i - step - step, i, epsilon)).toString() + "\n");
                bufferedWriter.write("Начало интервала: " + (i - step - step) + " Конец интервала: " + i + "\n");
                bufferedWriter.write("ТОЧНОСТЬ РАВНА = " + epsilon +"\n");
                bufferedWriter.write("Количество итераций: " + GoldenSection.minOperationsCounter +"\n");
                bufferedWriter.write("//////////////////////////////////////////////////////////////\n");
                bufferedWriter.write("\n");

                bufferedWriter.write("//////НАХОДИМ МИНИМУМ МЕТОДОМ КВАДРАТИЧНОЙ ИНТЕРПОЛЯЦИИ///////\n");
                bufferedWriter.write(Double.valueOf(ParableMethod.parabolicMethod(i - step - step, i, epsilon)).toString() + "\n");
                bufferedWriter.write("Начало интервала: " + (i - step - step) + " Конец интервала: " + i  +"\n");
                bufferedWriter.write("ТОЧНОСТЬ РАВНА = " + epsilon + "\n");
                bufferedWriter.write("Количество итераций: " + ParableMethod.minOperationsCounter + "\n");
                bufferedWriter.write("//////////////////////////////////////////////////////////////\n");
                bufferedWriter.write("\n");
                //System.out.println(i + " Минимум");
            }
            if (f < middleValue && middleValue > prevValue && prevValue != Double.MAX_VALUE && Math.abs(f - middleValue) < 1) {
                bufferedWriter.write("//////НАХОДИМ МАКСИМУМ ПРИ ПОМОЩИ МЕТОДА ЗОЛОТОГО СЕЧЕНИЯ//////"+ "\n");
                bufferedWriter.write(Double.valueOf(GoldenSection.findMax(i - step - step, i, epsilon)).toString() + "\n");
                bufferedWriter.write("Начало интервала: " + (i - step - step) + " Конец интервала: " + i + "\n");
                bufferedWriter.write("ТОЧНОСТЬ РАВНА = " + epsilon + "\n");
                bufferedWriter.write("Количество итераций: " + GoldenSection.maxOperationsCounter + "\n");
                bufferedWriter.write("///////////////////////////////////////////////////////////////\n");
                bufferedWriter.write("\n");
            }
            prevValue = middleValue;
            middleValue = f;
        }
        } catch (IOException e){ e.printStackTrace();}

       /* System.out.println(GS.findMin(0, 10, 0.001) + " Min");
        System.out.println(GS.findMax(-1, 0, 0.001) + " Max");*/

        Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd /d D: && .\\newFile.txt\"");
        Thread.sleep(100);
        Runtime.getRuntime().exec("taskkill /f /im cmd.exe") ;


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
        frame.setSize(400, 300);
        frame.show();
    }
}