package edu.chdtu.fitis.tsybenko.db.film;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class FilmMain {

    static List<Film> films;
    static List<Hall> halls;
    static List<JCheckBox> productsCheckBoxes;
    static JPanel filmsPanel = new JPanel();
    static JPanel hallsPanel = new JPanel();
    static JPanel addPanel = new JPanel();
    static JPanel removePanel = new JPanel();
    static JTextField nameTextField = new JTextField();
    static JTextField genreTextField = new JTextField();
    static JTextField durationTextField = new JTextField();
    static JTextField yearTextField = new JTextField();
    static JTextField actorsTextField = new JTextField();
    static List<JLabel> productsLabels;
    static JLabel selectedLabel;
    static Color labelBackgroundColor;
    static JFrame frame = new JFrame();
    static JButton addFilmsBtn = new JButton("Add film");
    static JButton removalFilmBtn = new JButton("Product removal");
    static JButton btnSortName = new JButton("Sort by name");
    static FilmService filmService = new FilmService();

    public static void main(String[] args) {
        //Створити ліст товарів і показати його на екрані, за аналогією, як було з працівниками
        films = filmService.readFilms();
        showFilms();
        showAddPanel();
        showRemovePanel();
        halls = filmService.readHalls();
        showHalls();

        frame.add(filmsPanel);
        frame.add(hallsPanel);

        filmsPanel.setBounds(20, 20, 650, 500);
        filmsPanel.setLayout(null);

        hallsPanel.setBounds(20, 520, 650, 500);
        hallsPanel.setLayout(null);

        removalFilmBtn.setBounds(200, 270, 150, 30);

        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setVisible(true);

        btnSortName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean isSorted = false;
                while(!isSorted) {
                    isSorted = true;
                    for (int i = 0; i < films.size()-1; i++) { //i - параметр циклу
                        if(films.get(i).getName().compareTo(films.get(i+1).getName()) > 0) {
                            isSorted = false;
                            Film buf = films.get(i);
                            films.set(i, films.get(i+1));
                            films.set(i+1, buf);
                        }
                    }
                }
                showFilms();
                repaintWindow();
            }
        });

        JComboBox comboBox = new JComboBox();
        comboBox.addItem("фільми");
        comboBox.addItem("зали");
        comboBox.setBounds(1, 2, 100, 20);
        frame.add(comboBox);
    }


    static void repaintWindow() {
        frame.getContentPane().setVisible(false);
        frame.getContentPane().setVisible(true);
    }

