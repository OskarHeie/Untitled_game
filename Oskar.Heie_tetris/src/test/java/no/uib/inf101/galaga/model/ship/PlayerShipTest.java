package no.uib.inf101.galaga.model.ship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;
import no.uib.inf101.grid.GridCell;


public class PlayerShipTest {


    @Test
    public void shiftedByTest() {

        PlayerShip ship = PlayerShip.newPlayerShip();
        ship = ship.shiftedBy(10, 10);

        List<GridCell<Character>> shipCells = new ArrayList<>();
		for (GridCell<Character> gc : ship) {
			shipCells.add(gc);
			System.out.println(gc.pos());
		}

        assertEquals(13, shipCells.size());
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(10, 12), 'P')));
		assertTrue(shipCells.contains(new GridCell<>(new CellPosition(11, 12), 'P')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(11, 11), 'P')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(11, 13), 'P')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(12, 12), 'P')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(12, 11), 'P')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(12, 13), 'P')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(12, 10), 'P')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(12, 14), 'P')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(13, 12), 'P')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(13, 11), 'P')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(13, 13), 'P')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(14, 12), 'P')));
    }

    @Test
    public void getMiddleAndFrontPositionTest() {

        PlayerShip ship = PlayerShip.newPlayerShip();
        ship = ship.shiftedBy(10, 10);
        assertTrue(ship.getMiddlePosition().equals(new CellPosition(12, 12)));
        assertTrue(ship.getFrontPosition().equals(new CellPosition(9, 12)));
    }

    @Test
    public void shiftedtoBottomMiddleTest() {

        PlayerShip ship = PlayerShip.newPlayerShip();
        Grid<Character> gd = new Grid<>(17, 24);
        ship = ship.shiftedToBottomCenterOf(gd);

        List<GridCell<Character>> shipCells = new ArrayList<>();
		for (GridCell<Character> gc : ship) {
			shipCells.add(gc);
			System.out.println(gc.pos());
		}

        assertEquals(13, shipCells.size());
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(10, 12), 'P')));
		assertTrue(shipCells.contains(new GridCell<>(new CellPosition(11, 12), 'P')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(11, 11), 'P')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(11, 13), 'P')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(12, 12), 'P')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(12, 11), 'P')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(12, 13), 'P')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(12, 10), 'P')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(12, 14), 'P')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(13, 12), 'P')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(13, 11), 'P')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(13, 13), 'P')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(14, 12), 'P')));
    }

    
}
