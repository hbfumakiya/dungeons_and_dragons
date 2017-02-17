package dungeons_and_dragons.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import dungeons_and_dragons.model.ItemModel;
import dungeons_and_dragons.view.ItemView;
/**
 * This class call item controller 
 * 
 * @author Urmil Kansara
 *
 */
public class ItemController implements ActionListener {
	
	JComboBox field_item;
	ItemModel model;
	ItemView view;
	public ItemController() {
		// TODO Auto-generated constructor stub
		//this.field_item = item_field;
		System.out.println("Inside Controller constructor");
		this.model = new ItemModel();
		this.view = new ItemView();
		
		this.model.addObserver(view);
		this.view.setListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Inside Controller action performed");
		System.out.println(arg0.getSource()+" "+arg0.getActionCommand());
		if(arg0.getSource().equals(view.item_type_field))
		{
			String get_item_type = (String) view.item_type_field.getSelectedItem();
			System.out.println(get_item_type);
			
			model.itemTypeSelected(get_item_type);
			
		}
		
	}
	
	

}