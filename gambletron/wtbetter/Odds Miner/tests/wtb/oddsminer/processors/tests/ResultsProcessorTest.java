package wtb.oddsminer.processors.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import wtb.oddsminer.calculator.BettingCalculator;
import wtb.oddsminer.calculator.algorithm.BettingAlgorithm;
import wtb.oddsminer.calculator.algorithm.interfaces.IAlgorithm;
import wtb.oddsminer.calculator.intefaces.ICalculator;
import wtb.oddsminer.exceptions.CantCreateResultsFileException;
import wtb.oddsminer.exceptions.NoOddsFilesToProcessException;
import wtb.oddsminer.exceptions.ResultsException;
import wtb.oddsminer.exceptions.SetupException;
import wtb.oddsminer.processor.interfaces.IOddsProcessor;
import wtb.oddsminer.processor.interfaces.IResultsProcessor;
import wtb.oddsminer.processors.OddsProcessor;
import wtb.oddsminer.processors.ResultsProcessor;
import wtb.oddsminer.testutilities.TestProperties;
import wtb.oddsminer.testutilities.TestUtilites;
import wtb.oddsminer.utilities.SetupUtilities;

public class ResultsProcessorTest {

	private IResultsProcessor	mResultsProcessor	= null;

	private ICalculator			mBettingCalculator	= null;

	private IAlgorithm			mBettingAlgorithm	= null;

	private IOddsProcessor		mOddsProcessor		= null;

	private static final String	KTEST_TIP			= "liverpool-v-leeds-utd.xml" + "Draw=0.49"
															+ "manchester-utd-v-leeds-utd.xml"
															+ "Leeds United=0.54";

	private File				file				= null;

	@Before
	public void before() {
		mResultsProcessor = new ResultsProcessor( new File( TestProperties.KTEST_RESULTS_FILE ) );
		mOddsProcessor = new OddsProcessor();
		TestUtilites.deleteDirectory( new File( TestProperties.KTEST_RESULTS_FILE_DIR ) );
		try {
			SetupUtilities.setupDirectoryStructure( TestProperties.KRESULTS_BASE_DIR );
		}
		catch ( SetupException e ) {
			e.printStackTrace();
			fail( "Can't create directories for results" );
		}
		file = new File( TestProperties.KTEST_RESULTS_FILE );
		mBettingAlgorithm = new BettingAlgorithm();
		mBettingCalculator = new BettingCalculator();
	}

	@Test
	public void testProcessFileCreation() {
		try {
			mResultsProcessor.process( TestProperties.KTEST_ODDS_DIR, mOddsProcessor,
					mBettingCalculator, mBettingAlgorithm );
		}
		catch ( ResultsException e ) {
			e.printStackTrace();
			fail( "Can't create results file" );
		}
		assertTrue( file.exists() );
	}

	/**
	 * tests that the correct tip is wrote to the results file
	 */
	@Test
	public void testProcessTip() {
		try {
			mResultsProcessor.process( TestProperties.KTEST_ODDS_DIR, mOddsProcessor,
					mBettingCalculator, mBettingAlgorithm );
		}
		catch ( ResultsException e ) {
			e.printStackTrace();
			fail( "Can't create results file" );
		}
		assertEquals( TestUtilites.readFile( file ), KTEST_TIP );

	}

	/**
	 * Check correct exception is thrown no odds file are present
	 */
	@Test
	public void testProcessCantReadOddsDir() {
		mResultsProcessor = new ResultsProcessor( new File( TestProperties.KTEST_RESULTS_FILE ) );
		try {
			mResultsProcessor.process( TestProperties.KNO_ODDS_DIR, mOddsProcessor,
					mBettingCalculator, mBettingAlgorithm );
			fail( "Should throw a exception as it shouldnt be able to create file" );
		}
		catch ( NoOddsFilesToProcessException e ) {
			e.printStackTrace();
		}
		catch ( ResultsException e ) {
			e.printStackTrace();
			fail( "Shouldn't be throwing any other exception other than NoOddsFilesToProcessException" );
		}
	}

	/**
	 * Check correct exception is thrown odds directory is read only
	 */
	@Test
	public void testProcessOddsDirReadOnly() {
		mResultsProcessor = new ResultsProcessor( new File( TestProperties.KTEST_RESULTS_FILE ) );
		try {
			mResultsProcessor.process( TestProperties.KODDS_DIR_READONLY, mOddsProcessor,
					mBettingCalculator, mBettingAlgorithm );
			fail( "Should throw a exception as it shouldnt be able to create file" );
		}
		catch ( NoOddsFilesToProcessException e ) {
			e.printStackTrace();
		}
		catch ( ResultsException e ) {
			e.printStackTrace();
			fail( "Shouldn't be throwing any other exception other than NoOddsFilesToProcessException" );
		}
	}

	/**
	 * Check correct exception is thrown if the file can't be created
	 */
	@Test
	public void testProcessCantCreateResultsFile() {
		mResultsProcessor = new ResultsProcessor( new File(
				TestProperties.KWRONG_LOCATION_FOR_RESULTS_FILE ) );
		try {
			mResultsProcessor.process( TestProperties.KTEST_ODDS_DIR, mOddsProcessor,
					mBettingCalculator, mBettingAlgorithm );
			fail( "Should throw a exception as it shouldnt be able to create file" );
		}
		catch ( CantCreateResultsFileException e ) {
			e.printStackTrace();
		}
		catch ( ResultsException e ) {
			e.printStackTrace();
			fail( "Shouldn't be throwing any other exception other than CantCreateResultsFileException" );
		}

	}
}
