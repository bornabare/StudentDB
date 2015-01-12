package hr.fer.oop.topic10.db;

/**
 * Created by borna on 1/11/15.
 */
public class EnrolmentRecord {
    private String courseID;
    private String jmbag;
    private String grade;

    public EnrolmentRecord(String courseID, String jmbag, String grade) {
        this.courseID = courseID;
        this.jmbag = jmbag;
        this.grade = grade;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EnrolmentRecord){
            return ((EnrolmentRecord) obj).courseID.equals(courseID) && ((EnrolmentRecord) obj).jmbag.equals(jmbag);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return jmbag.hashCode();
    }

    @Override
    public String toString() {
        return "EnrolmentRecord { " +
                "courseID='" + courseID + '\'' +
                ", jmbag='" + jmbag + '\'' +
                ", grade='" + grade + '\'' +
                " }\n";
    }

    public String getCourseID() {
        return courseID;
    }

    public String getJmbag() {
        return jmbag;
    }

    public String getGrade() {
        return grade;
    }
}
