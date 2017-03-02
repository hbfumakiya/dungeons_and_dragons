package test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import dungeons_and_dragons.helper.Game_constants;
import dungeons_and_dragons.model.AbilityScoresModel;
import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.model.ItemModel;

/**
 * Character Test
 * 
 * @author : Shahida Chauhan
 */

public class charactertest {
	/**
	 * ABILITY SCORE AND MODIFIER
	 * 
	 * 4D6 rolled 6times and set modifier according to score
	 * 
	 * [4D6 + modifier]*Level
	 * 
	 * Strength (Str) Dexterity (Dex) Constitution (Con) Intelligence (Int)
	 * Wisdom (Wis) Charisma (Cha)
	 */
	@Test

	public void testAbilityAndModifier() {
		// Given
		// When
		// Then
		int score,modifier=0;
		CharacterModel characterability=new CharacterModel();
		
		
		characterability.calculateAbilityScores();
		score=characterability.getAbilityScores().getStraight();
//		System.out.println(characterability.getAbilityScores().getStraight());

		if (score == 1) {
			modifier = -5;
		} else if (score == 2 || score == 3) {
			modifier = -4;
		} else if (score == 4 || score == 5) {
			modifier = -3;
		} else if (score == 6 || score == 7) {
			modifier = -2;
		} else if (score == 8 || score == 9) {
			modifier = -1;
		} else if (score == 10 || score == 11) {
			modifier = 0;
		} else if (score == 12 || score == 13) {
			modifier = 1;
		} else if (score == 14 || score == 15) {
			modifier = 2;
		} else if (score == 16 || score == 17) {
			modifier = 3;
		} else if (score == 18 || score == 19) {
			modifier = 4;
		} else if (score == 20 || score == 21) {
			modifier = 5;
		} else if (score == 22 || score == 23) {
			modifier = 6;
		} else if (score == 24 || score == 25) {
			modifier = 7;
		} else if (score == 26 || score == 27) {
			modifier = 8;
		} else if (score == 28 || score == 29) {
			modifier = 9;
		} else if (score == 30 || score == 31) {
			modifier = 10;
		} else if (score == 32 || score == 33) {
			modifier = 11;
		} else if (score == 34 || score == 35) {
			modifier = 12;
		} else if (score == 36 || score == 37) {
			modifier = 13;
		} else if (score == 38 || score == 39) {
			modifier = 14;
		} else if (score == 40 || score == 41) {
			modifier = 15;
		} else if (score == 42 || score == 43) {
			modifier = 16;
		} else if (score == 44 || score == 45) {
			modifier = 17;
		} else if (score == 46 || score == 47) {
			modifier = 18;
		}
		characterability.calculateModifires();
		

	//	System.out.println(characterability.getAbilityScores().getStraight());
		

	}

	/**
	 * HIT POINTS [10 + his Constitution modifier]*Level
	 */
	@Test
	public void testHitPoints() {
		// Given
		// When
		// Then

	}

	/**
	 * ARMOR CLASS(DEX+WORN ARMOR)
	 * 
	 * [D10+ARMOR BONUS(RANGES FROM +1 TO +5)+DEXTIRITY]*Level
	 */
	@Test
	public void testArmorClass() {
		// Given
		// When
		// Then

	}

	/**
	 * ATTACK BONUS(level+str/dex)
	 * 
	 * [+1/Level + str/dex]
	 * 
	 */
	@Test
	public void testAttackBonus() {
		// Given
		// When
		// Then

	}

	/**
	 * DAMAGE BONUS(STR ONLY FOR MELEE WEAPON) [str]*Level
	 */
	@Test
	public void testDamageBonus() {
		// Given
		// When
		// Then

	}
	
	/**
	 * Test character’s abilities according to items worn 
	 */
	@Test
	public void testAbilityAccordingToItemWornHelmetI()
	{
		int item_id=1;
		String item_name="abc";
		String itemtype = Game_constants.HELMET;
		String item_ability = Game_constants.INTELLIGENCE;
		int item_point=5;
		int intelligence=17;
		
		CharacterModel itemcharacter=new CharacterModel();
		
		ItemModel item=new ItemModel(item_id,item_name,item_point,itemtype,item_ability); 
		
		ArrayList<ItemModel> itemscha = new ArrayList<ItemModel>();
		itemscha.add(item);
		

		AbilityScoresModel abilityint=new AbilityScoresModel();

		itemcharacter.setItems(itemscha);
		itemcharacter.getAbilityScores().setIntelligence(10);
		
		System.out.println(itemcharacter.getAbilityScores().getIntelligence());

		itemcharacter.calculateModifires();
		System.out.println(itemcharacter.getAbilityScores().getIntelligence());
		
		System.out.println(abilityint.getStraight());
		
				
		Assert.assertEquals(abilityint.getIntelligence(),intelligence);
		
	}
	
