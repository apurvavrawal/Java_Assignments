package com.jspnlp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args){

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);

        Student student = context.getBean(Student.class);
        student.setRollNo(1);
        student.setFirstName("Apurva");
        student.setLastName("Rawal");
        student.setDepartment("CSE");
        student.setAddress(new Address("Sangli","Maharashtra","India"));
        System.out.println("Student Details: "+ student);
    }
}

