package wtb.oddsminer.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( { OddsFarmTest.class, wtb.oddsminer.harvester.tests.AllTests.class,
		wtb.oddsminer.processors.tests.AllTests.class,
		wtb.oddsminer.utilities.tests.AllTests.class,
		wtb.oddsminer.calculator.algorithm.tests.AllTests.class,
		wtb.oddsminer.calculator.tests.AllTests.class })
public class AllTests {
}
