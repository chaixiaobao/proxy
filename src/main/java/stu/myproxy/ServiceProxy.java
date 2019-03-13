package stu.myproxy;

import java.lang.reflect.Method;

public class ServiceProxy implements CHInvocationHandler {

    private Object target;

    public Object getInstance(Object target) {
        this.target = target;
        return CHProxy.newProxyInstance(new CHClassLoader(), target.getClass().getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // TODO Auto-generated method stub
        before();
        Object result = method.invoke(target, args);
        after();
        return result;
    }

    private void after() {
        // TODO Auto-generated method stub
        System.out.println("after service...");

    }

    private void before() {
        // TODO Auto-generated method stub
        System.out.println("before serviceqqq...");

    }

}
