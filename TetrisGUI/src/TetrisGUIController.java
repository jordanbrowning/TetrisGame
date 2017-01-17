/**
* Plays Tetris from the GUI application and changes board view to user
* @author browning
*/

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer; 
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class TetrisGUIController extends JPanel implements KeyListener {
	
	//board class instance. Pass it to the view
	private TetrisBoard board;
	
	//view class instance
	@SuppressWarnings("unused")
	private TetrisGUIView view;

	// drop rate of current piece falling 
	public static final int DEFAULT_DROP_RATE = 400; 
	
	// label for the number of lines  cleared so far
	private JLabel lineLabel;
	
	// label for the number of tetrises  cleared so far
	private JLabel tetrisLabel;
	
	// counts the time in the game
	private Timer gameTimer;
	
	
	/**
	 * Constructor: starts the game when run
	 */
	public TetrisGUIController() {
		// layout of panel
		super(new BorderLayout());				

		// begin game
		board = new TetrisBoard();
		
		// create displays for user
		createScore();
		createView();
		
		// add the key listener
		setFocusable(true);
		addKeyListener(this);
		
		// create timer in game
		setUpTimer();	
	}
	
	
	/**
	 * Creates the score display
	 */
	private void createScore() {
		// label for number of lines cleared
		lineLabel = new JLabel("Lines cleared: " + board.getNumLines());
		
		// label for number of tetrises cleared
		tetrisLabel = new JLabel("Tetrises Cleared: "  + board.getNumTetrises());
		
		// so both scores can be shown in the north section
		JPanel panelScores = new JPanel(new GridLayout(2, 1));
		
		// add labels to new panel
		panelScores.add(lineLabel);
		panelScores.add(tetrisLabel);
		
		// add panel to frame
		add(panelScores, BorderLayout.NORTH);
	}
	
	/**
	 * Creates the score display
	 */
	private void createView() {
		view = new TetrisGUIView(board);
		add(view, BorderLayout.CENTER);
	}
	
	
	/**
	 * Takes key input to move piece
	 */
	public void keyPressed(KeyEvent e) {
		//"KeyCode" is the enum of the key that was pressed
		//Check the KeyEvent documentation for more
		int key = e.getKeyCode();

	    if (key == KeyEvent.VK_DOWN) {
			board.moveDown();
	    }
	    
	    else if (key == KeyEvent.VK_LEFT) {
			board.moveLeft();
	    }
	    
	    else if (key == KeyEvent.VK_RIGHT) {
			board.moveRight();
	    }
	    
	    // drop to bottom of screen 
	    else if (key == KeyEvent.VK_SPACE) { 
	    	while(board.moveDown()) {
	    		board.moveDown();
	    	}
	    }
	    
	    // drop to bottom of screen
	    else if (key == KeyEvent.VK_X) { 
			board.rotateCCW();
	    }
	    
	    // drop to bottom of screen
	    else if (key == KeyEvent.VK_Z) { 
			board.rotateCW();
	    }
	    
	    refreshDisplay();
	}
	
	/**
	 * Does nothing
	 */
	public void keyReleased(KeyEvent e) {	
	}
	
	/**
	 * Does nothing
	 */
	public void keyTyped(KeyEvent e) {	
	}
	
	/**
	 * refreshes the display for the user
	 */
	public void refreshDisplay() {
		// update blocks
		view.repaint();
		
		// update scores
	    lineLabel.setText("Lines cleared: " + board.getNumLines());
	    tetrisLabel.setText("Tetrises Cleared: "  + board.getNumTetrises());
	}
	
	/**
	 * Creates the timer for the falling piece
	 */
	private void setUpTimer() {
		// create timer object
		gameTimer = new Timer(DEFAULT_DROP_RATE, new ActionListener() {
			
			// move block down when time is up
			public void actionPerformed(ActionEvent e) {
				board.moveDown();
				
				// update display
				refreshDisplay();
			}
			
		});
		
		// start the timer 
		gameTimer.start();	
	}
}
