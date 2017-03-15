package dungeons_and_dragons.controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JOptionPane;

import com.google.gson.JsonSyntaxException;

import dungeons_and_dragons.helper.FileHelper;
import dungeons_and_dragons.helper.Game_constants;
import dungeons_and_dragons.helper.MapButton;
import dungeons_and_dragons.helper.MapCharacter;
import dungeons_and_dragons.helper.MapItem;
import dungeons_and_dragons.helper.MapValidator;
import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.model.GameMapModel;
import dungeons_and_dragons.model.ItemModel;
import dungeons_and_dragons.view.MapGridView;

/**
 * This class is a controller where it handles all the actions performed in map
 * 
 * @author Urmil Kansara & Tejas Sadrani
 *
 */
public class MapGridController implements ActionListener {

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

	public static int finder = 0;

	/**
	 * Default constructor of Map Grid controller
	 * <p>
	 * MapGrid model and view are initialized Also view is binded to observer.
	 * <p>
	 * All the events of view are registered in constructor
	 */
	public MapGridController() {
		Point p = new Point();
		p.x = 5;
		p.y = 5;
		this.map_model = new GameMapModel(5, 5);
		this.map_view = new MapGridView(p);

		this.map_model.addObserver(map_view);
		this.map_view.setListener(this);
		finder = 0;
		this.map_model.setFinder(finder);
		
		ArrayList<CharacterModel> m = new ArrayList<CharacterModel>();
		try {
			m = FileHelper.getCharcters();
		} catch (JsonSyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.map_model.setInput_character_list(m);
		
		ArrayList<ItemModel> im = new ArrayList<ItemModel>();
		try {
			im = FileHelper.getItems();
		} catch (JsonSyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.map_model.setInput_item_list(im);
	}

	/**
	 * Constructor for mapgrid for updation of map. initialize view and model.
	 * 
	 * <p>
	 * Called for editing of map
	 * <p>
	 * 
	 * @param map
	 */
	public MapGridController(GameMapModel map) {
		Point p = new Point();
		p.x = 5;
		p.y = 5;
		this.map_model = map;
		this.map_view = new MapGridView(map);

		this.map_model.addObserver(map_view);
		this.map_view.setListener(this);
		this.map_view.setButtonListener(this);
		this.map_model.entryFlag = 1;
		this.map_model.exitFlag = 1;

		finder = 1;
		this.map_model.setFinder(finder);
	}

	/**
	 * Action event for saving of map,submitting of size for map,updation for
	 * map and going back to previous screen.
	 * 
	 * @param e
	 *            action Event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(map_view.back_button)) {
			new ManageMapController();
			this.map_view.dispose();
		} else if (e.getSource().equals(map_view.save_button)) {
			/* validation needs to be done */
			if (this.map_model.getMap_entry_door().x == -1 && this.map_model.getMap_entry_door().y == -1) {
				this.map_model.setErrorMessage("It is an invalid map as there should be one entry door in the map");
			} else if (this.map_model.getMap_exit_door().x == -1 && this.map_model.getMap_exit_door().y == -1) {
				this.map_model.setErrorMessage("It is an invalid map as there should be one exit door in the map");
			}

			else {
				if (new MapValidator(this.map_view, this.map_model).findPath(this.map_model.getMap_exit_door())) {

					// check if there are indeed enemies in the map of all that
					// are defined
					int count = 0;
					// ArrayList test = new ArrayList();
					Set mapSet = new HashSet(this.map_model.getMap_enemy_loc());
					Iterator mapIterator = mapSet.iterator();

					while (mapIterator.hasNext()) {

						MapCharacter mapCharacter = (MapCharacter) mapIterator.next();
						Point p = new Point(mapCharacter.getX(),mapCharacter.getY());
						
						if (new MapValidator(this.map_view, this.map_model).findPath(p)) {
							count++;

						}
					}
					if (count == this.map_model.getMap_enemy_loc().size() && count > 0) {

						this.map_model.setMap_name(this.map_view.map_name_textfield.getText());
						this.map_model.save();

						new ManageMapController();
						this.map_view.dispose();
					} else if (count == 0) {
						JOptionPane.showOptionDialog(null, "It is an invalid map as there should be atleast one enemy",
								"Invalid Map", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null,
								new Object[] {}, null);
					}
					else if(count != this.map_model.getMap_enemy_loc().size())
					{JOptionPane.showOptionDialog(null,
							"It is an invalid map as all the enemies that are defined should be in the defined path",
							"Invalid Map", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, new Object[] {},
							null);}

				}
				// need to manipulate error message window
				else {
					// this.map_view.setListener(this);
					// this.map_view.setButtonListener(this);
					// this.map_model.setErrorMessage("It is an invalid map as
					// there should be one path defined to reach from entry to
					// exit door");
					JOptionPane.showOptionDialog(null,
							"It is an invalid map as there should be one path defined to reach from entry to exit door",
							"Invalid Map", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, new Object[] {},
							null);

				}
			}

		} else if (e.getSource().equals(map_view.update_button)) {

			if (this.map_model.getMap_entry_door().x == -1 && this.map_model.getMap_entry_door().y == -1) {
				this.map_model.setErrorMessage("It is an invalid map as there should be one entry door in the map");
			} else if (this.map_model.getMap_exit_door().x == -1 && this.map_model.getMap_exit_door().y == -1) {
				this.map_model.setErrorMessage("It is an invalid map as there should be one exit door in the map");
			}

			else {
				if (new MapValidator(this.map_view, this.map_model).findPath(this.map_model.getMap_exit_door())) {

					// check if there are indeed enemies in the map of all that
					// are defined
					int count = 0;
					// ArrayList test = new ArrayList();
					Set mapSet = new HashSet(this.map_model.getMap_enemy_loc());
					Iterator mapIterator = mapSet.iterator();
					while (mapIterator.hasNext()) {
						Point p = (Point) mapIterator.next();
						if (new MapValidator(this.map_view, this.map_model).findPath(p)) {
							count++;
						}
					}
					if (count == this.map_model.getMap_enemy_loc().size() && count > 0) {

						this.map_model.setMap_name(this.map_view.map_name_textfield.getText());
						this.map_model.update();

						new ManageMapController();
						this.map_view.dispose();
					} else if (count == 0) {
						JOptionPane.showOptionDialog(null, "It is an invalid map as there should be atleast one enemy",
								"Invalid Map", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null,
								new Object[] {}, null);
					}

				}
				// need to manipulate error message window
				else {
					// this.map_view.setListener(this);
					// this.map_view.setButtonListener(this);
					// this.map_model.setErrorMessage("It is an invalid map as
					// there should be one path defined to reach from entry to
					// exit door");
					JOptionPane.showOptionDialog(null,
							"It is an invalid map as there should be one path defined to reach from entry to exit door",
							"Invalid Map", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, new Object[] {},
							null);

				}
			}
		}

		else if (e.getSource().equals(map_view.submit)) {
			if (this.map_view.map_name_textfield.getText().equals("")) {
				JOptionPane.showOptionDialog(null, "Please Enter Valid Name", "Invalid Name",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
				return;
			}
			try {

				if (Integer.parseInt(this.map_view.map_height_textfield.getText().trim()) < 3
						|| Integer.parseInt(this.map_view.map_width_textfield.getText().trim()) < 3) {
					throw new NumberFormatException();
				}
				if (Integer.parseInt(this.map_view.map_height_textfield.getText().trim()) > 20
						|| Integer.parseInt(this.map_view.map_width_textfield.getText().trim()) > 20) {
					throw new NumberFormatException();
				}
				Point store = new Point();
				store.x = Integer.parseInt(this.map_view.map_height_textfield.getText().trim());
				store.y = Integer.parseInt(this.map_view.map_width_textfield.getText().trim());

				if (this.map_model.getMap_size().getX() != store.x || this.map_model.getMap_size().getY() != store.y) {
					// to reset the map grid if they have changed the height or
					// width of map
					this.map_model.resetAll();

				}
				{
					// So that at time of loading same height and width all
					// entry door and exit door remain in same place
					this.map_model.entryFlag = 1;
					this.map_model.exitFlag = 1;
				}

				this.map_model.setFinder(finder);

				this.map_model.setMap_size(store);
				this.map_view.setButtonListener(this);
			} catch (NumberFormatException ex) {
				JOptionPane.showOptionDialog(null, "Please Enter Valid Height/Width between 3 and 20", "Invalid Name",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);

			}
		} else if (e.getSource().equals(map_view.map_entry_door)) {
			this.map_model.setMap_object_color_type(Game_constants.ENTRY_DOOR);

		} else if (e.getSource().equals(map_view.map_exit_door)) {
			this.map_model.setMap_object_color_type(Game_constants.EXIT_DOOR);

		} else if (e.getSource().equals(map_view.map_wall)) {
			this.map_model.setMap_object_color_type(Game_constants.WALLS);

		} else if (e.getSource().equals(map_view.map_enemy)) {
			this.map_model.setMap_object_color_type(Game_constants.ENEMIES);

		} else if (e.getSource().equals(map_view.map_chest)) {
			this.map_model.setMap_object_color_type(Game_constants.CHEST);

		} else if (e.getSource().equals(map_view.map_remove)) {
			this.map_model.removeMap_object_color_type();

		} else if (e.getSource().equals(map_view.map_friend)) {
			this.map_model.setMap_object_color_type(Game_constants.FRIENDS);

		} 
		else if(e.getSource().equals(map_view.map_dropdown_item)){
			//this.map_model.setSelectedItem((ItemModel)this.map_view.map_dropdown_item.getSelectedItem());
			
		}
		else if(e.getSource().equals(map_view.map_dropdown_enemy_friend))
		{
			
		}
		
		else if (((MapButton) e.getSource()).getButton_type().equals(Game_constants.GRID_BUTTON_TYPE)) {
			Point position = new Point();
			position.x = ((MapButton) e.getSource()).getxPos();
			position.y = ((MapButton) e.getSource()).getyPos();
			String mes;
			
			ArrayList<MapCharacter> m = new ArrayList<MapCharacter>();
			m = this.map_model.getMap_enemy_loc();
			CharacterModel npc = new CharacterModel();
			MapCharacter mc = new MapCharacter();
			int t = 0;
			for(int x =0;x<m.size();x++)
			{
				if(m.get(x).getX() == position.x && m.get(x).getY() == position.y)
				{
					if(m.get(x).getCharacterType() == MapCharacter.ENEMY)
					{
						t = 1;
					}
					else if(m.get(x).getCharacterType() == MapCharacter.FRIENDLY)
					{
						t = 2;
					}
					npc = m.get(x).getCharacter();
					mc = m.get(x);
				}
			}
			MapItem chest_item = this.map_model.getMap_chest();
			
			
//			if(this.map_view.map_dropdown_item.getSelectedItem() == null)
//			{
//				JOptionPane.showOptionDialog(null, "No item present", "Invalid item",
//						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
//				return;
//			}
			//chest_item.setItem((ItemModel) this.map_view.map_dropdown_item.getSelectedItem());
			

			if (this.map_model.getMap_object_color_type() == Game_constants.WALLS) {

				if (validateMapForExisting("wall", position,t)) {
					if (t == 1 || t == 2){
						this.map_model.removeNPC(mc);
						this.map_model.addNPCToComboBox(npc);
					}
					
					else if (this.map_model.getMap_chest().getX() == position.x && this.map_model.getMap_chest().getY() == position.y) 
					{
						this.map_model.addItemToComboBox(this.map_model.getMap_chest().getItem());
						this.map_model.removeChest(position);
					}
					else if (this.map_model.getMap_entry_door().equals(position))
						this.map_model.removeEntryDoor(position);
					else if (this.map_model.getMap_exit_door().equals(position))
						this.map_model.removeExitDoor(position);

					this.map_model.setMap_wall(position);
					this.map_view.setButtonListener(this);
				}

			} else if (this.map_model.getMap_object_color_type() == Game_constants.ENEMIES) {

				if (validateMapForExisting("Enemy", position,t)) {
					MapCharacter mapCharacters = new MapCharacter();
					mapCharacters.setX(position.x);
					mapCharacters.setY(position.y);
					if(this.map_view.map_dropdown_enemy_friend.getSelectedItem()==  null)
					{
						JOptionPane.showOptionDialog(null, "No Character present in dropdown", "Invalid Character",
								JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
						return;
					}
					mapCharacters.setCharacter((CharacterModel)this.map_view.map_dropdown_enemy_friend.getSelectedItem());
					mapCharacters.setCharacterType(MapCharacter.ENEMY);
					
					if (t == 2){
						this.map_model.removeNPC(mc);
						this.map_model.addNPCToComboBox(npc);
					}
					else if (this.map_model.getMap_walls().contains(position))
						this.map_model.removeWall(position);
					else if (this.map_model.getMap_chest().getX() == position.x && this.map_model.getMap_chest().getY() == position.y) 
					{
						this.map_model.addItemToComboBox(this.map_model.getMap_chest().getItem());
						this.map_model.removeChest(position);
					}
					else if (this.map_model.getMap_entry_door().equals(position))
						this.map_model.removeEntryDoor(position);
					else if (this.map_model.getMap_exit_door().equals(position))
						this.map_model.removeExitDoor(position);
					
					
					this.map_model.removeNPCFromComboBox((CharacterModel)this.map_view.map_dropdown_enemy_friend.getSelectedItem());
					
					this.map_model.setMap_enemy_loc(mapCharacters);
					this.map_view.setButtonListener(this);
				}
				

			}
			else if (this.map_model.getMap_object_color_type() == Game_constants.FRIENDS) {

				if (validateMapForExisting("Friend", position,t)) {
					MapCharacter mapCharacters = new MapCharacter();
					mapCharacters.setX(position.x);
					mapCharacters.setY(position.y);
					if(this.map_view.map_dropdown_enemy_friend.getSelectedItem()==  null)
					{
						JOptionPane.showOptionDialog(null, "No Character present in dropdown", "Invalid Character",
								JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
						return;
					}
					mapCharacters.setCharacter((CharacterModel)this.map_view.map_dropdown_enemy_friend.getSelectedItem());
					mapCharacters.setCharacterType(MapCharacter.FRIENDLY);
					
					if (t == 1){
						this.map_model.removeNPC(mc);
						this.map_model.addNPCToComboBox(npc);
					}
					else if (this.map_model.getMap_walls().contains(position))
						this.map_model.removeWall(position);
					else if (this.map_model.getMap_chest().getX() == position.x && this.map_model.getMap_chest().getY() == position.y) 
					{
						this.map_model.addItemToComboBox(this.map_model.getMap_chest().getItem());
						this.map_model.removeChest(position);
					}
					else if (this.map_model.getMap_entry_door().equals(position))
						this.map_model.removeEntryDoor(position);
					else if (this.map_model.getMap_exit_door().equals(position))
						this.map_model.removeExitDoor(position);
					
					
					this.map_model.removeNPCFromComboBox((CharacterModel)this.map_view.map_dropdown_enemy_friend.getSelectedItem());
					
					this.map_model.setMap_enemy_loc(mapCharacters);
					this.map_view.setButtonListener(this);
				}
			
			
			}else if (this.map_model.getMap_object_color_type() == Game_constants.ENTRY_DOOR) {

				if (validateMapForExisting("Entry Door", position,t)) {
					
					if (!((position.x == 0 && position.y != 0) || (position.x != 0 && position.y == 0)
							|| (position.x == this.map_model.getMap_size().getX() - 1 && position.y != this.map_model.getMap_size().getY() - 1)
							|| (position.x != this.map_model.getMap_size().getX() - 1 && position.y == this.map_model.getMap_size().getY() - 1)
							|| (position.x == 0 && position.y == 0)
							|| (position.x == this.map_model.getMap_size().getX() - 1 && position.y == this.map_model.getMap_size().getY() - 1))) {
						return;
					}
					
					if (t == 1 || t == 2)
					{
						this.map_model.removeNPC(mc);
						this.map_model.addNPCToComboBox(npc);
					}
					else if (this.map_model.getMap_chest().getX() == position.x && this.map_model.getMap_chest().getY() == position.y) 
					{
						this.map_model.addItemToComboBox(this.map_model.getMap_chest().getItem());
						this.map_model.removeChest(position);
					}
					else if (this.map_model.getMap_walls().contains(position))
						this.map_model.removeWall(position);
					else if (this.map_model.getMap_exit_door().equals(position))
						this.map_model.removeExitDoor(position);

					this.map_model.entryFlag = 1;
					this.map_model.setMap_entry_door(position);
					this.map_view.setButtonListener(this);
				}

			} else if (this.map_model.getMap_object_color_type() == Game_constants.EXIT_DOOR) {

				if (validateMapForExisting("Exit Door", position,t)) {
					
					if (!((position.x == 0 && position.y != 0) || (position.x != 0 && position.y == 0)
							|| (position.x == this.map_model.getMap_size().getX() - 1 && position.y != this.map_model.getMap_size().getY() - 1)
							|| (position.x != this.map_model.getMap_size().getX() - 1 && position.y == this.map_model.getMap_size().getY() - 1)
							|| (position.x == 0 && position.y == 0)
							|| (position.x == this.map_model.getMap_size().getX() - 1 && position.y == this.map_model.getMap_size().getY() - 1))) {
						return;
					}
					
					if (t == 1 || t == 2 )
					{
						this.map_model.removeNPC(mc);
						this.map_model.addNPCToComboBox(npc);
					}
					else if (this.map_model.getMap_chest().getX() == position.x && this.map_model.getMap_chest().getY() == position.y) {
						this.map_model.addItemToComboBox(this.map_model.getMap_chest().getItem());
						this.map_model.removeChest(position);
					}
					else if (this.map_model.getMap_walls().contains(position))
						this.map_model.removeWall(position);
					else if (this.map_model.getMap_entry_door().equals(position))
						this.map_model.removeEntryDoor(position);

					this.map_model.exitFlag = 1;
					this.map_model.setMap_exit_door(position);
					this.map_view.setButtonListener(this);
				}

			} else if (this.map_model.getMap_object_color_type() == Game_constants.CHEST) {
				
				
				MapItem chest = new MapItem();
				chest.setX(position.x);
				chest.setY(position.y);
				if(this.map_view.map_dropdown_item.getSelectedItem() == null)
				{
					JOptionPane.showOptionDialog(null, "No item present", "Invalid item",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
					return;
				}
				chest.setItem((ItemModel) this.map_view.map_dropdown_item.getSelectedItem());
				
				if (validateMapForExisting("Chest", position,t)) {
					if (t == 1 || t == 2)
					{
						this.map_model.removeNPC(mc);
						this.map_model.addNPCToComboBox(npc);
					}
					else if (this.map_model.getMap_entry_door().equals(position))
						this.map_model.removeEntryDoor(position);
					else if (this.map_model.getMap_walls().contains(position))
						this.map_model.removeWall(position);
					else if (this.map_model.getMap_exit_door().equals(position))
						this.map_model.removeExitDoor(position);
					
					this.map_model.removeItemFromComboBox(chest.getItem());
					this.map_model.setMap_chest(chest);
					this.map_view.setButtonListener(this);
				}

			} else if (this.map_model.getMap_object_color_type() == null) {
				

				if (t == 1 || t == 2) {
					
					this.map_model.removeNPC(mc);
					this.map_model.addNPCToComboBox(npc);
					this.map_model.callObservers();
					this.map_view.setButtonListener(this);
				} else if (this.map_model.getMap_chest().getX() == position.x && this.map_model.getMap_chest().getY() == position.y) {
					this.map_model.addItemToComboBox(this.map_model.getMap_chest().getItem());
					this.map_model.removeChest(position);
					this.map_model.callObservers();
					this.map_view.setButtonListener(this);
				} else if (this.map_model.getMap_entry_door().equals(position)) {
					this.map_model.removeEntryDoor(position);
					this.map_model.callObservers();
					this.map_view.setButtonListener(this);
				} else if (this.map_model.getMap_exit_door().equals(position)) {
					this.map_model.removeExitDoor(position);
					this.map_model.callObservers();
					this.map_view.setButtonListener(this);
				} else if (this.map_model.getMap_walls().contains(position)) {
					this.map_model.removeWall(position);
					this.map_model.callObservers();
					this.map_view.setButtonListener(this);
				}

			}

		}

	}

	/**
	 * Function used to check if the object is already present in the position
	 * or not
	 * 
	 * @param object name of object
	 * @param position position of object
	 * @return String
	 */
	private boolean validateMapForExisting(String object, Point position,int check_npc) {

		boolean validate = true;
		if (this.map_model.getMap_chest().getX() == position.x && this.map_model.getMap_chest().getY() == position.y ) {
			int confirm = JOptionPane.showConfirmDialog(this.map_view, "Do you want to replace chest with " + object);
			if (confirm == 0)
				validate = true;
			// this.map_model.setErrorMessage("You cannot place a"+object+"here
			// as chest is already present");
			else
				validate = false;
		} else if (this.map_model.getMap_entry_door().equals(position)) {
			int confirm = JOptionPane.showConfirmDialog(this.map_view,
					"Do you want to replace Entry Door with " + object);
			if (confirm == 0)
				validate = true;
			// this.map_model.setErrorMessage("You cannot place a"+object+"here
			// as chest is already present");
			else
				validate = false;
		} else if (this.map_model.getMap_exit_door().equals(position)) {
			int confirm = JOptionPane.showConfirmDialog(this.map_view,
					"Do you want to replace Exit Door with " + object);
			if (confirm == 0)
				validate = true;
			// this.map_model.setErrorMessage("You cannot place a"+object+"here
			// as chest is already present");
			else
				validate = false;
		} else {
			if (this.map_model.getMap_walls().contains(position)) {
				int confirm = JOptionPane.showConfirmDialog(this.map_view,
						"Do you want to replace Wall with " + object);
				if (confirm == 0)
					validate = true;
				// this.map_model.setErrorMessage("You cannot place
				// a"+object+"here as chest is already present");
				else
					validate = false;
			}
			if (check_npc == 1) {
				int confirm = JOptionPane.showConfirmDialog(this.map_view,
						"Do you want to replace Enemy with " + object);
				if (confirm == 0)
					validate = true;
				// this.map_model.setErrorMessage("You cannot place
				// a"+object+"here as chest is already present");
				else
					validate = false;
			}
			if (check_npc == 2) {
				int confirm = JOptionPane.showConfirmDialog(this.map_view,
						"Do you want to replace Friend with " + object);
				if (confirm == 0)
					validate = true;
				// this.map_model.setErrorMessage("You cannot place
				// a"+object+"here as chest is already present");
				else
					validate = false;
			}
		}
		return validate;

	}

	/**
	 * Used to show confirmation message
	 * 
	 * @param message
	 * @return boolean
	 */
	private boolean confirmDialogue(String message) {
		boolean validate = true;
		int confirm = JOptionPane.showConfirmDialog(this.map_view, message);
		if (confirm == 0) {
			validate = true;
		} else {
			validate = false;
		}

		return validate;
	}

}
