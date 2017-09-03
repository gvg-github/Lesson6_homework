import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by GVG on 02.09.2017.
 */
public class TestMetods {

    private static Main mainMetods;

    @BeforeClass
    public static void startTests(){
        mainMetods = new Main();
    }

    @Test
    public void testAfterFor() {
        Assert.assertArrayEquals(new int[0], Main.afterFor(new int[]{4, 2, 3, 1, 1, 4}));
        Assert.assertArrayEquals(new int[]{2, 3, 1, 1}, Main.afterFor(new int[]{4, 2, 3, 1, 1}));
    }

    @Test
    public void testHaveForOne() {
        Assert.assertEquals(false, Main.haveForOne(new int[]{4, 2, 3, 1, 1, 4}));
        Assert.assertEquals(true, Main.haveForOne(new int[]{4, 1, 1, 1, 1}));
    }
}
