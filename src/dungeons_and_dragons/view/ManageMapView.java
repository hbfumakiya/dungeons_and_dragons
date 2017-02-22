/**
 * 
 */
package dungeons_and_dragons.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.google.gson.JsonSyntaxException;

import dungeons_and_dragons.helper.GameButton;
import dungeons_and_dragons.model.GameMapModel;
import dungeons_and_dragons.model.ItemModel;

/**
 * @author Urmil Kansara
 *
 */
public class ManageMapView extends JFrame implements View {

	private String title = "Manage Map";

	private JPanel buttonPanel;

	private JPanel gridMainPanel;

	private JScrollPane scrollPane;

	public JButton addItemButton;

	public JButton backButton;

	private ArrayList<GameButton> editButtons;

	private ArrayList<GameButton> viewButtons;

	private ArrayList<GameButton> deleteButtons;

	/**
	 * 
	 * @param actionListener
	 * @param item
	 */
	public ManageMapView(ActionListener actionListener, ArrayList<GameMapModel> item) {

		// initialize game window
		this.initializeWindow(actionListener, item);

		// close frame while user click on close
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * 
	 * @param actionListener
	 * @param items
	 */
	public void initializeWindow(ActionListener actionListener, ArrayList<GameMapModel> items) {

		this.setTitle(this.title);

		this.editButtons = new ArrayList<GameButton>();
		this.viewButtons = new ArrayList<GameButton>();
		this.deleteButtons = new ArrayList<GameButton>();

		// button panel

		this.buttonPanel = new JPanel();
		this.buttonPanel.setLayout(new BoxLayout(this.buttonPanel, BoxLayout.LINE_AXIS));
		this.buttonPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.buttonPanel.add(Box.createHorizontalGlue());

		this.backButton = new JButton("Back");
		this.buttonPanel.add(this.backButton);

		this.buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));

		this.addItemButton = new JButton("Add Map");
		this.buttonPanel.add(this.addItemButton);

		// grid panel

		this.gridMainPanel = new JPanel();
		this.gridMainPanel.setLayout(new BoxLayout(this.gridMainPanel, BoxLayout.PAGE_AXIS));
		this.gridMainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.scrollPane = new JScrollPane(this.gridMainPanel);

		this.refreshItemGrid(actionListener, items);

		// Put panel into frame, using the content pane's BoxLayout.
		Container contentPane = getContentPane();
		contentPane.add(this.buttonPanel, BorderLayout.PAGE_START);
		contentPane.add(Box.createVerticalStrut(20));
		contentPane.add(this.scrollPane, BorderLayout.CENTER);

		this.setResizable(false);

		// set minimum size of frame
		this.setPreferredSize(new Dimension(400, 300));

		// Display the window.
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	/**
	 * 
	 * @param Grapphics g
	 */
	public void paint(Graphics g) {
		super.paint(g); // fixes the immediate problem.
		Graphics2D g2 = (Graphics2D) g;
		Line2D lin = new Line2D.Float(0, 62, 400, 62);
		g2.draw(lin);
	}
	/**
	 * 
	 * @param actionListener
	 * @param item
	 */

	public void refreshItemGrid(ActionListener actionListener, ArrayList<GameMapModel> item) {
		this.gridMainPanel.removeAll();

		if (item != null) {
			for (int i = 0; i < item.size(); i++) {

				JPanel gridSubPanel = new JPanel();
				gridSubPanel.setLayout((new GridLayout(1, 3, 5, 5)));
				gridSubPanel.setMaximumSize(new Dimension(400, 30));

				int id = item.get(i).getMap_id();
				JLabel itemName = new JLabel(item.get(i).getMap_name());
				GameButton viewButton = new GameButton();
				viewButton.setText("View");
				viewButton.setId(id);
				viewButton.setSource(item.get(i));
				viewButton.setButtonType(GameButton.BUTTON_TYPE_VIEW);
				viewButton.addActionListener(actionListener);
				this.viewButtons.add(viewButton);

				GameButton editButton = new GameButton();
				editButton.setText("Edit");
				editButton.setId(id);
				editButton.setSource(item.get(i));
				editButton.setButtonType(GameButton.BUTTON_TYPE_EDIT);
				editButton.addActionListener(actionListener);
				editButton.setPreferredSize(new Dimension(50, 30));
				this.editButtons.add(editButton);

/*				GameButton deleteButton = new GameButton();
				deleteButton.setText("Delete");
				deleteButton.setId(id);
				deleteButton.setSource(item.get(i));
				deleteButton.setButtonType(GameButton.BUTTON_TYPE_DELETE);
				deleteButton.addActionListener(actionListener);
				this.deleteButtons.add(deleteButton);
*/
				gridSubPanel.add(itemName);
				gridSubPanel.add(viewButton);
				gridSubPanel.add(editButton);
				//gridSubPanel.add(deleteButton);

				this.gridMainPanel.add(gridSubPanel);
			}
		}
	}

	/**
	 * 
	 */
	@Override
	public void setActionListener(ActionListener actionListener) {

		this.backButton.addActionListener(actionListener);

		this.addItemButton.addActionListener(actionListener);
	}

}

