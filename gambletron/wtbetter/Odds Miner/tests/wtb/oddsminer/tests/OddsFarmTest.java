package wtb.oddsminer.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import wtb.oddsminer.OddsFarm;
import wtb.oddsminer.calculator.BettingCalculator;
import wtb.oddsminer.calculator.algorithm.BettingAlgorithm;
import wtb.oddsminer.calculator.algorithm.interfaces.IAlgorithm;
import wtb.oddsminer.calculator.intefaces.ICalculator;
import wtb.oddsminer.exceptions.SetupException;
import wtb.oddsminer.harvester.OddsCheckerEventsHarvester;
import wtb.oddsminer.harvester.OddsCheckerOddsHarvester;
import wtb.oddsminer.harvester.interfaces.IHarvester;
import wtb.oddsminer.interfaces.IOddsFarm;
import wtb.oddsminer.processor.interfaces.IOddsProcessor;
import wtb.oddsminer.processor.interfaces.IProcessor;
import wtb.oddsminer.processor.interfaces.IResultsProcessor;
import wtb.oddsminer.processors.EventsProcessor;
import wtb.oddsminer.processors.OddsProcessor;
import wtb.oddsminer.processors.ResultsProcessor;
import wtb.oddsminer.testutilities.TestProperties;
import wtb.oddsminer.testutilities.TestUtilites;
import wtb.oddsminer.utilities.SetupUtilities;

public class OddsFarmTest {

	private static final String	KHARVEST_ODDS_PRODUCT_FILE_1			= "testresults/var/odds/liverpool-v-leeds-utd.xml";
	private static final String	KHARVEST_ODDS_PRODUCT_FILE_2			= "testresults/var/odds/aston-villa-v-tottenham.xml";
	private static final String	KHARVEST_ODDS_PRODUCT_FILE_3			= "testresults/var/odds/wolverhampton-v-birmingham.xml";
	private static final String	KHARVEST_ODDS_PRODUCT_FILE_4			= "testresults/var/odds/everton-v-liverpool.xml";
	private static final String	KHARVEST_ODDS_PRODUCT_FILE_5			= "testresults/var/odds/wigan-v-sunderland.xml";
	private static final String	KHARVEST_ODDS_PRODUCT_FILE_6			= "testresults/var/odds/arsenal-v-chelsea.xml";

	private static final String	KTEST_PATH								= "/allmatches.html";

	private static final String	KTEST_HOST								= "http://localhost";

	private IHarvester			pageHarvester							= null;

	private IHarvester			eventsHarvester							= null;

	private IProcessor			eventsProcessor							= null;

	private IOddsProcessor		mOddsProcessor							= null;
	private ICalculator			mBetCalculator							= null;
	private IAlgorithm			mBettingAlgorithm						= null;
	private IResultsProcessor	mResultProcessor						= null;

	private IOddsFarm			farm									= null;

	private File				file									= null;
	private File				matchFile								= null;
	private File				file2									= null;
	private File				file3									= null;
	private File				file4									= null;
	private File				file5									= null;
	private File				file6									= null;

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

	@Before
	public void before() {

		farm = new OddsFarm();
		pageHarvester = new OddsCheckerOddsHarvester( TestProperties.KTEST_ODDS_RESULTS_DIR );
		eventsHarvester = new OddsCheckerEventsHarvester( TestProperties.KEVENT_PRODUCT_FILE_DIR );
		eventsProcessor = new EventsProcessor( TestProperties.KEVENT_PRODUCT_FILE_DIR );
		mOddsProcessor = new OddsProcessor();
		mBetCalculator = new BettingCalculator();
		mBettingAlgorithm = new BettingAlgorithm();
		mResultProcessor = new ResultsProcessor( TestProperties.KTEST_ODDS_RESULTS_DIR, new File(
				TestProperties.KTEST_RESULTS_FILE ) );

		TestUtilites.deleteDirectory( new File( TestProperties.KRESULTS_BASE_DIR ) );

		try {
			SetupUtilities.setupDirectoryStructure( TestProperties.KRESULTS_BASE_DIR );
		}
		catch ( SetupException e ) {
			e.printStackTrace();
			fail( "Setup failed when creating directories" );

		}
	}

	@After
	public void after() {

	}

	@Test
	public void testFarm() {
		file = new File( TestProperties.KEVENT_PRODUCT_FILE );
		matchFile = new File( KHARVEST_ODDS_PRODUCT_FILE_1 );
		file2 = new File( KHARVEST_ODDS_PRODUCT_FILE_2 );
		file3 = new File( KHARVEST_ODDS_PRODUCT_FILE_3 );
		file4 = new File( KHARVEST_ODDS_PRODUCT_FILE_4 );
		file5 = new File( KHARVEST_ODDS_PRODUCT_FILE_5 );
		file6 = new File( KHARVEST_ODDS_PRODUCT_FILE_6 );

		try {
			farm.farm( eventsHarvester, pageHarvester, eventsProcessor, mResultProcessor,
					mOddsProcessor, mBetCalculator, mBettingAlgorithm, KTEST_HOST, KTEST_PATH );
		}
		catch ( Exception e ) {
			e.printStackTrace();
			fail();
		}

		assertTrue( file.getAbsolutePath(), file.exists() );
		assertTrue( matchFile.getAbsolutePath(), matchFile.exists() );
		assertTrue( file2.getAbsolutePath(), file2.exists() );
		assertTrue( file3.getAbsolutePath(), file3.exists() );
		assertTrue( file4.getAbsolutePath(), file4.exists() );
		assertTrue( file5.getAbsolutePath(), file5.exists() );
		assertTrue( file6.getAbsolutePath(), file6.exists() );

		assertEquals( TestUtilites.readFile( matchFile ), KHARVEST_PRODUCT_FILE_CONTENT_STRING );
	}

}
