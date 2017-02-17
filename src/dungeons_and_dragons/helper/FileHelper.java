package dungeons_and_dragons.helper;

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
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.model.ItemModel;



/**
 * this class use for manage game data into file
 * basically store data in json file  
 *	
 * @author Mihir Pujara
 *
 */
public class FileHelper {
	
	/**
	 * static variable for set character file path
	 * 
	 *  @type String
	 */
	private static final String CHARACTER_FILE = "res/character.json";
	
	/**
	 * static variable for set map file path
	 * 
	 *  @type String
	 */
	private static final String MAP_FILE = "res/map.json";
	
	/**
	 * static variable for set campaign file path
	 * 
	 *  @type String
	 */
	private static final String CAMPAIGN_FILE = "res/campaign.json";
	
	/**
	 * static variable for set item file path
	 * 
	 *  @type String
	 */
	private static final String ITEM_FILE = "res/item.json";
	
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
			// file exist
			
			//fetch old data from file and store that into array list
			item_list = getCharcters();
			
		} else {
			
			item_list = new ArrayList<CharacterModel>();
			
		}
		
		//add new data to arraylist
		item_list.add(character);
		
		//create writer object for character file
		Writer file_writer = new FileWriter(CHARACTER_FILE);
		
		// store object to json 
		Gson gson = new Gson();
		gson.toJson(item_list,file_writer);
		
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
	public static ArrayList<CharacterModel> getCharcters() throws IOException,JsonSyntaxException {
		
		//create reader objecr to read data from item file
		Reader reader = new FileReader(CHARACTER_FILE);
		
		// read data from json file convert it into arraylist and return it
		Gson gson = new Gson();
		return gson.fromJson(reader, new TypeToken<ArrayList<CharacterModel>>(){}.getType());	
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
			
			//fetch old data from file and store that into array list
			item_list = getItems();
			
		} else {
			
			item_list = new ArrayList<ItemModel>();
			
		}
		
		
		//add new data to arraylist
		item_list.add(item);
		
		//create writer object for item file
		Writer file_writer = new FileWriter(ITEM_FILE);
		
		// store object to json 
		Gson gson = new Gson();
		gson.toJson(item_list,file_writer);
		
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
	public static ArrayList<ItemModel> getItems() throws IOException,JsonSyntaxException {
		
		//create reader objecr to read data from item file
		Reader reader = new FileReader(ITEM_FILE);
		
		// read data from json file convert it into arraylist and return it
		Gson gson = new Gson();
		return gson.fromJson(reader, new TypeToken<ArrayList<ItemModel>>(){}.getType());		
	}
}
