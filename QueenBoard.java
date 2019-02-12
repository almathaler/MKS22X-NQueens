public class QueenBoard{
  private int[][]board;

  public static void main(String[] args){
    /*
    QueenBoard example = new QueenBoard(Integer.parseInt(args[0]));
    example.solve();
    System.out.println(example.toStringDebug());
    System.out.println(example.toString());
    */

    for (int i = 1; i<12; i++){
      QueenBoard example = new QueenBoard(i);
      example.solve();
      System.out.println(example.toStringDebug());
      System.out.println(example.toString());
    }

    QueenBoard example = new QueenBoard(8);
    System.out.println("Count for board size 8: (should be 92) " + example.countSolutions());
  }

  public QueenBoard(int size){
    board = new int[size][size];
    for (int i = 0; i<size; i++){
      for (int k = 0; k<size; k++){
        board[i][k] = 0;
      }
    }
  }
  private int getSize(){
    return board.length;
  }
  private boolean canPlace(int r, int c){
    //System.out.println("***CAN PLACE CHECKING IF "  + r + ", " + c + "IS FINE. THIS IS IT'S VALUE: " + board[r][c]);
    return board[r][c] == 0;
  }
  private int getVal(int r, int c){
    return board[r][c];
  }
  private boolean addQueen(int r, int c){
    if (board[r][c] != 0){
      return false; //there's already a queen
    }
    board[r][c] = -1;
    /*
    for (int upRow = 0; upRow<r; upRow++){
      board[upRow][c] += 1;
    }
    */
    for (int downRow = r+1; downRow<board.length; downRow++){
      board[downRow][c] += 1;
    }
    /*
    for (int leftCol = 0; leftCol<c; leftCol++){
      board[r][leftCol] += 1;
    }
    for (int rightCol = c+1; rightCol <board.length; rightCol++){
      board[r][rightCol] +=1;
    }
    */
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
    /*
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
    */
    return true;
    //if at col 3 and row 4, you can do +3+3, +2+2, +1+1 as your 3 diagonals that are above the QueenBoard
  };//PROBLEM IS THAT ADDING A QUEEN WILL ADD 1 TO THE QUEENS THAT ARE ELSEWHERE THAT IS A PROBLEM!!! THATS
  private boolean removeQueen(int r, int c){
    if (board[r][c] != -1){
      return false; //nothing to remove
    }
    board[r][c] = 0;
    /*
    for (int upRow = 0; upRow<r; upRow++){
      board[upRow][c] -= 1;
    }
    */
    for (int downRow = r+1; downRow<board.length; downRow++){
      board[downRow][c] -= 1;
    }
    /*
    for (int leftCol = 0; leftCol<c; leftCol++){
      board[r][leftCol] -= 1;
    }
    for (int rightCol = c+1; rightCol <board.length; rightCol++){
      board[r][rightCol] -=1;
    }
    */
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
    /*
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
    */
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
    for (int i = 0; i<board.length; i++){
      for (int k = 0; k<board.length; k++){
        if (board[i][k] != 0){
          throw new IllegalStateException("you need to use an object with an empty board!");
        }
      }
    }
    /*
    if (board.length == 1){ //"trivial"
      board[0][0] = -1;
      return true;
    }
    if (board.length < 4){ //doesn't work
      return false;
    */
    return helpSolve(0);

      //return false;
  }

//it shld be a helper solve method that is void, solve(int curR){}
//and it will use isSpace to scan through curR. IF there's a space, add to it. Continue to solve with curR+1
//if there isn't space, remove (find instance of -1 in board[curR-1]) previous queen and solve that row again
  private boolean helpSolve(int curR){
    if (curR >= board.length){
      return true; //if you've hit the end, you're true
    }
    for (int c = 0; c<board.length; c++){ //for every column in a row
      if (addQueen(curR, c)){ //check to see if you can add a queen there
        if (helpSolve(curR+1)){ //if you can, then go on to checking for all other possibilites if u can add a queen
          return true; //return true if you get to curR >= board.length, then you won't have to remove the queen
        }
        removeQueen(curR, c);
      }
    }
    return false;
  }
  /*
  private boolean helpSolve(int curR, QueenBoard temp){
    //base case is when curR >= board.length -- means you've filled up all the board
    //also if you've gotten to the end of the first row (as in, gone thru every possible first position due to a lot of removes, you've failed to complete the board. shouldn't really fail tho)
    //MAYBE DO SOMETHING THAT IS LIKE WITH A LOOP, FOR EVERY SPOT IN CURR RUN HELPSOLVE AND IF IT RUNS UNTIL A SPACE ON EVERY ROW IS FULL
    //THEN RETURN TRUE
    if (curR >= temp.getSize()){
      //System.out.println("****FOUND A WORKING BOARD****");
      //System.out.println("****LOOKS LIKE**** \n" + temp.toStringDebug());
      for (int k = 0; k<getSize(); k++){
        for (int z = 0; z<getSize(); z++){
          board[k][z] = temp.getVal(k, z);
        }
      }
      return true;
    }else{
      for (int i = 0 ; i < temp.getSize(); i++){
        if (temp.canPlace(curR, i)){
          temp.addQueen(curR, i);
          //System.out.println("****LOOKS LIKE**** \n" + temp.toStringDebug());
          if (helpSolve(curR+1, temp) == false){//will run even if it's ok, if it's ok don't do anything
            temp.removeQueen(curR, i);
            //System.out.println("****REMOVING WHAT WAS JUST PLACED BC NO SOLUTIONS PASSED THAT");
            //System.out.println("****CURRENTLY LOOKS LIKE: \n" + temp.toStringDebug());
          }
        }
      }
      return false;
    }
    */


    /*
    if (curR >= board.length){
      return true;
    }else if (curR == 0 && curC == board.length+1){//should be +1 or not?
      return false;
    }else if (isSpace(curR, curC) != -1){
      curC = isSpace(curR, curC);
      addQueen(curR, curC);
      System.out.println("added Queen to (" + curR +", " + curC + ")");
      System.out.println(toString());
      helpSolve(curR+1, curC);
    }else{
      int toRemove = 0;//BEFORE I JUST HAD INT TOREMOVE; THAT MIGHT BE HOW IT SOULD BE NOW TOO IDK
      for (int i = 0; i<board.length; i++){
        if (board[curR-1][i] == -1){
          toRemove = i;
        }
      }
      curR--;
      removeQueen(curR, toRemove);
      System.out.println("removed Queen from (" + curR +", " + toRemove + ")");
      System.out.println(toString());

      int lastToExclude = 0;
      for (int i = 0; i<board.length; i++){
        if (board[curR-1][i] == -1){
          lastToExclude = i;
        }
      }

      helpSolve(curR, toRemove);
    }
    return false;
  }
    */

  //exclude is supposed to be either previous space of queen, if you removed, or -1 meaning just check based on other queen's paths
  /*
  private int isSpace(int row, int exclude){ //for a given row, go thru all the columns. return first space that something can be put in
    for (int i = 0; i<board.length; i++){
      if (board[row][i] == 0 && exclude != -1 && i != exclude){
        return i;
      }// PROBLEM WITH THIS IS THAT YOU NEED A LIST OF EVERYTHING TO EXCLUDE, OTHERWISE IT WILL GO LIKE 0 THEN 1 THEN REMOVE 1, 1 IS EXCLUDE SO U CAN STILL DO 0
    }
    return -1;
  }
  */
  private void removeQueenS(int fromHereOn){
    for (int i = fromHereOn; i<board.length; i++){
      for (int k = 0; k<board.length; k++){
        if (board[i][k] == -1){
          removeQueen(i, k);
        }
      }
    }
  }
  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    for (int i = 0; i<board.length; i++){
      for (int k = 0; k<board.length; k++){
        if (board[i][k] != 0){
          throw new IllegalStateException("you need to use an object with an empty board!");
        }
      }
    }
    return helpCount(0, 0);
  }
  //note: emory helped me a lot
  private int helpCount(int r, int queens){
    int toReturn = 0;
    //^this will count how many good SOLUTIONS
    //V this counts how many queens we r currently dealing w. toReturn should only update once parameter queens is n
    if(queens == board.length){
      return 1; //if you have n queens, return 1. by end of recursion, you will have added 1 to toReturn as many times as # of solutions you hit
    }//^this is what gets toReturn to update, for every time u hit n queens you will add 1 to toReturn. initial
    //call to helpCount is from 0,0,0 so it can't return 1, only add to its toReturn every addition of 1 correct solution
    for(int curC = 0; curC < board.length; curC++){ //go thru every column for the row you're on
      if(addQueen(r, curC)){ //see if you can add a queen to this column
        //System.out.println("Adding queen to (" + r + ", " + curC + ")");
        //System.out.println(toString() + "\n" + toStringDebug());
        //System.out.println("There are currently " + (queens+1) + " queens on the board");
        toReturn += helpCount(r+1, queens+1); //if you can, go thru all possibilities of other combos
      }//when you could successfully add from that point, toReturn gains 1 solution
      //if you couldn't add, toReturn is given 0,
      removeQueen(r, curC);
    }
    //
    return toReturn;
    /*
    System.out.println("At the start of a new helpCount call");
    if (curR >= board.length){
      return total; //if you've hit the end, you're true
    }
    //for (int c = 0; c<board.length; c++){ //for every column in a row
    if (addQueen(curR, curC)){
      //System.out.println("added Queen");
      System.out.println("was able to add to " + curR + ", " + curC);
      if (helpSolve(curR+1)){
        System.out.println("a solution exists from this point, it's: ");
        System.out.println(toString() + "\n" + toStringDebug());
        if (curC == board.length-1){
          removeQueenS(curR+1); //if you're not finished checking all possibilites in this row, delete what u have so u can keep checking
        }else{
          removeQueenS(curR); //if you've looked at everything,
        }
        // delete everything helpSolve added after curR
        System.out.println("cleared what was added so we can check next block over")
        if (curC == board.length-1){
          return helpCount(curR+1, 0, total+1);
        }
        return helpCount(curR, curC+1, total+1);
      }
    }
    if (curC == board.length-1){
      return helpCount(curR+1, 0, total);
    }
    return helpCount(curR, curC+1, total);

      System.out.println("In the for loop for " + curR);
      System.out.println("Board looks like: ");
      System.out.println(toString());
      System.out.println(toStringDebug());
      if (addQueen(curR, c)){ //check to see if you can add a queen there
        //System.out.println("added Queen");
        System.out.println("checking spot " + c + ", was able to add to " + curR + ", " + c);
        if (helpSolve(curR+1)){ //if you can, then go on to checking for all other possibilites if u can add a queen
        //  System.out.println("called helpCount(" + (curR+1) + ", " + (total+1) + ")");
          //removeQueenS(curR+1);//don't want helpSolve to fill everything up
          System.out.println("Should just have removed queens, looks now like: ");
          System.out.println(toString() + "\n" + toStringDebug());
          System.out.println("a solution exists from that spot, total is now: ");
          total++; //return true if you get to curR >= board.length, then you won't have to remove the queen
          System.out.println(total);
          //System.out.println("Called helpCount again");
        }
      //  System.out.println("Can't solve from there, removing queen: ");
        removeQueen(curR, c);
        System.out.println("removing queen from: " + curR + ", " + c);
      }
      */
    //}
    //System.out.println("called helpCount again with " + (curR+1) + ", " + total);
    //return helpCount(curR+1, total);
    //return total;
    //return false;
    //what do i put here?
  }

}
