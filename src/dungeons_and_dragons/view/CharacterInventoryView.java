/**
 * 
 */
package dungeons_and_dragons.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.model.ItemModel;

/**
 * Shows character details and allow to equip and unequip items
 * 
 * @author Mihir Pujara
 *
 */
public class CharacterInventoryView extends JFrame implements View,Observer {

	private JPanel panel;

	public JList<ItemModel> itemList;

	public JList<ItemModel> backPackList;

	public JButton moveFromItemToBack;

	public JButton moveFromBackToItem;

	public JButton okButton;

	private CharacterModel character;

	public JScrollPane backPackScrollPane;

	public JScrollPane itemScrollPane;

	private JLabel nameLabel;

	private JLabel levelLabel;

	private JLabel hitPointsLabel;

	private JLabel armorClassLabel;

	private JLabel attackBonusLabel;

	private JLabel damageBonusLabel;

	private JLabel abilityScoreLabel;

	private JLabel modifierLabel;

	private JLabel strengthLabel;

	private JLabel dexterityLabel;

	private JLabel constitutionLabel;

	private JLabel intelligenceLabel;

	private JLabel wisdomLabel;

	private JLabel charismaLabel;

	private JLabel strengthASLabel;

	private JLabel dexterityASLabel;

	private JLabel constitutionASLabel;

	private JLabel intelligenceASLabel;

	private JLabel wisdomASLabel;

	private JLabel charismaASLabel;

	private JLabel strengthMLabel;

	private JLabel dexterityMLabel;

	private JLabel constitutionMLabel;

	private JLabel intelligenceMLabel;

	private JLabel wisdomMLabel;

	private JLabel charismaMLabel;
	
	private JLabel itemLabel;
	
	private JLabel backPackLabel;
	
	public boolean showInventory;
	
	public JButton showInventoryButton;

