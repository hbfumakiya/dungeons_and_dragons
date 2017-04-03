package dungeons_and_dragons.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import dungeons_and_dragons.controller.GamePlayController;
import dungeons_and_dragons.helper.Game_constants;
import dungeons_and_dragons.helper.MapButton;
import dungeons_and_dragons.helper.MapCharacter;
import dungeons_and_dragons.model.GameMapModel;
import dungeons_and_dragons.model.GamePlayModel;

/**
 * Renders the GamePlayModel into a form suitable for visualization or
 * interaction, typically a user interface element.
 * 
 * @author Mihir Pujara & Urmil Kansara
 *
 */
public class GamePlayView extends JFrame implements Observer, View {

	/**
	 * this variable used to set window title
	 * 
	 * @type String
	 */
	public String mapWindowTitle = "Play Game";

	public GamePlayModel gamePlayModel;
	public GameMapModel currentMap;
	private GamePlayController gamePlayController;

	public JPanel mapPanel;
	public JPanel topPanel;
	public JPanel infoPanel;
	public JPanel consoleMainPanel;
	public JScrollPane consolePanel;

	private Container contentPane;
	private MapButton[][] maps;
	public Point oldPosition;
	public int enemyFlag = 0;
	public JLabel campaignNameLabel;
	public JLabel campaignName;
	public JLabel mapNameLabel;
	public JLabel mapName;
	public JTextArea consoleTextArea;

	public JLabel map_entry_door;
	public JLabel map_exit_door;
	public JLabel map_chest;
	public JLabel map_enemy;
	public JLabel map_friend;
	public JLabel map_wall;
	private JLabel map_entry_color;
	private JLabel map_exit_color;
	private JLabel map_chest_color;
	private JLabel map_enemy_color;
	private JLabel map_friend_color;
	private JLabel map_wall_color;
	private JLabel empty;

	public JButton backButton;

	/**
	 * Constructor to initialize variables and objects of gameplay model and
	 * controller
	 * 
	 * @param gamePlayModel
	 * @param gamePlayController
	 */
	public GamePlayView(GamePlayModel gamePlayModel, GamePlayController gamePlayController) {

		this.setTitle(this.mapWindowTitle);
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
			BufferedImage map_friend_color_image = ImageIO.read(new File("res/orange.jpg"));
			map_friend_color = new JLabel(new ImageIcon(map_friend_color_image));
			// map_wall_color.setMaximumSize(new Dimension(10,10));

		} catch (IOException e) {
		}

		this.gamePlayModel = gamePlayModel;
		this.currentMap = this.gamePlayModel.getCampaignModel().getOutput_map_list()
				.get(this.gamePlayModel.getCurrentMapIndex());
		// only set while playing this game for the first time
		oldPosition = this.currentMap.getMap_entry_door();
		this.gamePlayModel.setGameCharacterPosition(oldPosition);

		this.gamePlayController = gamePlayController;

		// initialize game window
		this.initializeWindow();

