package stu.myproxy;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        IHelloService service = (IHelloService) new ServiceProxy().getInstance(new HelloServiceImpl());

        service.sayHello("Jack");

//        CHProxy.newProxyInstance(new CHClassLoader(), new Class[] { IHelloService.class },
//                new CHInvocationHandler() {
//
//                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                        // TODO Auto-generated method stub
//                        return null;
//                    }
//                });
    }

}
