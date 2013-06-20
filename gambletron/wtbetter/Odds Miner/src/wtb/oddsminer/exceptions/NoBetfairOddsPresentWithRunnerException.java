package wtb.oddsminer.exceptions;

public class NoBetfairOddsPresentWithRunnerException extends CalculationException {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public NoBetfairOddsPresentWithRunnerException () {
		super();
	}

	public NoBetfairOddsPresentWithRunnerException ( String aMessage ) {
		super( aMessage );
	}
}
