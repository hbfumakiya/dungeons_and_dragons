/**
 * 
 */
package dungeons_and_dragons.controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import dungeons_and_dragons.helper.Game_constants;
import dungeons_and_dragons.helper.MapButton;
import dungeons_and_dragons.model.GameMapModel;
import dungeons_and_dragons.view.MapGridView;

/**
 * @author Urmil Kansara & Tejas Sadrani
 *
 */
public class MapGridController implements ActionListener {
	
	/**
	 *	This creates new map model which is being observed
	 *  @type GameMapModel
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
	 * MapGrid model and view are initialized
	 * Also view is binded to observer.
	 * <p>
	 * All the events of view are registered in constructor
	 */
	public MapGridController() {
		Point p = new Point();
		p.x = 5;
		p.y = 5;
		this.map_model = new GameMapModel(5,5);
		this.map_view = new MapGridView(p);
		
		this.map_model.addObserver(map_view);
		this.map_view.setListener(this);
	}

	/**
	 * Action event of all the events are handled here
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(map_view.back_button))
		{
			new CreateGameController();
			map_view.dispose();
			
		}
		else if(e.getSource().equals(map_view.save_button))
		{	
			/*yet to be written*/
		}
		else if(e.getSource().equals(map_view.submit))
		{	
			Point store = new Point();
			store.x = Integer.parseInt(this.map_view.map_height_textfield.getText());
			store.y = Integer.parseInt(this.map_view.map_width_textfield.getText());
			this.map_model.setMap_size(store);
			this.map_view.setButtonListener(this);
		}
		else if(e.getSource().equals(map_view.map_entry_door))
		{
			this.map_model.setMap_object_color_type(Game_constants.ENTRY_DOOR);
			
		}
		else if(e.getSource().equals(map_view.map_exit_door))
		{
			this.map_model.setMap_object_color_type(Game_constants.EXIT_DOOR);
			
		}
		else if(e.getSource().equals(map_view.map_wall))
		{
			this.map_model.setMap_object_color_type(Game_constants.WALLS);
			
		}
		else if(e.getSource().equals(map_view.map_enemy))
		{
			this.map_model.setMap_object_color_type(Game_constants.ENEMIES);
			
		}
		else if(e.getSource().equals(map_view.map_chest))
		{
			this.map_model.setMap_object_color_type(Game_constants.CHEST);
			
		}
		else if(((MapButton)e.getSource()).getButton_type().equals(Game_constants.GRID_BUTTON_TYPE))
		{
			Point position = new Point();
			position.x =  ((MapButton)e.getSource()).getxPos();
			position.y =  ((MapButton)e.getSource()).getyPos();
			
			
			if(this.map_model.getMap_object_color_type() == Game_constants.WALLS)
				{
					validateMapForExisting("wall",position);
					this.map_model.setMap_wall(position);
				}			
			else if(this.map_model.getMap_object_color_type() == Game_constants.ENEMIES)
				{
					validateMapForExisting("Enemy",position);
					this.map_model.setMap_enemy_loc(position,null);
				}
			else if(this.map_model.getMap_object_color_type() == Game_constants.ENTRY_DOOR)
				{
					validateMapForExisting("Entry Door",position);
					this.map_model.entryFlag = 1;
					this.map_model.setMap_entry_door(position);
				}
			else if(this.map_model.getMap_object_color_type() == Game_constants.EXIT_DOOR)
				{
					validateMapForExisting("Exit Door",position);
					this.map_model.exitFlag = 1;
					this.map_model.setMap_exit_door(position);
				}
			else if(this.map_model.getMap_object_color_type() == Game_constants.CHEST)
				{	
					validateMapForExisting("Chest",position);
					this.map_model.setMap_chest(position);
				}
			
			this.map_view.setButtonListener(this);
		}
		
	}

	/**
	 * Function used to check if there is any object already present in the position mentioned
	 * @param object 
	 * @param position
	 */
	private void validateMapForExisting(String object, Point position) {
		
		if(this.map_model.getMap_chest().equals(position))
		{
			this.map_model.setErrorMessage("You cannot place a"+object+"here as chest is already present");
		}
		else if(this.map_model.getMap_entry_door().equals(position))
		{
			this.map_model.setErrorMessage("You cannot place a"+object+"here as entry door is already present");
		}
		else if(this.map_model.getMap_exit_door().equals(position))
		{
			this.map_model.setErrorMessage("You cannot place a"+object+"here as exit door is already present");
		}
		else
		{
			for(int i=0;i<this.map_model.getMap_walls().size();i++){
				if(this.map_model.getMap_walls().get(i).equals(position)){
					this.map_model.setErrorMessage("You cannot place a"+object+"here as wall is already present");
				}
			}
			for(int i=0;i<this.map_model.getMap_enemy_loc().size();i++){
				if(this.map_model.getMap_enemy_loc().keySet().contains(position)){
					this.map_model.setErrorMessage("You cannot place a"+object+"here as enemy is already present");
				}
			}
		}
		
		
	}


		
	}

	
	
	
