public class MyPow {
    public double myPow(double x, int n) {
        long power = (n > 0) ? (long)n : -((long)n);
        double res = 1;
        double x2power = x;
        for(int i = 0; i < 32; i++) {
            if((power & 1) > 0) {
                res *= x2power;
            }
            x2power *= x2power;
            power >>= 1;
        }
        if(n < 0) {
            res = 1 / res;
        }
        return res;
    }

    public static void main(String[] args) {
        MyPow myPow = new MyPow();
        System.out.println(myPow.myPow(2, -1));
    }
}
