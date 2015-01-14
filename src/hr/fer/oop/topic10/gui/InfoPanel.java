package hr.fer.oop.topic10.gui;

import hr.fer.oop.topic10.db.Database;
import hr.fer.oop.week9.db.StudentRecord;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Created by borna on 1/12/15.
 */
public class InfoPanel extends JPanel {

    public InfoPanel(Database database, StudentsFrame frame) {

        SpringLayout layout = new SpringLayout();
        setLayout(layout);

        JLabel jmbag = new JLabel("JMBAG: ");
        add(jmbag);

        JLabel prezime = new JLabel("Prezime: ");
        add(prezime);

        JLabel ime = new JLabel("Ime: ");
        add(ime);

        JTextField jmbagTextField = new JTextField();
        add(jmbagTextField);
        jmbagTextField.setPreferredSize(new Dimension(400, 18));

        JTextField prezimeTextField = new JTextField();
        add(prezimeTextField);
        prezimeTextField.setPreferredSize(new Dimension(400, 18));

        JTextField imeTextField = new JTextField();
        add(imeTextField);
        imeTextField.setPreferredSize(new Dimension(400, 18));

        layout.putConstraint(SpringLayout.WEST, jmbag, 50, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, jmbag, 50, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.NORTH, prezime, 5, SpringLayout.SOUTH, jmbag);
        layout.putConstraint(SpringLayout.EAST, prezime, 0, SpringLayout.EAST, jmbag);
        layout.putConstraint(SpringLayout.NORTH, ime, 5, SpringLayout.SOUTH, prezime);
        layout.putConstraint(SpringLayout.EAST, ime, 0, SpringLayout.EAST, jmbag);

        layout.putConstraint(SpringLayout.WEST, jmbagTextField, 5, SpringLayout.EAST, jmbag);
        layout.putConstraint(SpringLayout.NORTH, jmbagTextField, 50, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, prezimeTextField, 5, SpringLayout.EAST, prezime);
        layout.putConstraint(SpringLayout.NORTH, prezimeTextField, 5, SpringLayout.SOUTH, jmbagTextField);

        layout.putConstraint(SpringLayout.WEST, imeTextField, 5, SpringLayout.EAST, ime);
        layout.putConstraint(SpringLayout.NORTH, imeTextField, 5, SpringLayout.SOUTH, prezimeTextField);
    }
}
