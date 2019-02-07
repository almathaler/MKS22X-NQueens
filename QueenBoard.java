public class QueenBoard{
  private int[][]board;
  public QueenBoard(int size){
    board = new int[size][size];
    for (int i = 0; i<size; i++){
      for (int k = 0; k<size; k++){
        board[i][k] = 0;
      }
    }
  }
  private boolean addQueen(int r, int c){
    board[r][c] = -1;
    for (int upRow = 0; upRow<r; upRow++){
      board[upRow][col] += 1;
    }
    for (int downRow = r+1; downRow<size; downRow++){
      board[downRow][col] += 1;
    }
    for (int leftCol = 0; leftCol<c; leftCol++){
      board[r][leftCol] += 1;
    }
    for (int rightCol = c+1; rightCol < size; rightCol++){
      board[r][rightCol] +=1;
    }
    int maxBackDiag;
    if (size-r>=size-c){
      maxBackDiag = r;
    }else{
      maxBackDiag = c;
    }
    //if at col 3 and row 4, you can do +3+3, +2+2, +1+1 as your 3 diagonals that are above the QueenBoard
    
  };
  private boolean removeQueen(int r, int c){};
  /**
  *@return The output string formatted as follows:
  *All numbers that represent queens are replaced with 'Q'
  *all others are displayed as underscores '_'
  *There are spaces between each symbol:
  *"""_ _ Q _
  *Q _ _ _
  *_ _ _ Q
  *_ Q _ _"""
  *(pythonic string notation for clarity,
  *excludes the character up to the *)
  */
  public String toString(){}


  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;
  *        true when the board is solveable, and leaves the board in a solved state
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public boolean solve(){
    for (int r = 0; r<size; r++){
      //place a queen
    }
  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){}

}
