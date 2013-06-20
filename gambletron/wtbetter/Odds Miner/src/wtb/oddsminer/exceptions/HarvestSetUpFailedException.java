package wtb.oddsminer.exceptions;

public class HarvestSetUpFailedException extends HarvestException {

	private static final long	serialVersionUID	= 1L;

	public HarvestSetUpFailedException () {
		super();
	}

	public HarvestSetUpFailedException ( String aMessage ) {
		super( aMessage );
	}

}
