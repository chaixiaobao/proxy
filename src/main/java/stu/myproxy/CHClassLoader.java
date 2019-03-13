package stu.myproxy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class CHClassLoader extends ClassLoader {

    @Override
    public Class<?> findClass(String className) {
        try {
            String fullClassName = CHClassLoader.class.getPackageName() + "." + className;
            File classPath = new File(CHClassLoader.class.getResource("").getPath());
            File classFile = new File(classPath, className.replaceAll("\\.", "/") + ".class");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            FileInputStream fis = new FileInputStream(classFile);
            int len;
            byte[] buffer = new byte[1024];
            while ((len = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            fis.close();
            return defineClass(fullClassName, bos.toByteArray(), 0, bos.size());

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }

}
