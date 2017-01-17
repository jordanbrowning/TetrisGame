/**
l* Plays Tetris from the command line and changes board view to user
* @author browning
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class TetrisTextController {
	
	//board class instance. Pass it to the view
	private TetrisBoard board;
	
	//view class instance
	@SuppressWarnings("unused")
	private TetrisTextView view;
	
	/**
	 * Constructor: starts the game when run
	 */
	public TetrisTextController() {
		board = new TetrisBoard();
		view = new TetrisTextView(board);
		//begin game
		readInput();
	}
	
	/**
	 * To take in user's changes to the game
	 */
	protected void readInput() { 
		// Buffered readers are used to read text input from the command line
		BufferedReader in = new BufferedReader( new InputStreamReader(System.in));

		// to prevent exceptions from crashing program 
		
		//Used to hold what the user inputs
		String line = "";
		try {
			System.out.println("Please enter a move (l,r,d,z,x) or type Quit to end.");
					
			// Try to read a line
			// This function potentially throws an IOException
			line = in.readLine(); 
			String input = line.toLowerCase();

			// Loop until the user types "quit"
			while (!input.equals( "quit" )) {
				
				// good input 
				if (input.equals( "l" ) || input.equals( "r" ) || input.equals( "d" ) || input.equals( "z" ) ||
						input.equals( "x" )) {
					//performing a move
					moveEntered(input);
					
					//update board
					refreshDisplay();
					// instructions for next input
					System.out.println("Please enter a move (l,r,d,z,x) or type Quit to end.");
				}
				// bad input -- try again
				else {
					System.out.println("Error: Please enter a move (l,r,d,z,x) or type Quit to end.");
				}
				// repeat	
				line = in.readLine(); 
				input = line.toLowerCase();
				
			}

		}

		// catch I/O exception
		catch (IOException ioenfe) {
			//inform user of problem
			System.out.println("Error: IOException. ");
		}
	}
	
	/**
	 * To change the view displayed to the user
	 */
	protected void refreshDisplay() { 
		view = new TetrisTextView(board);
	}
	
	/**
	 * To turn input into a move
	 */
	protected void moveEntered(String move) { 
		// left
		if(move.equals( "l" )) {
			board.moveLeft();
		}
		
		// right
		if(move.equals( "r" )) {
			board.moveRight();
		}
		
		// down
		if(move.equals( "d" )) {
			board.moveDown();
		}
		
		// clockwise rotate
		if(move.equals( "z" )) {
			board.rotateCW();
		}
		// counter-clockwise rotate
		if(move.equals( "x" )) {
			board.rotateCCW();
		}
	}
	
	//to start the program
	public static void main(String[] args) {
		new TetrisTextController();
	}

}
