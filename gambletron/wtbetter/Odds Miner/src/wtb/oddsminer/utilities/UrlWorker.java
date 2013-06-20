package wtb.oddsminer.utilities;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlWorker {

	private static final String	KHTML_EXTENSION	= ".html";

	/**
	 * A static utility function to retrieve the host in string form including protocol from the Url
	 * string
	 * 
	 * @param aUrl
	 * @return String - host including protocol
	 * @throws MalformedURLException
	 */
	public static String getProtocolAndHostFromUrl( String aUrl ) throws MalformedURLException {

		final String protocolPostfix = "://";

		URL url = new URL( aUrl );
		return new StringBuilder( url.getProtocol() ).append( protocolPostfix ).append(
				url.getHost() ).toString();
	}

	/**
	 * A static utility function to retrieve the path in string form, from the Url string
	 * 
	 * @param aUrl
	 * @return String - path string with backslash prefix
	 * @throws MalformedURLException
	 */
	public static String getPathFromUrl( String aUrl ) throws MalformedURLException {

		URL url = new URL( aUrl );
		return url.getPath();
	}

	/**
	 * A static utility function to retrieve the file name without extension in string form, from
	 * the Url string (the string after the last forward slash
	 * 
	 * @param aUrl
	 *            - String to process
	 * @return String - filename without extension
	 * @throws MalformedURLException
	 */
	public static String getFileNameWithoutExtension( String aUrl ) throws MalformedURLException {

		final String forwardSlash = "/";
		StringBuilder builder = new StringBuilder( new URL( aUrl ).getFile() );
		// + 1 - doesn't include the forward slash
		return builder.substring( builder.lastIndexOf( forwardSlash ) + 1 );
	}

	/**
	 * 
	 * A specific static utility function to retrieve the event name from an event on the
	 * oddschecker website
	 * 
	 * @param aUrl
	 *            - the string to be processed
	 * @return String - the event name
	 * @throws MalformedURLException
	 */
	public static String getEventFromUrl( String aUrl ) throws MalformedURLException {

		final String winMarketString = "win-market";
		final String forwardSlash = "/";
		String event = getFileNameWithoutExtension( aUrl );
		final int urlLength = aUrl.length();

		if ( event.equals( winMarketString ) ) {
			// -11 is length of win-market and preceding forward slash string
			// so it can be removed from the
			// url
			event = aUrl.substring( 0, urlLength - 11 );
			// +1 so the back slash isn't included in the resultant string
			event = event.substring( event.lastIndexOf( forwardSlash ) + 1, event.length() );
		}
		else if ( event.contains( KHTML_EXTENSION ) ) {
			// removes last 5 characters which is the html extension
			event = event.substring( 0, event.length() - 5 );
		}
		return event;
	}

	/**
	 * 
	 * static utility function which takes a variable amount of string arguments and creates a Url
	 * path in sequence the arguments are sent in, doesn't include preceding or trailing forward
	 * slash
	 * 
	 * @param aDir
	 *            - all directories to join
	 * @return String - path joined together
	 */
	@Deprecated
	public static String buildPath( String... aDir ) {
		final String forwardSlash = "/";
		boolean firstDir = true;

		StringBuilder builder = new StringBuilder( "" );
		for ( String dir : aDir ) {
			if ( firstDir ) {
				builder.append( dir );
				firstDir = false;
			}
			else {
				builder.append( forwardSlash );
				builder.append( dir );
			}
		}
		return builder.toString();
	}

	/**
	 * 
	 * static utility function which takes a variable amount of string arguments and creates a Url
	 * path in sequence the arguments are sent in, doesn't include preceding or trailing forward
	 * slash
	 * 
	 * @param aDir
	 *            - all directories to join
	 * @param aLeadingForwardSlash
	 *            - should the path include a leading forwards slash
	 * 
	 * @return String - path joined together
	 */
	public static String buildPath( boolean aLeadingForwardSlash, String... aDir ) {
		final String forwardSlash = "/";

		StringBuilder builder = new StringBuilder( "" );
		for ( String dir : aDir ) {
			if ( aLeadingForwardSlash ) {
				builder.append( forwardSlash );
			}
			builder.append( dir );
			aLeadingForwardSlash = true;
		}
		return builder.toString();
	}
}
