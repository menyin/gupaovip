package gupaoedu.vip.mybatis.plugns;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

@Intercepts({@Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class ExamplePlugin1 implements Interceptor {
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("插件1>>>>>>>>拦截了Executor.query方法");
        return invocation.proceed();//invoke原本的方法，invocation包含原本的类实例及方法信息
//        return null;
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    public void setProperties(Properties properties) {

    }
}
