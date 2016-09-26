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

import java.util.ArrayList;

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
     * the
     */
    private ArrayList<Note> composition;

    /**
     * the VBox which holds all composition information
     */
    @FXML
    private Pane compositionBox;

    public Composition() {
        this.midiPlayer = new MidiPlayer(this.BPM, this.RESOLUTION);
        this.composition = new ArrayList<Note>();
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
     * Clears the midi player, adds all note to its stream and plays the piece
     */
    @FXML
    public void handlePlay() {
        this.midiPlayer.clear();
        for(Note note : this.composition) {
            this.midiPlayer.addNote(note.pitch, 80, note.startTick,
                    note.startTick + this.RESOLUTION, 0, 0);
        }
        this.midiPlayer.play();
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
     * Adds a note to our compositionNoteList and the midiPlayer's stream
     *
     * @param click the event that holds information on where the click occurred
     */
    @FXML
    public void addNoteOnClick(MouseEvent click) {

        int yloc = ((int)click.getY()/10)*10; //y coordinate of the top left of the rectangle
        int xloc = (int)click.getX();

        Rectangle rect = new Rectangle(100, 10);
        rect.getStyleClass().add("noteBox");
        rect.setTranslateX(xloc);
        rect.setTranslateY(yloc);
        compositionBox.getChildren().add(rect);

        this.composition.add(new Note(yloc/10, xloc));
    }

    private class Note{

        public int pitch;
        public int startTick;

        public Note(int pitch, int startTick){
            this.pitch = pitch;
            this.startTick = startTick;
        }
    }
}

