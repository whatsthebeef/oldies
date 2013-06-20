package wtb.oddsminer.harvester.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import wtb.oddsminer.exceptions.HarvestException;
import wtb.oddsminer.exceptions.HarvestNoOddsOnPageException;
import wtb.oddsminer.exceptions.SetupException;
import wtb.oddsminer.harvester.OddsCheckerOddsHarvester;
import wtb.oddsminer.harvester.interfaces.IHarvester;
import wtb.oddsminer.testutilities.TestProperties;
import wtb.oddsminer.testutilities.TestUtilites;
import wtb.oddsminer.utilities.SetupUtilities;

/**
 * @author wtb
 */
public class OddsCheckerOddsHarvesterTest {

	private IHarvester			pageHarvester							= null;

	private File				file									= null;

	private static final String	KHARVEST_PRODUCT_FILE					= "testresults/var/odds/liverpool-v-leeds-utd.xml";

	private static final String	KHARVEST_CONFIG_FILE					= "resources/oddspage.xml";

	private static final String	KTEST_HOST								= "http://localhost";

	private static final String	KTEST_PATH								= "/liverpool-v-leeds-utd.html";

	private static final String	KTEST_URL_WINMARKET						= "http://localhost/event/win-market";

	private static final String	KTEST_PATH_DOESNT_EXIST					= "/doesntexist.html";

	private static final String	KHARVEST_CONFIG_FILE_DOESNT_EXIST		= "resources/pagesoddsdoesntexist.xml";

	private static final String	KHARVEST_PRODUCT_FILE_CONTENT_STRING	= "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
																				+ "<match>"
																				+ "<outCome winner=\"Liverpool\" bestOdds=\"1.95\">"
																				+ "<odds bookie=\"669028030_VC\" dodds_bslip=\"1.9\"/>"
																				+ "<odds bookie=\"669028030_SJ\" dodds_bslip=\"1.95\"/>"
																				+ "<odds bookie=\"669028030_BF\" dodds_bslip=\"1.99\"/>"
																				+ "<odds bookie=\"669028030_SI\" dodds_bslip=\"14.5-16\"/>"
																				+ "</outCome>"
																				+ "<outCome winner=\"Draw\" bestOdds=\"3.50\">"
																				+ "<odds bookie=\"669028033_WH\" dodds_bslip=\"3.3\"/>"
																				+ "<odds bookie=\"669028033_BF\" dodds_bslip=\"3.6\"/>"
																				+ "<odds bookie=\"669028033_BD\" dodds_bslip=\"3.6\"/>"
																				+ "<odds bookie=\"669028033_SI\" dodds_bslip=\"\"/>"
																				+ "</outCome>"
																				+ "<outCome winner=\"Manchester City\" bestOdds=\"4.33\">"
																				+ "<odds bookie=\"669028027_B3\" dodds_bslip=\"4.2\"/>"
																				+ "<odds bookie=\"669028027_IG\" dodds_bslip=\"3.75\"/>"
																				+ "<odds bookie=\"669028027_BF\" dodds_bslip=\"4.4\"/>"
																				+ "<odds bookie=\"669028027_SI\" dodds_bslip=\"8-9.5\"/>"
																				+ "</outCome>"
																				+ "</match>";

	private static final String	KTEST_URL								= "http://localhost/liverpool-v-leeds-utd.html";

	@Before
	public void before() {

		pageHarvester = new OddsCheckerOddsHarvester( TestProperties.KTEST_ODDS_RESULTS_DIR );

		TestUtilites.deleteDirectory( new File( TestProperties.KRESULTS_BASE_DIR ) );
		try {
			SetupUtilities.setupDirectoryStructure( TestProperties.KRESULTS_BASE_DIR );
		}
		catch ( SetupException e ) {
			e.printStackTrace();
			fail( "Couldn't create results dir structure" );
		}
	}

	@After
	public void after() {
	}

	@Test
	public void testHarvest() {

		file = new File( KHARVEST_PRODUCT_FILE );

		try {
			pageHarvester.harvest( KTEST_HOST, KTEST_PATH, KHARVEST_CONFIG_FILE );
		}
		catch ( Exception e ) {
			e.printStackTrace();
			fail();
		}
		assertTrue( file.exists() );

		assertEquals( TestUtilites.readFile( file ), KHARVEST_PRODUCT_FILE_CONTENT_STRING );
	}

	@Test
	public void testHarvestOverloadWinMarketUrlPassed() {

		file = new File( KHARVEST_PRODUCT_FILE );

		try {
			pageHarvester.harvest( KTEST_URL, KHARVEST_CONFIG_FILE );
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

		file = new File( KHARVEST_PRODUCT_FILE );

		try {
			pageHarvester.harvest( KTEST_URL, KHARVEST_CONFIG_FILE );
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
			pageHarvester.harvest( KTEST_HOST, KTEST_PATH, KHARVEST_CONFIG_FILE_DOESNT_EXIST );
		}
		catch ( HarvestException e ) {

		}
		catch ( Exception e ) {
			fail();
		}
	}

	// @Test
	public void testHarvestDataFieldDoesntExist() {

		try {
			pageHarvester.harvest( KTEST_HOST, KTEST_PATH_DOESNT_EXIST, KHARVEST_CONFIG_FILE );
			fail();
		}
		catch ( HarvestNoOddsOnPageException e ) {

		}
		catch ( Exception e ) {
			fail();
		}
	}
}
