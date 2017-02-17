package dungeons_and_dragons.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * @author Mihir Pujara
 *
 */
public class CreateGameView extends JFrame implements ActionListener {

	/**
	 * this variable used to set window title
	 * 
	 * @type String
	 */
	private String window_title = "Create Game";
	
	/**
	 * this variable used for manage character button
	 * 
	 * @type JButton
	 */
	private JButton manage_character_button;
	
	/**
	 * this variable used for manage map button
	 * 
	 * @type JButton
	 */
	private JButton manage_map_button;
	
	/**
	 * this variable used for manage campaign button
	 * 
	 * @type JButton
	 */
	private JButton manage_campaign_button;
	
	/**
	 * this variable used for manage item button
	 * 
	 * @type JButton
	 */
	private JButton manage_item_button;
	
	/**
	 * this variable used for back to game button
	 * 
	 * @type JButton
	 */
	private JButton back_button;
	
	/**
	 * 
	 */
	public CreateGameView() {
		
		// initialize game window
		this.initializeWindow();
		
		//close frame while user click on close
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	/**
	 * initialize game window
	 * 
	 */
	private void initializeWindow() {
		
		// set window title
		this.setTitle(this.window_title);
		
		//Lay out the label and scroll pane from top to bottom.
		JPanel listPane = new JPanel();
		
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
		listPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
				
		// create  New Game button, set button alignment center  
		manage_character_button = new JButton("Manage Character");
		manage_character_button.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// set action listener for new game button
		manage_character_button.addActionListener(this);
		
		// add button to panel
		listPane.add(manage_character_button);
		
		
		//put space between two components
		listPane.add(Box.createVerticalStrut(5));
		
		
		// create & Load Game button, set button alignment center  
		manage_map_button = new JButton("Manage Map");
		manage_map_button.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// set action listener for new game button
		manage_map_button.addActionListener(this);
		
		// add button to panel
		listPane.add(manage_map_button);
		
		
		//put space between two components
		listPane.add(Box.createVerticalStrut(5));
		
		
		// create & Load Game button, set button alignment center  
		manage_campaign_button = new JButton("Manage Campaign");
		manage_campaign_button.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// set action listener for new game button
		manage_campaign_button.addActionListener(this);
		
		// add button to panel
		listPane.add(manage_campaign_button);
				
		
		//put space between two components
		listPane.add(Box.createVerticalStrut(5));
		
		
		// create & Load Game button, set button alignment center  
		manage_item_button = new JButton("Manage Item");
		manage_item_button.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// set action listener for new game button
		manage_item_button.addActionListener(this);
		
		// add button to panel
		listPane.add(manage_item_button);
		
		
		//put space between two components
		listPane.add(Box.createVerticalStrut(5));
		
		
		// create & Load Game button, set button alignment center  
		back_button = new JButton("Back");
		back_button.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// set action listener for new game button
		back_button.addActionListener(this);
		
		// add button to panel
		listPane.add(back_button);
		

		//Put panel into frame, using the content pane's BoxLayout.
		Container contentPane = getContentPane();
		contentPane.add(listPane, BorderLayout.CENTER);
		
		// set minimum size of frame 
		this.setMinimumSize(new Dimension(300, 300));
		
		//Display the window.
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		
		
		// check which button clicked by user
		if(actionEvent.getSource().equals(this.manage_character_button)) {
		
			// open manage character window
			this.showManageCharacterWindow();
			
		} else if(actionEvent.getSource().equals(this.manage_map_button)) {

			// open manage map window			
			this.showManageMapWindow();
			
		} else if(actionEvent.getSource().equals(this.manage_campaign_button)) {
			
			// open manage campaign window			
			this.showManageCampaignWindow();
			
		} else if(actionEvent.getSource().equals(this.manage_item_button)) {

			// open manage item window			
			this.showManageItemWindow();
			
		} else if(actionEvent.getSource().equals(this.back_button)) { 

			// back to game and open main game window			
			this.backToGame();
		}
	}
	
	
	/**
	 * method for show manage character window
	 */
	private void showManageCharacterWindow() {
		
		// show manage character view
		new ManageCharacterView();
		
		// hide window
		this.dispose();
	}
	
	/**
	 * method for show manage map window
	 */
	private void showManageMapWindow() {
		
		// hide game window
		// this.dispose();
		
		// show load game window....
		
	}
	
	/**
	 * method for show manage item window
	 */
	private void showManageItemWindow() {
		
		// hide game window
		// this.dispose();
		
		// show Item game window....
		new ItemView();
		
		this.dispose();
		
	}
	
	/**
	 * method for show create game window
	 */
	private void showManageCampaignWindow() {
		
		// hide game window
		// this.dispose();
		
		// show create game window....
		
	}
		
	/**
	 * method for show exit game
	 */
	private void backToGame() {
		
		//open game window
		new GameView();
		
		this.dispose();
	}

}

