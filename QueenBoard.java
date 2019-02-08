public class QueenBoard{
  private int[][]board;
  public static void main(String[] args){
    QueenBoard example = new QueenBoard(8);
    /*
    for (int i = 0; i<8; i++){
      for (int k = 0; k<8; k++){
        System.out.println("******");
        System.out.println("Adding at: " + i + ", " +k);
        example.addQueen(i,k);
        System.out.println(example.toStringDebug());
        System.out.println(example.toString());
        example.removeQueen(i,k);
        System.out.println(example.toStringDebug());
        System.out.println(example.toString());
        System.out.println("******");
      }
    }
    example.addQueen(4, 4);
    System.out.println(example.toStringDebug());
    System.out.println(example.toString());
    example.removeQueen(4, 4);
    System.out.println(example.toStringDebug());
    System.out.println(example.toString());
    */
    example.addQueen(4,4);
    example.addQueen(5,6);
    System.out.println(example.toStringDebug());
    example.removeQueen(5,6);
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
      return false; //nothing to remove
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
  public String toString(){
    String toReturn = "";
    for (int i = 0; i<board.length; i++){
      for (int k = 0; k<board.length; k++){
        if (board[i][k] == -1){
          toReturn += "Q ";
        }else{
          toReturn += "_ ";
        }
      }
      toReturn += "\n";
    }
    return toReturn;
  }

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
  public boolean solve() throws IllegalStateException{
    if (board[0][0] != 0){ //exception
      throw new IllegalStateException("You didn't input a empty board!");
    }
    if (board.length == 1){ //"trivial"
      board[0][0] = -1;
      return true;
    }
    if (board.length < 4){ //doesn't work
      return false;
    }else{
      return helpSolve(0,0);
    }
  }

//it shld be a helper solve method that is void, solve(int curR){}
//and it will use isSpace to scan through curR. IF there's a space, add to it. Continue to solve with curR+1
//if there isn't space, remove (find instance of -1 in board[curR-1]) previous queen and solve that row again
  private boolean helpSolve(int curR, int curC){
    if (curR >= board.length){
      return true;
    }else if (isSpace(curR, curC) != -1){
      addQueen(curR, curC);
      helpSolve(curR+1, curC+1);
    }else{
      int toRemove;
      for (int i = 0; i<board.length; i++){
        if board[curR-1][i] == -1;
        toRemove = i;
      }
      removeQueen[curR-1][toRemove];
      helpSolve(curR-1, toRemove+1);
    }if (curR == 0 and curC == board.length){
      return false;
    }
  }


  private int isSpace(int row, int start){ //for a given row, go thru all the columns. return first space that something can be put in
    for (int i = start; i<board.length; i++){
      if (board[row][i] == 0){
        return i;
      }
    }
    return -1;
  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  // public int countSolutions(){}

}
