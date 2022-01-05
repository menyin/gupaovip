import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigObject;
import goupao.CC;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class Test1 {
    /**
     * 支持.conf、.properties、
     * 文件内容可以是properties形式（如：name=jack），也可以是json字符串（如：name:jack）,还可以两者混合（如：core.name=jack）
     * 启动demo时注意好配置-Dinit.conf参数
     * @param args
     */
    public static void main(String[] args) {

        //基本使用
        /*Config config = ConfigFactory.load();//扫描所有可用文件
        String initParamName = "init.conf";//这个字符串会转换为-Dinit.conf，作为启动参数名。
        if(config.hasPath(initParamName )){
            String string = config.getString(initParamName);//获取启动参数-Da.conf的值
            File file = new File(string);
            if (file.isFile()&&file.exists()) {
                Config initConifg = ConfigFactory.parseFile(file);
                config = initConifg.withFallback(config);//initConfig和并config，以initConfig为准
            }
        }
        System.out.println(config.getInt("init.age"));*/

        //使用接口类做映射
       /* System.out.println(CC.core.name);
        System.out.println(CC.core.age);
        System.out.println(CC.init.name);
        System.out.println(CC.init.age);*/


        /*String[] split = "".split(":");
        System.out.println(split[0]);*/

        try {
            URL url = new URL("http://www.baidu.com/user/action?id=234&name=jack");
            System.out.println(url.getPath());
            System.out.println(url.getQuery());
            System.out.println(url.getPort());
            System.out.println(url.getProtocol());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
