package wtb.oddsminer.utilities.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.net.MalformedURLException;

import org.junit.Test;

import wtb.oddsminer.utilities.UrlWorker;

public class UrlWorkerTest {

	private final static String	KTEST_URL						= "http://localhost/football/english/premier-league/wigan-v-sunderland";

	private final static String	KTEST_URL_WITH_HTML				= "http://localhost/football/english/premier-league/wigan-v-sunderland.html";

	private final static String	KTEST_URL_WITH_WINMARKET		= "http://localhost/football/english/premier-league/wigan-v-sunderland/win-market";

	private final static String	KTEST_HOST						= "http://localhost";

	private final static String	KTEST_FILENAME_NO_EXTENSION		= "wigan-v-sunderland";

	private final static String	KTEST_FILENAME_PATH2			= "wigan-v-sunderland";
	private final static String	KTEST_FILENAME_PATH3			= "match-win";
	private final static String	KTEST_FILENAME_PATH1			= "event";

	private final static String	KTEST_FILENAME_COMPLETE_PATH	= "event/wigan-v-sunderland/match-win";

	private final static String	KTEST_PATH						= "/football/english/premier-league/wigan-v-sunderland";

	@Test
	public void testGetProtocolAndHostFromUrl() {
		try {
			assertEquals( "string is wrong " + UrlWorker.getProtocolAndHostFromUrl( KTEST_URL ),
					KTEST_HOST, UrlWorker.getProtocolAndHostFromUrl( KTEST_URL ) );
		}
		catch ( MalformedURLException e ) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testGetPathFromUrl() {
		try {
			assertEquals( "string is wrong " + UrlWorker.getPathFromUrl( KTEST_URL ), KTEST_PATH,
					UrlWorker.getPathFromUrl( KTEST_URL ) );
		}
		catch ( MalformedURLException e ) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testGetFileNameWithoutExtension() {
		try {
			assertEquals( KTEST_FILENAME_NO_EXTENSION, UrlWorker
					.getFileNameWithoutExtension( KTEST_URL ) );
		}
		catch ( MalformedURLException e ) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testGetEventFromUrl() {
		try {
			assertEquals( KTEST_FILENAME_NO_EXTENSION, UrlWorker.getEventFromUrl( KTEST_URL ) );
			assertEquals( KTEST_FILENAME_NO_EXTENSION, UrlWorker
					.getEventFromUrl( KTEST_URL_WITH_WINMARKET ) );
			assertEquals( KTEST_FILENAME_NO_EXTENSION, UrlWorker
					.getEventFromUrl( KTEST_URL_WITH_HTML ) );
		}
		catch ( MalformedURLException e ) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testBuildPath() {
		assertEquals( UrlWorker.buildPath( KTEST_FILENAME_PATH1, KTEST_FILENAME_PATH2,
				KTEST_FILENAME_PATH3 ), KTEST_FILENAME_COMPLETE_PATH );
	}
}
