package wtb.oddsminer.harvester;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;

import wtb.oddsminer.Properties;
import wtb.oddsminer.exceptions.HarvestException;
import wtb.oddsminer.exceptions.HarvestFieldLocationIncorrectException;
import wtb.oddsminer.harvester.interfaces.IHarvester;
import wtb.oddsminer.utilities.UrlWorker;

public class OddsCheckerEventsHarvester implements IHarvester {

	private static final String	KINSTRUCTION_FILE_NOT_PRESENT_EXCEPTION	= "Instruction file not present";

	private static final String	KURL_STRING								= "url";

	private static final String	KHOST_STRING							= "host";

	/**
	 * Member variables
	 */

	/**
	 * String location where the odds files are put by processor
	 */
	private String				mEventsHarvestResultsDirectory			= null;

	/**
	 * Constructor, Declares a String where the where the results will be attempted to be wrote too
	 * 
	 * @param aEventsHarvestResultsDirectory
	 */
	public OddsCheckerEventsHarvester ( String aEventsHarvestResultsDirectory ) {
		setmEventsHarvestResultsDirectory( aEventsHarvestResultsDirectory );
	}

	/**
	 * Constructor, Uses a default file path where the results will be attempted to be wrote too
	 * 
	 */
	public OddsCheckerEventsHarvester () {
		setmEventsHarvestResultsDirectory( Properties.KROOT_DIR + Properties.KEVENTS_DIR );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wtb.oddsminer.harvester.interfaces.IHarvester#harvest(java.lang.String,
	 * java.lang.String)
	 */
	public void harvest( String aHost, String aPath, String aInstructions ) throws HarvestException {

		ScraperConfiguration config = null;

		try {
			// set instructions so harvester can read them
			config = new ScraperConfiguration( aInstructions );
		}
		catch ( FileNotFoundException e ) {
			throw new HarvestException( KINSTRUCTION_FILE_NOT_PRESENT_EXCEPTION );
		}
		// provide instructions and place for harvest results
		Scraper scraper = new Scraper( config, getmEventsHarvestResultsDirectory() );
		// set field to harvest
		scraper.addVariableToContext( KURL_STRING, new StringBuilder( aHost ).append( aPath )
				.toString() );
		// set link host
		scraper.addVariableToContext( KHOST_STRING, aHost );
		// receive updates from harvest
		scraper.setDebug( true );
		// harvest
		scraper.execute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wtb.oddsminer.harvester.interfaces.IHarvester#harvest(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public void harvest( String aUrl, String aInstructions )
			throws HarvestFieldLocationIncorrectException, HarvestException {

		try {
			harvest( UrlWorker.getProtocolAndHostFromUrl( aUrl ), UrlWorker.getPathFromUrl( aUrl ),
					aInstructions );
		}
		catch ( MalformedURLException e ) {
			e.printStackTrace();
			throw new HarvestFieldLocationIncorrectException();
		}
	}

	/**
	 * @param mEventsHarvestResultsDirectory the mEventsHarvestResultsDirectory to set
	 */
	public void setmEventsHarvestResultsDirectory( String mEventsHarvestResultsDirectory ) {
		this.mEventsHarvestResultsDirectory = mEventsHarvestResultsDirectory;
	}

	/**
	 * @return the mEventsHarvestResultsDirectory
	 */
	public String getmEventsHarvestResultsDirectory() {
		return mEventsHarvestResultsDirectory;
	}
}
