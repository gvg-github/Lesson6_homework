import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by GVG on 02.09.2017.
 */
@RunWith(value = Parameterized.class)
public class MassTestMetods {

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(
                new Object[][]{
                        {new int[0], new int[]{4, 2, 3, 1, 1, 4}},
                        {new int[]{2, 3, 1, 1}, new int[]{4, 2, 3, 1, 1}},
                        {new int[]{1, 1}, new int[]{4, 2, 4, 1, 1}},
                }
        );
    }
    private static Main mainMetods;
    private int[] a;
    private int[] b;

    public MassTestMetods(int[] a, int[] b){
        this.a = a;
        this.b = b;
    }

    @BeforeClass
    public static void startTests(){
        mainMetods = new Main();
    }

    @Test
    public void testAfterFor() {
        Assert.assertArrayEquals(a, Main.afterFor(b));
    }
}

