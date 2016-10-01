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

    /**
     *  WRITE A JAVADOC HERE
     * @param primaryStage
     * @throws Exception
     */
    public void start( Stage primaryStage ) throws Exception {

        // initializing composition object
        Composition composition = new Composition();

        // loading FXML
        FXMLLoader fxmlLoader = new  FXMLLoader( getClass().getResource("Main.fxml") );
        fxmlLoader.setController( composition );      // Set Composition as the controller
        BorderPane root = fxmlLoader.load();
        guiLineSetup(root.getCenter());

        // Setting stage
        primaryStage.setTitle("Composition Player");
        primaryStage.setScene( new Scene( root, 600, 400) );
        primaryStage.setOnCloseRequest(event -> System.exit(0));

        // showing GUI
        primaryStage.show();
    }

    /**
     * Add 127 horizontal thin light gray lines spaced 10 pixels apart to the GUI
     *
     * @param center the pane that holds the VBox which holds the lines
     */
    public void guiLineSetup(Node center){


        // holds the noteLines, the notes (blocks), and the redLine
        Pane compositionBox = (Pane) ((ScrollPane) center).getContent();

        // looping over to add noteLines
        for ( int i = 1; i <= 127; i++ ){

            // initializing noteLine
            // i*10 used to control space between noteLines, 0 & 2000 for width
            Line noteLine = new Line( 0, i * 10, 2000, i * 10);

            // adding CSS class to noteLine object
            noteLine.getStyleClass().add( "compositionLine" );

            // adding noteLines to notePane
            compositionBox.getChildren().add( noteLine );
        }
    }

    /**
     * DO WE NEED A JAVADOC HERE?
     * @param args
     */
    public static void main( String[] args ) {
        launch(args);
    }
}
