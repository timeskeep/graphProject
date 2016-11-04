package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample1.fxml"));
        primaryStage.setTitle("Test");
        primaryStage.setScene(new Scene(root, 640, 480));
        long unixSeconds = System.currentTimeMillis();
        Date date = new Date(unixSeconds); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); // the format of your date
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+2")); // give a timezone reference for formating (see comment at the bottom
        String formattedDate = sdf.format(date);
        String time = formattedDate.replace(':', '.');
        float timeF = Float.parseFloat(time);
        System.out.println(timeF);

        double[] ix1 = new double[24];
        double[] iy1 = {25.5, 25.3, 25.4, 25.4, 25.4, 25.3,
                25.3, 25.4, 25.3, 25.5, 25.5, 25.5, 25.4, 25.4,
                25.4, 25.4, 25.3, 25.3, 25.5, 25.5, 25.6, 25.5,
                25.5, 25.4};
        double[] iy2 = {28.3, 28.3, 28.3, 28.3, 28.3, 28.2, 28.2,
                28.3, 28.3, 28.2, 28.1, 28.1, 28.2, 28.2, 28.3,
                28.3, 28.1, 28.2, 28.3, 28.2, 28.3, 28.2,
                28.1, 28.2};
        double[] iy3 = new double[24];

        for (int i = 0; i < 24; i++) {
            iy3[i] = iy1[i] / iy2[i];
        }

        ix1[0] = 0.0;
        for (int i = 1; i < 23; i++) {
            ix1[i] = ix1[i - 1] + 1.0;
            System.out.println(Double.toString(ix1[i]));
        }

                        NumberAxis x = new NumberAxis();
                        NumberAxis y = new NumberAxis();

                        //Создание ArrayList-ов для сортировки массива точек по Х и для дальнейшей работы с ними
                        ArrayList<Points> points = new ArrayList<Points>();
                        for (int i = 0; i < ix1.length; i++) {
                            points.add(new Points(ix1[i], iy1[i]) {
                            });
                        }
                        ArrayList<Points> points2 = new ArrayList<Points>();
        for (int i = 0; i < ix1.length; i++) {
            points2.add(new Points(ix1[i], iy2[i]) {
                            });
                        }
        ArrayList<Points> points3 = new ArrayList<Points>();
        for (int i = 0; i < ix1.length; i++) {
            points3.add(new Points(ix1[i], iy3[i]) {
            });
        }

                        //Отрисовка двух графиков
                        LineChart<Number, Number> numberLineChart = new LineChart<Number, Number>(x, y);
                        numberLineChart.setTitle("Функции");
                        XYChart.Series series1 = new XYChart.Series();
        series1.setName("$");
                        for (int i = 0; i < points.size(); i++) {
                            series1.getData().add(new XYChart.Data(points.get(i).x, points.get(i).y));
                        }
                        XYChart.Series series2 = new XYChart.Series();
        series2.setName("€");
                        for (int i = 0; i < points2.size(); i++) {
                            series2.getData().add(new XYChart.Data(points2.get(i).x, points2.get(i).y));
                        }
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Соотношение $/€");
        for (int i = 0; i < points3.size(); i++) {
            series3.getData().add(new XYChart.Data(points3.get(i).x, points3.get(i).y));
        }

                        Scene scene = new Scene(numberLineChart, 600, 600);
                        numberLineChart.getData().add(series1);
                        numberLineChart.getData().add(series2);
        numberLineChart.getData().add(series3);
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
