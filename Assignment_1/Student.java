public class Student extends Person{
    private int rollNo;

    private String course;
    private String department;

    public int getRoll_no() {
        return rollNo;
    }

    public void setRoll_no(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Student(String name, int age, int rollNo, String course, String department) {
        super(name, age);
        this.rollNo = rollNo;
        this.course = course;
        this.department = department;
    }

}
