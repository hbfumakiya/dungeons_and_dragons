package dungeons_and_dragons.model;
import java.awt.Point;
import java.util.ArrayList;

/**
 * 
 * @author mihir
 * @date 23-01-2017
 * 
 */

public class GameMapModel {

	
	
	/**
	 * Variable for identity of map. Value of all these map must be unique.
	 * 
	 * @type integer
	 */
	private int map_id;
	
	/**
	 * Variable for map name. 
	 * 
	 * @type String
	 */
	private String map_name;
	
	/**
	 * Variable for map size. 
	 * 
	 * @type Point
	 */
	private Point map_size;
	
	/**
	 * Variable for character array. 
	 * 
	 * @type Character
	 */
	private ArrayList<Character> map_characters;
	
	/**
	 * Variable for chest location in map. 
	 * 
	 * @type Point
	 */
	private Point map_chest;
	
	/**
	 * Variable for walls and their locations stored in an array. 
	 * 
	 * @type Point
	 */
	private ArrayList<Point> map_walls;
	
	/**
	 * Variable for entry wall location in map. 
	 * 
	 * @type Point
	 */
	private Point map_entry_door;
	
	/**
	 * Variable for exit wall location in map. 
	 * 
	 * @type Point
	 */
	private Point map_exit_door;
	
	
	/**
	 * constructor to initialize map object
	 * @param width of map
	 * @param height of map
	 */
	public GameMapModel(int width,int height) {
		this.map_size.setLocation(width, height);
	}
	
	
}