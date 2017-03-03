/**
 * 
 */
package dungeons_and_dragons.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import dungeons_and_dragons.controller.ManageMapController;
import dungeons_and_dragons.helper.Game_constants;
import dungeons_and_dragons.helper.MapButton;
import dungeons_and_dragons.model.GameMapModel;

/**
 * This class is created to show the view for map.
 * 
 * @author Urmil Kansara
 *
 */
public class MapView extends JFrame implements ActionListener {

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
	 * @type JLabel
	 */
	public JLabel map_name_textfield;

	/**
	 * this variable used to give map width
	 * 
	 * @type JLabel
	 */
	public JLabel map_width;

	/**
	 * this variable used to input height
	 * 
	 * @type JLabel
	 */
	public JLabel map_width_textfield;

	/**
	 * this variable used to give map height
	 * 
	 * @type JLabel
	 */
	public JLabel map_height;

	/**
	 * this variable used to input height
	 * 
	 * @type JLabel
	 */
	public JLabel map_height_textfield;

	/**
	 * this variable used for going back to create map window
	 * 
	 * @type JButton
	 */
	public JButton back_button;

	private JPanel main_panel;
	private JPanel sub_top_panel;
	private JPanel sub_bottom_panel;

	/**
	 * this variable is used to store all the buttons in the grid map
	 * 
	 * @type JButton
	 */
	public MapButton[][] maps;

	public JLabel map_entry_door;
	public JLabel map_exit_door;
	public JLabel map_chest;
	public JLabel map_enemy;
	public JLabel map_wall;
	private JLabel map_entry_color;
	private JLabel map_exit_color;
	private JLabel map_chest_color;
	private JLabel map_enemy_color;
	private JLabel map_wall_color;
	private JLabel empty;

	private int index = 0;

	private GameMapModel model;

	private static int check = 0;

