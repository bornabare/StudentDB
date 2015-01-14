package hr.fer.oop.topic10.GraphUsrInterface;
import hr.fer.oop.topic10.db.CourseDatabase;
import hr.fer.oop.topic10.db.CourseRecord;
import hr.fer.oop.topic10.db.Database;
import hr.fer.oop.topic10.db.EnrolmentRecord;
import hr.fer.oop.week9.db.StudentDatabase;
import hr.fer.oop.week9.db.StudentRecord;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

/**
 * Created by borna on 1/14/15.
 */

public class Panel extends JPanel {

    private static final long serialVersionUID = 1L;

    private JComboBox<StudentRecord> comboBoxStudents = new JComboBox<StudentRecord>();
    private JComboBox<CourseRecord> comboBoxCourses = new JComboBox<CourseRecord>();

    private JLabel jmbagLabel = new JLabel("JMBAG: ", SwingConstants.RIGHT);
    private JLabel lNameLabel = new JLabel("Prezime: ", SwingConstants.RIGHT);
    private JLabel nameLabel = new JLabel("Ime: ", SwingConstants.RIGHT);
    private JLabel jmbagField = new JLabel("", SwingConstants.LEFT);
    private JLabel lNameField = new JLabel("", SwingConstants.LEFT);
    private JLabel nameField = new JLabel("", SwingConstants.LEFT);
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JPanel enrolledCoursesPanel = new JPanel();
    private JPanel courseDetailsPanel = new JPanel();
    private JPanel studentsPanel = new JPanel();
    private JPanel coursePanel = new JPanel();
    private JLabel studentName = new JLabel("Ime i prezime");
    private JLabel studentGrade = new JLabel("Ocjena");
    /**
     * Main constructor for creating panel.
     */
    private Database db;
    public Panel (Database db) throws IOException{
        this.db = db;
        createGUI();
        createActions();
    }
    /**
     * Method for creating actions.
     * @throws IOException
     */

