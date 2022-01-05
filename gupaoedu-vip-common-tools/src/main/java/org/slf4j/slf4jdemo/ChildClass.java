package org.slf4j.slf4jdemo;

import java.util.Map;

public class ChildClass extends BaseClass {
    private Map<String, Object> body;

    public ChildClass(Map<String, Object> body) {
        this.body = body;
    }


    @Override
    public Map<String, Object> getBody() {
        return body;
    }
}
