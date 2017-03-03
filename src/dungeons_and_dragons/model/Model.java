/**
 * 
 */
package dungeons_and_dragons.model;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.JsonSyntaxException;

/**
 * @author Mihir Pujara
 * 
 */
public interface Model<T> {

	public void save();
	
	public ArrayList<T> getData() throws JsonSyntaxException, IOException;
	
	public void update();
}
