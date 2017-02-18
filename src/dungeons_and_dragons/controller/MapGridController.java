/**
 * 
 */
package dungeons_and_dragons.controller;

import dungeons_and_dragons.model.GameMapModel;
import dungeons_and_dragons.view.MapView;

/**
 * @author Urmil Kansara & Tejas Sadrani
 *
 */
public class MapGridController {
	
	GameMapModel map_model;
	MapView map_view;
	public MapGridController(GameMapModel game_map_model) {
		
		this.map_model = game_map_model;
		this.map_view = new MapView(this.map_model.getMap_size());
		
		this.map_model.addObserver(this.map_view);
		}
		
	}

	
	
	
