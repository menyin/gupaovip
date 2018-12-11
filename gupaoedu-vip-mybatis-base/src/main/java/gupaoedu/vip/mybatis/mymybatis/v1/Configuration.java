package gupaoedu.vip.mybatis.mymybatis.v1;

import java.util.HashMap;
import java.util.Map;

public class Configuration {
    private final static Map<String, Map<String,MethodMapper>> map= new HashMap<String,Map<String, MethodMapper>>();
    static {
        HashMap methodMappers = new HashMap<String,MethodMapper>();
        methodMappers.put("getPersonById",new MethodMapper("gupaoedu.vip.mybatis.mymybatis.v1.PersonMapper",
                "select * from person where id = #{id}",
                "getPersonById",
                "gupaoedu.vip.mybatis.mymybatis.v1.entity.Person"
        ));
        map.put("gupaoedu.vip.mybatis.mymybatis.v1.PersonMapper",methodMappers);
    }

    public static Map<String,MethodMapper> getMethodMapperList(String namespace){
        return map.get(namespace);
    }
    public static MethodMapper getMethodMapper(String namespace,String methodName){
        Map<String, MethodMapper> methodMapperList = getMethodMapperList(namespace);
        return methodMapperList.get(methodName);
    }

    public String getDatabaseDriver(){
        return "com.mysql.jdbc.Driver";
    }
    public String getDatabaseUrl(){
        return "jdbc:mysql://localhost:3309/test1?useUnicode=true&characterEncoding=utf-8";
    }
    public String getDatabaseUserName(){
        return "root";
    }
    public String getDatabaseUserPwd(){
        return "root";
    }


}
