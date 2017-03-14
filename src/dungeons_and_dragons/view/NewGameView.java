/**
 * 
 */
package dungeons_and_dragons.view;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import com.google.gson.JsonSyntaxException;

import dungeons_and_dragons.helper.LogHelper;
import dungeons_and_dragons.model.CampaignModel;
import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.model.ItemModel;

/**
 * @author Mihir Pujara
 *
 */
public class NewGameView extends JFrame implements View {

	private String windowTitle = "New Game";

	public JLabel characterLabel;

	public JComboBox<CharacterModel> characterList;

	public JLabel campaignLabel;

	public JComboBox<CampaignModel> campaignList;

	public JButton playButton;

	public JButton backButton;

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

		// create Character Label
		characterLabel = new JLabel("Select Character");
		characterLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

		// add label to panel
		listPane.add(characterLabel);

		// put space between two components
		listPane.add(Box.createVerticalStrut(5));

		// create dropdown for characters
		characterList = new JComboBox<CharacterModel>();
		characterList.setAlignmentX(Component.LEFT_ALIGNMENT);

		this.fillCharacters(this.characterList);

		// add dropdowm to panel
		listPane.add(characterList);

		// put space between two components
		listPane.add(Box.createVerticalStrut(5));

		// create Campaign Label
		campaignLabel = new JLabel("Select Campaign");
		campaignLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

		// add label to panel
		listPane.add(campaignLabel);

		// put space between two components
		listPane.add(Box.createVerticalStrut(5));

		// create dropdown for campaign
		campaignList = new JComboBox<CampaignModel>();
		campaignList.setAlignmentX(Component.LEFT_ALIGNMENT);

		this.fillCampaigns(this.campaignList);

		// add dropdowm to panel
		listPane.add(campaignList);

		// put space between two components
		listPane.add(Box.createVerticalStrut(5));

		// create Play button
		playButton = new JButton("Play");
		playButton.setAlignmentX(Component.LEFT_ALIGNMENT);

		// add button to panel
		listPane.add(playButton);

		// put space between two components
		listPane.add(Box.createVerticalStrut(5));

		// create Back button
		backButton = new JButton("Back");
		backButton.setAlignmentX(Component.LEFT_ALIGNMENT);

		// add button to panel
		listPane.add(backButton);

		// Put panel into frame, using the content pane's BoxLayout.
		Container contentPane = getContentPane();
		contentPane.add(listPane);

		// set minimum size of frame
		this.setMinimumSize(new Dimension(300, 180));

		this.setResizable(false);

		// Display the window.
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	private void fillCampaigns(JComboBox<CampaignModel> campaignList2) {
		CampaignModel campaignModel = new CampaignModel();

		try {
			ArrayList<CampaignModel> campaigns = campaignModel.getData();

			if (campaigns != null && campaigns.size() > 0) {
				for (int i = 0; i < campaigns.size(); i++) {
					if (campaigns.get(i) != null) {
						campaignList2.addItem(campaigns.get(i));
					}
				}
			}
			campaignList2.setRenderer(new CampaignRenderer());
		} catch (JsonSyntaxException | IOException e) {
			LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
		}
	}

	private void fillCharacters(JComboBox<CharacterModel> characterList2) {

		CharacterModel characterModel = new CharacterModel();

		try {
			ArrayList<CharacterModel> characters = characterModel.getData();

			if (characters != null && characters.size() > 0) {
				for (int i = 0; i < characters.size(); i++) {
					if (characters.get(i) != null) {
						characterList2.addItem(characters.get(i));
					}
				}
			}
			characterList2.setRenderer(new CharacterRenderer());
		} catch (JsonSyntaxException | IOException e) {
			LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
		}
	}

	class CharacterRenderer extends BasicComboBoxRenderer {

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			if (value != null) {
				CharacterModel item = (CharacterModel) value;
				setText(item.getCharacter_name());
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

	@Override
	public void setActionListener(ActionListener actionListener) {
		
		this.playButton.addActionListener(actionListener);
		
		this.backButton.addActionListener(actionListener);

	}

}
