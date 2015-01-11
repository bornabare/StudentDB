package hr.fer.oop.week9.db;

/**
 * Created by borna on 1/9/15.
 */
public interface IFilter {

    public boolean accepts(StudentRecord record);
}
