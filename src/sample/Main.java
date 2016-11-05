package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class Main extends Application {

    Stage secondaryStage = new Stage();
    long unixSeconds = System.currentTimeMillis();
    Date date = new Date(unixSeconds);
    SecondaryStage secStage = new SecondaryStage();
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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        secStage.start(secondaryStage);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        String formattedDate = sdf.format(date);
        String time = formattedDate.replace(':', '.');
        float timeF = Float.parseFloat(time);

        for (int i = 0; i < 24; i++){

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

                        Scene scene = new Scene(numberLineChart,screenSize.getWidth()/1.5,screenSize.getHeight()/1.8);
                        numberLineChart.getData().add(series1);
                        numberLineChart.getData().add(series2);

        numberLineChart.getData().add(series3);
        primaryStage.setScene(scene);
        primaryStage.setX(screenSize.getHeight() / 2);
        primaryStage.setY(screenSize.getHeight()/5);
        primaryStage.show();

    }
}
