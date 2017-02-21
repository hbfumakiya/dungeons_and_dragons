package dungeons_and_dragons.helper;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;

/*
 * This class is to store all constants in a single class file for accessing it anywhere within a program.
 * 
 * @author : Tejas Sadrani
 */
public class Game_constants {
	
	public static final String INTELLIGENCE = "Intelligence";
	public static final String WISDOM = "Wisdom";
	public static final String ARMOR_CLASS = "Armor class";
	public static final String STRENGTH = "Strength";
	public static final String CONSTITUTION = "Constitution";
	public static final String CHARISMA = "Charisma";
	public static final String DEXTERITY = "Dexterity";
	public static final String ATTACK_BONUS = "Attach bonus";
	public static final String DAMAGE_BONUS = "Damage bonus";
	
	public static final String HELMET = "Helmet";
	public static final String ARMOR = "Armor";
	public static final String SHIELD = "Shield";
	public static final String RING = "Ring";
	public static final String BELT = "Belt";
	public static final String BOOTS = "Boots";
	public static final String WEAPON = "Weapon";
	
	public static final Color ENTRY_DOOR = Color.YELLOW;
	public static final Color EXIT_DOOR = Color.GREEN;
	public static final Color WALLS = Color.GRAY;
	public static final Color ENEMIES = Color.RED;
	public static final Color CHEST = Color.BLUE;
	
	
	/**
	 * model for loading data into item ability based on selection of item type helmet
	 * @type :  DefaultComboBoxModel
	 */
     @SuppressWarnings({ "unchecked", "rawtypes" })
	public
    final static DefaultComboBoxModel HELMET_MODEL = new DefaultComboBoxModel(new String[]{Game_constants.INTELLIGENCE,Game_constants.WISDOM,Game_constants.ARMOR_CLASS});
    
    /**
	 * model for loading data into item ability based on selection of item type armor
	 * @type :  DefaultComboBoxModel
	 */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static final DefaultComboBoxModel ARMOR_MODEL = new DefaultComboBoxModel(new String[]{Game_constants.ARMOR_CLASS});
    
    /**
	 * model for loading data into item ability based on selection of item type shield
	 * @type :  DefaultComboBoxModel
	 */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static final DefaultComboBoxModel SHIELD_MODEL = new DefaultComboBoxModel(new String[]{Game_constants.ARMOR_CLASS});
    
    
    /**
	 * model for loading data into item ability based on selection of item type ring
	 * @type :  DefaultComboBoxModel
	 */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static final DefaultComboBoxModel RING_MODEL = new DefaultComboBoxModel(new String[]{Game_constants.STRENGTH,Game_constants.WISDOM,Game_constants.ARMOR_CLASS,Game_constants.CONSTITUTION,Game_constants.CHARISMA});
    
    
    /**
	 * model for loading data into item ability based on selection of item type belt
	 * @type :  DefaultComboBoxModel
	 */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static final DefaultComboBoxModel BELT_MODEL = new DefaultComboBoxModel(new String[]{Game_constants.STRENGTH,Game_constants.CONSTITUTION});
    
    /**
	 * model for loading data into item ability based on selection of item type boots
	 * @type :  DefaultComboBoxModel
	 */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static final DefaultComboBoxModel BOOTS_MODEL = new DefaultComboBoxModel(new String[]{Game_constants.DEXTERITY,Game_constants.WISDOM,Game_constants.ARMOR_CLASS});
    
    
    /**
	 * model for loading data into item ability based on selection of item type weapon
	 * @type :  DefaultComboBoxModel
	 */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static final DefaultComboBoxModel WEAPON_MODEL = new DefaultComboBoxModel(new String[]{Game_constants.ATTACK_BONUS,Game_constants.DAMAGE_BONUS});
    
    
    public static final String GRID_BUTTON_TYPE = "grid_button";
	
}
