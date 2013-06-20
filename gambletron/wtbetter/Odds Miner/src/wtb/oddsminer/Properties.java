package wtb.oddsminer;

public class Properties {

	/**
	 * Location of configuration file for web harvest to get events on a web page
	 */
	public static final String	KHARVEST_EVENTS_CONFIG_FILE	= "resources/eventspage.xml";

	/**
	 * Location of configuration file for web harvest to get odds on a web page
	 */
	public static final String	KHARVEST_PAGE_CONFIG_FILE	= "resources/oddspage.xml";

	/**
	 * Location of events to be processed
	 */
	public static final String	KEVENTS_DIR					= "var/events/";

	/**
	 * Path of events to be processed
	 */
	public static final String	KEVENTS_PATH				= "var/events/events.xml";

	/**
	 * Location of odds to be processed
	 */
	public static final String	KODDS_DIR					= "var/odds/";

	/**
	 * Location of events file name to be processed
	 */
	public static final String	KEVENTS_FILENAME			= "events.xml";

	/**
	 * Odds checker host
	 */
	public static final String	KODDSCHECKER_HOST			= "http://www.oddschecker.com";

	/**
	 * Odds checker path
	 */
	public static final String	KODDSCHECKER_PATH			= "/football/english/premier-league";

	/**
	 * Location of base results directory, dont include trailing forward slash
	 */
	public static final String	KROOT_DIR					= "/home/wtb/dev/gambletron/wtbetter/Odds Miner/";

	/**
	 * Location where complete results are generated
	 */
	public final static String	KRESULTS_DIR				= "var/results/";

	/**
	 * Path where complete results are generated
	 */
	public final static String	KRESULTS_PATH				= "var/results/results.txt";

	/**
	 * File where complete results are generated
	 */
	public final static String	KRESULTS_FILENAME			= "results.txt";

	/**
	 * base directory where files generated while harvesting will be kept
	 */
	public static final String	KBASE_DIR					= "var";

}