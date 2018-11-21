package com.gupaoedu.vip.pattern.delegate.decorator;

import com.gupaoedu.vip.pattern.delegate.decorator.impl.HouYiImpl;
import com.gupaoedu.vip.pattern.delegate.decorator.impl.HouYiImplExt;

public class UnitTest {
    public static void main(String[] args) {

        /*例如：DataInputStream就是InputStream的增强版*/
        /*注意该类其实也是一种特殊的适配器模式，注意区别：装饰器注重功能方法扩展，而适配器注重方法参数数据的适配*/

        System.out.println("---------普通版后羿--------------");
        HouYi houYi = new HouYiImpl();
        houYi.sheRi();
        System.out.println("---------增强版后羿--------------");
        HouYi houYiExt = new HouYiImplExt(houYi);
        houYiExt.sheRi();
    }
}
