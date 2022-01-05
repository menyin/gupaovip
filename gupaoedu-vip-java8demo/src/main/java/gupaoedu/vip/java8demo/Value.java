package gupaoedu.vip.java8demo;

public  class Value<T>{
    public  T getOrDefault(T value,T defaultValue){
        return value==null?defaultValue:value;
    }
}
