package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   MyDateAccesseurTest.class,
   MyDateUtilsTest.class,
   MyDateTest.class
})
public class Launcher {
}
