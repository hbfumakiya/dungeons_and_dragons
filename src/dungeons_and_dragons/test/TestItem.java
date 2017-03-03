package dungeons_and_dragons.test;
import static org.junit.Assert.*;

import org.junit.Test;

import dungeons_and_dragons.model.ItemModel;

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

	

}
