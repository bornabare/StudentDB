package hr.fer.oop.topic10.db;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by borna on 1/11/15.
 */
public class EnrolmentDatabase {

    private Set<EnrolmentRecord> enrolmentRecordsSet = new HashSet<EnrolmentRecord>();
    private List<String> enrolmentString;
    private String studentJMBAG;
    private String courseID;
    private String grade;


    public EnrolmentDatabase(List<String> enrolmentString) {
        this.enrolmentString = enrolmentString;

        for(String line : enrolmentString) {
            String[] args = line.split ("\t");
            courseID = args[0];
            studentJMBAG = args[1];
            grade = args[2];
            enrolmentRecordsSet.add(new EnrolmentRecord (courseID, studentJMBAG, grade));
        }
    }

    @Override
    public String toString() {
        return "EnrolmentDatabase{ " +
                "\nenrolmentRecordsSet=" + enrolmentRecordsSet +
                "}";
    }

    public Collection<EnrolmentRecord> findByStudent (String studentJMBAG) {

        Set<EnrolmentRecord> set = new HashSet<EnrolmentRecord>();

        for (EnrolmentRecord record : enrolmentRecordsSet) {
            if (record.getJmbag().equals(studentJMBAG)){
                set.add(record);
            }
        }
        return set;
    }

    public Collection<EnrolmentRecord> findByCourse (String courseID) {

        Set<EnrolmentRecord> set = new HashSet<EnrolmentRecord>();

        for (EnrolmentRecord record : enrolmentRecordsSet) {
            if (record.getCourseID().equals(courseID)){
                set.add(record);
            }
        }

        return set;
    }

    public EnrolmentRecord findByStudentAndCourse(String studentJMBAG,
                                                  String courseID) {

        for (EnrolmentRecord record : enrolmentRecordsSet) {
            if (record.getCourseID().equals(courseID) && record.getJmbag().equals(studentJMBAG)){
                return record;
            }
        }
        System.exit(1);
        return null;
    }

    public EnrolmentRecord newCourse(String studentJMBAG,
                                     String courseID) { // default grade is 0 – not passed

        EnrolmentRecord newRecord = new EnrolmentRecord(courseID, studentJMBAG, "0");
        enrolmentRecordsSet.add(newRecord);
        return newRecord;
    }

    public void updateEnrolment(EnrolmentRecord record) {

        for (EnrolmentRecord recordInLoop : enrolmentRecordsSet) {
            if (recordInLoop.equals(record)) {
                enrolmentRecordsSet.remove(recordInLoop);
                enrolmentRecordsSet.add(record);
            }
        }

    }

    public void deleteRecord(String studentJMBAG, String courseID) {

        for (EnrolmentRecord record : enrolmentRecordsSet) {
            if (record.getCourseID().equals(courseID) && record.getJmbag().equals(studentJMBAG)){
                enrolmentRecordsSet.remove(record);
            }
        }
    }
}
