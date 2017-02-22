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
	 * entry door validation considering all six cases
	 */
	Point map_size=new Point(5,5);
	
	@Test
	public void test_setMap_entry_door()
	{
		
		//Given
		Point map_entry_door1= new Point(0,0);
		Point map_entry_door2= new Point(0,4);
		Point map_entry_door3= new Point(3,0);
		Point map_entry_door4= new Point(4,4);
		Point map_entry_door5= new Point(4,2);
		Point map_entry_door6= new Point(2,4);


		//When
		GameMapModel mapentry= new GameMapModel();
		mapentry.setMap_size(map_size);

		mapentry.setMap_entry_door(map_entry_door1);
		
		//Then
		Assert.assertEquals(mapentry.getMap_entry_door(),map_entry_door1);
		
		//When
		mapentry.setMap_entry_door(map_entry_door2);
		
		//Then
		Assert.assertEquals(mapentry.getMap_entry_door(),map_entry_door2);

		//When
		mapentry.setMap_entry_door(map_entry_door3);
		//Then
		Assert.assertEquals(mapentry.getMap_entry_door(),map_entry_door3);
	
		//When
		mapentry.setMap_entry_door(map_entry_door4);
		//Then
		Assert.assertEquals(mapentry.getMap_entry_door(),map_entry_door4);

		//When
		mapentry.setMap_entry_door(map_entry_door5);
		//Then
		Assert.assertEquals(mapentry.getMap_entry_door(),map_entry_door5);

		//When
		mapentry.setMap_entry_door(map_entry_door6);
	
		//Then
		Assert.assertEquals(mapentry.getMap_entry_door(),map_entry_door6);

	}
	
	
	/**
	 * exit door validation considering all six cases
	 */
	@Test 
	public void test_setMap_exit_door()
	{
				//Given
				Point map_exit_door1= new Point(0,0);
				Point map_exit_door2= new Point(0,4);
				Point map_exit_door3= new Point(3,0);
				Point map_exit_door4= new Point(4,4);
				Point map_exit_door5= new Point(4,2);
				Point map_exit_door6= new Point(2,4);
				
				//When
				GameMapModel mapexit= new GameMapModel();
				mapexit.setMap_size(map_size);

				mapexit.setMap_exit_door(map_exit_door1);
				
				//Then
				Assert.assertEquals(mapexit.getMap_exit_door(),map_exit_door1);
				
				//When
				mapexit.setMap_exit_door(map_exit_door2);
				
				//Then
				Assert.assertEquals(mapexit.getMap_exit_door(),map_exit_door2);
				
				//When
				mapexit.setMap_exit_door(map_exit_door3);
				
				//Then
				Assert.assertEquals(mapexit.getMap_exit_door(),map_exit_door3);
				
				//When
				mapexit.setMap_exit_door(map_exit_door4);
				
				//Then
				Assert.assertEquals(mapexit.getMap_exit_door(),map_exit_door4);
				//When
				mapexit.setMap_exit_door(map_exit_door5);
				
				//Then
				Assert.assertEquals(mapexit.getMap_exit_door(),map_exit_door5);
				//When
				mapexit.setMap_exit_door(map_exit_door6);
				
				//Then
				Assert.assertEquals(mapexit.getMap_exit_door(),map_exit_door6);
		
		
	}

}
