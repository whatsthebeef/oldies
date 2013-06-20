package wtb.oddsminer;

import java.io.File;

import wtb.oddsminer.calculator.BettingCalculator;
import wtb.oddsminer.calculator.algorithm.BettingAlgorithm;
import wtb.oddsminer.calculator.algorithm.interfaces.IAlgorithm;
import wtb.oddsminer.calculator.intefaces.ICalculator;
import wtb.oddsminer.exceptions.HarvestException;
import wtb.oddsminer.exceptions.ProcessingException;
import wtb.oddsminer.exceptions.ResultsException;
import wtb.oddsminer.exceptions.SetupException;
import wtb.oddsminer.harvester.OddsCheckerEventsHarvester;
import wtb.oddsminer.harvester.OddsCheckerOddsHarvester;
import wtb.oddsminer.harvester.interfaces.IHarvester;
import wtb.oddsminer.interfaces.IOddsFarm;
import wtb.oddsminer.processor.interfaces.IOddsProcessor;
import wtb.oddsminer.processor.interfaces.IProcessor;
import wtb.oddsminer.processor.interfaces.IResultsProcessor;
import wtb.oddsminer.processors.EventsProcessor;
import wtb.oddsminer.processors.OddsProcessor;
import wtb.oddsminer.processors.ResultsProcessor;
import wtb.oddsminer.utilities.SetupUtilities;

public class OddsFarm implements IOddsFarm {

	public static void main( String[] args ) {
		// Setup directory structure
		try {
			SetupUtilities.setupDirectoryStructure( Properties.KROOT_DIR );
		}
		catch ( SetupException e1 ) {
			e1.printStackTrace();
		}
		try {
			new OddsFarm().farm( new OddsCheckerEventsHarvester(), new OddsCheckerOddsHarvester(),
					new EventsProcessor(), new ResultsProcessor( new File( Properties.KROOT_DIR
							+ Properties.KRESULTS_PATH ) ), new OddsProcessor(),
					new BettingCalculator(), new BettingAlgorithm(), Properties.KODDSCHECKER_HOST,
					Properties.KODDSCHECKER_PATH );
		}
		catch ( SetupException e ) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wtb.oddsminer.interfaces.IOddsFarm#farm()
	 */
	@Override
	public String farm( IHarvester aEventsHarvester, IHarvester aPageHarvester,
			IProcessor aEventsProcessor, IResultsProcessor aResultsProcessor,
			IOddsProcessor aOddsProcessor, ICalculator aBetCalculator,
			IAlgorithm aBettingAlgorithm, String aHost, String aPath ) throws SetupException {

		try {
			aEventsHarvester.harvest( aHost, aPath, Properties.KHARVEST_EVENTS_CONFIG_FILE );
		}
		catch ( HarvestException e ) {
			e.printStackTrace();
		}

		try {
			for ( String aUrl : aEventsProcessor.process() )
				try {
					aPageHarvester.harvest( "", aUrl, Properties.KHARVEST_PAGE_CONFIG_FILE );
				}
				catch ( HarvestException e ) {
					e.printStackTrace();
				}
		}
		catch ( ProcessingException e ) {
			e.printStackTrace();
		}

		try {
			aResultsProcessor.process( Properties.KROOT_DIR + Properties.KODDS_DIR, aOddsProcessor,
					aBetCalculator, aBettingAlgorithm );

		}
		catch ( ResultsException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}
}
