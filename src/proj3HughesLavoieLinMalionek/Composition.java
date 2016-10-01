/*
 * File: Composition.java
 * Names: Phoebe Hughes, Erin Lavoie, Jenny Lin, Joseph Malionek
 * Class: CS361
 * Project: 3
 * Date: October 3, 2016
 */

package proj3HughesLavoieLinMalionek;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

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
     * The number of beats per minute.
     * Used when initializing the MidiPlayer.
     */
    private final int BPM = 60;

    /**
     * The number of ticks per beat.
     * Used when initializing the MidiPlayer.
     */
    private final int DURATION = 100;

    /**
     * The default volume for the MidiPlayer.
     * Used for adding notes to the MidiPlayer.
     */
    private final int VOLUME = 80;

    /**
     * The MidiPlayer that will be used to play the user's composition piece.
     */
    private MidiPlayer midiPlayer;

    /**
     * The list that stores all notes added by the user to the composition
     * to be played by the MidiPlayer.
     */
    private ArrayList<Note> noteList;

    /**
     * The Pane that stores the redLine, noteLines, and note block.
     */
    @FXML
    private Pane compositionBox;

    /**
     * The redLine which moves across the screen as a composition is played.
     */
    @FXML
    private Line redLine;

    /**
     * The timeline for animating the redLine.
     */
    private Timeline timeline;


    /**
     * Constructor for Composition
     */
    public Composition() {

        // midiPlayer for playing notes
        this.midiPlayer = new MidiPlayer(this.BPM, this.DURATION);

        // noteList for storing notes
        this.noteList = new ArrayList<Note>();

        // timeline for animating redLine
        this.timeline = new Timeline();

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

        // stop the program
        this.stopComposition();

        // keeps track of the final note in the composition
        int finalTick = 0;

        // looping over our note list
        for( Note note : this.noteList ) {
            
            // adding each note from note list to the midiPlayer cache
            this.midiPlayer.addNote( note.pitch, this.VOLUME, note.startTick,
                    note.startTick + this.DURATION, 0, 0 );

            // check if the note being added is past the current finalTick
            if( note.startTick + this.DURATION > finalTick ){

                // if yes, we update the value for finalTick
                finalTick = note.startTick + this.DURATION;
            }

        }

        // moving redLine in time with the midiPlayer as it plays
        this.midiPlayer.play();
        this.moveRedLine(finalTick);

    }

    /**
     * Triggered when "Stop" button is clicked
     * Stops and clears the midiPlayer
     */
    @FXML
    public void stopComposition() {

        // stop the midiPlayer and clear the noteCache
        this.midiPlayer.stop();
        this.midiPlayer.clear();

        // making redLine invisible
        this.redLine.setVisible(false);
        // stops the animation
        this.timeline.stop();
        // reset timeline events
        this.timeline.getKeyFrames().clear();
    }

    /**
     * Triggered when the user clicks anywhere within the compositionBox
     * Adds a note to our compositionNoteList and the midiPlayer's stream
     *
     * @param click the event that holds information on where the click occurred
     */
    @FXML
    public void addNoteOnClick(MouseEvent click) {

        // we divide y by 10, floor the result and multiply by 10 in order
        // to lock the box within the bounds of the noteLines
        int yloc = ((int) click.getY() / 10) * 10; // y coord of top left corner of box
        int xloc = (int) click.getX();

        // creating Rectangle and adding CSS class to it
        Rectangle rect = new Rectangle( 100, 10);
        rect.getStyleClass().add("noteBox");

        // moving the rect to clicked location
        rect.setTranslateX(xloc);
        rect.setTranslateY(yloc);

        compositionBox.getChildren().add(rect);

        // create and store the equivalent note in noteList
        this.noteList.add( new Note( 127 - yloc / 10, xloc) );  // ***JENNY!!!! Pwease explain the arithmetic here******************************
    }


    /**
     * Makes a red line go across the screen as the notes are played.
     */

    public void moveRedLine(int stopPosition){

        // set the redLine back to the beginning [of the timeline?]
        redLine.setEndX(0);
        redLine.setStartX(0);

        // set the redLine to visible
        redLine.setVisible(true);

        // Duration object for KeyFrame that specifies the duration of the animation
        // we multiply stopPosition by 10 because duration takes an input in milliseconds
        Duration duration = new Duration( stopPosition * 10 );

        // the KeyValue on the timeline that animates the top of the redLine
        KeyValue startXKey = new KeyValue( redLine.startXProperty(), stopPosition );

        // the KeyValue on the timeline that animates the bottom of the redLine
        KeyValue endXKey = new KeyValue( redLine.endXProperty(), stopPosition );


        // KeyFrame defines how the redLine moves
        // the stopPosition is where the line should stop moving and disappear
        KeyFrame motion = new KeyFrame( duration,
                event -> redLine.setVisible(false),     // set redLine to invisible when animation has finished
                startXKey, endXKey );

        // we create a timeline object that orchestrates and stores the keyframes of our animation
        timeline = new Timeline();

        // we add our motion KeyFrame to our timeline
        timeline.getKeyFrames().add(motion);

        timeline.play();
    }

    /**
     * A note object that stores the pitch and startTick values
     * to be played on a MidiPlayer
     */
    private class Note{

        // information required to play a note with the MidiPlayer
        private int pitch;
        private int startTick;

        /**
         * Constructs a Note object
         *
         * @param pitch         the pitch of the note to be played with a MidiPlayer (Range 0-127)
         * @param startTick     the tick that the note begins playing in the MidiPlayer
         */
        Note( int pitch, int startTick ){

            // initializing fields
            this.pitch = pitch;
            this.startTick = startTick;

        }
    }
}

