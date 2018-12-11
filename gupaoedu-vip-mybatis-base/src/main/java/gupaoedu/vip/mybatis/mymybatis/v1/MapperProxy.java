package gupaoedu.vip.mybatis.mymybatis.v1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MapperProxy<T> implements InvocationHandler {
    private Sqlsession sqlsession;

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {//Object基类的方法
            return method.invoke(this, args);
        } else {
            String methodName = method.getName();
            String fullName = method.getDeclaringClass().getName();
            //交给sqlsession去执行sql
//            String fullName = proxy.getClass().getName();
            MethodMapper methodMapper = Configuration.getMethodMapper(fullName, methodName);
            return sqlsession.selectList(methodMapper.getSql(), args[0]).get(0);
        }

    }

    public static <T> T newInstance(Class<T> mapperClass, Sqlsession sqlsession) {
        MapperProxy<T> tMapperProxy = new MapperProxy<T>();
        tMapperProxy.setSqlsession(sqlsession);
        return (T)Proxy.newProxyInstance(mapperClass.getClassLoader(), new Class[]{mapperClass}, tMapperProxy);
    }

    public void setSqlsession(Sqlsession sqlsession) {
        this.sqlsession = sqlsession;
    }
}
