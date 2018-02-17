import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TrappingRainWaterTest {

    @DataProvider(name = "testCases")
    public Object[][] testCases() {
        return new Object[][]{
                {6, new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}},
                {60, new int[]{0, 2, 1, 0, 2, 3, 1, 2, 0, 0, 1, 2, 1, 0, 4, 3, 2, 0, 1, 1, 3, 2, 1, 2, 6, 5, 7, 4, 3, 2, 5, 4, 6, 7, 5, 5, 4}}
        };
    }

    @Test(dataProvider = "testCases")
    public void test(int waterAmount, int[] height) {
        Assert.assertEquals(new TrappingRainWater().trap(height), waterAmount);
    }
}
