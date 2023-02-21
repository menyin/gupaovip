package com.gupaoedu.vip.pattern.singleton.lazy;

/**
 * @Description: TODO
 * @author: geduo
 * @date: 2022年11月23日 15:49
 */
public class FinalSingleton {
    private static boolean isNew = false;
    private FinalSingleton(){
        synchronized (FinalSingleton.class) {
            if(!isNew){
                isNew=!isNew;
            }else{
                throw new RuntimeException("单例不可侵犯，请调用getSingleton方法");
            }
        }
    }


    public static final FinalSingleton getSingleton(){
        return InnerClass.singleton;
    }

    private static class InnerClass{
        private static final FinalSingleton singleton = new FinalSingleton();
    }
}
