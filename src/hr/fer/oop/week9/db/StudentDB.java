package hr.fer.oop.week9.db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by borna on 1/9/15.
 */
public class StudentDB {

    public static void main(String[] args) {

        System.out.println("Procesing database . . . ");
        StudentDatabase database = processDatabase();
        System.out.println(database.forJMBAG("0000000014").toString());

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.printf("> ");
            try {
                String line = bf.readLine();
                String[] splitLine = line.split(" ");

                for (String s : splitLine){
                    System.out.println(s);
                }

                if (splitLine[0] == "query") {
                    System.out.println("Wrong argument !");
                    System.exit(1);
                }

                splitLine[0] = null;
                String line2 = null;

                for (String string : splitLine){
                    line2 = line2+string;
                }
                splitLine = line2.split("=");

                if(splitLine[0].equals("query")){
                   database.filter(new LastNameFilter(splitLine[1]));
                }

                else if (splitLine[0].equals("jmbag")){
                    String s = splitLine[1].substring(splitLine[1].indexOf("\"") + 1);
                    s = s.substring(0, s.indexOf("\""));
                    database.forJMBAG(s);
                }

            } catch (IOException e){
                e.printStackTrace();
            }

            break;
        }

    }

    public static StudentDatabase processDatabase(){

        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader("//Users/borna/IdeaProjects/StudentDB/database.txt"));
            List<String> students = new ArrayList<String>();

            while ((line = br.readLine()) != null) {
                students.add(line);
            }

//            students.add
            StudentDatabase database = new StudentDatabase(students);

            return database;

        } catch (IOException e){
           e.printStackTrace();
        } finally {
            try {
                if (br != null){
                    br.close();
                }
            } catch (IOException x){
                x.printStackTrace();
            }
        }
        return null;
    }
}
