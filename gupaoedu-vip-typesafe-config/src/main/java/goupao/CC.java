package goupao;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;

public interface CC {
    Config cfg=load();
    static Config load(){
        Config config= ConfigFactory.load();
        String initParamName = "init.conf";//这个字符串会转换为-Dinit.conf，作为启动参数名。
        if(config.hasPath(initParamName )){
            String string = config.getString(initParamName);//获取启动参数-Da.conf的值
            File file = new File(string);
            if (file.isFile()&&file.exists()) {
                Config initConifg = ConfigFactory.parseFile(file);
                config = initConifg.withFallback(config);//initConfig和并config，以initConfig为准
            }
        }
        return config;
    }
    interface core{
        Config cfg = CC.cfg.getObject("core").toConfig();
        String name = cfg.getString("name");
        int age = cfg.getInt("age");
    }

    interface init{
        Config cfg = CC.cfg.getObject("init").toConfig();
        String name = cfg.getString("name");
        int age = cfg.getInt("age");
    }
}
