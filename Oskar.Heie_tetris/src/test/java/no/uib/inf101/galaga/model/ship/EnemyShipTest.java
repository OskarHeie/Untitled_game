package no.uib.inf101.galaga.model.ship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;
import no.uib.inf101.grid.GridCell;

public class EnemyShipTest {

    @Test
    public void getFrontPositionTest() {

        EnemyShip ship = EnemyShip.newEnemyShip();
        ship = ship.shiftedBy(10, 10);
        System.out.println(ship.getFrontPosition());
        assertTrue(ship.getFrontPosition().equals(new CellPosition(15, 12)));
    }

    @Test
    public void shiftedToRightMiddleTest() {
        EnemyShip ship = EnemyShip.newEnemyShip();
        Grid<Character> gd = new Grid<>(22, 11);
        ship = ship.shiftedToRightMiddle(gd, null);

        List<GridCell<Character>> shipCells = new ArrayList<>();
		for (GridCell<Character> gc : ship) {
			shipCells.add(gc);
			System.out.println(gc.pos());
		}

        assertFalse(ship.movingRight());
        assertEquals(11, shipCells.size());
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(11, 12), 'E')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(10, 12), 'E')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(9, 12), 'E')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(12, 12), 'E')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(13, 12), 'E')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(10, 11), 'E')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(10, 10), 'E')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(10, 13), 'E')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(10, 14), 'E')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(11, 11), 'E')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(11, 13), 'E')));

    }

    @Test
    public void shiftedToLeftMiddleTest() {
        EnemyShip ship = EnemyShip.newEnemyShip();
        Grid<Character> gd = new Grid<>(20, 11);
        ship = ship.shiftedToLeftMiddle(gd, null);

        List<GridCell<Character>> shipCells = new ArrayList<>();
		for (GridCell<Character> gc : ship) {
			shipCells.add(gc);
			System.out.println(gc.pos());
		}

        assertTrue(ship.movingRight());
        assertEquals(11, shipCells.size());
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(11, -2), 'E')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(10, -2), 'E')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(9, -2), 'E')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(12, -2), 'E')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(13, -2), 'E')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(10, -1), 'E')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(10, 0), 'E')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(10, -3), 'E')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(10, -4), 'E')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(11, -1), 'E')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(11, -3), 'E')));
    }

}

