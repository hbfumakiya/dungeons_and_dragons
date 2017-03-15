package dungeons_and_dragons.controller;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dungeons_and_dragons.helper.Game_constants;
import dungeons_and_dragons.helper.MapButton;
import dungeons_and_dragons.helper.MapCharacter;
import dungeons_and_dragons.helper.MapItem;
import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.model.GameMapModel;
import dungeons_and_dragons.model.GamePlayModel;
import dungeons_and_dragons.model.ItemModel;
import dungeons_and_dragons.view.CharacterInventoryView;
import dungeons_and_dragons.view.GamePlayView;

/**
 * The GamePlayController translates the user's interactions with the
 * GamePlayView into actions that the GamePlayModel will perform that may use
 * some additional/changed data gathered in a user-interactive view.
 * 
 * @author Tejas Sadrani & Urmil Kansara & Mihir Pujara
 */
public class GamePlayController implements KeyListener, ActionListener, WindowListener {

	/**
	 * This creates new game play model which is being observed
	 * 
	 * @type GameMapModel
	 */
	public GamePlayModel gamePlayModel;

	/**
	 * This create game play view object which is an observer
	 * 
	 * @type MapGridView
	 */
	public GamePlayView gamePlayView;

	private ArrayList<CharacterModel> shownInventories;

	/**
	 * Default constructor of Map Grid controller
	 * <p>
	 * MapGrid model and view are initialized Also view is binded to observer.
	 * <p>
	 * All the events of view are registered in constructor
	 */
	public GamePlayController(GamePlayModel gamePlayModel) {

		this.gamePlayModel = gamePlayModel;

		// GameMapModel currentMap =
		// this.gamePlayModel.getCampaignModel().getOutput_map_list().get(this.gamePlayModel.getCurrentMapIndex());

		this.gamePlayView = new GamePlayView(this.gamePlayModel, this);

		this.gamePlayModel.addObserver(gamePlayView);
		this.gamePlayView.setListener(this);
		this.gamePlayView.setVisible(true);

		this.shownInventories = new ArrayList<CharacterModel>();

		matchNPCToPlayer();

	}

	private void matchNPCToPlayer() {

		ArrayList<MapCharacter> npc = this.gamePlayModel.getCampaignModel().getOutput_map_list()
				.get(this.gamePlayModel.getCurrentMapIndex()).getMap_enemy_loc();

		CharacterModel character;

		for (int i = 0; i < npc.size(); i++) {
			character = npc.get(i).getCharacter();
			character.setCharacter_level(this.gamePlayModel.getCharacterModel().getCharacter_level());
			ArrayList<ItemModel> items = character.getItems();
			for (int j = 0; j < items.size(); j++) {
				items.get(i).setItem_point(getItemScoreByLevel(character.getCharacter_level()));
			}

			ArrayList<ItemModel> backPackItems = character.getBackPackItems();
			for (int j = 0; j < backPackItems.size(); j++) {
				backPackItems.get(i).setItem_point(getItemScoreByLevel(character.getCharacter_level()));
			}
			character.calculateModifires();
			character.calculateArmorClass();
			character.calculateAttackBonus(character.getCharacter_level());
			character.calculateHitPoints(character.getCharacter_level());
			character.calculateDamageBonus();
		}
	}

	private int getItemScoreByLevel(int level) {
		if (level >= 1 && level <= 4) {
			return 1;
		} else if (level >= 5 && level <= 8) {
			return 2;
		} else if (level >= 9 && level <= 12) {
			return 3;
		} else if (level >= 13 && level <= 16) {
			return 4;
		} else if (level >= 17) {
			return 5;
		}
		return 1;
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();
		Point tempPoint = (Point) this.gamePlayModel.getGameCharacterPosition().clone();
		Point oldPoint = (Point) tempPoint.clone();

		if (key == KeyEvent.VK_LEFT) {
			tempPoint.y = tempPoint.y - 1;
			moveCharacter(tempPoint, oldPoint);

		} else if (key == KeyEvent.VK_RIGHT) {
			tempPoint.y = tempPoint.y + 1;
			moveCharacter(tempPoint, oldPoint);

		} else if (key == KeyEvent.VK_UP) {
			tempPoint.x = tempPoint.x - 1;
			moveCharacter(tempPoint, oldPoint);

		} else if (key == KeyEvent.VK_DOWN) {
			tempPoint.x = tempPoint.x + 1;
			moveCharacter(tempPoint, oldPoint);

		}
	}

