public class QueenBoard{
  private int[][]board;
  public static void main(String[] args){
    QueenBoard example = new QueenBoard(8);
    example.addQueen(4, 4);
    System.out.println(example.toStringDebug());
    example.removeQueen(4, 4);
    System.out.println(example.toStringDebug());
  }
  public QueenBoard(int size){
    board = new int[size][size];
    for (int i = 0; i<size; i++){
      for (int k = 0; k<size; k++){
        board[i][k] = 0;
      }
    }
  }
  private boolean addQueen(int r, int c){
    if (board[r][c] == -1){
      return false; //there's already a queen
    }
    board[r][c] = -1;
    for (int upRow = 0; upRow<r; upRow++){
      board[upRow][c] += 1;
    }
    for (int downRow = r+1; downRow<board.length; downRow++){
      board[downRow][c] += 1;
    }
    for (int leftCol = 0; leftCol<c; leftCol++){
      board[r][leftCol] += 1;
    }
    for (int rightCol = c+1; rightCol <board.length; rightCol++){
      board[r][rightCol] +=1;
    }
    int curR = r+1;
    int curC = c+1; // down to bottom right
    while (curR < board.length && curC < board.length){
      board[curR][curC] += 1;
      curR++;
      curC++;
    }
    curR = r+1;
    curC = c-1; // down to bottom left
    while (curR < board.length && curC >= 0){
      board[curR][curC] += 1;
      curR++;
      curC--;
    }
    curR = r-1;
    curC = c-1; // up to top left
    while (curR >= 0 && curC >= 0){
      board[curR][curC] += 1;
      curR--;
      curC--;
    }
    curR = r-1;
    curC = c+1; // up to top right
    while (curR >= 0 && curC < board.length){
      board[curR][curC] += 1;
      curR--;
      curC++;
    }
    return true;
    //if at col 3 and row 4, you can do +3+3, +2+2, +1+1 as your 3 diagonals that are above the QueenBoard
  };
  private boolean removeQueen(int r, int c){
    if (board[r][c] == 0){
      return false; nothing to remove
    }
    board[r][c] = 0;
    for (int upRow = 0; upRow<r; upRow++){
      board[upRow][c] -= 1;
    }
    for (int downRow = r+1; downRow<board.length; downRow++){
      board[downRow][c] -= 1;
    }
    for (int leftCol = 0; leftCol<c; leftCol++){
      board[r][leftCol] -= 1;
    }
    for (int rightCol = c+1; rightCol <board.length; rightCol++){
      board[r][rightCol] -=1;
    }
    int curR = r+1;
    int curC = c+1; // down to bottom right
    while (curR < board.length && curC < board.length){
      board[curR][curC] -= 1;
      curR++;
      curC++;
    }
    curR = r+1;
    curC = c-1; // down to bottom left
    while (curR < board.length && curC >= 0){
      board[curR][curC] -= 1;
      curR++;
      curC--;
    }
    curR = r-1;
    curC = c-1; // up to top left
    while (curR >= 0 && curC >= 0){
      board[curR][curC] -= 1;
      curR--;
      curC--;
    }
    curR = r-1;
    curC = c+1; // up to top right
    while (curR >= 0 && curC < board.length){
      board[curR][curC] -= 1;
      curR--;
      curC++;
    }
    return true;
  };
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
  // public String toString(){}

  private String toStringDebug(){
    String toReturn = "";
    for (int i = 0; i<board.length; i++){
      for (int k = 0; k<board.length; k++){
        toReturn += board[i][k] + ", ";
      }
      toReturn += "\n";
    }
    return toReturn;
  }

  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;
  *        true when the board is solveable, and leaves the board in a solved state
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  // public boolean solve(){}

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  // public int countSolutions(){}

}
