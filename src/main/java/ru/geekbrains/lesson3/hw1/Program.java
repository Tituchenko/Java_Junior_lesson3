package ru.geekbrains.lesson3.hw1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.geekbrains.lesson3.task1.UserData;
import ru.geekbrains.lesson3.task2.ToDo;

import java.io.*;
import java.util.Collection;
import java.util.List;

public class Program {
    private static final String FILE="studentdata.bin";
    public static final String FILE_XML = "student.xml";
    public static final String FILE_JSON = "student.json";

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();



    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student student= new Student("Иванов",19,4.2);
        try(FileOutputStream fileOutputStream = new FileOutputStream(FILE);
            //BIN
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject(student);
            //JSON
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            objectMapper.writeValue(new File(FILE_JSON), student);
            //XML
            xmlMapper.writeValue(new File(FILE_XML), student);
            System.out.println("Объект Student сериализован.");
        }
        System.out.println(student);

        try(FileInputStream fileInputStream = new FileInputStream(FILE);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
            student = (Student) objectInputStream.readObject();
            System.out.println("Объект UserData десериализован (BIN).");
            System.out.println(student);
        }
        //Не понимаю о чем это, видимо что-то с transiet связано, без этого исключения падают. Нашел в Web
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Student studentJSON =(Student) objectMapper.readValue(new File(FILE_JSON),Student.class);
        System.out.println("Объект UserData десериализован (JSON).");
        System.out.println(studentJSON);

        //Не понимаю о чем это, видимо что-то с transiet связано, без этого исключения падают. Нашел в Web
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Student studentXML=(Student) xmlMapper.readValue(new File(FILE_XML),Student.class);
        System.out.println("Объект UserData десериализован (XML).");
        System.out.println(studentXML);

    }

}
