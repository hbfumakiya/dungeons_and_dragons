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

import com.google.gson.annotations.Expose;

import dungeons_and_dragons.helper.DiceHelper;
import dungeons_and_dragons.helper.FileHelper;
import dungeons_and_dragons.helper.LogHelper;
import dungeons_and_dragons.helper.MapCharacter;
import dungeons_and_dragons.helper.MapItem;
import dungeons_and_dragons.strategy.AggressiveNPC;
import dungeons_and_dragons.strategy.CharacterStrategy;
import dungeons_and_dragons.strategy.ComputerPlayer;
import dungeons_and_dragons.strategy.FriendlyNPC;
import dungeons_and_dragons.strategy.HumanPlayer;
import dungeons_and_dragons.view.GamePlayView;

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

	private Object gamePlayView;

	private GamePlayView gamePlayView2;

	@Expose
	private ArrayList<MapCharacter> turnList;

	@Expose
	private int currentTurn;

	@Expose
	private boolean isGameRunning;

	@Expose
	private String playerStrategy;

	/**
	 * constructor to initialize map object
	 */
	public GamePlayModel() {
		this.currentMapIndex = 0;
		this.turnList = new ArrayList<MapCharacter>();
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
	 * @param turnList the turnList to set
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
	 * @param currentTurn the currentTurn to set
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
	 * @param isGameRunning the isGameRunning to set
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
	 * @param playerStrategy the playerStrategy to set
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
		Thread t = new Thread(this);
		t.start();
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
	 * @return true if boundary else false
	 */
	public boolean checkBoundaries(Point tempPoint) {
		// GamePlayModel gpm=new GamePlayModel();
		// GamePlayController gpc=new GamePlayController(gpm);
		// gamePlayView2=new GamePlayView(gpm, gpc);
		if (tempPoint.x < 0 || tempPoint.y < 0 || tempPoint.x >= this.gamePlayView2.currentMap.getMap_size().x
				|| tempPoint.y >= this.gamePlayView2.currentMap.getMap_size().y) {

			this.gamePlayView2.consoleTextArea.setForeground(Color.RED);
			this.gamePlayView2.consoleTextArea
					.setText(this.gamePlayView2.consoleTextArea.getText() + "Oops...Cannot go ahead...\n");
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
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
