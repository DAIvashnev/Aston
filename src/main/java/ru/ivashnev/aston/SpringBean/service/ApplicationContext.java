package ru.ivashnev.aston.SpringBean.service;

import ru.ivashnev.aston.SpringBean.service.annotationImpl.Component;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ApplicationContext {
    private Map<String, Object> context = new HashMap<>();

    public ApplicationContext(String basePackage) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try {
            Enumeration<URL> resources = classLoader.getResources(basePackage.replace('.', '/'));
            while (resources.hasMoreElements()) {
                File dir = new File(resources.nextElement().toURI());
                for (File f : Objects.requireNonNull(dir.listFiles())) {
                    if (f.getName().endsWith("class")) {
                        String fileName = f.getName().substring(0, f.getName().lastIndexOf("."));
                        Class<?> classObj = Class.forName(basePackage + "." + fileName);
                        if (classObj.isAnnotationPresent(Component.class)) {
                            Object obj = classObj.newInstance();
                            context.put(fileName.toLowerCase(), obj);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Object getBean(String nameBean) {
        return context.get(nameBean.toLowerCase());
    }
}
