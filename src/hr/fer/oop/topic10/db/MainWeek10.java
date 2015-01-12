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
        BufferedReader predmetiReader;
        BufferedReader studentiReader;
        BufferedReader upisaniPredmetiReader;

        try {
            predmetiReader = new BufferedReader(new FileReader("//Users/borna/IdeaProjects/StudentDB/predmeti.txt"));
            studentiReader = new BufferedReader(new FileReader("//Users/borna/IdeaProjects/StudentDB/studenti.txt"));
            upisaniPredmetiReader = new BufferedReader(new FileReader("//Users/borna/IdeaProjects/StudentDB/upisaniPredmeti.txt"));

            String line;

            List<String> predmeti = new LinkedList<String>();
            List<String> studenti = new LinkedList<String>();
            List<String> upisaniPredmeti = new LinkedList<String>();

            while ((line = predmetiReader.readLine()) != null){
                predmeti.add(line);
            }

            while ((line = studentiReader.readLine()) != null){
                studenti.add(line);
            }

            while ((line = upisaniPredmetiReader.readLine()) != null){
                upisaniPredmeti.add(line);
            }

            CourseDatabase courseDatabase = new CourseDatabase(predmeti);
            StudentDatabase studentDatabase = new StudentDatabase(studenti);
            EnrolmentDatabase enrolmentDatabase = new EnrolmentDatabase(upisaniPredmeti);



            Database database = new Database(studentDatabase, courseDatabase, enrolmentDatabase);

            // dohvacanje popisa studenata i ispis
            StudentDatabase studData = database.getStudentTable();
            System.out.printf(studData.toString());

            // dohvaćanje popisa predmeta jednog studenta
            String jmbag = "0000000001";
            Collection<EnrolmentRecord> predmetiStudenta = database.getEnrolmentTable().findByStudent(jmbag);
            for(EnrolmentRecord record : predmetiStudenta){
                System.out.println(record.toString());
            }

            // stvaranje novog predmeta
            predmeti.add("4\tMAT");
            courseDatabase = new CourseDatabase(predmeti);
            System.out.println(courseDatabase.toString());

            // upisivanje predmeta nekom studentu
            database.getEnrolmentTable().newCourse("0000000001", "4");

            // postavljanje ocjene
            database.getEnrolmentTable().updateEnrolment(new EnrolmentRecord("3", "0000000002", "3"));
            System.out.println(database.getEnrolmentTable().toString());

            // brisanje upisa predmeta.
//            database.getEnrolmentTable().deleteRecord("0000000001", "3");
//            System.out.println(database.getEnrolmentTable().toString());

        } catch (IOException e){}
    }
}
