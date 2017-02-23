package dungeons_and_dragons.helper;

import java.awt.Point;
import dungeons_and_dragons.model.GameMapModel;
import dungeons_and_dragons.view.MapGridView;

public class MapValidator {

	/**
	 * This creates new map model which is being observed
	 * 
	 * @type GameMapModel
	 */
	GameMapModel map_model;
	/**
	 * This create Map view object which is an observer
	 * 
	 * @type MapGridView
	 */
	MapGridView map_view;

	/**
	 * Constructs a map validator with map_view and map_model objects
	 * 
	 * @param map_view
	 * @param map_model
	 */
	public MapValidator(MapGridView map_view, GameMapModel map_model) {
		this.map_model = map_model;
		this.map_view = map_view;
	}

	/**
	 * A utility function to check if x,y is valid index for N*N maps
	 * 
	 * @param maps
	 *            map that is provided as input
	 * @param x
	 *            x-coordinate point for which the value is checked for
	 * @param y
	 *            y-coordinate point for which the value is checked for
	 * @return true if there is a valid index else returns false
	 */
	private boolean isSafe(MapButton maps[][], int x, int y) {
		// if (x,y outside maze) return false
		
		try{
			if(x >= 0 && x < this.map_model.getMap_size().x && y >= 0 && y < this.map_model.getMap_size().y){
				if(maps[x][y].getPointValue() !=0  && maps[x][y].getDirty_flag() == 0){
						{
							return true;
						}
					}
				}
			}
		catch(Exception e){
			System.out.println("array index out of bound at --> "+x+" and "+y);
			System.exit(0);
		}
		return false;
	}

	/**
	 * This function finds the path from one end point to the other in grid
	 * using Backtracking. It mainly uses findPathUtil() to solve the problem.
	 * It returns false if no path is possible, otherwise return true.
	 * 
	 * @param p
	 *            point for which one wants to reach from entry point of the
	 *            grid
	 * @return returns true if there is a path else returns false
	 */
	public boolean findPath(Point p) {
		MapButton maps[][] = this.map_view.maps;
				
		for(int i = 0;i<this.map_model.getMap_size().x;i++){
			for(int j=0;j<this.map_model.getMap_size().y;j++){
				maps[i][j].setDirty_flag(0);
			}
		}

		if (findPathUtil(maps, this.map_model.getMap_entry_door().x, this.map_model.getMap_entry_door().y,
				p) == false) {
			{
				System.out.println("Solution doesn't exist");
				return false;
			}

		}

		// solution does exist
		return true;
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
	 * @param end
	 *            point that is to be reached
	 * @return true if there is indeed a path
	 */
	private boolean findPathUtil(MapButton[][] maps, int x, int y, Point p) {

		// if (x,y is goal) return true (recursion exit condition)
		if (x == p.x && y == p.y) {
			return true;
		}

		// Check if maps[x][y] is valid by validating boundary conditions and
		// doesn't hit the wall
		if (isSafe(maps, x, y) == true) {

			// so that it doesn't traverse the same cell again
			maps[x][y].setDirty_flag(1);

			/* Move y in forward direction */
			if (findPathUtil(maps, x, y + 1, p)) {
				return true;
			}

			/*
			 * Move X in downward direction
			 */
			if (findPathUtil(maps, x + 1, y, p)) {
				return true;
			}

			/*
			 * Move x in upward direction
			 */
			if (findPathUtil(maps, x - 1, y, p)) {
				return true;
			}

			/*
			 * Move y in left direction
			 */
			if (findPathUtil(maps, x, y - 1, p)) {
				return true;
			}

			/*
			 * If none of the above movements work then BACKTRACK: return false
			 */
			return false;
		}

		return false;
	}

}
