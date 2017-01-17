/**
* Creates a view of the tetris board for the user
* @author browning
*/

import javax.swing.JComponent;
import java.awt.Graphics; 
import java.awt.Color;

public class TetrisGUIView extends JComponent {

	//The board object from the game
	private TetrisBoard board;
	
	/**
	 * constructor takes in the board and creates a display
	 * @param board
	 */
	public TetrisGUIView(TetrisBoard board) {
		this.board = board;
		repaint();
	
	}
	
	/**
	 * Paint the game
	 */
	public void paint(Graphics g) {
			
		// the board with current block positions 
		boolean[][] gameBoard = board.getBlockMatrix();
		
		// piece currently dropping
		TetrisPiece currentPiece = board.getCurrentPiece(); 
		
		// northwest corner of piece's space on board
		int[] currentPieceGridPosition = board.getCurrentPieceGridPosition(); 
		
		// rows in the board
		int numRows = board.getNumRows();
		
		// columns in the board
		int numCols = board.getNumCols(); 
		
		// current rotation of piece
		int rot = currentPiece.getPieceRotation(); 
		
		// size of block
		int blockSize = 35;
		
		// create outline of board
		paintBoardOutline(g, blockSize);
		
		//loop through board and add piece locations
		// for each board row
		for(int i = 0; i < numRows; i++) {
			//for each board column
			for(int j = 0; j < numCols; j++) {
				// in filled squares, the row to check if square is filled
				int row_piece = i - currentPieceGridPosition[0]; 
				
				// in filled squares, the col to check if square is filled
				int col_piece = j - currentPieceGridPosition[1]; 
				
				// paint pieces already landed/saved in block matrix
				if(gameBoard[i][j]) {	
					paintBlock(g, j*blockSize, i*blockSize, blockSize);
				}
				
				// paint the piece currently falling
				else if(row_piece >= 0 && row_piece <= 3 && col_piece >= 0 && col_piece <= 3) {
					if(currentPiece.isFilled(rot, row_piece, col_piece)) {
						paintBlock(g, j*blockSize, i*blockSize, blockSize);
					}
				}
			}
		}
	}
	
	/**
	 * Paint the block at grid row, grid col
	 */
	private void paintBlock(Graphics g, int row, int col, int blockSize) {
		
		// fill blocks
		g.setColor(Color.GREEN);
		g.fillRect(row, col, blockSize, blockSize); 
		
		// outline of block
		g.setColor(Color.MAGENTA);
		g.drawRect(row, col, blockSize, blockSize);
	}
	
	/**
	 * Paint the board outline according to block size
	 */
	private void paintBoardOutline(Graphics g, int blockSize) {
		// rows in the board
		int numRows = board.getNumRows();
		
		// columns in the board
		int numCols = board.getNumCols(); 
		
		// draw board outline
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, numCols*blockSize, numRows*blockSize);
	}
}
