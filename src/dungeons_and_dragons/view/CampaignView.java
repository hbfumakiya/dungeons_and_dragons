package dungeons_and_dragons.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.google.gson.JsonSyntaxException;

import dungeons_and_dragons.helper.FileHelper;
import dungeons_and_dragons.helper.LogHelper;
import dungeons_and_dragons.model.GameMapModel;

/**
 * @author Hirangi Naik
 *
 */

public class CampaignView extends JFrame implements Observer, View {

	/**
	 * this variable used to set window title
	 * 
	 * @type String
	 */
	private String window_title = "Campaign";
	public JLabel map_label;
	public JComboBox map_combobox;
	private ArrayList<GameMapModel> maps;
	public JButton add;
	public LinkedList<GameMapModel> campaign;

	public CampaignView() {

		this.maps = new ArrayList<GameMapModel>();
		try {
			this.maps = FileHelper.getMaps();
		} catch (JsonSyntaxException | IOException e) {
			// TODO Auto-generated catch block
			LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
		}

		// initialize game window
		this.initializeWindow();
		// close frame while user click on close
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initializeWindow() {

		// set window title
		this.setTitle(this.window_title);
		this.setPreferredSize(new Dimension(320, 510));
		this.setResizable(false);
		this.setLayout(null);

		map_label = new JLabel("Select Map");
		map_label.setBounds(10, 10, 100, 25);
		this.add(map_label);

		GameMapModel[] map_names = new GameMapModel[this.maps.size()];
		//Object[] names = (Object[]) new Object();
		Integer[] id=new Integer[this.maps.size()];
		for (int i = 0; i < this.maps.size(); i++) {
			map_names[i] = this.maps.get(i);
			id[i]=map_names[i].getMap_id();
			//names[i] = map_names[i].getMap_name();
		}
		map_combobox = new JComboBox<Integer>(id);
		map_combobox.setBounds(150, 10, 150, 25);
		this.add(map_combobox);

		/*String[] names={"1","2"};
		map_combobox = new JComboBox<String>(names);
		map_combobox.setBounds(150, 10, 150, 25);
		this.add(map_combobox);
		// this.setMinimumSize(new Dimension(300, 300));
*/
		add=new JButton("Add to Campaign");
		add.setBounds(50, 50, 150, 25);
		this.add(add);
		
		// Display the window.
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		// TODO Auto-generated method stub
		//this.add.addActionListener(actionListener);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

}