	/**
	 * Constructor to view the map
	 * 
	 * @param map
	 */
	public MapView(GameMapModel map) {
		this.setTitle(this.map_window_title);

		try {
			BufferedImage map_entry_color_image = ImageIO.read(new File("res/yelllow.jpg"));
			map_entry_color = new JLabel(new ImageIcon(map_entry_color_image));
			// map_entry_color.setMaximumSize(new Dimension(10,10));

			BufferedImage map_exit_color_image = ImageIO.read(new File("res/green.png"));
			map_exit_color = new JLabel(new ImageIcon(map_exit_color_image));
			// map_exit_color.setMaximumSize(new Dimension(10,10));

			BufferedImage map_chest_color_image = ImageIO.read(new File("res/blue.jpg"));
			map_chest_color = new JLabel(new ImageIcon(map_chest_color_image));
			// map_chest_color.setMaximumSize(new Dimension(10,10));

			BufferedImage map_enemy_color_image = ImageIO.read(new File("res/red.jpg"));
			map_enemy_color = new JLabel(new ImageIcon(map_enemy_color_image));
			// map_enemy_color.setMaximumSize(new Dimension(10,10));

			BufferedImage map_wall_color_image = ImageIO.read(new File("res/grey.jpg"));
			map_wall_color = new JLabel(new ImageIcon(map_wall_color_image));
			// map_wall_color.setMaximumSize(new Dimension(10,10));

		} catch (IOException e) {
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

		listPane.setLayout((new GridLayout(2, 4, 5, 5)));
		listPane.setMaximumSize(new Dimension(400, 150));

		sub_top_panel.add(listPane);
		listPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Initializing all labels
		map_name = new JLabel("Name");
		map_height = new JLabel("Height");
		map_width = new JLabel("Width");
		empty = new JLabel();
		// item_score = new JLabel("Point");

		// Initializing label text field,dropdown of map height and width
		map_name_textfield = new JLabel(map.getMap_name());
		map_height_textfield = new JLabel(String.valueOf(map.getMap_size().getX()));
		map_width_textfield = new JLabel(String.valueOf(map.getMap_size().getY()));

		// initializing back,next and submit buttons
		back_button = new JButton("Back");
		back_button.addActionListener(this);
		// save_button = new JButton("Save");
		// submit = new JButton("Submit");
		// Adding necessary components into panel
		listPane.add(map_name);

		listPane.add(map_height);

		listPane.add(map_width);

		listPane.add(empty);

		listPane.add(map_name_textfield);

		listPane.add(map_height_textfield);

		listPane.add(map_width_textfield);

		listPane.add(back_button);

		main_panel.add(sub_top_panel);

		sub_bottom_panel = new JPanel();

		this.setPreferredSize(new Dimension(1000, 700));

		// Display the window.
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		edit(map);
	}

	/**
	 * Used to show a grid map along with the right panel list
	 * 
	 * @param width_height
	 * @param map_walls
	 * @param character
	 * @param chest
	 * @param entryDoor
	 * @param exitDoor
	 * @param exitFlag
	 */
	private void updateMap(Point width_height, ArrayList<Point> map_walls, ArrayList<Point> character, Point chest,
			Point entryDoor, Point exitDoor, int entryFlag, int exitFlag) {
		// sub bottom panel consisting of map and info regarding map

		main_panel.remove(sub_bottom_panel);
		sub_bottom_panel = new JPanel();
		sub_bottom_panel.setLayout((new GridLayout(1, 2, 5, 5)));
		sub_bottom_panel.setBorder(BorderFactory.createRaisedBevelBorder());
		sub_bottom_panel.setMaximumSize(new Dimension(1000, 700));

		// info panel embedded inside sub bottom panel's right bottom corner
		JPanel leftGridPanel = new JPanel();

		leftGridPanel.setLayout(new BoxLayout(leftGridPanel, BoxLayout.Y_AXIS));
		leftGridPanel.setMaximumSize(new Dimension(750, 500));
		leftGridPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		// map panel embedded inside sub bottom panel's left bottom corner
		JPanel LeftGridMapPane = new JPanel();

		LeftGridMapPane.setLayout((new GridLayout(width_height.x, width_height.y, 1, 1)));
		LeftGridMapPane.setMaximumSize(new Dimension(750, 500));
		// LeftGridMapPane.setBorder(BorderFactory.createLineBorder(Color.BLACK,
		// 2));
		LeftGridMapPane.setBorder(BorderFactory.createEmptyBorder());
		/**
		 * yet to be constructed on the basis of top information
		 */

		// JButton[][] maps = new JButton[width_height.x][width_height.y];

		maps = new MapButton[width_height.x][width_height.y];

		for (int i = 0; i < width_height.x; i++) {
			for (int j = 0; j < width_height.y; j++) {
				maps[i][j] = new MapButton();
				maps[i][j].setPointValue(1);
				maps[i][j].setxPos(i);
				maps[i][j].setyPos(j);
				Point p = new Point();
				p.x = i;
				p.y = j;

				if (map_walls != null && map_walls.contains(p)) {
					maps[i][j].setBackground(Game_constants.WALLS);
					maps[i][j].setPointValue(0);
				} else if (character != null && character.contains(p)) {
					maps[i][j].setBackground(Game_constants.ENEMIES);
					maps[i][j].setPointValue(2);
				} else if (chest != null && chest.equals(p)) {
					maps[i][j].setBackground(Game_constants.CHEST);
				} else if (entryFlag == 1
						&& entryDoor.equals(p)) { /* t,l,b,r */
					borderSelection(entryDoor, i, j, width_height, "Entry_door");
				} else if (exitFlag == 1 && exitDoor.equals(p)) {
					borderSelection(exitDoor, i, j, width_height, "Exit_door");
				}
				LeftGridMapPane.add(maps[i][j]);
			}
		}

		leftGridPanel.add(LeftGridMapPane);

		// info panel embedded inside sub bottom panel's right bottom corner
		JPanel RightInfoPanel = new JPanel();

		RightInfoPanel.setLayout(new BoxLayout(RightInfoPanel, BoxLayout.Y_AXIS));
		RightInfoPanel.setMaximumSize(new Dimension(250, 300));
		RightInfoPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		// list panel consisting of information for map to display contents in
		// map embedded inside right info panel
		JPanel RightInfoListPane = new JPanel();

		RightInfoListPane.setLayout((new GridLayout(5, 2, 5, 5)));
		RightInfoListPane.setMaximumSize(new Dimension(250, 300));
		RightInfoPanel.add(RightInfoListPane);
		RightInfoListPane.setBorder(new BevelBorder(1));

		map_entry_door = new JLabel("Entry Door");
		map_exit_door = new JLabel("Exit Door");
		map_chest = new JLabel("Chest");
		map_enemy = new JLabel("Enemy");
		map_wall = new JLabel("Wall");
		// map_remove = new JLabel("Remove");

		// ButtonGroup bg = new ButtonGroup();
		// bg.add(map_entry_door);
		// bg.add(map_exit_door);
		// bg.add(map_chest);
		// bg.add(map_enemy);
		// bg.add(map_wall);
		// bg.add(map_remove);

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
		// RightInfoListPane.add(map_remove);
		// RightInfoListPane.add(new JLabel());
		// RightInfoListPane.add(back_button);
		// RightInfoListPane.add(save_button);

		JSplitPane spane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftGridPanel, RightInfoPanel);
		spane.setOneTouchExpandable(true);
		spane.setDividerLocation(650);
		spane.setOneTouchExpandable(false);
		sub_bottom_panel.add(spane);
		main_panel.add(sub_bottom_panel);

		this.pack();

	}