	/**
	 * This functions allows the Player to move from its position based on the
	 * key pressed
	 * 
	 * @param tempPoint
	 * @param oldPoint
	 */
	public void moveCharacter(Point tempPoint, Point oldPoint) {

		// first of all check if the boundaries have been reached
		if (checkBoundaries(tempPoint)) {

			// boundary not reached

			// check if the point is wall
			if (this.gamePlayView.showWalls(tempPoint)) {

				tempPoint = oldPoint;
				this.gamePlayView.consoleTextArea.setForeground(Color.RED);
				this.gamePlayView.consoleTextArea
						.setText(this.gamePlayView.consoleTextArea.getText() + "Oops...bumped into wall...\n");
				// print message that "Oops wall has been reached"

			} else if (this.gamePlayView.showExitDoor(tempPoint)) {

				// check if mission is completed -- > if yes let him escape
				updatePostion(tempPoint, oldPoint);

			} else if (this.gamePlayView.showChest(tempPoint)) {

				GameMapModel map = this.gamePlayModel.getCampaignModel().getOutput_map_list()
						.get(this.gamePlayModel.getCurrentMapIndex());
				
				String msg = "";
				if (map.getMap_chest() != null && map.getMap_chest().getX() != -1 && map.getMap_chest().getY() != -1) {
					ArrayList<ItemModel> backPackItems = this.gamePlayModel.getCharacterModel().getBackPackItems();
					if (backPackItems.size() < 2) {
						backPackItems.add(map.getMap_chest().getItem());
						this.gamePlayModel.getCharacterModel().setBackPackItems(backPackItems);
						
						ItemModel i =this.gamePlayModel.getCampaignModel().getOutput_map_list().get(this.gamePlayModel.getCurrentMapIndex()).getMap_chest().getItem();
						
						msg = "Item "+i.getItem_name()+" has been added in your backpack";
						
						this.gamePlayModel.removeChest(tempPoint);	
					}
					else
					{
						msg = "Sorry your backpack is full.So cannot add any new Item";
					}
				}
				
				updatePostion(tempPoint, oldPoint);
				JOptionPane.showMessageDialog(new JFrame(), msg);
				this.gamePlayView.consoleTextArea.setForeground(Color.GREEN);
				this.gamePlayView.consoleTextArea.setText(
						this.gamePlayView.consoleTextArea.getText() + "Found an item...."+msg);

				// exchange item message on console and automatically traverse
				// items in chest to the players backpack

			} else if (this.gamePlayView.showCharacter(tempPoint) != null) {
				if (this.gamePlayView.enemyFlag == 1) {

					this.gamePlayView.consoleTextArea.setForeground(Color.GREEN);
					String emoji = String.valueOf(Character.toChars(0x263A));
					this.gamePlayView.consoleTextArea.setText(this.gamePlayView.consoleTextArea.getText()
							+ "You are sooo dead in my eyes..." + emoji + "\n");
					System.out.println("\u1F47F");
					updatePostion(tempPoint, oldPoint);

					// Enemy is dead message when it reaches here(hell lot of
					// programming in build3 for this :P)

				} else if (this.gamePlayView.enemyFlag == 0) {

					this.gamePlayView.consoleTextArea.setForeground(Color.GREEN);
					this.gamePlayView.consoleTextArea.setText(this.gamePlayView.consoleTextArea.getText()
							+ "Hey bud wssup...I m here to take your items nothing else :P \n");
					updatePostion(tempPoint, oldPoint);

					// friendly items traversal to backpack of character
				}
			} else {
				this.gamePlayView.consoleTextArea.setForeground(Color.GREEN);
				this.gamePlayView.consoleTextArea.setText(this.gamePlayView.consoleTextArea.getText()
						+ "Ohhh yeaaa...!! Time to move ahead in life....\n");
				updatePostion(tempPoint, oldPoint);
			}

		} else {
			// revert the point as boundary reached
			tempPoint = oldPoint;
		}

	}

	/**
	 * This function performs move ahead steps -- clean behind button text and
	 * set new text to new point via notifying observer
	 * 
	 * @param tempPoint
	 * @param oldPoint
	 */
	public void updatePostion(Point tempPoint, Point oldPoint) {
		this.gamePlayView.oldPosition = oldPoint;
		this.gamePlayModel.setGameCharacterPosition(tempPoint);
	}

	/**
	 * This function checks if the boundary conditions are reached
	 * 
	 * @param tempPoint
	 * @return
	 */
	private boolean checkBoundaries(Point tempPoint) {
		if (tempPoint.x < 0 || tempPoint.y < 0 || tempPoint.x >= this.gamePlayView.currentMap.getMap_size().x
				|| tempPoint.y >= this.gamePlayView.currentMap.getMap_size().y) {

			this.gamePlayView.consoleTextArea.setForeground(Color.RED);
			this.gamePlayView.consoleTextArea
					.setText(this.gamePlayView.consoleTextArea.getText() + "Oops...Cannot go ahead...\n");
			return false;
		} else {
			// boundary not reached
			return true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MapButton button = (MapButton) e.getSource();
		if (button != null && button.getButton_type().equals(Game_constants.GRID_BUTTON_TYPE)) {
			if (button.getCharacterType() == MapButton.ENEMY) {

				if (!this.shownInventories.contains(button.getCharacter())) {
					this.shownInventories.add(button.getCharacter());
					new CharacterInventoryController(button.getCharacter(), this);
				}

			} else if (button.getCharacterType() == MapButton.FRIENDLY_PLAYER) {

				if (!this.shownInventories.contains(button.getCharacter())) {
					this.shownInventories.add(button.getCharacter());
					new CharacterInventoryController(button.getCharacter(), this);
				}

			} else if (button.getCharacterType() == MapButton.PLAYER) {

				if (!this.shownInventories.contains(button.getCharacter())) {
					this.shownInventories.add(button.getCharacter());
					new CharacterInventoryController(button.getCharacter(), this);
				}

			}
		}

	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {

		CharacterModel character = ((CharacterInventoryView) e.getSource()).getCharacter();
		if (this.shownInventories.contains(character)) {
			this.shownInventories.remove(character);
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {

		CharacterModel character = ((CharacterInventoryView) e.getSource()).getCharacter();
		if (this.shownInventories.contains(character)) {
			this.shownInventories.remove(character);
		}
	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowOpened(WindowEvent e) {

	}

}