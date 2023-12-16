package ru.geekbrains.lesson3.hw1;

import java.io.Serializable;

/*
Разработайте класс Student с полями String name, int age, transient double GPA (средний балл).
Обеспечьте поддержку сериализации для этого класса. Создайте объект класса Student и инициализируйте его данными.
Сериализуйте этот объект в файл. Десериализуйте объект обратно в программу из файла. Выведите все поля объекта,
включая GPA, и обсудите, почему значение GPA не было сохранено/восстановлено.
 */
public class Student implements Serializable {
    private String name;
    private int age;
    private transient double GPA;

    public Student(String name, int age, double GPA) {
        this.name = name;
        this.age = age;
        this.GPA = GPA;
    }

    public String getName() {
        return name;
    }

    public Student() {

    }

    public int getAge() {
        return age;
    }

    public double getGPA() {
        return GPA;
    }

    @Override
    public String toString() {
        return "Имя=" + name + '\n' +
                "Возраст=" + age + '\n' +
                "Средний бал=" + GPA;
    }
}
