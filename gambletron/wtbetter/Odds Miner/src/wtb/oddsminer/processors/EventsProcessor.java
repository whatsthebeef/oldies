package wtb.oddsminer.processors;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import wtb.oddsminer.Properties;
import wtb.oddsminer.exceptions.NoEventsInputFilesToProcessException;
import wtb.oddsminer.exceptions.ProcessingException;
import wtb.oddsminer.processor.interfaces.IProcessor;

public class EventsProcessor implements IProcessor {

	private final Logger		log										= Logger
																				.getLogger( EventsProcessor.class );

	private static final String	KRESULTS_SECURITY_PROBLEM				= "Problem with security, check creation path etc...";

	private static final String	KRESULTS_NO_EVENTS_INPUT_FILES			= "No events files present in the events folder";

	private final static String	KINPUT_FILE_NOT_FOUND_EXCEPTION_MESSAGE	= "events input file not at specified location or not a directory";

	private final static String	KINPUT_MALFORMED_EVENTS_INPUT			= "malformed events input";

	private final static String	KLINK_ELEMENT_IDENTIFER					= "a";

	private final static String	KHREF_STRING							= "href";

	/**
	 * Member variables
	 */

	/**
	 * String location where the events file are to be processed
	 */
	private String				mEventHarvestFilesDir					= null;

	/**
	 * Constructor, Declares a String where the where the events will be processed from
	 * 
	 * @param aEventHarvestFile
	 */
	public EventsProcessor ( String aEventHarvestFileDir ) {
		mEventHarvestFilesDir = aEventHarvestFileDir;
	}

	/**
	 * Constructor, Uses a default file path where the events will be processed from
	 * 
	 */
	public EventsProcessor () {
		mEventHarvestFilesDir = Properties.KROOT_DIR + Properties.KEVENTS_DIR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wtb.oddsminer.processor.interfaces.IProcessor#process(java.lang.String)
	 */
	public List<String> process( String aEventHarvestFilesDir ) throws ProcessingException {

		if ( aEventHarvestFilesDir != null ) {
			mEventHarvestFilesDir = aEventHarvestFilesDir;
		}
		return this.process();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wtb.oddsminer.processor.interfaces.IProcessor#process()
	 */
	public List<String> process() throws ProcessingException {
		List<String> eventList = new ArrayList<String>();

		File eventHarvestFilesDir = new File( mEventHarvestFilesDir );
		if ( eventHarvestFilesDir.isDirectory() ) {
			if ( eventHarvestFilesDir.canRead() ) {
				File[] eventsFiles = eventHarvestFilesDir.listFiles();
				if ( eventsFiles.length != 0 ) {
					for ( File eventHarvestFile : eventHarvestFilesDir.listFiles() ) {

						DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
						try {
							DocumentBuilder builder = factory.newDocumentBuilder();
							Document document = builder.parse( eventHarvestFile );
							NodeList list = document.getElementsByTagName( KLINK_ELEMENT_IDENTIFER );
							for ( int i = 0; i < list.getLength(); i++ ) {
								eventList.add( list.item( i ).getAttributes().getNamedItem(
										KHREF_STRING ).getNodeValue() );
							}
						}
						catch ( ParserConfigurationException e ) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						catch ( SAXException e ) {
							log.error( KINPUT_MALFORMED_EVENTS_INPUT );
						}
						catch ( IOException e ) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				else {
					log.warn( KRESULTS_NO_EVENTS_INPUT_FILES );
					throw new NoEventsInputFilesToProcessException( KRESULTS_NO_EVENTS_INPUT_FILES );
				}
			}
			else {
				log.error( KRESULTS_SECURITY_PROBLEM );
				throw new ProcessingException( KRESULTS_SECURITY_PROBLEM );
			}
		}
		else {
			log.error( KINPUT_FILE_NOT_FOUND_EXCEPTION_MESSAGE );
			throw new ProcessingException( KINPUT_FILE_NOT_FOUND_EXCEPTION_MESSAGE );
		}
		return eventList;
	}

	/**
	 * @param mEventHarvestFile
	 *            the mEventHarvestFile to set
	 */
	public void setEventHarvestFile( String mEventHarvestFile ) {
		this.mEventHarvestFilesDir = mEventHarvestFile;
	}

	/**
	 * @return the mEventHarvestFile
	 */
	public String getEventHarvestFile() {
		return mEventHarvestFilesDir;
	}
}
