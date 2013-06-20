package wtb.oddsminer.processor.interfaces;

import wtb.oddsminer.calculator.algorithm.interfaces.IAlgorithm;
import wtb.oddsminer.calculator.intefaces.ICalculator;
import wtb.oddsminer.exceptions.ResultsException;

/**
 * Used to present results in readable text file
 * 
 * @author wtb
 * 
 */
/**
 * @author wtb
 * 
 */
public interface IResultsProcessor {

	/**
	 * Process the harvested odds file to present results in human readable form
	 * 
	 * @param String
	 *            - location of odds files
	 * @param IOddsProcessor
	 *            - the processor used to get odds
	 * @param ICalculator
	 * 
	 * @param IAlgorithm
	 * @throws ResultsException
	 */
	public void process( String aOddsDir, IOddsProcessor aOddsProcessor,
			ICalculator aBetCalculator, IAlgorithm aBettingAlgorithm ) throws ResultsException;

	/**
	 * Process the harvested odds file to present results in human readable form using predefined
	 * odds directory
	 * 
	 * @param aOddsProcessor
	 * @param aBetCalculator
	 * @param aBettingAlgorithm
	 * @throws ResultsException
	 */
	public void process( IOddsProcessor aOddsProcessor, ICalculator aBetCalculator,
			IAlgorithm aBettingAlgorithm ) throws ResultsException;
}
