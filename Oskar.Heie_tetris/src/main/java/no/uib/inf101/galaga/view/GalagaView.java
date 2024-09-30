package no.uib.inf101.galaga.view;

import javax.swing.JPanel;

import no.uib.inf101.galaga.model.GameState;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class GalagaView extends JPanel {

  public static final int OUTERMARGIN = 15;
  public static final int CELLMARGIN = 0;
  public static final int PREFERREDSIDESIZE = 8;

  private ViewableGalagaModel viewableGalagaModel;
  private ColorTheme colorTheme;

  public GalagaView(ViewableGalagaModel viewableGalagaModel) {
    this.viewableGalagaModel = viewableGalagaModel;

    this.colorTheme = new DefaultColorTheme();
    this.setBackground(colorTheme.getBackgroundColor());

    this.setFocusable(true);
    this.setPreferredSize(getDefaultSize(viewableGalagaModel.getDimension()));
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    drawGame(g2);
  }

  private void drawGame(Graphics2D g2) {
    Rectangle2D box = new Rectangle2D.Double(OUTERMARGIN, OUTERMARGIN, this.getWidth() - OUTERMARGIN * 2,
        this.getHeight() - OUTERMARGIN * 2);
    g2.setColor(null);
    g2.fill(box);

    CellPositionToPixelConverter converter = new CellPositionToPixelConverter(box, viewableGalagaModel.getDimension(),
        CELLMARGIN);

    drawCells(g2, viewableGalagaModel.getTilesOnBoard(), converter, colorTheme);
    drawCells(g2, viewableGalagaModel.getPlayerShip(), converter, colorTheme);
    for (Iterable<GridCell<Character>> ship : viewableGalagaModel.getEnemies()) {
      drawCells(g2, ship, converter, colorTheme);
    }
    for (Iterable<GridCell<Character>> ship : viewableGalagaModel.getMissiles()) {
      drawCells(g2, ship, converter, colorTheme);
    }
    drawScoreLives(g2);

    if (viewableGalagaModel.getGameState() == GameState.GAME_START) {
      drawGameStart(g2);
    }

    if (viewableGalagaModel.getGameState() == GameState.GAME_OVER) {
      drawGameOver(g2);
    }
  }

  // Helper method to draw score and lives at the top of the screen
  private void drawScoreLives(Graphics2D g2) {
    Rectangle2D box1 = new Rectangle2D.Double(OUTERMARGIN, OUTERMARGIN, this.getWidth() / 2 - OUTERMARGIN,
        this.getHeight() / 8 - OUTERMARGIN);
    g2.setColor(colorTheme.getPopUpColor());
    g2.fill(box1);

    g2.setColor(Color.WHITE);
    g2.setFont(new Font("Monospaced", Font.BOLD, 20));
    Inf101Graphics.drawCenteredString(g2, "Lives: " + viewableGalagaModel.getLives(), box1);

    Rectangle2D box2 = new Rectangle2D.Double(this.getWidth() / 2, OUTERMARGIN, this.getWidth() / 2 - OUTERMARGIN,
        this.getHeight() / 8 - OUTERMARGIN);
    g2.setColor(colorTheme.getPopUpColor());
    g2.fill(box2);

    g2.setColor(Color.WHITE);
    g2.setFont(new Font("Monospaced", Font.BOLD, 20));
    Inf101Graphics.drawCenteredString(g2, "Score: " + viewableGalagaModel.getScore(), box2);

  }

  // Helper method to draw if game has not started yet
  private void drawGameStart(Graphics2D g2) {
    Rectangle2D box = new Rectangle2D.Double(0, 0, this.getWidth(), this.getHeight());
    g2.setColor(colorTheme.getPopUpColor());
    g2.fill(box);

    g2.setColor(Color.WHITE);
    g2.setFont(new Font("Monospaced", Font.BOLD, 30));
    Inf101Graphics.drawCenteredString(g2, "Press Space To Start", getBounds());
  }

  // Helper method to draw game over
  private void drawGameOver(Graphics2D g2) {
    Rectangle2D box = new Rectangle2D.Double(0, 0, this.getWidth(), this.getHeight());
    g2.setColor(colorTheme.getPopUpColor());
    g2.fill(box);

    g2.setColor(Color.WHITE);
    g2.setFont(new Font("Monospaced", Font.BOLD, 50));
    Inf101Graphics.drawCenteredString(g2, "GAME OVER", getBounds());
  }

  // Helper method to draw cells on the board
  private static void drawCells(Graphics2D g2, Iterable<GridCell<Character>> iterable,
      CellPositionToPixelConverter converter, ColorTheme colorTheme) {
    for (GridCell<Character> cell : iterable) {
      CellPosition pos = cell.pos();
      Character c = cell.value();
      Rectangle2D tile = converter.getBoundsForCell(pos);

      g2.setColor(colorTheme.getCellColor(c));
      g2.fill(tile);
    }
  }

  private static Dimension getDefaultSize(GridDimension gd) {
    int width = (int) (PREFERREDSIDESIZE * gd.cols() + CELLMARGIN * (gd.cols() + 1) + 2 * OUTERMARGIN);
    int height = (int) (PREFERREDSIDESIZE * gd.rows() + CELLMARGIN * (gd.cols() + 1) + 2 * OUTERMARGIN);
    return new Dimension(width, height);
  }

}
