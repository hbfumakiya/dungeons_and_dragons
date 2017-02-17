package dungeons_and_dragons.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * @author Hrangi Naik
 *
 */

public class ManageCharacterView extends JFrame implements Observer,View {

	/**
	 * this variable used to set window title
	 * 
	 * @type String
	 */
	public String window_title = "Create Character";
	
	/**
	 * this variable used for character name button
	 * 
	 * @type JLabel
	 */
	public JLabel charactername_label;
	
	/**
	 * this variable used for additem label
	 * 
	 * @type JLabel
	 */
	public JLabel additem_label;

	/**
	 * this variable used for level label
	 * 
	 * @type JLabel
	 */
	public JLabel level_label;
	
	/**
	 * this variable used for backpack label
	 * 
	 * @type JLabel
	 */
	public JLabel backpack_label;
	
	/**
	 * this variable used for main panel
	 * 
	 * @type JPanel
	 */
	public JPanel main_panel;
	
	/**
	 * this variable used for sub panel
	 * 
	 * @type JPanel
	 */
	public JPanel sub_panel;
	
	/**
	 * this variable used for list panel
	 * 
	 * @type JPanel
	 */
	public JPanel list_panel;
	
	/**
	 * this variable used for character name textfield
	 * 
	 * @type JTextField
	 */
	public JTextField charactername_textfield;
	
	/**
	 * this variable used for level textfield
	 * 
	 * @type JTextField
	 */
	public JTextField level_textfield;
	
	/**
	 * this variable used for item combobox
	 * 
	 * @type JComboBox
	 */
	public JComboBox<String> item_combobox;
	
	/**
	 * this variable used for backpack combobox
	 * 
	 * @type JComboBox
	 */
	public JComboBox<String> backpack_combobox;
	
	/**
	 * this variable used for save button
	 * 
	 * @type JButton
	 */
	public JButton save;
	
	/**
	 * this variable used for back button
	 * 
	 * @type JButton
	 */
	public JButton back;
	
	public ManageCharacterView() {
		// TODO Auto-generated constructor stub
		// initialize game window
				this.initializeWindow();
				
				//close frame while user click on close
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * initialize character window
	 * 
	 */
	private void initializeWindow() {
		
		// set window title
		this.setTitle(this.window_title);
		
		main_panel = new JPanel();
		main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.PAGE_AXIS));
		main_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(main_panel);
		
		sub_panel = new JPanel();
		sub_panel.setLayout(new BoxLayout(sub_panel, BoxLayout.LINE_AXIS));
		sub_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		sub_panel.setMaximumSize(new Dimension(300, 1000));
		
		main_panel.add(sub_panel);

		list_panel = new JPanel();
		list_panel.setLayout(new GridLayout(5, 2, 5, 5));
		list_panel.setMaximumSize(new Dimension(300, 150));

		sub_panel.add(list_panel);
		
		charactername_label=new JLabel("Enter a Name");
		list_panel.add(charactername_label);

		charactername_textfield=new JTextField();
		charactername_textfield.setPreferredSize(new Dimension(50, 40));
		charactername_textfield.setAlignmentX(Component.LEFT_ALIGNMENT);
		list_panel.add(charactername_textfield);
		
		additem_label=new JLabel("Add items");
		list_panel.add(additem_label);
		
		item_combobox=new JComboBox<String>();
		item_combobox.setAlignmentX(Component.LEFT_ALIGNMENT);
		list_panel.add(item_combobox);
		
		level_label=new JLabel("Enter Level");
		list_panel.add(level_label);
		
		level_textfield=new JTextField();
		level_textfield.setPreferredSize(new Dimension(50, 40));
		level_textfield.setAlignmentY(LEFT_ALIGNMENT);
		list_panel.add(level_textfield);
		
		backpack_label=new JLabel("BackPack");
		list_panel.add(backpack_label);
		
		backpack_combobox = new JComboBox(new String[]{"1","2"});
		backpack_combobox.setAlignmentX(LEFT_ALIGNMENT);
		list_panel.add(backpack_combobox);
		
		save=new JButton("Save");
		list_panel.add(save);
		
		back=new JButton("Back");
		list_panel.add(back);
		
		// set minimum size of frame
		this.setPreferredSize(new Dimension(320, 220));

		// Display the window.
		this.pack();
		this.setLocationRelativeTo(null);

	}
	

	@Override
	public void update(Observable arg0, Object arg1) {
		
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		// TODO Auto-generated method stub
		this.item_combobox.addActionListener(actionListener);
		this.save.addActionListener(actionListener);
		this.back.addActionListener(actionListener);
	}

}
