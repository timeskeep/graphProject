package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample1.fxml"));
        primaryStage.setTitle("Test");
        primaryStage.setScene(new Scene(root, 640, 480));

                //Исключительное устловие разности длины массива точек
        double[] ix1 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12,
                13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24};
        double[] iy1 = {25.5, 25.3, 25.4, 25.4, 25.4, 25.3,
                25.3, 25.4, 25.3, 25.5, 25.5, 25.5, 25.4, 25.4,
                25.4, 25.4, 25.3, 25.3, 25.5, 25.5, 25.6, 25.5,
                25.5, 25.4};
        double[] ix2 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12,
                13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24};
        double[] iy2 = {28.3, 28.3, 28.3, 28.3, 28.3, 28.2, 28.2,
                28.3, 28.3, 28.2, 28.1, 28.1, 28.2, 28.2, 28.3,
                28.3, 28.1, 28.2, 28.3, 28.2, 28.3, 28.2, 28.1, 28.2};
        double[] ix3 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12,
                13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24};
        double[] iy3 = new double[ix3.length];

        for (int i = 0; i < ix3.length; i++) {
            iy3[i] = iy1[i] / iy2[i];
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
                        for (int i = 0; i < ix2.length; i++) {
                            points2.add(new Points(ix2[i], iy2[i]) {
                            });
                        }
        ArrayList<Points> points3 = new ArrayList<Points>();
        for (int i = 0; i < ix3.length; i++) {
            points3.add(new Points(ix3[i], iy3[i]) {
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
