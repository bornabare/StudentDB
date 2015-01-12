package hr.fer.oop.topic10.db;

import hr.fer.oop.week9.db.StudentDatabase;

/**
 * Created by borna on 1/11/15.
 */
public class Database {

    private StudentDatabase studentTable;
    private CourseDatabase courseTable;
    private EnrolmentDatabase enrolmentTable;


    public Database (StudentDatabase studentTable, CourseDatabase courseTable, EnrolmentDatabase enrolmentTable) {
        this.studentTable = studentTable;
        this.courseTable = courseTable;
        this.enrolmentTable = enrolmentTable;
    }

    public StudentDatabase getStudentTable() {
        return studentTable;
    }

    public CourseDatabase getCourseTable() {
        return courseTable;
    }

    public EnrolmentDatabase getEnrolmentTable() {
        return enrolmentTable;
    }
}
