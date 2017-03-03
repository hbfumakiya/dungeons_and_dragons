package dungeons_and_dragons.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import com.google.gson.JsonSyntaxException;

import dungeons_and_dragons.helper.FileHelper;
import dungeons_and_dragons.helper.Game_constants;
import dungeons_and_dragons.helper.LogHelper;
import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.model.ItemModel;

/**
 * @author Hrangi Naik
 *
 */

public class CharacterView extends JFrame implements Observer, View {

	/**
	 * this variable used to set window title
	 * 
	 * @type String
	 */
	public String window_title = "Create Character";

	/**
	 * this variable used for character name button
	 * 
	 * @type JLabel
	 */
	public JLabel charactername_label;

	/**
	 * this variable used for additem label
	 * 
	 * @type JLabel
	 */
	public JLabel additem_label;

	/**
	 * this variable used for level label
	 * 
	 * @type JLabel
	 */
	public JLabel level_label;

	/**
	 * this variable used for backpack label
	 * 
	 * @type JLabel
	 */
	public JLabel backpack_label;

	/**
	 * this variable used for character name textfield
	 * 
	 * @type JTextField
	 */
	public JTextField charactername_textfield;

	/**
	 * this variable used for level textfield
	 * 
	 * @type JTextField
	 */
	public JTextField level_textfield;

	/**
	 * this variable used for item combobox
	 * 
	 * @type JComboBox
	 */
	public JComboBox<String> helmet_combobox;
	public JComboBox armer_combobox;
	public JComboBox shield_combobox;
	public JComboBox belt_combobox;
	public JComboBox boot_combobox;
	public JComboBox ring_combobox;
	public JComboBox weapon_combobox;

	/**
	 * this variable used for backpack combobox
	 * 
	 * @type JComboBox
	 */
	public JComboBox<String> backpack_combobox;

	/**
	 * this variable used for save button
	 * 
	 * @type JButton
	 */
	public JButton save;

	/**
	 * this variable used for back button
	 * 
	 * @type JButton
	 */
	public JButton back;
	public JButton rolldice_save;
	public JList<ItemModel> backPackList;

	public JButton add;
	public JButton update;
	private ArrayList<ItemModel> items;
	private ArrayList<ItemModel> backpack;
	public int i;

