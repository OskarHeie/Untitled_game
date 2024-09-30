package no.uib.inf101.galaga.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import no.uib.inf101.galaga.model.ship.Ship;
import no.uib.inf101.galaga.model.ship.ShipFactory;
import no.uib.inf101.galaga.model.ship.VariableShipFactory;
import no.uib.inf101.galaga.view.ViewableGalagaModel;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;

public class GalagaModelTest {

	@Test
	public void clockTickMoveMissileTest() {
		GalagaBoard board = new GalagaBoard(20, 9);
		ShipFactory factory = new VariableShipFactory();
		GalagaModel model = new GalagaModel(board, factory);
		model.shoot();
		model.clockTick();

		List<GridCell<Character>> shipCells = new ArrayList<>();
		Ship missile = (Ship) model.getMissiles().get(0);
		for (GridCell<Character> gc : missile) {
			shipCells.add(gc);
			System.out.println(gc);
		}

		assertTrue(shipCells.contains(new GridCell<>(new CellPosition(9, 4), 'M')));
		assertTrue(shipCells.contains(new GridCell<>(new CellPosition(10, 4), 'M')));
	}

	@Test
	public void clockTickEnemyTest() {
		GalagaBoard board = new GalagaBoard(80, 67);
		ShipFactory factory = new VariableShipFactory();
		GalagaModel model = new GalagaModel(board, factory);
		int i = 0;
		while (i < 15) {
			model.clockTick();
			i += 1;
		}
		//Enemy should not have spawned yet
		assertEquals(model.getEnemies().size(), 0);

		//Spawns enemy and checks if the backmost square in the ship is correctly placed
		model.clockTick();
		assertEquals(model.getEnemies().size(), 1);
		List<GridCell<Character>> shipCells = new ArrayList<>();
		Ship ship = (Ship) model.getEnemies().get(0);
		for (GridCell<Character> gc : ship) {
			shipCells.add(gc);
			System.out.println(gc);
		}
		assertEquals(11, shipCells.size());
		assertTrue(shipCells.contains(new GridCell<>(new CellPosition(39, -2), 'E'))
					|| shipCells.contains(new GridCell<>(new CellPosition(39, 69), 'E')));

		//Does one move for the enemy ship and checks if backmost square is correctly placed
		model.clockTick();
		model.clockTick();
		List<GridCell<Character>> shipCells2 = new ArrayList<>();
		Ship ship2 = (Ship) model.getEnemies().get(0);
		System.out.println();
		for (GridCell<Character> gc : ship2) {
			shipCells2.add(gc);
			System.out.println(gc);
		}
		assertEquals(11, shipCells2.size());
		assertTrue(shipCells2.contains(new GridCell<>(new CellPosition(38, -2), 'E'))
					|| shipCells2.contains(new GridCell<>(new CellPosition(38, 69), 'E')));

		//Does one move for the enemy ship and checks if backmost square is correctly placed
		model.clockTick();
		model.clockTick();
		List<GridCell<Character>> shipCells3 = new ArrayList<>();
		Ship ship3 = (Ship) model.getEnemies().get(0);
		System.out.println();
		for (GridCell<Character> gc : ship3) {
			shipCells3.add(gc);
			System.out.println(gc);
		}
		assertEquals(11, shipCells3.size());
		assertTrue(shipCells3.contains(new GridCell<>(new CellPosition(38, -1), 'E'))
					|| shipCells3.contains(new GridCell<>(new CellPosition(38, 68), 'E')));

	}

	@Test
	public void livesLostTest() {
		GalagaBoard board = new GalagaBoard(80, 67);
		ShipFactory factory = new VariableShipFactory();
		GalagaModel model = new GalagaModel(board, factory);

		while (model.getLives() == 3) {
			model.clockTick();
		}
		assertEquals(model.getLives(), 2);
	}


	//This test could fail without anything beeing wrong if ships dont spawn
	//on the right side as this is random, but higly unlikely
	@Test
	public void scoreGainedTest() {
		GalagaBoard board = new GalagaBoard(80, 67);
		ShipFactory factory = new VariableShipFactory();
		GalagaModel model = new GalagaModel(board, factory);

		assertTrue(model.moveShip(30));
		while (model.getScore() == 0) {
			model.clockTick();
			model.shoot();
		}
		assertEquals(model.getScore(), 100);
	}

