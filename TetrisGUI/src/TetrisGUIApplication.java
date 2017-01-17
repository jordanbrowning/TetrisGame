/**
* Command line version of the GUI Tetris game
* @author browning
*/

import javax.swing.JFrame;

public class TetrisGUIApplication {
	
	/**
	 * Constructor: starts a new controller
	 */
	public TetrisGUIApplication() {
		new TetrisGUIController();
	}
	
	/**
	 * Starts the game
	 */
	public static void main(String[] args) {
		JFrame guiFrame;
		
		// create a new JFrame to hold Tetris 
		guiFrame = new JFrame( "Tetris");
	
		// set size
		guiFrame.setSize( 500, 800 );

		// create new Tetris game
		guiFrame.add(new TetrisGUIController());
		
		// exit normally on closing the window
		guiFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		// show frame
		guiFrame.setVisible( true );
	}
}