	/**
	 * Default constructor of character view
	 * <p>
	 * get items and initialize window
	 */
	public CharacterView() {

		this.items = new ArrayList<ItemModel>();

		try {
			this.items = FileHelper.getItems();
		} catch (JsonSyntaxException | IOException e) {
			LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
		}

		this.i = 0;

		// initialize game windowra
		this.initializeWindow();

		// close frame while user click on close
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * 
	 * @param characterModel
	 */
	public CharacterView(CharacterModel characterModel) {
		this.items = new ArrayList<ItemModel>();

		try {
			this.items = FileHelper.getItems();
		} catch (JsonSyntaxException | IOException e) {
			LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
		}

		this.i = 1;

		// initialize game window
		this.initializeWindow();

		this.charactername_textfield.setText(characterModel.getCharacter_name());
		this.level_textfield.setText(characterModel.getCharacter_level() + "");

		// close frame while user click on close
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * initialize character window
	 * 
	 */
	private void initializeWindow() {

		// frame=new JFrame("Character window");
		this.setTitle(this.window_title);
		this.setPreferredSize(new Dimension(320, 580));
		this.setResizable(false);
		this.setLayout(null);

		charactername_label = new JLabel("Enter a Name");
		charactername_label.setBounds(10, 10, 100, 25);
		this.add(charactername_label);
		charactername_textfield = new JTextField();
		charactername_textfield.setBounds(150, 10, 150, 25);
		this.add(charactername_textfield);

		// helmet

		additem_label = new JLabel("Select Helmet");
		additem_label.setBounds(10, 40, 100, 25);
		this.add(additem_label);
		Object[] ob1 = new Object[] { null };
		Object[] helmetTemp = this.items.stream().filter(p -> p.getItem_type().equals(Game_constants.HELMET)).toArray();
		Object[] helmet = Stream.of(ob1, helmetTemp).flatMap(Stream::of).toArray();
		helmet_combobox = new JComboBox(helmet);
		if (helmet.length > 0)
			helmet_combobox.setRenderer(new ItemRenderer());
		helmet_combobox.setBounds(150, 40, 150, 25);
		this.add(helmet_combobox);

		// armer

		additem_label = new JLabel("Select Armor");
		additem_label.setBounds(10, 70, 100, 25);
		this.add(additem_label);
		Object[] armerTemp = this.items.stream().filter(p -> p.getItem_type().equals(Game_constants.ARMOR)).toArray();
		Object[] armer = Stream.of(ob1, armerTemp).flatMap(Stream::of).toArray();
		armer_combobox = new JComboBox(armer);
		if (armer.length > 0)
			armer_combobox.setRenderer(new ItemRenderer());
		armer_combobox.setBounds(150, 70, 150, 25);
		this.add(armer_combobox);

		// shield

		additem_label = new JLabel("Select Shield");
		additem_label.setBounds(10, 100, 100, 25);
		this.add(additem_label);
		Object[] shieldsTemp = this.items.stream().filter(p -> p.getItem_type().equals(Game_constants.SHIELD))
				.toArray();
		Object[] shields = Stream.of(ob1, shieldsTemp).flatMap(Stream::of).toArray();
		shield_combobox = new JComboBox(shields);
		if (shields.length > 0)
			shield_combobox.setRenderer(new ItemRenderer());
		shield_combobox.setBounds(150, 100, 150, 25);
		this.add(shield_combobox);

		// ring

		additem_label = new JLabel("Select Ring");
		additem_label.setBounds(10, 130, 100, 25);
		this.add(additem_label);
		Object[] ringsTemp = this.items.stream().filter(p -> p.getItem_type().equals(Game_constants.RING)).toArray();
		Object[] rings = Stream.of(ob1, ringsTemp).flatMap(Stream::of).toArray();
		ring_combobox = new JComboBox(rings);
		if (rings.length > 0)
			ring_combobox.setRenderer(new ItemRenderer());
		ring_combobox.setBounds(150, 130, 150, 25);
		this.add(ring_combobox);

		// belt

		additem_label = new JLabel("Select Belt");
		additem_label.setBounds(10, 160, 100, 25);
		this.add(additem_label);
		Object[] beltsTemp = this.items.stream().filter(p -> p.getItem_type().equals(Game_constants.BELT)).toArray();
		Object[] belts = Stream.of(ob1, beltsTemp).flatMap(Stream::of).toArray();
		belt_combobox = new JComboBox(belts);
		if (belts.length > 0)
			belt_combobox.setRenderer(new ItemRenderer());
		belt_combobox.setBounds(150, 160, 150, 25);
		this.add(belt_combobox);

		// boot

		additem_label = new JLabel("Select Boot");
		additem_label.setBounds(10, 190, 100, 25);
		this.add(additem_label);
		Object[] bootsTemp = this.items.stream().filter(p -> p.getItem_type().equals(Game_constants.BOOTS)).toArray();
		Object[] boots = Stream.of(ob1, bootsTemp).flatMap(Stream::of).toArray();
		boot_combobox = new JComboBox(boots);
		if (boots.length > 0)
			boot_combobox.setRenderer(new ItemRenderer());
		boot_combobox.setBounds(150, 190, 150, 25);
		this.add(boot_combobox);

		// weapon

		additem_label = new JLabel("Select Weapon");
		additem_label.setBounds(10, 220, 100, 25);
		this.add(additem_label);
		Object[] weaponRangeTemp = this.items.stream().filter(p -> p.getItem_type().equals(Game_constants.WEAPON_RANGE))
				.toArray();
		Object[] weaponMeleeTemp = this.items.stream().filter(p -> p.getItem_type().equals(Game_constants.WEAPON_MELEE))
				.toArray();
		Object[] weapon = Stream.of(ob1, weaponRangeTemp).flatMap(Stream::of).toArray();
		weapon = Stream.of(weapon, weaponMeleeTemp).flatMap(Stream::of).toArray();
		weapon_combobox = new JComboBox(weapon);
		if (weapon.length > 0)
			weapon_combobox.setRenderer(new ItemRenderer());
		weapon_combobox.setBounds(150, 220, 150, 25);
		this.add(weapon_combobox);

		level_label = new JLabel("Enter Level");
		level_label.setBounds(10, 250, 100, 25);
		this.add(level_label);

		level_textfield = new JTextField();
		level_textfield.setPreferredSize(new Dimension(50, 40));
		level_textfield.setBounds(150, 250, 150, 25);
		this.add(level_textfield);

		backpack_label = new JLabel("BackPack");
		backpack_label.setBounds(10, 280, 100, 25);
		this.add(backpack_label);

		ItemModel[] backpack = new ItemModel[this.items.size()];
		for (int i = 0; i < this.items.size(); i++) {
			backpack[i] = this.items.get(i);
		}

		backPackList = new JList<ItemModel>();
		if (backpack.length > 0) {

			backPackList.setListData(backpack);
			backPackList.setCellRenderer(new ItemCellRenderer());
		}
		backPackList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane backPackPane = new JScrollPane(backPackList);
		backPackPane.setBounds(150, 280, 150, 150);
		this.add(backPackPane);

		if (this.i == 0) {
			rolldice_save = new JButton("Roll Dice & Save");
			rolldice_save.setBounds(110, 445, 200, 25);
			this.add(rolldice_save);
		}

		back = new JButton("Back");
		back.setBounds(20, 500, 80, 25);
		this.add(back);

		// if (this.i == 0) {
		// save = new JButton("Save");
		// save.setBounds(110, 500, 80, 25);
		// save.setEnabled(false);
		// this.add(save);
		// }

		if (this.i == 1) {
			update = new JButton("Update");
			update.setBounds(110, 500, 80, 25);
			this.add(update);
		}
		// Display the window.
		this.pack();
		this.setLocationRelativeTo(null);
	}

	@Override
	public void update(Observable arg0, Object arg1) {

	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		if (this.i == 0) {
			// this.save.addActionListener(actionListener);
			this.rolldice_save.addActionListener(actionListener);
		} else
			this.update.addActionListener(actionListener);
		this.back.addActionListener(actionListener);
	}

	/**
	 * 
	 * @author Hirangi
	 *
	 */
	class ItemRenderer extends BasicComboBoxRenderer {
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			if (value != null) {
				ItemModel item = (ItemModel) value;
				setText(item.getItem_name());
			} else {
				setText("");
			}

			return this;
		}
	}

	/**
	 * 
	 * @author Hirangi
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
				setText(arg1.getItem_name());
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

}
