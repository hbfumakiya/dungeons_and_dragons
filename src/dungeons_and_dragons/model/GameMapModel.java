package dungeons_and_dragons.model;
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
	 *Variable for enemy creation and location of enemy
	 * 
	 * @type HashMap
	 */
	private HashMap<Point,Character> map_enemy_loc ;
	
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


	public GameMapModel() {
		// TODO Auto-generated constructor stub
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
	}


	/**
	 * @return the map_characters
	 */
	public ArrayList<Character> getMap_characters() {
		return map_characters;
	}


	/**
	 * @param map_characters the map_characters to set
	 */
	public void setMap_characters(ArrayList<Character> map_characters) {
		this.map_characters = map_characters;
	}


	/**
	 * @return the map_chest
	 */
	public Point getMap_chest() {
		return map_chest;
	}


	/**
	 * @param map_chest the map_chest to set
	 */
	public void setMap_chest(Point map_chest) {
		this.map_chest = map_chest;
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
	public void setMap_walls(ArrayList<Point> map_walls) {
		this.map_walls = map_walls;
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
	}


	/**
	 * @return the map_enemy_loc
	 */
	public HashMap<Point, Character> getMap_enemy_loc() {
		return map_enemy_loc;
	}


	/**
	 * @param map_enemy_loc the map_enemy_loc to set
	 */
	public void setMap_enemy_loc(HashMap<Point, Character> map_enemy_loc) {
		this.map_enemy_loc = map_enemy_loc;
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
	
	
}