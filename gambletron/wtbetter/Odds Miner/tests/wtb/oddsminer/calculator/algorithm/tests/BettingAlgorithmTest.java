package wtb.oddsminer.calculator.algorithm.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import wtb.oddsminer.calculator.algorithm.BettingAlgorithm;
import wtb.oddsminer.calculator.algorithm.interfaces.IAlgorithm;

public class BettingAlgorithmTest {

	private IAlgorithm			mBettingAlgorithm	= null;

	private ArrayList<Double>	mOdds				= null;

	private ArrayList<Double>	mNonNumericOdds		= null;

	private Double				mBFOdds				= null;

	private static final double	KDELTA				= 0.0;

	private static final double	KRESULT				= 0.16;

	private static final double	KWRONG_INPUT_RESULT	= 0.19;

	@Before
	public void before() {
		mBettingAlgorithm = new BettingAlgorithm();

		mOdds = new ArrayList<Double>();
		mOdds.add( 2.2 );
		mOdds.add( 2.0 );
		mOdds.add( 2.2 );
		mOdds.add( 2.2 );
		mOdds.add( 2.2 );
		mOdds.add( 2.2 );
		mOdds.add( 2.4 );
		mOdds.add( 2.4 );
		mOdds.add( 1.8 );
		mOdds.add( 2.0 );
		mOdds.add( 2.0 );
		mOdds.add( 0.0 );
		mOdds.add( 0.0 );

		mNonNumericOdds = new ArrayList<Double>();
		mNonNumericOdds.add( 2.2 );
		mNonNumericOdds.add( -2.0 );
		mNonNumericOdds.add( 2.2 );
		mNonNumericOdds.add( 2.2 );
		mNonNumericOdds.add( 2.2 );
		mNonNumericOdds.add( 2.2 );
		mNonNumericOdds.add( 2.4 );
		mNonNumericOdds.add( 2.4 );
		mNonNumericOdds.add( 1.8 );
		mNonNumericOdds.add( 2.0 );
		mNonNumericOdds.add( 2.0 );

		mBFOdds = 2.7;

	}

	@Test
	public void testExecute() {
		assertEquals( KRESULT, mBettingAlgorithm.execute( mOdds, mBFOdds ), KDELTA );
	}

	@Test
	public void testExecuteWrongInputFormat() {
		assertEquals( "algorithm not returning correct value", KWRONG_INPUT_RESULT,
				mBettingAlgorithm.execute( mNonNumericOdds, mBFOdds ), KDELTA );
	}

	@Test
	public void testExecuteOnlyZerosInOtherOdds() {
		final ArrayList<Double> mOddsOnlyZeros = new ArrayList<Double>();
		mOddsOnlyZeros.add( 0.0 );
		mOddsOnlyZeros.add( 0.0 );
		mOddsOnlyZeros.add( 0.0 );
		mOddsOnlyZeros.add( 0.0 );
		mOddsOnlyZeros.add( 0.0 );

		assertEquals( "Only zeros in other odds should return zero", KDELTA, mBettingAlgorithm
				.execute( mOddsOnlyZeros, mBFOdds ), KDELTA );
	}

	@Test
	public void testExecuteBetfairOddsZero() {
		mBFOdds = 0.0;
		assertEquals( "Only zeros in other odds should return zero", KDELTA, mBettingAlgorithm
				.execute( mOdds, mBFOdds ), KDELTA );
	}

	@Test
	public void testExecuteEmptyOtherOddsList() {
		ArrayList<Double> mEmptyOddsList = new ArrayList<Double>();

		assertEquals( "Empty other odds should return zero", KDELTA, mBettingAlgorithm.execute(
				mEmptyOddsList, mBFOdds ), KDELTA );
	}

	@Test
	public void testExecuteNullOtherOddsList() {
		final ArrayList<Double> mNullList = null;

		assertEquals( "Null other odds should return zero", KDELTA, mBettingAlgorithm.execute(
				mNullList, mBFOdds ), KDELTA );
	}

