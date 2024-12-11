package com.ywq.classloader;

import java.io.InputStream;

/**
 * @author wuque
 * @date 2024/12/11
 * 对于任意一个类，都必须由加载它的类加载器和这个类本身一起共同确立其在Java虚拟机中的唯一性，每一个类加载器，都拥有一个独立的类名称空间
 */

public class ClassLoaderTest {
    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] bytes = new byte[is.available()];
                    is.read(bytes);
                    return defineClass(name, bytes, 0, bytes.length);
                } catch (Exception e) {
                    throw new ClassNotFoundException();
                }
            }
        };

        Object aClass = classLoader.loadClass("com.ywq.classloader.ClassLoaderTest").getDeclaredConstructor().newInstance();
        System.out.println(aClass);
        // false
        System.out.println(aClass instanceof ClassLoaderTest);
    }
}
