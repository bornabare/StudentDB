package hr.fer.oop.topic10.gui;

import hr.fer.oop.topic10.db.Database;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by borna on 1/12/15.
 */
public class StudentsFrame extends JFrame {

    public StudentsFrame (Database database){

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
        setDefaultCloseOperation ( WindowConstants.EXIT_ON_CLOSE);
        setTitle("Title");

        StudentsPanel studentsPanel = new StudentsPanel(database, this);
        contentPane.add(studentsPanel,BorderLayout.CENTER);

    }




    public static void main(String[] args) {

        Database database = new Database ("//Users/borna/IdeaProjects/StudentDB/predmeti.txt",
                "//Users/borna/IdeaProjects/StudentDB/studenti.txt",
                "//Users/borna/IdeaProjects/StudentDB/upisaniPredmeti.txt");

        try {
            SwingUtilities.invokeAndWait(() -> {
                StudentsFrame window = new StudentsFrame(database);
                window.setBounds(100, 100, 550, 400);
                window.setVisible(true);
            });
        } catch (InvocationTargetException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
