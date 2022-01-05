package apche.commons.lang3demo;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

public class DateUtilsDemo {
    public static void main(String[] args) throws InterruptedException, ParseException {
        //时间格式化
        //DateFormatUtils
        System.out.println(DateFormatUtils.format(new Date(), "yyy-MM-dd hh:mm:ss")); //hh是12小时制
        System.out.println(DateFormatUtils.format(new Date(), "yyy-MM-dd HH:mm:ss")); //HH是24小时制

        //时间计算
        //DateUtils
        System.out.println(DateUtils.addDays(new Date(), 2));
        System.out.println(DateUtils.addDays(new Date(), -2));
        System.out.println(DateFormatUtils.format(DateUtils.addDays(new Date(2019,1,28), 2),"yyy-MM-dd"));//注意 month的开始值为0

        //时间比较
        Date date = new Date();
        Thread.sleep(1000);
        System.out.println(date.before(new Date()));

        //字符串转时间对象
        System.out.println(DateUtils.parseDate("2013 02 01 07:23:11", "yyyy MM dd HH:mm:ss")); //？？这里居然吗，唯一将07点转为19点
        System.out.println(DateUtils.parseDateStrictly("2013 02 01 07:23:11", "yyyy MM dd HH:mm:ss"));
    }
}
