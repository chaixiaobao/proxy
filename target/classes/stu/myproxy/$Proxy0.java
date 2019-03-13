package stu.myproxy;
import stu.myproxy.IHelloService;
import java.lang.reflect.*;
public final class $Proxy0 implements IHelloService {
private CHInvocationHandler h;
public $Proxy0(CHInvocationHandler h) {
this.h = h;
}
public void sayHello(String msg) {
try {
Method m = stu.myproxy.IHelloService.class.getMethod("sayHello",new Class[]{String.class});
this.h.invoke(this, m, new Object[]{msg});
return;
} catch(Throwable t) {
t.printStackTrace();
}
}
}