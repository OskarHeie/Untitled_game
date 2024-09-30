package no.uib.inf101.galaga.controller;

import no.uib.inf101.galaga.model.GameState;

public interface ControllableGalagaModel {
  
  /**
  * Method to help move around the ship on the board
  * The new position of the ship needs to be within bounds of the board
  *
  * @param deltaCol number of columns to move the ship
  * @return true if the move happened, otherwise return false
  */
  boolean moveShip(int deltaCol);
  
  /**
  * Tells us the state of the game
  *
  * @return the state of the game
  */
  GameState getGameState();
  
  /**
  * Get the time between each clock tick in ms
  *
  * @return time in ms
  */
  int dropRate();
  
  /**
  * The method will be called each time the clock ticks.
  * This means potentially spawning enemies, moving enemies,
  * moving missiles and checking if missiles hit.
  */
  void clockTick();
  
  /**
  * Method to make a ship fire a missile. Will be moved
  * by clock tick
  * 
  * @return true if a missile was fired and false if it was not
  */
  boolean shoot();
  
  /**
  * Method to change game state to active game
  * 
  */
  void startGame();
  
}