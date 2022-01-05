package org.slf4j.slf4jdemo;

public class BaseClass {
    private byte[] body="hello".getBytes();
    public <T> T getBody() { //cny_note 和setBody一样，应该是会被子类重写
        return (T) body;
    }
}
