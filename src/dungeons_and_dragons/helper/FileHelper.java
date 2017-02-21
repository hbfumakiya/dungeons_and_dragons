package dungeons_and_dragons.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import dungeons_and_dragons.exception.NotFoundException;
import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.model.GameMapModel;
import dungeons_and_dragons.model.ItemModel;

/**
 * this class use for manage game data into file basically store data in json
 * file
 * 
 * @author Mihir Pujara
 *
 */
public class FileHelper {

	/**
	 * static variable for set character file path
	 * 
	 * @type String
	 */
	private static final String CHARACTER_FILE = "res/character.json";

	/**
	 * static variable for set map file path
	 * 
	 * @type String
	 */
	private static final String MAP_FILE = "res/map.json";

	/**
	 * static variable for set campaign file path
	 * 
	 * @type String
	 */
	private static final String CAMPAIGN_FILE = "res/campaign.json";

	/**
	 * static variable for set item file path
	 * 
	 * @type String
	 */
	private static final String ITEM_FILE = "res/item.json";
	
	
	/**
	 * 
	 * @param map
	 * @throws IOException
	 */
	public static void saveMap(GameMapModel map) throws IOException {

		Path path = Paths.get(MAP_FILE);

		ArrayList<GameMapModel> map_list;

		if (Files.exists(path)) {
			// file exist

			// fetch old data from file and store that into array list
			map_list = getMaps();
			if (map_list == null) {
				map_list = new ArrayList<GameMapModel>();
			}

		} else {

			map_list = new ArrayList<GameMapModel>();

		}

		// add new data to arraylist
		map_list.add(map);

		// create writer object for item file
		Writer file_writer = new FileWriter(MAP_FILE);

		// store object to json
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		// String ness = gson.toJson(item);
		gson.toJson(map_list, file_writer);

		// close file
		file_writer.close();

	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<GameMapModel> getMaps() throws IOException {

		File mapFile = new File(MAP_FILE);

		if (!mapFile.exists()) {
			mapFile.createNewFile();
		}

		// create reader objecr to read data from item file
		Reader reader = new FileReader(MAP_FILE);

		// read data from json file convert it into arraylist and return it
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.fromJson(reader, new TypeToken<ArrayList<GameMapModel>>() {
		}.getType());

	}

	/**
	 * 
	 * @param map
	 * @throws IOException
	 * @throws NotFoundException
	 */
	public static void updateMap(GameMapModel map) throws IOException, NotFoundException {
		
		Path path = Paths.get(ITEM_FILE);

		ArrayList<GameMapModel> map_list;

		if (Files.exists(path)) {

			// fetch old data from file and store that into array list
			map_list = getMaps();
			if (map_list == null) {
				throw new NotFoundException();
			}
			boolean found = false;
			for (int i = 0; i < map_list.size(); i++) {
				GameMapModel tempItem = map_list.get(i);

				if (tempItem.getMap_id() == map.getMap_id()) {

					/*tempItem.setItem_name(item.getItem_name());
					tempItem.setItem_type(item.getItem_type());
					tempItem.setItem_ability(item.getItem_ability());
					tempItem.setItem_point(item.getItem_point());*/
					found = true;
				}
			}

			if (found) {
				// create writer object for item file
				Writer file_writer = new FileWriter(ITEM_FILE);

				// store object to json
				Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

				gson.toJson(map_list, file_writer);

				// close file
				file_writer.close();
			} else {
				throw new NotFoundException();
			}
		}

	}

	/**
	 * this method used to save character into character file
	 * 
	 * @param character
	 * @throws IOException
	 */
	public static void saveCharacter(CharacterModel character) throws IOException {

		Path path = Paths.get(CHARACTER_FILE);

		ArrayList<CharacterModel> item_list;

		if (Files.exists(path)) {
			item_list = getCharcters();
		} else {
			item_list = new ArrayList<CharacterModel>();
		}

		if (item_list == null) {
			item_list = new ArrayList<CharacterModel>();
		}

		// add new data to arraylist
		item_list.add(character);

		// create writer object for character file
		Writer file_writer = new FileWriter(CHARACTER_FILE);

		// store object to json
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		gson.toJson(item_list, file_writer);

		// close file
		file_writer.close();
	}

	/**
	 * this method gives the list of characters that saved in file
	 * 
	 * @return ArrayList<CharacterModel>
	 * @throws IOException
	 * @throws JsonSyntaxException
	 */
	public static ArrayList<CharacterModel> getCharcters() throws IOException, JsonSyntaxException {

		if (!Files.exists(Paths.get(CHARACTER_FILE))) {
			return null;
		}

		// create reader objecr to read data from item file
		Reader reader = new FileReader(CHARACTER_FILE);

		// read data from json file convert it into arraylist and return it
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.fromJson(reader, new TypeToken<ArrayList<CharacterModel>>() {
		}.getType());
	}

	/**
	 * 
	 * @param character
	 * @throws JsonSyntaxException
	 * @throws IOException
	 * @throws NotFoundException
	 */
	public static void updateCharacter(CharacterModel character)
			throws JsonSyntaxException, IOException, NotFoundException {
		Path path = Paths.get(ITEM_FILE);

		ArrayList<CharacterModel> character_list;

		if (Files.exists(path)) {

			// fetch old data from file and store that into array list
			character_list = getCharcters();
			if (character_list == null) {
				throw new NotFoundException();
			}
			boolean found = false;
			for (int i = 0; i < character_list.size(); i++) {
				CharacterModel tempChatacter = character_list.get(i);

				if (tempChatacter.getCharacter_id() == character.getCharacter_id()) {

					// tempChatacter.setCharacter_name(character_name);

					found = true;
				}
			}

			if (found) {
				// create writer object for item file
				Writer file_writer = new FileWriter(CHARACTER_FILE);

				// store object to json
				Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

				gson.toJson(character_list, file_writer);

				// close file
				file_writer.close();
			} else {
				throw new NotFoundException();
			}
		}
	}

	/**
	 * this method used to save item into item file
	 * 
	 * @param item
	 * @throws IOException
	 */
	public static void saveItem(ItemModel item) throws IOException {

		Path path = Paths.get(ITEM_FILE);

		ArrayList<ItemModel> item_list;

		if (Files.exists(path)) {
			// file exist

			// fetch old data from file and store that into array list
			item_list = getItems();
			if (item_list == null) {
				item_list = new ArrayList<ItemModel>();
			}

		} else {

			item_list = new ArrayList<ItemModel>();

		}

		// add new data to arraylist
		item_list.add(item);

		// create writer object for item file
		Writer file_writer = new FileWriter(ITEM_FILE);

		// store object to json
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		// String ness = gson.toJson(item);
		gson.toJson(item_list, file_writer);

		// close file
		file_writer.close();
	}

	/**
	 * this method gives the list of items that saved in file
	 * 
	 * @return ArrayList<ItemModel>
	 * @throws IOException
	 * @throws JsonSyntaxException
	 */
	public static ArrayList<ItemModel> getItems() throws IOException, JsonSyntaxException {

		// create reader objecr to read data from item file
		Reader reader = new FileReader(ITEM_FILE);

		// read data from json file convert it into arraylist and return it
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.fromJson(reader, new TypeToken<ArrayList<ItemModel>>() {
		}.getType());
	}

	/**
	 * 
	 * @param item
	 * @throws JsonSyntaxException
	 * @throws IOException
	 * @throws NotFoundException
	 */
	public static void updateItem(ItemModel item) throws JsonSyntaxException, IOException, NotFoundException {
		Path path = Paths.get(ITEM_FILE);

		ArrayList<ItemModel> item_list;

		if (Files.exists(path)) {

			// fetch old data from file and store that into array list
			item_list = getItems();
			if (item_list == null) {
				throw new NotFoundException();
			}
			boolean found = false;
			for (int i = 0; i < item_list.size(); i++) {
				ItemModel tempItem = item_list.get(i);

				if (tempItem.getItem_id() == item.getItem_id()) {

					tempItem.setItem_name(item.getItem_name());
					tempItem.setItem_type(item.getItem_type());
					tempItem.setItem_ability(item.getItem_ability());
					tempItem.setItem_point(item.getItem_point());
					found = true;
				}
			}

			if (found) {
				// create writer object for item file
				Writer file_writer = new FileWriter(ITEM_FILE);

				// store object to json
				Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

				gson.toJson(item_list, file_writer);

				// close file
				file_writer.close();
			} else {
				throw new NotFoundException();
			}
		}
	}
}
