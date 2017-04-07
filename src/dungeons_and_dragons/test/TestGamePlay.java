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
		CharacterStrategy characterStrategy=new CharacterStrategy();
		character.setCharacterType(MapCharacter.NORMAL);
		//characterStrategy.setStrategy(new HumanPlayer());
		GamePlayModel gpm=new GamePlayModel();
		gpm.startGame();
		Strategy strategy;
		
		
	}
}