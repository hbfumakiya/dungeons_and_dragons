package dungeons_and_dragons.model;

import java.awt.Color;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.google.gson.annotations.Expose;

import dungeons_and_dragons.controller.NPCItemController;
import dungeons_and_dragons.helper.DiceHelper;
import dungeons_and_dragons.helper.FileHelper;
import dungeons_and_dragons.helper.GameStatus;
import dungeons_and_dragons.helper.Game_constants;
import dungeons_and_dragons.helper.LogHelper;
import dungeons_and_dragons.helper.MapButton;
import dungeons_and_dragons.helper.MapCharacter;
import dungeons_and_dragons.helper.MapItem;
import dungeons_and_dragons.strategy.AggressiveNPC;
import dungeons_and_dragons.strategy.CharacterStrategy;
import dungeons_and_dragons.strategy.ComputerPlayer;
import dungeons_and_dragons.strategy.FriendlyNPC;
import dungeons_and_dragons.strategy.HumanPlayer;

/**
 * Once GamePlayModel gets a change state query request from any view they
 * respond to instructions to change the state from GamePlayController
 * 
 * @author Tejas Sadrani & Urmil Kansara & Mihir Pujara & Hirangi Naik
 */
public class GamePlayModel extends Observable implements Runnable {

	/**
	 * Sets id to the game play instance
	 */
	@Expose
	private int gamePlayId;

	public boolean ishit;
	/**
	 * Sets the location of the player that is playing
	 */
	@Expose
	private Point gameCharacterPosition;

	/**
	 * Creates an object of campaign
	 */
	@Expose
	private CampaignModel campaignModel;

	/**
	 * Creates an object of character
	 */
	@Expose
	private CharacterModel characterModel;

	@Expose
	private int currentMapIndex;

	@Expose
	private ArrayList<MapCharacter> turnList;

	@Expose
	private int currentTurn;

	@Expose
	private boolean isGameRunning;

	@Expose
	private String playerStrategy;

	public Thread gameThread;

	public Point charachterTempPoint;

	public Point charachterOldPoint;

	public GameStatus gameStatus = new GameStatus();

	public Point attackStartPoint;

	public Point attackEndPoint;

	public Point enemyPoint;

	public int currentEnemyIndex;

	public int currentFriendIndex;

	public ArrayList<MapCharacter> enemyList;

	public ArrayList<MapCharacter> friendList;

	public MapButton currentMap[][];

	/**
	 * constructor to initialize map object
	 */
	public GamePlayModel() {
		this.currentMapIndex = 0;
		this.turnList = new ArrayList<MapCharacter>();
		this.enemyList = new ArrayList<MapCharacter>();
		this.friendList = new ArrayList<MapCharacter>();
		this.gamePlayId = 0;
		this.currentTurn = 0;
		this.isGameRunning = true;
		this.playerStrategy = MapCharacter.NORMAL;
	}

	/**
	 * @return the gamePlayId
	 */
	public int getGamePlayId() {
		return gamePlayId;
	}

	/**
	 * @param gamePlayId
	 *            the gamePlayId to set
	 */
	public void setGamePlayId(int gamePlayId) {
		this.gamePlayId = gamePlayId;
	}

	/**
	 * @return the campaignModel
	 */
	public CampaignModel getCampaignModel() {
		return campaignModel;
	}

	/**
	 * @param campaignModel
	 *            the campaignModel to set
	 */
	public void setCampaignModel(CampaignModel campaignModel) {
		this.campaignModel = campaignModel;
	}

	/**
	 * @return the characterModel
	 */
	public CharacterModel getCharacterModel() {
		return characterModel;
	}

	/**
	 * @param characterModel
	 *            the characterModel to set
	 */
	public void setCharacterModel(CharacterModel characterModel) {
		this.characterModel = characterModel;
	}

	/**
	 * to save it to file
	 * 
	 * @param path
	 *            location
	 */
	public void save(String path) {

		try {
			FileHelper.saveGame(path, this);
		} catch (IOException e) {
			LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
		}

	}

	/**
	 * returns character position
	 * 
	 * @return point of current position of character
	 */
	public Point getGameCharacterPosition() {
		return gameCharacterPosition;
	}

	/**
	 * set the current position of player
	 * 
	 * @param gameCharacterPosition
	 *            current point of player
	 */
	public void setGameCharacterPosition(Point gameCharacterPosition) {
		this.gameCharacterPosition = gameCharacterPosition;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * @return the turnList
	 */
	public ArrayList<MapCharacter> getTurnList() {
		return turnList;
	}

	/**
	 * @param turnList
	 *            the turnList to set
	 */
	public void setTurnList(ArrayList<MapCharacter> turnList) {
		this.turnList = turnList;
	}

	/**
	 * @return the currentTurn
	 */
	public int getCurrentTurn() {
		return currentTurn;
	}

	/**
	 * @param currentTurn
	 *            the currentTurn to set
	 */
	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}

	/**
	 * @return the isGameRunning
	 */
	public boolean isGameRunning() {
		return isGameRunning;
	}

