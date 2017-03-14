package dungeons_and_dragons.model;

import java.awt.Color;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.Expose;

import dungeons_and_dragons.exception.NotFoundException;
import dungeons_and_dragons.helper.FileHelper;
import dungeons_and_dragons.helper.LogHelper;
import dungeons_and_dragons.helper.MapCharacter;

/**
 * This class is created to manage map data,store all the points of map
 * walls,enemies,entry door, exit door and chest.
 * 
 * @author Mihir and Urmil Kansara
 * 
 */

public class GameMapModel extends Observable implements Model<GameMapModel> {

	/**
	 * To restrict the map to be filled by default with entry door
	 */
	public int entryFlag = 0;
	/**
	 * To restrict the map to be filled by default with exit door
	 */
	public int exitFlag = 0;

	/**
	 * Variable used to show a error message in case received
	 */
	private String errorMessage;

	/**
	 * Variable for identity of map. Value of all these map must be unique.
	 * 
	 * @type integer
	 */
	@Expose
	private int map_id;

	/**
	 * Variable for map name.
	 * 
	 * @type String
	 */
	@Expose
	private String map_name;

	/**
	 * Variable for map size.
	 * 
	 * @type Point
	 */
	@Expose
	private Point map_size = new Point(1, 1);

	/**
	 * Variable for chest location in map.
	 * 
	 * @type Point
	 */
	@Expose
	private Point map_chest;

	/**
	 * Variable for walls and their locations stored in an array.
	 * 
	 * @type Point
	 */
	@Expose
	private ArrayList<Point> map_walls;

	/**
	 * Variable for entry wall location in map.
	 * 
	 * @type Point
	 */
	@Expose
	private Point map_entry_door;

	/**
	 * Variable having point locations of enemies which will be defined later
	 * 
	 * @type ArrayList
	 */
	@Expose
	private ArrayList<MapCharacter> map_enemy_loc;

	/**
	 * Variable for exit wall location in map.
	 * 
	 * @type Point
	 */
	@Expose
	private Point map_exit_door;

	/**
	 * defines colot of map object type.
	 * 
	 * @type Color
	 */
	private Color map_object_color_type;

	/**
	 * constructor to initialize map object
	 * 
	 * @param width
	 *            of map
	 * @param height
	 *            of map
	 */
	public GameMapModel(int width, int height) {
		this.map_size.setLocation(width, height);
		this.map_walls = new ArrayList<Point>();
		this.map_chest = new Point(-1, -1);
		this.map_entry_door = new Point(-1, -1);
		this.map_exit_door = new Point(-1, -1);
		this.map_id = 0;
		this.map_name = "";
		this.map_enemy_loc = new ArrayList<MapCharacter>();
	}

	/**
	 * default constructor to set map features
	 */
	public GameMapModel() {
		this.map_size.setLocation(5, 5);
		this.map_walls = new ArrayList<Point>();
		this.map_chest = new Point(-1, -1);
		this.map_entry_door = new Point(-1, -1);
		this.map_exit_door = new Point(-1, -1);
		this.map_id = 0;
		this.map_name = "";
		this.map_enemy_loc = new ArrayList<MapCharacter>();
	}

	/**
	 * @return the map_id
	 */
	public int getMap_id() {
		return map_id;
	}

