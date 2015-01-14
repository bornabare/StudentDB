package hr.fer.oop.topic10.GraphUsrInterface;

import hr.fer.oop.topic10.db.Database;
import javafx.scene.layout.Pane;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

/**
 * Created by borna on 1/14/15.
 */

public class Frame extends JFrame {

    private static final long serialVersionUID = 1L;
    /**
     * Main constructor.
     * @param studentsPanel student panel
     */
    public Frame(Panel studentsPanel){
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(studentsPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    /**
     * Main method for testing.
     * @param args cmd arguments
     */

    public static void main(String[] args) {
        Database db = new Database("//Users/borna/IdeaProjects/StudentDB/predmeti.txt",
                "//Users/borna/IdeaProjects/StudentDB/studenti.txt",
                "//Users/borna/IdeaProjects/StudentDB/upisaniPredmeti.txt");


        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException
                        | IllegalAccessException
                        | UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
                Panel sp = null;
                try {
                    sp = new Panel(db);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                JFrame frame = new Frame(sp);
                frame.setSize(500, 300);
                frame.setVisible(true);
            }

        });
    }

}


