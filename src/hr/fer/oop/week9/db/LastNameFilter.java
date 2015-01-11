package hr.fer.oop.week9.db;

/**
 * Created by borna on 1/10/15.
 */
public class LastNameFilter implements IFilter {

    String lastName;

    public LastNameFilter(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean accepts(StudentRecord record) {
        if (lastName.equals(record)){
            return true;
        }
        return false;
    }
}
