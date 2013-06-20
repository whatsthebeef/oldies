package wtb.oddsminer.utilities;

import java.io.File;

import wtb.oddsminer.Properties;
import wtb.oddsminer.exceptions.SetupException;

public class SetupUtilities {

	private static final String	KRESULTS_ODDS_DIR_NOT_CREATED		= "Odds results directory not created";

	private static final String	KRESULTS_EVENTS_DIR_NOT_CREATED		= "Events results directory not created";

	private static final String	KRESULTS_RESULTS_DIR_NOT_CREATED	= "Results results directory not created";

	private static final String	KRESULTS_BASE_DIR_NOT_CREATED		= "Base results directory not created";

	/**
	 * static utility used to create structure where files are stored, if passed 'null'
	 * aResultsLocation it will use KROOT_DIR
	 * 
	 * @param aResultsLocation
	 * 
	 * @throws SetupException
	 */
	public static void setupDirectoryStructure( String aResultsLocation ) throws SetupException {

		if ( aResultsLocation == null ) {
			aResultsLocation = Properties.KROOT_DIR;
		}

		// creates directory for odds dir
		File oddsFile = new File( aResultsLocation + Properties.KODDS_DIR );

		if ( !oddsFile.exists() ) {
			oddsFile.mkdirs();
			if ( !oddsFile.exists() ) {
				throw new SetupException( KRESULTS_BASE_DIR_NOT_CREATED );
			}

		}

		// creates directory for events dir
		File eventsFile = new File( aResultsLocation + Properties.KEVENTS_DIR );

		if ( !eventsFile.exists() ) {
			eventsFile.mkdirs();
			if ( !eventsFile.exists() ) {
				throw new SetupException( KRESULTS_EVENTS_DIR_NOT_CREATED );
			}

		}

		// creates directory for results dir
		File resultsFile = new File( aResultsLocation + Properties.KRESULTS_DIR );

		if ( !resultsFile.exists() ) {
			resultsFile.mkdirs();
			if ( !resultsFile.exists() ) {
				throw new SetupException( KRESULTS_RESULTS_DIR_NOT_CREATED );
			}

		}
	}
}
