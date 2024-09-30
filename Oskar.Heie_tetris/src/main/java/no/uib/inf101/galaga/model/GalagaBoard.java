package no.uib.inf101.galaga.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;

public class GalagaBoard extends Grid<Character> {

  public GalagaBoard(int rows, int cols) {
    super(rows, cols, '-');
  }

  /**
   * A string representation of the board in a pretty way For test purposes
   *
   * @return a string representation of the board
   */
  public String prettyString() {
    String pretty = "";
    for (int i = 0; i < this.rows(); i++) {
      for (int j = 0; j < this.cols(); j++) {
        pretty += this.get(new CellPosition(i, j));
      }
      pretty += "\n";
    }
    return pretty.strip();
  }

}
