package hr.fer.oop.topic10.gui;

import hr.fer.oop.topic10.db.Database;

import javax.swing.*;
import java.awt.*;

/**
 * Created by borna on 1/12/15.
 */
public class EnrolmentPanel extends JPanel{

    public  EnrolmentPanel(Database database, StudentsFrame frame) {
        setLayout(new GridLayout());
        setBorder(BorderFactory.createLineBorder(Color.black));

    }
}
