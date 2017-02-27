package dungeons_and_dragons.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import dungeons_and_dragons.model.CampaignModel;
import dungeons_and_dragons.model.GameMapModel;
import dungeons_and_dragons.model.ItemModel;
import dungeons_and_dragons.view.CharacterInventoryView.ItemCellRenderer;

/**
 * @author Hirangi Naik and Tejas Sadrani
 *
 */

public class CampaignView extends JFrame implements Observer, View {

	/**
	 * this variable used to set window title
	 * 
	 * @type String
	 */
	private String window_title = "Create Campaign";
	public JLabel campaign_label;
	public JComboBox campaign_combobox;
	private ArrayList<GameMapModel> gameMapModel_map_list;
	private Object[] campaign_array;
	public JButton campaign_add;
	public ArrayList<GameMapModel> campaign_map_list = new ArrayList<>();
	public BufferedImage arrow_image;
	private JPanel main_panel;

	// map J list for game map model
	public JList<GameMapModel> output_map_list;
	public JScrollPane mapScrollPane;
	private CampaignViewRenderer campaign_view_renderer;
	ArrayList<GameMapModel> map;
	public JButton moveMapUP;
	public JButton moveMapDown;
	public JButton removeMap;
	public JButton save_button;
	public JButton update_button;
	public JLabel campaign_name_label;
	public JTextField campaign_name_text;
	public JButton back_button;
	public int restrict_renderer = 0;
	public JLabel[] campaign_maps_label;
	public JLabel campaign_arrow;

	public boolean isView = false;
	public boolean isEdit = false;

	/**
	 * Constructor of Campaign View class
	 * 
	 * @param map_list
	 *            returns list of maps that are already defined in map view
	 */
	public CampaignView(ArrayList<GameMapModel> map_list) {

		this.gameMapModel_map_list = map_list;

		// Initialize Campaign window
		this.setTitle(this.window_title);
		this.initializeView();

		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}

	/**
	 * Used to initialize the campaign view
	 */
	private void initializeView() {

		this.main_panel = new JPanel();
		this.main_panel.setLayout(null);
		this.main_panel.setPreferredSize(new Dimension(500, 400));

		this.output_map_list = new JList<GameMapModel>();

		// label asking to select a map for a campaign
		campaign_name_label = new JLabel("Campaign name");
		campaign_name_label.setBounds(100, 10, 100, 25);
		this.main_panel.add(campaign_name_label);

		// text field to input the name
		campaign_name_text = new JTextField();
		campaign_name_text.setBounds(200, 10, 100, 25);
		this.main_panel.add(campaign_name_text);

		// label asking to select a map for a campaign
		campaign_label = new JLabel("Select Map");
		campaign_label.setBounds(100, 50, 100, 25);
		this.main_panel.add(campaign_label);

		campaign_view_renderer = new CampaignViewRenderer();
		// Combo-box for selecting a particular map from list of maps available
		// in the gameMapModel
		campaign_array = this.gameMapModel_map_list.toArray();
		campaign_combobox = new JComboBox(campaign_array);

		if (campaign_array.length > 0) {
			campaign_combobox.setRenderer(campaign_view_renderer);
		}
		/*
		 * else{ campaign_combobox.disable(); }
		 */
		campaign_combobox.setBounds(200, 50, 150, 25);
		this.main_panel.add(campaign_combobox);

		// Add button to add one map in a campaign
		campaign_add = new JButton("Add Map to Campaign");
		campaign_add.setBounds(150, 95, 170, 25);
		this.main_panel.add(campaign_add);

		this.add(this.main_panel);

		// move selected map up

		this.moveMapUP = new JButton("\u2191"); // \u2191 unicode for up key
		this.moveMapUP.setBounds(350, 170, 50, 30);

		this.main_panel.add(this.moveMapUP);

		// move selected map down

		this.removeMap = new JButton("X"); // \u2193 unicode for down key
		this.removeMap.setBounds(350, 225, 50, 30);

		this.main_panel.add(this.removeMap);

		// move selected map down

		this.moveMapDown = new JButton("\u2193"); // \u2193 unicode for down key
		this.moveMapDown.setBounds(350, 280, 50, 30);

		this.main_panel.add(this.moveMapDown);

		// back button to go back to manage campaign view

		this.back_button = new JButton("back");
		this.back_button.setBounds(150, 360, 65, 30);
		this.main_panel.add(this.back_button);

		// Save button to save the campaigns containing defined maps

		this.save_button = new JButton("Save");
		this.save_button.setBounds(250, 360, 65, 30);
		this.main_panel.add(this.save_button);

		// update button to update the campaigns containing defined maps

		this.update_button = new JButton("Update");
		this.update_button.setBounds(250, 360, 80, 30);
		this.main_panel.add(this.update_button);

		this.mapScrollPane = new JScrollPane(this.output_map_list);
		this.mapScrollPane.setBounds(142, 140, 200, 200);
		this.main_panel.add(this.mapScrollPane);

	}

