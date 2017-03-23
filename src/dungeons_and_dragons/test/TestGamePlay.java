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
	public void testMovement(){
	GamePlayModel gamePlayModel = new GamePlayModel();
	CharacterModel characterModel = new CharacterModel();
	CampaignModel campaignModel = new CampaignModel();
	gamePlayModel.setCharacterModel(characterModel);
	gamePlayModel.setCampaignModel(campaignModel);
	
	Point oldPoint = new Point(3,1);
	
	
	
	
	
	
	
	
	}
	

}
