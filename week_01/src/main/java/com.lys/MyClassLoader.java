package com.lys;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class MyClassLoader extends ClassLoader {
    String fileName;

    public MyClassLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = loadClass();
        if (data == null) return null;
        return defineClass(name, data, 0, data.length);
    }


    /**
     * 加载classpath 目录下指定的class文件
     */
    private byte[] loadClass() {
        String path = this.getClass().getResource("/").getPath() + fileName;
        File file = new File(path);
        if (file.exists()) {
            try (FileInputStream in = new FileInputStream(file); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                byte[] buffer = new byte[in.available()];
                in.read(buffer);
                for (int i = 0; i < buffer.length; i++) {
                    buffer[i] = (byte) (255 - buffer[i]);
                }
                return buffer;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
