package no.uib.inf101.galaga.model.ship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;

public class Ship implements Iterable<GridCell<Character>> {

  private char symbol;
  private boolean[][] shape;
  private CellPosition pos;

  Ship(char symbol, boolean[][] shape, CellPosition pos) {
    this.symbol = symbol;
    this.shape = shape;
    this.pos = pos;
  }

  /**
   * A function to move a ship on a grid determined by the input
   * 
   * @param deltaRow how many rows the ship should move
   * @param deltaCol how many cols the ship should move
   * @return an identical ship except it's coordinates are changed
   */
  public Ship shiftedBy(int deltaRow, int deltaCol) {
    CellPosition newPos = new CellPosition(pos.row() + deltaRow, pos.col() + deltaCol);
    return new Ship(symbol, shape, newPos);
  }

  char symbol() {
    return this.symbol;
  }

  boolean[][] shape() {
    return this.shape;
  }

  CellPosition pos() {
    return this.pos;
  }

  @Override
  public Iterator<GridCell<Character>> iterator() {
    List<GridCell<Character>> list = new ArrayList<>();
    for (int i = 0; i < shape.length; i++) {
      for (int j = 0; j < shape[0].length; j++) {
        if (shape[i][j]) {
          CellPosition pos = new CellPosition(this.pos.row() + i, this.pos.col() + j);
          list.add(new GridCell<Character>(pos, symbol));
        }
      }
    }
    return list.iterator();
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Ship)) {
      return false;
    }
    Ship tetromino = (Ship) o;
    return symbol == tetromino.symbol && Arrays.deepEquals(shape, tetromino.shape)
        && Objects.equals(pos, tetromino.pos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.symbol, Arrays.deepHashCode(this.shape), this.pos);
  }

}
