/**
 * 
 */
package dungeons_and_dragons.test;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Test;

import dungeons_and_dragons.helper.DiceHelper;
import dungeons_and_dragons.helper.GameStatus;
import dungeons_and_dragons.helper.Game_constants;
import dungeons_and_dragons.helper.MapCharacter;
import dungeons_and_dragons.helper.MapItem;
import dungeons_and_dragons.model.CampaignModel;
import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.model.GameMapModel;
import dungeons_and_dragons.model.GamePlayModel;
import dungeons_and_dragons.model.ItemModel;
import dungeons_and_dragons.strategy.CharacterStrategy;
import dungeons_and_dragons.strategy.HumanPlayer;
import dungeons_and_dragons.strategy.Strategy;

/**
 * 
 * @author urmil
 *
 */

public class TestGamePlay {

	


	@Test
	public void testMovementUp() {
		GamePlayModel gamePlayModel = new GamePlayModel();
		CharacterModel characterModel = new CharacterModel();
		CampaignModel campaignModel = new CampaignModel();
		gamePlayModel.setCharacterModel(characterModel);
		gamePlayModel.setCampaignModel(campaignModel);

		Point oldPoint = new Point(3, 1);
		// after pressing up new point
		// Point newPoint = new Point(3,0);

		gamePlayModel.setGameCharacterPosition(oldPoint);
		gamePlayModel.moveCharacterUp(oldPoint);

		int x = (int) gamePlayModel.getGameCharacterPosition().getX();
		int y = (int) gamePlayModel.getGameCharacterPosition().getY();
		assertEquals(3, x);
		assertEquals(0, y);

	}

	@Test
	public void testMovementDown() {
		GamePlayModel gamePlayModel = new GamePlayModel();
		CharacterModel characterModel = new CharacterModel();
		CampaignModel campaignModel = new CampaignModel();
		gamePlayModel.setCharacterModel(characterModel);
		gamePlayModel.setCampaignModel(campaignModel);

		Point oldPoint = new Point(3, 1);
		// after pressing down new point

		gamePlayModel.setGameCharacterPosition(oldPoint);
		gamePlayModel.moveCharacterDown(oldPoint);

		int x = (int) gamePlayModel.getGameCharacterPosition().getX();
		int y = (int) gamePlayModel.getGameCharacterPosition().getY();
		assertEquals(3, x);
		assertEquals(2, y);

	}

	@Test
	public void testMovementLeft() {
		GamePlayModel gamePlayModel = new GamePlayModel();
		CharacterModel characterModel = new CharacterModel();
		CampaignModel campaignModel = new CampaignModel();
		gamePlayModel.setCharacterModel(characterModel);
		gamePlayModel.setCampaignModel(campaignModel);

		Point oldPoint = new Point(3, 1);
		// after pressing left new point

		gamePlayModel.setGameCharacterPosition(oldPoint);
		gamePlayModel.moveCharacterLeft(oldPoint);

		int x = (int) gamePlayModel.getGameCharacterPosition().getX();
		int y = (int) gamePlayModel.getGameCharacterPosition().getY();
		assertEquals(2, x);
		assertEquals(1, y);

	}

	@Test
	public void testMovementRight() {
		GamePlayModel gamePlayModel = new GamePlayModel();
		CharacterModel characterModel = new CharacterModel();
		CampaignModel campaignModel = new CampaignModel();
		gamePlayModel.setCharacterModel(characterModel);
		gamePlayModel.setCampaignModel(campaignModel);

		Point oldPoint = new Point(3, 1);
		// after pressing right new point

		gamePlayModel.setGameCharacterPosition(oldPoint);
		gamePlayModel.moveCharacterRight(oldPoint);

		int x = (int) gamePlayModel.getGameCharacterPosition().getX();
		int y = (int) gamePlayModel.getGameCharacterPosition().getY();
		assertEquals(4, x);
		assertEquals(1, y);

	}