		// close frame while user click on close
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * initialize the window for game play
	 */
	private void initializeWindow() {

		this.setLayout(null);

		this.topPanel = new JPanel();
		this.topPanel.setBounds(500, 5, 300, 40);
		this.topPanel.setLayout(new GridLayout(2, 3));

		campaignNameLabel = new JLabel("Campaign -->");
		campaignName = new JLabel(this.gamePlayModel.getCampaignModel().getCampaign_name());
		backButton = new JButton("back");
		mapNameLabel = new JLabel("Map -->");
		mapName = new JLabel(this.currentMap.getMap_name());

		this.topPanel.add(campaignNameLabel);
		this.topPanel.add(campaignName);
		this.topPanel.add(backButton);
		this.topPanel.add(mapNameLabel);
		this.topPanel.add(mapName);

		this.mapPanel = new JPanel();
		this.mapPanel.setBounds(5, 50, 695, 445);
		this.mapPanel.setBorder(BorderFactory.createRaisedBevelBorder());

		this.showMap(this.currentMap, this.mapPanel);
		displayPlayer(maps[(int) this.currentMap.getMap_entry_door().getX()][(int) this.currentMap.getMap_entry_door()
				.getY()]);

		// yet to be constructed
		this.infoPanel = new JPanel();
		this.infoPanel.setBounds(710, 50, 280, 445);
		this.infoPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.infoPanel.setLayout(new GridLayout(6, 2, 15, 15));
		this.infoPanel.setBorder(new BevelBorder(1));

		map_entry_door = new JLabel("Entry Door");
		map_exit_door = new JLabel("Exit Door");
		map_chest = new JLabel("Chest");
		map_enemy = new JLabel("Enemy");
		map_friend = new JLabel("Friend");
		map_wall = new JLabel("Wall");

		this.infoPanel.add(map_entry_door);
		this.infoPanel.add(map_entry_color);
		this.infoPanel.add(map_exit_door);
		this.infoPanel.add(map_exit_color);
		this.infoPanel.add(map_chest);
		this.infoPanel.add(map_chest_color);
		this.infoPanel.add(map_enemy);
		this.infoPanel.add(map_enemy_color);
		this.infoPanel.add(map_friend);
		this.infoPanel.add(map_friend_color);
		this.infoPanel.add(map_wall);
		this.infoPanel.add(map_wall_color);

		this.consoleMainPanel = new JPanel();
		this.consoleMainPanel.setBounds(5, 510, 985, 150);
		this.consoleMainPanel.setBorder(new BevelBorder(1));

		this.consoleTextArea = new JTextArea("Hello to this mighty world!!\n", 10, 500);
		this.consoleTextArea.setEditable(false);
		this.consoleTextArea.setFocusable(false);
		this.consoleTextArea.setVisible(true);
		this.consoleTextArea.setForeground(Color.WHITE);
		this.consoleTextArea.setBackground(Color.BLACK);

		this.consolePanel = new JScrollPane(this.consoleTextArea);
		this.consolePanel.setPreferredSize(new Dimension(950, 130));

		this.consoleMainPanel.add(this.consolePanel);

		contentPane = getContentPane();
		contentPane.add(this.topPanel);
		contentPane.add(this.mapPanel);
		contentPane.add(this.infoPanel);
		contentPane.add(this.consoleMainPanel);

		// set minimum size of frame
		this.setMinimumSize(new Dimension(1000, 700));
		this.setResizable(false);

		// Display the window.
		this.pack();
		this.setLocationRelativeTo(null);

		this.backButton.setFocusable(false);
	}