    private void createActions() throws IOException {
        fillComboBoxes();
        comboBoxStudents.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int index = comboBoxStudents.getSelectedIndex();
                if(index != -1){
                    StudentRecord sr = comboBoxStudents.getItemAt(index);
                    jmbagField.setText(sr.getJmbag());
                    nameField.setText(sr.getFirstName());
                    lNameField.setText(sr.getLastName());

                    Collection<EnrolmentRecord> enrolments = db.getEnrolmentTable().findByStudent(sr.getJmbag());
                    enrolledCoursesPanel.removeAll();
                    enrolledCoursesPanel.setLayout(new GridLayout(enrolments.size(), 1));
                    for(EnrolmentRecord enrolment : enrolments){
                        String courseName = db.getCourseTable().findById(enrolment.getCourseID()).getCourseName();
                        JButton button = new JButton(courseName);
                        button.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JOptionPane.showMessageDialog(Panel.this, "Ocjena uèenika " + sr + " iz predmeta " + courseName + " je: " + enrolment.getGrade(), "Ocjena", JOptionPane.INFORMATION_MESSAGE);

                            }
                        });
                        enrolledCoursesPanel.add(button);
                    }
                    repaint();
                    revalidate();
                }

            }
        });
        comboBoxCourses.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int index = comboBoxCourses.getSelectedIndex();
                if(index != -1){
                    CourseRecord cr = comboBoxCourses.getItemAt(index);
                    Collection<EnrolmentRecord> enrolments = db.getEnrolmentTable().findByCourse(cr.getCourseID());
                    courseDetailsPanel.removeAll();
                    courseDetailsPanel.setLayout(new GridLayout(enrolments.size()+1, 3, 0, 5));
                    studentName.setHorizontalAlignment(JLabel.LEFT);
                    courseDetailsPanel.add(studentName);
                    studentGrade.setHorizontalAlignment(JLabel.RIGHT);
                    courseDetailsPanel.add(studentGrade);
                    JLabel empty = new JLabel("");
                    courseDetailsPanel.add(empty);
                    int cnt = 0;
                    for(EnrolmentRecord er : enrolments){
                        StudentRecord name = db.getStudentTable().getJmbagMap().get(er.getJmbag());
                        JLabel detailNameLabel = new JLabel("");
                        JLabel gradeLabel = new JLabel("");
                        detailNameLabel.setText(name.toString());
                        detailNameLabel.setHorizontalAlignment(JLabel.LEFT);
                        detailNameLabel.setOpaque(true);
                        gradeLabel.setText(er.getGrade().toString());
                        gradeLabel.setHorizontalAlignment(JLabel.RIGHT);
                        gradeLabel.setOpaque(true);
                        ++cnt;
                        if(cnt % 2 == 1){
                            detailNameLabel.setBackground(Color.GRAY);
                            gradeLabel.setBackground(Color.GRAY);
                        }
                        else{
                            detailNameLabel.setBackground(Color.LIGHT_GRAY);
                            gradeLabel.setBackground(Color.LIGHT_GRAY);
                        }
                        courseDetailsPanel.add(detailNameLabel);
                        courseDetailsPanel.add(gradeLabel);
                        JButton details = new JButton("Detalji");
                        details.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                comboBoxStudents.setSelectedItem(name);
                                tabbedPane.setSelectedIndex(0);
                            }
                        });
                        courseDetailsPanel.add(details);

                    }
                    repaint();
                    revalidate();


                }

            }
        });
    }
    /**
     * Method for filling combo boxes.
     * @throws IOException
     */

    private void fillComboBoxes() throws IOException {
        StudentDatabase studentDB = db.getStudentTable();
        for(StudentRecord sr : studentDB.getStudents()){
            comboBoxStudents.addItem(sr);
        }
        comboBoxStudents.setSelectedItem(null);

        CourseDatabase courseDB = db.getCourseTable();
        for(CourseRecord cr : courseDB.getCourses()){
            comboBoxCourses.addItem(cr);
        }
        comboBoxCourses.setSelectedItem(null);
    }

    /**
     * Method for creating GUI.
     */
    private void createGUI() {
        setLayout(new BorderLayout());
        jmbagField.setBackground(Color.white);
        jmbagField.setOpaque(true);
        lNameField.setBackground(Color.white);
        lNameField.setOpaque(true);
        nameField.setBackground(Color.white);
        nameField.setOpaque(true);
        add(tabbedPane, BorderLayout.CENTER);
        tabbedPane.addTab("Studenti", studentsPanel);
        tabbedPane.addTab("Predmeti", coursePanel);
        studentsPanel.setLayout(new BorderLayout());
        studentsPanel.add(comboBoxStudents, BorderLayout.NORTH);
        JPanel formLabelsPanel = new JPanel(new GridLayout(3, 1, 4, 4));
        formLabelsPanel.add(jmbagLabel);
        formLabelsPanel.add(lNameLabel);
        formLabelsPanel.add(nameLabel);
        JPanel formFieldsPanel = new JPanel(new GridLayout(3, 1, 4, 4));
        formFieldsPanel.add(jmbagField);
        formFieldsPanel.add(lNameField);
        formFieldsPanel.add(nameField);
        studentsPanel.add(formFieldsPanel, BorderLayout.CENTER);
        studentsPanel.add(formLabelsPanel, BorderLayout.WEST);
        enrolledCoursesPanel.setBorder(BorderFactory.createTitledBorder("Upisani predmeti"));
        studentsPanel.add(new JScrollPane(enrolledCoursesPanel), BorderLayout.SOUTH);
        coursePanel.setLayout(new BorderLayout());
        coursePanel.add(comboBoxCourses, BorderLayout.NORTH);
        coursePanel.add(new JScrollPane(courseDetailsPanel), BorderLayout.CENTER);
    }
}
