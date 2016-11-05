package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;

/**
 * Created by 47 on 05.11.2016.
 */
public class SecondaryStage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage secondaryStage) throws Exception {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Parent root = FXMLLoader.load(getClass().getResource("sample1.fxml"));
        secondaryStage.setTitle("Test");
        secondaryStage.setScene(new Scene(root, screenSize.getWidth() / 4.8, screenSize.getHeight() / 4));
        secondaryStage.setResizable(false);
        secondaryStage.setX(0);
        secondaryStage.setY(0);
        secondaryStage.show();

    }
}
