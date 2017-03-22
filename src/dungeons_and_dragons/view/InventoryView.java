/**
 * 
 */
package dungeons_and_dragons.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
 * @author Mihir Pujara
 *
 */
public class InventoryView extends JFrame implements View {

	
	public CharacterModel character;
	
	public boolean isReadOnly;
	
	private JPanel panel;

	public JList<ItemModel> itemList;

	public JList<ItemModel> backPackList;

	public JButton moveFromItemToBack;

	public JButton moveFromBackToItem;

	public JButton okButton;
	
	public JScrollPane backPackScrollPane;

	public JScrollPane itemScrollPane;
	
	private JLabel itemLabel;
	
	private JLabel backPackLabel;
	/**
	 * Constructor to initialize variables and objects
	 */
	public InventoryView(CharacterModel character,boolean isReadOnly, ActionListener actionListener) {
		this.character = character;
		
		this.isReadOnly = isReadOnly;
		
		this.setTitle(this.character.getCharacter_name()+" Inventory");
		
		this.initilizeView();

		this.setResizable(false);

		this.pack();
		this.setLocationRelativeTo(null);
	}
	/**
	 * initialize view of inventory
	 */
	public void initilizeView() {

		this.panel = new JPanel();
		this.panel.setLayout(null);
		this.panel.setPreferredSize(new Dimension(500, 300));

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
		this.itemScrollPane.setBounds(10, 30, 200, 200);
		this.panel.add(this.itemScrollPane);
		
		// back pack Label 
		this.itemLabel = new JLabel("Items");
		this.itemLabel.setBounds(10, 10, 200, 20);
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
		this.backPackScrollPane.setBounds(290, 30, 200, 200);
		this.panel.add(this.backPackScrollPane);
		
		
		// back pack Label 
		this.backPackLabel = new JLabel("Backpack Items");
		this.backPackLabel.setBounds(290, 10, 200, 20);
		this.panel.add(this.backPackLabel);

		// move to item to backpack >

		this.moveFromItemToBack = new JButton(">");
		this.moveFromItemToBack.setBounds(225, 40, 50, 50);

		this.panel.add(this.moveFromItemToBack);

		// move to backpack to item <

		this.moveFromBackToItem = new JButton("<");
		this.moveFromBackToItem.setBounds(225, 150, 50, 50);

		this.panel.add(this.moveFromBackToItem);

		// ok button

		this.okButton = new JButton("OK");
		this.okButton.setBounds(217, 260, 65, 30);

		this.panel.add(this.okButton);

		this.getContentPane().add(this.panel);
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
}
