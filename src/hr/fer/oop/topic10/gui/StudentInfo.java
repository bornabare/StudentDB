package hr.fer.oop.topic10.gui;

import hr.fer.oop.topic10.db.Database;
import hr.fer.oop.week9.db.StudentRecord;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Created by borna on 1/12/15.
 */
public class StudentInfo extends JPanel {

    public StudentInfo(Database database, StudentsFrame frame) {

        setLayout(new BorderLayout());

        add(new InfoPanel(database, frame), BorderLayout.CENTER);
        add(new EnrolmentPanel(database, frame), BorderLayout.SOUTH);

    }
}
