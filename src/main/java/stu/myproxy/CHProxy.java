package stu.myproxy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class CHProxy {

    private static final String ln = "\r\n";

    public static Object newProxyInstance(CHClassLoader classLoader, Class<?>[] interfaces,
            CHInvocationHandler h) {

        String javaCode = generaterJavaCode(interfaces);

        String filePath = CHProxy.class.getResource("").getPath();
        File javaFile = new File(filePath + "$Proxy0.java");
        FileWriter fw;
        try {
            fw = new FileWriter(javaFile);
            fw.write(javaCode);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
        Iterable iterable = manager.getJavaFileObjects(javaFile);
        JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, iterable);
        task.call();
        
        try {
            manager.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        Class clazz = new CHClassLoader().findClass("$Proxy0");
        try {
            Constructor cs = clazz.getConstructor(CHInvocationHandler.class);
            try {
                return cs.newInstance(h);
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public static String generaterJavaCode(Class<?>[] interfaces) {
        // TODO Auto-generated method stub
        StringBuilder sb = new StringBuilder();
        StringBuilder interfaceNames = new StringBuilder();
        sb.append("package " + interfaces[0].getPackageName() + ";" + ln);
        for (Class<?> interfaceCls : interfaces) {
            sb.append("import " + interfaceCls.getName() + ";" + ln);
            interfaceNames.append(interfaceCls.getSimpleName() + ",");
        }
        interfaceNames.deleteCharAt(interfaceNames.length() - 1);
        sb.append("import java.lang.reflect.*;" + ln);
        sb.append("public final class $Proxy0 implements " + interfaceNames + " {" + ln);
        sb.append("private CHInvocationHandler h;" + ln);
        sb.append("public $Proxy0(CHInvocationHandler h) {" + ln);
        sb.append("this.h = h;" + ln);
        sb.append("}" + ln);

        for (Method method : interfaces[0].getMethods()) {
            sb.append("public " + method.getReturnType().getName() + " " + method.getName() + "(String msg) {"
                    + ln);
            sb.append("try {" + ln);
            sb.append("Method m = " + interfaces[0].getName() + ".class.getMethod(\"" + method.getName()
                    + "\",new Class[]{String.class});" + ln);
            sb.append("this.h.invoke(this, m, new Object[]{msg});" + ln);
            sb.append("return;" + ln);
            sb.append("} catch(Throwable t) {" + ln);
            sb.append("t.printStackTrace();" + ln);
            sb.append("}" + ln);
            sb.append("}" + ln);
        }

        sb.append("}");
        return sb.toString();
    }

//    private static class MyStringBuilder {
//
//        StringBuilder sb;
//
//        public MyStringBuilder(StringBuilder sb) {
//            // TODO Auto-generated constructor stub
//            this.sb = sb;
//        }
//
//        public MyStringBuilder append(String str) {
//            sb.append(str + ln);
//            return this;
//        }
//
//        public String toString() {
//            return this.sb.toString();
//        }
//
//    }

}
