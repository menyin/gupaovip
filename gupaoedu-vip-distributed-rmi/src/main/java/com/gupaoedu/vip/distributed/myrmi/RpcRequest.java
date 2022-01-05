package com.gupaoedu.vip.distributed.myrmi;

import java.io.Serializable;

public class RpcRequest implements Serializable {
    private Object[] parameters;
    private String className;
    private String methodName;

    public RpcRequest(Object[] parameters, String className, String methodName) {
        this.parameters = parameters;
        this.className = className;
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
