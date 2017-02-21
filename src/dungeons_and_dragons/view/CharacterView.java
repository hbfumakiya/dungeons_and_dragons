package dungeons_and_dragons.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import com.google.gson.JsonSyntaxException;

import dungeons_and_dragons.helper.FileHelper;
import dungeons_and_dragons.helper.Game_constants;
import dungeons_and_dragons.helper.LogHelper;
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
	 * this variable used for main panel
	 * 
	 * @type JPanel
	 */
	public JPanel main_panel;

	/**
	 * this variable used for sub panel
	 * 
	 * @type JPanel
	 */
	public JPanel sub_panel;

	/**
	 * this variable used for list panel
	 * 
	 * @type JPanel
	 */
	public JPanel list_panel;

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
	public JComboBox helmet_combobox;
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

	/**
	 * 
	 */
	private ArrayList<ItemModel> items;

	public CharacterView() {

		this.items = new ArrayList<ItemModel>();

		try {
			this.items = FileHelper.getItems();
		} catch (JsonSyntaxException | IOException e) {
			// TODO Auto-generated catch block
			LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
		}

		// initialize game window
		this.initializeWindow();

		// close frame while user click on close
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * initialize character window
	 * 
	 */
	private void initializeWindow() {

		// set window title
		this.setTitle(this.window_title);

		main_panel = new JPanel();
		main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.PAGE_AXIS));
		main_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(main_panel);

		sub_panel = new JPanel();
		sub_panel.setLayout(new BoxLayout(sub_panel, BoxLayout.LINE_AXIS));
		sub_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		sub_panel.setMaximumSize(new Dimension(300, 1000));

		main_panel.add(sub_panel);

		list_panel = new JPanel();
		list_panel.setLayout(new GridLayout(15, 2, 5, 5));
		list_panel.setMaximumSize(new Dimension(400, 450));

		sub_panel.add(list_panel);

		// character name
		charactername_label = new JLabel("Enter a Name");
		list_panel.add(charactername_label);

		charactername_textfield = new JTextField();
		charactername_textfield.setPreferredSize(new Dimension(50, 40));
		charactername_textfield.setAlignmentX(Component.LEFT_ALIGNMENT);
		list_panel.add(charactername_textfield);

		// helmet

		additem_label = new JLabel("Select Helmet");
		list_panel.add(additem_label);
		Object[] helmet = this.items.stream().filter(p -> p.getItem_type().equals(Game_constants.HELMET)).toArray();
		helmet_combobox = new JComboBox(helmet);
		if (helmet.length > 0)
			helmet_combobox.setRenderer(new ItemRenderer());
		helmet_combobox.setAlignmentX(Component.LEFT_ALIGNMENT);
		list_panel.add(helmet_combobox);

		// armer

		additem_label = new JLabel("Select Armer");
		list_panel.add(additem_label);
		Object[] armer = this.items.stream().filter(p -> p.getItem_type().equals(Game_constants.ARMOR)).toArray();
		armer_combobox = new JComboBox(armer);
		if (armer.length > 0)
			armer_combobox.setRenderer(new ItemRenderer());
		armer_combobox.setAlignmentX(Component.LEFT_ALIGNMENT);
		list_panel.add(armer_combobox);

		// shield

		additem_label = new JLabel("Select Shield");
		list_panel.add(additem_label);
		Object[] shields = this.items.stream().filter(p -> p.getItem_type().equals(Game_constants.SHIELD)).toArray();
		shield_combobox = new JComboBox(shields);
		if (shields.length > 0)
			shield_combobox.setRenderer(new ItemRenderer());
		shield_combobox.setAlignmentX(Component.LEFT_ALIGNMENT);
		list_panel.add(shield_combobox);

		// ring

		additem_label = new JLabel("Select Ring");
		list_panel.add(additem_label);
		Object[] rings = this.items.stream().filter(p -> p.getItem_type().equals(Game_constants.RING)).toArray();
		ring_combobox = new JComboBox(rings);
		if (rings.length > 0)
			ring_combobox.setRenderer(new ItemRenderer());
		ring_combobox.setAlignmentX(Component.LEFT_ALIGNMENT);
		list_panel.add(ring_combobox);

		// belt

		additem_label = new JLabel("Select Belt");
		list_panel.add(additem_label);
		Object[] belts = this.items.stream().filter(p -> p.getItem_type().equals(Game_constants.BELT)).toArray();
		belt_combobox = new JComboBox(belts);
		if (belts.length > 0)
			belt_combobox.setRenderer(new ItemRenderer());
		belt_combobox.setAlignmentX(Component.LEFT_ALIGNMENT);
		list_panel.add(belt_combobox);

		// boot

		additem_label = new JLabel("Select Boot");
		list_panel.add(additem_label);
		Object[] boots = this.items.stream().filter(p -> p.getItem_type().equals(Game_constants.BOOTS)).toArray();
		boot_combobox = new JComboBox(boots);
		if (boots.length > 0)
			boot_combobox.setRenderer(new ItemRenderer());
		boot_combobox.setAlignmentX(Component.LEFT_ALIGNMENT);
		list_panel.add(boot_combobox);

		// weapon

		additem_label = new JLabel("Select Weapon");
		list_panel.add(additem_label);
		Object[] weapon = this.items.stream().filter(p -> p.getItem_type().equals(Game_constants.WEAPON)).toArray();
		weapon_combobox = new JComboBox(weapon);
		if (weapon.length > 0)
			weapon_combobox.setRenderer(new ItemRenderer());
		weapon_combobox.setAlignmentX(Component.LEFT_ALIGNMENT);
		list_panel.add(weapon_combobox);

		// list= new JList<String>(names);
		// list.setAlignmentX(Component.LEFT_ALIGNMENT);
		// list.setPreferredSize(new Dimension(200, 200));
		// list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		// list_panel.add(new JScrollPane(list));

		level_label = new JLabel("Enter Level");
		list_panel.add(level_label);

		level_textfield = new JTextField();
		level_textfield.setPreferredSize(new Dimension(50, 40));
		level_textfield.setAlignmentY(LEFT_ALIGNMENT);
		list_panel.add(level_textfield);

		backpack_label = new JLabel("BackPack");
		list_panel.add(backpack_label);

		backpack_combobox = new JComboBox(new String[] { "1", "2" });
		backpack_combobox.setAlignmentX(LEFT_ALIGNMENT);
		list_panel.add(backpack_combobox);

		back = new JButton("Back");
		list_panel.add(back);

		save = new JButton("Save");
		list_panel.add(save);

		// set minimum size of frame
		this.setPreferredSize(new Dimension(300, 600));

		// Display the window.
		this.pack();
		this.setLocationRelativeTo(null);

	}

	@Override
	public void update(Observable arg0, Object arg1) {

	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		// TODO Auto-generated method stub
		this.save.addActionListener(actionListener);
		this.back.addActionListener(actionListener);
	}

	class ItemRenderer extends BasicComboBoxRenderer {
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			ItemModel item = (ItemModel) value;
			setText(item.getItem_name());

			return this;
		}
	}

}
