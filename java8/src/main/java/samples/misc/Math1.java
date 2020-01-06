package samples.misc;

import org.junit.Test;

public class Math1 {

    @Test
    public void t1(){
        System.out.println(Integer.parseUnsignedInt("123"));
    }

    /**
     * 强转正整数
     */
    @Test
    public void testUnsignedInt() {
        try {
            Integer.parseUnsignedInt("-123", 10);
        }
        catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        long maxUnsignedInt = (1l << 32) - 1;
        System.out.println(maxUnsignedInt);

        String string = String.valueOf(maxUnsignedInt);

        int unsignedInt = Integer.parseUnsignedInt(string, 10);
        System.out.println(unsignedInt);

        String string2 = Integer.toUnsignedString(unsignedInt, 10);
        System.out.println(string2);

        try {
            Integer.parseInt(string, 10);
        }
        catch (NumberFormatException e) {
            System.err.println("could not parse signed int of " + maxUnsignedInt);
        }
    }

    /**
     * 2147483647
     * -2147483648
     * -2147483647
     * integer overflow
     * integer overflow
     */
    @Test
    public void testMathExact() {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE + 1);
        System.out.println(Integer.MAX_VALUE + 2);

        try {
            Math.addExact(Integer.MAX_VALUE, 1); // addExact 函数相加
        }
        catch (ArithmeticException e) {
            System.err.println(e.getMessage());
        }

        try {
            Math.toIntExact(Long.MAX_VALUE); // toIntExact 返回参数值
        }
        catch (ArithmeticException e) {
            System.err.println(e.getMessage());
        }
    }
}
