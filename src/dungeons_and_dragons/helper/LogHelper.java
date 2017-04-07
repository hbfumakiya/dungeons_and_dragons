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
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * this class is for creating logs during game playing time
 * 
 * @author Mihir Pujara
 *
 */
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
	 * Constant for game error log
	 * 
	 * @type String
	 */
	public static final String TYPE_INFO_ERROR = "GAME_ERROR";

	/**
	 * this method used to store log
	 * 
	 * @param log_type
	 * @param log_message
	 * @throws IOException
	 */
	public static void Log(String log_type, String log_message) {

		Path path = Paths.get(LOG_FILE);
		Charset charset = StandardCharsets.UTF_8;
		ArrayList<String> details = new ArrayList<String>();

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS dd-MM-yyyy ");

		Date now = new Date();
		String strDate = "[ " + sdf.format(now) + " " + log_type + " ]";

		details.add(strDate + " " + log_message);

		try {
			if (Files.exists(path)) {
				Files.write(path, details, charset, StandardOpenOption.APPEND);
			} else {
				Files.write(path, details, charset, StandardOpenOption.CREATE_NEW);
			}
		} catch (IOException e) {
		}

	}
	
	public static String getLastLine() {
		Path path = Paths.get(LOG_FILE);
		Charset charset = StandardCharsets.UTF_8;
		ArrayList<String> details = new ArrayList<String>();
		
		String line = "";

		try {
			if (Files.exists(path)) {
				List<String> lines = Files.readAllLines(path);
				ArrayList<String> filteredLines= lines.stream().filter(s -> (s.contains(TYPE_INFO) || s.contains(TYPE_INFO_ERROR))).collect(Collectors.toCollection(ArrayList::new));
				line = filteredLines.get(filteredLines.size()-1);
			} 
		} catch (IOException e) {
		}
		return line;
	}
}
