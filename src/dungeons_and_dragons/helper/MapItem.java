/**
 * 
 */
package dungeons_and_dragons.helper;

import dungeons_and_dragons.model.ItemModel;

/**
 * @author Mihir Pujara
 *
 */
public class MapItem {
	
	private int x;
	
	private int y;
	
	private ItemModel item;

	/**
	 *	 Default Constructor which set default values of each properties 
	 */
	public MapItem() {
		this.x = 0;
		
		this.y = 0;
		
		this.item = null;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the item
	 */
	public ItemModel getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(ItemModel item) {
		this.item = item;
	}
}