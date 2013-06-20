package wtb.oddsminer.utilities.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import wtb.oddsminer.Properties;
import wtb.oddsminer.exceptions.SetupException;
import wtb.oddsminer.testutilities.TestUtilites;
import wtb.oddsminer.utilities.SetupUtilities;

public class SetupUtilitiesTest {

	private final static String	KFORWARD_SLASH					= "/";

	private final static String	KRESULTS_LOCATION_UNWRITABLE	= "/var";

	private final static String	KCHANGE_RESULTS_LOCATION		= "Try change results location";

	@Before
	public void before() {
		TestUtilites.deleteDirectory( new File( Properties.KBASE_DIR ) );
	}

	@After
	public void after() {

	}

	@Test
	public void testSetupDirectoryStructure() {
		try {
			SetupUtilities.setupDirectoryStructure( Properties.KROOT_DIR );
		}
		catch ( SetupException e ) {
			e.printStackTrace();
		}
		assertTrue( KCHANGE_RESULTS_LOCATION, new File( Properties.KROOT_DIR
				+ Properties.KRESULTS_DIR ).exists() );
		assertTrue( KCHANGE_RESULTS_LOCATION,
				new File( Properties.KROOT_DIR + Properties.KODDS_DIR ).exists() );
		assertTrue( KCHANGE_RESULTS_LOCATION, new File( Properties.KROOT_DIR
				+ Properties.KEVENTS_DIR ).exists() );

	}

	@Test
	public void testSetupDirectoryStructurePassingNull() {
		try {
			SetupUtilities.setupDirectoryStructure( null );
		}
		catch ( SetupException e ) {
			e.printStackTrace();
		}
		assertTrue( KCHANGE_RESULTS_LOCATION, new File( Properties.KROOT_DIR
				+ Properties.KRESULTS_DIR ).exists() );
		assertTrue( KCHANGE_RESULTS_LOCATION,
				new File( Properties.KROOT_DIR + Properties.KODDS_DIR ).exists() );
		assertTrue( KCHANGE_RESULTS_LOCATION, new File( Properties.KROOT_DIR
				+ Properties.KEVENTS_DIR ).exists() );

	}

	@Test
	public void testSetupDirectoryStructureThrowsCorrectException() {
		try {
			SetupUtilities.setupDirectoryStructure( KRESULTS_LOCATION_UNWRITABLE );
			fail();
		}
		catch ( SetupException e ) {
		}
		catch ( Exception e ) {
			fail();
		}
	}
}
