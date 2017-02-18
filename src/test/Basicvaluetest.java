package test;

import org.junit.Assert;
import org.junit.Test;

import dungeons_and_dragons.model.CharacterModel;
/**
 * @author shahida
 *
 */

public class Basicvaluetest {
	@Test
	public void basicvaluecheck(){
		
		//Given
		String character_name="Mick";
		int character_id=1;
		int strength=10;
		
		//When
		CharacterModel character=new CharacterModel(character_id,character_name,strength);
		
		//Then
		Assert.assertTrue(character.getCharacter_name().equals(character_name));
		
	}
}
