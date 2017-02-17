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

public class ManageCharacterView extends JFrame implements Observer {

	/**
	 * this variable used to set window title
	 * 
	 * @type String
	 */
	private String window_title = "Create Character";
	
	/**
	 * this variable used for character name button
	 * 
	 * @type JLabel
	 */
	private JLabel charactername_label;
	
	/**
	 * this variable used for additem label
	 * 
	 * @type JLabel
	 */
	private JLabel additem_label;

	/**
	 * this variable used for main panel
	 * 
	 * @type JPanel
	 */
	private JPanel main_panel;
	
	/**
	 * this variable used for sub panel
	 * 
	 * @type JPanel
	 */
	private JPanel sub_panel;
	
	/**
	 * this variable used for list panel
	 * 
	 * @type JPanel
	 */
	private JPanel list_panel;
	
	/**
	 * this variable used for character name textfield
	 * 
	 * @type JTextField
	 */
	private JTextField charactername_textfield;
	
	/**
	 * this variable used for item combobox
	 * 
	 * @type JComboBox
	 */
	private JComboBox<String> item_combobox;
	
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
		
		// set minimum size of frame
		this.setPreferredSize(new Dimension(320, 220));

		// Display the window.
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}
	

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void setListener(ActionListener e) {
		// TODO Auto-generated method stub
		this.item_combobox.addActionListener(e);
		
	}

}
