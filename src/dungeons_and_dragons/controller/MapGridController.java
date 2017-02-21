/**
 * 
 */
package dungeons_and_dragons.controller;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dungeons_and_dragons.helper.Game_constants;
import dungeons_and_dragons.helper.MapButton;
import dungeons_and_dragons.model.GameMapModel;
import dungeons_and_dragons.view.MapFormView;
import dungeons_and_dragons.view.MapGridView;

/**
 * @author Urmil Kansara & Tejas Sadrani
 *
 */
public class MapGridController implements ActionListener,DocumentListener {
	
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
		this.map_view.setDocumentListener(this);
	}

	/**
	 * Action event of all the events are handled here
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(map_view.back_button))
		{
			new CreateGameController();
			map_view.dispose();
			
		}else if(e.getSource().equals(map_view.save_button))
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
		else if(e.getSource().equals(map_view.map_entry_door)){
			this.map_model.setType(Game_constants.ENTRY_DOOR);
			
		}else if(e.getSource().equals(map_view.map_exit_door)){
			this.map_model.setType(Game_constants.EXIT_DOOR);
			
		}
		else if(e.getSource().equals(map_view.map_wall)){
			this.map_model.setType(Game_constants.WALLS);
			
		}
		else if(e.getSource().equals(map_view.map_enemy)){
			this.map_model.setType(Game_constants.ENEMIES);
			
		}
		else if(e.getSource().equals(map_view.map_chest)){
			this.map_model.setType(Game_constants.CHEST);
			
		}
		else if(((MapButton)e.getSource()).getType().equals(Game_constants.GRID_BUTTON_TYPE)){
			Point position = new Point();
			position.x =  ((MapButton)e.getSource()).getxPos();
			position.y =  ((MapButton)e.getSource()).getyPos();
			
			
			if(this.map_model.getType() == Game_constants.WALLS)
			this.map_model.setMap_wall(position);
			
			else if(this.map_model.getType() == Game_constants.ENEMIES)
				this.map_model.setMap_characters(position);
			
			else if(this.map_model.getType() == Game_constants.ENTRY_DOOR)
				this.map_model.setMap_entry_door(position);
			
			else if(this.map_model.getType() == Game_constants.EXIT_DOOR)
				this.map_model.setMap_exit_door(position);
			
			else if(this.map_model.getType() == Game_constants.CHEST)
				this.map_model.setMap_chest(position);
			
			
			this.map_view.setButtonListener(this);
		}
		
	}

	//-------------- Yet to be coded ! ----------------
	
	
	
	@Override
	public void insertUpdate(DocumentEvent e) {
		
		Point p = new Point(5,5);
		
		if(e.getDocument().equals(map_view.map_height_textfield))
		{	
			p.setLocation((Integer.parseInt(map_view.map_height_textfield.getText())),5);
			map_model.setMap_size(p);
		}
		else if(e.getDocument().equals(map_view.map_width_textfield))
		{
			p.setLocation(5,(Integer.parseInt(map_view.map_width_textfield.getText())));
			map_model.setMap_size(p);
		}
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		Point p = new Point(5,5);
		
		if(e.getDocument().equals(map_view.map_height_textfield))
		{	
			p.setLocation((Integer.parseInt(map_view.map_height_textfield.getText())),5);
			map_model.setMap_size(p);
		}
		else if(e.getDocument().equals(map_view.map_width_textfield))
		{
			p.setLocation(5,(Integer.parseInt(map_view.map_width_textfield.getText())));
			map_model.setMap_size(p);
		}
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		
		Point p = new Point(5,5);
		
		if(e.getDocument().equals(map_view.map_height_textfield))
		{	
			p.setLocation((Integer.parseInt(map_view.map_height_textfield.getText())),5);
			map_model.setMap_size(p);
		}
		else if(e.getDocument().equals(map_view.map_width_textfield))
		{
			p.setLocation(5,(Integer.parseInt(map_view.map_width_textfield.getText())));
			map_model.setMap_size(p);
		}
		
	}
		
	}

	
	
	
