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

	public void save(T t);
	
	public ArrayList<T> getData();
	
	public void update(T t);
}
