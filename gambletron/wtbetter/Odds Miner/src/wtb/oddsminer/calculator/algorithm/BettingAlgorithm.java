package wtb.oddsminer.calculator.algorithm;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import wtb.oddsminer.calculator.algorithm.interfaces.IAlgorithm;

public class BettingAlgorithm implements IAlgorithm {

	private final Logger		log		= Logger.getLogger( BettingAlgorithm.class );

	private static final Double	KZERO	= 0.0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see wtb.oddsminer.calculator.algorithm.interfaces.IAlgorithm#execute(java.util.List,
	 * java.lang.Double)
	 */
	@SuppressWarnings("unchecked")
	public double execute( ArrayList<Double> aOtherOdds, Double aBetfairOdds ) {
		double otherOddsTotal = KZERO;
		double percentageDifference = KZERO;
		double betfairOdds = aBetfairOdds;

		if ( betfairOdds != ( KZERO ) ) {
			if ( aOtherOdds != null ) {
				ArrayList<Double> localOtherOdds = ( ArrayList<Double> ) aOtherOdds.clone();
				filter( localOtherOdds );
				if ( !localOtherOdds.isEmpty() ) {
					// Core algorithm
					for ( double arg : localOtherOdds ) {
						otherOddsTotal = otherOddsTotal + arg;
					}
					double otherOddsAverage = otherOddsTotal
							/ new Integer( localOtherOdds.size() ).doubleValue();
					percentageDifference = ( betfairOdds - otherOddsAverage ) / betfairOdds;
				}
				else {
					log.info( "Empty list after filtering, no odds collected" );
				}
			}
			else {
				log.error( "Null value, Other odds list is null" );
			}
		}
		else {
			log.info( "Betfair odds passed is zero" );
		}
		return roundTwoDecimals( percentageDifference );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wtb.oddsminer.calculator.algorithm.interfaces.IAlgorithm#filter(java.util.ArrayList)
	 */
	public List<Double> filter( ArrayList<Double> aArgs ) {
		if ( aArgs != null ) {
			// remove all zero
			oddsListRemover( aArgs, KZERO );
			if ( !aArgs.isEmpty() ) {
				Collections.sort( aArgs );
				// remove highest odds value from the list and all occurrences
				if ( !aArgs.isEmpty() ) {
					oddsListRemover( aArgs, aArgs.get( 0 ) );
					if ( !aArgs.isEmpty() ) {
						oddsListRemover( aArgs, aArgs.get( 0 ) );
					}
					else {
						log.info( "Empty list after removing second values, no odds collected" );
					}
				}
				else {
					log.info( "Empty list after removing first values, no odds collected" );
				}
			}
			else {
				log.info( "Empty list after removing zeros, no odds collected" );
			}
		}
		else {
			log.error( "Empty list being passed to algorithm" );
		}
		return aArgs;
	}

	/**
	 * A simple math utility to reduce something to 2 decimal places
	 * 
	 * @param aDouble
	 *            - the Double to reduce to 2 decimal places
	 * @return double - double with 2 decimal places
	 */
	private double roundTwoDecimals( Double aDouble ) {
		Double formattedDouble = 0.00;
		if ( !aDouble.isNaN() ) {
			DecimalFormat twoDForm = new DecimalFormat( "#.##" );
			formattedDouble = Double.valueOf( twoDForm.format( aDouble ) );
		}
		return formattedDouble;
	}

	/**
	 * Method which iterates over the odds list provided in the arguments and removes the specified
	 * value from the odds list then returns the list
	 * 
	 * @param aOddsList
	 *            - list to remove value from
	 * @param aValuetoRemove
	 *            - value to remove from the list
	 * 
	 */
	private void oddsListRemover( List<Double> aOddsList, Double aValuetoRemove ) {
		for ( Iterator<Double> iterator = aOddsList.iterator(); iterator.hasNext(); ) {
			Double value = iterator.next();
			if ( value.equals( aValuetoRemove ) ) {
				iterator.remove();
			}
		}
	}

}
