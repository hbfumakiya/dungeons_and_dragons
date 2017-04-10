/**
 * 
 */
package dungeons_and_dragons.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.google.gson.JsonSyntaxException;

import dungeons_and_dragons.helper.LogHelper;
import dungeons_and_dragons.model.CampaignModel;
import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.model.GamePlayModel;
import dungeons_and_dragons.view.NewGameView;

/**
 * @author Mihir Pujara & Hirangi Naik
 *
 */
public class NewGameController implements ActionListener {

	private GamePlayModel gamePlayModel;

	private NewGameView newGameView;

	/**
	 * Constructor for new game
	 */
	public NewGameController() {

		this.gamePlayModel = new GamePlayModel();

		this.newGameView = new NewGameView();

		this.newGameView.setActionListener(this);

		CharacterModel characterModel = new CharacterModel();

		ArrayList<CharacterModel> characters;
		try {
			characters = characterModel.getData();

			if (characters != null && characters.size() > 0) {

				CampaignModel campaignModel = new CampaignModel();

				ArrayList<CampaignModel> campaigns = campaignModel.getData();

				if (campaigns != null && campaigns.size() > 0) {

					this.newGameView.setVisible(true);

				} else {

					JOptionPane.showMessageDialog(this.newGameView, "No Champaign found. Create it to play game",
							"Error", JOptionPane.ERROR_MESSAGE);

					this.newGameView.dispose();

					new GameController();
				}
			} else {

				JOptionPane.showMessageDialog(this.newGameView, "No character found. Create it to play game", "Error",
						JOptionPane.ERROR_MESSAGE);

				this.newGameView.dispose();

				new GameController();
			}
		} catch (JsonSyntaxException | IOException e) {
			LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
		}

	}
	
	/**
	 * ALl the events for back button,play button are handled here
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(this.newGameView.backButton)) {

			this.backToGame();

		} else if (e.getSource().equals(this.newGameView.playButton)) {

			this.gamePlayModel.setCharacterModel((CharacterModel) this.newGameView.characterList.getSelectedItem());

			this.gamePlayModel.setCampaignModel((CampaignModel) this.newGameView.campaignList.getSelectedItem());

			new GamePlayController(this.gamePlayModel);

			this.newGameView.dispose();
		}
	}

	/**
	 * method for show exit game
	 */
	private void backToGame() {

		// open game window
		new GameController();

		this.newGameView.dispose();
	}

}
