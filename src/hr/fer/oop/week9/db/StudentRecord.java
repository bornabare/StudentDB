package hr.fer.oop.week9.db;

import java.util.Objects;

/**
 * Created by borna on 1/9/15.
 */
public class StudentRecord {
    
    private String jmbag;
    private String lastName;
    private String firstName;
    private String finalGrade;

    public StudentRecord(String jmbag, String lastName, String firstName, String finalGrade) {
        this.jmbag = jmbag;
        this.lastName = lastName;
        this.firstName = firstName;
        this.finalGrade = finalGrade;
    }

    @Override
    public String toString() {
        return "StudentRecord{" +
                "jmbag='" + jmbag + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", finalGrade='" + finalGrade + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return jmbag.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof String) {
            return jmbag.equals(obj);
        }
        throw new IllegalArgumentException();
    }
}
