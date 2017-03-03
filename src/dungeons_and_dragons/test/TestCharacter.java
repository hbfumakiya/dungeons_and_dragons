package dungeons_and_dragons.test;
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


public class TestCharacter {
	
	
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
		int item_point=1;
		int intelligence=11;
		
		CharacterModel itemcharacter=new CharacterModel();
		
		ItemModel item=new ItemModel(item_id,item_name,item_point,itemtype,item_ability); 
		
		ArrayList<ItemModel> items_cha = new ArrayList<ItemModel>();
		items_cha.add(item);
		
		itemcharacter.setItems(items_cha);
		itemcharacter.getRawAbilityScores().setIntelligence(10);
		
		itemcharacter.calculateModifires();

		Assert.assertEquals(itemcharacter.getAbilityScores().getIntelligence(), intelligence);
		
	}
	
	@Test
	public void testAbilityAccordingToItemWornHelmetW()
	{
		int item_id=1;
		String item_name="abc";
		String itemtype = Game_constants.HELMET;
		String item_ability = Game_constants.WISDOM;
		int item_point=1;
		int wisdom=11;
	
		CharacterModel itemcharacter=new CharacterModel();

		ItemModel item=new ItemModel(item_id,item_name,item_point,itemtype,item_ability); 

		ArrayList<ItemModel> items_cha = new ArrayList<ItemModel>();
		items_cha.add(item);
	

		itemcharacter.setItems(items_cha);
		itemcharacter.getRawAbilityScores().setWisdom(10);
		
		itemcharacter.calculateModifires();
			
		Assert.assertEquals(itemcharacter.getAbilityScores().getWisdom(),wisdom);
		
	}
	
	@Test
	public void testAbilityAccordingToItemWornRingS()
	{
		int item_id=1;
		String item_name="abc";
		String itemtype = Game_constants.RING;
		String item_ability = Game_constants.STRENGTH;
		int item_point=1;
		int strength=11;
		
		CharacterModel itemcharacter=new CharacterModel();
		
		ItemModel item=new ItemModel(item_id,item_name,item_point,itemtype,item_ability); 
		
		ArrayList<ItemModel> items_cha = new ArrayList<ItemModel>();
		items_cha.add(item);
		
		itemcharacter.setItems(items_cha);
		itemcharacter.getRawAbilityScores().setstrength(10);
		
		itemcharacter.calculateModifires();
			
		Assert.assertEquals(itemcharacter.getAbilityScores().getStraight(),strength);
		
	}
	
	@Test
	public void testAbilityAccordingToItemWornRingC()
	{
		int item_id=1;
		String item_name="abc";
		String itemtype = Game_constants.RING;
		String item_ability = Game_constants.CONSTITUTION;
		int item_point=1;
		int constitution=11;
		
		CharacterModel itemcharacter=new CharacterModel();
		
		ItemModel item=new ItemModel(item_id,item_name,item_point,itemtype,item_ability); 
		
		ArrayList<ItemModel> itemscha = new ArrayList<ItemModel>();
		itemscha.add(item);
		

		itemcharacter.setItems(itemscha);
		itemcharacter.getRawAbilityScores().setConstitution(10);
		
		itemcharacter.calculateModifires();
			
		Assert.assertEquals(itemcharacter.getAbilityScores().getConstitution(),constitution);
		
	}
	
	@Test
	public void testAbilityAccordingToItemWornRingW()
	{
		int item_id=1;
		String item_name="abc";
		String itemtype = Game_constants.RING;
		String item_ability = Game_constants.WISDOM;
		int item_point=1;
		int wisdom=11;
		
		CharacterModel item_character=new CharacterModel();
		
		ItemModel item=new ItemModel(item_id,item_name,item_point,itemtype,item_ability); 
		
		ArrayList<ItemModel> items_cha = new ArrayList<ItemModel>();
		items_cha.add(item);

		item_character.setItems(items_cha);
		item_character.getRawAbilityScores().setWisdom(10);
		
		item_character.calculateModifires();
			
		Assert.assertEquals(item_character.getAbilityScores().getWisdom(),wisdom);
		
	}
	
	@Test
	public void testAbilityAccordingToItemWornRingCh()
	{
		int item_id=1;
		String item_name="abc";
		String itemtype = Game_constants.RING;
		String item_ability = Game_constants.CHARISMA;
		int item_point=1;
		int charisma=11;
		
		CharacterModel item_character=new CharacterModel();
		
		ItemModel item=new ItemModel(item_id,item_name,item_point,itemtype,item_ability); 
		
		ArrayList<ItemModel> items_cha = new ArrayList<ItemModel>();
		items_cha.add(item);
		
		item_character.setItems(items_cha);
		item_character.getRawAbilityScores().setCharisma(10);
		

		item_character.calculateModifires();
		
			
		Assert.assertEquals(item_character.getAbilityScores().getCharisma(),charisma);
		
	}
	
	@Test
	public void testAbilityAccordingToItemWornBeltS()
	{
		int item_id=1;
		String item_name="abc";
		String itemtype = Game_constants.BELT;
		String item_ability = Game_constants.STRENGTH;
		int item_point=1;
		int strength=11;
		
		CharacterModel item_character=new CharacterModel();
		
		ItemModel item=new ItemModel(item_id,item_name,item_point,itemtype,item_ability); 
		
		ArrayList<ItemModel> items_cha = new ArrayList<ItemModel>();
		items_cha.add(item);
		


		item_character.setItems(items_cha);
		item_character.getRawAbilityScores().setstrength(10);
		

		item_character.calculateModifires();
		
		
				
		Assert.assertEquals(item_character.getAbilityScores().getStraight(),strength);
		
	}
	
	@Test
	public void testAbilityAccordingToItemWornBeltC()
	{
		int item_id=1;
		String item_name="abc";
		String itemtype = Game_constants.BELT;
		String item_ability = Game_constants.CONSTITUTION;
		int item_point=1;
		int constitution=11;
		
		CharacterModel item_character=new CharacterModel();
		
		ItemModel item=new ItemModel(item_id,item_name,item_point,itemtype,item_ability); 
		
		ArrayList<ItemModel> items_cha = new ArrayList<ItemModel>();
		items_cha.add(item);
		


		item_character.setItems(items_cha);
		item_character.getRawAbilityScores().setConstitution(10);
		

		item_character.calculateModifires();
		
		Assert.assertEquals(item_character.getAbilityScores().getConstitution(),constitution);
		
	}
	
	@Test
	public void testAbilityAccordingToItemWornBoot()
	{
		int item_id=1;
		String item_name="abc";
		String itemtype = Game_constants.BOOTS;
		String item_ability = Game_constants.DEXTERITY;
		int item_point=1;
		int dexterity=11;
		
		CharacterModel item_character=new CharacterModel();
		
		ItemModel item=new ItemModel(item_id,item_name,item_point,itemtype,item_ability); 
		
		ArrayList<ItemModel> items_cha = new ArrayList<ItemModel>();
		items_cha.add(item);



		item_character.setItems(items_cha);
		item_character.getRawAbilityScores().setDexterity(10);
		

		item_character.calculateModifires();
		
		Assert.assertEquals(item_character.getAbilityScores().getDexterity(),dexterity);
		
	}
	
	

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
		
		
	}



