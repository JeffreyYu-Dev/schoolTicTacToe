// Hours spent: 20 minutes

public class Data {

  // variables
  private String config;
  private int score;

  // constructor
  public Data(String config, int score) {
    this.config = config;
    this.score = score;
  };

  // return the configuration of board
  public String getConfiguration() { return this.config; }

  // return the score of the board configuration
  public int getScore() { return this.score; }
}
