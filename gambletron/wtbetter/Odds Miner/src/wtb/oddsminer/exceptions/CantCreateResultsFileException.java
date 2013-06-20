package wtb.oddsminer.exceptions;

public class CantCreateResultsFileException extends ResultsException {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public CantCreateResultsFileException () {
		super();
	}

	public CantCreateResultsFileException ( String aCause ) {
		super( aCause );

	}

	public CantCreateResultsFileException ( String aCause, Throwable e ) {
		super( aCause, e );
	}
}
