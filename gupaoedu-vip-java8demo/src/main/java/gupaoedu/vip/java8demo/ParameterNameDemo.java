package gupaoedu.vip.java8demo;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.function.Consumer;

public class ParameterNameDemo {
    /**
     * 在java编译时添加compilerArgument参数参数可以使得 方法参数名得以“保留”
     * 去掉pom.xml的compilerArgument参数配置，则输出arge0，否则输出真实参数名称arges
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Method method = ParameterNameDemo.class.getMethod( "main", String[].class );
        for( final Parameter parameter: method.getParameters() ) {
            System.out.println( "Parameter: " + parameter.getName() );
        }

    }
}
