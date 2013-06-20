package wtb.oddsminer.harvester;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.GregorianCalendar;

import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;

import wtb.oddsminer.Properties;
import wtb.oddsminer.exceptions.HarvestException;
import wtb.oddsminer.harvester.interfaces.IHarvester;
import wtb.oddsminer.utilities.UrlWorker;

public class OddsCheckerOddsHarvester implements IHarvester {

	private static final String	KHARVEST_RESULTS_FILE_NAME				= "fileName";

	private static final String	KINSTRUCTION_FILE_NOT_PRESENT_EXCEPTION	= "Instruction file not present";

	private static final String	KURL_STRING								= "url";

	private static final String	KXML_EXTENSION							= ".xml";

	/**
	 * Member variables
	 */

	/**
	 * String location where the odds files are put by processor
	 */
	private String				mOddsHarvestResultsDirectory			= null;

	/**
	 * Constructor, Declares a File object where the results will be attempted to be wrote too v *
	 * 
	 * @param aOddsHarvestResultsDirectory
	 */
	public OddsCheckerOddsHarvester ( String aOddsHarvestResultsDirectory ) {
		mOddsHarvestResultsDirectory = aOddsHarvestResultsDirectory;
	}

	/**
	 * Constructor, Uses a default file path where the results will be attempted to be wrote too
	 * 
	 */
	public OddsCheckerOddsHarvester () {
		mOddsHarvestResultsDirectory = Properties.KROOT_DIR + Properties.KODDS_DIR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wtb.odds.analyser.page.interfaces.IPageHarvester#harvestData(java.lang .String)
	 */
	public void harvest( String aHost, String aPath, String aInstructions ) throws HarvestException {

		harvest( new StringBuilder( aHost ).append( aPath ).toString(), aInstructions );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wtb.oddsminer.harvester.interfaces.IHarvester#harvest(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void harvest( String aUrl, String aInstructions ) throws HarvestException {
		ScraperConfiguration config = null;
		String fallbackFileName = "default";
		try {
			// set instructions so harvester can read them
			config = new ScraperConfiguration( aInstructions );
		}
		catch ( FileNotFoundException e ) {
			throw new HarvestException( KINSTRUCTION_FILE_NOT_PRESENT_EXCEPTION );
		}
		// provide instructions and place for harvest results
		Scraper scraper = new Scraper( config, mOddsHarvestResultsDirectory );

		// set field to harvest
		scraper.addVariableToContext( KURL_STRING, aUrl );
		// set result file name
		try {
			scraper.addVariableToContext( KHARVEST_RESULTS_FILE_NAME, UrlWorker
					.getEventFromUrl( aUrl )
					+ KXML_EXTENSION );
		}
		catch ( MalformedURLException e ) {
			e.printStackTrace();
			scraper.addVariableToContext( KHARVEST_RESULTS_FILE_NAME, fallbackFileName
					+ new GregorianCalendar().getTimeInMillis() + KXML_EXTENSION );
		}
		// receive updates from harvest
		scraper.setDebug( true );
		// harvest
		scraper.execute();
		// TODO: maybe check to see if there are odds on page
	}

	/**
	 * @param mOddsHarvestResultsDirectory
	 *            the mOddsHarvestResultsDirectory to set
	 */
	public void setOddsHarvestResultsDirectory( String mOddsHarvestResultsDirectory ) {
		this.mOddsHarvestResultsDirectory = mOddsHarvestResultsDirectory;
	}

	/**
	 * @return the mOddsHarvestResultsDirectory
	 */
	public String getOddsHarvestResultsDirectory() {
		return mOddsHarvestResultsDirectory;
	}
}
