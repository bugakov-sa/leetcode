public class IntegerToRoman {

    public String intToRoman(int num) {

        String[] onesToRoman = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] tensToRoman = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] hundredsToRoman = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] thousandsToRoman = {"", "M", "MM", "MMM"};

        return new StringBuilder(thousandsToRoman[num / 1000])
                .append(hundredsToRoman[(num % 1000) / 100])
                .append(tensToRoman[(num % 100) / 10])
                .append(onesToRoman[num % 10])
                .toString();
    }

    public static void main(String[] args) {
        IntegerToRoman integerToRoman = new IntegerToRoman();
        System.out.println(integerToRoman.intToRoman(1994));
    }
}
