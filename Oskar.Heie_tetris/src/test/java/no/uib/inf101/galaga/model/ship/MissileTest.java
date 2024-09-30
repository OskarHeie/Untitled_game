package no.uib.inf101.galaga.model.ship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;

public class MissileTest {

    @Test
    public void placeMissileEnemyTest() {
        Missile missile = Missile.newMissile();
        missile = missile.placeMissileEnemy(new CellPosition(0, 0), null);

        List<GridCell<Character>> shipCells = new ArrayList<>();
		for (GridCell<Character> gc : missile) {
			shipCells.add(gc);
			System.out.println(gc.pos());
		}

        assertFalse(missile.getFromPlayer());
        assertEquals(2, shipCells.size());
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(0, 0), 'M')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(1, 0), 'M')));
    }

    @Test
    public void placeMissilePlayerTest() {
        Missile missile = Missile.newMissile();
        missile = missile.placeMissilePlayer(new CellPosition(1, 0));

        List<GridCell<Character>> shipCells = new ArrayList<>();
		for (GridCell<Character> gc : missile) {
			shipCells.add(gc);
			System.out.println(gc.pos());
		}
        
        assertTrue(missile.getFromPlayer());
        assertEquals(2, shipCells.size());
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(0, 0), 'M')));
        assertTrue(shipCells.contains(new GridCell<>(new CellPosition(1, 0), 'M')));
    }
    
}