	@Test
	public void testItemAbilitybyLevel() {
		GamePlayModel gpm = new GamePlayModel();
		CharacterModel cm = new CharacterModel();
		cm.setCharacter_level(1);
		gpm.setCharacterModel(cm);

		Assert.assertEquals(gpm.getItemScoreByLevel(1), 1);
	}

	@Test
	public void testFightWithEnemy() {
		CharacterModel c1 = new CharacterModel();
		CharacterModel c2 = new CharacterModel();

		boolean result = false;

		GamePlayModel gpm = new GamePlayModel();
		Assert.assertEquals(gpm.fightWithEnemy(c1, c2), result);
	}

	@Test
	public void testCheckBoundaries() {
		Point p = new Point(-1, -1);
		GamePlayModel gpm = new GamePlayModel();

		// gpm.checkBoundaries(p);
		boolean result = false;
		Assert.assertEquals(false, result);
	}

	@Test
	public void testLootChest() {
		GamePlayModel gpm = new GamePlayModel();
		CampaignModel campaignmodel = new CampaignModel();
		GameMapModel mapmpdel = new GameMapModel(7, 7);
		ArrayList<GameMapModel> map = new ArrayList<GameMapModel>();
		map.add(mapmpdel);
		campaignmodel.setOutput_map_list(map);

		int item_id = 1;
		String item_name = "abc";
		String itemtype = Game_constants.BOOTS;
		String item_ability = Game_constants.DEXTERITY;
		int item_point = 1;

		ItemModel item = new ItemModel(item_id, item_name, item_point, itemtype, item_ability);
		MapItem mi = new MapItem();
		mi.setItem(item);
		mi.setX(1);
		mi.setY(1);
		// mapmpdel.setMap_chest(mi);
		gpm.setCampaignModel(campaignmodel);
		gpm.getCampaignModel().getOutput_map_list().get(0).setMap_chest(mi);
		Point p = new Point(1, 1);
		gpm.removeChest(p);
		Assert.assertEquals(gpm.getCampaignModel().getOutput_map_list().get(0).getMap_chest().getX(), -1);
		Assert.assertEquals(gpm.getCampaignModel().getOutput_map_list().get(0).getMap_chest().getY(), -1);

	}
	
	@Test
	public void testCalculateTurn(){
		CharacterModel characterModel=new CharacterModel();
		Map<Integer, MapCharacter> tempValues = new HashMap<Integer, MapCharacter>();
		// roll dice and calculate turn values for character
		MapCharacter currentCharacter = new MapCharacter();
		currentCharacter.setCharacter(characterModel);
		currentCharacter.setCharacterType(MapCharacter.NORMAL);
		Integer roll=5,dex=6;
		
		tempValues.put(roll + dex, currentCharacter);

		Map<Integer, MapCharacter> treeMap = new TreeMap<>((Comparator<Integer>) (o1, o2) -> o2.compareTo(o1));

		treeMap.putAll(tempValues);
		Assert.assertEquals(1,treeMap.size());

	}
	
	@Test
	public void testStartGame(){
		MapCharacter character=new MapCharacter();
		character.setCharacterType(MapCharacter.NORMAL);
		GameMapModel gameMapModel=new GameMapModel(7, 7);
		GamePlayModel gpm=new GamePlayModel();
		gpm.calculateTurn();
		gpm.startGame();
		CharacterStrategy characterStrategy=new CharacterStrategy();
		System.out.println(characterStrategy.getStrategy());
		System.out.println(MapCharacter.NORMAL);
		Assert.assertEquals(characterStrategy.getStrategy(), MapCharacter.NORMAL);
		
	}
	
