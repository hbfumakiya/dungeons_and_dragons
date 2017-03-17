/**
 * 
 */
package dungeons_and_dragons.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
	public JTextArea itemTextArea;

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
		this.panel.setPreferredSize(new Dimension(200, 420));
		fruitsName = new DefaultListModel<>();
		this.itemtodisp = new ItemModel[items.size()];
		this.itemTextArea = new JTextArea();
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i) != null) {

				itemtodisp[i] = items.get(i);
				itemTextArea.append(itemtodisp[i].getItem_name() + "\n");
			}
		}

		this.itemTextArea.setBounds(10, 10, 120, 350);
		JScrollPane plainScroll = new JScrollPane(itemTextArea);
		plainScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.panel.add(plainScroll);
		this.panel.add(itemTextArea);
		this.okButton = new JButton("OK");
		this.okButton.setBounds(10, 360, 65, 30);
		this.panel.add(this.okButton);

		this.getContentPane().add(this.panel);

	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.okButton.addActionListener(actionListener);
	}

}
