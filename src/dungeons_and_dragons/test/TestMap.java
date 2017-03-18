package dungeons_and_dragons.test;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import dungeons_and_dragons.controller.ManageMapController;
import dungeons_and_dragons.controller.MapGridController;
import dungeons_and_dragons.helper.MapButton;
import dungeons_and_dragons.helper.MapCharacter;
import dungeons_and_dragons.helper.MapValidator;
import dungeons_and_dragons.model.GameMapModel;
import dungeons_and_dragons.view.MapGridView;

import static org.junit.Assert.*;

/**
 * 
 * @author Shahida, Urmil & Tejas
 *
 */


public class TestMap {
	
	
		/**
		 * entry door validation considering all six cases
		 */
	
		Point map_size = new Point(5, 5);

		@Test
		public void testSetMapEntryDoor() {

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
			assertEquals(mapentry.getMap_entry_door(), map_entry_door1);

			// When
			mapentry.setMap_entry_door(map_entry_door2);

			// Then
			assertEquals(mapentry.getMap_entry_door(), map_entry_door2);

			// When
			mapentry.setMap_entry_door(map_entry_door3);
			// Then
			assertEquals(mapentry.getMap_entry_door(), map_entry_door3);

			// When
			mapentry.setMap_entry_door(map_entry_door4);
			// Then
			assertEquals(mapentry.getMap_entry_door(), map_entry_door4);

			// When
			mapentry.setMap_entry_door(map_entry_door5);
			// Then
			assertEquals(mapentry.getMap_entry_door(), map_entry_door5);

			// When
			mapentry.setMap_entry_door(map_entry_door6);

			// Then
			assertEquals(mapentry.getMap_entry_door(), map_entry_door6);

		}

		/**
		 * exit door validation considering all six cases
		 */
		@Test
		public void testSetMapExitDoor() {
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
			assertEquals(mapexit.getMap_exit_door(), map_exit_door1);

			// When
			mapexit.setMap_exit_door(map_exit_door2);

			// Then
			assertEquals(mapexit.getMap_exit_door(), map_exit_door2);

			// When
			mapexit.setMap_exit_door(map_exit_door3);

			// Then
			assertEquals(mapexit.getMap_exit_door(), map_exit_door3);

			// When
			mapexit.setMap_exit_door(map_exit_door4);

			// Then
			assertEquals(mapexit.getMap_exit_door(), map_exit_door4);
			// When
			mapexit.setMap_exit_door(map_exit_door5);

			// Then
			assertEquals(mapexit.getMap_exit_door(), map_exit_door5);
			// When
			mapexit.setMap_exit_door(map_exit_door6);

			// Then
			assertEquals(mapexit.getMap_exit_door(), map_exit_door6);

		}

		/**
		 * wall test
		 */
		@Test
		public void testSetMapWall() {
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
			assertNotSame(c, p.size());

		}

		/**
		 * enemy test
		 */

		@Test
		public void testSetMapEnemyLoc() {
			// Given
			GameMapModel setmap_enemy = new GameMapModel();
			ArrayList<MapCharacter> map_enemy_loc1 = new ArrayList<MapCharacter>() ;
			//Point map_enemy_loc1 = new Point(3, 3);
			Point map_enemy_loc2 = new Point(2, 3);
			Point map_enemy_loc3 = new Point(4, 3);
			Point map_enemy_loc4 = new Point(3, 3);

			ArrayList<MapCharacter> p;
			int c = 4;

			// When
			setmap_enemy.setMap_enemy_loc(map_enemy_loc1);
			//setmap_enemy.setMap_enemy_loc(map_enemy_loc2);
			//setmap_enemy.setMap_enemy_loc(map_enemy_loc3);
			//setmap_enemy.setMap_enemy_loc(map_enemy_loc4);

			p = setmap_enemy.getMap_enemy_loc();
			
			System.out.println(p.size());
			// Then
			assertNotSame(c, p.size());

		}
		
		/**
		 * entry - exit path validation
		 */
		@Test
		public void testEntryToExitDoor() {
			
			
			ArrayList<MapCharacter> map_enemy_loc = new ArrayList<MapCharacter>();
			ArrayList<Point> map_walls = new ArrayList<Point>();
			GameMapModel gameMapModel = new GameMapModel();
			
			//Given Data
			Point map_size1 = new Point(4,4);
			Point map_entry_door = new Point(0,0);
			Point map_exit_door = new Point(2,4);
			//map_enemy_loc.add(new Point(1,0));
			map_walls.add(new Point(0,1));
			map_walls.add(new Point(1,1));
			map_walls.add(new Point(2,1));
			//map_walls.add(new Point(3,1));
			
		/*	Point map_size1 = new Point(5,5);
			Point map_entry_door = new Point(1,0);
			Point map_exit_door = new Point(2,5);
			//map_enemy_loc.add(new Point(1,0));
			map_walls.add(new Point(0,1));
			map_walls.add(new Point(1,1));
			map_walls.add(new Point(2,1));
			//map_walls.add(new Point(3,1));
*/			
			//MapGridController mapGridController = new MapGridController();
			gameMapModel.setMap_enemy_loc(map_enemy_loc);
			gameMapModel.setMap_entry_door(map_entry_door);
			gameMapModel.setMap_exit_door(map_exit_door);
			gameMapModel.setMap_walls(map_walls);
			gameMapModel.setMap_size(map_size1);

			MapButton[][] maps = new MapButton[map_size1.x][map_size1.y];
			Point p = new Point();
			
			for (int i = 0; i < gameMapModel.getMap_size().x; i++) {
				for (int j = 0; j < gameMapModel.getMap_size().y; j++) {
					
					maps[i][j] = new MapButton();
					p.x = i;
					p.y = j;
					
					if(gameMapModel.getMap_walls().contains(p)){
						maps[i][j].setPointValue(0);
					} else if(gameMapModel.getMap_enemy_loc().contains(p)){
						maps[i][j].setPointValue(2);
					}
				}
			}
			
			MapGridView mapGridView = new MapGridView(gameMapModel);
			mapGridView.maps = maps;
			
			MapValidator mapValidator = new  MapValidator(mapGridView, gameMapModel);
			boolean expectedResult = mapValidator.findPath(gameMapModel.getMap_exit_door());
			
			
			
			assertEquals(expectedResult, true);
			

		}
		
