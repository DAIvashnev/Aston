package ru.ivashnev.aston.SpringBean;

import ru.ivashnev.aston.SpringBean.model.Cat;
import ru.ivashnev.aston.SpringBean.model.Dog;
import ru.ivashnev.aston.SpringBean.service.ApplicationContext;

public class App {
    private static final String PATH_TO_COMPONENT = "ru.ivashnev.aston.SpringBean.model";

    public static void main( String[] args ) {
        ApplicationContext context = new ApplicationContext(PATH_TO_COMPONENT);

        Cat cat = (Cat) context.getBean("cat");
        Dog dog = (Dog) context.getBean("dog");

        cat.setName("Murka");
        dog.setName("Sharick");

        System.out.println(cat.voice());
        System.out.println(dog.voice());


    }
}
