package gupaoedu.vip.mybatis.mymybatis.v2;

import gupaoedu.vip.mybatis.mymybatis.v2.excutor.Excutor;
import gupaoedu.vip.mybatis.mymybatis.v2.mapperstatement.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Properties;

@Intercepts({@Signature(
        type = Excutor.class,
        method = "query",
        args = {MappedStatement.class, Object.class})})
public class ExamplePlugin implements Interceptor  {
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("测试插件>>>>>");
        return invocation.proceed();
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    public void setProperties(Properties properties) {

    }
}
