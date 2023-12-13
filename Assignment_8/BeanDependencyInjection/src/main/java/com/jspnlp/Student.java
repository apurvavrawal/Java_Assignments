package com.jspnlp;

public class Student {
    private int rollNo;
    private String firstName;
    private String lastName;
    private String department;
    private Address address;

    public Student(int rollNo, String firstName, String lastName, String department, Address address) {
        this.rollNo = rollNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.address = address;
    }

    public Student() {

    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "rollNo=" + rollNo +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department='" + department + '\'' +
                ", address=" + address +
                '}';
    }
}


