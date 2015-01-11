package hr.fer.oop.week9.db;

/**
 * Created by borna on 1/10/15.
 */
public class jmbagFilter implements IFilter {

    String jmbag;

    public jmbagFilter(String jmbag) {
        this.jmbag = jmbag;
    }

    @Override
    public boolean accepts(StudentRecord record) {
        return false;
    }
}
