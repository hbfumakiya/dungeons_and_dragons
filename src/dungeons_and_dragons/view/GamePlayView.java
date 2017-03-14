package dungeons_and_dragons.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.google.gson.JsonSyntaxException;

import dungeons_and_dragons.controller.GamePlayController;
import dungeons_and_dragons.helper.FileHelper;
import dungeons_and_dragons.model.CampaignModel;
import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.view.MapGridView.MapViewRenderer;

/**
 * Renders the GamePlayModel into a form suitable for visualization or
 * interaction, typically a user interface element.
 * 
 * @author Urmil Kansara & Tejas Sadrani
 *
 */
public class GamePlayView extends JFrame implements Observer {

	/**
	 * this variable used to set window title
	 * 
	 * @type String
	 */
	public String map_window_title = "Play Game";
	
	JPanel main_panel;
	JPanel left_panel;
	JPanel left_sub_panel;
	JPanel right_panel;
	JPanel right_sub_top_panel;
	JPanel right_sub_bottom_panel;
	
	JLabel campaign_name_label;
	JLabel map_name_label;
	JLabel campaign_name;
	JLabel map_name;
	
	JTextArea console_text_area;
	
	/**
	 * This creates new campaign model which is being observed
	 * 
	 * @type GameMapModel
	 */
	CampaignModel campaignModel;
	
	/**
	 * This create a new character model object which is an observer
	 * 
	 * @type MapGridView
	 */
	CharacterModel charachterModel;

	
	/**
	 * This Constructor initialized view of game play model
	 * @param charachterModel 
	 * @param campaignModel 
	 */
	public GamePlayView(CampaignModel campaignModel, CharacterModel charachterModel) {

		this.campaignModel = campaignModel;
		this.charachterModel = charachterModel;
		
		this.setTitle(this.map_window_title);
		// main panel covering body
		main_panel = new JPanel();
		main_panel.setLayout(new GridLayout(1,2,1000,1000));
		//main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.PAGE_AXIS));
		//main_panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		getContentPane().add(main_panel);
		
		// for console
		left_panel = new JPanel();
		left_panel.setLayout(new BoxLayout(left_panel, BoxLayout.PAGE_AXIS));
		left_panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		// heading console
		left_sub_panel = new JPanel();
		left_sub_panel.setLayout(new BoxLayout(left_sub_panel, BoxLayout.Y_AXIS));
		left_sub_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		//left_sub_top_panel.setMaximumSize(new Dimension(400, 100));
		
		/*// list panel to display contents inside the sub top panel
		JPanel left_list_panel = new JPanel();

		left_list_panel.setLayout((new GridLayout(1,1, 5, 5)));
		left_list_panel.setMaximumSize(new Dimension(400, 150));*/

		console_text_area = new JTextArea();
		
		
		//error with import of textareaoutputstream
	   /* TextAreaOutputStream console_text_area_stream = new TextAreaOutputStream( console_text_area, 60 );
	    ps = new PrintStream( taos );
	    System.setOut( ps );
	    System.setErr( ps );*/
	    
	    left_sub_panel.add(console_text_area);
	    left_panel.add(left_sub_panel);
	    
	     // for map view on the right hand side
 		right_panel = new JPanel();
 		right_panel.setLayout(new BoxLayout(right_panel, BoxLayout.PAGE_AXIS));
 		right_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
 		
 		// heading for map
		right_sub_top_panel = new JPanel();
		right_sub_top_panel.setLayout(new BoxLayout(right_sub_top_panel, BoxLayout.X_AXIS));
		right_sub_top_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		// list panel to display contents inside the sub top panel
		JPanel listPane = new JPanel();

		listPane.setLayout((new GridLayout(2, 2, 5, 5)));
		listPane.setMaximumSize(new Dimension(400, 150));

		right_sub_top_panel.add(listPane);
		listPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Initializing all labels
		campaign_name_label = new JLabel("Campaign -- >");
		map_name_label = new JLabel("Map -- >");
		campaign_name = new JLabel(this.campaignModel.getCampaign_name());
		map_name = new JLabel(this.charachterModel.getCharacter_name());

		listPane.add(campaign_name_label);
		listPane.add(campaign_name);
		listPane.add(map_name_label);
		listPane.add(map_name);

		right_panel.add(right_sub_top_panel);

		//pending to be made
		right_sub_bottom_panel = new JPanel();
		right_panel.add(right_sub_bottom_panel);
		
		main_panel.add(left_panel);
		main_panel.add(right_panel);
		
		this.setPreferredSize(new Dimension(1000, 1000));

		// Display the window.
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	public void setListener(GamePlayController gamePlayController) {
		// TODO Auto-generated method stub
		
	}
	
	
}
