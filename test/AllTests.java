import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({VailidateAddress.class, ValidateNameTest.class, ValidatePhoneNumberTest.class})
public class AllTests {

}
