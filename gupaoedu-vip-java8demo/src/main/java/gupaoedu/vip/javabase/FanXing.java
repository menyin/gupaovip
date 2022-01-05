package gupaoedu.vip.javabase;
import org.junit.Test;

public class FanXing {

    /**
     * 泛型类实例在使用过程中，必须类的泛型参数如果不确定，则该类的泛型方法也不能正常使用（如正常返回泛型返回值）
     */
    @Test
    public void  test1() {
        PPP ppp = new PPP();
        String show = ppp.show("");//能正常返回泛型String
        PP<String> pp1=new PP();
        String show1 = pp1.show("");//能正常返回泛型String
        PP pp2=new PP();
        Object show2 = pp2.show("");//不能正常返回泛型String

    }
    public class PP<T>{
        public <D> D show(D d){
            return d;
        }
    }
    public class PPP extends PP<String>{}

    /**
     * 在非泛型类的泛型方法下，就可以直接使用泛型
     */
    @Test
    public void  test2() {
        String show = show("");
    }
    public <E> E show(E e){
        return e;
    }

}
