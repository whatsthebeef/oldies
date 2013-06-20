package wtb.oddsminer.processor.interfaces;

import java.util.List;

import wtb.oddsminer.exceptions.ProcessingException;

/**
 * 
 * This interface will expose methods which will allow for the processing of the events information
 * 
 * @author wtb
 * 
 */
public interface IProcessor {

	/**
	 * this method returns list of strings of events extracted form the events harvest
	 * 
	 * @param aEventHarvest
	 *            - the location of the events data to process
	 * 
	 * @return List<String> - String list of events to harvest data
	 * @throws ProcessingException
	 */
	public List<String> process( String aEventHarvest ) throws ProcessingException;

	/**
	 * this method returns list of strings of events extracted form the events harvest
	 * 
	 * @return List<String> - String list of events to harvest data
	 * @throws ProcessingException
	 * 
	 */
	public List<String> process() throws ProcessingException;
}
