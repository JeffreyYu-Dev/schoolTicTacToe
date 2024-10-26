// hours Spent: 2

public class Configurations {

  // variables
  private int board_size;
  private int lengthToWin;
  private int max_levels;

  // board
  char[][] board;

  public Configurations(int board_size, int lengthToWin, int max_levels) {

    // define variables to use in our class
    this.board_size = board_size;
    this.lengthToWin = lengthToWin;
    this.max_levels = max_levels;

    // initialize the board
    board = new char[board_size][board_size];
    for (int i = 0; i < board_size; i++) {
      for (int j = 0; j < board_size; j++) {
        this.board[i][j] = ' ';
      }
    }
  }

  // returns an empty HashDictionary
  public HashDictionary createDictionary() {

    // 7019 is my hash table size (test)

    return new HashDictionary(7019);
  }

  // This method first stores the
  // characters of the board in a String; then it checks
  // whether the String representing the board is in the hashTable: If the
  // String is in the hashTable this method returns its associated score,
  // otherwise it returns the value -1
  public int repeatedConfiguration(HashDictionary hashTable) {

    // turn the board into a string
    String config = stringifyBoard();

    // return score or -1 when not in the table
    return hashTable.get(config);
  }

  // This method first
  // represents the content of the board as a String as described above;
  // then it inserts this String and score in the hashDictionary.
  public void addConfiguration(HashDictionary hashDictionary, int score) {

    // turn board into string
    String config = stringifyBoard();

    // put data into the data object
    Data record = new Data(config, score);

    // add the record to the table
    hashDictionary.put(record);

    return;
  }

  // Stores a symbol in the board[row][col]
  public void savePlay(int row, int col, char symbol) {
    board[row][col] = symbol;
  }

  // returns true if board[row][col] is ’ ’
  // otherwise it returns false
  public boolean squareIsEmpty(int row, int col) {

    // check position in board[row][col] if empty
    if (board[row][col] == ' ') {
      return true;
    }

    // return false if not empty
    return false;
  }

  // Returns true if there is s of the kind symbol on the board,
  // where k is the length of a continuous sequence of length
  // at least k formed by the vertical or horizontal of diagonal line
  // needed to win the game

  // length to win >= length to win
  public boolean wins(char symbol) {

    // return true if any of these are valid
    if (checkHorizontally(symbol) || checkVertically(symbol) ||
        checkDiagonally(symbol)) {

      return true;
    }

    // return false when no squence is found or sufficient
    return false;
  }

  // check diagonally topLeft -> bottomRight and bottomLeft -> topRight
  // (THIS WAY IS MUCH EASIER)
  private boolean checkDiagonally(char symbol) {

    // top left -> bottom right
    for (int i = 0; i <= board_size - lengthToWin; i++) {
      for (int j = 0; j <= board_size - lengthToWin; j++) {
        boolean isFound = true;
        // find squence of k length
        for (int k = 0; k < lengthToWin; k++) {
          if (board[i + k][j + k] != symbol) {
            isFound = false;
            break;
          }
        }

        // if squence is good return true
        if (isFound) {
          return true;
        }
      }
    }

    // bottom left -> top right
    for (int i = lengthToWin - 1; i < board_size; i++) {
      for (int j = 0; j <= board_size - lengthToWin; j++) {
        boolean isFound = true;
        // find squence of k length
        for (int k = 0; k < lengthToWin; k++) {
          if (board[i - k][j + k] != symbol) {
            isFound = false;
            break;
          }
        }

        // if squence is good return true
        if (isFound) {
          return true;
        }
      }
    }

    // no diagonal wins
    return false;
  }

  // check vertically
  private boolean checkVertically(char symbol) {

    for (int i = 0; i <= board_size - lengthToWin; i++) {
      for (int j = 0; j <= board_size - lengthToWin; j++) {
        boolean isFound = true;
        // check if position is equal to symbol
        // check vertically

        // find squence of k length
        for (int k = 0; k < lengthToWin; k++) {
          if (board[i + k][j] != symbol) {
            isFound = false;
            break;
          }
        }

        // if squence is good return true
        if (isFound) {
          return true;
        }
      }
    }
    return false;
  }

  // check horizontally
  private boolean checkHorizontally(char symbol) {

    for (int i = 0; i <= board_size - lengthToWin; i++) {
      for (int j = 0; j <= board_size - lengthToWin; j++) {
        boolean isFound = true;
        // check if position is equal to symbol
        // check vertically

        // find squence of k length
        for (int k = 0; k < lengthToWin; k++) {
          if (board[i][j + k] != symbol) {
            isFound = false;
            break;
          }
        }

        // if squence is good return true
        if (isFound) {
          return true;
        }
      }
    }
    return false;
  }

  // Returns true if board has no empty positions left and no player has
  // won the game
  public boolean isDraw() {

    // checks if the board is empty
    for (int i = 0; i < board_size; i++) {
      for (int j = 0; j < board_size; j++) {
        if (board[i][j] == ' ') {
          return false;
        }
      }
    }

    // checks if a player has won
    char player = 'X';
    char computer = 'O';

    if (wins(computer) || wins(player)) {
      return false;
    }

    return true;
  }

  // returns one of the following values:
  // 3 if computer has won
  // 0 if human has won
  // 2 if the game is a tie
  // 1 if the game is still undecided; there are still empty positions in
  // board and no play has won yet

  public int evalBoard() {
    char player = 'X';
    char computer = 'O';

    if (wins(computer)) {
      // computer
      return 3;

    } else if (wins(player)) {
      // human
      return 0;

    } else if (isDraw()) {
      // draw
      return 2;

    } else {
      // undecided
      return 1;
    }
  }

  // turns the board into an string
  private String stringifyBoard() {

    String string = "";
    // board size in the context is the length of one sie of the square
    // turn the table into a string
    for (int i = 0; i < board_size; i++) {
      for (int j = 0; j < board_size; j++) {
        string += board[i][j];
      }
    }

    // return the resulting string
    return string;
  }
}
