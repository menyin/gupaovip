package apche.commons.lang3demo;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

//详见commons-lang3-3.2.jar中的常用工具类的使用
public class StringUtilsDemo {
    public static void main(String[] args) {
        /*验证*/
        //xxEmpty方法验证null和"" 但是验证不了"   "
        //xxBlank方法验证null和""及"   "
        //System.out.println(StringUtils.isBlank(""));
        //System.out.println(StringUtils.isNoneEmpty(null));
        //System.out.println(StringUtils.isNoneEmpty(""));
        //System.out.println(StringUtils.isBlank(null));
        //System.out.println(StringUtils.isAllLowerCase("aaa"));
        //System.out.println(StringUtils.isAllUpperCase("AAA"));

        //截取
        System.out.println(StringUtils.substring("0123456", 1));

        //合并
        List list = new ArrayList(2);
        list.add("张三");
        list.add("李四");
        list.add("王五");
        System.out.println(StringUtils.join(list, ","));

    }
}
