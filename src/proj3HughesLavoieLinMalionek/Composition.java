/*
 * File: Composition.java
 * Names: Phoebe Hughes, Erin Lavoie, Jenny Lin, Joseph Malionek
 * Class: CS361
 * Project: 3
 * Date: October 3, 2016
 */

package proj3HughesLavoieLinMalionek;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 *  This class handles all the FXML actions, allowing users to add notes and play the composition
 *  @author Phoebe Hughes
 *  @author Erin Lavoie
 *  @author Jenny Lin
 *  @author Joseph Malionek
 */
public class Composition {

    /**
     * the number of beats per minute
     */
    private static final int BPM = 60;

    /**
     * the number of ticks per beat
     */
    private static final int RESOLUTION = 100;

    /**
     * the MidiPlayer which will be used to play the composed piece
     */
    private MidiPlayer midiPlayer;

    /**
     * the VBox which holds all composition information
     */
    @FXML
    private Pane compositionBox;

    public Composition() {
        this.midiPlayer = new MidiPlayer(this.BPM, this.RESOLUTION);
    }

    /**
     * Triggered when "Exit" is clicked
     * Exits the program when user chooses to exit from File Menu
     */
    @FXML
    public void exitProgram() {
        System.exit(0);
    }

    /**
     * Triggered when "Play" is clicked
     * Plays the piece using the midiPlayer and moves the red bar to display progress
     */
    @FXML
    public void playComposition() {
        System.out.println("Playing");
    }

    /**
     * Triggered when "Stop" button is clicked
     * Stops and clears the midiPlayer
     */
    @FXML
    public void stopComposition() {
        this.midiPlayer.stop();
        this.midiPlayer.clear();
        System.out.println("Stopping");
    }

    /**
     * Triggered when the user clicks anywhere within the compositionBox
     * Adds a note to the GUI and the midiPlayer's stream
     *
     * @param click the event that holds information on where the click occurred
     */
    @FXML
    public void addNoteOnClick(MouseEvent click) {

        int yloc = ((int)click.getY()/10)*10; //y coordinate of the top left of the rectangle

        Rectangle rect = new Rectangle(100, 10);
        rect.getStyleClass().add("noteBox");
        rect.setTranslateX(click.getX());
        rect.setTranslateY(yloc);
        compositionBox.getChildren().add(rect);
    }
}

