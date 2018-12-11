package gupaoedu.vip.mybatis.mymybatis.v2.session;

import gupaoedu.vip.mybatis.mymybatis.v2.excutor.Excutor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Configuration {
    private final static Map<String, String> sqlMap = new HashMap<String, String>();
    private final static List<Interceptor> plugnList = new ArrayList<Interceptor>();

    static {
        /*模拟mapper.xml的数据*/
        //mapper
        sqlMap.put("gupaoedu.vip.mybatis.mymybatis.v2.PersonMapper.getPersonById",
                "select * from person where id = #{id}");
        //mybatis插件
        String plugin="gupaoedu.vip.mybatis.mymybatis.v2.ExamplePlugin";
        try {
            plugnList.add((Interceptor) Class.forName(plugin).newInstance());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   public static String getMapperSql(String statement){
       return sqlMap.get(statement);
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


    public List<InvocationHandler> getPlugns(){
//        return invocationHandlers;
        return null;
    }
    public static Excutor getWrapperExcutor(Excutor excutor){

        for (Interceptor interceptor : plugnList) {
            excutor = (Excutor)interceptor.plugin(excutor);

            /*Intercepts intercepts = interceptor.getClass().getAnnotation(Intercepts.class);
            Signature[] value = intercepts.value();
            for (Signature signature : value) {
                if(signature.type().equals(Executor.class)){//筛选出Excutor.query(MappedStatement ms, Object parameter)
                    Class<?>[] args = signature.args();
                    String methodName = signature.method();
                    Excutor excutorProxy = (Excutor) Proxy.newProxyInstance(excutor.getClass().getClassLoader(),
                            excutor.getClass().getInterfaces(),
                            new Plugin(excutor,interceptor,args,));
                    return excutorProxy;

                }
            }*/

        }
        return excutor;
    }
}