	/**
	 * @param isGameRunning
	 *            the isGameRunning to set
	 */
	public void setGameRunning(boolean isGameRunning) {
		this.isGameRunning = isGameRunning;
	}

	/**
	 * @return the playerStrategy
	 */
	public String getPlayerStrategy() {
		return playerStrategy;
	}

	/**
	 * @param playerStrategy
	 *            the playerStrategy to set
	 */
	public void setPlayerStrategy(String playerStrategy) {
		this.playerStrategy = playerStrategy;
	}

	/**
	 * @return the currentMapIndex
	 */
	public int getCurrentMapIndex() {
		return currentMapIndex;
	}

	/**
	 * @param currentMapIndex
	 *            the currentMapIndex to set
	 */
	public void setCurrentMapIndex(int currentMapIndex) {
		this.currentMapIndex = currentMapIndex;
	}

	/**
	 * Method to remove Point in the map
	 * 
	 * @param tempPoint
	 */
	public void removeChest(Point tempPoint) {
		int i = this.getCurrentMapIndex();
		this.getCampaignModel().getOutput_map_list().get(i).setMap_chest(new MapItem());
		setChanged();
		notifyObservers();

	}

	/**
	 * move character according to key listener
	 * 
	 * @param newPoint
	 * @param oldPoint
	 */
	public void moveCharacterUp(Point oldPoint) {
		Point newPoint = new Point();
		newPoint.x = (int) oldPoint.getX();
		newPoint.y = (int) oldPoint.getY() - 1;
		this.setGameCharacterPosition(newPoint);
	}

	/**
	 * move character according to key listener
	 * 
	 * @param newPoint
	 * @param oldPoint
	 */
	public void moveCharacterDown(Point oldPoint) {
		Point newPoint = new Point();
		newPoint.x = (int) oldPoint.getX();
		newPoint.y = (int) oldPoint.getY() + 1;
		this.setGameCharacterPosition(newPoint);
	}

	/**
	 * move character according to key listener
	 * 
	 * @param newPoint
	 * @param oldPoint
	 */
	public void moveCharacterRight(Point oldPoint) {
		Point newPoint = new Point();
		newPoint.x = (int) oldPoint.getX() + 1;
		newPoint.y = (int) oldPoint.getY();
		this.setGameCharacterPosition(newPoint);
	}

	/**
	 * move character according to key listener
	 * 
	 * @param newPoint
	 * @param oldPoint
	 */
	public void moveCharacterLeft(Point oldPoint) {
		Point newPoint = new Point();
		newPoint.x = (int) oldPoint.getX() - 1;
		newPoint.y = (int) oldPoint.getY();
		this.setGameCharacterPosition(newPoint);
	}

