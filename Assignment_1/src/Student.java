public class Student extends Person{
    private int roll_no;

    private String course;
    private String department;

    public int getRoll_no() {
        return roll_no;
    }

    public void setRoll_no(int roll_no) {
        this.roll_no = roll_no;
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

    public Student(String name, int age, int roll_no, String course, String department) {
        super(name, age);
        this.roll_no = roll_no;
        this.course = course;
        this.department = department;
    }

}
