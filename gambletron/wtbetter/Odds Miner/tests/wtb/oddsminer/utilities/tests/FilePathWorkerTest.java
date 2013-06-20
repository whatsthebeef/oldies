package wtb.oddsminer.utilities.tests;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import wtb.oddsminer.utilities.FilePathWorker;

public class FilePathWorkerTest {

	private File				iOddsFile						= null;

	private static final String	KODDSFILE_STRING				= "testdata/odds/liverpool-v-leeds-utd.xml";

	private static final String	KODDSFILE_STRING_FUNNYFORMAT	= "testdata/odds/funnyformat.xml";

	private static final String	KLIVERPOOL						= "liverpool";

	private static final String	KLEEDSUTD						= "leeds-utd";

	@Before
	public void before() {

	}

	@Test
	public void testGetTeamsFileName() {
		iOddsFile = new File( KODDSFILE_STRING );
		assertEquals( FilePathWorker.getTeamsFromFileName( iOddsFile.getName() ).get( 0 ),
				KLIVERPOOL );
		assertEquals( FilePathWorker.getTeamsFromFileName( iOddsFile.getName() ).get( 1 ),
				KLEEDSUTD );
	}

	@Test
	public void testGetTeamsFileNameBadFileString() {
		iOddsFile = new File( KODDSFILE_STRING_FUNNYFORMAT );
		assertEquals( FilePathWorker.getTeamsFromFileName( iOddsFile.getName() ), null );

	}
}
