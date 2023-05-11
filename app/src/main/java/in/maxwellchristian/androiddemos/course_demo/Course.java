package in.maxwellchristian.androiddemos.course_demo;

public class Course {

    public String name;
    public String code;
    public int hours;
    public int credits;
    public String description;

    public Course(String name, String code, int hours, int credits, String description) {
        this.name = name;
        this.code = code;
        this.hours = hours;
        this.credits = credits;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", hours=" + hours +
                ", credits=" + credits +
                ", description='" + description + '\'' +
                '}';
    }
}
