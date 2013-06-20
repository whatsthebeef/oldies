package wtb.oddsminer.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import wtb.oddsminer.Constants;
import wtb.oddsminer.calculator.algorithm.interfaces.IAlgorithm;
import wtb.oddsminer.calculator.intefaces.ICalculator;
import wtb.oddsminer.exceptions.NoBetfairOddsPresentWithRunnerException;
import wtb.oddsminer.exceptions.NoOddsMapAssociatedWithRunnerException;

public class BettingCalculator implements ICalculator {

	Logger	log	= Logger.getLogger( BettingCalculator.class );

	/*
	 * (non-Javadoc)
	 * 
	 * @see wtb.oddsminer.calculator.intefaces.ICalculator#calculate(java.util.Map,
	 * wtb.oddsminer.calculator.algorithm.interfaces.IAlgorithm)
	 */
	public Map<String, Double> calculate( Map<String, Map<String, String>> aData,
			IAlgorithm aAlgorithm ) {
		ArrayList<Double> otherOdds = null;
		double betfairOdds = 0.0;
		Map<String, Double> runnersPercentageDifference = new HashMap<String, Double>();

		for ( String runner : aData.keySet() ) {
			Map<String, String> runnerOdds = aData.get( runner );
			// Get betfair odds
			if ( runnerOdds != null ) {
				String betfairOddsString = runnerOdds.remove( Constants.KBETFAIR_BOOKIE_IDENTIFIER );
				if ( betfairOddsString != null ) {
					try {
						betfairOdds = Double.parseDouble( betfairOddsString );
						otherOdds = new ArrayList<Double>();
						// Get rest of the odds
						for ( String odds : runnerOdds.keySet() ) {
							String bookieOdds = runnerOdds.get( odds );
							if ( bookieOdds != null ) {
								try {
									otherOdds.add( Double.parseDouble( bookieOdds ) );
								}
								catch ( NumberFormatException e ) {
									log.warn( "Another odds value can't be converted to a double",
											new NumberFormatException() );
								}
							}
						}
						runnersPercentageDifference.put( runner, aAlgorithm.execute( otherOdds,
								betfairOdds ) );

					}
					catch ( NumberFormatException e ) {
						log.warn( "The betfair odds value can't be converted to a double",
								new NumberFormatException() );
					}
				}
				else {
					log.warn( "There is not a Betfair odds value associated with this runner" );
					log.info( new NoBetfairOddsPresentWithRunnerException() );
				}
			}
			else {
				log.error( "A runner is assocatied with a null odds map.",
						new NoOddsMapAssociatedWithRunnerException() );
			}
		}
		return runnersPercentageDifference;
	}
}
