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
	/*private JLabel[] campaign_maps_label;
	private JLabel campaign_arrow;*/
	public JComboBox campaign_combobox;
	private ArrayList<GameMapModel> gameMapModel_map_list;
	private Object[] campaign_array;
	public JButton campaign_add;
	public ArrayList<GameMapModel> campaign_map_list = new ArrayList<>();
	BufferedImage arrow_image;
	private JPanel main_panel;
	private JPanel campaign_view_panel;
	
	//map J list for game map model
	public JList<GameMapModel> output_map_list;
	public JScrollPane mapScrollPane;
	private JButton save_button;
	private CampaignViewRenderer campaign_view_renderer;
	ArrayList<GameMapModel> map;
	public JButton moveMapUP;
	public JButton moveMapDown;
	public JLabel campaign_name_label;
	public JTextField campaign_name_text;
	public JButton back_button;
	
	/**
	 * Constructor of Campaign View class
	 * @param map_list returns list of maps that are already defined in map view
	 */
	public CampaignView(ArrayList<GameMapModel>  map_list) {

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
	private void initializeView(){
		
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
		// Combo-box for selecting a particular map from list of maps available in the gameMapModel
		campaign_array = this.gameMapModel_map_list.toArray();
		campaign_combobox = new JComboBox(campaign_array);
		
		if (campaign_array.length > 0)
		{
			campaign_combobox.setRenderer(campaign_view_renderer);
		}
		/*else{
			campaign_combobox.disable();
		}*/
		campaign_combobox.setBounds(200, 50, 150, 25);
		this.main_panel.add(campaign_combobox);
		
		// Add button to add one map in a campaign
		campaign_add = new JButton("Add Map to Campaign");
		campaign_add.setBounds(150,95, 170,25);
		this.main_panel.add(campaign_add);
		
		this.add(this.main_panel);
		
		
		// move selected map up

		this.moveMapUP = new JButton("\u2191"); //\u2191 unicode for up key
		this.moveMapUP.setBounds(350, 170, 50,30);

		this.main_panel.add(this.moveMapUP);

		// move selected map down
		
		this.moveMapDown = new JButton("\u2193"); //\u2193 unicode for down key
		this.moveMapDown.setBounds(350, 280,50,30);

		this.main_panel.add(this.moveMapDown);
		
		
		// Save button to save the campaigns containing defined maps

		this.back_button = new JButton("back");
		this.back_button.setBounds(150, 360, 65, 30);
		this.main_panel.add(this.back_button);
		
		
		// Save button to save the campaigns containing defined maps

		this.save_button = new JButton("Save");
		this.save_button.setBounds(250, 360, 65, 30);
		this.main_panel.add(this.save_button);
		
	}
	
	
	private void updateWindow(ArrayList<GameMapModel> temp) {
		
		for(int i=0;i<temp.size();i++){
			campaign_combobox.removeItem(temp.get(i));
		}
		
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
			System.out.println();
			this.output_map_list.setCellRenderer(new CampaignCellRenderer());
		}
		
		this.mapScrollPane = new JScrollPane(this.output_map_list);
		this.mapScrollPane.setBounds(142, 140, 200, 200);
		this.main_panel.add(this.mapScrollPane);
		
		
	}

	
	/**
	 * Invoked upon by Game map Model(observer) object once the data in width and height of first game view is set
	 */
	@Override
	public void update(Observable obs, Object obj) {
		
		campaign_map_list = ((CampaignModel)obs).getOutput_map_list();
		this.gameMapModel_map_list = ((CampaignModel)obs).getInput_map_list();
		
		this.updateWindow(campaign_map_list);
		
	}
	
	@Override
	public void setActionListener(ActionListener actionListener) {
		this.campaign_add.addActionListener(actionListener);
	}
	
	public class CampaignViewRenderer extends BasicComboBoxRenderer {
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			GameMapModel map_model = (GameMapModel) value;
			if(map_model != null)
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
