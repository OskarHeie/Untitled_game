package no.uib.inf101.galaga.view;

import java.awt.Color;

public class DefaultColorTheme implements ColorTheme {

  @Override
  public Color getCellColor(Character c) {
    Color color = switch (c) {
    case 'P' -> Color.BLUE;
    case 'E' -> Color.ORANGE;
    case 'M' -> Color.WHITE;
    case '-' -> Color.BLACK;
    default -> throw new IllegalArgumentException("No available color for '" + c + "'");
    };
    return color;
  }

  @Override
  public Color getFrameColor() {
    return new Color(0, 0, 0, 0);
  }

  @Override
  public Color getBackgroundColor() {
    return null;
  }

  @Override
  public Color getPopUpColor() {
    return new Color(0, 0, 0, 128);
  }

}
