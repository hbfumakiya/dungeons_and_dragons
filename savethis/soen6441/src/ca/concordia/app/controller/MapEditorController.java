package ca.concordia.app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import ca.concordia.app.model.Country;
import ca.concordia.app.model.GameMap;
import ca.concordia.app.view.MapEditorView;

public class MapEditorController implements ActionListener, MouseListener{
	
	public MapEditorView map_editor_view;
	
	GameMap gameMap;
	
	int tempX,tempY;
	
	public MapEditorController() {
		
		gameMap = GameMap.getInstance();
		
		map_editor_view = new MapEditorView();
		map_editor_view.setActionListener(this);
		map_editor_view.setMouseListener(this);
		map_editor_view.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(e);
		if(e.getSource().equals(map_editor_view.map_area.mapArea)){
			tempX = e.getX();
			tempY = e.getY();
			map_editor_view.map_area.mapArea.drawCountry(e.getX(),e.getY());
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(map_editor_view.save_button)){
			
			Country c = new Country(map_editor_view.country_name_value.getText().trim(), 
					tempX, tempY, "");
			
			gameMap.getCountries().add(c);
			
			List<String> selValues = map_editor_view.neighbours_list.getSelectedValuesList();
			
			
			HashMap<Country, ArrayList<String>> territories = (HashMap<Country, ArrayList<String>>) gameMap.getTerritories().clone(); 
			
			
			
			ArrayList<String> selectedNeighbours = new ArrayList<>(selValues);
			
			territories.put(c, selectedNeighbours);
			
			HashMap<String, List<String>> crossLinks = new HashMap<String,List<String>>();
			
			ArrayList<String> sNeighbours;
			
			for(String s : selectedNeighbours){
				sNeighbours = (ArrayList<String>) territories.get(gameMap.getCountryByName(s));
				sNeighbours.add(c.getCountryName());
				crossLinks.put(s, sNeighbours);
			}
			
			gameMap.setTerritories(territories);
			
			map_editor_view.repaintNeighbours();
			
			map_editor_view.connectNeighbours(c.getCountryName());
			
			map_editor_view.country_name_value.setText("");
			
		}
	}

}
