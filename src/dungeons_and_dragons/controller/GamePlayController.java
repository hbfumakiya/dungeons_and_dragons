package dungeons_and_dragons.controller;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import dungeons_and_dragons.helper.GameStatus;
import dungeons_and_dragons.helper.Game_constants;
import dungeons_and_dragons.helper.LogHelper;
import dungeons_and_dragons.helper.MapButton;
import dungeons_and_dragons.helper.MapCharacter;
import dungeons_and_dragons.helper.PostProcessingClass;
import dungeons_and_dragons.model.AbilityScoresModel;
import dungeons_and_dragons.model.CharacterModel;
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
public class GamePlayController implements KeyListener, ActionListener, WindowListener, Runnable {

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

	public Thread fileThread;

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

		try {
			PrintWriter pw = new PrintWriter(LogHelper.LOG_FILE);
			pw.close();
		} catch (FileNotFoundException e) {
		}

		this.fileThread = new Thread(this);
		this.fileThread.start();

		this.gamePlayModel.postProcessingThread = new Thread(new PostProcessingClass(this.gamePlayModel,this.gamePlayView,this));
		this.gamePlayModel.postProcessingThread.start();
		
		this.gamePlayModel.calculateTurn();

		this.gamePlayModel.startGame();

	}
	
	public GamePlayController(GamePlayModel gamePlayModel, boolean loadgame) {

		this.gamePlayModel = gamePlayModel;

		// GameMapModel currentMap =
		// this.gamePlayModel.getCampaignModel().getOutput_map_list().get(this.gamePlayModel.getCurrentMapIndex());

		this.gamePlayView = new GamePlayView(this.gamePlayModel, this);

		this.gamePlayModel.addObserver(gamePlayView);
		this.gamePlayView.setListener(this);
		this.gamePlayView.setVisible(true);

		this.shownInventories = new ArrayList<CharacterModel>();

		matchNPCToPlayer();

		try {
			PrintWriter pw = new PrintWriter(LogHelper.LOG_FILE);
			pw.close();
		} catch (FileNotFoundException e) {
		}

		this.fileThread = new Thread(this);
		this.fileThread.start();

		this.gamePlayModel.postProcessingThread = new Thread(new PostProcessingClass(this.gamePlayModel,this.gamePlayView,this));
		this.gamePlayModel.postProcessingThread.start();
		
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
		this.gamePlayModel.charachterTempPoint = (Point) this.gamePlayModel.getGameCharacterPosition().clone();
		this.gamePlayModel.charachterOldPoint = (Point) this.gamePlayModel.charachterTempPoint.clone();

		if (key == KeyEvent.VK_LEFT) {
			if (this.gamePlayModel.gameThread.getState().equals(Thread.State.WAITING)) {
				this.gamePlayModel.charachterTempPoint.y = this.gamePlayModel.charachterTempPoint.y - 1;

				try {
					synchronized (this.gamePlayModel.gameThread) {
						this.gamePlayModel.gameThread.notify();
					}

				} catch (Exception e1) {
					LogHelper.Log(LogHelper.TYPE_ERROR, e1.getMessage());
				}

				this.postProcessing(this.gamePlayModel.gameStatus);
			}

		} else if (key == KeyEvent.VK_RIGHT) {
			if (this.gamePlayModel.gameThread.getState().equals(Thread.State.WAITING)) {
				this.gamePlayModel.charachterTempPoint.y = this.gamePlayModel.charachterTempPoint.y + 1;

				try {
					synchronized (this.gamePlayModel.gameThread) {
						this.gamePlayModel.gameThread.notify();
					}

				} catch (Exception e1) {
					LogHelper.Log(LogHelper.TYPE_ERROR, e1.getMessage());
				}

				this.postProcessing(this.gamePlayModel.gameStatus);
			}

		} else if (key == KeyEvent.VK_UP) {
			if (this.gamePlayModel.gameThread.getState().equals(Thread.State.WAITING)) {
				this.gamePlayModel.charachterTempPoint.x = this.gamePlayModel.charachterTempPoint.x - 1;
				try {
					synchronized (this.gamePlayModel.gameThread) {
						this.gamePlayModel.gameThread.notify();
					}

				} catch (Exception e1) {
					LogHelper.Log(LogHelper.TYPE_ERROR, e1.getMessage());
				}

				this.postProcessing(this.gamePlayModel.gameStatus);
			}

		} else if (key == KeyEvent.VK_DOWN) {
			if (this.gamePlayModel.gameThread.getState().equals(Thread.State.WAITING)) {
				this.gamePlayModel.charachterTempPoint.x = this.gamePlayModel.charachterTempPoint.x + 1;
				try {
					synchronized (this.gamePlayModel.gameThread) {
						this.gamePlayModel.gameThread.notify();
					}

				} catch (Exception e1) {
					LogHelper.Log(LogHelper.TYPE_ERROR, e1.getMessage());
				}

				this.postProcessing(this.gamePlayModel.gameStatus);
			}
		}
	}

	/**
	 * 
	 * @param gameSatus
	 */
	public void postProcessing(GameStatus gameSatus) {
		switch (gameSatus.getGameStatus()) {
		case GameStatus.NEXT_LEVEL:
			this.gamePlayModel.setCurrentMapIndex(this.gamePlayModel.getCurrentMapIndex() + 1);
			this.gamePlayModel.deleteObserver(this.gamePlayView);
			this.gamePlayView.dispose();
			this.gamePlayView = null;
			this.gamePlayView = new GamePlayView(this.gamePlayModel, this);

			this.gamePlayModel.addObserver(this.gamePlayView);
			this.gamePlayView.setListener(this);
			this.gamePlayView.setVisible(true);

			this.gamePlayModel.getCharacterModel()
					.setCharacter_level(this.gamePlayModel.getCharacterModel().getCharacter_level() + 1);

			this.shownInventories = new ArrayList<CharacterModel>();

			matchNPCToPlayer();
			break;
		case GameStatus.WON_GAME:
			this.gamePlayView.dispose();
			this.gamePlayModel.deleteObserver(this.gamePlayView);
			JOptionPane.showMessageDialog(new JFrame(), "Congratulations!You won the game");
			new GameController();
			this.gamePlayModel.setGameRunning(false);
			break;
		case GameStatus.GAME_OVER:
			this.gamePlayView.dispose();
			this.gamePlayModel.deleteObserver(this.gamePlayView);
			JOptionPane.showMessageDialog(new JFrame(), "Oops game is over!");
			this.gamePlayModel.setGameRunning(false);
			new GameController();
			break;
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

		} else if (e.getSource().equals(this.gamePlayView.saveButton)) {
			JFileChooser c = new JFileChooser();
			c.setFileFilter(new FileNameExtensionFilter("JSON File", "json"));
			// Demonstrate "Save" dialog:
			int rVal = c.showSaveDialog(this.gamePlayView);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				String file = c.getSelectedFile().toString();
				if(file.length() > 5) {
					if(!(file.substring(file.length() - 5).equalsIgnoreCase(".json"))) {
						file = file+".json";
					} 
				} else {
					file = file+".json";
				}
				this.gamePlayModel.save(file);
				this.gamePlayModel.setGameRunning(false);
				this.gamePlayView.dispose();
				new GameController();
			}
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
		this.gamePlayModel.notifyChange();
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
		this.gamePlayModel.notifyChange();
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

	@Override
	public void run() {
		while (true) {
			try {
				WatchService watcher = FileSystems.getDefault().newWatchService();
				Path dir = Paths.get("res");
				dir.register(watcher, ENTRY_CREATE, ENTRY_MODIFY);

				while (true) {
					WatchKey key;
					try {
						key = watcher.take();
					} catch (InterruptedException ex) {
						return;
					}
					for (WatchEvent<?> event : key.pollEvents()) {
						WatchEvent.Kind<?> kind = event.kind();

						@SuppressWarnings("unchecked")
						WatchEvent<Path> ev = (WatchEvent<Path>) event;
						Path fileName = ev.context();
						if (kind == ENTRY_MODIFY && fileName.toString().equals("game.log")) {
							List<String> lines = LogHelper.getLastLine();
							this.gamePlayView.consoleTextArea.setText("");
							for (int i = 0; i < lines.size(); i++) {
								this.gamePlayView.consoleTextArea
										.setText(this.gamePlayView.consoleTextArea.getText() + lines.get(i) + "\n");
							}
						} else if (kind == ENTRY_CREATE && fileName.toString().equals("game.log")) {
							List<String> lines = LogHelper.getLastLine();
							this.gamePlayView.consoleTextArea.setText("");
							for (int i = 0; i < lines.size(); i++) {
								this.gamePlayView.consoleTextArea
										.setText(this.gamePlayView.consoleTextArea.getText() + lines.get(i) + "\n");
							}
						}
					}

					boolean valid = key.reset();
					if (!valid) {
						break;
					}
				}

			} catch (IOException ex) {
				System.err.println(ex);
			}
		}
	}
}