	/**
	 * 
	 * @param level
	 * @return
	 */
	public int getItemScoreByLevel(int level) {
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
	 * calculate turn of all players of the current map and store it sortedlist
	 * in turnlist to determine turn of every players
	 */
	public void calculateTurn() {
		Map<Integer, MapCharacter> tempValues = new HashMap<Integer, MapCharacter>();

		// roll dice and calculate turn values for character
		MapCharacter currentCharacter = new MapCharacter();
		currentCharacter.setCharacter(this.characterModel);
		currentCharacter.setCharacterType(this.playerStrategy);
		tempValues.put(DiceHelper.rollD20() + this.characterModel.getModifiers().getDexterity(), currentCharacter);

		// roll dice and calculate turn values for NPCs
		ArrayList<MapCharacter> npcs = this.campaignModel.getOutput_map_list().get(this.getCurrentMapIndex())
				.getMap_enemy_loc();
		for (int i = 0; i < npcs.size(); i++) {
			tempValues.put(DiceHelper.rollD20() + npcs.get(i).getCharacter().getModifiers().getDexterity(),
					npcs.get(i));
		}

		Map<Integer, MapCharacter> treeMap = new TreeMap<>((Comparator<Integer>) (o1, o2) -> o2.compareTo(o1));

		treeMap.putAll(tempValues);

		setValueToTurnList(treeMap);
	}

	/**
	 * these mathod start game and manage all game
	 */
	public void startGame() {
		CharacterStrategy characterStrategy;
		for (int i = 0; i < turnList.size(); i++) {
			this.currentTurn = i;
			switch (this.turnList.get(i).getCharacterType()) {
			case MapCharacter.NORMAL:
				characterStrategy = new CharacterStrategy();
				characterStrategy.setStrategy(new HumanPlayer());
				this.turnList.get(i).setCharacterStrategy(characterStrategy);
				break;
			case MapCharacter.COMPUTER:
				characterStrategy = new CharacterStrategy();
				characterStrategy.setStrategy(new ComputerPlayer());
				this.turnList.get(i).setCharacterStrategy(characterStrategy);
				break;
			case MapCharacter.ENEMY:
				characterStrategy = new CharacterStrategy();
				characterStrategy.setStrategy(new AggressiveNPC());
				this.turnList.get(i).setCharacterStrategy(characterStrategy);
				break;
			case MapCharacter.FRIENDLY:
				characterStrategy = new CharacterStrategy();
				characterStrategy.setStrategy(new FriendlyNPC());
				this.turnList.get(i).setCharacterStrategy(characterStrategy);
				break;
			}
		}
		this.gameThread = new Thread(this);
		this.gameThread.start();

	}

	/**
	 * @param map
	 *            - Map<Integer, CharacterModel>
	 */
	private <K, V> void setValueToTurnList(Map<Integer, MapCharacter> map) {
		for (Map.Entry<Integer, MapCharacter> entry : map.entrySet()) {
			this.turnList.add(entry.getValue());
		}
	}

	/**
	 * This function checks if the boundary conditions are reached
	 * 
	 * @param tempPoint
	 * @return false if boundary else true
	 */
	private boolean checkBoundaries(Point tempPoint) {
		if (tempPoint.x < 0 || tempPoint.y < 0
				|| tempPoint.x >= this.getCampaignModel().getOutput_map_list().get(this.getCurrentMapIndex())
						.getMap_size().getX()
				|| tempPoint.y >= this.getCampaignModel().getOutput_map_list().get(this.getCurrentMapIndex())
						.getMap_size().getY()) {

			/*
			 * TODO this.gamePlayView.consoleTextArea.setForeground(Color.RED);
			 * this.gamePlayView.consoleTextArea
			 * .setText(this.gamePlayView.consoleTextArea.getText() +
			 * "Oops...Cannot go ahead...\n");
			 */
			return false;
		} else {
			// boundary not reached
			return true;
		}
	}

	/**
	 * This function match character's level to NPC and calculate
	 * modifiers,armorclass,attackbonus,hitpoints and damage bonus.
	 * 
	 */
	public void matchNPCToPlayer() {

		ArrayList<MapCharacter> npc = this.getCampaignModel().getOutput_map_list().get(this.getCurrentMapIndex())
				.getMap_enemy_loc();

		CharacterModel character;

		for (int i = 0; i < npc.size(); i++) {
			character = npc.get(i).getCharacter();
			character.setCharacter_level(this.getCharacterModel().getCharacter_level());
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

	@Override
	public void run() {
		while (isGameRunning) {
			for (int i = 0; i < turnList.size(); i++) {
				this.currentTurn = i;
				this.turnList.get(i).getCharacterStrategy().executeStrategy(this);
			}
		}
	}

	public boolean checkWalls(Point point) {

		ArrayList<Point> wallList = this.getCampaignModel().getOutput_map_list().get(this.getCurrentMapIndex())
				.getMap_walls();

		return wallList.contains(point);
	}

	/**
	 * This functions allows the Player to move from its position based on the
	 * key pressed
	 * 
	 * @param tempPoint
	 *            next point in the map
	 * @param oldPoint
	 *            old point in the map
	 */
	public GameStatus moveCharacter(Point tempPoint, Point oldPoint) {

		GameStatus gameStatus = new GameStatus();
		// first of all check if the boundaries have been reached
		if (checkBoundaries(tempPoint)) {
			if (this.checkWalls(tempPoint)) {
				gameStatus.setGameStatus(GameStatus.RUNNING);
			} else if (this.checkChest(tempPoint)) {

				GameMapModel map = this.getCampaignModel().getOutput_map_list().get(this.getCurrentMapIndex());

				String msg = "";
				if (map.getMap_chest() != null && map.getMap_chest().getX() != -1 && map.getMap_chest().getY() != -1) {
					ArrayList<ItemModel> backPackItems = this.getCharacterModel().getBackPackItems();
					if (backPackItems.size() < 10) {
						backPackItems.add(map.getMap_chest().getItem());
						this.getCharacterModel().setBackPackItems(backPackItems);

						ItemModel i = this.getCampaignModel().getOutput_map_list().get(this.getCurrentMapIndex())
								.getMap_chest().getItem();

						msg = "Item " + i.getItem_name() + " has been added in your backpack";

						this.removeChest(tempPoint);
					} else {
						msg = "Sorry your backpack is full.So cannot add any new Item";
					}
					this.setGameCharacterPosition(tempPoint);
					JOptionPane.showMessageDialog(new JFrame(), msg);
				}

				gameStatus.setGameStatus(GameStatus.RUNNING);

			} else if (this.checkCharacter(tempPoint)) {

				ArrayList<MapCharacter> chars = this.getCampaignModel().getOutput_map_list()
						.get(this.getCurrentMapIndex()).getMap_enemy_loc();

				MapCharacter npcLocal = null;

				for (int i = 0; i < chars.size(); i++) {
					if ((tempPoint.getX() == chars.get(i).getX()) && (tempPoint.getY() == chars.get(i).getY())) {
						npcLocal = chars.get(i);
					}
				}

				if (npcLocal != null) {
					if (npcLocal.getCharacterType().equals(MapCharacter.ENEMY)) {
						System.out.println("Enemy");
						GameMapModel map = this.getCampaignModel().getOutput_map_list().get(this.getCurrentMapIndex());
						int numOfCharacters = map.getMap_enemy_loc().size();
						CharacterModel enemy = new CharacterModel();
						MapCharacter enemyMap = new MapCharacter();
						int index = -1;
						for (int j = 0; j < numOfCharacters; j++) {

							if (map.getMap_enemy_loc().get(j).getX() == tempPoint.x
									&& map.getMap_enemy_loc().get(j).getY() == tempPoint.y) {
								enemy = map.getMap_enemy_loc().get(j).getCharacter();
								enemyMap = map.getMap_enemy_loc().get(j);
								index = j;
							}
						}

						CharacterModel player = this.getCharacterModel();
						boolean enemyAlive = true;
						if (enemy != null)
							enemyAlive = fightWithEnemy(enemy, player);

						enemy.updateView();
						String msg = "";
						if (enemyAlive == false) {
							JOptionPane.showMessageDialog(new JFrame(),
									"Enemy " + enemy.getCharacter_name() + " is dead.You can loot its items");
							enemy.setAlive(false);
							ArrayList<ItemModel> allEnemyItems = new ArrayList<ItemModel>();
							if (!enemy.getItems().isEmpty()) {
								allEnemyItems = enemy.getItems();
							}
							if (!enemy.getBackPackItems().isEmpty()) {
								for (int i = 0; i < enemy.getBackPackItems().size(); i++) {
									allEnemyItems.add(enemy.getBackPackItems().get(i));
								}
							}
							new NPCItemController(this, allEnemyItems, true, enemy);

							msg = "is dead.You can loot its item";
						}
					} else if (npcLocal.getCharacterType().equals(MapCharacter.FRIENDLY)) {
						System.out.println("Friendly");
						GameMapModel map = this.getCampaignModel().getOutput_map_list().get(this.getCurrentMapIndex());
						int numOfCharacters = map.getMap_enemy_loc().size();
						CharacterModel friendly = new CharacterModel();
						for (int j = 0; j < numOfCharacters; j++) {

							if (map.getMap_enemy_loc().get(j).getX() == tempPoint.x
									&& map.getMap_enemy_loc().get(j).getY() == tempPoint.y) {
								friendly = map.getMap_enemy_loc().get(j).getCharacter();
							}
						}
						new NPCItemController(this, this.getCharacterModel().getBackPackItems(), false, friendly);
					}
				}
				this.setGameCharacterPosition(tempPoint);

				gameStatus.setGameStatus(GameStatus.RUNNING);

			} else {
				this.setGameCharacterPosition(tempPoint);

				gameStatus.setGameStatus(GameStatus.RUNNING);
			}
		} else {

			if (this.getCampaignModel().getOutput_map_list().get(this.getCurrentMapIndex()).getMap_exit_door()
					.equals(oldPoint)) {

				ArrayList<MapCharacter> npcs = this.getCampaignModel().getOutput_map_list()
						.get(this.getCurrentMapIndex()).getMap_enemy_loc();

				int totalEnemy = 0;
				int deadEnemy = 0;
				for (int i = 0; i < npcs.size(); i++) {

					if (npcs.get(i).getCharacterType().equals(MapCharacter.ENEMY)) {
						totalEnemy++;
						if (!npcs.get(i).getCharacter().isAlive()) {
							deadEnemy++;
						}
					}
				}

				if (totalEnemy == deadEnemy) {
					if (this.getCurrentMapIndex() + 1 < this.getCampaignModel().getOutput_map_list().size()) {
						gameStatus.setGameStatus(GameStatus.NEXT_LEVEL);
					} else {
						gameStatus.setGameStatus(GameStatus.WON_GAME);
					}
				} else {
					gameStatus.setGameStatus(GameStatus.KILL_ALL);
					JOptionPane.showMessageDialog(new JFrame(), "You have to kill all enemies to go to next level");
				}
			}
		}
		setChanged();
		notifyObservers();
		return gameStatus;
	}

	public boolean checkCharacter(Point point) {
		ArrayList<MapCharacter> npcsLocal = this.getCampaignModel().getOutput_map_list().get(this.getCurrentMapIndex())
				.getMap_enemy_loc();

		for (int i = 0; i < npcsLocal.size(); i++) {
			if ((point.getX() == npcsLocal.get(i).getX()) && (point.getY() == npcsLocal.get(i).getY())) {
				return true;
			}
		}
		return false;
	}

	public boolean checkChest(Point point) {
		MapItem chest = this.getCampaignModel().getOutput_map_list().get(this.getCurrentMapIndex()).getMap_chest();
		return ((point.getX() == chest.getX()) && (point.getY() == chest.getY()));
	}

	/**
	 * This function validates if any character can perform a move or not
	 * 
	 * @param tempPoint
	 * @param oldPoint
	 * @return status of the game after the move event
	 */
	public GameStatus validateMove(Point tempPoint, Point oldPoint) {

		if (checkBoundaries(tempPoint) || !this.checkWalls(tempPoint)) {
			this.setGameCharacterPosition(tempPoint);
		} else {
			// shouldn't allow the move and show type info error message
			this.setGameCharacterPosition(oldPoint);
			charachterTempPoint = charachterOldPoint;
			LogHelper.Log(LogHelper.TYPE_INFO, "Oops bumped into a wall can't move ahead");
		}
		gameStatus.setGameStatus(GameStatus.RUNNING);
		setChanged();
		notifyObservers();
		return gameStatus;
	}

	/**
	 * This function checks if there is any valid NPC in range of the player
	 * character or not
	 * 
	 * @param tempPoint
	 * @return true if attack can be initiated on an enemy else it returns false
	 */
	public boolean validateAttack(MapCharacter characterFrom, MapCharacter characterTo) {

		// check if player is on enemies cell or not
		if (characterFrom.getX() == characterTo.getX() && characterFrom.getY() == characterTo.getY()) {
			return true;
		} else {
			ArrayList<ItemModel> items = characterFrom.getCharacter().getItems();
			if (items == null || items.size() < 1) {
				return false;
			}
			boolean isRange = false;
			boolean isMelle = false;

			for (int k = 0; k < items.size(); k++) {
				if (items.get(k).getItem_type().equals(Game_constants.WEAPON_MELEE)) {
					isMelle = true;
				} else if (items.get(k).getItem_type().equals(Game_constants.WEAPON_RANGE)) {
					isRange = true;
				}
			}

			if (!isRange && !isMelle) {
				return false;
			} else {
				Point startPoint = new Point();
				Point endPoint = new Point();
				GameMapModel currentMap = this.getCampaignModel().getOutput_map_list().get(this.getCurrentMapIndex());

				if (isMelle) {
					startPoint.x = characterFrom.getX() - 1;
					startPoint.y = characterFrom.getY() - 1;
					endPoint.x = characterFrom.getX() + 1;
					endPoint.y = characterFrom.getY() + 1;
				} else {
					startPoint.x = characterFrom.getX() - 2;
					startPoint.y = characterFrom.getY() - 2;
					endPoint.x = characterFrom.getX() + 2;
					endPoint.y = characterFrom.getY() + 2;
				}

				Point mapSize = currentMap.getMap_size();
				if (startPoint.x < 0) {
					startPoint.x = 0;
				}
				if (startPoint.y < 0) {
					startPoint.y = 0;
				}
				if (endPoint.x > mapSize.x) {
					endPoint.x = mapSize.x;
				}
				if (endPoint.y > mapSize.y) {
					endPoint.y = mapSize.y;
				}

				if (characterTo.getX() >= startPoint.x && characterTo.getX() <= endPoint.x
						&& characterTo.getY() >= startPoint.y && characterTo.getY() <= endPoint.y) {
					return true;
				}
			}
		}

		return false;
	}

	private void attackToPlayer(MapCharacter character) {
		// TODO Auto-generated method stub

	}

	public void attackToEnemy(MapCharacter character) {
		character.setX((int) this.getGameCharacterPosition().getX());
		character.setY((int) this.getGameCharacterPosition().getY());

		for (MapCharacter turnChar : this.getTurnList()) {
			if (turnChar.getCharacterType().equals(MapCharacter.ENEMY) && turnChar.getCharacter().isAlive()) {
				if (this.validateAttack(character, turnChar)) {
					int temp = character.getCharacter().getAttackBonus();
					int diceValue = DiceHelper.rollD20();
					int stModi = character.getCharacter().getModifiers().getStraight();
					for (int i = temp; i > 0; i -= 5) {
						if ((diceValue + stModi + i) >= turnChar.getCharacter().getArmorClass()) {
							ishit = true;
							LogHelper.Log(LogHelper.TYPE_INFO, "Player can hit anemy");

							ArrayList<ItemModel> items = character.getCharacter().getItems();
							if (items == null || items.size() < 1) {
								break;
							}
							boolean isRange = false;
							boolean isMelle = false;

							for (int k = 0; k < items.size(); k++) {
								if (items.get(k).getItem_type().equals(Game_constants.WEAPON_MELEE)) {
									isMelle = true;
								} else if (items.get(k).getItem_type().equals(Game_constants.WEAPON_RANGE)) {
									isRange = true;
								}
							}
							int diceD8 = DiceHelper.rollD8();
							if (isMelle) {
								int points = (diceD8 + character.getCharacter().getModifiers().getStraight());
								turnChar.getCharacter().setHitpoints(turnChar.getCharacter().getHitpoints() - points);
								LogHelper.Log(LogHelper.TYPE_INFO, points + " hit point deducted from enemy");
							} else if (isRange) {
								int points = diceD8;
								turnChar.getCharacter().setHitpoints(turnChar.getCharacter().getHitpoints() - points);
								LogHelper.Log(LogHelper.TYPE_INFO, points + " hit point deducted from enemy");
							}
						}
					}
					if (turnChar.getCharacter().getHitpoints() <= -10) {
						turnChar.getCharacter().setAlive(false);
						AbilityScoresModel zeroAbilities = new AbilityScoresModel();
						zeroAbilities.setCharisma(-10);
						zeroAbilities.setConstitution(-10);
						zeroAbilities.setDexterity(-10);
						zeroAbilities.setIntelligence(-10);
						zeroAbilities.setstrength(-10);
						zeroAbilities.setWisdom(-10);

						turnChar.getCharacter().setAbilityScores(zeroAbilities);
						turnChar.getCharacter().setAttackBonus(0);
						turnChar.getCharacter().setHitpoints(0);
						turnChar.getCharacter().setDamageBonus(0);
						turnChar.getCharacter().setArmorClass(0);
						turnChar.getCharacter().setRawAbilityScores(zeroAbilities);
						turnChar.getCharacter().calculateModifires();

						this.turnList.remove(turnChar);
					}
					break;
				}
			}
		}
	}

	/**
	 * This function performs an attack on a character using D20 rules. Checks
	 * the status and returns the appropriate situation accordingly.
	 * 
	 * @return
	 */
	public GameStatus initiateAttack(MapCharacter character) {
		if (character.getCharacterType().equals(MapCharacter.COMPUTER)
				|| character.getCharacterType().equals(MapCharacter.NORMAL)) {

			this.attackToEnemy(character);

		} else if (character.getCharacterType().equals(MapCharacter.ENEMY)) {

			this.attackToPlayer(character);
		}
		return null;
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
	public boolean fightWithEnemy(CharacterModel enemy, CharacterModel player) {
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
	 * This function validates if there is a chest or a dead NPC or a friend to
	 * share items and if it exists it interacts accordingly
	 * 
	 * @param tempPoint
	 * @return true if yes
	 */
	public GameStatus initateInteract(Point tempPoint) {
		gameStatus = new GameStatus();
		if (this.checkChest(tempPoint)) {

			GameMapModel map = this.getCampaignModel().getOutput_map_list().get(this.getCurrentMapIndex());

			String msg = "";
			if (map.getMap_chest() != null && map.getMap_chest().getX() != -1 && map.getMap_chest().getY() != -1) {
				ArrayList<ItemModel> backPackItems = this.getCharacterModel().getBackPackItems();
				if (backPackItems.size() < 10) {
					backPackItems.add(map.getMap_chest().getItem());
					this.getCharacterModel().setBackPackItems(backPackItems);

					ItemModel i = this.getCampaignModel().getOutput_map_list().get(this.getCurrentMapIndex())
							.getMap_chest().getItem();

					msg = "Item " + i.getItem_name() + " has been added in your backpack";

					this.removeChest(tempPoint);
				} else {
					msg = "Sorry your backpack is full.So cannot add any new Item";
				}
				this.setGameCharacterPosition(tempPoint);
				LogHelper.Log(LogHelper.TYPE_INFO, msg);
			}

		}
		// interact with friend & enemy
		else if (this.checkCharacter(tempPoint)) {
			String msg = "";
			ArrayList<MapCharacter> chars = this.getCampaignModel().getOutput_map_list().get(this.getCurrentMapIndex())
					.getMap_enemy_loc();

			MapCharacter npcLocal = null;

			for (int i = 0; i < chars.size(); i++) {
				if ((tempPoint.getX() == chars.get(i).getX()) && (tempPoint.getY() == chars.get(i).getY())) {
					npcLocal = chars.get(i);
				}
			}

			if (npcLocal != null) {
				if (npcLocal.getCharacterType().equals(MapCharacter.FRIENDLY)) {
					System.out.println("Friendly");
					GameMapModel map = this.getCampaignModel().getOutput_map_list().get(this.getCurrentMapIndex());
					int numOfCharacters = map.getMap_enemy_loc().size();
					CharacterModel friendly = new CharacterModel();
					for (int j = 0; j < numOfCharacters; j++) {

						if (map.getMap_enemy_loc().get(j).getX() == tempPoint.x
								&& map.getMap_enemy_loc().get(j).getY() == tempPoint.y) {
							friendly = map.getMap_enemy_loc().get(j).getCharacter();
						}
					}
					new NPCItemController(this, this.getCharacterModel().getBackPackItems(), false, friendly);
				} else if (npcLocal.getCharacterType().equals(MapCharacter.ENEMY)) {
					
					System.out.println("Enemy");
					GameMapModel map = this.getCampaignModel().getOutput_map_list().get(this.getCurrentMapIndex());
					int numOfCharacters = map.getMap_enemy_loc().size();
					CharacterModel enemy = new CharacterModel();
					MapCharacter enemyMap = new MapCharacter();
					int index = -1;
					for (int j = 0; j < numOfCharacters; j++) {

						if (map.getMap_enemy_loc().get(j).getX() == tempPoint.x
								&& map.getMap_enemy_loc().get(j).getY() == tempPoint.y) {
							enemy = map.getMap_enemy_loc().get(j).getCharacter();
							enemyMap = map.getMap_enemy_loc().get(j);
							index = j;
						}
					}
					
					ArrayList<ItemModel> allEnemyItems = new ArrayList<ItemModel>();
					if (!enemy.getItems().isEmpty()) {
						for(int i = 0;i<enemy.getItems().size();i++)
						allEnemyItems.add( enemy.getItems().get(i));
					}
					if (!enemy.getBackPackItems().isEmpty()) {
						for (int i = 0; i < enemy.getBackPackItems().size(); i++) {
							allEnemyItems.add(enemy.getBackPackItems().get(i));
						}
					}
					new NPCItemController(this, allEnemyItems, true, enemy);
					
				}
			}

		} else {
			LogHelper.Log(LogHelper.TYPE_INFO, "No interaction to any player so no item found");
		}

		gameStatus.setGameStatus(GameStatus.RUNNING);
		return gameStatus;

	}

	/**
	 * Method to move enemy
	 * 
	 * @param enemy
	 *            current enemy being moved
	 */
	public void moveEnemy(MapCharacter enemy) {
		Point playerPosition = this.gameCharacterPosition;
		if (enemy.Frightening == false) {
			moveAggresiveEnemy(enemy, playerPosition, 1);
			moveAggresiveEnemy(enemy, playerPosition, 2);
			moveAggresiveEnemy(enemy, playerPosition, 3);

		} else if (enemy.Frightening == true && enemy.frighteningTurn <= enemy.frighteningBonus - 1) {
			++enemy.frighteningTurn;
			moveFrightenedEnemy(enemy, playerPosition);
			moveFrightenedEnemy(enemy, playerPosition);
			moveFrightenedEnemy(enemy, playerPosition);
		}

		setChanged();
		notifyObservers();

	}

	/**
	 * Frightened enemy move towards walls
	 * 
	 * @param enemy
	 *            enemy
	 * @param playerPosition
	 *            player current position
	 */
	public void moveFrightenedEnemy(MapCharacter enemy, Point playerPosition) {
		int mapSizeX = this.getCampaignModel().getOutput_map_list().get(this.getCurrentMapIndex()).getMap_size().x - 1;
		int mapSizeY = this.getCampaignModel().getOutput_map_list().get(this.getCurrentMapIndex()).getMap_size().y - 1;

		int enemyX = enemy.getX();
		int enemyY = enemy.getY();
		// go down
		if (playerPosition.x < enemy.getX() && enemy.getX() < mapSizeX
				&& this.checkWalls(new Point(enemyX + 1, enemyY))) {

			enemy.setX(enemy.getX() + 1);

		}
		// go up
		else if (playerPosition.x > enemy.getX() && enemy.getX() > 0
				&& this.checkWalls(new Point(enemyX - 1, enemyY))) {
			enemy.setX(enemy.getX() - 1);

		}
		// go left
		else if (playerPosition.y < enemy.getY() && enemy.getY() > 0
				&& this.checkWalls(new Point(enemyX, enemyY + 1))) {
			enemy.setY(enemy.getY() + 1);

		}
		// go right
		else if (playerPosition.y > enemy.getY() && enemy.getY() < mapSizeY
				&& this.checkWalls(new Point(enemyX, enemyY - 1))) {
			enemy.setY(enemy.getY() - 1);

		}

		setChanged();
		notifyObservers();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private HashMap<CharacterModel, Point> prevEnemyPos = new HashMap<CharacterModel, Point>();

	public void prevPosition(CharacterModel character, Point p) {
		prevEnemyPos.remove(character);
		prevEnemyPos.put(character, p);
	}

	public void removePostion() {
		prevEnemyPos.clear();
	}

	/**
	 * aggresive enemy move towards player
	 * 
	 * @param enemy
	 *            enemy
	 * @param playerPosition
	 *            player current position
	 */
	public void moveAggresiveEnemy(MapCharacter enemy, Point playerPosition, int moveNumber) {
		int enemyX = enemy.getX();
		int enemyY = enemy.getY();
		Point nextPos = prevEnemyPos.get(enemy.getCharacter());
		if (nextPos == null) {
			nextPos = new Point(-1, -1);
		}
		// go down
		if (playerPosition.x > enemy.getX() && !this.checkWalls(new Point(enemyX + 1, enemyY))
				&& nextPos.x != enemyX + 1)// &&
		// this.checkWalls(new
		// Point(enemyX+1,enemyY)))
		{
			prevPosition(enemy.getCharacter(), new Point(enemy.getX(), enemy.getY()));
			enemy.setX(enemy.getX() + 1);
			//check left right and dwon
			if (!this.checkWalls(new Point(enemyX + 1, enemyY)) && !this.checkWalls(new Point(enemyX, enemyY + 1))
					&& !this.checkWalls(new Point(enemyX, enemyY - 1))) {
				prevPosition(enemy.getCharacter(), new Point(-1,-1));

			}

		}
		// go up
		else if (playerPosition.x < enemy.getX() && !this.checkWalls(new Point(enemyX - 1, enemyY))
				&& nextPos.x != enemyX - 1)// && this.checkWalls(new
											// Point(enemyX-1,enemyY)))
		{
			prevPosition(enemy.getCharacter(), new Point(enemy.getX(), enemy.getY()));
			enemy.setX(enemy.getX() - 1);
			//check left right and dwon
			if (!this.checkWalls(new Point(enemyX - 1, enemyY)) && !this.checkWalls(new Point(enemyX, enemyY + 1))
					&& !this.checkWalls(new Point(enemyX, enemyY - 1))) {
				prevPosition(enemy.getCharacter(), new Point(-1,-1));

			}

		}
		// go right
		else if (playerPosition.y > enemy.getY() && !this.checkWalls(new Point(enemyX, enemyY + 1))
				&& nextPos.y != enemyY + 1)// && this.checkWalls(new
											// Point(enemyX,enemyY+1)))
		{
			prevPosition(enemy.getCharacter(), new Point(enemy.getX(), enemy.getY()));
			enemy.setY(enemy.getY() + 1);
			//check  right and dwon and up
			if (!this.checkWalls(new Point(enemyX+1 , enemyY)) && !this.checkWalls(new Point(enemyX, enemyY + 1))
					&& !this.checkWalls(new Point(enemyX-1, enemyY))) {
				prevPosition(enemy.getCharacter(), new Point(-1,-1));

			}

		}
		// go left
		else if (playerPosition.y < enemy.getY() && !this.checkWalls(new Point(enemyX, enemyY - 1))
				&& nextPos.y != enemyY - 1)// && this.checkWalls(new
											// Point(enemyX,enemyY-1)))
		{
			prevPosition(enemy.getCharacter(), new Point(enemy.getX(), enemy.getY()));
			enemy.setY(enemy.getY() - 1);
			//check left right and dwon
			if (!this.checkWalls(new Point(enemyX + 1, enemyY)) && !this.checkWalls(new Point(enemyX, enemyY - 1))
					&& !this.checkWalls(new Point(enemyX-1, enemyY))) {
				prevPosition(enemy.getCharacter(), new Point(-1,-1));

			}

		} else if (playerPosition.x == enemyX && playerPosition.y != enemyY) {
			// go down
			if (!this.checkWalls(new Point(enemyX + 1, enemyY)) && nextPos.x != enemyX + 1)// &&
			// this.checkWalls(new
			// Point(enemyX+1,enemyY)))
			{
				prevPosition(enemy.getCharacter(), new Point(enemy.getX(), enemy.getY()));
				enemy.setX(enemy.getX() + 1);
				//check left right and dwon
				if (!this.checkWalls(new Point(enemyX + 1, enemyY)) && !this.checkWalls(new Point(enemyX, enemyY + 1))
						&& !this.checkWalls(new Point(enemyX, enemyY - 1))) {
					prevPosition(enemy.getCharacter(), new Point(-1,-1));

				}

			}
			// go up
			else if (!this.checkWalls(new Point(enemyX - 1, enemyY)) && nextPos.x != enemyX - 1)// &&
																								// this.checkWalls(new
																								// Point(enemyX-1,enemyY)))
			{
				prevPosition(enemy.getCharacter(), new Point(enemy.getX(), enemy.getY()));
				enemy.setX(enemy.getX() - 1);
				//check left right and dwon
				if (!this.checkWalls(new Point(enemyX - 1, enemyY)) && !this.checkWalls(new Point(enemyX, enemyY + 1))
						&& !this.checkWalls(new Point(enemyX, enemyY - 1))) {
					prevPosition(enemy.getCharacter(), new Point(-1,-1));

				}

			}
		} else if (playerPosition.y == enemyY && playerPosition.x != enemyX) {
			// go right
			 if (playerPosition.y > enemy.getY() && !this.checkWalls(new Point(enemyX, enemyY + 1))
					&& nextPos.y != enemyY + 1)// && this.checkWalls(new
												// Point(enemyX,enemyY+1)))
			{
				prevPosition(enemy.getCharacter(), new Point(enemy.getX(), enemy.getY()));
				enemy.setY(enemy.getY() + 1);
				//check  right and dwon and up
				if (!this.checkWalls(new Point(enemyX+1 , enemyY)) && !this.checkWalls(new Point(enemyX, enemyY + 1))
						&& !this.checkWalls(new Point(enemyX-1, enemyY))) {
					prevPosition(enemy.getCharacter(), new Point(-1,-1));

				}

			}
			// go left
			else if (playerPosition.y < enemy.getY() && !this.checkWalls(new Point(enemyX, enemyY - 1))
					&& nextPos.y != enemyY - 1)// && this.checkWalls(new
												// Point(enemyX,enemyY-1)))
			{
				prevPosition(enemy.getCharacter(), new Point(enemy.getX(), enemy.getY()));
				enemy.setY(enemy.getY() - 1);
				//check left right and dwon
				if (!this.checkWalls(new Point(enemyX + 1, enemyY)) && !this.checkWalls(new Point(enemyX, enemyY - 1))
						&& !this.checkWalls(new Point(enemyX-1, enemyY))) {
					prevPosition(enemy.getCharacter(), new Point(-1,-1));

				}

			}
		}
		

		if (moveNumber == 3) {
			prevPosition(enemy.getCharacter(), new Point(-1, -1));
		}
		setChanged();
		notifyObservers();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

	/**
	 * Void method used to notify a change in state if anything gets changed
	 */
	public void notifyChange() {
		setChanged();
		notifyObservers(this);
	}

}
