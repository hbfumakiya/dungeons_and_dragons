package dungeons_and_dragons.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dungeons_and_dragons.helper.Game_constants;
import dungeons_and_dragons.model.ItemModel;
//import game.play.ItemWindowModel/

//import game.helper.Game_constants;

/**
 * This class is to open new Window
 * 
 * @author : Urmil Kansara
 */
public class ItemView extends JFrame implements Observer {

	/**
	 * this variable used to set window title
	 * 
	 * @type String
	 */
	public String item_window_title = "Manage Item";

	/**
	 * this variable used to give Item name
	 * 
	 * @type JLabel
	 */
	public JLabel item_name;

	/**
	 * this variable used to input name
	 * 
	 * @type JTextField
	 */
	public JTextField item_name_field;

	/**
	 * this variable used to give Item Type
	 * 
	 * @type JLabel
	 */
	public JLabel item_type;

	/**
	 * this variable used to get Item Type
	 * 
	 * @type JComboBox
	 */
	public JComboBox<String> item_type_field;

	/**
	 * this variable used to for Item Ability
	 * 
	 * @type JLabel
	 */
	public JLabel item_ability;

	/**
	 * this variable used to get Item Ability
	 * 
	 * @type JComboBox
	 */
	public JComboBox<String> item_ability_field;

	/**
	 * this variable used to give Item score
	 * 
	 * @type JLabel
	 */
	public JLabel item_score;

	/**
	 * this variable used to get item Score
	 * 
	 * @type JTextField
	 */
	public JTextField item_score_field;

	/**
	 * this variable used for going back to create game window
	 * 
	 * @type JButton
	 */
	public JButton back_button;

	/**
	 * this variable used to save item
	 * 
	 * @type JButton
	 */
	public JButton save_item;

	/**
	 * this variable is used for validating same item type click multiple types
	 * 
	 * @type Integer
	 */
	
	/**
	 * this variable used to save item
	 * 
	 * @type JButton
	 */
	public JButton update_item;
	private JPanel main_panel;
	private JPanel sub_panel;
	
