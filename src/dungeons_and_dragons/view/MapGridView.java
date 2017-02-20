/**
 * 
 */
package dungeons_and_dragons.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;

import dungeons_and_dragons.model.GameMapModel;

/**
 * @author User
 *
 */
public class MapGridView extends JFrame implements Observer {

	/**
	 * this variable used to set window title
	 * 
	 * @type String
	 */
	public String map_window_title = "Create Map";

	/**
	 * this variable used to give Item name
	 * 
	 * @type JLabel
	 */
	public JLabel map_name;

	/**
	 * this variable used to input name
	 * 
	 * @type JTextField
	 */
	public JTextField map_name_textfield;
	
	/**
	 * this variable used to give map width
	 * 
	 * @type JLabel
	 */
	public JLabel map_width;

	/**
	 * this variable used to input height
	 * 
	 * @type JTextField
	 */
	public JTextField map_width_textfield;
	
	/**
	 * this variable used to give map height
	 * 
	 * @type JLabel
	 */
	public JLabel map_height;

	/**
	 * this variable used to input height
	 * 
	 * @type JTextField
	 */
	public JTextField map_height_textfield;
	
	/**
	 * this variable used for going back to create map window
	 * 
	 * @type JButton
	 */
	public JButton back_button;

	/**
	 * this variable used to save map
	 * 
	 * @type JButton
	 */
	public JButton save_button;
	
	private JPanel main_panel;
	private JPanel sub_top_panel;
	private JPanel sub_bottom_panel;
	
	/**
	 *  this variable is used to store all the buttons in the grid map
	 *  
	 *  @type JButton
	 */
	private ArrayList<JButton> grid_button;
	
	private JRadioButton map_entry_door;
	private JRadioButton map_exit_door;
	private JRadioButton map_chest; 
	private JRadioButton map_enemy;
	private JRadioButton map_wall;
	private JLabel map_entry_color;
	private JLabel map_exit_color;
	private JLabel map_chest_color;
	private JLabel map_enemy_color;
	private JLabel map_wall_color;
	
	
	
