/**
 * 
 */
package dungeons_and_dragons.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.ScrollPaneConstants;

import dungeons_and_dragons.model.ItemModel;

/**
 * @author Hirangi Naik
 *
 */
public class NPCItemView extends JFrame implements View {

	private JPanel panel;
	public ArrayList<ItemModel> items;
	public JButton okButton;
	public ItemModel[] itemtodisp;
	public DefaultListModel<String> fruitsName;
	public JList<ItemModel> itemList;

	/**
	 * 
	 */
	public NPCItemView(ArrayList<ItemModel> items) {
		this.items = items;
		this.setTitle("Character Inventory");

		this.initilizeView();

		this.setResizable(false);

		this.pack();
		this.setLocationRelativeTo(null);
	}

	private void initilizeView() {
		this.panel = new JPanel();
		this.panel.setLayout(null);
		this.panel.setPreferredSize(new Dimension(300, 370));
		fruitsName = new DefaultListModel<>();
		this.itemtodisp = new ItemModel[items.size()];
		
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i) != null) {

				if(items.get(i) != null)
					itemtodisp[i] = items.get(i);
			}
		}
		this.itemList = new JList<ItemModel>(itemtodisp);
		this.itemList.setCellRenderer(new ItemCellRenderer());
		JScrollPane plainScroll = new JScrollPane(this.itemList);
		plainScroll.setBounds(10, 10, 280, 300);
		this.panel.add(plainScroll);
		this.okButton = new JButton("OK");
		this.okButton.setBounds(10, 320, 280, 30);
		this.panel.add(this.okButton);

		this.getContentPane().add(this.panel);

	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.okButton.addActionListener(actionListener);
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

}
