package dungeons_and_dragons.test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import com.google.gson.JsonSyntaxException;

import dungeons_and_dragons.helper.FileHelper;
import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.model.GamePlayModel;
import dungeons_and_dragons.model.ItemModel;
import junit.framework.Assert;

/**
 * Check item values of character
 * 
 * @author : Shahida Chauhan & Urmil Kansara
 * 
 * 
 */

public class TestItem {


		@Test
		public void testItemTypeSelected() {
			// Given
			String item_type = "HELMET";
			String item_ability = "HELMET_MODEL";
			int item_id = 1;
			String item_name = "ABC";
			int item_point = 10;

			// When
			ItemModel item = new ItemModel(item_id, item_name, item_point, item_type, item_ability);

			// Then

			item.itemTypeSelected(item_type);
			assertEquals(item.getItem_ability(), item_ability);

		}
		
		@Test
		public void testSetCurrentId(){
			int var = 0;
			int check = var +1;
			ArrayList<ItemModel> alldata = new ArrayList<ItemModel>();
			try {
				alldata = FileHelper.getItems();
			} catch (JsonSyntaxException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (null != alldata) {
				var = alldata.get(alldata.size() - 1).getItem_id() + 1;
				check = var;
			} else {
				var = 1;
			}
			
			assertEquals(check,var);
		}

		

}
