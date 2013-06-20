package wtb.oddsminer.processors.tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import wtb.oddsminer.Constants;
import wtb.oddsminer.processor.interfaces.IOddsProcessor;
import wtb.oddsminer.processors.OddsProcessor;

public class OddsProcessorTest {

	private IOddsProcessor		oddsProcessor				= null;

	private File				oddsFile					= null;

	private static final String	KTEST_ODDS_XML				= "testdata/odds/manchester-utd-v-leeds-utd.xml";

	private static final String	KTEST_VICTORCHANDLER_KEY	= "VC";

	private static final String	KTEST_WILLIAMHILL_KEY		= "WH";

	private static final String	KTEST_LEEDSUTD_KEY			= "Leeds United";

	private static final String	KTEST_MANCHESTERUTD_KEY		= "Manchester United";

	private static final String	KTEST_DRAW_KEY				= "Draw";

	@Before
	public void before() {
		oddsProcessor = new OddsProcessor();
		oddsFile = new File( KTEST_ODDS_XML );
	}

	@Test
	public void testProcess() {
		Map<String, Map<String, String>> odds = oddsProcessor.process( oddsFile );
		assertEquals( odds.get( KTEST_LEEDSUTD_KEY ).get( Constants.KBETFAIR_BOOKIE_IDENTIFIER ),
				"10.0" );
		assertEquals( odds.get( KTEST_MANCHESTERUTD_KEY ).get( KTEST_VICTORCHANDLER_KEY ), "1.9" );
		assertEquals( odds.get( KTEST_DRAW_KEY ).get( KTEST_WILLIAMHILL_KEY ), "3.3" );
	}
}
