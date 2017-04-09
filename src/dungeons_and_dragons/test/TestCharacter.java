package dungeons_and_dragons.test;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import dungeons_and_dragons.helper.Game_constants;
import dungeons_and_dragons.helper.MapItem;
import dungeons_and_dragons.model.AbilityScoresModel;
import dungeons_and_dragons.model.CampaignModel;
import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.model.GameMapModel;
import dungeons_and_dragons.model.GamePlayModel;
import dungeons_and_dragons.model.ItemModel;

/**
 * Character Test
 * 
 * @author : Shahida Chauhan, Hirangi Naik & Mihir Pujara
 */

public class TestCharacter {

	/**
	 * Test character’s abilities according to items worn
	 */
	@Test
	public void testAbilityAccordingToItemWornHelmetI() {
		int item_id = 1;
		String item_name = "abc";
		String itemtype = Game_constants.HELMET;
		String item_ability = Game_constants.INTELLIGENCE;
		int item_point = 1;
		int intelligence = 11;

		CharacterModel itemcharacter = new CharacterModel();

		ItemModel item = new ItemModel(item_id, item_name, item_point, itemtype, item_ability);

		ArrayList<ItemModel> items_cha = new ArrayList<ItemModel>();
		items_cha.add(item);

		itemcharacter.setItems(items_cha);
		itemcharacter.getRawAbilityScores().setIntelligence(10);

		itemcharacter.calculateModifires();

		Assert.assertEquals(itemcharacter.getAbilityScores().getIntelligence(), intelligence);

	}

	@Test
	public void testAbilityAccordingToItemWornHelmetW() {
		int item_id = 1;
		String item_name = "abc";
		String itemtype = Game_constants.HELMET;
		String item_ability = Game_constants.WISDOM;
		int item_point = 1;
		int wisdom = 11;

		CharacterModel itemcharacter = new CharacterModel();

		ItemModel item = new ItemModel(item_id, item_name, item_point, itemtype, item_ability);

		ArrayList<ItemModel> items_cha = new ArrayList<ItemModel>();
		items_cha.add(item);

		itemcharacter.setItems(items_cha);
		itemcharacter.getRawAbilityScores().setWisdom(10);

		itemcharacter.calculateModifires();

		Assert.assertEquals(itemcharacter.getAbilityScores().getWisdom(), wisdom);

	}

	@Test
	public void testAbilityAccordingToItemWornRingS() {
		int item_id = 1;
		String item_name = "abc";
		String itemtype = Game_constants.RING;
		String item_ability = Game_constants.STRENGTH;
		int item_point = 1;
		int strength = 11;

		CharacterModel itemcharacter = new CharacterModel();

		ItemModel item = new ItemModel(item_id, item_name, item_point, itemtype, item_ability);

		ArrayList<ItemModel> items_cha = new ArrayList<ItemModel>();
		items_cha.add(item);

		itemcharacter.setItems(items_cha);
		itemcharacter.getRawAbilityScores().setstrength(10);

		itemcharacter.calculateModifires();

		Assert.assertEquals(itemcharacter.getAbilityScores().getStraight(), strength);

	}

	@Test
	public void testAbilityAccordingToItemWornRingC() {
		int item_id = 1;
		String item_name = "abc";
		String itemtype = Game_constants.RING;
		String item_ability = Game_constants.CONSTITUTION;
		int item_point = 1;
		int constitution = 11;

		CharacterModel itemcharacter = new CharacterModel();

		ItemModel item = new ItemModel(item_id, item_name, item_point, itemtype, item_ability);

		ArrayList<ItemModel> itemscha = new ArrayList<ItemModel>();
		itemscha.add(item);

		itemcharacter.setItems(itemscha);
		itemcharacter.getRawAbilityScores().setConstitution(10);

		itemcharacter.calculateModifires();

		Assert.assertEquals(itemcharacter.getAbilityScores().getConstitution(), constitution);

	}

	@Test
	public void testAbilityAccordingToItemWornRingW() {
		int item_id = 1;
		String item_name = "abc";
		String itemtype = Game_constants.RING;
		String item_ability = Game_constants.WISDOM;
		int item_point = 1;
		int wisdom = 11;

		CharacterModel item_character = new CharacterModel();

		ItemModel item = new ItemModel(item_id, item_name, item_point, itemtype, item_ability);

		ArrayList<ItemModel> items_cha = new ArrayList<ItemModel>();
		items_cha.add(item);

		item_character.setItems(items_cha);
		item_character.getRawAbilityScores().setWisdom(10);

		item_character.calculateModifires();

		Assert.assertEquals(item_character.getAbilityScores().getWisdom(), wisdom);

	}

