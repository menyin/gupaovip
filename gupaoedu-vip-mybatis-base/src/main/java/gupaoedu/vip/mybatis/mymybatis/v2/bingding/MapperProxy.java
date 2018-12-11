package gupaoedu.vip.mybatis.mymybatis.v2.bingding;

import gupaoedu.vip.mybatis.mymybatis.v2.session.Sqlsession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class MapperProxy<T> implements InvocationHandler {
    private Sqlsession sqlsession;

    public MapperProxy(Sqlsession sqlsession) {
        this.sqlsession = sqlsession;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {//Object基类的方法
            return method.invoke(this, args);
        } else {
            String methodName = method.getName();
            String fullName = method.getDeclaringClass().getName();
            //交给sqlsession去执行
            List<Object> objects = sqlsession.selectList(fullName + "." + methodName, args);
            return method.getReturnType().equals(List.class)?objects:objects.get(0);
        }

    }

    public static <T> T newInstance(Class<T> mapperClass, Sqlsession sqlsession) {
        MapperProxy<T> tMapperProxy = new MapperProxy<T>(sqlsession);
        return (T) Proxy.newProxyInstance(mapperClass.getClassLoader(), new Class[]{mapperClass}, tMapperProxy);
    }

}

