/**
 * 
 */
package dungeons_and_dragons.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dungeons_and_dragons.controller.ItemController;
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
	public JTextField item_name_field ;

	
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
	public JTextField item_score_field ;
	
	
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
	 *  @type Integer
	 */
	
    public	int item_type_validator;
    
    
    
    
    
	
    
    
    
	public ItemView() {
		// TODO Auto-generated constructor stub
		
		// set window title
				this.setTitle(this.item_window_title);
				
				//Lay out the label and scroll pane from top to bottom.
				JPanel listPane = new JPanel();
				
				listPane.setLayout((new GridBagLayout()));
				listPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				
				//initialize validator for item type
				item_type_validator = 0;
				
				//Initializing all labels
				item_name = new JLabel("Name");
				item_ability = new JLabel("Ability");
				item_type = new JLabel("Item Type");
				item_score = new JLabel("Point");
				
				
				
				//Initializing label text field,dropdown of item type,item ability and item score
				item_name_field = new JTextField(10);
				item_ability_field = new JComboBox<String>();
				item_type_field = new JComboBox<String>();
				item_score_field = new JTextField(1);
				
				//initializing back and save buttons
				back_button = new JButton("Back");
				save_item = new JButton("Save");
				
				
				//adding name listener
				//item_name_field.addActionListener(this);
				
				//adding item type listener
				//item_type_field.addActionListener(this);
				
				//adding ability listener
				//item_ability_field.addActionListener(this);
				
				//adding score listener
				//item_score_field.addActionListener(this);
				
				
				//filling the details of type item type combobox
				item_type_field.addItem("Select Item Type");
				item_type_field.addItem(Game_constants.HELMET);
				item_type_field.addItem(Game_constants.ARMOR);
				item_type_field.addItem(Game_constants.SHIELD);
				item_type_field.addItem(Game_constants.RING);
				item_type_field.addItem(Game_constants.BELT);
				item_type_field.addItem(Game_constants.BOOTS);
				item_type_field.addItem(Game_constants.WEAPON);
				
				
//				//filling details of item ability
//				item_ability_field.addItem("Select your Ability");
				
				//sizes
				item_name.setPreferredSize(new Dimension(50, 40));
				item_type.setPreferredSize(new Dimension(70, 30));
				item_ability.setPreferredSize(new Dimension(50, 40));
				item_score.setPreferredSize(new Dimension(50, 30));
				
				
				
				//Container contentPane = getContentPane();
				getContentPane().add(new JButton(),BorderLayout.WEST);
				getContentPane().add(new JButton(),BorderLayout.EAST);
				getContentPane().add(new JButton(),BorderLayout.NORTH);
				getContentPane().add(new JButton(),BorderLayout.NORTH);
				getContentPane().add(listPane, BorderLayout.CENTER);
		        getContentPane().add(BorderLayout.SOUTH, back_button);
		        getContentPane().add(BorderLayout.SOUTH, save_item);
		        //getContentPane().add(BorderLayout.NORTH, title);

		        //panel.setLayout);
		        //panel.setBackground(Color.green);
		       // getContentPane().add(listPane);
		        GridBagConstraints left = new GridBagConstraints();
		        left.anchor = GridBagConstraints.EAST;
		        GridBagConstraints right = new GridBagConstraints();
		        right.weightx = 2.0;
		        right.anchor = GridBagConstraints.WEST;
		       // right.fill = GridBagConstraints.HORIZONTAL;
		        right.gridwidth = GridBagConstraints.REMAINDER;
		        
		        //add item name
		        listPane.add(item_name, left);
		        listPane.add(Box.createHorizontalStrut(5));	
		        listPane.add(item_name_field, right);
		        //listPane.add(Box.createRigidArea(new Dimension(5,0)));
		       //listPane.add(Box.createVerticalStrut(5));
		        
		        
		        //add item type
		        listPane.add(item_type, left);
		        listPane.add(Box.createHorizontalStrut(5));
		        listPane.add(item_type_field, right);
		        
		        //add item ability
		        listPane.add(item_ability, left);
		       listPane.add(Box.createHorizontalStrut(5));
		        listPane.add(item_ability_field, right);
		       
		        
		        //add score
		        listPane.add(item_score, left);
		       listPane.add(Box.createHorizontalStrut(5));
		        listPane.add(item_score_field, right);
		       //listPane.add(Box.createVerticalStrut(5));
		        listPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		        
		        item_ability_field.setVisible(false);
		        item_ability.setVisible(false);
		        
		        item_score_field.setVisible(false);
		        item_score.setVisible(false);
		        
		        this.setMinimumSize(new Dimension(300, 300));
				
				//Display the window.
		        this.pack();
		        this.setLocationRelativeTo(null);
		        this.setVisible(true);
				
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		
//		
//		//this variable created to get the item type selected.
////		if(e.getSource().equals(item_type_field)) {
////			
////		}
//		String get_item_type = (String) item_type_field.getSelectedItem();
//		System.out.println(get_item_type);
//		
//		
//		 // this variable created to get the item ability selected.
//		 
//		String get_item_ability = null;
//		
//		if(!get_item_type.equals("Select Item Type"))
//		{ 
//			item_ability_field.setVisible(true);
//			item_ability.setVisible(true);
//			
//			item_score.setVisible(true);
//			item_score_field.setVisible(true);
//			if(get_item_type.equals(Game_constants.HELMET))
//			{
//				item_ability_field.setModel(GameHELMET_MODEL);
//
//			}
//			else if (get_item_type.equals(Game_constants.ARMOR))
//			{
//				
//				item_ability_field.setModel(ARMOR_MODEL);
//			}
//			else if (get_item_type.equals(Game_constants.SHIELD))
//			{
//				
//				item_ability_field.setModel(SHIELD_MODEL);
//			}
//			else if (get_item_type.equals(Game_constants.RING))
//			{
//				
//				item_ability_field.setModel(RING_MODEL);
//			}
//			else if (get_item_type.equals(Game_constants.BELT))
//			{
//				
//				item_ability_field.setModel(BELT_MODEL);
//			}
//			else if (get_item_type.equals(Game_constants.BOOTS))
//			{
//				
//				item_ability_field.setModel(BOOTS_MODEL);
//			}
//			else if (get_item_type.equals(Game_constants.WEAPON))
//			{
//				
//				item_ability_field.setModel(WEAPON_MODEL);
//			}
//		}
//		else{
//			item_ability_field.setVisible(false);
//			item_ability.setVisible(false);
//			
//			item_score.setVisible(false);
//			item_score_field.setVisible(false);
//			
//		}
//		
//	}

	

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		DefaultComboBoxModel x = ((ItemModel) o).getItemAbility();

		if(!x.equals("Select Item Type"))
		{ 
			item_ability_field.setModel(x);
			item_ability_field.setVisible(true);
			item_ability.setVisible(true);
			
			item_score.setVisible(true);
			item_score_field.setVisible(true);
		}
	}

	public void setListener(ActionListener e) {
		// TODO Auto-generated method stub
		this.item_type_field.addActionListener(e);
		this.item_ability_field.addActionListener(e);
		//this.item_score_field.addActionListener(e);
		this.save_item.addActionListener(e);
		this.back_button.addActionListener(e);
		
		
	}

}