	/**
	 * @param map_id
	 *            the map_id to set
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
	 * @param map_name
	 *            the map_name to set
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
	 * @param map_size
	 *            the map_size to set
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
	 * 
	 * @param map_chest
	 *            the map_chest to set
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
	 * This function checks whether point already present in arraylist. If it is
	 * not present in arraylist then add into arraylist else ignore.
	 * 
	 * @param map_walls
	 *            the map_walls to set
	 */
	public void setMap_wall(Point wall) {
		if (!this.map_walls.contains(wall))
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
	 * @param map_walls
	 *            the map_walls to set
	 */
	public void setMap_walls(ArrayList<Point> map_walls) {
		this.map_walls = map_walls;
	}

	/**
	 * @param map_enemy_loc
	 *            the map_enemy_loc to set
	 */
	public void setMap_enemy_loc(ArrayList<MapCharacter> map_enemy_loc) {
		this.map_enemy_loc = map_enemy_loc;
	}

	/**
	 * @param map_entry_door
	 *            the map_entry_door to set
	 */
	public void setMap_entry_door(Point map_entry_door) {

		if ((map_entry_door.x == 0 && map_entry_door.y != 0) || (map_entry_door.x != 0 && map_entry_door.y == 0)
				|| (map_entry_door.x == map_size.getX() - 1 && map_entry_door.y != map_size.getY() - 1)
				|| (map_entry_door.x != map_size.getX() - 1 && map_entry_door.y == map_size.getY() - 1)
				|| (map_entry_door.x == 0 && map_entry_door.y == 0)
				|| (map_entry_door.x == map_size.getX() - 1 && map_entry_door.y == map_size.getY() - 1)) {
			this.map_entry_door = map_entry_door;
		}
		setChanged();
		notifyObservers(this);
	}

	/**
	 * @return the map_enemy_loc
	 */
	public ArrayList<MapCharacter> getMap_enemy_loc() {
		return map_enemy_loc;
	}

	/**
	 * @param map_enemy_loc
	 *            the map_enemy_loc to set
	 */
	public void setMap_enemy_loc(MapCharacter pos_char) {
		if (!this.map_enemy_loc.contains(pos_char))
			this.map_enemy_loc.add(pos_char);
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
	 * @param map_exit_door
	 *            the map_exit_door to set
	 */
	public void setMap_exit_door(Point map_exit_door) {
		if ((map_exit_door.x == 0 && map_exit_door.y != 0) || (map_exit_door.x != 0 && map_exit_door.y == 0)
				|| (map_exit_door.x == map_size.getX() - 1 && map_exit_door.y != map_size.getY() - 1)
				|| (map_exit_door.x != map_size.getX() - 1 && map_exit_door.y == map_size.getY() - 1)
				|| (map_exit_door.x == 0 && map_exit_door.y == 0)
				|| (map_exit_door.x == map_size.getX() - 1 && map_exit_door.y == map_size.getY() - 1)) {
			this.map_exit_door = map_exit_door;
		}
		setChanged();
		notifyObservers(this);
	}

	/**
	 * method to save map
	 */
	@Override
	public void save() {
		try {
			this.setCurrentId();
			FileHelper.saveMap(this);
		} catch (JsonSyntaxException | IOException e) {
			LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
		}
	}

	/**
	 * method to set current id
	 * 
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private void setCurrentId() throws JsonSyntaxException, IOException {

		ArrayList<GameMapModel> alldata = this.getData();
		if (alldata != null) {
			this.map_id = alldata.get(alldata.size() - 1).getMap_id() + 1;
		} else {
			this.map_id = 1;
		}
	}

	/**
	 * This method is used to get all data of map from files
	 * 
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	@Override
	public ArrayList<GameMapModel> getData() throws JsonSyntaxException, IOException {
		return FileHelper.getMaps();
	}

	/**
	 * method to update map
	 */
	@Override
	public void update() {
		try {
			FileHelper.updateMap(this);
		} catch (IOException | NotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method is used to detect which radio button is selected.
	 * 
	 * @return Color
	 */
	public Color getMap_object_color_type() {
		return map_object_color_type;
	}

	/**
	 * This method is used to set which radio button is selected.
	 * 
	 * @param map_object_color_type
	 */
	public void setMap_object_color_type(Color map_object_color_type) {
		this.map_object_color_type = map_object_color_type;
	}

	/**
	 * This method is used to return error message
	 * 
	 * @return String
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * This method is used to set error message
	 * 
	 * @param errorMessage
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * This method is used to remove wall if radio button map_remove is selected
	 * 
	 * @param position
	 *            point to be removed from arraylist
	 */
	public void removeWall(Point position) {
		this.map_walls.remove(position);

	}

	/**
	 * This method is used to remove enemy from arraylist if radio button
	 * map_remove is selected
	 * 
	 * @param position
	 *            point to be removed from arraylist
	 */
	public void removeNPC(MapCharacter character) {
		this.map_enemy_loc.remove(character);
	}

	/**
	 * This method is used to remove chest if radio button map_remove is
	 * selected and then clicked on point containing chest
	 * 
	 * @param position
	 *            point to be removed for chest
	 */
	public void removeChest(Point position) {
		this.map_chest = new Point(-1, -1);
	}

	/**
	 * This method is used to remove entry door if radio button map_remove is
	 * selected
	 * 
	 * @param position
	 *            point to be removed for entry door
	 */
	public void removeEntryDoor(Point position) {
		this.map_entry_door = new Point(-1, -1);
	}

	/**
	 * This method is used to remove exit door if radio button map_remove is
	 * selected
	 * 
	 * @param position
	 *            point to be removed for exit door
	 */
	public void removeExitDoor(Point position) {
		this.map_exit_door = new Point(-1, -1);
	}

	/**
	 * This method is used to remove color if radio button map_remove is
	 * selected
	 * 
	 * @param position
	 *            point to be removed for color
	 */
	public void removeMap_object_color_type() {
		this.map_object_color_type = null;
	}

	public void callObservers() {
		setChanged();
		notifyObservers(this);

	}

	/**
	 * method to reset map features
	 */
	public void resetAll() {
		this.map_walls.removeAll(map_walls);
		this.map_chest.setLocation(-1, -1);
		this.map_entry_door.setLocation(-1, -1);
		this.map_exit_door.setLocation(-1, -1);
		// this.map_id = 0;
		// this.map_name = ;
		this.map_enemy_loc.removeAll(map_enemy_loc);
	}

	/**
	 * This Variable id to check whether update or save should be there in view
	 */
	private int finder; // if finder = 0 save else if finder = 1 then update

	/**
	 * This method sets finder.
	 * 
	 * @param finder
	 */
	public void setFinder(int finder) {
		this.finder = finder;
	}

	/**
	 * 
	 * @return int
	 */
	public int getFinder() {
		return this.finder;
	}

	
	private ArrayList<CharacterModel> input_character_list;
	private ArrayList<CharacterModel> output_character_list;
	
	public void removeNPCFromComboBox(CharacterModel m_char){
			input_character_list.remove(m_char);
			setChanged();
			notifyObservers(this);
	}

	/**
	 * @return the input_character_list
	 */
	public ArrayList<CharacterModel> getInput_character_list() {
		return input_character_list;
	}

	

	/**
	 * @param input_character_list the input_character_list to set
	 */
	public void setInput_character_list(ArrayList<CharacterModel> input_character_list) {
		this.input_character_list = input_character_list;
	}
	
	public void addNPCToComboBox(CharacterModel mc) {
		// TODO Auto-generated method stub
		if(!input_character_list.contains(mc)){
		input_character_list.add(mc);
		setChanged();
		notifyObservers(this);
		}
	}

}