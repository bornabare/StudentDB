package hr.fer.oop.topic10.db;

/**
 * Created by borna on 1/11/15.
 */
public class CourseRecord {
    private String courseID;
    private String courseName;

    public CourseRecord(String courseID, String courseName) {
        this.courseID = courseID;
        this.courseName = courseName;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CourseRecord){
            return ((CourseRecord) o).courseID.equals(courseID);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return courseID.hashCode();
    }

    @Override
    public String toString() {
        return "CourseRecord {" +
                "courseID='" + courseID + '\'' +
                ", courseName='" + courseName + '\'' +
                " }\n";
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }
}
