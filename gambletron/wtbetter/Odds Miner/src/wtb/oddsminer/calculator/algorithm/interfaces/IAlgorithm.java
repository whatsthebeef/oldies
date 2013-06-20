package wtb.oddsminer.calculator.algorithm.interfaces;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface where the execute method acts as a mathematic equation
 * 
 */
public interface IAlgorithm {

	/**
	 * Contains logic which takes the 2 arguments and performs the desired calcualtion on them
	 * before returning a value
	 * 
	 * @param ArrayList
	 *            <Double> - list of values
	 * @param Double
	 *            - single value
	 * @return
	 */
	public double execute( ArrayList<Double> aArgs, Double aArg );

	/**
	 * The method filters a list removing values defined in the method as a precursor for the
	 * algorithm
	 * 
	 * @param aArgs
	 *            - list to filter
	 * @return ArrayList<Double> - filtered list
	 * 
	 */
	public List<Double> filter( ArrayList<Double> aArgs );

}
