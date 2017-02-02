package io.digitalsynapse.classificationIndicator;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.*;


import io.digitalsynapse.classificationIndicator.CommandLineParse;


/**
 * Created by raparkhurst on 1/31/17.
 */
public class Main extends Application {

    public static void main(String[] args) {
        new CommandLineParse(args).parse();

        Application.launch(args);
    }



    @Override
    public void start(Stage primaryStage) {
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        List<String> classificationStringArray = new ArrayList<>();

        try {
            classificationStringArray = ClassificationFileReader.Reader("/etc/classification");
        } catch (IOException e) {
            e.printStackTrace();
        }

        StackPane root = new StackPane();
        Scene scene = new Scene(root, 100, 100, Color.DARKGRAY);

        Text classificationTextBanner = new Text(classificationStringArray.get(1));
        classificationTextBanner.setFont(Font.font ("Verdana", FontWeight.BOLD, 12));

        switch (classificationStringArray.get(0)) {
            case "UNCLASSIFIED":
                scene.setFill(Color.DARKGREEN);
                classificationTextBanner.setFill(Color.WHITE);
                break;
            case "CONFIDENTIAL":
                scene.setFill(Color.DARKGREEN);
                classificationTextBanner.setFill(Color.WHITE);
                break;
            case "SECRET":
                scene.setFill(Color.DARKRED);
                classificationTextBanner.setFill(Color.WHITE);
                break;
            case "TOP SECRET":
                scene.setFill(Color.YELLOW);
                classificationTextBanner.setFill(Color.BLACK);
                break;
            default:
                break;
        }

        root.getChildren().addAll(classificationTextBanner);
        StackPane.setAlignment(classificationTextBanner, Pos.CENTER);


        //set Stage boundaries to visible bounds of the main screen
        // full screen
        //primaryStage.setX(primaryScreenBounds.getMinX());
        //primaryStage.setY(primaryScreenBounds.getMinY());
        //primaryStage.setWidth(primaryScreenBounds.getWidth());
        //primaryStage.setHeight(classificationTextBanner.getBoundsInLocal().getHeight()+8);


        // just center screen top
        primaryStage.setX(
                (primaryScreenBounds.getMaxX() - (classificationTextBanner.getBoundsInLocal().getWidth() + 20)) / 2
        );
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(classificationTextBanner.getBoundsInLocal().getWidth() + 20);
        primaryStage.setHeight(classificationTextBanner.getBoundsInLocal().getHeight()+8);
        primaryStage.setAlwaysOnTop(true);



        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
