package wtb.oddsminer.interfaces;

import wtb.oddsminer.calculator.algorithm.interfaces.IAlgorithm;
import wtb.oddsminer.calculator.intefaces.ICalculator;
import wtb.oddsminer.exceptions.SetupException;
import wtb.oddsminer.harvester.interfaces.IHarvester;
import wtb.oddsminer.processor.interfaces.IOddsProcessor;
import wtb.oddsminer.processor.interfaces.IProcessor;
import wtb.oddsminer.processor.interfaces.IResultsProcessor;

/**
 * The odds farm interface acts as the interface for all data farming, it will provide basic
 * functions to instruct the farming
 * 
 * @author wtb
 * 
 */
public interface IOddsFarm {

	/**
	 * farm the odds
	 * 
	 * @param aEventsHarvester
	 *            - harvester used to collect events
	 * @param eventsProcessor
	 *            - Processes events so they can feed into odds harvester
	 * @param aHost
	 *            - Host of field
	 * @param aPath
	 *            - Path to field
	 * @param aPageHarvester
	 * @param aEventsProcessor
	 * @param aResultsProcessor
	 * @param aOddsProcessor
	 * @param aBetCalculator
	 * @param aBettingAlgorithm
	 * @return
	 * @throws SetupException
	 */
	public String farm( IHarvester aEventsHarvester, IHarvester aPageHarvester,
			IProcessor aEventsProcessor, IResultsProcessor aResultsProcessor,
			IOddsProcessor aOddsProcessor, ICalculator aBetCalculator,
			IAlgorithm aBettingAlgorithm, String aHost, String aPath ) throws SetupException;
}