	@Test
	public void testExecuteNoValuesLeftAfterFirstRemoval() {

		ArrayList<Double> mNoOddsLeftAfterPrepA = new ArrayList<Double>();
		mNoOddsLeftAfterPrepA.add( 1.0 );
		mNoOddsLeftAfterPrepA.add( 0.0 );
		mNoOddsLeftAfterPrepA.add( 0.0 );
		mNoOddsLeftAfterPrepA.add( 0.0 );
		mNoOddsLeftAfterPrepA.add( 0.0 );

		assertEquals(
				"After all the first non zero values are removed from the other odds list there is nothing left",
				KDELTA, mBettingAlgorithm.execute( mNoOddsLeftAfterPrepA, mBFOdds ), KDELTA );
	}

	@Test
	public void testExecuteNoValuesLeftAfterSecondRemoval() {
		ArrayList<Double> mNoOddsLeftAfterPrepB = new ArrayList<Double>();
		mNoOddsLeftAfterPrepB.add( 1.0 );
		mNoOddsLeftAfterPrepB.add( 2.0 );
		mNoOddsLeftAfterPrepB.add( 0.0 );
		mNoOddsLeftAfterPrepB.add( 0.0 );
		mNoOddsLeftAfterPrepB.add( 0.0 );

		assertEquals(
				"After all the second set of values are removed from the other odds list there is nothing left",
				KDELTA, mBettingAlgorithm.execute( mNoOddsLeftAfterPrepB, mBFOdds ), KDELTA );
	}

	@Test
	public void testFilter() {
		assertEquals( "the incorrect number of values of have been taken out", mBettingAlgorithm
				.filter( mOdds ).size(), 7 );
	}

	@Test
	public void testFilterWithNegative() {
		assertEquals( "the incorrect number of values of have been taken out, look for negative",
				mBettingAlgorithm.filter( mNonNumericOdds ).size(), 9 );
	}

	@Test
	public void testFilterWithOnlyNegatives() {
		ArrayList<Double> onlyNegatives = new ArrayList<Double>();
		onlyNegatives.add( -2.2 );
		onlyNegatives.add( -2.0 );
		onlyNegatives.add( 0.0 );

		assertEquals( "the incorrect number of values of have been taken out, look for negative",
				mBettingAlgorithm.filter( onlyNegatives ).size(), 0.0, KDELTA );
	}

	@Test
	public void testFilterOneValue() {

		ArrayList<Double> mNoOddsLeftAfterPrepA = new ArrayList<Double>();
		mNoOddsLeftAfterPrepA.add( 1.0 );
		mNoOddsLeftAfterPrepA.add( 1.0 );
		mNoOddsLeftAfterPrepA.add( 1.0 );
		mNoOddsLeftAfterPrepA.add( 0.0 );
		mNoOddsLeftAfterPrepA.add( 0.0 );
		mNoOddsLeftAfterPrepA.add( 0.0 );
		mNoOddsLeftAfterPrepA.add( 0.0 );
		mNoOddsLeftAfterPrepA.add( 0.0 );
		mNoOddsLeftAfterPrepA.add( 0.0 );

		assertEquals( "the incorrect number of values have been taken out, look for negative",
				mBettingAlgorithm.filter( mNoOddsLeftAfterPrepA ).size(), 0 );
	}

	@Test
	public void testFilterTwoValues() {
		ArrayList<Double> mNoOddsLeftAfterPrepB = new ArrayList<Double>();
		mNoOddsLeftAfterPrepB.add( 1.0 );
		mNoOddsLeftAfterPrepB.add( 1.0 );
		mNoOddsLeftAfterPrepB.add( 2.0 );
		mNoOddsLeftAfterPrepB.add( 2.2 );
		mNoOddsLeftAfterPrepB.add( 1.2 );
		mNoOddsLeftAfterPrepB.add( 2.2 );
		mNoOddsLeftAfterPrepB.add( 2.1 );
		mNoOddsLeftAfterPrepB.add( 2.0 );
		mNoOddsLeftAfterPrepB.add( 0.0 );
		mNoOddsLeftAfterPrepB.add( 0.0 );
		mNoOddsLeftAfterPrepB.add( 0.0 );
		mNoOddsLeftAfterPrepB.add( 0.0 );

		assertEquals( "the incorrect number of values of have been taken out, look for negative",
				mBettingAlgorithm.filter( mNoOddsLeftAfterPrepB ).get( 0 ), ( Double ) 2.0 );
	}

}