//    public class TestFrame extends JFrame {
//
//        public TestFrame() {
//
//            super("Тестове вікно");
//
//            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//            Font font = new Font("", Font.PLAIN, 18);
//
//            String[] items = {
//                    String.valueOf(films),
//                    String.valueOf(halls),
//            };
//
//            Container content = getContentPane();
//
//            content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
//
//            final JLabel label = new JLabel(" ");
//            label.setAlignmentX(LEFT_ALIGNMENT);
//            label.setFont(font);
//            content.add(label);
//
//            ActionListener actionListener = new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    JComboBox box = (JComboBox) e.getSource();
//                    String item = (String) box.getSelectedItem();
//                    label.setText(item);
//                }
//            };
//
//            JComboBox comboBox = new JComboBox(items);
//            comboBox.setFont(font);
//            comboBox.setAlignmentX(LEFT_ALIGNMENT);
//            comboBox.addActionListener(actionListener);
//            content.add(comboBox);
//
//            JComboBox editComboBox = new JComboBox(items);
//            editComboBox.setEditable(true);
//            editComboBox.setAlignmentX(LEFT_ALIGNMENT);
//            editComboBox.setFont(font);
//            editComboBox.addActionListener(actionListener);
//            content.add(editComboBox);
//
//            setPreferredSize(new Dimension(240, 130));
//            pack();
//            setLocationRelativeTo(null);
//            setVisible(true);
//        }
//
//        public static void main(String[] args) {
//            javax.swing.SwingUtilities.invokeLater(new Runnable() {
//                public void run() {
//                    JFrame.setDefaultLookAndFeelDecorated(true);
//                    new TestFrame();
//                }
//            });
//        }
//    }

    static void showFilms() {
        final int LEFT_X = 10;
        final int HEADER_Y = 1;
        productsLabels = new ArrayList<>();
        filmsPanel.removeAll();
        JLabel labelHeader1 = new JLabel("Ім'я");
        filmsPanel.add(labelHeader1);
        labelHeader1.setBounds(LEFT_X , HEADER_Y, 190, 30);
        labelBackgroundColor = labelHeader1.getBackground();

        JLabel labelHeader2 = new JLabel("Жанр");
        filmsPanel.add(labelHeader2);
        labelHeader2.setBounds(LEFT_X + 90, HEADER_Y, 190, 30);

        JLabel labelHeader3 = new JLabel("Тривалість");
        filmsPanel.add(labelHeader3);
        labelHeader3.setBounds(LEFT_X + 190, HEADER_Y, 70, 30);

        JLabel labelHeader4 = new JLabel("Рік");
        filmsPanel.add(labelHeader4);
        labelHeader4.setBounds(LEFT_X + 260, HEADER_Y, 100, 30);

        JLabel labelHeader5 = new JLabel("Актори");
        filmsPanel.add(labelHeader5);
        labelHeader5.setBounds(LEFT_X + 330, HEADER_Y, 300, 30);

        for (int i = 0; i < films.size(); i++) {
            JLabel label1 = new JLabel(films.get(i).getName());
            JLabel label2 = new JLabel(films.get(i).getGenre());
            JLabel label3 = new JLabel(""+films.get(i).getDuration());
            JLabel label4 = new JLabel(""+films.get(i).getYear());
            JLabel label5 = new JLabel(films.get(i).getActors());

            productsLabels.add(label1);
            filmsPanel.add(label1);
            label1.setBounds(LEFT_X, 25 + i * 30, 90, 30);
            filmsPanel.add(label2);
            label2.setBounds(LEFT_X + 90, 25 + i * 30, 190, 30);
            filmsPanel.add(label3);
            label3.setBounds(LEFT_X + 190, 25 + i * 30, 190, 30);
            filmsPanel.add(label4);
            label4.setBounds(LEFT_X + 260, 25 + i * 30, 190, 30);
            filmsPanel.add(label5);
            label5.setBounds(LEFT_X + 330, 25 + i * 30, 190, 30);

            //productsPanel.add(productsCheckBoxes.get(i));
            //productsCheckBoxes.get(i).setBounds(1, 10 + i * 30, 20, 30);
        }
        labelHeader1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean isSorted = false;
                while(!isSorted) {
                    isSorted = true;
                    for (int i = 0; i < films.size()-1; i++) { //i - параметр циклу
                        if(films.get(i).getName().compareTo(films.get(i+1).getName()) > 0) {
                            isSorted = false;
                            Film buf = films.get(i);
                            films.set(i, films.get(i+1));
                            films.set(i+1, buf);
                        }
                    }
                }
                showFilms();
            }
        });
    }

    static void showHalls() {
        final int LEFT_X = 10;
        final int HEADER_Y = 1;
        productsLabels = new ArrayList<>();
        hallsPanel.removeAll();
        JLabel labelHeader1 = new JLabel("Ім'я");
        hallsPanel.add(labelHeader1);
        labelHeader1.setBounds(LEFT_X, HEADER_Y, 220, 30);
        labelBackgroundColor = labelHeader1.getBackground();

        JLabel labelHeader2 = new JLabel("Для дітей");
        hallsPanel.add(labelHeader2);
        labelHeader2.setBounds(LEFT_X + 90, HEADER_Y, 220, 30);

        JLabel labelHeader3 = new JLabel("Номер місця");
        hallsPanel.add(labelHeader3);
        labelHeader3.setBounds(LEFT_X + 190, HEADER_Y, 80, 30);

        for (int i = 0; i < halls.size(); i++) {
            JLabel label1 = new JLabel(halls.get(i).getName());
            JLabel label2 = new JLabel(halls.get(i).getForChildren());
            JLabel label3 = new JLabel(""+halls.get(i).getPlaceNumber());


            productsLabels.add(label1);
            hallsPanel.add(label1);
            label1.setBounds(LEFT_X, 25 + i * 30, 90, 30);
            hallsPanel.add(label2);
            label2.setBounds(LEFT_X + 90, 25 + i * 30, 190, 30);
            hallsPanel.add(label3);
            label3.setBounds(LEFT_X + 190, 25 + i * 30, 190, 30);

        }
    }

    static void showRemovePanel() {

    }

    static void showAddPanel() {
        addPanel.setLayout(null);
        addPanel.add(nameTextField);
        addPanel.add(genreTextField);
        addPanel.add(durationTextField);
        addPanel.add(yearTextField);
        addPanel.add(actorsTextField);

        btnSortName.setBounds(10, 30, 120, 20);
        nameTextField.setBounds(10, 10, 150, 30);
        genreTextField.setBounds(10, 50, 150, 30);
        durationTextField.setBounds(10, 90, 150, 30);
        yearTextField.setBounds(10, 130, 150, 30);
        actorsTextField.setBounds(10, 170, 150, 30);
        addFilmsBtn.setBounds(10, 210, 150, 30);
        addPanel.add(addFilmsBtn);
        frame.add(addPanel);
        addPanel.setBounds(600, 50, 200, 250);
        addFilmsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Film film = new Film();
                film.setName(nameTextField.getText());
                film.setGenre(genreTextField.getText());
                film.setDuration(Integer.parseInt(durationTextField.getText()));
                film.setYear(Integer.parseInt(yearTextField.getText()));
                film.setActors(actorsTextField.getText());
                films.add(film);
                filmService.addFilm(film);
                showFilms();
            }
        });
    }

    static List<Film> createFilmsList() {
        List<Film> films = new ArrayList<>();
        Film film1 = new Film();
        film1.setName("Гетьман");
        film1.setDuration(80);
        film1.setGenre("мелодрама");
        film1.setYear(2010);
        film1.setActors("Mark Smith, Jack Hamilton");
        films.add(film1);

        Film film2 = new Film();
        film2.setName("Вальс");
        film2.setDuration(80);
        film2.setGenre("історичний");
        film2.setYear(2007);
        film2.setActors("Max Ivanov, Oleg Lyknenko");
        films.add(film2);

        Film film3 = new Film();
        film3.setName("Листопад");
        film3.setDuration(80);
        film3.setGenre("драма");
        film3.setYear(2012);
        film3.setActors("Kevin Tax");
        films.add(film3);

        return films;
    }
}
