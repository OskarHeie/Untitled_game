package no.uib.inf101.galaga.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import no.uib.inf101.galaga.model.GameState;
import no.uib.inf101.galaga.view.GalagaView;

public class GalagaController implements KeyListener {
  
  private ControllableGalagaModel model;
  private GalagaView view;
  private Timer timer;
  private static long lastFiredTime;
  
  public GalagaController(ControllableGalagaModel controllableModel, GalagaView view) {
    this.model = controllableModel;
    this.view = view;
    
    view.addKeyListener(this);
    
    lastFiredTime = 0;
    this.timer = new Timer(model.dropRate(), this::clockTick);
    timer.start();
  }
  
  @Override
  public void keyPressed(KeyEvent e) {
    if (model.getGameState() == GameState.GAME_OVER)
    return;
    
    else if (model.getGameState() == GameState.GAME_START) {
      if (e.getKeyCode() == KeyEvent.VK_SPACE) {
        model.startGame();
      }
      return;
    }
    
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      model.moveShip(-2);
    }
    else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      model.moveShip(2);
    }
    else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      long currentTime = System.currentTimeMillis();
      if (currentTime - lastFiredTime >= 500) {
        model.shoot();
        lastFiredTime = currentTime;
      }
    }
    view.repaint();
  }
  
  /**
  * Method to be called every so often given by timer
  * 
  * @param e
  */
  public void clockTick(ActionEvent e) {
    if (this.model.getGameState() == GameState.ACTIVE_GAME) {
      this.model.clockTick();
      this.view.repaint();
    }
  }
  
  @Override
  public void keyTyped(KeyEvent e) {
  }
  
  @Override
  public void keyReleased(KeyEvent e) {
  }
  
}
