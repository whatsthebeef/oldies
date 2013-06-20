package wtb.oddsminer.calculator.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import wtb.oddsminer.Constants;
import wtb.oddsminer.calculator.BettingCalculator;
import wtb.oddsminer.calculator.algorithm.BettingAlgorithm;
import wtb.oddsminer.calculator.algorithm.interfaces.IAlgorithm;
import wtb.oddsminer.calculator.intefaces.ICalculator;

public class BettingCalculatorTest {

	private ICalculator							mBettingCalculator	= null;

	private IAlgorithm							mBettingAlgorithm	= null;

	private Map<String, Map<String, String>>	mOdds				= null;
	private Map<String, String>					mBookiesOddsMap		= null;

	private static final double					KRESULT				= 0.16;

	private static final double					KDELTA				= 0.0;

	private static final String					KRUNNER_A			= "RunnerA";
	private static final String					KRUNNER_B			= "RunnerB";
	private static final String					KRUNNER_C			= "RunnerC";

	@Before
	public void before() {
		mBettingAlgorithm = new BettingAlgorithm();
		mBettingCalculator = new BettingCalculator();
		mOdds = new HashMap<String, Map<String, String>>();
		mBookiesOddsMap = new HashMap<String, String>();
		mBookiesOddsMap.put( Constants.KBETFAIR_BOOKIE_IDENTIFIER, "2.7" );
		mBookiesOddsMap.put( "VC", "2.2" );
		mBookiesOddsMap.put( "WH", "2.0" );
		mBookiesOddsMap.put( "B3", "2.2" );
		mBookiesOddsMap.put( "LB", "2.2" );
		mBookiesOddsMap.put( "SK", "2.2" );
		mBookiesOddsMap.put( "BX", "2.2" );
		mBookiesOddsMap.put( "BY", "2.4" );
		mBookiesOddsMap.put( "BD", "2.4" );
		mBookiesOddsMap.put( "SI", "1.8" );
		mBookiesOddsMap.put( "FR", "2.0" );
		mBookiesOddsMap.put( "EX", "2.0" );
		mOdds.put( KRUNNER_A, mBookiesOddsMap );
		mOdds.put( KRUNNER_B, new HashMap<String, String>() );
		mOdds.put( KRUNNER_C, new HashMap<String, String>() );

	}

	@Test
	public void testCalculate() {
		assertEquals( "not calculating correct value", KRESULT, mBettingCalculator.calculate(
				mOdds, mBettingAlgorithm ).get( KRUNNER_A ), KDELTA );
	}

	@Test
	public void testCalculateIncorrectOddsFormat() {
		mBookiesOddsMap.put( "EX", "wrong" );
		assertEquals( "not calculating correct value", KRESULT, mBettingCalculator.calculate(
				mOdds, mBettingAlgorithm ).get( KRUNNER_A ), KDELTA );
	}

	@Test
	public void testCalculateRunnerOddsMapNull() {
		mOdds.put( KRUNNER_C, null );
		assertEquals( "not calculating correct value", KRESULT, mBettingCalculator.calculate(
				mOdds, mBettingAlgorithm ).get( KRUNNER_A ), KDELTA );
	}

	@Test
	public void testCalculateNoBetfairOddsInAllRunners() {
		mBookiesOddsMap.remove( Constants.KBETFAIR_BOOKIE_IDENTIFIER );
		assertTrue( "Map isn't empty but should be", mBettingCalculator.calculate( mOdds,
				mBettingAlgorithm ).isEmpty() );
	}
}
