/**
 * 
 */
package dungeons_and_dragons.controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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
		
		this.map_model = new GameMapModel(5,5);
		this.map_view = new MapGridView(map_model);
		
		this.map_model.addObserver(map_view);
		this.map_view.setListener(this);
		/*this.map_view.setDocumentListener(this);*/
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
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
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

	
	
	