	@Test
	public void testAbilityAccordingToItemWornHelmetW()
	{
		int item_id=1;
		String item_name="abc";
		String itemtype = Game_constants.HELMET;
		String item_ability = Game_constants.WISDOM;
		int item_point=5;
		int wisdom=17;
	
		CharacterModel itemcharacter=new CharacterModel();

		ItemModel item=new ItemModel(item_id,item_name,item_point,itemtype,item_ability); 

		ArrayList<ItemModel> itemscha = new ArrayList<ItemModel>();
		itemscha.add(item);
	

		AbilityScoresModel abilityint=new AbilityScoresModel();
		itemcharacter.setItems(itemscha);
		itemcharacter.getAbilityScores().setWisdom(10);
		
		System.out.println(itemcharacter.getAbilityScores().getIntelligence());

		itemcharacter.calculateModifires();
		System.out.println(itemcharacter.getAbilityScores().getIntelligence());
		
		System.out.println(abilityint.getStraight());
		
				
		Assert.assertEquals(abilityint.getWisdom(),wisdom);
		
	}
	
	@Test
	public void testAbilityAccordingToItemWornRingS()
	{
		int item_id=1;
		String item_name="abc";
		String itemtype = Game_constants.RING;
		String item_ability = Game_constants.STRENGTH;
		int item_point=5;
		int strength=17;
		
		CharacterModel itemcharacter=new CharacterModel();
		
		ItemModel item=new ItemModel(item_id,item_name,item_point,itemtype,item_ability); 
		
		ArrayList<ItemModel> itemscha = new ArrayList<ItemModel>();
		itemscha.add(item);
		

		AbilityScoresModel abilityint=new AbilityScoresModel();

		itemcharacter.setItems(itemscha);
		itemcharacter.getAbilityScores().setstrength(10);
		
	//	System.out.println(itemcharacter.getAbilityScores().getIntelligence());

		itemcharacter.calculateModifires();
	//	System.out.println(itemcharacter.getAbilityScores().getIntelligence());
		
	//	System.out.println(abilityint.getStraight());
		
				
		Assert.assertEquals(abilityint.getStraight(),strength);
		
	}
	
	@Test
	public void testAbilityAccordingToItemWornRingC()
	{
		int item_id=1;
		String item_name="abc";
		String itemtype = Game_constants.RING;
		String item_ability = Game_constants.CONSTITUTION;
		int item_point=5;
		int constitution=17;
		
		CharacterModel itemcharacter=new CharacterModel();
		
		ItemModel item=new ItemModel(item_id,item_name,item_point,itemtype,item_ability); 
		
		ArrayList<ItemModel> itemscha = new ArrayList<ItemModel>();
		itemscha.add(item);
		

		AbilityScoresModel abilityint=new AbilityScoresModel();

		itemcharacter.setItems(itemscha);
		itemcharacter.getAbilityScores().setConstitution(10);
		
	//	System.out.println(itemcharacter.getAbilityScores().getIntelligence());

		itemcharacter.calculateModifires();
	//	System.out.println(itemcharacter.getAbilityScores().getIntelligence());
		
	//	System.out.println(abilityint.getStraight());
		
				
		Assert.assertEquals(abilityint.getConstitution(),constitution);
		
	}
	
	@Test
	public void testAbilityAccordingToItemWornRingW()
	{
		int item_id=1;
		String item_name="abc";
		String itemtype = Game_constants.RING;
		String item_ability = Game_constants.WISDOM;
		int item_point=5;
		int wisdom=17;
		
		CharacterModel itemcharacter=new CharacterModel();
		
		ItemModel item=new ItemModel(item_id,item_name,item_point,itemtype,item_ability); 
		
		ArrayList<ItemModel> itemscha = new ArrayList<ItemModel>();
		itemscha.add(item);
		

		AbilityScoresModel abilityint=new AbilityScoresModel();

		itemcharacter.setItems(itemscha);
		itemcharacter.getAbilityScores().setWisdom(10);
		
	//	System.out.println(itemcharacter.getAbilityScores().getIntelligence());

		itemcharacter.calculateModifires();
	//	System.out.println(itemcharacter.getAbilityScores().getIntelligence());
		
	//	System.out.println(abilityint.getStraight());
		
				
		Assert.assertEquals(abilityint.getWisdom(),wisdom);
		
	}
	
