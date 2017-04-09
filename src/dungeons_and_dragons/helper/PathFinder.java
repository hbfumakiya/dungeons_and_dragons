package dungeons_and_dragons.helper;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import dungeons_and_dragons.model.GameMapModel;
import dungeons_and_dragons.model.GamePlayModel;

/**
 * This class is used to find a path for a character to kill another character
 * 
 * @author Tejas Sadrani
 *
 */
public class PathFinder {

	/**
	 * This creates new map model which is being observed
	 * 
	 * @type GameMapModel
	 */
	GameMapModel map_model;
	/**
	 * This creates a new gamePlayModel object
	 * 
	 * @type GamePlayModel
	 */
	GamePlayModel gamePlayModel;

	/**
	 * This creates an instance of Random class used to generate random numbers
	 */
	Random randomGenerator;

	/**
	 * This is used to create a local friend character list
	 */
	ArrayList<MapCharacter> friendList;
	
	/**
	 * This is used to create a local enemy character list
	 */
	ArrayList<MapCharacter> enemyList;

	/**
	 * This is used to construct the class with setting all local variables that are defined
	 * @param gamePlayModel
	 */
	public PathFinder(GamePlayModel gamePlayModel) {
		map_model = gamePlayModel.getCampaignModel().getOutput_map_list().get(gamePlayModel.getCurrentMapIndex());
		this.gamePlayModel = gamePlayModel;
		this.enemyList = this.gamePlayModel.enemyList;
		this.friendList = this.gamePlayModel.friendList;
		
		randomGenerator = new Random();

		for (int i = 0; i < this.map_model.getMap_enemy_loc().size(); i++) {
			if (this.map_model.getMap_enemy_loc().get(i).getCharacterType().equals("Friendly")) {
				friendList.add(this.map_model.getMap_enemy_loc().get(i));
				gamePlayModel.friendList.add(this.map_model.getMap_enemy_loc().get(i));
			} else if(this.map_model.getMap_enemy_loc().get(i).getCharacterType().equals("Enemy")){
				enemyList.add(this.map_model.getMap_enemy_loc().get(i));
				gamePlayModel.enemyList.add(this.map_model.getMap_enemy_loc().get(i));
			}
		}

	}
	
	/**
	 * A utility function to check if x,y is valid index for N*N maps
	 * @param maps
	 * @param x
	 * @param y
	 * @return true if there is a valid index else returns false
	 */
	private boolean isSafe(MapButton maps[][], int x, int y) {
		// if (x,y outside maze) return false

		try {
			if (x >= 0 && x < this.map_model.getMap_size().x && y >= 0 && y < this.map_model.getMap_size().y) {
				if (maps[x][y].getPointValue() != 0 && maps[x][y].getDirty_flag() == 0) {
					{
						return true;
					}
				}
			}
		} catch (Exception e) {
			System.exit(0);
		}
		return false;
	}

	/**
	 * This function finds the path from one end point to the other in grid
	 * using Backtracking. It mainly uses findPathUtil() to solve the problem.
	 * It returns false if no path is possible, otherwise return true.
	 * 
	 * @param p	point for which one wants to reach from entry point of the grid
	 *            
	 * @return returns a path that exists between two points
	 */
	public MapButton[][] findPath(String characterType) {
		MapButton maps[][] = this.gamePlayModel.currentMap;
		
		MapButton path[][] = maps.clone();

		/*for (int i = 0; i < this.map_model.getMap_size().x; i++) {
			for (int j = 0; j < this.map_model.getMap_size().y; j++) {
				maps[i][j].setDirty_flag(0);
			}
		}*/

		switch (characterType) {

		case "enemy": {

			Point p = gamePlayModel.getGameCharacterPosition();
			int i = randomGenerator.nextInt(enemyList.size());

			this.gamePlayModel.currentEnemyIndex = i;
			findPathUtil(maps, enemyList.get(i).getX(), enemyList.get(i).getY(), p, path);
			break;
		}
		case "computer": {

			int i = randomGenerator.nextInt(this.map_model.getMap_enemy_loc().size());
			Point p = new Point(this.map_model.getMap_enemy_loc().get(i).getX(),
					this.map_model.getMap_enemy_loc().get(i).getY());

			findPathUtil(maps, gamePlayModel.getGameCharacterPosition().x, gamePlayModel.getGameCharacterPosition().y,
					p, path);
			break;

		}
		case "friend": {

			int index = randomGenerator.nextInt(friendList.size());
			this.gamePlayModel.currentEnemyIndex = index;
			
			Point p = new Point(friendList.get(index).getX(), friendList.get(index).getY());


			boolean pathExists = false;
			while(pathExists  == true)
			{
				int i = randomGenerator.nextInt(4);
				switch (i) {
	
				case 0: {
					p.x++;
				}
				case 1: {
					p.x--;
				}
				case 2: {
					p.y++;
				}
				case 3: {
					p.y--;
				}
	
				}
				if (isSafe(maps, p.x, p.y) == true) {
					path[p.x][p.y].path = 1;
					pathExists = true;
				}
			}
			break;
		}
		}
		return path;
	}

	/**
	 * A recursive utility function to find path
	 * 
	 * @param maps
	 *            consisting of input map that is the grid drawn
	 * @param x
	 *            is the entry x location of the point
	 * @param y
	 *            is the entry y location of the point
	 * @param sol
	 *            is the map produced consisting of the path towards the aim
	 * 
	 * @param end point that is to be reached
	 * 
	 * @return true if there is indeed a path
	 */
	private boolean findPathUtil(MapButton[][] maps, int x, int y, Point p, MapButton[][] path) {

		// if (x,y is goal) return true (recursion exit condition)
		if (x == p.x && y == p.y) {
			path[x][y].path = 1;
			return true;
		}

		// Check if maps[x][y] is valid by validating boundary conditions and
		// doesn't hit the wall
		if (isSafe(maps, x, y) == true) {

			// so that it doesn't traverse the same cell again
			maps[x][y].setDirty_flag(1);
			path[x][y].path = 1;

			/* Move y in forward direction */
			if (findPathUtil(maps, x, y + 1, p, path)) {
				return true;
			}

			/*
			 * Move X in downward direction
			 */
			if (findPathUtil(maps, x + 1, y, p, path)) {
				return true;
			}

			/*
			 * Move x in upward direction
			 */
			if (findPathUtil(maps, x - 1, y, p, path)) {
				return true;
			}

			/*
			 * Move y in left direction
			 */
			if (findPathUtil(maps, x, y - 1, p, path)) {
				return true;
			}

			/*
			 * If none of the above movements work then BACKTRACK: return false
			 */
			path[x][y].path = 0;
			return false;
		}

		return false;
	}

}
