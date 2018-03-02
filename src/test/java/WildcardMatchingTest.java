import org.testng.Assert;
import org.testng.annotations.Test;

public class WildcardMatchingTest {
    @Test
    public void test() {
        WildcardMatching matching = new WildcardMatching();
        Assert.assertFalse(matching.isMatch("aa", "a"));
        Assert.assertTrue(matching.isMatch("aa", "aa"));
        Assert.assertFalse(matching.isMatch("aaa", "aa"));
        Assert.assertTrue(matching.isMatch("aa", "*"));
        Assert.assertTrue(matching.isMatch("aa", "a*"));
        Assert.assertTrue(matching.isMatch("ab", "?*"));
        Assert.assertFalse(matching.isMatch("aab", "c*a*b"));
        Assert.assertTrue(matching.isMatch("aa", "*a"));
    }
}
