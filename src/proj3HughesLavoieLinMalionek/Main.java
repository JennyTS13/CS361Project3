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
     * the number of beats per minute
     */
//    private static final int BPM = 120;

    /**
     * the number of ticks per beat
     */
//    private static final int RESOLUTION = 4;

    /**
     * the MidiPlayer which will be used to play the scale
     */
//    private MidiPlayer midiPlayer;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        this.midiPlayer = new MidiPlayer(this.BPM, this.RESOLUTION);

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

    /**
     * Plays a scale by generating and adding a major scale to the MidiPlayer
     * composition given a starting note.
     * Handles illegal values by showing error & launching scale dialog again.
     *
     * @param startingPitch the MIDI pitch to build the scale on
     */
//    private void playScale(int startingPitch) {
//
//        if (startingPitch < 0 || startingPitch > 115) {
//            displayError("Illegal Starting Note", "Please enter a value between 0 & 115.");
//            launchScaleDialog().ifPresent(this::playScale);
//            return;
//        }
//
//        this.midiPlayer.clear();
//
//        int majorScale[] = {0, 2, 4, 5, 7, 9, 11, 12, 11, 9, 7, 5, 4, 2, 0};
//
//        IntStream.range(0, majorScale.length)
//                .forEach(i -> this.midiPlayer.addNote(startingPitch + majorScale[i],
//                        80, i * this.RESOLUTION,
//                        this.RESOLUTION, 0, 0));
//
//        this.midiPlayer.play();
//    }
//
//    /**
//     * Launches a TextInputDialog for user to input a starting note.
//     * Handles non-integer input errors accordingly.
//     * @return the selected starting note, empty if cancelled
//     */
//    private Optional<Integer> launchScaleDialog() {
//        TextInputDialog dialog = new TextInputDialog("60"); //Default is middle-C
//        dialog.setTitle("Starting Note");
//        dialog.setHeaderText("Please enter a starting note (0-115)");
//
//        Optional<String> result = dialog.showAndWait();
//
//        if (result.isPresent()) {
//            try {
//                return Optional.of(Integer.parseInt(result.get()));
//            } catch (NumberFormatException e) {
//                displayError("Illegal Value", "Please enter an integer.");
//                return launchScaleDialog();
//            }
//        }
//
//        return Optional.empty();
//    }
//
//    /**
//     * Convenience method for displaying a generic error
//     *
//     * @param header the Alert header
//     * @param body the Alert content
//     */
//    private void displayError(String header, String body) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Error");
//        alert.setHeaderText(header);
//        alert.setContentText(body);
//        alert.showAndWait();
//    }

}
