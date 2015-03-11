package cartier.cartierTest.cartier;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ LoginTest.class, FirstPageTest.class, 
	    CarListPageTest.class, ViewAdPageTest.class, 
	    CollectionTest.class, SellCarTest.class,
		ZSettingTest.class })
public class AllTests {

}