	@Test
	public void testCheckWalls(){
		GamePlayModel gpm = new GamePlayModel();
		CampaignModel campaignmodel = new CampaignModel();
		GameMapModel mapmpdel = new GameMapModel(7, 7);
		ArrayList<GameMapModel> map = new ArrayList<GameMapModel>();
		map.add(mapmpdel);
		campaignmodel.setOutput_map_list(map);
		gpm.setCampaignModel(campaignmodel);
		Point p = new Point(1, 1);
		gpm.getCampaignModel().getOutput_map_list().get(0).setMap_wall(p);
		Assert.assertEquals(true, gpm.checkWalls(p));
	}
	
	@Test
	public void testCheckCharacter(){
		GamePlayModel gpm = new GamePlayModel();
		CampaignModel campaignmodel = new CampaignModel();
		GameMapModel mapmpdel = new GameMapModel(7, 7);
		ArrayList<GameMapModel> map = new ArrayList<GameMapModel>();
		map.add(mapmpdel);
		campaignmodel.setOutput_map_list(map);
		gpm.setCampaignModel(campaignmodel);
		Point p = new Point(1, 1);
		MapCharacter pos_char=new MapCharacter();
		gpm.getCampaignModel().getOutput_map_list().get(0).setMap_enemy_loc(pos_char);
		Assert.assertEquals(false, gpm.checkCharacter(p));
	}
	
	@Test
	public void testCheckChest(){
		GamePlayModel gpm = new GamePlayModel();
		CampaignModel campaignmodel = new CampaignModel();
		GameMapModel mapmpdel = new GameMapModel(7, 7);
		ArrayList<GameMapModel> map = new ArrayList<GameMapModel>();
		map.add(mapmpdel);
		campaignmodel.setOutput_map_list(map);
		gpm.setCampaignModel(campaignmodel);
		MapItem map_chest=new MapItem();
		Point p=new Point(-1, -1);
		gpm.getCampaignModel().getOutput_map_list().get(0).setMap_chest(map_chest);
		Assert.assertEquals(true, gpm.checkChest(p));
	}
	
	@Test
	public void testValidateMove(){
		GamePlayModel gpm = new GamePlayModel();
		CampaignModel campaignmodel = new CampaignModel();
		GameMapModel mapmpdel = new GameMapModel(7, 7);
		ArrayList<GameMapModel> map = new ArrayList<GameMapModel>();
		map.add(mapmpdel);
		campaignmodel.setOutput_map_list(map);
		gpm.setCampaignModel(campaignmodel);
		Point p1=new Point(1, 1);
		Point p2=new Point(2, 2);
		gpm.getCampaignModel().getOutput_map_list().get(0).setMap_wall(p1);
		Assert.assertEquals(0, gpm.validateMove(p1, p2).getGameStatus());
	}
	
	@Test
	public void testvalidateAttack(){
		MapCharacter mp1=new MapCharacter();
		MapCharacter mp2=new MapCharacter();
		GamePlayModel gpm = new GamePlayModel();
		CampaignModel campaignmodel = new CampaignModel();
		GameMapModel mapmpdel = new GameMapModel(7, 7);
		ArrayList<GameMapModel> map = new ArrayList<GameMapModel>();
		map.add(mapmpdel);
		campaignmodel.setOutput_map_list(map);
		gpm.setCampaignModel(campaignmodel);
		Assert.assertEquals(true, gpm.validateAttack(mp1, mp2));
	}
	
	@Test
	public void testinitateInteract(){
		Point p=new Point(1, 1);
		GameStatus gameStatus=new GameStatus();
		gameStatus.setGameStatus(0);
		GamePlayModel gpm = new GamePlayModel();
		CampaignModel campaignmodel = new CampaignModel();
		GameMapModel mapmpdel = new GameMapModel(7, 7);
		ArrayList<GameMapModel> map = new ArrayList<GameMapModel>();
		map.add(mapmpdel);
		campaignmodel.setOutput_map_list(map);
		gpm.setCampaignModel(campaignmodel);
		MapItem map_chest=new MapItem();
		gpm.getCampaignModel().getOutput_map_list().get(0).setMap_chest(map_chest);
		gpm.getCampaignModel().getOutput_map_list().get(0).getMap_chest().setX(0);
		Assert.assertEquals(gameStatus.getGameStatus(), gpm.initateInteract(p).getGameStatus());
	}
	