	public void updateWindow(ArrayList<GameMapModel> temp) {

		if (this.isView) {
			
			// Need to implement a scroll for future -- >> JScrollPane scrollPanel = new JScrollPane(t);
			JPanel view_panel = new JPanel();
			view_panel.setBounds(200, 10, 400, 400);
			view_panel.setLayout(null);
			
			campaign_maps_label = new JLabel[temp.size() * 2];
			campaign_maps_label = new JLabel[temp.size()];

			try {
				arrow_image = ImageIO.read(new File("res/down_image.png"));
			} catch (Exception e) {

			}

			for (int i = 0; i < temp.size(); i++) {

				campaign_maps_label[i] = new JLabel((temp.get(i).getMap_name()));
				campaign_maps_label[i].setBounds(10, 50*(i+1) , 100, 20);
				
				campaign_maps_label[i].setForeground(Color.BLUE);

				view_panel.add(campaign_maps_label[i]);

				if (i != 0) {
					campaign_arrow = new JLabel("\u2193");		//new ImageIcon(arrow_image)
					campaign_arrow.setBounds(25, 50*(i+1)-25, 50, 30);
					view_panel.add(campaign_arrow);
				}

			}
			this.main_panel.add(view_panel);
		}else{
			
			//remove items already present in the bottom list
			for (int i = 0; i < temp.size(); i++) {
				System.out.println(temp.get(i));
				System.out.println(campaign_combobox.equals(temp.get(i)));
				campaign_combobox.removeItem(temp.get(i));
			}

			//when pressed upon remove map
			if (this.removeMap.isEnabled()) {
				campaign_array = this.gameMapModel_map_list.toArray();
				campaign_combobox.removeAllItems();
				for (int i = 0; i < this.campaign_array.length; i++) {
					campaign_combobox.addItem(this.campaign_array[i]);
				}
			}
			
			//generating new list of maps to be viewed
			this.output_map_list.removeAll();

			this.map = new ArrayList<GameMapModel>();

			if ((temp != null) && (temp.size() > 0)) {

				for (int i = 0; i < temp.size(); i++) {
					if (temp.get(i) != null)
						map.add(temp.get(i));
				}
			}

			if ((map != null) || (map.size() > 0)) {

				GameMapModel[] maps = new GameMapModel[map.size()];

				for (int i = 0; i < map.size(); i++) {
					if (map.get(i) != null)
						maps[i] = map.get(i);
				}

				this.output_map_list.setListData(maps);
				this.output_map_list.setCellRenderer(new CampaignCellRenderer());
				this.output_map_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			}
		}
	}

	/**
	 * Invoked upon by Game map Model(observer) object once the data in width
	 * and height of first game view is set
	 */
	@Override
	public void update(Observable obs, Object obj) {

		campaign_map_list = ((CampaignModel) obs).getOutput_map_list();
		this.gameMapModel_map_list = ((CampaignModel) obs).getInput_map_list();

		this.updateWindow(campaign_map_list);

	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.campaign_add.addActionListener(actionListener);
		this.moveMapUP.addActionListener(actionListener);
		this.moveMapDown.addActionListener(actionListener);
		this.removeMap.addActionListener(actionListener);
		this.back_button.addActionListener(actionListener);
		this.save_button.addActionListener(actionListener);
		this.update_button.addActionListener(actionListener);
	}

	public class CampaignViewRenderer extends BasicComboBoxRenderer {
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			GameMapModel map_model = (GameMapModel) value;
			if (map_model != null)
				setText(map_model.getMap_name());

			return this;
		}
	}

	class CampaignCellRenderer extends JLabel implements ListCellRenderer<GameMapModel> {

		private static final long serialVersionUID = 1L;

		public CampaignCellRenderer() {
			setOpaque(true);
		}

		@Override
		public Component getListCellRendererComponent(JList<? extends GameMapModel> arg0, GameMapModel arg1, int arg2,
				boolean arg3, boolean arg4) {

			if (arg1 != null) {
				setText(arg1.getMap_name());
				setSize(200, 30);
			}

			if (arg3) {
				setBackground(new Color(0, 0, 128));
				setForeground(Color.white);
			} else {
				setBackground(Color.white);
				setForeground(Color.black);
			}

			return this;
		}
	}

}
