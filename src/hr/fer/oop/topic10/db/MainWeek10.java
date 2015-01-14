package hr.fer.oop.topic10.db;

import hr.fer.oop.week9.db.StudentDatabase;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by borna on 1/11/15.
 */
public class MainWeek10 {

    public static void main(String[] args) {

        Database database = new Database ("//Users/borna/IdeaProjects/StudentDB/predmeti.txt",
                                        "//Users/borna/IdeaProjects/StudentDB/studenti.txt",
                                        "//Users/borna/IdeaProjects/StudentDB/upisaniPredmeti.txt");

        // dohvacanje popisa studenata i ispis
        StudentDatabase studData = database.getStudentTable();
        System.out.printf("Get student table: \n"+studData.toString());

        // dohvaćanje popisa predmeta jednog studenta
        System.out.println("\nPopis predmeta jednog studenta: ");
        String jmbag = "0000000002";
        Collection<EnrolmentRecord> predmetiStudenta = database.getEnrolmentTable().findByStudent(jmbag);
        for(EnrolmentRecord record : predmetiStudenta){
            System.out.println(record.toString());
        }

        // stvaranje novog predmeta
        System.out.println("Stvaranje novog predmeta:");
        database.getCourseTable().getStringList().add("4\tMAT");
        CourseDatabase courseDatabase = new CourseDatabase(database.getCourseTable().getStringList());
        System.out.println(courseDatabase.toString());

        // upisivanje predmeta nekom studentu
        database.getEnrolmentTable().newCourse("0000000001", "4");

        // postavljanje ocjene
        System.out.println("\nPostavljanje ocjene: ");
        database.getEnrolmentTable().updateEnrolment(new EnrolmentRecord("3", "0000000002", "3"));
        System.out.println(database.getEnrolmentTable().toString());

        // brisanje upisa predmeta.
        System.out.println("\nBrisanje upisa predmeta:");
        database.getEnrolmentTable().deleteRecord("0000000001", "3");
        System.out.println(database.getEnrolmentTable().toString());

    }
}
