package ru.ivashnev.aston.SpringBean.model;

import ru.ivashnev.aston.SpringBean.model.modelImpl.Animal;
import ru.ivashnev.aston.SpringBean.service.annotationImpl.Component;

@Component
public class Dog implements Animal {
    private String name;

    public Dog() {
    }

    public Dog(String name) {
        this.name = name;
    }

    @Override
    public String voice() {
        return name + " does " + "Aw-Aw";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }
}