	public MapGridView(GameMapModel map_model){
		this.setTitle(this.map_window_title);
		
		try{
		BufferedImage map_entry_color_image = ImageIO.read(new File("C:/Users/Tejas09/git/dungeons_and_dragons/res/yelllow.jpg"));
		map_entry_color = new JLabel(new ImageIcon(map_entry_color_image));
		//map_entry_color.setMaximumSize(new Dimension(10,10));
		
		BufferedImage map_exit_color_image = ImageIO.read(new File("C:/Users/Tejas09/git/dungeons_and_dragons/res/green.png"));
		map_exit_color = new JLabel(new ImageIcon(map_exit_color_image));
		//map_exit_color.setMaximumSize(new Dimension(10,10));
		
		BufferedImage map_chest_color_image = ImageIO.read(new File("C:/Users/Tejas09/git/dungeons_and_dragons/res/blue.jpg"));
		map_chest_color = new JLabel(new ImageIcon(map_chest_color_image));
		//map_chest_color.setMaximumSize(new Dimension(10,10));
		
		BufferedImage map_enemy_color_image = ImageIO.read(new File("C:/Users/Tejas09/git/dungeons_and_dragons/res/red.jpg"));
		map_enemy_color = new JLabel(new ImageIcon(map_enemy_color_image));
		//map_enemy_color.setMaximumSize(new Dimension(10,10));
		
		BufferedImage map_wall_color_image = ImageIO.read(new File("C:/Users/Tejas09/git/dungeons_and_dragons/res/grey.jpg"));
		map_wall_color = new JLabel(new ImageIcon(map_wall_color_image));
		//map_wall_color.setMaximumSize(new Dimension(10,10));
		
		}catch(IOException e){
			System.out.println(e);
		}
		
		// main panel covering body
		main_panel = new JPanel();
		main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.PAGE_AXIS));
		main_panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		getContentPane().add(main_panel);

		// sub top panel covering heading with details to be filled
		sub_top_panel = new JPanel();
		sub_top_panel.setLayout(new BoxLayout(sub_top_panel, BoxLayout.X_AXIS));
		sub_top_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		sub_top_panel.setMaximumSize(new Dimension(400, 100));
		
				
		// list panel to display contents inside the sub top panel
		JPanel listPane = new JPanel();

		listPane.setLayout((new GridLayout(2, 3, 5, 5)));
		listPane.setMaximumSize(new Dimension(400, 150));

		sub_top_panel.add(listPane);	
		listPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Initializing all labels
		map_name = new JLabel("Name");
		map_height = new JLabel("Height");
		map_width = new JLabel("Width");
		//item_score = new JLabel("Point");

		// Initializing label text field,dropdown of map height and width		
		map_name_textfield = new JTextField(1);
		map_height_textfield = new JTextField(1);
		map_width_textfield = new JTextField(1);


		// initializing back and next buttons
		back_button = new JButton("Back");
		save_button = new JButton("Save");
		// Adding necessary components into panel
		listPane.add(map_name);

		listPane.add(map_height);
		
		listPane.add(map_width);
		
		listPane.add(map_name_textfield);

		listPane.add(map_height_textfield);		

		listPane.add(map_width_textfield);

		main_panel.add(sub_top_panel);
		
		
		// sub bottom panel consisting of map and info regarding map
		sub_bottom_panel = new JPanel();
		sub_bottom_panel.setLayout((new GridLayout(1, 2, 5, 5)));
		sub_bottom_panel.setBorder(BorderFactory.createRaisedBevelBorder());
		sub_bottom_panel.setMaximumSize(new Dimension(500, 500));
		
				
		// info panel embedded inside sub bottom panel's right bottom corner
		JPanel leftGridPanel= new JPanel();

		leftGridPanel.setLayout(new BoxLayout(leftGridPanel, BoxLayout.Y_AXIS));
		leftGridPanel.setMaximumSize(new Dimension(250, 300));
		leftGridPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		// map panel embedded inside sub bottom panel's left bottom corner
		JPanel LeftGridMapPane= new JPanel();

		LeftGridMapPane.setLayout((new GridLayout(map_model.getMap_size().x, map_model.getMap_size().y, 1, 1)));
		LeftGridMapPane.setMaximumSize(new Dimension(250, 300));
		LeftGridMapPane.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		
		/*
		 * yet to be constructed on the basis of top information
		 */
		
		for(int i=0; i<(map_model.getMap_size().x)*(map_model.getMap_size().y); i++){
			LeftGridMapPane.add(new JButton());
		}
		leftGridPanel.add(LeftGridMapPane);
		
		
		// info panel embedded inside sub bottom panel's right bottom corner
		JPanel RightInfoPanel= new JPanel();

		RightInfoPanel.setLayout(new BoxLayout(RightInfoPanel, BoxLayout.Y_AXIS));
		RightInfoPanel.setMaximumSize(new Dimension(250, 300));
		RightInfoPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		// list panel consisting of information for map to display contents in map embedded inside right info panel
		JPanel RightInfoListPane = new JPanel();

		RightInfoListPane.setLayout((new GridLayout(6, 2, 5, 5)));
		RightInfoListPane.setMaximumSize(new Dimension(250, 300));
		RightInfoPanel.add(RightInfoListPane);	
		RightInfoListPane.setBorder(new BevelBorder(1));
		
		
		map_entry_door = new JRadioButton("Entry Door");
		map_exit_door = new JRadioButton("Exit Door");
		map_chest = new JRadioButton("Chest");
		map_enemy = new JRadioButton("Enemy");
		map_wall = new JRadioButton("Wall");
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(map_entry_door);
		bg.add(map_exit_door);
		bg.add(map_chest);
		bg.add(map_enemy);
		bg.add(map_wall);
		
		
		RightInfoListPane.add(map_entry_door);
		RightInfoListPane.add(map_entry_color);
		RightInfoListPane.add(map_exit_door);
		RightInfoListPane.add(map_exit_color);
		RightInfoListPane.add(map_chest);
		RightInfoListPane.add(map_chest_color);
		RightInfoListPane.add(map_enemy);
		RightInfoListPane.add(map_enemy_color);
		RightInfoListPane.add(map_wall);
		RightInfoListPane.add(map_wall_color);
		RightInfoListPane.add(back_button);
		RightInfoListPane.add(save_button);
				
		sub_bottom_panel.add(leftGridPanel);	
		sub_bottom_panel.add(RightInfoPanel);
		
		
		main_panel.add(sub_bottom_panel);

		this.setPreferredSize(new Dimension(500,500));

		// Display the window.
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

	/*public void setListener(MapFormController mapFormController) {
		// TODO Auto-generated method stub
		
	}*/
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
/**
 * Configuring listeners to Controllers
 * @param mapGridController
 */
	public void setListener(ActionListener mapGridController) {
			
			this.save_button.addActionListener(mapGridController);
			this.back_button.addActionListener(mapGridController);
			this.map_height_textfield.getDocument().addDocumentListener((DocumentListener) mapGridController);
			this.map_width_textfield.getDocument().addDocumentListener((DocumentListener) mapGridController);
		
	}
	
	

}
