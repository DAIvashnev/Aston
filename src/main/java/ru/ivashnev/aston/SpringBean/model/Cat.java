package ru.ivashnev.aston.SpringBean.model;

import ru.ivashnev.aston.SpringBean.model.modelImpl.Animal;
import ru.ivashnev.aston.SpringBean.service.annotationImpl.Component;

@Component
public class Cat implements Animal {
    private String name;

    public Cat() {
    }

    public Cat(String name) {
        this.name = name;
    }

    @Override
    public String voice() {
        return name + " does " + "Miy-Miy";
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                '}';
    }
}