	/**
	 * Default constructor of item View which initializes combobox, button and labels.
	 * 
	 */
	public ItemView() {
		// TODO Auto-generated constructor stub

		// set window title
		this.setTitle(this.item_window_title);

		// main panel
		main_panel = new JPanel();
		main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.PAGE_AXIS));
		main_panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		getContentPane().add(main_panel);

		// sub panel
		sub_panel = new JPanel();
		sub_panel.setLayout(new BoxLayout(sub_panel, BoxLayout.LINE_AXIS));
		sub_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		sub_panel.setMaximumSize(new Dimension(300, 1000));

		main_panel.add(sub_panel);
		// Lay out the label and scroll pane from top to bottom.
		JPanel listPane = new JPanel();

		listPane.setLayout((new GridLayout(5, 2, 5, 5)));
		listPane.setMaximumSize(new Dimension(300, 150));

		sub_panel.add(listPane);
		// listPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Initializing all labels
		item_name = new JLabel("Name");
		item_ability = new JLabel("Ability");
		item_type = new JLabel("Item Type");
		item_score = new JLabel("Point");

		// Initializing label text field,dropdown of item type,item ability and
		// item score
		item_name_field = new JTextField(10);
		item_ability_field = new JComboBox<String>();
		item_type_field = new JComboBox<String>();
		item_score_field = new JTextField(1);

		// initializing back and save buttons
		back_button = new JButton("Back");
		save_item = new JButton("Save");
		update_item = new JButton("Uodate");

		// filling the details of type item type combobox
		item_type_field.addItem(Game_constants.HELMET);
		item_type_field.addItem(Game_constants.ARMOR);
		item_type_field.addItem(Game_constants.SHIELD);
		item_type_field.addItem(Game_constants.RING);
		item_type_field.addItem(Game_constants.BELT);
		item_type_field.addItem(Game_constants.BOOTS);
		item_type_field.addItem(Game_constants.WEAPON);

		// filling the details of type item ability
		item_ability_field.addItem(Game_constants.INTELLIGENCE);
		item_ability_field.addItem(Game_constants.WISDOM);
		item_ability_field.addItem(Game_constants.ARMOR_CLASS);

		// Adding necessary components into panel
		listPane.add(item_name);

		listPane.add(item_name_field);

		listPane.add(item_type);

		listPane.add(item_type_field);

		listPane.add(item_ability);

		listPane.add(item_ability_field);

		listPane.add(item_score);

		listPane.add(item_score_field);

		listPane.add(back_button);
		listPane.add(save_item);

		this.setPreferredSize(new Dimension(320, 220));

		// Display the window.
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	public ItemView(ItemModel itemModel) 
	{
		this.setTitle(this.item_window_title);

		// main panel
		main_panel = new JPanel();
		main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.PAGE_AXIS));
		main_panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		getContentPane().add(main_panel);

		// sub panel
		sub_panel = new JPanel();
		sub_panel.setLayout(new BoxLayout(sub_panel, BoxLayout.LINE_AXIS));
		sub_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		sub_panel.setMaximumSize(new Dimension(300, 1000));

		main_panel.add(sub_panel);
		// Lay out the label and scroll pane from top to bottom.
		JPanel listPane = new JPanel();

		listPane.setLayout((new GridLayout(5, 2, 5, 5)));
		listPane.setMaximumSize(new Dimension(300, 150));

		sub_panel.add(listPane);
		// listPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Initializing all labels
		item_name = new JLabel("Name");
		item_ability = new JLabel("Ability");
		item_type = new JLabel("Item Type");
		item_score = new JLabel("Point");

		// Initializing label text field,dropdown of item type,item ability and
		// item score
		item_name_field = new JTextField(10);
		item_ability_field = new JComboBox<String>();
		item_type_field = new JComboBox<String>();
		item_score_field = new JTextField(1);

		// initializing back and save buttons
		back_button = new JButton("Back");
		save_item = new JButton("Save");
		update_item = new JButton("Update");
		// filling the details of type item type combobox
		item_type_field.addItem(Game_constants.HELMET);
		item_type_field.addItem(Game_constants.ARMOR);
		item_type_field.addItem(Game_constants.SHIELD);
		item_type_field.addItem(Game_constants.RING);
		item_type_field.addItem(Game_constants.BELT);
		item_type_field.addItem(Game_constants.BOOTS);
		item_type_field.addItem(Game_constants.WEAPON);

		// filling the details of type item ability
		item_ability_field.addItem(Game_constants.INTELLIGENCE);
		item_ability_field.addItem(Game_constants.WISDOM);
		item_ability_field.addItem(Game_constants.ARMOR_CLASS);
		
		item_name_field.setText(itemModel.getItem_name());
		item_type_field.setSelectedItem(itemModel.getItem_type());
		item_ability_field.setSelectedItem(itemModel.getItem_type());
		item_score_field.setText(String.valueOf(itemModel.getItem_point()));
		
		// Adding necessary components into panel
		listPane.add(item_name);

		listPane.add(item_name_field);

		listPane.add(item_type);

		listPane.add(item_type_field);

		listPane.add(item_ability);

		listPane.add(item_ability_field);

		listPane.add(item_score);

		listPane.add(item_score_field);

		listPane.add(back_button);
		listPane.add(update_item);

		this.setPreferredSize(new Dimension(320, 220));

		// Display the window.
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	
	/**
	 * This is overridden method of Observable superclass
	 * 
	 * @param o  Observable object of model class
	 * @param arg object of model Class
	 */

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		DefaultComboBoxModel selected_model = ((ItemModel) o).getItemAbility();

			item_ability_field.setModel(selected_model);
			
			
		
	}

	/**
	 * Registering the listener method of actions
	 * 
	 * @param e it defines listening event of actions
	 */
	public void setListener(ActionListener e) {
		// TODO Auto-generated method stub
		this.item_type_field.addActionListener(e);
		this.item_ability_field.addActionListener(e);
		// this.item_score_field.addActionListener(e);
		this.save_item.addActionListener(e);
		this.back_button.addActionListener(e);
		this.update_item.addActionListener(e);

	}

}