	/**
	 * 
	 * @param character
	 */
	public CharacterInventoryView(CharacterModel character) {

		this.character = character;

		this.showInventory = true;
		
		this.setTitle("Character Inventory");

		this.initilizeView();

		this.setResizable(false);

		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	public CharacterInventoryView(CharacterModel character, boolean showInventory) {

		this.character = character;
		
		this.showInventory = showInventory;

		this.setTitle("Character Inventory");

		this.initilizeView();

		this.setResizable(false);

		this.pack();
		this.setLocationRelativeTo(null);
	}
	

	/**
	 * @return the character
	 */
	public CharacterModel getCharacter() {
		return character;
	}



	/**
	 * method to initialize inventory view
	 */
	public void initilizeView() {

		this.panel = new JPanel();
		this.panel.setLayout(null);
		if(showInventory)
			this.panel.setPreferredSize(new Dimension(500, 420));
		else 
			this.panel.setPreferredSize(new Dimension(500, 220));

		this.showDetails(this.character);
		
		this.showInventoryButton = new JButton("View Inventory");
		this.showInventoryButton.setBounds(170, 180, 160, 30);
		
		if(!showInventory) {
			this.panel.add(this.showInventoryButton);
		}

		// item list

		this.itemList = new JList<ItemModel>();

		ArrayList<ItemModel> temp = this.character.getItems();
		ArrayList<ItemModel> item = new ArrayList<ItemModel>();

		if ((temp != null) && (temp.size() > 0)) {

			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i) != null)
					item.add(temp.get(i));
			}
		}

		if ((item != null) || (item.size() > 0)) {

			ItemModel[] items = new ItemModel[item.size()];

			for (int i = 0; i < item.size(); i++) {
				if (item.get(i) != null)
					items[i] = item.get(i);
			}

			this.itemList.setListData(items);
			this.itemList.setCellRenderer(new ItemCellRenderer());
		}

		this.itemScrollPane = new JScrollPane(this.itemList);
		this.itemScrollPane.setBounds(10, 160, 200, 200);
		if(showInventory)
			this.panel.add(this.itemScrollPane);
		
		// back pack Label 
		this.itemLabel = new JLabel("Items");
		this.itemLabel.setBounds(10, 140, 200, 20);
		if(showInventory)
			this.panel.add(this.itemLabel);

		// backpack list

		this.backPackList = new JList<ItemModel>();
		this.backPackList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		temp = this.character.getBackPackItems();

		ArrayList<ItemModel> backPackItem = new ArrayList<ItemModel>();
		if ((temp != null) && (temp.size() > 0)) {
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i) != null)
					backPackItem.add(temp.get(i));
			}
		}

		if ((backPackItem != null) || (backPackItem.size() > 0)) {

			ItemModel[] items = new ItemModel[backPackItem.size()];

			for (int i = 0; i < backPackItem.size(); i++) {
				if (backPackItem.get(i) != null)
					items[i] = backPackItem.get(i);
			}

			this.backPackList.setListData(items);
			this.backPackList.setCellRenderer(new ItemCellRenderer());
		}

		this.backPackScrollPane = new JScrollPane(this.backPackList);
		this.backPackScrollPane.setBounds(290, 160, 200, 200);
		if(showInventory)
			this.panel.add(this.backPackScrollPane);
		
		
		// back pack Label 
		this.backPackLabel = new JLabel("Backpack Items");
		this.backPackLabel.setBounds(290, 140, 200, 20);
		if(showInventory)
			this.panel.add(this.backPackLabel);

		// move to item to backpack >

		this.moveFromItemToBack = new JButton(">");
		this.moveFromItemToBack.setBounds(225, 170, 50, 50);
		if(showInventory)
			this.panel.add(this.moveFromItemToBack);

		// move to backpack to item <

		this.moveFromBackToItem = new JButton("<");
		this.moveFromBackToItem.setBounds(225, 280, 50, 50);
		if(showInventory)
			this.panel.add(this.moveFromBackToItem);

		// ok button

		this.okButton = new JButton("OK");
		this.okButton.setBounds(217, 360, 65, 30);
		if(showInventory)
			this.panel.add(this.okButton);

		this.getContentPane().add(this.panel);
	}

	/**
	 * mathod for showing details of character
	 * 
	 * @param character
	 */
	public void showDetails(CharacterModel character) {
		
		//this.panel.removeAll();
		// name
		
		nameLabel = new JLabel("Name: " + character.getCharacter_name());
		nameLabel.setBounds(10, 5, 120, 20);
		this.panel.remove(nameLabel);
		this.panel.add(nameLabel);

		// level
		
		levelLabel = new JLabel("Level: " + character.getCharacter_level());
		levelLabel.setBounds(10, 25, 120, 20);
		this.panel.remove(levelLabel);
		this.panel.add(levelLabel);

		// hit points

		hitPointsLabel = new JLabel("Hit Points: " + character.getHitpoints());
		hitPointsLabel.setBounds(10, 45, 120, 20);
		this.panel.add(hitPointsLabel);

		// armor class

		armorClassLabel = new JLabel("Armor Class: " + character.getArmorClass());
		armorClassLabel.setBounds(10, 65, 120, 20);
		this.panel.add(armorClassLabel);

		// attack bonus
		String attackBonus = "";
		int temp = character.getAttackBonus();

		for (int i = temp; i > 0; i -= 5) {
			attackBonus += i + "/";
		}

		if (attackBonus.length() > 0)
			attackBonus = attackBonus.substring(0, attackBonus.length() - 1);

		attackBonusLabel = new JLabel("Attack Bonus: " + attackBonus);
		attackBonusLabel.setBounds(10, 85, 200, 20);
		this.panel.remove(attackBonusLabel);
		this.panel.add(attackBonusLabel);

		// damage bonus

		damageBonusLabel = new JLabel("Damage Bonus: " + character.getDamageBonus());
		damageBonusLabel.setBounds(10, 105, 120, 20);
		this.panel.add(damageBonusLabel);

		abilityScoreLabel = new JLabel("Ability Score");
		abilityScoreLabel.setBounds(300, 5, 100, 20);
		this.panel.add(abilityScoreLabel);

		modifierLabel = new JLabel("Modifiers");
		modifierLabel.setBounds(400, 5, 100, 20);
		this.panel.add(modifierLabel);

		strengthLabel = new JLabel("Strength");
		strengthLabel.setBounds(210, 20, 100, 20);
		
		this.panel.add(strengthLabel);

		dexterityLabel = new JLabel("Dexterity");
		dexterityLabel.setBounds(210, 40, 100, 20);
		this.panel.add(dexterityLabel);

		constitutionLabel = new JLabel("Constitution");
		constitutionLabel.setBounds(210, 60, 100, 20);
		this.panel.add(constitutionLabel);

		intelligenceLabel = new JLabel("Intelligence");
		intelligenceLabel.setBounds(210, 80, 100, 20);
		this.panel.add(intelligenceLabel);

		wisdomLabel = new JLabel("Wisdom");
		wisdomLabel.setBounds(210, 100, 100, 20);
		this.panel.add(wisdomLabel);

		charismaLabel = new JLabel("Charisma");
		charismaLabel.setBounds(210, 120, 100, 20);
		this.panel.add(charismaLabel);

		
		
		
		
		strengthASLabel = new JLabel(character.getAbilityScores().getStraight() + "");
		strengthASLabel.setBounds(330, 20, 100, 20);
		this.panel.add(strengthASLabel);
		
		dexterityASLabel = new JLabel(character.getAbilityScores().getDexterity() + "");
		dexterityASLabel.setBounds(330, 40, 100, 20);
		this.panel.add(dexterityASLabel);

		constitutionASLabel = new JLabel(character.getAbilityScores().getConstitution() + "");
		constitutionASLabel.setBounds(330, 60, 100, 20);
		this.panel.add(constitutionASLabel);

		intelligenceASLabel = new JLabel(character.getAbilityScores().getIntelligence() + "");
		intelligenceASLabel.setBounds(330, 80, 100, 20);
		this.panel.add(intelligenceASLabel);

		wisdomASLabel = new JLabel(character.getAbilityScores().getWisdom() + "");
		wisdomASLabel.setBounds(330, 100, 100, 20);
		this.panel.add(wisdomASLabel);

		charismaASLabel = new JLabel(character.getAbilityScores().getCharisma() + "");
		charismaASLabel.setBounds(330, 120, 120, 20);
		this.panel.add(charismaASLabel);

		strengthMLabel = new JLabel(character.getModifiers().getStraight() + "");
		strengthMLabel.setBounds(420, 20, 100, 20);
		this.panel.add(strengthMLabel);

		dexterityMLabel = new JLabel(character.getModifiers().getDexterity() + "");
		dexterityMLabel.setBounds(420, 40, 100, 20);
		this.panel.add(dexterityMLabel);

		constitutionMLabel = new JLabel(character.getModifiers().getConstitution() + "");
		constitutionMLabel.setBounds(420, 60, 100, 20);
		this.panel.add(constitutionMLabel);

		intelligenceMLabel = new JLabel(character.getModifiers().getIntelligence() + "");
		intelligenceMLabel.setBounds(420, 80, 100, 20);
		this.panel.add(intelligenceMLabel);

		wisdomMLabel = new JLabel(character.getModifiers().getWisdom() + "");
		wisdomMLabel.setBounds(420, 100, 100, 20);
		this.panel.add(wisdomMLabel);

		charismaMLabel = new JLabel(character.getModifiers().getCharisma() + "");
		charismaMLabel.setBounds(420, 120, 100, 20);
		this.panel.add(charismaMLabel);
	}

	/**
	 * method to update backpack and item
	 * 
	 * @param character
	 */
	public void updateList(CharacterModel character) {

		this.itemList.removeAll();
		ArrayList<ItemModel> temp = character.getItems();
		ArrayList<ItemModel> item = new ArrayList<ItemModel>();

		if ((temp != null) && (temp.size() > 0)) {

			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i) != null)
					item.add(temp.get(i));
			}
		}

		if ((item != null) || (item.size() > 0)) {

			ItemModel[] items = new ItemModel[item.size()];

			for (int i = 0; i < item.size(); i++) {
				if (item.get(i) != null)
					items[i] = item.get(i);
			}

			this.itemList.setListData(items);
		}

		this.backPackList.removeAll();

		temp = character.getBackPackItems();

		ArrayList<ItemModel> backPackItem = new ArrayList<ItemModel>();
		if ((temp != null) && (temp.size() > 0)) {
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i) != null)
					backPackItem.add(temp.get(i));
			}
		}

		if ((backPackItem != null) || (backPackItem.size() > 0)) {

			ItemModel[] items = new ItemModel[backPackItem.size()];

			for (int i = 0; i < backPackItem.size(); i++) {
				if (backPackItem.get(i) != null)
					items[i] = backPackItem.get(i);
			}

			this.backPackList.setListData(items);
		}

	}

	/**
	 * 
	 * class for custom items in JList
	 * 
	 * @author Mihir Pujara
	 *
	 */
	class ItemCellRenderer extends JLabel implements ListCellRenderer<ItemModel> {

		private static final long serialVersionUID = 1L;

		public ItemCellRenderer() {
			setOpaque(true);
		}

		@Override
		public Component getListCellRendererComponent(JList<? extends ItemModel> arg0, ItemModel arg1, int arg2,
				boolean arg3, boolean arg4) {

			if (arg1 != null) {
				setText(arg1.getItem_name() + "("+arg1.getItem_type()+" - "+arg1.getItem_ability()+")");
				setSize(200, 30);
			}

			if (arg3) {
				setBackground(new Color(0, 0, 128));
				setForeground(Color.white);
			} else {
				setBackground(Color.white);
				setForeground(Color.black);
			}

			return this;
		}
	}

	@Override
	public void setActionListener(ActionListener actionListener) {

		this.moveFromItemToBack.addActionListener(actionListener);

		this.moveFromBackToItem.addActionListener(actionListener);

		this.okButton.addActionListener(actionListener);
		
		if(this.showInventoryButton != null) {
			this.showInventoryButton.addActionListener(actionListener);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
		this.panel.remove(strengthASLabel);
		this.panel.remove(dexterityASLabel);
		this.panel.remove(constitutionASLabel);
		this.panel.remove(charismaASLabel);
		this.panel.remove(wisdomASLabel);
		this.panel.remove(intelligenceASLabel);
		
		
		this.panel.remove(strengthLabel);
		this.panel.remove(dexterityLabel);
		this.panel.remove(constitutionLabel);
		this.panel.remove(charismaLabel);
		this.panel.remove(wisdomLabel);
		this.panel.remove(intelligenceLabel);
		
		this.panel.remove(hitPointsLabel);
		this.panel.remove(nameLabel);
		this.panel.remove(levelLabel);
		this.panel.remove(armorClassLabel);
		this.panel.remove(attackBonusLabel);
		this.panel.remove(damageBonusLabel);
		
		this.panel.remove(abilityScoreLabel);
		this.panel.remove(modifierLabel);
		
		
		this.panel.remove(strengthMLabel);
		this.panel.remove(dexterityMLabel);
		this.panel.remove(constitutionMLabel);
		this.panel.remove(charismaMLabel);
		this.panel.remove(wisdomMLabel);
		this.panel.remove(intelligenceMLabel);
		
		showDetails((CharacterModel) o);
		updateList((CharacterModel) o);
		
		
		this.validate();
		this.repaint();
		//this.panel.getComponent();
		
		//this.panel.updateUI();
		this.revalidate();
		this.pack();
	}
	
	/**
	 * 
	 * @param windowListener
	 */
	public void setWindowListener(WindowListener windowListener) {
		this.addWindowListener(windowListener);
	}
	
}
