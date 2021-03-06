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
import javafx.scene.Scene;
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
     * Overrides the start method of the Application class
     * Sets up the GUI
     *
     * @param primaryStage the stage to display the GUI
     * @throws Exception the exception that is thrown if Main.fxml cannot be found and loaded
     */
    @Override
    public void start( Stage primaryStage ) throws Exception {

        // initializing composition object
        Composition composition = new Composition();

        // loading FXML
        FXMLLoader fxmlLoader = new  FXMLLoader( getClass().getResource("Main.fxml") );
        fxmlLoader.setController( composition );      // set Composition as the controller
        BorderPane root = fxmlLoader.load();
        guiLineSetup(composition.getCompositionBox());

        // setting stage
        primaryStage.setTitle("Composition Player");
        primaryStage.setScene( new Scene( root, 600, 400) );
        primaryStage.setOnCloseRequest(event -> System.exit(0));

        // showing GUI
        primaryStage.show();
    }

    /**
     * Add 127 horizontal thin light gray lines spaced 10 pixels apart to the GUI
     *
     * @param compositionBox the pane that will hold the noteLines, the notes (blocks), and the redLine
     */
    public void guiLineSetup(Pane compositionBox){

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
     * Launches the program
     */
    public static void main( String[] args ) {
        launch(args);
    }
}