	@Test
	public void playerShipStartTest() {
		GalagaBoard board = new GalagaBoard(20,9);
		ShipFactory factory = new VariableShipFactory();
		ViewableGalagaModel model = new GalagaModel(board, factory);

		List<GridCell<Character>> shipCells = new ArrayList<>();
		for (GridCell<Character> gc : model.getPlayerShip()) {
			shipCells.add(gc);
			System.out.println(gc.pos());
		}

		assertEquals(13, shipCells.size());
		assertTrue(shipCells.contains(new GridCell<>(new CellPosition(17, 4), 'P')));
		assertTrue(shipCells.contains(new GridCell<>(new CellPosition(16, 4), 'P')));
		assertTrue(shipCells.contains(new GridCell<>(new CellPosition(15, 4), 'P')));
		assertTrue(shipCells.contains(new GridCell<>(new CellPosition(14, 4), 'P')));
		assertTrue(shipCells.contains(new GridCell<>(new CellPosition(13, 4), 'P')));
		assertTrue(shipCells.contains(new GridCell<>(new CellPosition(16, 3), 'P')));
		assertTrue(shipCells.contains(new GridCell<>(new CellPosition(15, 3), 'P')));
		assertTrue(shipCells.contains(new GridCell<>(new CellPosition(14, 3), 'P')));
		assertTrue(shipCells.contains(new GridCell<>(new CellPosition(16, 5), 'P')));
		assertTrue(shipCells.contains(new GridCell<>(new CellPosition(15, 5), 'P')));
		assertTrue(shipCells.contains(new GridCell<>(new CellPosition(14, 5), 'P')));
		assertTrue(shipCells.contains(new GridCell<>(new CellPosition(15, 2), 'P')));
		assertTrue(shipCells.contains(new GridCell<>(new CellPosition(15, 6), 'P')));
	}

	@Test
	public void moveShipLegalTest() {

		GalagaBoard board = new GalagaBoard(20,9);
		ShipFactory factory = new VariableShipFactory();
		GalagaModel model = new GalagaModel(board, factory);
		assertTrue(model.moveShip(-1));
		assertTrue(model.moveShip(-1));
		assertTrue(model.moveShip(2));
		assertTrue(model.moveShip(-1));
		
		List<GridCell<Character>> shipCells = new ArrayList<>();
		for (GridCell<Character> gc : model.getPlayerShip()) {
			shipCells.add(gc);
			System.out.println(gc.pos());
		}

		assertEquals(13, shipCells.size());
		
		assertTrue(shipCells.contains(new GridCell<>(new CellPosition(17, 3), 'P')));
		assertTrue(shipCells.contains(new GridCell<>(new CellPosition(13, 3), 'P')));
		assertTrue(shipCells.contains(new GridCell<>(new CellPosition(15, 1), 'P')));
		assertTrue(shipCells.contains(new GridCell<>(new CellPosition(15, 5), 'P')));

	}

	@Test
	public void moveShipillegalTest() {

		GalagaBoard board = new GalagaBoard(20,9);
		ShipFactory factory = new VariableShipFactory();
		GalagaModel model = new GalagaModel(board, factory);
		assertFalse(model.moveShip(-5));
		assertFalse(model.moveShip(10));
		assertFalse(model.moveShip(-10));

	}

	@Test
	public void moveShipReturnsTest() {

		GalagaBoard board = new GalagaBoard(20,9);
		ShipFactory factory = new VariableShipFactory();
		GalagaModel model = new GalagaModel(board, factory);
		assertTrue(model.moveShip(-1));
		assertTrue(model.moveShip(1));
		
		List<GridCell<Character>> shipCells = new ArrayList<>();
		for (GridCell<Character> gc : model.getPlayerShip()) {
			shipCells.add(gc);
			System.out.println(gc.pos());
		}

		assertEquals(13, shipCells.size());
		
		assertTrue(shipCells.contains(new GridCell<>(new CellPosition(17, 4), 'P')));
		assertTrue(shipCells.contains(new GridCell<>(new CellPosition(13, 4), 'P')));
		assertTrue(shipCells.contains(new GridCell<>(new CellPosition(15, 2), 'P')));
		assertTrue(shipCells.contains(new GridCell<>(new CellPosition(15, 6), 'P')));

	}

	@Test
	public void shootTest() {
		GalagaBoard board = new GalagaBoard(20,9);
		ShipFactory factory = new VariableShipFactory();
		GalagaModel model = new GalagaModel(board, factory);
		model.shoot();
		

		List<GridCell<Character>> shipCells = new ArrayList<>();
		Ship missile = (Ship) model.getMissiles().get(0);
		for (GridCell<Character> gc : missile) {
			shipCells.add(gc);
			System.out.println(gc);
		}

		assertTrue(shipCells.contains(new GridCell<>(new CellPosition(11, 4), 'M')));
		assertTrue(shipCells.contains(new GridCell<>(new CellPosition(12, 4), 'M')));

	}

	

}
