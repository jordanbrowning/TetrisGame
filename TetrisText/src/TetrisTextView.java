/**
* Prints out the tetris board, creating a view for the user
* @author browning
*/
public class TetrisTextView {
	
	//The board object from the game
	private TetrisBoard board;
	
	/**
	 * constructor takes in the board and prints it to screen
	 * @param board
	 */
	public TetrisTextView(TetrisBoard board){
		this.board = board;
		printBoard();
	}
	
	/**
	 * prints the board to screen
	 */
	public void printBoard() { 
		
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
	
		System.out.println("Number of lines cleared:" + board.getNumLines()); 
		System.out.println("Number of Tetrises cleared:" + board.getNumTetrises()); 
		System.out.println("----------");
		
		//loop through board and add piece locations
		// for each board column
		for(int i = 0; i < numRows; i++) {
			//for each board row
			for(int j = 0; j < numCols; j++) {
				// in filled squares, the row to check if square is filled
				int row_piece = i - currentPieceGridPosition[0]; 
				
				// in filled squares, the col to check if square is filled
				int col_piece = j - currentPieceGridPosition[1]; 
				
				// print pieces already landed/saved in block matrix
				if(gameBoard[i][j]) {	
					System.out.print("x");
				}
				
				// print the piece currently falling
				else if(row_piece >= 0 && row_piece <= 3 && col_piece >= 0 && col_piece <= 3) {
					if(currentPiece.isFilled(rot, row_piece, col_piece)) {
						System.out.print("x");
					}
					
					//where there is nothing
					else {
						System.out.print(" ");
					}
				}
				
				// where there is nothing
				else {
					System.out.print(" ");
				}

			}

			System.out.println();
		}
		System.out.println("----------");
	}
}
