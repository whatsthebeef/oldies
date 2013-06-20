package wtb.oddsminer.harvester.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import wtb.oddsminer.exceptions.HarvestException;
import wtb.oddsminer.exceptions.HarvestFieldLocationIncorrectException;
import wtb.oddsminer.exceptions.SetupException;
import wtb.oddsminer.harvester.OddsCheckerEventsHarvester;
import wtb.oddsminer.harvester.interfaces.IHarvester;
import wtb.oddsminer.testutilities.TestProperties;
import wtb.oddsminer.testutilities.TestUtilites;
import wtb.oddsminer.utilities.SetupUtilities;

public class OddsCheckerEventsHarvesterTest {

	private File				file									= null;

	private static final String	KHARVEST_CONFIG_FILE					= "resources/eventspage.xml";

	private static final String	KTEST_HOST								= "http://localhost";

	private static final String	KTEST_PATH								= "/allmatches.html";

	private static final String	KTEST_URL								= "http://localhost/allmatches.html";

	private static final String	KTEST_URL_MALFORMED_MESS				= "httlocalhost/allmatches.html";

	private static final String	KTEST_URL_MALFORMED_NO_BACKSLASH		= "http:localhost/allmatches.html";

	private static final String	KTEST_PATH_DOESNT_EXIST					= "/doesntexist.html";

	private static final String	KHARVEST_CONFIG_FILE_DOESNT_EXIST		= "resources/pagesoddsdoesntexist.xml";

	private static final String	KHARVEST_PRODUCT_FILE_CONTENT_STRING	= "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
																				+ "<matches>"
																				+ "<a href=\"http://localhost/liverpool-v-leeds-utd.html\"/>"
																				+ "<a href=\"http://localhost/football/english/premier-league//wigan-v-sunderland\"/>"
																				+ "<a href=\"http://localhost/football/english/premier-league//aston-villa-v-tottenham\"/>"
																				+ "<a href=\"http://localhost/football/english/premier-league//wolverhampton-v-birmingham\"/>"
																				+ "<a href=\"http://localhost/football/english/premier-league//everton-v-liverpool\"/>"
																				+ "<a href=\"http://localhost/football/english/premier-league//arsenal-v-chelsea\"/>"
																				+ "</matches>";

	private IHarvester			eventsHarvester							= null;

	@Before
	public void before() {

		eventsHarvester = new OddsCheckerEventsHarvester( TestProperties.KEVENT_PRODUCT_FILE_DIR );

		TestUtilites.deleteDirectory( new File( TestProperties.KRESULTS_BASE_DIR ) );

		try {
			SetupUtilities.setupDirectoryStructure( TestProperties.KRESULTS_BASE_DIR );
		}
		catch ( SetupException e ) {
			e.printStackTrace();
			fail( "Setup failed, can't create directory structure" );
		}
	}

	@After
	public void after() {

	}

	@Test
	public void testHarvest() {

		file = new File( TestProperties.KEVENT_PRODUCT_FILE );

		try {
			eventsHarvester.harvest( KTEST_HOST, KTEST_PATH, KHARVEST_CONFIG_FILE );
		}
		catch ( Exception e ) {
			e.printStackTrace();
			fail();
		}

		assertTrue( file.exists() );

		assertEquals( TestUtilites.readFile( file ), KHARVEST_PRODUCT_FILE_CONTENT_STRING );

	}

	@Test
	public void testHarvestOverloadUrlPassed() {

		file = new File( TestProperties.KEVENT_PRODUCT_FILE );

		try {
			eventsHarvester.harvest( KTEST_URL, KHARVEST_CONFIG_FILE );
		}
		catch ( Exception e ) {
			e.printStackTrace();
			fail();
		}

		assertTrue( file.exists() );

		assertEquals( TestUtilites.readFile( file ), KHARVEST_PRODUCT_FILE_CONTENT_STRING );

	}

	@Test
	public void testHarvestConfigDoesntExist() {

		try {
			eventsHarvester.harvest( KTEST_HOST, KTEST_PATH, KHARVEST_CONFIG_FILE_DOESNT_EXIST );
		}
		catch ( HarvestException e ) {

		}
		catch ( Exception e ) {
			fail();
		}
	}

	// TODO Look into whether this tests needs sorting out and is valid
	// @Test
	public void testHarvestDataFieldDoesntExist() {

		try {
			eventsHarvester.harvest( KTEST_HOST, KTEST_PATH_DOESNT_EXIST, KHARVEST_CONFIG_FILE );
			fail();
		}
		catch ( HarvestException e ) {

		}
		catch ( Exception e ) {
			fail();
		}
	}

	// TODO looks like defect into library, verify
	// @Test
	public void testHarvestMalformedFieldUrlMoForwardSlash() {

		try {
			eventsHarvester.harvest( KTEST_URL_MALFORMED_NO_BACKSLASH, KHARVEST_CONFIG_FILE );
			fail();
		}
		catch ( HarvestException e ) {

		}
		catch ( Exception e ) {
			fail();
		}
	}

	@Test
	public void testHarvestMalformedFieldUrlMess() {

		try {
			eventsHarvester.harvest( KTEST_URL_MALFORMED_MESS, KHARVEST_CONFIG_FILE );
			fail();
		}
		catch ( HarvestFieldLocationIncorrectException e ) {

		}
		catch ( Exception e ) {
			fail();
		}
	}

}
