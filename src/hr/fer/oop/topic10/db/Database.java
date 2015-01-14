package hr.fer.oop.topic10.db;

import hr.fer.oop.week9.db.StudentDatabase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by borna on 1/11/15.
 */
public class Database {

    private StudentDatabase studentDatabase;
    private CourseDatabase courseDatabase;
    private EnrolmentDatabase enrolmentDatabase;


    public Database (String courseFile, String studentFile, String enrolmentFile) {

        BufferedReader predmetiReader;
        BufferedReader studentiReader;
        BufferedReader upisaniPredmetiReader;

        try {
            predmetiReader = new BufferedReader(new FileReader(courseFile));
            studentiReader = new BufferedReader(new FileReader(studentFile));
            upisaniPredmetiReader = new BufferedReader(new FileReader(enrolmentFile));

            String line;

            List<String> predmeti = new LinkedList<String>();
            List<String> studenti = new LinkedList<String>();
            List<String> upisaniPredmeti = new LinkedList<String>();

            while ((line = predmetiReader.readLine()) != null) {
                predmeti.add(line);
            }

            while ((line = studentiReader.readLine()) != null) {
                studenti.add(line);
            }

            while ((line = upisaniPredmetiReader.readLine()) != null) {
                upisaniPredmeti.add(line);
            }

            this.courseDatabase = new CourseDatabase(predmeti);
            this.studentDatabase = new StudentDatabase(studenti);
            this.enrolmentDatabase = new EnrolmentDatabase(upisaniPredmeti);

        } catch (IOException e){}

    }

    public StudentDatabase getStudentTable() {
        return studentDatabase;
    }

    public CourseDatabase getCourseTable() {
        return courseDatabase;
    }

    public EnrolmentDatabase getEnrolmentTable() {
        return enrolmentDatabase;
    }
}
