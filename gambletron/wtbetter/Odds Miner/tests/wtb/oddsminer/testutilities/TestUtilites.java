package wtb.oddsminer.testutilities;

import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TestUtilites {

	/**
	 * Will delete a directory even if it contains files
	 * 
	 * @param path
	 *            to directory for delete
	 * @return boolean - whether it was deleted or not
	 */
	public static boolean deleteDirectory( File aDir ) {
		if ( aDir.exists() ) {
			File[] files = aDir.listFiles();
			for ( int i = 0; i < files.length; i++ ) {
				if ( files[i].isDirectory() ) {
					deleteDirectory( files[i] );
				}
				else {
					files[i].delete();
				}
			}
		}
		return ( aDir.delete() );
	}

	/**
	 * Convert text in a file into a string
	 * 
	 * @param aFile
	 * @return String - file content
	 */
	public static String readFile( File aFile ) {
		StringBuilder contents = new StringBuilder();

		try {
			// Use buffering, reading one line at a time
			// FileReader always assumes default encoding is OK!
			BufferedReader input = new BufferedReader( new FileReader( aFile ) );
			try {
				// Not declared within while loop
				String line = null;
				while ( ( line = input.readLine() ) != null ) {
					contents.append( line.trim() );
				}
			}
			finally {
				input.close();
			}
		}
		catch ( IOException e ) {
			e.printStackTrace();
			fail( "Problem deleting files and dirs recursively" );
		}
		return contents.toString();
	}

}
