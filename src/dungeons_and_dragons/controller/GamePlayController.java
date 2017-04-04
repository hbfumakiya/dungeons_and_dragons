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
import dungeons_and_dragons.model.AbilityScoresModel;
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

		this.gamePlayModel.calculateTurn();

		this.gamePlayModel.startGame();
	}

	/**
	 * This function match character's level to NPC and calculate
	 * modifiers,armorclass,attackbonus,hitpoints and damage bonus.
	 * 
	 */
	private void matchNPCToPlayer() {

		ArrayList<MapCharacter> npc = this.gamePlayModel.getCampaignModel().getOutput_map_list()
				.get(this.gamePlayModel.getCurrentMapIndex()).getMap_enemy_loc();

		CharacterModel character;

		for (int i = 0; i < npc.size(); i++) {
			character = npc.get(i).getCharacter();
			character.setCharacter_level(this.gamePlayModel.getCharacterModel().getCharacter_level());
			ArrayList<ItemModel> items = character.getItems();
			for (int j = 0; j < items.size(); j++) {
				items.get(j).setItem_point(getItemScoreByLevel(character.getCharacter_level()));
			}

			ArrayList<ItemModel> backPackItems = character.getBackPackItems();
			for (int j = 0; j < backPackItems.size(); j++) {
				backPackItems.get(j).setItem_point(getItemScoreByLevel(character.getCharacter_level()));
			}
			character.calculateModifires();
			character.calculateArmorClass();
			character.calculateAttackBonus(character.getCharacter_level());
			character.calculateHitPoints(character.getCharacter_level());
			character.calculateDamageBonus();
		}
	}

	/**
	 * pass character level to get item points for NPC
	 * 
	 * @param level
	 *            character level
	 * @return item points based on character level
	 */
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

	/**
	 * key pressed events are handled here
	 */
	@Override
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();
		Point tempPoint = (Point) this.gamePlayModel.getGameCharacterPosition().clone();
		Point oldPoint = (Point) tempPoint.clone();

		if (key == KeyEvent.VK_LEFT) {
			tempPoint.y = tempPoint.y - 1;
			this.gamePlayModel.moveCharacter(tempPoint, oldPoint);

		} else if (key == KeyEvent.VK_RIGHT) {
			tempPoint.y = tempPoint.y + 1;
			this.gamePlayModel.moveCharacter(tempPoint, oldPoint);

		} else if (key == KeyEvent.VK_UP) {
			tempPoint.x = tempPoint.x - 1;
			this.gamePlayModel.moveCharacter(tempPoint, oldPoint);
			/*try {
				synchronized (this.gamePlayModel.gameThread) {
					this.gamePlayModel.gameThread.notify();
				}
				
			} catch (Exception e1) {
				
			}*/

		} else if (key == KeyEvent.VK_DOWN) {
			tempPoint.x = tempPoint.x + 1;
			this.gamePlayModel.moveCharacter(tempPoint, oldPoint);

		}
	}

	

	/**
	 * This method is created to have fight between enemy
	 * 
	 * @param enemy
	 *            Enemy to fight with
	 * @param player
	 *            main character player
	 * @return boolean return true if enemy survives else false if enemy is dead
	 */
	private boolean fightWithEnemy(CharacterModel enemy, CharacterModel player) {
		AbilityScoresModel zeroAbilities = new AbilityScoresModel();
		zeroAbilities.setCharisma(-10);
		zeroAbilities.setConstitution(-10);
		zeroAbilities.setDexterity(-10);
		zeroAbilities.setIntelligence(-10);
		zeroAbilities.setstrength(-10);
		zeroAbilities.setWisdom(-10);

		enemy.setAbilityScores(zeroAbilities);
		enemy.setAttackBonus(0);
		enemy.setHitpoints(0);
		enemy.setDamageBonus(0);
		enemy.setArmorClass(0);
		enemy.setRawAbilityScores(zeroAbilities);
		enemy.calculateModifires();
		return false;

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

	

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * This method handles event for back button,map buttons with enemy,friend
	 * or character.
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.gamePlayView.backButton)) {
			this.gamePlayModel.setGameRunning(false);
			new NewGameController();
			this.gamePlayView.dispose();

		} else {
			MapButton button = (MapButton) e.getSource();
			if (button != null && button.getButton_type().equals(Game_constants.GRID_BUTTON_TYPE)) {
				if (button.getCharacterType() == MapButton.ENEMY) {

					if (!this.shownInventories.contains(button.getCharacter())) {
						this.shownInventories.add(button.getCharacter());
						new CharacterInventoryController(button.getCharacter(), this, button.getCharacterType());
					}

				} else if (button.getCharacterType() == MapButton.FRIENDLY_PLAYER) {

					if (!this.shownInventories.contains(button.getCharacter())) {
						this.shownInventories.add(button.getCharacter());
						new CharacterInventoryController(button.getCharacter(), this, button.getCharacterType());
					}

				} else if (button.getCharacterType() == MapButton.PLAYER) {

					if (!this.shownInventories.contains(button.getCharacter())) {
						this.shownInventories.add(button.getCharacter());
						new CharacterInventoryController(button.getCharacter(), this, button.getCharacterType());
					}

				}
			}
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	/**
	 * window closed events are handles here
	 */

	@Override
	public void windowClosed(WindowEvent e) {

		CharacterModel character = ((CharacterInventoryView) e.getSource()).getCharacter();
		if (this.shownInventories.contains(character)) {
			this.shownInventories.remove(character);
		}
	}

	/**
	 * window closing events are handles here
	 */

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
