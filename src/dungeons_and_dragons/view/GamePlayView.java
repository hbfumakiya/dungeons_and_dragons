package dungeons_and_dragons.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dungeons_and_dragons.controller.GamePlayController;
import dungeons_and_dragons.helper.Game_constants;
import dungeons_and_dragons.helper.MapButton;
import dungeons_and_dragons.model.GameMapModel;
import dungeons_and_dragons.model.GamePlayModel;

/**
 * Renders the GamePlayModel into a form suitable for visualization or
 * interaction, typically a user interface element.
 * 
 * @author Mihir Pujara
 *
 */
public class GamePlayView extends JFrame implements Observer, View {

	/**
	 * this variable used to set window title
	 * 
	 * @type String
	 */
	public String mapWindowTitle = "Play Game";

	private GamePlayModel gamePlayModel;

	public JPanel mapPanel;

	public JPanel infoPanel;

	public GamePlayView(GamePlayModel gamePlayModel) {

		this.setTitle(this.mapWindowTitle);

		this.gamePlayModel = gamePlayModel;

		// initialize game window
		this.initializeWindow();

		// close frame while user click on close
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initializeWindow() {

		this.setLayout(null);

		this.mapPanel = new JPanel();
		this.mapPanel.setBounds(5, 50, 695, 445);
		GameMapModel currentMap = this.gamePlayModel.getCampaignModel().getOutput_map_list()
				.get(this.gamePlayModel.getCurrentMapIndex());

		this.showMap(currentMap, this.mapPanel);

		this.infoPanel = new JPanel();
		this.infoPanel.setBounds(710, 50, 285, 445);

		Container contentPane = getContentPane();
		contentPane.add(this.mapPanel);
		contentPane.add(this.infoPanel);

		// set minimum size of frame
		this.setMinimumSize(new Dimension(1000, 700));

		this.setResizable(false);

		// Display the window.
		this.pack();
		this.setLocationRelativeTo(null);
	}

	private void showMap(GameMapModel currentMap, JPanel panel) {

		int x = (int) currentMap.getMap_size().getX();
		int y = (int) currentMap.getMap_size().getY();

		panel.setLayout(new GridLayout(x, y));

		MapButton[][] maps = new MapButton[x][y];

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				maps[i][j] = new MapButton();
				maps[i][j].setPointValue(1);
				maps[i][j].setxPos(i);
				maps[i][j].setyPos(j);
				Point p = new Point();
				p.x = i;
				p.y = j;
				panel.add(maps[i][j]);

				if (currentMap.getMap_walls().contains(p)) {
					maps[i][j].setBackground(Game_constants.WALLS);
				}
				panel.add(maps[i][j]);
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setActionListener(ActionListener actionListener) {
	}

	public void setListener(GamePlayController gamePlayController) {
		this.addKeyListener(gamePlayController);
	}

}
