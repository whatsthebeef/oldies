/**
 * 
 */
package wtb.oddsminer.harvester.interfaces;

import wtb.oddsminer.exceptions.HarvestException;
import wtb.oddsminer.exceptions.HarvestFieldLocationIncorrectException;

/**
 * 
 * This interface expose methods for the Page Harvester so the lowest leve; data
 * can be extracted from the pages
 * 
 * @author wtb
 * 
 */
public interface IHarvester {

	/**
	 * Method implemented to harvest the data source ('field') for data required
	 * 
	 * @param aHost
	 *            - String host of field
	 * @param aPath
	 *            - String path to field
	 * @param aInstructions
	 *            - String harvesting instructions
	 * @throws HarvestException
	 */
	public void harvest( String aHost, String aPath, String aInstructions )
			throws HarvestException;

	/**
	 * Overload method implemented to harvest the data source ('field') for data
	 * required when passed the complete Url
	 * 
	 * @param aUrl
	 *            String Url to field
	 * @param aInstructions
	 *            - String harvesting instructions
	 * @throws HarvestFieldLocationIncorrectException
	 *             - if incorrect url is being passed as data source
	 * @throws HarvestException
	 *             - if something goes wrong with scraping
	 */
	public void harvest( String aUrl, String aInstructions )
			throws HarvestFieldLocationIncorrectException, HarvestException;

}
