/**
 * 
 */
package dungeons_and_dragons.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import dungeons_and_dragons.model.CampaignModel;
import dungeons_and_dragons.model.ItemModel;

/**
 * @author Mihir Pujara
 *
 */
public class NewGameView extends JFrame {

	private String windowTitle = "New Game";
	
	public JLabel characterLabel;
	
	private JLabel characterList;
	
	public JLabel campaignLabel;
	
	private JLabel campaignList;
	
	private JButton playButton;

	/**
	 * 
	 */
	public NewGameView() {

		// initialize game window
		this.initializeWindow();

		// close frame while user click on close
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void initializeWindow() {

		// set window title
		this.setTitle(this.windowTitle);

		// Lay out the label and scroll pane from top to bottom.
		JPanel listPane = new JPanel();

		listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
		listPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// create New Game button, set button alignment center
		playButton = new JButton("Play");
		playButton.setAlignmentX(Component.LEFT_ALIGNMENT);

		// add button to panel
		listPane.add(playButton);

		// put space between two components
		listPane.add(Box.createVerticalStrut(5));

		// Put panel into frame, using the content pane's BoxLayout.
		Container contentPane = getContentPane();
		contentPane.add(listPane);

		// set minimum size of frame
		this.setMinimumSize(new Dimension(300, 300));

		// Display the window.
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

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

	class CampaignRenderer extends BasicComboBoxRenderer {

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			if (value != null) {
				CampaignModel item = (CampaignModel) value;
				setText(item.getCampaign_name());
			} else {
				setText("");
			}

			return this;
		}
	}

}
