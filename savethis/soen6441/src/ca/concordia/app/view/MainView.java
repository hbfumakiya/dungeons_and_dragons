package ca.concordia.app.view;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JButton;

public class MainView extends JFrame implements IView{


	public JButton new_game,create_map,edit_map;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
			
		
		MainView window = new MainView();
		window.setVisible(true);
	
	}

	/**
	 * Create the application.
	 */
	public MainView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		this.setBounds(100, 100, 1280, 763);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		new_game = new JButton("New Game");
		new_game.setBounds(167, 185, 213, 109);
		this.getContentPane().add(new_game);
		
		create_map = new JButton("Create Map");
		create_map.setBounds(448, 185, 199, 109);
		this.getContentPane().add(create_map);
		
		edit_map = new JButton("Edit Map");
		edit_map.setBounds(716, 185, 213, 109);
		this.getContentPane().add(edit_map);
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		new_game.addActionListener(actionListener);
		create_map.addActionListener(actionListener);
		edit_map.addActionListener(actionListener);
	}

	@Override
	public void setMouseListener(MouseListener mouseListener) {
		// TODO Auto-generated method stub
		
	}

}
