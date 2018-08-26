import org.junit.Assert;
import org.junit.Test;

public class JavaTestExampleTest {
    @Test
    public void test1() {
        int a = 2;
        int b = 3;
        int  res = JavaTestExample.sumMet(a,b);
        Assert.assertTrue(res == (a + b));
    }
}
