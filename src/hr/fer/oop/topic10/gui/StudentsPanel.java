package hr.fer.oop.topic10.gui;

import hr.fer.oop.topic10.db.Database;
import hr.fer.oop.week9.db.StudentRecord;

import javax.swing.*;
import java.awt.*;
import java.util.Map;


/**
 * Created by borna on 1/12/15.
 */
public class StudentsPanel extends JPanel {

    public StudentsPanel(Database database, StudentsFrame frame) {
        setLayout(new BorderLayout());


        JComboBox jComboBox = new JComboBox<String>();

        for (Map.Entry<String, StudentRecord> studenti : database.getStudentTable().getStudentRecordMap().entrySet()){
            jComboBox.addItem(studenti.getValue().getComboFirstAndLastname());
        }

        StudentInfo studentInfo = new StudentInfo(database, frame);
        add(jComboBox, BorderLayout.NORTH);
        add(studentInfo, BorderLayout.CENTER);

    }



}
