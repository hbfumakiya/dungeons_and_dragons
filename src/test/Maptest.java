package test;

import java.awt.Point;

import org.junit.Assert;
import org.junit.Test;

import dungeons_and_dragons.model.GameMapModel;

/**
 * 
 * @author Shahida
 * Map validation
 *
 */


public class Maptest {
	
	/**
	 * entry door validation
	 */
	
	
	@Test
	public void test_setMap_entry_door()
	{
		//Given
		Point map_entry_door= new Point(0,0);
		
		//When
		GameMapModel mapentry= new GameMapModel();
		mapentry.setMap_entry_door(map_entry_door);
		
		//Then
		Assert.assertEquals(mapentry.getMap_entry_door(),map_entry_door);
	}
	
	/**
	 * exit door validation
	 */
	@Test 
	public void test_setMap_exit_door()
	{
		
	}

}
