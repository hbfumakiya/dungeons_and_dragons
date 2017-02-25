package test;

import static org.junit.Assert.*;

import org.junit.Test;

import dungeons_and_dragons.model.CharacterModel;

/**
 * Check basic values of  character
 * 
 * @author : Shahida Chauhan
 * 
 * 
 */

public class basictest {
	@Test
	public void basicValueTest()
	{	
		//Given
		int character_id =1;
		String character_name="Bean";
		int strength=10;
		
		//When
		CharacterModel newcharacter=new CharacterModel();
		
		//Then
		assertEquals(newcharacter.getCharacter_name(),character_name);
		assertEquals(newcharacter.getCharacter_id(),character_id);
		assertEquals(newcharacter.getStrength(),strength);

	}

}