	@Test
	public void testAbilityAccordingToItemWornRingCh()
	{
		int item_id=1;
		String item_name="abc";
		String itemtype = Game_constants.RING;
		String item_ability = Game_constants.CHARISMA;
		int item_point=5;
		int charisma=17;
		
		CharacterModel itemcharacter=new CharacterModel();
		
		ItemModel item=new ItemModel(item_id,item_name,item_point,itemtype,item_ability); 
		
		ArrayList<ItemModel> itemscha = new ArrayList<ItemModel>();
		itemscha.add(item);
		

		AbilityScoresModel abilityint=new AbilityScoresModel();

		itemcharacter.setItems(itemscha);
		itemcharacter.getAbilityScores().setCharisma(10);
		
	//	System.out.println(itemcharacter.getAbilityScores().getIntelligence());

		itemcharacter.calculateModifires();
	//	System.out.println(itemcharacter.getAbilityScores().getIntelligence());
		
	//	System.out.println(abilityint.getStraight());
		
				
		Assert.assertEquals(abilityint.getCharisma(),charisma);
		
	}
	
	@Test
	public void testAbilityAccordingToItemWornBeltS()
	{
		int item_id=1;
		String item_name="abc";
		String itemtype = Game_constants.BELT;
		String item_ability = Game_constants.STRENGTH;
		int item_point=5;
		int strength=17;
		
		CharacterModel itemcharacter=new CharacterModel();
		
		ItemModel item=new ItemModel(item_id,item_name,item_point,itemtype,item_ability); 
		
		ArrayList<ItemModel> itemscha = new ArrayList<ItemModel>();
		itemscha.add(item);
		

		AbilityScoresModel abilityint=new AbilityScoresModel();

		itemcharacter.setItems(itemscha);
		itemcharacter.getAbilityScores().setstrength(10);
		
	//	System.out.println(itemcharacter.getAbilityScores().getIntelligence());

		itemcharacter.calculateModifires();
	//	System.out.println(itemcharacter.getAbilityScores().getIntelligence());
		
	//	System.out.println(abilityint.getStraight());
		
				
		Assert.assertEquals(abilityint.getStraight(),strength);
		
	}
	
	@Test
	public void testAbilityAccordingToItemWornBeltC()
	{
		int item_id=1;
		String item_name="abc";
		String itemtype = Game_constants.BELT;
		String item_ability = Game_constants.CONSTITUTION;
		int item_point=5;
		int constitution=17;
		
		CharacterModel itemcharacter=new CharacterModel();
		
		ItemModel item=new ItemModel(item_id,item_name,item_point,itemtype,item_ability); 
		
		ArrayList<ItemModel> itemscha = new ArrayList<ItemModel>();
		itemscha.add(item);
		

		AbilityScoresModel abilityint=new AbilityScoresModel();

		itemcharacter.setItems(itemscha);
		itemcharacter.getAbilityScores().setConstitution(10);
		
	//	System.out.println(itemcharacter.getAbilityScores().getIntelligence());

		itemcharacter.calculateModifires();
	//	System.out.println(itemcharacter.getAbilityScores().getIntelligence());
		
	//	System.out.println(abilityint.getStraight());
		
				
		Assert.assertEquals(abilityint.getConstitution(),constitution);
		
	}
	
	@Test
	public void testAbilityAccordingToItemWornBoot()
	{
		int item_id=1;
		String item_name="abc";
		String itemtype = Game_constants.BOOTS;
		String item_ability = Game_constants.DEXTERITY;
		int item_point=5;
		int dexterity=17;
		
		CharacterModel itemcharacter=new CharacterModel();
		
		ItemModel item=new ItemModel(item_id,item_name,item_point,itemtype,item_ability); 
		
		ArrayList<ItemModel> itemscha = new ArrayList<ItemModel>();
		itemscha.add(item);
		

		AbilityScoresModel abilityint=new AbilityScoresModel();

		itemcharacter.setItems(itemscha);
		itemcharacter.getAbilityScores().setDexterity(10);
		
	//	System.out.println(itemcharacter.getAbilityScores().getIntelligence());

		itemcharacter.calculateModifires();
	//	System.out.println(itemcharacter.getAbilityScores().getIntelligence());
		
	//	System.out.println(abilityint.getStraight());
		
				
		Assert.assertEquals(abilityint.getDexterity(),dexterity);
		
	}
}
