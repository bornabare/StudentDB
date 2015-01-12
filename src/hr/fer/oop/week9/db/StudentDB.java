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
        System.out.println("Process completed !");

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.printf("> ");
            try {
                String line = bf.readLine();
                String[] splitLine = line.split(" ");

                if (!splitLine[0].equals("query")) {
                    System.out.println("Undefined command !");
                    System.exit(1);
                }

                splitLine[0] = null;
                String line2 = "";

                for (int i = 0; i < splitLine.length-1; i++) {
                    line2 = line2+splitLine[i+1];
                }

                splitLine = line2.split("=");

                if(splitLine[0].equals("lastName")){
                    String s = splitLine[1].substring(splitLine[1].indexOf("\"") + 1);
                    s = s.substring(0, s.indexOf("\""));

                    System.out.println("+============+===========+===========+===+");

                    IFilter filter = new LastNameFilter(s);
                    List<StudentRecord> queryDatabase = database.filter(filter);

                    for (StudentRecord record : queryDatabase){
                        System.out.println(record.toString());
                    }
                    System.out.println("+============+===========+===========+===+");
                    System.out.println("Records selected: " + queryDatabase.size());
                }

                else if (splitLine[0].equals("jmbag")){
                    String s = splitLine[1].substring(splitLine[1].indexOf("\"") + 1);
                    s = s.substring(0, s.indexOf("\""));
                    System.out.println("+============+===========+===========+===+");
                    StudentRecord queryDatebase = database.forJMBAG(s);
                    System.out.println(queryDatebase.toString());
                    System.out.println("+============+===========+===========+===+");
                    int length = 1;
                    if (queryDatebase == null){
                        length = 0;
                    }
                    System.out.println("Records selected: "+ length);
                }
                else {
                    System.out.println("Wrong argument line !");
                    System.exit(1);
                }

            } catch (IOException e){
                e.printStackTrace();
            }

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