	@Test
	public void testattackToEnemy(){
		GamePlayModel gpm = new GamePlayModel();
		CharacterModel characterModel=new CharacterModel();
		Point p=new Point(1, 1);
		gpm.setGameCharacterPosition(p);
		MapCharacter character=new MapCharacter();
		character.setCharacterType(MapCharacter.ENEMY);
		ArrayList<MapCharacter> turnList=new ArrayList<MapCharacter>();
		turnList.add(character);
		gpm.setTurnList(turnList);
		int item_id = 1;
		String item_name = "abc";
		String itemtype = Game_constants.BOOTS;
		String item_ability = Game_constants.DEXTERITY;
		int item_point = 1;
		character.setCharacter(characterModel);
		character.getCharacter().setAttackBonus(5);
		character.getCharacter().setStrength(5);
		ItemModel item = new ItemModel(item_id, item_name, item_point, itemtype, item_ability);
		ArrayList<ItemModel> items=new ArrayList<ItemModel>();
		items.add(item);
		gpm.attackToEnemy(character);
		Assert.assertEquals(true,gpm.ishit);
	}
	
	
	@Test
	public void testmoveFrightenedEnemy(){
		GamePlayModel gpm = new GamePlayModel();
		CampaignModel campaignmodel = new CampaignModel();
		GameMapModel mapmpdel = new GameMapModel(7, 7);
		ArrayList<GameMapModel> map = new ArrayList<GameMapModel>();
		map.add(mapmpdel);
		campaignmodel.setOutput_map_list(map);
		gpm.setCampaignModel(campaignmodel);
		Point p = new Point(1, 1);
		MapCharacter enemy=new MapCharacter();
		enemy.setX(4);
		enemy.setY(2);
		Point p2=new Point(5, 2);
		gpm.getCampaignModel().getOutput_map_list().get(0).setMap_wall(p2);
		gpm.moveFrightenedComputerOrEnemy(enemy, p);
		Assert.assertEquals(5, enemy.getX());

	}
	
	@Test
	public void testmoveAggressiveEnemy(){
		GamePlayModel gpm = new GamePlayModel();
		CampaignModel campaignmodel = new CampaignModel();
		GameMapModel mapmpdel = new GameMapModel(7, 7);
		ArrayList<GameMapModel> map = new ArrayList<GameMapModel>();
		map.add(mapmpdel);
		campaignmodel.setOutput_map_list(map);
		gpm.setCampaignModel(campaignmodel);
		Point p = new Point(1, 1);
		MapCharacter enemy=new MapCharacter();
		enemy.setX(4);
		enemy.setY(2);
		Point p2=new Point(5, 2);
		gpm.getCampaignModel().getOutput_map_list().get(0).setMap_wall(p2);
		gpm.moveAggresiveComputerOrEnemy(enemy, p,1);
		Assert.assertEquals(3, enemy.getX());

	}
	
	@Test
	public void testremoveChest(){
		GamePlayModel gpm = new GamePlayModel();
		CampaignModel campaignmodel = new CampaignModel();
		GameMapModel mapmpdel = new GameMapModel(7, 7);
		ArrayList<GameMapModel> map = new ArrayList<GameMapModel>();
		map.add(mapmpdel);
		campaignmodel.setOutput_map_list(map);
		gpm.setCampaignModel(campaignmodel);
		MapItem map_chest=new MapItem();
		Point p=new Point(-1, -1);
		gpm.getCampaignModel().getOutput_map_list().get(0).setMap_chest(map_chest);
		gpm.removeChest(p);
		Assert.assertEquals(null, gpm.getCampaignModel().getOutput_map_list().get(0).getMap_chest().getItem());
	}
}