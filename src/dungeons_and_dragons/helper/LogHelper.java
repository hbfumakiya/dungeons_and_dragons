package dungeons_and_dragons.helper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class LogHelper {

	/**
	 * string constant for log file
	 * 
	 * @type String
	 */
	private static final String LOG_FILE = "res/game.log";

	/**
	 * Constant for information log
	 * 
	 * @type String
	 */
	public static final String TYPE_INFO = "INFO";

	/**
	 * Constant for error log
	 * 
	 * @type String
	 */
	public static final String TYPE_ERROR = "ERROR";

	/**
	 * this method used to store log 
	 * @param log_type
	 * @param log_message
	 * @throws IOException 
	 */
	public static void Log(String log_type, String log_message) throws IOException {
	
		Path path = Paths.get(LOG_FILE);
		Charset charset = StandardCharsets.UTF_8;
		ArrayList<String> details = new ArrayList<String>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS dd-MM-yyyy ");
		
		Date now = new Date();
	    String strDate = "[ "+sdf.format(now)+" "+log_type+" ]";
		
		details.add(strDate+" "+log_message);
		
		if(Files.exists(path)) {
			Files.write(path,  details, charset, StandardOpenOption.APPEND);
		} else {
			Files.write(path,  details, charset, StandardOpenOption.CREATE_NEW);
		}
		
	}
}
