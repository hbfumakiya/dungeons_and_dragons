package dungeons_and_dragons.model;
import java.awt.Color;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import com.google.gson.JsonSyntaxException;

/**
 * 
 * @author mihir
 * @date 23-01-2017
 * 
 */

public class GameMapModel extends Observable implements Model<GameMapModel>{

	
	public int entryFlag=0;
	public int exitFlag=0;
	private String errorMessage;
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
	private Point map_size = new Point(1, 1);
	
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
	 *Variable having point locations of enemies which will be defined later
	 * 
	 * @type HashMap
	 */
	private HashMap<Point,CharacterModel> map_enemy_loc ;
	
	/**
	 * Variable for exit wall location in map. 
	 * 
	 * @type Point
	 */
	private Point map_exit_door;
	
	private Color map_object_color_type;
	
	
	
	/**
	 * constructor to initialize map object
	 * @param width of map
	 * @param height of map
	 */
	public GameMapModel(int width,int height) {
		this.map_size.setLocation(width, height);
		this.map_walls = new ArrayList<Point>();
		this.map_chest = new Point(-1,-1);
		this.map_entry_door =  new Point(-1,-1);
		this.map_exit_door = new Point(-1,-1);
		this.map_id = 0;
		this.map_name = "";
		this.map_enemy_loc = new HashMap<Point,CharacterModel>();
	}


	public GameMapModel() {
		this.map_size.setLocation(5,5);
		this.map_walls = new ArrayList<Point>();
		this.map_chest = new Point(-1,-1);
		this.map_entry_door =  new Point(-1,-1);
		this.map_exit_door = new Point(-1,-1);
		this.map_id = 0;
		this.map_name = "";
		this.map_enemy_loc = new HashMap<Point,CharacterModel>();
	}


	/**
	 * @return the map_id
	 */
	public int getMap_id() {
		return map_id;
	}


	/**
	 * @param map_id the map_id to set
	 */
	public void setMap_id(int map_id) {
		this.map_id = map_id;
	}


	/**
	 * @return the map_name
	 */
	public String getMap_name() {
		return map_name;
	}


	/**
	 * @param map_name the map_name to set
	 */
	public void setMap_name(String map_name) {
		this.map_name = map_name;
	}


	/**
	 * @return the map_size
	 */
	public Point getMap_size() {
		return map_size;
	}


	/**
	 * @param map_size the map_size to set
	 */
	public void setMap_size(Point map_size) {
		this.map_size = map_size;
		setChanged();
		notifyObservers(this);
	}


	/**
	 * @return the map_chest
	 */
	public Point getMap_chest() {
		return map_chest;
	}


	/**
	 * Used to invoke grid map view by notifying observers
	 * @param map_chest the map_chest to set
	 */
	public void setMap_chest(Point map_chest) {
		this.map_chest = map_chest;
		setChanged();
		notifyObservers(this);
	}


	/**
	 * @return the map_walls
	 */
	public ArrayList<Point> getMap_walls() {
		return map_walls;
	}


	/**
	 * @param map_walls the map_walls to set
	 */
	public void setMap_wall(Point wall) {
		
		this.map_walls.add(wall);
		setChanged();
		notifyObservers(this);
	}


	/**
	 * @return the map_entry_door
	 */
	public Point getMap_entry_door() {
		return map_entry_door;
	}


	/**
	 * @param map_entry_door the map_entry_door to set
	 */
	public void setMap_entry_door(Point map_entry_door) {
		this.map_entry_door = map_entry_door;
		setChanged();
		notifyObservers(this);
	}


	/**
	 * @return the map_enemy_loc
	 */
	public HashMap<Point, CharacterModel> getMap_enemy_loc() {
		return map_enemy_loc;
	}


	/**
	 * @param map_enemy_loc the map_enemy_loc to set
	 */
	public void setMap_enemy_loc(Point position,CharacterModel character) {
		this.map_enemy_loc.put(position, character);
		setChanged();
		notifyObservers(this);
		
	}


	/**
	 * @return the map_exit_door
	 */
	public Point getMap_exit_door() {
		return map_exit_door;
	}


	/**
	 * @param map_exit_door the map_exit_door to set
	 */
	public void setMap_exit_door(Point map_exit_door) {
		this.map_exit_door = map_exit_door;
		setChanged();
		notifyObservers(this);
	}


	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public ArrayList<GameMapModel> getData() throws JsonSyntaxException, IOException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}


	public void setWidthHeight(Point store) {
		// TODO Auto-generated method stub
		
		
		
	}


	public Color getMap_object_color_type() {
		return map_object_color_type;
	}


	public void setMap_object_color_type(Color map_object_color_type) {
		this.map_object_color_type = map_object_color_type;
	}

	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		setChanged();
		notifyObservers(this);
	}


	public void removeWall(Point position) {
		// TODO Auto-generated method stub
		this.map_walls.remove(position);
		
	}


	public void removeEnemy(Point position) {
		// TODO Auto-generated method stub
		this.map_enemy_loc.remove(position);
	}


	public void removeChest(Point position) {
		// TODO Auto-generated method stub
		this.map_chest = new Point(-1,-1);
	}


	public void removeEntryDoor(Point position) {
		// TODO Auto-generated method stub
		this.map_entry_door =new Point(-1,-1);
	}


	public void removeExitDoor(Point position) {
		// TODO Auto-generated method stub
		this.map_exit_door = new Point(-1,-1);
	}

	
	
}