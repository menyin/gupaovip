package apche.commons.lang3demo;

import org.apache.commons.lang3.math.NumberUtils;

public class NumberUtilsDemo {
    public static void main(String[] args) {
        System.out.println(NumberUtils.isCreatable("123"));
        System.out.println(NumberUtils.isNumber("123"));
        System.out.println(NumberUtils.isDigits("123.1"));

        System.out.println(NumberUtils.toDouble("123.1"));
        System.out.println(NumberUtils.toDouble("null"));//如果字符串不能转换则返回默认值

        System.out.println(NumberUtils.createBigDecimal("33333333333333333333333333333333333"));

    }
}