	@Test
	public void testAbilityAccordingToItemWornRingCh() {
		int item_id = 1;
		String item_name = "abc";
		String itemtype = Game_constants.RING;
		String item_ability = Game_constants.CHARISMA;
		int item_point = 1;
		int charisma = 11;

		CharacterModel item_character = new CharacterModel();

		ItemModel item = new ItemModel(item_id, item_name, item_point, itemtype, item_ability);

		ArrayList<ItemModel> items_cha = new ArrayList<ItemModel>();
		items_cha.add(item);

		item_character.setItems(items_cha);
		item_character.getRawAbilityScores().setCharisma(10);

		item_character.calculateModifires();

		Assert.assertEquals(item_character.getAbilityScores().getCharisma(), charisma);

	}

	@Test
	public void testAbilityAccordingToItemWornBeltS() {
		int item_id = 1;
		String item_name = "abc";
		String itemtype = Game_constants.BELT;
		String item_ability = Game_constants.STRENGTH;
		int item_point = 1;
		int strength = 11;

		CharacterModel item_character = new CharacterModel();

		ItemModel item = new ItemModel(item_id, item_name, item_point, itemtype, item_ability);

		ArrayList<ItemModel> items_cha = new ArrayList<ItemModel>();
		items_cha.add(item);

		item_character.setItems(items_cha);
		item_character.getRawAbilityScores().setstrength(10);

		item_character.calculateModifires();

		Assert.assertEquals(item_character.getAbilityScores().getStraight(), strength);

	}

	@Test
	public void testAbilityAccordingToItemWornBeltC() {
		int item_id = 1;
		String item_name = "abc";
		String itemtype = Game_constants.BELT;
		String item_ability = Game_constants.CONSTITUTION;
		int item_point = 1;
		int constitution = 11;

		CharacterModel item_character = new CharacterModel();

		ItemModel item = new ItemModel(item_id, item_name, item_point, itemtype, item_ability);

		ArrayList<ItemModel> items_cha = new ArrayList<ItemModel>();
		items_cha.add(item);

		item_character.setItems(items_cha);
		item_character.getRawAbilityScores().setConstitution(10);

		item_character.calculateModifires();

		Assert.assertEquals(item_character.getAbilityScores().getConstitution(), constitution);

	}

	@Test
	public void testAbilityAccordingToItemWornBoot() {
		int item_id = 1;
		String item_name = "abc";
		String itemtype = Game_constants.BOOTS;
		String item_ability = Game_constants.DEXTERITY;
		int item_point = 1;
		int dexterity = 11;

		CharacterModel item_character = new CharacterModel();

		ItemModel item = new ItemModel(item_id, item_name, item_point, itemtype, item_ability);

		ArrayList<ItemModel> items_cha = new ArrayList<ItemModel>();
		items_cha.add(item);

		item_character.setItems(items_cha);
		item_character.getRawAbilityScores().setDexterity(10);

		item_character.calculateModifires();

		Assert.assertEquals(item_character.getAbilityScores().getDexterity(), dexterity);

	}

	@Test
	public void testcalculateDamageBonus(){
		CharacterModel characterModel=new CharacterModel();
		int item_id = 1;
		String item_name = "abc";
		String itemtype = Game_constants.BOOTS;
		String item_ability = Game_constants.DEXTERITY;
		int item_point = 1;
		ItemModel item = new ItemModel(item_id, item_name, item_point, itemtype, item_ability);
		item.setItem_type(Game_constants.WEAPON_MELEE);
		ArrayList<ItemModel> items=new ArrayList<ItemModel>();
		items.add(item);
		characterModel.setItems(items);
		characterModel.setStrength(5);
		AbilityScoresModel modifiers=new AbilityScoresModel();
		characterModel.setModifiers(modifiers);
		modifiers.setstrength(5);
		characterModel.calculateDamageBonus();
		Assert.assertEquals(true,characterModel.isMeleeDamage);
		characterModel.setDamageBonus(0);
	}
	

	
}
