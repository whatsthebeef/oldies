package wtb.oddsminer.exceptions;

public class ProcessingException extends MinerException {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public ProcessingException () {
		super();
	}

	public ProcessingException ( String aCause ) {
		super( aCause );

	}

	public ProcessingException ( String aCause, Throwable e ) {
		super( aCause, e );
	}
}
