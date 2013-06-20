package wtb.oddsminer.processor.interfaces;

import java.io.File;
import java.util.Map;

/**
 * Get Odds from file and stores the bookies and associated odds in a calculatable map
 * 
 * @author wtb
 * 
 */
public interface IOddsProcessor {

	/**
	 * Method which extracts the odds and creates map
	 * 
	 * @param aFile
	 * @return Map<String, Map<String, String>> - stores bookies and associated odds
	 */
	public Map<String, Map<String, String>> process( File aFile );

}
