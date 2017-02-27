package test;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import dungeons_and_dragons.model.GameMapModel;

/**
 * 
 * @author Shahida Map validation
 *
 */

public class Maptest {

	/**
	 * entry door validation considering all six cases
	 */
	Point map_size = new Point(5, 5);

	@Test
	public void test_setMap_entry_door() {

		// Given
		Point map_entry_door1 = new Point(0, 0);
		Point map_entry_door2 = new Point(0, 4);
		Point map_entry_door3 = new Point(3, 0);
		Point map_entry_door4 = new Point(4, 4);
		Point map_entry_door5 = new Point(4, 2);
		Point map_entry_door6 = new Point(2, 4);

		// When
		GameMapModel mapentry = new GameMapModel();
		mapentry.setMap_size(map_size);

		mapentry.setMap_entry_door(map_entry_door1);

		// Then
		Assert.assertEquals(mapentry.getMap_entry_door(), map_entry_door1);

		// When
		mapentry.setMap_entry_door(map_entry_door2);

		// Then
		Assert.assertEquals(mapentry.getMap_entry_door(), map_entry_door2);

		// When
		mapentry.setMap_entry_door(map_entry_door3);
		// Then
		Assert.assertEquals(mapentry.getMap_entry_door(), map_entry_door3);

		// When
		mapentry.setMap_entry_door(map_entry_door4);
		// Then
		Assert.assertEquals(mapentry.getMap_entry_door(), map_entry_door4);

		// When
		mapentry.setMap_entry_door(map_entry_door5);
		// Then
		Assert.assertEquals(mapentry.getMap_entry_door(), map_entry_door5);

		// When
		mapentry.setMap_entry_door(map_entry_door6);

		// Then
		Assert.assertEquals(mapentry.getMap_entry_door(), map_entry_door6);

	}

	/**
	 * exit door validation considering all six cases
	 */
	@Test
	public void test_setMap_exit_door() {
		// Given
		Point map_exit_door1 = new Point(0, 0);
		Point map_exit_door2 = new Point(0, 4);
		Point map_exit_door3 = new Point(3, 0);
		Point map_exit_door4 = new Point(4, 4);
		Point map_exit_door5 = new Point(4, 2);
		Point map_exit_door6 = new Point(2, 4);

		// When
		GameMapModel mapexit = new GameMapModel();
		mapexit.setMap_size(map_size);

		mapexit.setMap_exit_door(map_exit_door1);

		// Then
		Assert.assertEquals(mapexit.getMap_exit_door(), map_exit_door1);

		// When
		mapexit.setMap_exit_door(map_exit_door2);

		// Then
		Assert.assertEquals(mapexit.getMap_exit_door(), map_exit_door2);

		// When
		mapexit.setMap_exit_door(map_exit_door3);

		// Then
		Assert.assertEquals(mapexit.getMap_exit_door(), map_exit_door3);

		// When
		mapexit.setMap_exit_door(map_exit_door4);

		// Then
		Assert.assertEquals(mapexit.getMap_exit_door(), map_exit_door4);
		// When
		mapexit.setMap_exit_door(map_exit_door5);

		// Then
		Assert.assertEquals(mapexit.getMap_exit_door(), map_exit_door5);
		// When
		mapexit.setMap_exit_door(map_exit_door6);

		// Then
		Assert.assertEquals(mapexit.getMap_exit_door(), map_exit_door6);

	}

	/**
	 * wall test
	 */
	@Test
	public void test_setMap_wall() {
		// Given
		GameMapModel setmap = new GameMapModel();
		Point mapwall1 = new Point(3, 3);
		Point mapwall2 = new Point(2, 3);
		Point mapwall3 = new Point(4, 3);
		Point mapwall4 = new Point(3, 3);

		ArrayList<Point> p;
		int c = 4;

		// When
		setmap.setMap_wall(mapwall1);
		setmap.setMap_wall(mapwall2);
		setmap.setMap_wall(mapwall3);
		setmap.setMap_wall(mapwall4);

		p = setmap.getMap_walls();

		System.out.println(p.size());
		// Then
		Assert.assertNotSame(c, p.size());

	}

	/**
	 * enemy test
	 */

	@Test
	public void test_setMap_enemy_loc() {
		// Given
		GameMapModel setmap_enemy = new GameMapModel();
		Point map_enemy_loc1 = new Point(3, 3);
		Point map_enemy_loc2 = new Point(2, 3);
		Point map_enemy_loc3 = new Point(4, 3);
		Point map_enemy_loc4 = new Point(3, 3);

		ArrayList<Point> p;
		int c = 4;

		// When
		setmap_enemy.setMap_enemy_loc(map_enemy_loc1);
		setmap_enemy.setMap_enemy_loc(map_enemy_loc2);
		setmap_enemy.setMap_enemy_loc(map_enemy_loc3);
		setmap_enemy.setMap_enemy_loc(map_enemy_loc4);

		p = setmap_enemy.getMap_enemy_loc();

		System.out.println(p.size());
		// Then
		Assert.assertNotSame(c, p.size());

	}

}
