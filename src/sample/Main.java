package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.*;

public class Main extends Application {
    @FXML
    LineChart<Number, Number> graf1;
    @FXML
    TabPane tabPane;
    @FXML
    Tab tab;
    @FXML
    Tab tab1;
    @FXML
    Tab tab2;
    @FXML
    Tab tab3;
    String x1, y1, x2, y2;
    public static String[] mx1, mx2, my1, my2;
    double[] ix1 ;//= new double[5];
    double[] ix2 ;//= new double[5];
    double[] iy1 ;//= new double[5];
    double[] iy2 ;//= new double[5];
    @FXML
    JFXTextField tfX1, tfX2, tfY1, tfY2;
    @FXML
    JFXButton enter;

    @FXML
    JFXButton mainEnter;

    Stage stageGraf = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample1.fxml"));
        primaryStage.setTitle("Test");
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.show();
    }

    public void initialize() {
        //Обработка нажатия на кнопку
        mainEnter.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Считывание данных с полей
                x1 = tfX1.getText();
                y1 = tfY1.getText();
                x2 = tfX2.getText();
                y2 = tfY2.getText();
                mx1 = x1.split(" ");
                mx2 = x2.split(" ");
                my1 = y1.split(" ");
                my2 = y2.split(" ");

                String infoMessage = "Количество Х не совпадает с количеством У!";
                String titleBar = "Ошибка";

                String infoMessage2 = "Некорректный ввод!";
                String titleBar2 = "Ошибка";

                //Исключительное устловие разности длины массива точек
                if ((mx1.length != my1.length) || (mx2.length  != my2.length) ) {
                    JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
                } else {

                    double[] ix1 = new double[mx1.length];
                    double[] ix2 = new double[mx2.length];
                    double[] iy1 = new double[my1.length];
                    double[] iy2 = new double[my2.length];

                    //Перевод данных из String в Double
                    try {
                        for (int i = 0; i < mx1.length; i++) {
                            ix1[i] = Double.parseDouble(mx1[i]);
                        }

                        for (int i = 0; i < mx2.length; i++) {
                            ix2[i] = Double.parseDouble(mx2[i]);
                        }

                        for (int i = 0; i < my1.length; i++) {
                            iy1[i] = Double.parseDouble(my1[i]);
                        }
                        for (int i = 0; i < my2.length; i++) {
                            iy2[i] = Double.parseDouble(my2[i]);
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

                        //Сортировка ArrayList-ов
                        Collections.sort(points);
                        Collections.sort(points2);

                        //Отрисовка двух графиков
                        LineChart<Number, Number> numberLineChart = new LineChart<Number, Number>(x, y);
                        numberLineChart.setTitle("Функции");
                        XYChart.Series series1 = new XYChart.Series();
                        series1.setName("Функция №1");
                        for (int i = 0; i < points.size(); i++) {
                            series1.getData().add(new XYChart.Data(points.get(i).x, points.get(i).y));
                        }

                        XYChart.Series series2 = new XYChart.Series();
                        series2.setName("Функция №2");
                        for (int i = 0; i < points2.size(); i++) {
                            series2.getData().add(new XYChart.Data(points2.get(i).x, points2.get(i).y));
                        }

                        Scene scene = new Scene(numberLineChart, 600, 600);
                        numberLineChart.getData().add(series1);
                        numberLineChart.getData().add(series2);
                        stageGraf.setScene(scene);

                        stageGraf.show();

                        //Проверка корректности ввода(ТОЛЬКО ЦИФРЫ!)
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(null, infoMessage2, titleBar2, JOptionPane.INFORMATION_MESSAGE);

                    }


                }
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