	/**
	 * show the map for current selected map
	 * 
	 * @param currentMap
	 *            map of campaign
	 * @param mapPanel
	 *            panel of map
	 */
	public void showMap(GameMapModel currentMap, JPanel mapPanel) {
		mapPanel.removeAll();
		int x = (int) currentMap.getMap_size().getX();
		int y = (int) currentMap.getMap_size().getY();
		Point tempPoint;
		mapPanel.setLayout(new GridLayout(x, y));

		maps = new MapButton[x][y];

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				maps[i][j] = new MapButton();
				maps[i][j].setPointValue(1);
				maps[i][j].setxPos(i);
				maps[i][j].setyPos(j);
				tempPoint = new Point(i, j);
				MapCharacter character = showCharacter(tempPoint);
				// setting all objects on the map
				if (showWalls(tempPoint)) {
					maps[i][j].setBackground(Game_constants.WALLS);
					maps[i][j].setPointValue(0);
				} else if (showEntryDoor(tempPoint)) {
					borderSelection(this.currentMap.getMap_entry_door(), i, j, currentMap.getMap_size(), "Entry_door");

				} else if (showExitDoor(tempPoint)) {
					borderSelection(this.currentMap.getMap_exit_door(), i, j, currentMap.getMap_size(), "Exit_door");
				} else if (showChest(tempPoint)) {
					maps[i][j].setBackground(Game_constants.CHEST);
				} else if (character != null) {
					if (enemyFlag == 1) {
						maps[i][j].setBackground(Game_constants.ENEMIES);
						if (!character.getCharacter().isAlive())
							maps[i][j].setText("D");
						maps[i][j].setPointValue(2);
						maps[i][j].setCharacterType(MapButton.ENEMY);
						maps[i][j].setCharacter(character.getCharacter());
						maps[i][j].addActionListener(this.gamePlayController);
					} else if (enemyFlag == 0) {
						maps[i][j].setBackground(Game_constants.FRIENDS);
						maps[i][j].setCharacterType(MapButton.FRIENDLY_PLAYER);
						maps[i][j].setCharacter(character.getCharacter());
						maps[i][j].addActionListener(this.gamePlayController);
						// maps[i][j].setPointValue(99);
					}

				}

				maps[i][j].setFocusable(false);
				mapPanel.add(maps[i][j]);
			}
		}
	}

	/**
	 * This method is used to display player on the basis of the changing map
	 * conditions
	 * 
	 * @param mapButton
	 */
	public void displayPlayer(MapButton mapButton) {
		mapButton.setText("P");
		mapButton.setCharacterType(MapButton.PLAYER);
		mapButton.setCharacter(this.gamePlayModel.getCharacterModel());
		mapButton.addActionListener(this.gamePlayController);
		// mapButton.setFont(new Font("Comic", Font.BOLD, 40));;
	}

	/**
	 * This method is used to display player on the basis of the changing map
	 * conditions
	 * 
	 * @param p
	 *            point where it should display
	 */
	public void displayPlayer(Point p) {
		MapButton mapButton = maps[(int) p.getX()][(int) p.getY()];
		mapButton.setText("P");
		mapButton.setCharacterType(MapButton.PLAYER);
		mapButton.setCharacter(this.gamePlayModel.getCharacterModel());
		mapButton.addActionListener(this.gamePlayController);
	}

	/**
	 * This method is used to display player on the basis of the changing map
	 * conditions
	 * 
	 * @param mapButton
	 */
	public void eraseButtonBackground(MapButton mapButton) {
		// mapButton.setBackground(null);
		if (!mapButton.getText().equals("D"))
			mapButton.setText(null);
	}

	/**
	 * This function returns true if there is a chest in the corresponding point
	 * P that is passed as a parameter
	 * 
	 * @param p
	 * @return
	 */
	public boolean showChest(Point p) {
		if (this.currentMap.getMap_chest() != null && this.currentMap.getMap_chest().getX() == p.x
				&& this.currentMap.getMap_chest().getY() == p.y) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * This function returns true if there is a exit door in the corresponding
	 * point P that is passed as a parameter
	 * 
	 * @param p
	 * @return
	 */
	public boolean showExitDoor(Point p) {
		if (this.currentMap.getMap_exit_door().equals(p)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * This function returns true if there is a entry door in the corresponding
	 * point P that is passed as a parameter
	 * 
	 * @param p
	 * @return
	 */
	public boolean showEntryDoor(Point p) {
		if (this.currentMap.getMap_entry_door().equals(p)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This function returns true if there is an enemy or a friendly character
	 * in the corresponding point P that is passed as a parameter
	 * 
	 * @param p
	 * @return
	 */
	public MapCharacter showCharacter(Point p) {
		if (!this.currentMap.getMap_enemy_loc().isEmpty()) {
			for (int x = 0; x < this.currentMap.getMap_enemy_loc().size(); x++) {
				MapCharacter c = this.currentMap.getMap_enemy_loc().get(x);
				if (c.getX() == p.x && c.getY() == p.y) {
					if (c.getCharacterType().equals(MapCharacter.ENEMY)) {
						enemyFlag = 1;

					} else if (c.getCharacterType().equals(MapCharacter.FRIENDLY)) {
						enemyFlag = 0;
					}
					return c;
				}
			}
		}
		return null;
	}

	/**
	 * This function returns true if there is a wall in the corresponding point
	 * P that is passed as a parameter
	 * 
	 * @param p
	 * @return
	 */
	public boolean showWalls(Point p) {

		if (this.currentMap.getMap_walls() != null && this.currentMap.getMap_walls().contains(p)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * default update method implementing observer pattern
	 */
	@Override
	public void update(Observable obs, Object obj) {

		if (((GamePlayModel) obs).ConsoleLogChanged != true) {
			this.mapPanel.removeAll();
			this.showMap(((GamePlayModel) obs).getCampaignModel().getOutput_map_list()
					.get(((GamePlayModel) obs).getCurrentMapIndex()), this.mapPanel);
			displayPlayer(maps[((GamePlayModel) obs).getGameCharacterPosition().x][((GamePlayModel) obs)
					.getGameCharacterPosition().y]);
			if (this.maps[oldPosition.x][oldPosition.y] != null
					&& this.maps[oldPosition.x][oldPosition.y].getCharacter() != null) {

				eraseButtonBackground(maps[oldPosition.x][oldPosition.y]);

				ArrayList<MapCharacter> enemies = this.gamePlayModel.getCampaignModel().getOutput_map_list()
						.get(this.gamePlayModel.getCurrentMapIndex()).getMap_enemy_loc();

				for (int i = 0; i < enemies.size(); i++) {

					int x = enemies.get(i).getX();
					int y = enemies.get(i).getY();

					if (x != oldPosition.x & y != oldPosition.y) {
						maps[oldPosition.x][oldPosition.y].removeActionListener(this.gamePlayController);
						maps[oldPosition.x][oldPosition.y].setCharacterType(-1);
					}

				}

			}
		}
		else{
			this.consoleTextArea.setForeground( ((GamePlayModel)obs).consoleLog.consoleTextAreaColor) ;
			this.consoleTextArea
					.setText(this.consoleTextArea.getText()+ ((GamePlayModel)obs).consoleLog.consoleTextAreaText);
		}
	}

	/**
	 * register events from view
	 * 
	 * @param gamePlayController
	 */
	public void setListener(GamePlayController gamePlayController) {
		this.addKeyListener(gamePlayController);
		this.backButton.addActionListener(gamePlayController);
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		// TODO Auto-generated method stub

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

}
