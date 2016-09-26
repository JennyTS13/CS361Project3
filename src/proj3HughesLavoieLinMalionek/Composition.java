package proj3HughesLavoieLinMalionek;

import javafx.fxml.FXML;

/**
 * Created by jslin on 9/26/16.
 */
public class Composition {

    /**
     * the number of beats per minute
     */
    private static final int BPM = 120;

    /**
     * the number of ticks per beat
     */
    private static final int RESOLUTION = 4;

    /**
     * the MidiPlayer which will be used to play the composed piece
     */
    private MidiPlayer midiPlayer;

    public Composition(){
        this.midiPlayer = new MidiPlayer(this.BPM, this.RESOLUTION);
    }

    /**
     * Triggered when "Exit" is clicked
     * Exits the program when user chooses to exit from File Menu
     */
    @FXML
    public void exitProgram(){
        System.exit(0);
    }

    /**
     * Triggered when "Play" is clicked
     * Plays the piece using the midiPlayer and moves the red bar to display progress
     */
    @FXML
    public void playComposition(){
        System.out.println("Playing");
    }

    /**
     * Triggered when "Stop" button is clicked
     * Stops and clears the midiPlayer
     */
    @FXML
    public void stopComposition(){
        this.midiPlayer.stop();
        this.midiPlayer.clear();
        System.out.println("Stopping");
    }
}