	/**
	 * Invoked upon by Game map Model(observer) object once the data in width
	 * and height of first game view is set
	 * 
	 * @param map
	 */

	public void edit(GameMapModel map) {

		Point width_height = map.getMap_size();
		ArrayList<Point> map_walls = map.getMap_walls();
		ArrayList<Point> map_character = map.getMap_enemy_loc();
		Point Chest = map.getMap_chest();
		Point EntryDoor = map.getMap_entry_door();
		Point ExitDoor = map.getMap_exit_door();
		this.model = map;
		int entryFlag = 1;
		int exitFlag = 1;// map.exitFlag;
		check = 1;
		updateMap(width_height, map_walls, map_character, Chest, EntryDoor, ExitDoor, entryFlag, exitFlag);

	}

	/**
	 * Used to set the border of doors based on the conditions
	 * 
	 * @param Door
	 * @param i
	 * @param j
	 * @param width_height
	 * @param door_type
	 */
	public void borderSelection(Point Door, int i, int j, Point width_height, String door_type) {

		if (Door.x == 0 && Door.y == 0) {

			maps[i][j].setBorder(BorderFactory.createMatteBorder(6, 6, 0, 0,
					((door_type == "Entry_door") ? Game_constants.ENTRY_DOOR : Game_constants.EXIT_DOOR)));
			// maps[i][j].setBorder(BorderFactory.createLineBorder);
		} else if (Door.x == width_height.x - 1 && Door.y == 0) {
			maps[i][j].setBorder(BorderFactory.createMatteBorder(0, 6, 6, 0,
					((door_type == "Entry_door") ? Game_constants.ENTRY_DOOR : Game_constants.EXIT_DOOR)));
		} else if (Door.y == width_height.y - 1 && Door.x == 0) {
			maps[i][j].setBorder(BorderFactory.createMatteBorder(6, 0, 0, 6,
					((door_type == "Entry_door") ? Game_constants.ENTRY_DOOR : Game_constants.EXIT_DOOR)));
		} else if (Door.x == width_height.x - 1 && Door.y == width_height.y - 1) {
			maps[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 6, 6,
					((door_type == "Entry_door") ? Game_constants.ENTRY_DOOR : Game_constants.EXIT_DOOR)));
		} else if (Door.x == 0) {
			maps[i][j].setBorder(BorderFactory.createMatteBorder(6, 0, 0, 0,
					((door_type == "Entry_door") ? Game_constants.ENTRY_DOOR : Game_constants.EXIT_DOOR)));
		} else if (Door.y == 0) {
			maps[i][j].setBorder(BorderFactory.createMatteBorder(0, 6, 0, 0,
					((door_type == "Entry_door") ? Game_constants.ENTRY_DOOR : Game_constants.EXIT_DOOR)));
		} else if (Door.x == width_height.x - 1) {
			maps[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 6, 0,
					((door_type == "Entry_door") ? Game_constants.ENTRY_DOOR : Game_constants.EXIT_DOOR)));

		} else if (Door.y == width_height.y - 1) {
			maps[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 6,
					((door_type == "Entry_door") ? Game_constants.ENTRY_DOOR : Game_constants.EXIT_DOOR)));
		}
	}

	/**
	 * This method handles event for back button
	 *
	 * @param e
	 *            action event for buttons
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.back_button)) {
			new ManageMapController();
			this.dispose();
		}
	}

}
