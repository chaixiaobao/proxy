package stu.myproxy;

import java.lang.reflect.Method;

public interface CHInvocationHandler {

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;
    
}
