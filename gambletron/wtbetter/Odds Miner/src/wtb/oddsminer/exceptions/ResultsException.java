package wtb.oddsminer.exceptions;

public class ResultsException extends MinerException {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public ResultsException () {
		super();
	}

	public ResultsException ( String aCause ) {
		super( aCause );

	}

	public ResultsException ( String aCause, Throwable e ) {
		super( aCause, e );
	}
}