		/**
		 * checks whether the enemies are present in the defined path or not
		 */
		@Test
		public void testEnenmyExists() {
			
			
			ArrayList<MapCharacter> map_enemy_loc = new ArrayList<MapCharacter>();
			ArrayList<Point> map_walls = new ArrayList<Point>();
			GameMapModel gameMapModel = new GameMapModel();
			int count = 0;
			boolean expectedResult = false;
			
			//Given Data
			/*Point map_size1 = new Point(4,4);
			Point map_entry_door = new Point(0,0);
			Point map_exit_door = new Point(2,4);
			map_enemy_loc.add(new Point(1,0));
			map_enemy_loc.add(new Point(3,0));
			map_walls.add(new Point(0,1));
			map_walls.add(new Point(1,1));
			map_walls.add(new Point(2,1));*/
			
			
			Point map_size1 = new Point(5,5);
			Point map_entry_door = new Point(1,0);
			Point map_exit_door = new Point(4,0);			
			map_enemy_loc.add(new MapCharacter());
			map_walls.add(new Point(0,1));
			map_walls.add(new Point(1,1));
			map_walls.add(new Point(2,1));
			map_walls.add(new Point(3,1));
			map_walls.add(new Point(4,1));
			
			//MapGridController mapGridController = new MapGridController();
			gameMapModel.setMap_enemy_loc(map_enemy_loc);
			gameMapModel.setMap_entry_door(map_entry_door);
			gameMapModel.setMap_exit_door(map_exit_door);
			gameMapModel.setMap_walls(map_walls);
			gameMapModel.setMap_size(map_size1);

			MapButton[][] maps = new MapButton[map_size1.x][map_size1.y];
			Point p = new Point();
			
			for (int i = 0; i < gameMapModel.getMap_size().x; i++) {
				for (int j = 0; j < gameMapModel.getMap_size().y; j++) {
					
					maps[i][j] = new MapButton();
					p.x = i;
					p.y = j;
					
					if(gameMapModel.getMap_walls().contains(p)){
						maps[i][j].setPointValue(0);
					} else if(gameMapModel.getMap_enemy_loc().contains(p)){
						maps[i][j].setPointValue(2);
					}
				}
				
			}
			
			MapGridView mapGridView = new MapGridView(gameMapModel);
			mapGridView.maps = maps;
			
			MapValidator mapValidator = new  MapValidator(mapGridView, gameMapModel);
			
			// functions to check if the enemies are in the defined path or not
			Set mapSet = new HashSet(gameMapModel.getMap_enemy_loc());
			Iterator mapIterator = mapSet.iterator();
			
			while (mapIterator.hasNext()) {
				Point x = (Point) mapIterator.next();
				if (new MapValidator(mapGridView, gameMapModel).findPath(x)) {
					count++;
				}
			}
			if(count == gameMapModel.getMap_enemy_loc().size() && count > 0){
				expectedResult = true;
			}			
			//assertEquals(expectedResult, true);
			assertNotEquals(expectedResult, true);
			

		}
		
		@Test
		public void testResetAll()
		{
			ArrayList<MapCharacter> map_enemy_loc = new ArrayList<MapCharacter>();
			ArrayList<Point> map_walls = new ArrayList<Point>();
			GameMapModel gameMapModel = new GameMapModel();
			
			//Given Data
			Point map_size1 = new Point(4,4);
			Point map_entry_door = new Point(0,0);
			Point map_exit_door = new Point(2,4);
			//map_enemy_loc.add(new Point(1,0));
			map_walls.add(new Point(0,1));
			map_walls.add(new Point(1,1));
			map_walls.add(new Point(2,1));
			//map_walls.add(new Point(3,1));
			
			//MapGridController mapGridController = new MapGridController();
			gameMapModel.setMap_enemy_loc(map_enemy_loc);
			gameMapModel.setMap_entry_door(map_entry_door);
			gameMapModel.setMap_exit_door(map_exit_door);
			gameMapModel.setMap_walls(map_walls);
			gameMapModel.setMap_size(map_size1);
			Point p = new Point(-1,-1);
			gameMapModel.resetAll();
			
			ArrayList<Point> check_walls = new ArrayList<Point>();
			
			ArrayList<Point> check_enemy = new ArrayList<Point>();
			
			assertEquals(p,gameMapModel.getMap_chest());
			assertEquals(p,gameMapModel.getMap_entry_door());
			assertEquals(p,gameMapModel.getMap_exit_door());
			assertEquals(check_walls,gameMapModel.getMap_walls());
			assertEquals(check_enemy,gameMapModel.getMap_walls());
			
		}

	}



