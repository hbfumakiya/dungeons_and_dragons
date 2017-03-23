/**
 * 
 */
package dungeons_and_dragons.test;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.JsonSyntaxException;

import dungeons_and_dragons.controller.GamePlayController;
import dungeons_and_dragons.controller.ManageMapController;
import dungeons_and_dragons.controller.MapGridController;
import dungeons_and_dragons.helper.MapButton;
import dungeons_and_dragons.helper.MapCharacter;
import dungeons_and_dragons.helper.MapValidator;
import dungeons_and_dragons.model.CampaignModel;
import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.model.GameMapModel;
import dungeons_and_dragons.model.GamePlayModel;
import dungeons_and_dragons.view.MapGridView;

import static org.junit.Assert.*;
/**
 * 
 * @author urmil
 *
 */

public class TestGamePlay {
	
	//GameMapModel mapmodel=new GameMapModel(7,7);
	@Test
	public void testMovementUp(){
	GamePlayModel gamePlayModel = new GamePlayModel();
	CharacterModel characterModel = new CharacterModel();
	CampaignModel campaignModel = new CampaignModel();
	gamePlayModel.setCharacterModel(characterModel);
	gamePlayModel.setCampaignModel(campaignModel);
	
	Point oldPoint = new Point(3,1);
	//after pressing up new point
	//Point newPoint  = new Point(3,0);
	
	
	
	gamePlayModel.setGameCharacterPosition(oldPoint);
	gamePlayModel.moveCharacterUp(oldPoint);
	
	int x =(int)gamePlayModel.getGameCharacterPosition().getX();
	int y =(int)gamePlayModel.getGameCharacterPosition().getY();
	assertEquals(3, x);
	assertEquals(0, y);
	
	
	}
	
	@Test
	public void testMovementDown(){
	GamePlayModel gamePlayModel = new GamePlayModel();
	CharacterModel characterModel = new CharacterModel();
	CampaignModel campaignModel = new CampaignModel();
	gamePlayModel.setCharacterModel(characterModel);
	gamePlayModel.setCampaignModel(campaignModel);
	
	Point oldPoint = new Point(3,1);
	//after pressing down new point
	
	
	
	gamePlayModel.setGameCharacterPosition(oldPoint);
	gamePlayModel.moveCharacterDown(oldPoint);
	
	int x =(int)gamePlayModel.getGameCharacterPosition().getX();
	int y =(int)gamePlayModel.getGameCharacterPosition().getY();
	assertEquals(3, x);
	assertEquals(2, y);
	
	
	}
	
	@Test
	public void testMovementLeft(){
	GamePlayModel gamePlayModel = new GamePlayModel();
	CharacterModel characterModel = new CharacterModel();
	CampaignModel campaignModel = new CampaignModel();
	gamePlayModel.setCharacterModel(characterModel);
	gamePlayModel.setCampaignModel(campaignModel);
	
	Point oldPoint = new Point(3,1);
	//after pressing left new point
	
	
	
	gamePlayModel.setGameCharacterPosition(oldPoint);
	gamePlayModel.moveCharacterLeft(oldPoint);
	
	int x =(int)gamePlayModel.getGameCharacterPosition().getX();
	int y =(int)gamePlayModel.getGameCharacterPosition().getY();
	assertEquals(2, x);
	assertEquals(1, y);
	
	
	}
	
	@Test
	public void testMovementRight(){
	GamePlayModel gamePlayModel = new GamePlayModel();
	CharacterModel characterModel = new CharacterModel();
	CampaignModel campaignModel = new CampaignModel();
	gamePlayModel.setCharacterModel(characterModel);
	gamePlayModel.setCampaignModel(campaignModel);
	
	Point oldPoint = new Point(3,1);
	//after pressing right new point
	
	
	
	gamePlayModel.setGameCharacterPosition(oldPoint);
	gamePlayModel.moveCharacterRight(oldPoint);
	
	int x =(int)gamePlayModel.getGameCharacterPosition().getX();
	int y =(int)gamePlayModel.getGameCharacterPosition().getY();
	assertEquals(4, x);
	assertEquals(1, y);
	
	
	}
	
	

}
