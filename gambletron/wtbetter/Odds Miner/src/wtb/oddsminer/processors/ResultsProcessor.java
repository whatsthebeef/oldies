package wtb.oddsminer.processors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;

import wtb.oddsminer.Properties;
import wtb.oddsminer.calculator.algorithm.interfaces.IAlgorithm;
import wtb.oddsminer.calculator.intefaces.ICalculator;
import wtb.oddsminer.exceptions.CantCreateResultsFileException;
import wtb.oddsminer.exceptions.NoOddsFilesToProcessException;
import wtb.oddsminer.exceptions.ResultsException;
import wtb.oddsminer.processor.interfaces.IOddsProcessor;
import wtb.oddsminer.processor.interfaces.IResultsProcessor;

public class ResultsProcessor implements IResultsProcessor {

	/** Logging Tag */
	private final Logger		log								= Logger
																		.getLogger( ResultsProcessor.class );

	private static final String	KEQUALS							= "=";

	private static final String	KNEWLINE						= "\n";

	private static final String	KRESULTS_FILE_CREATION_FAILURE	= "Can't create results file, check creation path etc...";

	private static final String	KRESULTS_SECURITY_PROBLEM		= "Problem with security, check creation path etc...";

	private static final String	KRESULTS_FILE_WRITING_PROBLEM	= "Problem writing results to results file";

	private static final String	KRESULTS_CANT_CLOSE_STREAM		= "Problem closing stream";

	private static final String	KRESULTS_NO_ODDS_FILES			= "No odds files present in the odds folder";

	/**
	 * Member variables
	 */

	/**
	 * File object where results file will
	 */
	private File				mResultsFileLocation			= null;

	/**
	 * String path to the location of the odds files
	 */
	private String				mOddsDir						= null;

	/**
	 * Constructor, Declares a File object where the results will be attempted to be wrote too
	 * 
	 * @param aResultFileLocation
	 * 
	 * @param aOddsDir
	 */
	public ResultsProcessor ( String aOddsDir, File aResultFileLocation ) {
		if ( aOddsDir != null ) {
			mOddsDir = aOddsDir;
		}
		else {
			mOddsDir = Properties.KROOT_DIR + Properties.KEVENTS_DIR;
		}
		mResultsFileLocation = aResultFileLocation;
	}

	/**
	 * Constructor, Declares a File object where the results will be attempted to be wrote too
	 * 
	 * @param aResultFileLocation
	 * 
	 */
	public ResultsProcessor ( File aResultFileLocation ) {
		mOddsDir = Properties.KROOT_DIR + Properties.KEVENTS_DIR;
		mResultsFileLocation = aResultFileLocation;
	}

	/**
	 * Constructor, default File object where the results will be attempted to be wrote too
	 */
	public ResultsProcessor () {
		mOddsDir = Properties.KROOT_DIR + Properties.KEVENTS_DIR;
		mResultsFileLocation = new File( Properties.KROOT_DIR + Properties.KRESULTS_PATH );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wtb.oddsminer.processor.interfaces.IResultsProcessor#process(java.lang.String,
	 * wtb.oddsminer.processor.interfaces.IOddsProcessor,
	 * wtb.oddsminer.calculator.intefaces.ICalculator,
	 * wtb.oddsminer.calculator.algorithm.interfaces.IAlgorithm)
	 */
	public void process( IOddsProcessor aOddsProcessor, ICalculator aBettingCalculator,
			IAlgorithm aBettingAlgorithm ) throws ResultsException {
		FileOutputStream fileOutputStream = null;
		File oddsDir = null;

		try {
			try {
				mResultsFileLocation.createNewFile();
			}
			catch ( IOException e ) {
				log.error( KRESULTS_FILE_CREATION_FAILURE, new FileNotFoundException() );
				throw new CantCreateResultsFileException( KRESULTS_FILE_CREATION_FAILURE, e );
			}
			StringBuilder stringBuilder = new StringBuilder();
			fileOutputStream = new FileOutputStream( mResultsFileLocation );
			oddsDir = new File( mOddsDir );
			if ( oddsDir != null ) {
				if ( oddsDir.canRead() ) {
					for ( File oddsFile : oddsDir.listFiles() ) {
						stringBuilder.append( oddsFile.getName() ).append( KNEWLINE );
						Map<String, Double> runnersPercentageDifference = aBettingCalculator
								.calculate( aOddsProcessor.process( oddsFile ), aBettingAlgorithm );
						for ( String runner : runnersPercentageDifference.keySet() ) {
							Double percentage = runnersPercentageDifference.get( runner );
							// percentage diff should be greater than 0.2 to be identified
							if ( percentage > 0.05 ) {
								stringBuilder.append( runner ).append( KEQUALS ).append(
										percentage.toString() ).append( KNEWLINE );
							}
						}
					}
					fileOutputStream.write( stringBuilder.toString().getBytes() );
					fileOutputStream.close();
				}
				else {
					log.error( KRESULTS_NO_ODDS_FILES );
					throw new NoOddsFilesToProcessException( KRESULTS_NO_ODDS_FILES );
				}
			}
		}
		catch ( SecurityException e ) {
			e.printStackTrace();
			log.error( KRESULTS_SECURITY_PROBLEM, new SecurityException() );
			throw new ResultsException( KRESULTS_SECURITY_PROBLEM, e );
		}
		catch ( FileNotFoundException e ) {
			e.printStackTrace();
			log.error( KRESULTS_FILE_CREATION_FAILURE, new FileNotFoundException() );
			throw new CantCreateResultsFileException( KRESULTS_FILE_CREATION_FAILURE, e );
		}
		catch ( IOException e ) {
			e.printStackTrace();
			log.error( KRESULTS_FILE_WRITING_PROBLEM, new IOException() );
			try {
				fileOutputStream.close();
			}
			catch ( IOException ioe ) {
				log.error( KRESULTS_CANT_CLOSE_STREAM, new FileNotFoundException() );
				// if the stream can't close it can't close, we have to carry on and throw the
				// exception
			}
			throw new ResultsException( KRESULTS_FILE_WRITING_PROBLEM, e );
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wtb.oddsminer.processor.interfaces.IResultsProcessor#process(java.lang.String,
	 * wtb.oddsminer.processor.interfaces.IOddsProcessor,
	 * wtb.oddsminer.calculator.intefaces.ICalculator,
	 * wtb.oddsminer.calculator.algorithm.interfaces.IAlgorithm)
	 */
	public void process( String aOddsDir, IOddsProcessor aOddsProcessor,
			ICalculator aBettingCalculator, IAlgorithm aBettingAlgorithm ) throws ResultsException {

		mOddsDir = aOddsDir;

		this.process( aOddsProcessor, aBettingCalculator, aBettingAlgorithm );

	}

	/**
	 * @return the mOddsDir
	 */
	public String getOddsDir() {
		return mOddsDir;
	}

	/**
	 * @param set
	 *            the mOddsDir
	 */
	public void setOddsDir( String aOddsDir ) {
		mOddsDir = aOddsDir;
	}
}
