package hr.fer.oop.week9.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by borna on 1/9/15.
 */

public class StudentDatabase {

    private List<String> studentList;
    private Map<String, StudentRecord> studentRecordMap = new HashMap<String, StudentRecord>();

    public StudentDatabase(List<String> studentList) {
        this.studentList = studentList;

        for(String student : studentList){
            String[] studentArgs = student.split("\t");
            studentRecordMap.put(studentArgs[0], new StudentRecord(studentArgs[0], studentArgs[1], studentArgs[2], studentArgs[3]));
        }
    }

    @Override
    public String toString() {
        String string="";

        for (Map.Entry<String, StudentRecord> recordEntry: studentRecordMap.entrySet()){
            string = string+recordEntry.getValue().toString()+"\n";
        }
        return string;
    }

    public StudentRecord forJMBAG(String jmbag){
        return studentRecordMap.get(jmbag);
    }

    public List<StudentRecord> filter(IFilter filter){
        List<StudentRecord> filteredList = new ArrayList<StudentRecord>();

        for (String key : studentRecordMap.keySet()) {
            if (filter.accepts(studentRecordMap.get(key))) {
                filteredList.add(studentRecordMap.get(key));
            }
        }

        return filteredList;
    }
}
