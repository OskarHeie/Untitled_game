package no.uib.inf101.galaga;

import javax.swing.JFrame;

import no.uib.inf101.galaga.controller.GalagaController;
import no.uib.inf101.galaga.model.GalagaBoard;
import no.uib.inf101.galaga.model.GalagaModel;
import no.uib.inf101.galaga.model.ship.ShipFactory;
import no.uib.inf101.galaga.model.ship.VariableShipFactory;
import no.uib.inf101.galaga.view.GalagaView;

public class GalagaMain {

  public static final String WINDOW_TITLE = "GALAGA";

  public static void main(String[] args) {
    GalagaBoard board = new GalagaBoard(80, 67);
    ShipFactory ShipFactory = new VariableShipFactory();
    GalagaModel model = new GalagaModel(board, ShipFactory);
    GalagaView view = new GalagaView(model);
    new GalagaController(model, view);

    JFrame frame = new JFrame(WINDOW_TITLE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.setContentPane(view);

    frame.pack();
    frame.setVisible(true);
  }

}
