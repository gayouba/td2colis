import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Double.max;

public class Pack {

    private int height;
    private int width;
    private int depth;
    private double weight;
    private static double limit_height= 229;
    private static double limit_width= 162;
    private static double limit_depth= 25;
    private static double limit_weight= 1.800;
    private static double limit_weight_coef= 17.59;
    private static double fixed_fees= 2.86;
    private static double monaco_rate= 1.087;
    private static double underweight_price=12.0;
    private static double overweight_price=21.62;
    private static double volume_price=1.43;


    public Pack(int height, int width, int depth, double weight) {

        this.height=height;
        this.width=width;
        this.depth=depth;
        this.weight=weight;

    }



    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    //calc volume in dm3 with dimensions in mm
    private double volume(int L, int H, int D){
        return (L*H*D*0.000001);
    }

    public double calculateLocalShippingCost(String location){

        double sum;

        if (this.width<=limit_width&&this.height<=limit_height&&this.depth<=limit_depth){
            if (location.equals("MC")) sum=underweight_price*monaco_rate;
            else sum=underweight_price;
        }
        else if (this.weight<=limit_weight){
            if (location.equals("MC"))sum= (limit_weight_coef*this.weight+fixed_fees)*monaco_rate;
            else sum=limit_weight_coef*this.weight+fixed_fees;
        }
        else {
            sum= max(this.weight*overweight_price,volume(this.width,this.height,this.depth)*volume_price);
            if (location.equals("MC"))sum=sum*monaco_rate;
        }


        return round(sum,2);
    }
}
