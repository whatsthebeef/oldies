package wtb.oddsminer.exceptions;

public class MinerException extends Exception {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public MinerException () {
		super();
	}

	public MinerException ( String aCause ) {
		super( aCause );

	}

	public MinerException ( String aCause, Throwable e ) {
		super( aCause, e );
	}
}
