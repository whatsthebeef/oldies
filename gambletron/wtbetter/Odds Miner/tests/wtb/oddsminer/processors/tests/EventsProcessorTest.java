package wtb.oddsminer.processors.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import wtb.oddsminer.exceptions.NoEventsInputFilesToProcessException;
import wtb.oddsminer.exceptions.ProcessingException;
import wtb.oddsminer.processor.interfaces.IProcessor;
import wtb.oddsminer.processors.EventsProcessor;

public class EventsProcessorTest {

	private final static String	KTEST_XML									= "testdata/events.xml";

	private final static String	KTEST_EVENT_INPUT_FILE_DIR					= "testdata/eventsinputfile";

	private static final String	KTEST_EVENT_INPUT_FILE_DIR_XML_DOESNT_EXIST	= "testdata/eventsinputfiledoesntexist";

	private static final String	KTEST_XML_MALFORMED							= "testdata/eventsinputfilemalformed";

	private IProcessor			eventsProcessor								= null;

	private File				file										= null;

	// private final static String KEVENTS_FILE_CONTENT =
	// "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
	// + "<matches>"
	// + "<a href=\"http://localhost/match.html\"/>"
	// + "<a href=\"http://localhost/football/english/premier-league//wigan-v-sunderland\"/>"
	// + "<a href=\"http://localhost/football/english/premier-league//aston-villa-v-tottenham\"/>"
	// +
	// "<a href=\"http://localhost/football/english/premier-league//wolverhampton-v-birmingham\"/>"
	// + "<a href=\"http://localhost/football/english/premier-league//everton-v-liverpool\"/>"
	// + "<a href=\"http://localhost/football/english/premier-league//arsenal-v-chelsea\"/>"
	// + "</matches>";

	private final static String	KEVENTS_LIST_SECOND_ELEMENT					= "http://localhost/football/english/premier-league//aston-villa-v-tottenham";

	@Before
	public void before() {

		eventsProcessor = new EventsProcessor( KTEST_XML );

	}

	@Test
	public void testProcess() {

		file = new File( KTEST_EVENT_INPUT_FILE_DIR );
		assertTrue( file.exists() );

		List<String> processedEvents = null;
		try {
			processedEvents = eventsProcessor.process( KTEST_EVENT_INPUT_FILE_DIR );
		}
		catch ( ProcessingException e ) {
			e.printStackTrace();
			fail();
		}

		assertEquals( processedEvents.size(), 6 );
		assertEquals( "wrong string " + processedEvents.get( 2 ), processedEvents.get( 2 ),
				KEVENTS_LIST_SECOND_ELEMENT );
	}

	@Test
	public void testProcessOverload() {

		eventsProcessor = new EventsProcessor( KTEST_EVENT_INPUT_FILE_DIR );

		file = new File( KTEST_EVENT_INPUT_FILE_DIR );
		assertTrue( file.exists() );

		List<String> processedEvents = null;
		try {
			processedEvents = eventsProcessor.process();
		}
		catch ( ProcessingException e ) {
			e.printStackTrace();
			fail();
		}

		assertEquals( processedEvents.size(), 6 );
		assertEquals( "wrong string " + processedEvents.get( 2 ), processedEvents.get( 2 ),
				KEVENTS_LIST_SECOND_ELEMENT );

	}

	@Test
	public void testProcessEventsInputDoesntExist() {

		file = new File( KTEST_EVENT_INPUT_FILE_DIR_XML_DOESNT_EXIST );
		assertTrue( file.exists() );

		try {
			eventsProcessor.process( KTEST_EVENT_INPUT_FILE_DIR_XML_DOESNT_EXIST );
			fail();
		}
		catch ( NoEventsInputFilesToProcessException e ) {
		}
		catch ( ProcessingException e ) {
			e.printStackTrace();
			fail();
		}

	}

	@Test
	public void testProcessEventsMalformedInput() {

		file = new File( KTEST_EVENT_INPUT_FILE_DIR );
		assertTrue( file.exists() );

		try {
			eventsProcessor.process( KTEST_XML_MALFORMED );
		}
		catch ( ProcessingException e ) {
			e.printStackTrace();
			fail();
		}

	}

	@Test
	public void testProcessPassingNonDir() {

		file = new File( KTEST_XML );
		assertTrue( file.exists() );

		try {
			eventsProcessor.process( KTEST_XML );
			fail();
		}
		catch ( ProcessingException e ) {
			e.printStackTrace();
		}

	}

}
