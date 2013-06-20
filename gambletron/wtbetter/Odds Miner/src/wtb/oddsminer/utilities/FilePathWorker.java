package wtb.oddsminer.utilities;

import java.util.ArrayList;
import java.util.List;

public class FilePathWorker {

	private static final String	KSPLIT					= "-v-";

	private static final int	KCHARS_IN_SPLIT			= 3;

	private static final int	KFILE_EXTENSION_LENGTH	= 4;

	/**
	 * static utility method to get list of teams from file name, removing all hyphens form the name
	 * 
	 * @param aFileName
	 *            - to extract teams from
	 * @return List<String> - list of teams, 1st team 1st element, 2nd team 2nd element
	 */
	public static List<String> getTeamsFromFileName( String aFileName ) {
		List<String> teams = null;

		StringBuilder builder = new StringBuilder( aFileName );
		int splitLocation = builder.indexOf( KSPLIT );
		if ( splitLocation != -1 ) {
			teams = new ArrayList<String>();
			teams.add( builder.substring( 0, splitLocation ) );
			// the number of characters in the split, get string after split
			teams.add( builder.substring( splitLocation + KCHARS_IN_SPLIT, builder.length()
					- KFILE_EXTENSION_LENGTH ) );
		}
		return teams;
	}
}
