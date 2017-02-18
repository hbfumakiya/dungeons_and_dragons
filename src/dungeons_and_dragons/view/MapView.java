/**
 * 
 */
package dungeons_and_dragons.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dungeons_and_dragons.controller.MapFormController;

/**
 * @author User
 *
 */
public class MapView extends JFrame implements Observer {

	/**
	 * this variable used to set window title
	 * 
	 * @type String
	 */
	public String map_window_title = "Creating Map";

	/**
	 * this variable used to give Item name
	 * 
	 * @type JLabel
	 */
	public JLabel map_name;

	/**
	 * this variable used to input name
	 * 
	 * @type JTextField
	 */
	public JTextField map_name_textfield;
	
	/**
	 * this variable used to give map width
	 * 
	 * @type JLabel
	 */
	public JLabel map_width;

	/**
	 * this variable used to input height
	 * 
	 * @type JTextField
	 */
	public JTextField map_width_textfield;
	
	/**
	 * this variable used to give map height
	 * 
	 * @type JLabel
	 */
	public JLabel map_height;

	/**
	 * this variable used to input height
	 * 
	 * @type JTextField
	 */
	public JTextField map_height_textfield;
	
	/**
	 * this variable used for going back to create map window
	 * 
	 * @type JButton
	 */
	public JButton back_button;

	/**
	 * this variable used to save map
	 * 
	 * @type JButton
	 */
	public JButton next_button;
	
	private JPanel main_panel;
	private JPanel sub_panel;
	
	public MapView(Point store){
		this.setTitle(this.map_window_title);

		// main panel
		main_panel = new JPanel();
		main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.PAGE_AXIS));
		main_panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		getContentPane().add(main_panel);

		// sub panel
		sub_panel = new JPanel();
		sub_panel.setLayout(new BoxLayout(sub_panel, BoxLayout.LINE_AXIS));
		sub_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		sub_panel.setMaximumSize(new Dimension(300, 1000));

		main_panel.add(sub_panel);
		// Lay out the label and scroll pane from top to bottom.
		JPanel listPane = new JPanel();

		listPane.setLayout((new GridLayout(4, 2, 5, 5)));
		listPane.setMaximumSize(new Dimension(300, 150));

		sub_panel.add(listPane);
		// listPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Initializing all labels
		map_name = new JLabel("Name");
		map_height = new JLabel("Height");
		map_width = new JLabel("Width");
		//item_score = new JLabel("Point");

		// Initializing label text field,dropdown of map height and width		
		map_name_textfield = new JTextField(1);
		map_height_textfield = new JTextField(1);
		map_width_textfield = new JTextField(1);


		// initializing back and next buttons
		back_button = new JButton("Back");
		next_button = new JButton("Save");

	

		// Adding necessary components into panel
		listPane.add(map_name);

		listPane.add(map_name_textfield);

		listPane.add(map_height);

		listPane.add(map_height_textfield);

		listPane.add(map_width);

		listPane.add(map_width_textfield);

		

		listPane.add(back_button);
		listPane.add(next_button);

		this.setPreferredSize(new Dimension(320, 220));

		// Display the window.
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

	public void setListener(MapFormController mapFormController) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	

}
