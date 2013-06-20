package wtb.oddsminer.exceptions;

public class EventsFileMalformedException extends ProcessingException {

	private static final long	serialVersionUID	= 1L;

	public EventsFileMalformedException () {
		super();
	}

	public EventsFileMalformedException ( String aMessage ) {
		super( aMessage );
	}

	public EventsFileMalformedException ( String aMessage, Throwable e ) {
		super( aMessage, e );
	}
}
