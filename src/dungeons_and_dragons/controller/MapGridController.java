package dungeons_and_dragons.controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import dungeons_and_dragons.helper.Game_constants;
import dungeons_and_dragons.helper.MapButton;
import dungeons_and_dragons.helper.MapValidator;
import dungeons_and_dragons.model.GameMapModel;
import dungeons_and_dragons.view.MapGridView;

/**
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

	/**
	 * Default constructor of Map Grid controller
	 * <p>
	 * MapGrid model and view are initialized Also view is binded to observer.
	 * <p>
	 * All the events of view are registered in constructor
	 */

	public static int finder = 0;

	public MapGridController() {
		Point p = new Point();
		p.x = 5;
		p.y = 5;
		this.map_model = new GameMapModel(5, 5);
		this.map_view = new MapGridView(p);

		this.map_model.addObserver(map_view);
		this.map_view.setListener(this);
	}
	
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
	 * Action event of all the events are handled here
	 * 
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
					//ArrayList test = new ArrayList();
					Set mapSet = new HashSet(this.map_model.getMap_enemy_loc());
					Iterator mapIterator = mapSet.iterator();
					System.out.println("check1");
					while (mapIterator.hasNext()) {
						System.out.println("check2");
						Point p = (Point) mapIterator.next();
						if (new MapValidator(this.map_view, this.map_model).findPath(p	)) {
							count++;
							System.out.println("check3");
						}
					}
					if (count == this.map_model.getMap_enemy_loc().size()) {
						System.out.println("check4");
						System.out.println(
								"In MapGridController.actionPerformed and ready for save button as map is valid");
						
						this.map_model.setMap_name(this.map_view.map_name_textfield.getText());
						this.map_model.save();
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

		} else if(e.getSource().equals(map_view.update_button))
		{
			
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
					//ArrayList test = new ArrayList();
					Set mapSet = new HashSet(this.map_model.getMap_enemy_loc());
					Iterator mapIterator = mapSet.iterator();
					System.out.println("check1");
					while (mapIterator.hasNext()) {
						System.out.println("check2");
						Point p = (Point) mapIterator.next();
						if (new MapValidator(this.map_view, this.map_model).findPath(p)) {
							count++;
							System.out.println("check3");
						}
					}
					if (count == this.map_model.getMap_enemy_loc().size()) {
						System.out.println("check4");
						System.out.println(
								"In MapGridController.actionPerformed and ready for update button as map is valid");
						
						this.map_model.setMap_name(this.map_view.map_name_textfield.getText());
						this.map_model.update();
						
						new ManageMapController();
						this.map_view.dispose();
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
			if(this.map_view.map_name_textfield.getText().equals(""))
			{
				JOptionPane.showOptionDialog(null,
						"Please Enter Valid Name",
						"Invalid Name", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {},
						null);
				return;
			}
			try{
			Point store = new Point();
			store.x = Integer.parseInt(this.map_view.map_height_textfield.getText());
			store.y = Integer.parseInt(this.map_view.map_width_textfield.getText());
			
			if(this.map_model.getMap_size().getX() != store.x || this.map_model.getMap_size().getY() != store.y)
			{
				//to reset the map grid if they have changed the height or width of map 
			this.map_model.resetAll();
			
			}
			{
				//So that at time of loading same height and width all entry door and exit door remain in same place
				this.map_model.entryFlag = 1;
				this.map_model.exitFlag = 1;
			}
			
			this.map_model.setFinder(finder);
			
			this.map_model.setMap_size(store);
			this.map_view.setButtonListener(this);
			}
			catch(NumberFormatException ex){
				JOptionPane.showOptionDialog(null,
						"Please Enter Valid Height/Width",
						"Invalid Name", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {},
						null);
				
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

		} else if (((MapButton) e.getSource()).getButton_type().equals(Game_constants.GRID_BUTTON_TYPE)) {
			Point position = new Point();
			position.x = ((MapButton) e.getSource()).getxPos();
			position.y = ((MapButton) e.getSource()).getyPos();
			String mes;

			if (this.map_model.getMap_object_color_type() == Game_constants.WALLS) {

				if (validateMapForExisting("wall", position)) {
					if (this.map_model.getMap_enemy_loc().contains(position))
						this.map_model.removeEnemy(position);
					else if (this.map_model.getMap_chest().equals(position))
						this.map_model.removeChest(position);
					else if (this.map_model.getMap_entry_door().equals(position))
						this.map_model.removeEntryDoor(position);
					else if (this.map_model.getMap_exit_door().equals(position))
						this.map_model.removeExitDoor(position);

					this.map_model.setMap_wall(position);
					this.map_view.setButtonListener(this);
				}

			} else if (this.map_model.getMap_object_color_type() == Game_constants.ENEMIES) {

				if (validateMapForExisting("Enemy", position)) {

					if (this.map_model.getMap_walls().contains(position))
						this.map_model.removeWall(position);
					else if (this.map_model.getMap_chest().equals(position))
						this.map_model.removeChest(position);
					else if (this.map_model.getMap_entry_door().equals(position))
						this.map_model.removeEntryDoor(position);
					else if (this.map_model.getMap_exit_door().equals(position))
						this.map_model.removeExitDoor(position);

					this.map_model.setMap_enemy_loc(position);
					this.map_view.setButtonListener(this);
				}

			} else if (this.map_model.getMap_object_color_type() == Game_constants.ENTRY_DOOR) {

				if (validateMapForExisting("Entry Door", position)) {

					if (this.map_model.getMap_enemy_loc().contains(position))
						this.map_model.removeEnemy(position);
					else if (this.map_model.getMap_chest().equals(position))
						this.map_model.removeChest(position);
					else if (this.map_model.getMap_walls().contains(position))
						this.map_model.removeWall(position);
					else if (this.map_model.getMap_exit_door().equals(position))
						this.map_model.removeExitDoor(position);

					this.map_model.entryFlag = 1;
					this.map_model.setMap_entry_door(position);
					this.map_view.setButtonListener(this);
				}

			} else if (this.map_model.getMap_object_color_type() == Game_constants.EXIT_DOOR) {

				if (validateMapForExisting("Exit Door", position)) {

					if (this.map_model.getMap_enemy_loc().contains(position))
						this.map_model.removeEnemy(position);
					else if (this.map_model.getMap_chest().equals(position))
						this.map_model.removeChest(position);
					else if (this.map_model.getMap_walls().contains(position))
						this.map_model.removeWall(position);
					else if (this.map_model.getMap_entry_door().equals(position))
						this.map_model.removeEntryDoor(position);

					this.map_model.exitFlag = 1;
					this.map_model.setMap_exit_door(position);
					this.map_view.setButtonListener(this);
				}

			} else if (this.map_model.getMap_object_color_type() == Game_constants.CHEST) {

				if (validateMapForExisting("Chest", position)) {
					if (this.map_model.getMap_enemy_loc().contains(position))
						this.map_model.removeEnemy(position);
					else if (this.map_model.getMap_entry_door().equals(position))
						this.map_model.removeEntryDoor(position);
					else if (this.map_model.getMap_walls().contains(position))
						this.map_model.removeWall(position);
					else if (this.map_model.getMap_exit_door().equals(position))
						this.map_model.removeExitDoor(position);

					this.map_model.setMap_chest(position);
					this.map_view.setButtonListener(this);
				}

			} else if (this.map_model.getMap_object_color_type() == null) {

				if (this.map_model.getMap_enemy_loc().contains(position)) {
					this.map_model.removeEnemy(position);
					this.map_model.callObservers();
					this.map_view.setButtonListener(this);
				} else if (this.map_model.getMap_chest().equals(position)) {
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
	 * @param object
	 * @param position
	 * @return String
	 */
	private boolean validateMapForExisting(String object, Point position) {

		boolean validate = true;
		if (this.map_model.getMap_chest().equals(position)) {
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
			if (this.map_model.getMap_enemy_loc().contains(position)) {
				int confirm = JOptionPane.showConfirmDialog(this.map_view,
						"Do you want to replace Enemy with " + object);
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
