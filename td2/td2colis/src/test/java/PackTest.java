
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PackTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{

                { 191, 123, 18,  2.354, "FR", "12.0" },

                { 253, 215, 164, 1.565, "FR", "30.39" },

                { 653, 133, 271, 2.132, "FR", "46.09" },

                { 653, 331, 271, 3.650, "FR", "83.76" },

                { 191, 123, 18,  2.354, "MC", "13.04" },

                { 253, 215, 164, 1.565, "MC", "33.03" },

                { 653, 133, 271, 2.132, "MC", "50.1" },

                { 653, 331, 271, 3.650, "MC", "91.05" }

        });}


    static String actual;
    static String expected;



    public PackTest(int height, int width, int depth, double weight, String Loc, String result) {
        Pack pack1 = new Pack(height,width,depth,weight);
        actual=Double.toString(pack1.calculateLocalShippingCost(Loc));
        expected= result;
    }

    @Test
    public void Must_return_right_price_for_each_location(){

        assertEquals(expected,actual);
    }

}