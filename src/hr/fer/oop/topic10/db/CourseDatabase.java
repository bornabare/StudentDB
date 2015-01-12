package hr.fer.oop.topic10.db;

import hr.fer.oop.week9.db.StudentRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by borna on 1/11/15.
 */
public class CourseDatabase {

    private Map<String, CourseRecord> courseRecordMap = new HashMap<String, CourseRecord>();
    private List<String> stringList;
    private String courseID;
    private String courseName;

    public CourseDatabase(List<String> stringList) {
        this.stringList = stringList;

        for (String line : stringList) {
            String[] args = line.split("\t");
            courseID = args[0];
            courseName = args[1];
            courseRecordMap.put(courseID, new CourseRecord(courseID, courseName));
        }
    }

    @Override
    public String toString() {
        return "CourseDatabase { \n" +
                "\tcourseRecordMap= " + courseRecordMap +
                " }";
    }

    public CourseRecord findById(String courseID) {
        return courseRecordMap.get(courseID);
    }

    // case insensitive searching with * e.g. "objektno*"
    public CourseRecord findFirstByName (String partialCourseName) {
        for (String stringID : courseRecordMap.keySet()){
            if (searchSuccess (partialCourseName, courseRecordMap.get(stringID).getCourseName())) {
                return courseRecordMap.get(stringID);
            }
        }
        return null;
        //search unsuccessful
    }

    public boolean searchSuccess(String partialCourseName, String courseName) {
        String[] string = partialCourseName.split("\\*");

        /*
        if string length is 2, that means asterisk is in between beginning and end of lastName
         */
        if (string.length == 2) {
            if (!isBeginningSame(string[0], courseName)) {
                return false;
            }
            if (!isEdingSame(string[1], courseName)) {
                return false;
            }
            return true;
        }
        /*
        if only 1 string that means asterisk is given either on the beginning or at the end of the lastName

        1.1 define where is asterisk? start or end
        1.2 calculate first or second for loop
         */

        else if (string.length == 1) {

            if (string[0].charAt(0) == '*') {
                if (isEdingSame(string[0], courseName)) {
                    return true;
                }
            } else {
                if (isBeginningSame(string[0], courseName)) {
                    return true;
                }
            }
        }

        else {
            System.out.println("Wrong asterisk consumation !");
            System.exit(1);
        }

        return false; //nebitno
    }

    public boolean isBeginningSame(String string, String courseName) {
        for (int i = 0; i < string.length(); i++) {
            if (!(string.charAt(i) == courseName.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean isEdingSame(String string, String courseName) {
        for (int i = 0; i < string.length(); i++) {
            if (!(string.charAt(string.length() - i - 1) == courseName.charAt(courseName.length() - i - 1))) {
                return false;
            }
        }
        return true;
    }
}

