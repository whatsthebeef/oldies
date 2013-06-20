package wtb.oddsminer.calculator.intefaces;

import java.util.Map;

import wtb.oddsminer.calculator.algorithm.interfaces.IAlgorithm;
import wtb.oddsminer.exceptions.CalculationException;

/**
 * 
 * Interface implemented by calculator type objects which have a calculate method are passed maps of
 * data and an algorithm and return a value
 * 
 */
public interface ICalculator {

	/**
	 * most important method which calculates the required value
	 * 
	 * @param aData
	 *            - odds of event
	 * @param aAlgorithm
	 *            - algorithm to use to calculate whether to bet
	 * @return Map<String, Double> - result of the calculation, key - runner, value - percentage
	 *         difference between betfair and algorithm calculated average
	 * @throws CalculationException
	 */
	public Map<String, Double> calculate( Map<String, Map<String, String>> aData,
			IAlgorithm aAlgorithm );
}
