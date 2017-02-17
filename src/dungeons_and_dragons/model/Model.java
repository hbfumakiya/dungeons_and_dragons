/**
 * 
 */
package dungeons_and_dragons.model;

import java.util.ArrayList;

/**
 * @author mihir
 *
 */
public interface Model<T> {

	public void save();
	
	public ArrayList<T> getData();
	
}
