/**
 * 
 */
package dungeons_and_dragons.view;



import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * 
 * @author Mihir Pujara
 *
 */

public class GameView extends JFrame implements View {

	/**
	 * this variable used to set window title
	 * 
	 * @type String
	 */
	private String window_title = "Game Window";
	
	/**
	 * this variable used for new game button
	 * 
	 * @type JButton
	 */
	public JButton new_game_button;
	
	/**
	 * this variable used for load game button
	 * 
	 * @type JButton
	 */
	public JButton load_game_button;
	
	/**
	 * this variable used for create game button
	 * 
	 * @type JButton
	 */
	public JButton create_game_button;
	
	/**
	 * this variable used for exit game button
	 * 
	 * @type JButton
	 */
	public JButton exit_game_button;
	
	/**
	 *  constructor 
	 * 
	 */
	public GameView() {
		
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
		new_game_button = new JButton("New Game");
		new_game_button.setAlignmentX(Component.CENTER_ALIGNMENT);
		

		// add button to panel
		listPane.add(new_game_button);
		
		
		//put space between two components
		listPane.add(Box.createVerticalStrut(5));
		
		
		// create & Load Game button, set button alignment center  
		load_game_button = new JButton("Load Game");
		load_game_button.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		
		// add button to panel
		listPane.add(load_game_button);
		
		
		//put space between two components
		listPane.add(Box.createVerticalStrut(5));
		
		
		// create & Load Game button, set button alignment center  
		create_game_button = new JButton("Create Game");
		create_game_button.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// add button to panel
		listPane.add(create_game_button);
				
		
		//put space between two components
		listPane.add(Box.createVerticalStrut(5));
		
		
		// create & Load Game button, set button alignment center  
		exit_game_button = new JButton("Exit");
		exit_game_button.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		// add button to panel
		listPane.add(exit_game_button);
				

		//Put panel into frame, using the content pane's BoxLayout.
		Container contentPane = getContentPane();
		contentPane.add(listPane, BorderLayout.CENTER);
		
		// set minimum size of frame 
		this.setMinimumSize(new Dimension(300, 200));
		
		//Display the window.
        this.pack();
        this.setLocationRelativeTo(null);
	}
	
	
	/**
	 * 
	 */
	@Override
	public void setActionListener(ActionListener actionListener) {
	
		// set action listener for new game button
		new_game_button.addActionListener(actionListener);
		
		// set action listener for new game button
		load_game_button.addActionListener(actionListener);
		
		// set action listener for new game button
		create_game_button.addActionListener(actionListener);
		
		// set action listener for new game button
		exit_game_button.addActionListener(actionListener);
	}
}


