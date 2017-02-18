/**
 * 
 */
package dungeons_and_dragons.controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dungeons_and_dragons.model.GameMapModel;
import dungeons_and_dragons.view.MapFormView;

/**
 * This class is for creating controller class of map view
 * 
 * Implemented with pair programming
 * 
 * 
 * @author Urmil Kansara & Tejas Sadrani
 *
 */
public class MapFormController implements ActionListener{

	
	MapFormView map_form_view;
	GameMapModel map_form_model;
	
	public MapFormController() {
		// TODO Auto-generated constructor stub
		this.map_form_model = new GameMapModel();
		this.map_form_view = new MapFormView();
		
		this.map_form_model.addObserver(map_form_view);
		this.map_form_view.setListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		if(arg0.getSource().equals(map_form_view.save_button))
		{	
			String map_name =	map_form_view.map_name_textfield.getText();
			map_form_model.setMap_name(map_name);
			String map_height = map_form_view.map_height_textfield.getText();
			Point store = new Point();
			
			store.x = Integer.parseInt(map_form_view.map_height_textfield.getText());
			store.y = Integer.parseInt(map_form_view.map_width_textfield.getText());
			map_form_model.setMap_size(store);
			map_form_model.save();
		}
		else if(arg0.getSource().equals(map_form_view.back_button))
		{	
			new CreateGameController();
			
			map_form_view.dispose();
		}
		
	}
	

}

