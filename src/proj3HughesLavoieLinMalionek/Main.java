/*
 * File: Main.java
 * Names: Phoebe Hughes, Erin Lavoie, Jenny Lin, Joseph Malionek
 * Class: CS361
 * Project: 3
 * Date: October 3, 2016
 */

package proj3HughesLavoieLinMalionek;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 *  This class creates a JavaFX window for the user to compose a musical piece to be played
 *  @author Phoebe Hughes
 *  @author Erin Lavoie
 *  @author Jenny Lin
 *  @author Joseph Malionek
 */
public class Main extends Application{


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Composition compositionPane = new Composition();

        FXMLLoader fxmlLoader = new  FXMLLoader(getClass().getResource("Main.fxml"));
        fxmlLoader.setController(compositionPane); //Set Composition as the controller
        BorderPane root = fxmlLoader.load();
        guiLineSetup(root.getCenter());

        primaryStage.setTitle("Composition Player");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setOnCloseRequest(event -> System.exit(0));

        primaryStage.show();
    }

    /**
     * Add 127 horizontal thin light gray lines spaced 10 pixels apart to the GUI
     * @param center the pane that holds the VBox which holds the lines
     */
    public void guiLineSetup(Node center){
        Pane compositionPane = (Pane)((ScrollPane)center).getContent();
        for(int i = 1; i <= 127; i++){
            Line l = new Line();
            l.setStartX(0);
            l.setEndX(2000);
            l.setTranslateY(i*10);
            l.getStyleClass().add("compositionLine");
            compositionPane.getChildren().add(l);
        }
    }
}
